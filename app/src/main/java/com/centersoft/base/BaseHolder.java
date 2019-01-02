package com.centersoft.base;

import android.view.View;

/**
 * Description    basehoder
 * FileName       BaseHolder
 * CopyRight      CenterSoft
 * Author         LH
 * Createdate     2016/11/21 上午10:11
 * ------------------------------
 * updateAuthor   <修改人员>
 * updateDate     <修改日期>
 * updateNeedNum  <需求单号>
 * updateContent  <修改内容>
 * ------------------------------
 */
public abstract class BaseHolder<T> {
    protected View mRootView;
    protected T mData;
    public int position;

    protected <T extends View> T $(View v, int resId) {
        return (T) v.findViewById(resId);
    }

    public BaseHolder() {
        mRootView = initView();
        // 打标记
        mRootView.setTag(this);
    }

    protected abstract View initView();

    public void setData(T data) {
        this.mData = data;

        // UI刷新
        refreshUI(mData);
    }
    protected abstract void refreshUI(T data);
    public View getRootView() {
        return mRootView;
    }
}
