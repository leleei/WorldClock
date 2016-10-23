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
        if (new Integer(time.split(":")[0])>=12) {
            mTv_am.setText("PM");
        }
        int HRS = appInfo.getGmtOffset() / 3600;
        mTv_gtm.setText("Today," + HRS + "HRS");
        mTv_city.setText(split[split.length-1]);
        mTv_time.setText(time);
    }
}
