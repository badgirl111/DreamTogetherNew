package com.example.huangyiting.dreamtogether.ui;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import com.example.huangyiting.dreamtogether.R;


public class BornTimeSetActivity extends Activity implements View.OnClickListener {
    private DatePicker mDatepicker;
    private Button mOkButton;
    private Button mCancelButton;
    private int mBornYear;
    private int mBornMonth;
    private int mBornDate;
    private final int RESULT_OK = 1;
    private final int RESULT_CANCEL = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_born_time_set);

        //初始化时间选择控件，及时间设置确认取消按钮
        mDatepicker = (DatePicker) findViewById(R.id.data_picker);
        mOkButton = (Button) findViewById(R.id.time_set_ok);
        mCancelButton = (Button) findViewById(R.id.time_set_cancel);
        mOkButton.setOnClickListener(this);
        mCancelButton.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            // 时间设置被确定
            case R.id.time_set_ok:

                //获取设置的出生年月日
                mBornYear = mDatepicker.getYear();
                mBornMonth = mDatepicker.getMonth() + 1;
                mBornDate = mDatepicker.getDayOfMonth();
                Intent intent = new Intent();
                intent.putExtra("mBornYear", mBornYear);
                intent.putExtra("mBornMonth", mBornMonth);
                intent.putExtra("mBornDate", mBornDate);
                setResult(RESULT_OK, intent);
                finish();
                break;

                //时间设置被取消
            case R.id.time_set_cancel:
                setResult(RESULT_CANCEL, null);
                finish();
                break;
        }
    }
}