package com.example.huangyiting.dreamtogether.ui.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;


import com.example.huangyiting.dreamtogether.R;
import com.example.huangyiting.dreamtogether.adapter.MyFragmentAdapter;
import java.util.ArrayList;
import java.util.List;

public class RiverOfWishFragment extends Fragment implements ViewPager.OnPageChangeListener,View.OnClickListener {
    public ViewPager mViewPager;
    public MyFragmentAdapter mAdapter;
/*    public PagerTitleStrip mTitle;*/
    public List<Fragment> mFragments;
/*    public List<String> mTitleList;*/
    public FragmentActivity mFragmentActivity;
    public List<LinearLayout> mItemList;
    public List<View> mTabList;
    public View mTabOne;
    public View mTabTwo;


    public RiverOfWishFragment() {
        // Required empty public constructor
    }
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mFragmentActivity = (FragmentActivity) activity;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        /*填充布局view*/
        View view = inflater.inflate(R.layout.fragment_river_of_wish, container, false);
        // Inflate the layout for this fragment
        /*初始化viewpager和pagertitlestrip*/
        mViewPager = (ViewPager)view.findViewById(R.id.rw_viewpager);
/*        mTitle = (PagerTitleStrip)view.findViewById(R.id.title);*/
        /*初始化titlelist并且添加每个title内容*/
/*        mTitleList = new ArrayList<>();
        mTitleList.add(getResources().getString(R.string.all_wishes));
        mTitleList.add(getResources().getString(R.string.my_wishes));*/
        /*初始化mItemList并添加每个Item内容*/
        mItemList = new ArrayList<>() ;
        mItemList.add((LinearLayout)view.findViewById(R.id.item_one));
        mItemList.add((LinearLayout)view.findViewById(R.id.item_two));
        mItemList.get(0).setOnClickListener(this);
        mItemList.get(1).setOnClickListener(this);
        /*初始化mTabList并添加每个tab内容*/
        mTabList = new ArrayList<>();
        mTabList.add((View) view.findViewById(R.id.tab_one));
        mTabList.add((View)view.findViewById(R.id.tab_two));
        mTabList.get(0).setVisibility(View.VISIBLE);
        mTabList.get(1).setVisibility(View.INVISIBLE);

        /*初始化mFragments并且添加每个fragment*/
        mFragments = new ArrayList<>();
        mFragments.add(new AllWishesFragment());
        mFragments.add(new MyWishesFragment());
        /*初始化MyFragmentAdapter*/
        mAdapter = new MyFragmentAdapter(mFragmentActivity.getSupportFragmentManager(),mFragments);
        mViewPager.setAdapter(mAdapter);
        mViewPager.setOnPageChangeListener(this);

        return view;
    }


    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {

        for (int i = 0 ; i < mTabList.size(); i++){
            if(i != position) {
                mTabList.get(i).setVisibility(View.INVISIBLE);
            } else {
                mTabList.get(position).setVisibility(View.VISIBLE);
            }

        }

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.item_one:
                mViewPager.setCurrentItem(0);
                break;
            case R.id.item_two:
                mViewPager.setCurrentItem(1);
                break;
            default:
                break;
        }
    }
}
