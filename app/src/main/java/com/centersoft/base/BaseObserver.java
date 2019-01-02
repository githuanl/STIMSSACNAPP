package com.centersoft.base;

import android.content.DialogInterface;

import com.blankj.utilcode.util.NetworkUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.centersoft.utils.AppManager;
import com.centersoft.utils.LoadingDialog;
import com.centersoft.utils.MyLog;

import java.net.ConnectException;
import java.net.SocketTimeoutException;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import retrofit2.HttpException;

/**
 * Created by liudong on 2017/10/27.
 */

public abstract class BaseObserver<R> implements Observer<R> {

    private LoadingDialog mDialog;
    private Disposable mDisposable;

    public BaseObserver(LoadingDialog dialog) {

        mDialog = dialog;

        if (mDialog != null) {
            mDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
                @Override
                public void onCancel(DialogInterface dialog) {
                    mDisposable.dispose();
                }
            });
        }
    }

    @Override
    public void onSubscribe(Disposable d) {
        mDisposable = d;
        if (!NetworkUtils.isConnected()) {
            read("网络未连接");
            ToastUtils.showShort("网络未连接");
            d.dispose();
            return;
        }

        if (mDialog != null && !mDialog.isShowing()) {
            mDialog.show();
        }
    }

    @Override
    public void onNext(R value) {
        if (value instanceof BaseEntity) {
            BaseEntity b = (BaseEntity) value;
            if (b.getId() < 0 || b.getFlag() < 0 || b.getAutoid() < 0) {
                BaseActivity activity = (BaseActivity) AppManager.getInstance().getTopActivity();
                activity.read(b.getMsg());
                ToastUtils.showLong(b.getMsg());
                return;
            }
        }
        onSuccess(value);
    }

    public void read(String str) {
        BaseActivity activity = (BaseActivity) AppManager.getInstance().getTopActivity();
        activity.read(str);
    }


    @Override
    public void onError(Throwable throwable) {

        MyLog.i("onError", "error:" + throwable.getMessage() + "=====>" + throwable.toString());

        if (mDialog != null && mDialog.isShowing()) {
            mDialog.dismiss();
        }

        if (!mDisposable.isDisposed()) {
            mDisposable.dispose();
        }

//        Null is not a valid element
        if (throwable instanceof HttpException) {
            HttpException error = (HttpException) throwable;
            try {
                MyLog.i("BaseObserver", error.response().errorBody().string());
            } catch (Exception e) {

            }
        } else if (throwable instanceof ConnectException) {
            read("服务器连接失败");
            ToastUtils.showLong("服务器连接失败");
            return;
        } else if (throwable instanceof SocketTimeoutException) {
            read("服务器连接超时");
            ToastUtils.showLong("服务器连接超时!");
            return;
        } else if (throwable instanceof NullPointerException && throwable.getMessage().equals("Null is not a valid element")) {
            read("登录失效，请重新扫描");
            ToastUtils.showLong("登录失效，请重新扫描!");
            return;
        } else {
            throwable.printStackTrace();
        }

        read("数据异常");
        ToastUtils.showLong("数据异常!");

    }

    @Override
    public void onComplete() {
        if (mDialog != null && mDialog.isShowing()) {
            mDialog.dismiss();
        }
        if (!mDisposable.isDisposed()) {
            mDisposable.dispose();
        }
    }

    protected abstract void onSuccess(R t);

}