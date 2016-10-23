package com.lv.http.worldclock.adapter;

import com.lv.http.worldclock.holder.BasicHolder;
import com.lv.http.worldclock.holder.TimeZoneHolder;
import com.lv.http.worldclock.http.model.TimeList;

import java.util.List;


public class TimeZoneAdapter extends BasicAdapter<TimeList.ZonesBean> {
    public TimeZoneAdapter(List<TimeList.ZonesBean> showItems) {
        super(showItems);
    }

    @Override
    protected BasicHolder createViewholder(int position) {
        return new TimeZoneHolder();
    }
}
