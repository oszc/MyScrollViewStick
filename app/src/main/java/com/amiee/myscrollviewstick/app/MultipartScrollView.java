package com.amiee.myscrollviewstick.app;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Rect;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ListView;
import android.widget.ScrollView;

import java.util.NoSuchElementException;

/**
 * 1/13/15  10:21 AM
 * Created by JustinZhang.
 */
public class MultipartScrollView extends ScrollView {

    private static final String TAG = MultipartScrollView.class.getSimpleName();
    private ListView mListview;

    public MultipartScrollView(Context context) {
        super(context);
      //  shareConstructor();
    }

    public MultipartScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
       // shareConstructor();
    }

    public MultipartScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
       // shareConstructor();
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public MultipartScrollView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
      //  shareConstructor();
    }

    @Override
    public void onWindowFocusChanged(boolean hasWindowFocus) {
        super.onWindowFocusChanged(hasWindowFocus);
        shareConstructor();
    }

    private void shareConstructor() {

        int listViewId = getResources().getIdentifier("lv", "id", getContext().getPackageName());

        if(listViewId == 0){
            throw new NoSuchElementException("Did your view with id \"lv\" exists?");
        }
        mListview = (ListView)findViewById(listViewId);
        mListview.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {

            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

                if(firstVisibleItem == 0){
                    reachBottom = false;
                }
            }
        });
    }

    private float mDownY = 0;
    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        if(!reachBottom){
            return true;
        }else{
            return false;
        }
    }

    private boolean reachBottom = false;
    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        View view = (View) getChildAt(getChildCount()-1);
        int diff = (view.getBottom()-(getHeight()+getScrollY()+view.getTop()));// Calculate the scrolldiff
        if( diff == 0 ){  // if diff is zero, then the bottom has been reached
            Log.d(TAG, "MyScrollView: Bottom has been reached" );
            reachBottom = true;
        }
        super.onScrollChanged(l, t, oldl, oldt);
    }

}
