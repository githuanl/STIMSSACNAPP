package com.centersoft.utils;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.widget.TextView;

import com.ant.liao.GifView;
import com.blankj.utilcode.util.SizeUtils;
import com.centersoft.stimsscanapp.R;


/**
 * Description     加载中Dialog
 * FileName       LoadingDialog.java
 * CopyRight      CenterSoft
 * Author         LH
 * Createdate     2015年4月11日 下午2:59:13
 * ------------------------------
 * updateAuthor   <修改人员>
 * updateDate     <修改日期>
 * updateNeedNum  <需求单号>
 * updateContent  <修改内容>
 * ------------------------------
 */

public class LoadingDialog extends AlertDialog {

    private TextView tips_loading_msg;

    private String message = null;

    public LoadingDialog(Context context) {
        super(context);
        message = getContext().getResources().getString(R.string.msg_load_ing);
    }

    public LoadingDialog(Context context, String message) {
        super(context);
        this.message = message;
        this.setCancelable(false);    //设置为false，按返回键不能退出 true时关闭dialog
        this.setCanceledOnTouchOutside(false); //设置点击屏幕任何位置时不关闭dialog
    }

    public LoadingDialog(Context context, int theme, String message) {
        super(context, theme);
        this.message = message;
        this.setCancelable(true);
        this.setCanceledOnTouchOutside(false);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.view_tips_loading);

        tips_loading_msg = (TextView) findViewById(R.id.tips_loading_msg);
        GifView gifv = (GifView) findViewById(R.id.gioof1);
        gifv.setGifImage(R.mipmap.gif1);
        gifv.setShowDimension(SizeUtils.dp2px(38), SizeUtils.dp2px(38));
        gifv.setGifImageType(GifView.GifImageType.COVER);


        tips_loading_msg.setText(this.message);
    }

    public void setText(String message) {
        this.message = message;
        tips_loading_msg.setText(this.message);
    }

    public void setText(int resId) {
        setText(getContext().getResources().getString(resId));
    }

}
