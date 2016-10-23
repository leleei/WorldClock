package com.lv.http.worldclock.view.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.lv.http.worldclock.R;
import com.lv.http.worldclock.adapter.CityListAdapter;
import com.lv.http.worldclock.data.CityCountry;
import com.lv.http.worldclock.constant.ParamConstant;
import com.lv.http.worldclock.constant.SPConstant;
import com.lv.http.worldclock.engine.TimeListEngine;
import com.lv.http.worldclock.http.RetrofitUtil;
import com.lv.http.worldclock.http.model.TimeList;
import com.lv.http.worldclock.view.widget.QuickIndexBar;
import com.lv.http.worldclock.utils.SPUtil;
import com.lv.http.worldclock.utils.ToastUtil;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class AddAttentionActivity extends AppCompatActivity implements QuickIndexBar.OnLetterUpdateListener, View.OnClickListener, AdapterView.OnItemClickListener {

    private TextView      mBt_cancel;
    private QuickIndexBar mQib;
    private EditText      mEt_search;
    private ListView      mLv_city_list;
    private List<CityCountry> mCityList = new ArrayList<>();
    private List<CityCountry> mTempList = new ArrayList<>();
    private CityListAdapter mCityListAdapter;

    @Override

    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_attention);
        initUI();
        initData();
    }

    private void initData() {
        Call<TimeList> timeListCall = RetrofitUtil.createHttpApiInstance().getTimeListZone(ParamConstant.LIST_TIME_ZONE_KEY, ParamConstant.LIST_TIME_ZONE_FORMAT);
        timeListCall.enqueue(new Callback<TimeList>() {
            @Override
            public void onResponse(Call<TimeList> call, Response<TimeList> response) {
                TimeList timeList = response.body();
                if (timeList != null) {
                    List<TimeList.ZonesBean> zones = timeList.getZones();
                    mCityList = TimeListEngine.initCityList(timeList);
                    Collections.sort(mCityList);
                    mCityListAdapter = new CityListAdapter(mCityList);
                    mLv_city_list.setAdapter(mCityListAdapter);
                } else {
                    ToastUtil.showShort(AddAttentionActivity.this, "数据加载失败");
                }
            }

            @Override
            public void onFailure(Call<TimeList> call, Throwable t) {
                ToastUtil.showShort(AddAttentionActivity.this, "网络连接失败");
            }
        });
    }

    private void initUI() {
        mBt_cancel = (TextView) findViewById(R.id.bt_cancel);
        mQib = (QuickIndexBar) findViewById(R.id.qib);
        mEt_search = (EditText) findViewById(R.id.et_search);
        mLv_city_list = (ListView) findViewById(R.id.lv_city_list);
        mQib.setOnLetterUpdateListener(this);
        mBt_cancel.setOnClickListener(this);
        mLv_city_list.setOnItemClickListener(this);
        mEt_search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                mTempList.clear();
                if (s.toString().length() == 0) {
                    mCityListAdapter.setList(mCityList);
                } else {
                    for (CityCountry item : mCityList) {
                        String substring = item.getCityCountry().substring(0, s.toString().length());
                        if (substring.equalsIgnoreCase(s.toString())) {
                            mTempList.add(item);
                        }
                    }
                    mCityListAdapter.setList(mTempList);
                }
            }
        });
    }

    @Override
    public void onLetterUpdate(String letter) {
        for (int i = 0; i < mCityList.size(); i++) {
            String firstLetter = mCityList.get(i).getCityCountry().charAt(0) + "";
            if (TextUtils.equals(firstLetter, letter)) {
                mLv_city_list.setSelection(i);
                break;
            }
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_cancel:
                mEt_search.setText("");
                break;
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        CityCountry item = mCityListAdapter.getItem(position);
        Set<String> attentionSet = SPUtil.getStringSet(AddAttentionActivity.this, SPConstant.ATTENTION_CITY);
        attentionSet.add(item.getCityName());
        SPUtil.put(AddAttentionActivity.this, SPConstant.ATTENTION_CITY, attentionSet);
        ToastUtil.showShort(AddAttentionActivity.this, "关注成功");
    }
}
