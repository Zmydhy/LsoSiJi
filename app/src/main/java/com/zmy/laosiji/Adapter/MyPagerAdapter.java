package com.zmy.laosiji.Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.zmy.laosiji.Fragment.MainFragment;

import java.util.ArrayList;

/**
 * Created by hugeterry(http://hugeterry.cn)
 * Date: 16/1/28 17:24
 */
public class MyPagerAdapter extends FragmentPagerAdapter {
    private ArrayList<MainFragment> mFragments = new ArrayList<>();
    private String[] mTitles;

    public MyPagerAdapter(FragmentManager fm, ArrayList<MainFragment> mFragments, String[] mTitles) {
        super(fm);
        this.mFragments = mFragments;
        this.mTitles = mTitles;
    }

    @Override
    public int getCount() {
        return mFragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mTitles[position];
    }

    @Override
    public Fragment getItem(int position) {
        return mFragments.get(position);
    }
}
