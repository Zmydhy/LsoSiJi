package com.zmy.laosiji.Activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.zmy.laosiji.Fragment.ShaderFragment;
import com.zmy.laosiji.Fragment.TuoDongFragment;
import com.zmy.laosiji.Fragment.ViewFragment;
import com.zmy.laosiji.R;

import java.util.ArrayList;
import java.util.List;

public class CustomViewActivity extends AppCompatActivity {

    ViewPager viewpagerFragment;
    List<Fragment> mList = new ArrayList<>();
    String[] titles = {"对号","刮刮乐"};
    int[] layoutIds = {R.layout.fragment_ciecle_duihao,R.layout.fragment_guaguale};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_view);
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


