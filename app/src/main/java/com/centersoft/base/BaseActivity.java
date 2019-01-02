package com.centersoft.base;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.ArrayMap;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

import com.centersoft.stimsscanapp.R;
import com.centersoft.utils.*;
import com.zltd.industry.ScannerManager;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.reactivex.annotations.Nullable;

import static com.centersoft.utils.SoundUtils.SOUND_TYPE_SUCCESS;

public abstract class BaseActivity<T> extends AppCompatActivity {


    protected String TAG = this.getClass().getSimpleName() + " ============>";

    protected ActionBar actionBar;

    protected Context context;
    private Unbinder unbinder;


    protected ArrayMap<String, Object> reqMap = new ArrayMap<>();

    protected List<T> listDatas = new ArrayList<>();
    protected LoadingDialog dialog;

    @Nullable
    @BindView(R.id.toolbar)
    protected Toolbar toolbar;

    View mActionBarView = null;
    protected TextView toobarTitle;

    protected static HttpService httpService = NetUtil.getHttpService();

    protected Handler uiHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    reqData();
                    break;
            }
        }
    };

    protected ScannerManager mScannerManager;
    protected SoundUtils mSoundUtils;
    protected String barcodeStr = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(initResource());

        unbinder = ButterKnife.bind(this);

        //设置toolbar
        if (toolbar != null) {
            setSupportActionBar(toolbar);
            actionBar = getSupportActionBar();

            ActionBar.LayoutParams lp = new ActionBar.LayoutParams(ActionBar.LayoutParams.WRAP_CONTENT, ActionBar.LayoutParams.MATCH_PARENT, Gravity.CENTER);
            mActionBarView = LayoutInflater.from(this).inflate(R.layout.actionbar_title, null);

            actionBar.setCustomView(mActionBarView, lp);
            actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
            actionBar.setDisplayShowCustomEnabled(true);
            actionBar.setDisplayShowTitleEnabled(false);

            if (hasBackButton()) {
                actionBar.setDisplayHomeAsUpEnabled(true);
                actionBar.setHomeButtonEnabled(true);
//            actionBar.setHomeAsUpIndicator(R.drawable.ic_keyboard_arrow_left_white_36dp);
            }
            actionBar.setElevation(0);    //去阴影

            toobarTitle = $(mActionBarView, R.id.title);
            setToolBarTitle();
        }

        //初始化
        init();

        initData();
    }


    public void onScanner(byte[] arg0) {
        barcodeStr = new String(arg0);
        MyLog.i(TAG, barcodeStr);
        if (barcodeStr.contains("timeout")) {
            barcodeStr = "";
            return;
        }
        barcodeStr = barcodeStr.replace("\r", "");
        mSoundUtils.playSound(SOUND_TYPE_SUCCESS);
        uiHandler.sendEmptyMessage(1);
    }


    AlertDialog.Builder builder = null;

    public AlertDialog adialog = null;

    //显示消息
    public void showMessage(String title) {
        read(title);
        if (adialog != null) {
            adialog.dismiss();
        }
        builder = new AlertDialog.Builder(context);
        builder.setTitle(title);
        builder.setCancelable(true);
        // 更新
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        adialog = builder.show();
    }


    /**
     * 设置toolBar
     */
    protected void reqData() {
    }

    /**
     * 设置toolBar
     */
    protected abstract void setToolBarTitle();

    //初始化
    protected void init() {
        context = this;
        dialog = new LoadingDialog(context, R.style.dialog, "加载数据中...");
        dialog.setCanceledOnTouchOutside(false);

        mScannerManager = ScannerManager.getInstance();
        mSoundUtils = SoundUtils.getInstance();
        mSoundUtils.init(this);


    }

    /**
     * 语音朗诵
     */
    protected void read(String sayStr) {
//        int code = ScanApplication.mTts.startSpeaking(sayStr, null);
//        if (code != ErrorCode.SUCCESS) {
//            MyLog.i("---", "未安装离线包");
//        } else {
//            MyLog.i("---", "已安装离线包");
//        }
    }

    /**
     * 是否有返回按钮
     *
     * @return
     */
    protected boolean hasBackButton() {
        return true;
    }

    /**
     * 初始化布局资源文件
     */
    protected abstract int initResource();

    /**
     * 初始化数据
     */
    protected void initData() {

    }


    protected <E extends View> E $(View v, int resId) {
        return (E) v.findViewById(resId);
    }

    protected <E extends View> E $(int resId) {
        return (E) findViewById(resId);
    }

    private static TipsToast tipsToast;

    protected void showTips(int iconResId, String tips) {
        read(tips);
        if (tipsToast != null) {
            if (Build.VERSION.SDK_INT < Build.VERSION_CODES.ICE_CREAM_SANDWICH) {
                tipsToast.cancel();
            }
        } else {
            tipsToast = TipsToast.makeText(getApplication().getBaseContext(),
                    tips, TipsToast.LENGTH_SHORT);
        }
        tipsToast.show();
        tipsToast.setIcon(iconResId);
        tipsToast.setText(tips);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                goBack();
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            goBack();
        } else if (keyCode == KeyEvent.KEYCODE_BUTTON_A) {
            mScannerManager.dispatchScanKeyEvent(event);
        }
        return true;
    }

    /**
     * 返回前做处理
     */
    protected boolean beforeBack() {
        return true;
    }


    /**
     * @throws
     * @Title 返回
     * @Description TODO
     * @author LH
     */
    protected void goBack() {
        if (!beforeBack()) {
            return;
        }
        AppManager.getInstance().killActivity(this);
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
    }

    /**
     * 通过类名启动Activity
     *
     * @param pClass
     */
    protected void openActivity(Class<?> pClass) {
        openActivity(pClass, null);
    }

    /**
     * 通过类名启动Activity，并且含有Bundle数据
     *
     * @param pClass
     * @param pBundle
     */
    protected void openActivity(Class<?> pClass, Bundle pBundle) {
        Intent intent = new Intent(this, pClass);
        if (pBundle != null) {
            intent.putExtras(pBundle);
        }
        startActivity(intent);
        overridePendingTransition(R.anim.pre_fade_in, R.anim.pre_fade_out);
    }

    protected void openActivityForResult(Class<?> pClass, Bundle pBundle) {
        Intent intent = new Intent(this, pClass);
        if (pBundle != null) {
            intent.putExtras(pBundle);
        }
        startActivityForResult(intent, 0);
        overridePendingTransition(R.anim.pre_fade_in, R.anim.pre_fade_out);
    }

    //控制点击其他位置时关闭键盘
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (ev.getAction() == MotionEvent.ACTION_DOWN) {
            View v = getCurrentFocus();
            if (isShouldHideInput(v, ev)) {
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                if (imm != null) {
                    imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                }
            }
            return super.dispatchTouchEvent(ev);
        }
        // 必不可少，否则所有的组件都不会有TouchEvent了
        if (getWindow().superDispatchTouchEvent(ev)) {
            return true;
        }
        return onTouchEvent(ev);
    }

    public boolean isShouldHideInput(View v, MotionEvent event) {
        if (v != null && (v instanceof EditText)) {
            int[] leftTop = {0, 0};
            //获取输入框当前的location位置
            v.getLocationInWindow(leftTop);
            int left = leftTop[0];
            int top = leftTop[1];
            int bottom = top + v.getHeight();
            int right = left + v.getWidth();
            if (event.getX() > left && event.getX() < right
                    && event.getY() > top && event.getY() < bottom) {
                // 点击的是输入框区域，保留点击EditText的事件
                return false;
            } else {
                return true;
            }
        }
        return false;
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (unbinder != null) {
            unbinder.unbind();
        }
    }

}
