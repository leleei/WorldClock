package com.lv.http.worldclock.view.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.lv.http.worldclock.R;

public class CommonTitle extends RelativeLayout implements View.OnClickListener {


    private OnButtonClickListener mListener;
    private TextView mTv_edit;
    private TextView mTv_title;
    private ImageView mIv_icon;


    public CommonTitle(Context context) {
        this(context, null);
    }

    public CommonTitle(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        View view = View.inflate(context, R.layout.title_bar, this);
        mTv_edit = (TextView) view.findViewById(R.id.tv_edit);
        mTv_title = (TextView) view.findViewById(R.id.tv_title);
        mIv_icon = (ImageView) view.findViewById(R.id.iv_icon);
        mIv_icon.setOnClickListener(this);
    }

    public void  setTitle(String title) {
        mTv_title.setText(title);
    }



    public interface OnButtonClickListener{
        public void onClickRightButton();
    }

    public void setOnButtonClickListener(OnButtonClickListener listener) {
        this.mListener = listener;
    }


    @Override
    public void onClick(View v) {
        if (mListener != null) {
            switch (v.getId()) {
                case R.id.iv_icon:
                    mListener.onClickRightButton();
                    break;
            }
        }
    }


}
