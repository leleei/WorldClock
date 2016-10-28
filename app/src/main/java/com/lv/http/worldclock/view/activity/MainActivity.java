package com.lv.http.worldclock.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.lv.http.worldclock.R;
import com.lv.http.worldclock.adapter.TimeZoneAdapter;
import com.lv.http.worldclock.constant.ParamConstant;
import com.lv.http.worldclock.constant.SPConstant;
import com.lv.http.worldclock.http.RetrofitUtil;
import com.lv.http.worldclock.http.model.TimeList;
import com.lv.http.worldclock.utils.SPUtil;
import com.lv.http.worldclock.utils.ToastUtil;
import com.lv.http.worldclock.view.widget.CommonTitle;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {


    private CommonTitle     mViewCt;
    private ListView        mLvClockZone;
    private TimeZoneAdapter mTimeZoneAdapter;
    private boolean isRunning = true;
    private Handler mHandler  = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 0:
                    upDateTimer();
                    if (isRunning) {
                        mHandler.sendEmptyMessageDelayed(0, 1000);
                    }
                    break;
            }
        }
    };
    private ProgressBar mPb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mViewCt = (CommonTitle) findViewById(R.id.view_ct);
        mLvClockZone = (ListView) findViewById(R.id.lv_clock_zone);
        mPb = (ProgressBar) findViewById(R.id.pb);
        initUI();
    }

    @Override
    protected void onStart() {
        super.onStart();
        isRunning = true;
        initTimeData();
    }

    private void initUI() {
        mViewCt.setTitle("World Clock");
        mViewCt.setOnButtonClickListener(new CommonTitle.OnButtonClickListener() {
            @Override
            public void onClickRightButton() {
                Intent intent = new Intent(MainActivity.this, AddAttentionActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onStop() {
        super.onStop();
        isRunning = false;
    }

    private void initTimeData() {
        mPb.setVisibility(View.VISIBLE);
        mLvClockZone.setVisibility(View.GONE);
        Call<TimeList> timeListZone = RetrofitUtil.createHttpApiInstance().getTimeListZone(ParamConstant.LIST_TIME_ZONE_KEY, ParamConstant.LIST_TIME_ZONE_FORMAT);
        timeListZone.enqueue(new Callback<TimeList>() {
            @Override
            public void onResponse(Call<TimeList> call, Response<TimeList> response) {
                TimeList timeList = response.body();
                if (timeList != null) {
                    List<TimeList.ZonesBean> zones = timeList.getZones();
                    Set<String> attentionSet = SPUtil.getStringSet(MainActivity.this, SPConstant.ATTENTION_CITY);
                    List<TimeList.ZonesBean> addItem = new ArrayList();
                    for (TimeList.ZonesBean zone : zones) {
                        String[] zoneSplit = zone.getZoneName().split("/");
                        for (String attention : attentionSet) {
                            if (TextUtils.equals(zoneSplit[zoneSplit.length - 1], attention)) {
                                addItem.add(zone);
                            }
                        }
                    }
                    mTimeZoneAdapter = new TimeZoneAdapter(addItem);
                    mLvClockZone.setAdapter(mTimeZoneAdapter);
                } else {
                    ToastUtil.showShort(MainActivity.this, "数据加载失败");
                }
                mPb.setVisibility(View.GONE);
                mLvClockZone.setVisibility(View.VISIBLE);
                mHandler.sendEmptyMessageDelayed(0, 1000);
            }

            @Override
            public void onFailure(Call<TimeList> call, Throwable t) {
                mPb.setVisibility(View.GONE);
                mLvClockZone.setVisibility(View.VISIBLE);
                ToastUtil.showShort(MainActivity.this, "网络连接失败");
            }
        });
    }

    private void upDateTimer() {
        List<TimeList.ZonesBean> showItems = mTimeZoneAdapter.getShowItems();
        for (TimeList.ZonesBean showItem : showItems) {
            showItem.setTimestamp(showItem.getTimestamp() + 1000);
        }
        mTimeZoneAdapter.notifyDataSetChanged();
    }
}
