package com.stone.tasklist;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.stone.tasklist.entity.Tasklist;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2015/1/6.
 */
public class MainAdapter extends FragmentStatePagerAdapter{

    private List<Tasklist> mData;
    private List<Fragment> mFragments;


    public MainAdapter(FragmentManager fm, List<Tasklist> data) {
        super(fm);
        mData = data;
        if(data == null) {
            mData = new ArrayList<Tasklist>();
        }
    }

    @Override
    public Fragment getItem(int position) {
        if(mFragments.size()-1 < position) {
            TasklistFragment fg = new TasklistFragment();
            Bundle b = new Bundle();
            b.putParcelable("data", mData.get(position));
            fg.setArguments(b);
            mFragments.add(fg);
        }
        return mFragments.get(position);
    }

    @Override
    public int getCount() {
        return mData.size();
    }

    public void addAll(List<Tasklist> data) {
        if(data == null) return;
        mData.addAll(data);
        notifyDataSetChanged();
    }
}
