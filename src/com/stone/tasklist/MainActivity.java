package com.stone.tasklist;

import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;

import android.support.v4.view.ViewPager;
import android.widget.Toast;

import com.stone.tasklist.biz.LoadTask;
import com.stone.tasklist.entity.Tasklist;
import com.viewpagerindicator.CirclePageIndicator;

import java.util.List;

/**
 * Created by Administrator on 2015/1/6.
 */
public class MainActivity extends ActionBarActivity implements LoadTask.Callback {

    private ViewPager mPager;
    private CirclePageIndicator mCirclePageIndicator;

    private MainAdapter mAdapter;
    private List<Tasklist> mData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        loadData();
    }

    private void loadData() {
        new LoadTask(this).start();
    }

    private void initView() {
        mPager = (ViewPager) findViewById(R.id.pager);
        mCirclePageIndicator = (CirclePageIndicator) findViewById(R.id.indicator);
        setViewIndicator();

        mAdapter = new MainAdapter(getSupportFragmentManager(), null);
        mPager.setAdapter(mAdapter);
        mCirclePageIndicator.setViewPager(mPager);
    }

    private void setViewIndicator() {
        Resources res = getResources();
        mCirclePageIndicator.setRadius(res.getDimension(R.dimen.page_indicator_radius));
        mCirclePageIndicator.setCentered(true);
        mCirclePageIndicator.setFillColor(res.getColor(R.color.holo_green_dark));
        mCirclePageIndicator.setPageColor(res.getColor(R.color.holo_blue_bright));
    }

    @Override
    public void onLoadFinised(List<Tasklist> data) {
        mData = data;
        mAdapter.addAll(mData);
    }

    @Override
    public void onLoadFaild() {
        Toast.makeText(this,"加载失败", Toast.LENGTH_SHORT).show();
    }
}
