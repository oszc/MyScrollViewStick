package com.amiee.myscrollviewstick.app;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import butterknife.ButterKnife;
import butterknife.InjectView;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends ActionBarActivity {

    @InjectView(R.id.lv)
    ListView mLv;
    @InjectView(R.id.sv)
    MultipartScrollView mSv;

    private List<String> mData;
    private Screen mScreen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);

        mScreen = new Screen(this);
        mLv.post(new Runnable() {
            @Override
            public void run() {
                  mLv.getLayoutParams().height  = mSv.getHeight();
                mSv.scrollTo(0,0);

            }
        });
    //    mLv.getLayoutParams().height = mScreen.getHeight();

        mData = new ArrayList<String>();
        for (int i = 0; i < 100; i++) {
            mData.add(i + "");
        }

        mLv.setAdapter(new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_list_item_1, mData));

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
