package com.zmy.laosiji.moudle.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import com.zmy.laosiji.R;
import com.zmy.laosiji.base.BaseActivity;
import com.zmy.laosiji.moudle.fragment.ShaderFragment;
import com.zmy.laosiji.moudle.fragment.TuoDongFragment;
import com.zmy.laosiji.moudle.fragment.ViewFragment;

import java.util.ArrayList;
import java.util.List;

public class CustomViewActivity extends BaseActivity {

    ViewPager viewpagerFragment;
    List<Fragment> mList = new ArrayList<>();




    @Override
    protected void setContentView(Bundle savedInstanceState) {
        setContentLayout(R.layout.activity_custom_view);
        viewpagerFragment = (ViewPager) findViewById(R.id.view_pager_fragment);
        ViewFragment viewFragment = new ViewFragment();
        ShaderFragment shaderFragment = new ShaderFragment();
        TuoDongFragment tuoDongFragment = new TuoDongFragment();

        mList.add(viewFragment);
        mList.add(shaderFragment);
        mList.add(tuoDongFragment);
        CircleAdapter mAdapter = new CircleAdapter(getSupportFragmentManager(),mList);
        viewpagerFragment.setAdapter(mAdapter);
    }

    class CircleAdapter extends FragmentPagerAdapter {
        private List<Fragment> listFragments;
        public CircleAdapter(FragmentManager fm ,List<Fragment> al ) {
            super(fm);
            listFragments = al;
        }

        @Override
        public Fragment getItem(int position) {
            return listFragments.get(position);
        }

        @Override
        public int getCount() {
            return listFragments == null ? 0 : mList.size();
        }

    }

}


