package com.lv.http.worldclock.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.lv.http.worldclock.holder.BasicHolder;

import java.util.ArrayList;
import java.util.List;


public abstract class BasicAdapter<T> extends BaseAdapter {

    private List<T> mShowItems = new ArrayList<>();

    public BasicAdapter(List<T> showItems) {
        this.mShowItems = showItems;
    }

    public void setShowItems(List<T> showItems) {
        mShowItems = showItems;
    }

    public List<T> getShowItems() {
        return mShowItems;
    }

    @Override
    public int getCount() {
        return mShowItems.size();
    }

    @Override
    public Object getItem(int position) {
        return mShowItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        BasicHolder viewHolder;
        if (convertView == null) {
            viewHolder = createViewholder(position);
        } else {
            viewHolder = (BasicHolder) convertView.getTag();
        }
        if (viewHolder == null) {
            System.out.println("holderkong");
        }
        viewHolder.bindView(mShowItems.get(position));
        View view = viewHolder.getView();
        return view;
    }
    protected abstract BasicHolder createViewholder(int position);
}
