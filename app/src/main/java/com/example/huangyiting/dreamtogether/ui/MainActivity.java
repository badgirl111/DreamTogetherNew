package com.example.huangyiting.dreamtogether.ui;



import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.TextView;
import com.example.huangyiting.dreamtogether.R;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;


public class MainActivity extends FragmentActivity implements View.OnClickListener{

    private Fragment[] mFragments;//fragment数组
    private TextView mTimeLifeMenu;//menu中生之时选项
    private TextView mRiverWishMenu;//menu中心愿之河选项
    private TextView mBackGroudSetMenu;//menu中背景设置选项
    private TextView mFeedBackMenu; //menu中意见反馈选项
    private TextView mVersionUpdateMenu; //menu中版本更新选项
    public static SlidingMenu menu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*设置slidingmenu内容*/
        menu = new SlidingMenu(this);
        menu.setMode(SlidingMenu.LEFT);
        /*设置menu触摸屏幕的模式*/
        menu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
        menu.setShadowWidthRes(R.dimen.shadow_width);
        /*设置menu菜单视图滑动的宽度*/
        menu.setBehindOffsetRes(R.dimen.slidingmenu_offset);
        menu.setFadeDegree(0.5f);
        /*设置menu的背景颜色*/
        menu.setBackgroundResource(R.drawable.gray);
        /*将menu菜单和activity绑定*/
        menu.attachToActivity(this,SlidingMenu.SLIDING_CONTENT);
        /*menu布局绑定*/
        menu.setMenu(R.layout.slidingmenu);
        /*初始化menu布局*/
        mTimeLifeMenu = (TextView)menu.findViewById(R.id.at_the_time_of_life);
        mRiverWishMenu = (TextView)menu.findViewById(R.id.river_of_wish);
        mBackGroudSetMenu = (TextView)menu.findViewById(R.id.backgroud_set);
        mFeedBackMenu = (TextView)menu.findViewById(R.id.feedback);
        mVersionUpdateMenu = (TextView)menu.findViewById(R.id.version_update);
        /*设置menu选项监听*/
        mTimeLifeMenu.setOnClickListener(this);
        mRiverWishMenu.setOnClickListener(this);
        mBackGroudSetMenu.setOnClickListener(this);
        mFeedBackMenu.setOnClickListener(this);
        mVersionUpdateMenu.setOnClickListener(this);
        /*初始化fragment布局，且初次打开页面，首先显示生之时fragment*/
        mFragments = new Fragment[5];
        mFragments[0]= getSupportFragmentManager().findFragmentById(R.id.at_the_time_of_life);
        mFragments[1]= getSupportFragmentManager().findFragmentById(R.id.river_of_wish);
        mFragments[2]= getSupportFragmentManager().findFragmentById(R.id.backgroud_set);
        mFragments[3]= getSupportFragmentManager().findFragmentById(R.id.feedback);
        mFragments[4]= getSupportFragmentManager().findFragmentById(R.id.version_update);
        getSupportFragmentManager().beginTransaction().hide(mFragments[1]).hide(mFragments[2]).hide(mFragments[3]).hide(mFragments[4]).show(mFragments[0]).commit();

    }

//    @Override
    public void onClick(View v) {
          switch (v.getId()){
              case R.id.at_the_time_of_life:
                  getSupportFragmentManager().beginTransaction().hide(mFragments[1]).hide(mFragments[2]).hide(mFragments[3]).hide(mFragments[4]).show(mFragments[0]).commit();
                  menu.showContent();
                  break;
              case R.id.river_of_wish:
                  getSupportFragmentManager().beginTransaction().hide(mFragments[0]).show(mFragments[1]).hide(mFragments[2]).hide(mFragments[3]).hide(mFragments[4]).commit();
                  menu.showContent();
                  break;
              case R.id.backgroud_set:
                  getSupportFragmentManager().beginTransaction().hide(mFragments[0]).hide(mFragments[1]).show(mFragments[2]).hide(mFragments[3]).hide(mFragments[4]).commit();
                  menu.showContent();
                  break;
              case R.id.feedback:
                  getSupportFragmentManager().beginTransaction().hide(mFragments[0]).hide(mFragments[1]).hide(mFragments[2]).show(mFragments[3]).hide(mFragments[4]).commit();
                  menu.showContent();
                  break;
              case R.id.version_update:
                  getSupportFragmentManager().beginTransaction().hide(mFragments[0]).hide(mFragments[1]).hide(mFragments[2]).hide(mFragments[3]).show(mFragments[4]).commit();
                  menu.showContent();
                  break;
          }
    };
}