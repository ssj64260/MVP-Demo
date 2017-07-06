package com.cxb.mvp_project.ui.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.cxb.mvp_project.ui.fragment.GankNewsFragment;

import java.util.ArrayList;

/**
 * 干货 ViewPager 适配器
 */

public class GankFragmentPagerAdapter extends FragmentPagerAdapter {

    private ArrayList<Fragment> mFragments;
    private String[] mTitles;

    public GankFragmentPagerAdapter(FragmentManager fm, String[] titles) {
        super(fm);

        this.mTitles = titles;
        mFragments = new ArrayList<>();
        for (String title : mTitles) {
            mFragments.add(GankNewsFragment.newInstance(title));
        }
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mTitles[position];
    }

    @Override
    public Fragment getItem(int position) {
        return mFragments.get(position);
    }

    @Override
    public int getCount() {
        return mFragments.size();
    }
}
