package com.example.huangyiting.dreamtogether.ui.fragment;


import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import android.os.Handler;
import android.os.Message;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AnalogClock;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.huangyiting.dreamtogether.R;
import com.example.huangyiting.dreamtogether.ui.BornTimeSetActivity;
import com.example.huangyiting.dreamtogether.ui.MainActivity;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;


public class AtTimeOfLiveFragment extends Fragment implements View.OnClickListener {

    public int requestCode = 1;
    private ImageView mBackToSetting;
    private Button mCheckBornTime;
    private int mBornYear;
    private int mBornMonth;
    private int mBornDate;
    private int mCurrentYear;
    private int mCurrentMonth;
    private int mCurrentDate;
    private SimpleDateFormat mDateFormat;
    private Date mCurDate;
    private Date mSetDate;
    public int mDifYear;
    public int mDifMonth;
    public long mDifWeek;
    public long mDifdate;
    public long mDifHour;
    public long mDifMin;
    static public double mDifSeconds;
    public TextView mAgeTextView;
    public TextView mTimeSetHint;
    public DecimalFormat decimalFormat = new DecimalFormat("0.00000000");
    public AnalogClock analogClock;
    public ImageView dataForm;
    public TextView mYear;
    public TextView mMonth;
    public TextView mWeek;
    public TextView mDay;
    public TextView mHour;
    public TextView mMin;
    public RelativeLayout timeSetOverLayout;
    protected SharedPreferences mSharePreferences;
    protected SharedPreferences.Editor mEditor;

