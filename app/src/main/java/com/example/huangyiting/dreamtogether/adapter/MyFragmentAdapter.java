package com.example.huangyiting.dreamtogether.adapter;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import java.util.List;

/**
 * Created by huangyiting on 2015/10/20.
 */
public class MyFragmentAdapter extends FragmentPagerAdapter {
    public List<Fragment> mFragments;



    public MyFragmentAdapter(FragmentManager fm, List<Fragment> mFragments){
        super(fm);
        this.mFragments = mFragments;

    }

    @Override
    public Fragment getItem(int position) {
        return mFragments.get(position);
    }

    @Override
    public int getCount() {
        return mFragments.size();
    }


/*    @Override
    public CharSequence getPageTitle(int position) {
        return mTitleList.get(position);
    }*/



}
