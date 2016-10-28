package com.lv.http.worldclock.holder;

import android.view.View;
import android.widget.TextView;

import com.lv.http.worldclock.R;
import com.lv.http.worldclock.gloabl.WorldClockApp;
import com.lv.http.worldclock.http.model.TimeList;

import java.text.SimpleDateFormat;
import java.util.Date;


public class TimeZoneHolder extends BasicHolder<TimeList.ZonesBean> {

    private TextView mTv_city;
    private TextView mTv_time;
    private TextView mTv_am;
    private TextView mTv_gtm;

    @Override
    protected View createHolderView() {
        View view = View.inflate(WorldClockApp.context, R.layout.item_time_zone, null);
        mTv_city = (TextView) view.findViewById(R.id.tv_city);
        mTv_time = (TextView) view.findViewById(R.id.tv_time);
        mTv_am = (TextView) view.findViewById(R.id.tv_am);
        mTv_gtm = (TextView) view.findViewById(R.id.tv_GTM);
        return view;
    }

    @Override
    public void bindView(TimeList.ZonesBean appInfo) {
        String[] split = appInfo.getZoneName().split("/");
        Date date = new Date(appInfo.getTimestamp());
        SimpleDateFormat format = new SimpleDateFormat("HH:mm");
        String time = format.format(date);
        int HRS = appInfo.getGmtOffset() / 3600;
        mTv_city.setText(split[split.length - 1]);
        setTime(time, HRS);
    }

    private void setTime(String time, int HRS) {
        String t = time.split(":")[0];
        String m = time.split(":")[1];
        int hour = Integer.parseInt(t)+HRS;
        if (hour > 24) {
            hour -= 24;
            mTv_time.setText(hour + ":" + m);
            mTv_gtm.setText("Next day," + HRS + "HRS");
        } else if (hour < 0) {
            hour = 24 - hour;
            mTv_time.setText(hour + ":" + m);
            mTv_gtm.setText("Yesterday," + HRS + "HRS");
        } else {
            mTv_time.setText(hour + ":" + m);
            mTv_gtm.setText("Today," + HRS + "HRS");
        }
        if (hour >= 12) {
            mTv_am.setText("PM");
        }
    }
}