    public AtTimeOfLiveFragment(){

    }
    public void onAttach(Activity activity) {
        super.onAttach(activity);

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.at_the_time_of_life, container, true);
        mBackToSetting = (ImageView) view.findViewById(R.id.back_to_setting);
        mCheckBornTime = (Button)view.findViewById(R.id.check_born_time);
        mAgeTextView = (TextView) view.findViewById(R.id.dif_age);
        mTimeSetHint = (TextView) view.findViewById(R.id.born_time_set_hint);
        analogClock = (AnalogClock) view.findViewById(R.id.analogClock);
        dataForm = (ImageView) view.findViewById(R.id.six_data_form);
        mYear = (TextView) view.findViewById(R.id.year);
        mMonth = (TextView) view.findViewById(R.id.month);
        mWeek = (TextView) view.findViewById(R.id.week);
        mDay = (TextView) view.findViewById(R.id.day);
        mHour = (TextView) view.findViewById(R.id.hour);
        mMin = (TextView) view.findViewById(R.id.min);
        timeSetOverLayout = (RelativeLayout) view.findViewById(R.id.time_set_over);
        mSharePreferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
        mEditor = mSharePreferences.edit();
        mBackToSetting.setOnClickListener(this);
        mCheckBornTime.setOnClickListener(this);
        if (mSharePreferences.getBoolean("isFirst", false)) {
            mBornYear = mSharePreferences.getInt("mBornYear", Calendar.getInstance().get(Calendar.YEAR));
            mBornMonth = mSharePreferences.getInt("mBornMonth", Calendar.getInstance().get(Calendar.MONTH));
            mBornDate = mSharePreferences.getInt("mBornDate", Calendar.getInstance().get(Calendar.DATE));
            refreshUI();
        }
        return view;

    }




    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back_to_setting:
                MainActivity.menu.showMenu();
                break;
            case R.id.check_born_time:
                if (mSharePreferences.getBoolean("isFirst", false)) {
                    AnimatorSet animatorSet = new AnimatorSet();
                    ObjectAnimator mClockAnimator = ObjectAnimator.ofFloat(analogClock, "translationY", -300F, 0F);
                    ObjectAnimator mDataFormAnimator = ObjectAnimator.ofFloat(timeSetOverLayout, "alpha", 1F, 0F);
                    ObjectAnimator mAgeAnimator = ObjectAnimator.ofFloat(mAgeTextView, "alpha", 1F, 0F);
                    animatorSet.playSequentially(mDataFormAnimator, mClockAnimator);
                    animatorSet.playTogether(mDataFormAnimator, mAgeAnimator);
                    animatorSet.setDuration(800);
                    animatorSet.start();
                }
                Intent intent = new Intent();
                intent.setClass(getActivity(), BornTimeSetActivity.class);
                startActivityForResult(intent, requestCode);
                break;
            default:
        }
    }
    // 出生时间设置返回结果处理
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (resultCode) {
            //时间设置确定后返回，获取设置的时间，并设定timer计时器，每隔1秒执行一次myTask线程
            case 1:

                mBornYear = data.getIntExtra("mBornYear", Calendar.getInstance().get(Calendar.YEAR));
                mBornMonth = data.getIntExtra("mBornMonth", Calendar.getInstance().get(Calendar.MONTH));
                mBornDate = data.getIntExtra("mBornDate", Calendar.getInstance().get(Calendar.DATE));
                mEditor.putBoolean("isFirst",true);
                mEditor.putInt("mBornYear",mBornYear);
                mEditor.putInt("mBornMonth",mBornMonth);
                mEditor.putInt("mBornDate",mBornDate);
                mEditor.commit();
                //首页页面更新
                refreshUI();
                break;

            case 2:
                if(mSharePreferences.getBoolean("isFirst",false)){
                    mBornYear = mSharePreferences.getInt("mBornYear",Calendar.getInstance().get(Calendar.YEAR));
                    mBornMonth = mSharePreferences.getInt("mBornMonth",Calendar.getInstance().get(Calendar.MONTH));
                    mBornDate = mSharePreferences.getInt("mBornDate",Calendar.getInstance().get(Calendar.DATE));
                    refreshUI();
                }
                break;

        }
    } ;

    private void refreshUI(){
        //设置属性动画
        AnimatorSet animatorSet = new AnimatorSet();
        ObjectAnimator mClockAnimator = ObjectAnimator.ofFloat(analogClock,"translationY",0F,-300F);
        ObjectAnimator mDataFormAnimator = ObjectAnimator.ofFloat(timeSetOverLayout,"alpha",0F,1F);
        ObjectAnimator mAgeAnimator= ObjectAnimator.ofFloat(mAgeTextView,"alpha",0F,1F);
        animatorSet.playSequentially(mClockAnimator,mDataFormAnimator);
        animatorSet.playTogether(mDataFormAnimator,mAgeAnimator);
        animatorSet.setDuration(1000);
        animatorSet.start();
        Timer timer = new Timer();
        timer.schedule(new myTask(), 1000,1000);
    }
    //myTask内部类，继承TimerTask
    class  myTask extends TimerTask {

        @Override
        public void run() {
            //获取当前日历上的时间
            Calendar mCL = Calendar.getInstance();
            mCurrentYear = mCL.get(Calendar.YEAR);
            mCurrentMonth = mCL.get(Calendar.MONTH)+1;
            mCurrentDate = mCL.get(Calendar.DATE);

            //计算出生至今的年差，月差
            calculateDifYearAndMonth();
            Log.d("mDifTime", mDifYear + "");
            Log.d("mDifTime",mDifMonth+"");
            Log.d("mBornYear",mBornYear+"");
            Log.d("mBornMonth",mBornMonth+"");
            Log.d("mBornDate",mBornDate+"");
            Log.d("mCurrentMonth",mCurrentMonth+"");

            //获取当前系统时间
            mCurDate = new Date(System.currentTimeMillis());
            mDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String mTime = mBornYear+"-"+mBornMonth+"-"+mBornDate+" "+"00:00:00";
            try {
                mSetDate = mDateFormat.parse(mTime);
            } catch (ParseException e){
                e.printStackTrace();
            }

            //计算出生至今的天差，周差，小时差，秒差
            mDifSeconds = (mCurDate.getTime()-mSetDate.getTime())/1000;
            mDifMin = (mCurDate.getTime() - mSetDate.getTime())/1000/60;
            mDifHour = mDifMin/60;
            mDifdate = mDifHour/24;
            mDifWeek = mDifdate/7;
            Log.d("mDifMin",mDifMin+"");
            Log.d("mDifHour",mDifHour+"");
            Log.d("mDifDate",mDifdate+"");
            Log.d("mDifWeek",mDifWeek+"");

            //通过handler机制将数据传给UI线程
            Message msg = new Message();
            msg.what = 1;
            handler.sendMessage(msg);

        }
    }

    //计算出生至今的年差，月差
    private void calculateDifYearAndMonth(){

        if(mCurrentMonth > mBornMonth){
            mDifYear = mCurrentYear - mBornYear;
            mDifMonth = mDifYear*12 + mCurrentMonth - mBornMonth;
        }else if (mCurrentMonth < mBornMonth){
            mDifYear = mCurrentYear - mBornYear - 1;
            mDifMonth = (mDifYear-1)*12+mCurrentMonth;
        }else{
            if(mCurrentDate >= mBornDate){
                mDifYear = mCurrentYear - mBornYear;
                mDifMonth = mDifYear*12;
            }else{
                mDifYear = mCurrentYear - mBornYear -1;
                mDifMonth = mDifYear*12 -1;
            }
        }
    }

    //通过handler机制，实时刷新UI界面的年月日分等数据
    Handler handler = new Handler() {
        public void handleMessage(Message msg){
            switch (msg.what){
                case 1:
                    mAgeTextView.setVisibility(View.VISIBLE);
                    int j = 0;
                    for(int i = mBornYear; i <= mCurrentYear; i++){
                        if( i % 4 ==0){
                            j++;
                        }
                    }
                    double mAge = (mDifSeconds/60/60/24-j*366)/365+j ;
                    mAgeTextView.setText(decimalFormat.format(mAge)+"");
                    mYear.setText(mDifYear+"");
                    mMonth.setText(mDifMonth+"");
                    mWeek.setText(mDifWeek+"");
                    mDay.setText(mDifdate+"");
                    mHour.setText(mDifHour+"");
                    mMin.setText(mDifMin+"");

                    Log.d("mAge",decimalFormat.format(mAge)+"");
                    break;
                default:
            }
        }
    };

}
