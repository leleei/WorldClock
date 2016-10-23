package com.lv.http.worldclock.holder;

import android.view.View;


public abstract class BasicHolder<T> {

    private View view;

    public BasicHolder() {
        view = createHolderView();
        view.setTag(this);
    }

    protected abstract View createHolderView();
    public View getView() {
        return view;
    }
    public abstract void bindView( T appInfo);
}
