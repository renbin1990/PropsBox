package com.renbin.propsbox;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.format.DateFormat;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MyLoveMainActivity extends AppCompatActivity {

    private TextView mTv_totle_time;
    private TextView mTv_now_time;
    private Long xiangshi = 1530288000000L;
    private Long qianshou = 1603987200000L;
    private TextView mTv_xs_day;
    //在主线程里面处理消息并更新UI界面
    private Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 1:
                    long sysTime = System.currentTimeMillis();//获取系统时间
                    CharSequence sysTimeStr = DateFormat.format("yyyy-MM-dd HH:mm:ss", sysTime);//时间显示格式
                    if (mTv_now_time!= null){
                        mTv_now_time.setText(sysTimeStr); //更新时间
                    }
                    break;
                default:
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_love);

        initView();
    }

    private void initView() {
        mTv_xs_day = findViewById(R.id.tv_xs_day);
        mTv_totle_time = findViewById(R.id.tv_totle_time);
        mTv_now_time = findViewById(R.id.tv_now_time);

        //获取当前时间
        long currentMillis = DateUtils.getCurrentMillis();
        mTv_xs_day.setText(DateUtils.getDayOffset(currentMillis,xiangshi)+"天");
        mTv_totle_time.setText(DateUtils.getDayOffset(currentMillis,qianshou)+"天");
        new TimeThread().start(); //启动新的线程
    }

    class TimeThread extends Thread {
        @Override
        public void run() {
            do {
                try {
                    Thread.sleep(1000);
                    Message msg = new Message();
                    msg.what = 1;  //消息(一个整型值)
                    mHandler.sendMessage(msg);// 每隔1秒发送一个msg给mHandler
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } while (true);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mHandler!= null){
            mHandler.removeCallbacksAndMessages(null);
        }
    }
}