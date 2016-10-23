package com.lv.http.worldclock.adapter;

import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.lv.http.worldclock.R;
import com.lv.http.worldclock.data.CityCountry;

import java.util.List;


public class CityListAdapter extends BaseAdapter {
    private List<CityCountry> list;

    public CityListAdapter(List<CityCountry> list) {
        this.list = list;
    }

    public void setList(List<CityCountry> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public CityCountry getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view;
        if (convertView == null) {
            view = View.inflate(parent.getContext(), R.layout.item_city_zone, null);
        } else {
            view = convertView;
        }
        CityCountry CityCountry = getItem(position);
        TextView tv_index = (TextView) view.findViewById(R.id.tv_index);
        TextView tv_name = (TextView) view.findViewById(R.id.tv_city);

        String letter = null;
        String currentLetter = CityCountry.getCityCountry().charAt(0) + "";
        if (position == 0) {
            letter = currentLetter;
        } else {
            String preLetter = getItem(position - 1).getCityCountry().charAt(0) + "";
            if (!TextUtils.equals(preLetter, currentLetter)) {
                letter = currentLetter;
            }
        }

        tv_index.setVisibility(letter == null ? View.GONE : View.VISIBLE);
        tv_index.setText(letter);
        tv_name.setText(CityCountry.getCityCountry());

        return view;
    }
}
