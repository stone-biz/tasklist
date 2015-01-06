package com.stone.tasklist;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;

import android.support.v4.view.ViewPager;

import com.viewpagerindicator.CirclePageIndicator;

/**
 * Created by Administrator on 2015/1/6.
 */
public class MainActivity extends ActionBarActivity {

    private ViewPager mPager;
    private CirclePageIndicator mCirclePageIndicator;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
    }

    private void initView() {
        mPager = (ViewPager) findViewById(R.id.pager);
        mCirclePageIndicator = (CirclePageIndicator) findViewById(R.id.indicator);
//        mCirclePageIndicator.setRadius();

    }
}
