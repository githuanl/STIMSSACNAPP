package com.centersoft.view;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.blankj.utilcode.util.SPUtils;
import com.centersoft.base.BaseActivity;
import com.centersoft.base.BaseEntity;
import com.centersoft.base.BaseObserver;
import com.centersoft.entity.AppUpdate;
import com.centersoft.entity.LoginBean;
import com.centersoft.stimsscanapp.R;
import com.centersoft.transformer.CommonTransformer;
import com.centersoft.utils.*;
import com.jakewharton.rxbinding2.view.RxView;
import com.jakewharton.rxbinding2.widget.RxTextView;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;

/**
 * 登录
 */
public class LoginActivity extends BaseActivity {


    @BindView(R.id.ed_name)
    EditText edLoginName;

    @BindView(R.id.ed_password)
    EditText edLoginPassword;

    @BindView(R.id.btn_login)
    TextView btn_login;

    @BindView(R.id.ed_ipAddress)
    EditText ed_ipAddress;

    @Override
    protected void setToolBarTitle() {

    }

    /**
     * 获取软件版本号
     *
     * @param context
     * @return
     */
    private int getVersionCode(Context context) {
        int versionCode = 0;
        try {
            versionCode = context.getPackageManager().getPackageInfo("com.centersoft.stimsscanapp", 0).versionCode;
        } catch (Exception e) {
        }
        return versionCode;
    }

    //下载更新
    private void updateApp(String fileUrl, final String destDir, final String fileName,
                           final FileDownLoadObserver<File> fileDownLoadObserver) {

        httpService.downloadAppFile(fileUrl)
                .subscribeOn(Schedulers.io())//subscribeOn和ObserOn必须在io线程，如果在主线程会出错
                .observeOn(Schedulers.io())
                .observeOn(Schedulers.computation())//需要
                .map(new Function<ResponseBody, File>() {
                    @Override
                    public File apply(@NonNull ResponseBody responseBody) throws Exception {
                        return fileDownLoadObserver.saveFile(responseBody, destDir, fileName);
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(fileDownLoadObserver);

    }

    /**
     * 安装APK文件
     */
    private void installApk(String filePath, String name) {
        File apkfile = new File(filePath, name);
        if (!apkfile.exists()) {
            System.out.println("找不到文件");
            return;
        }
        // 通过Intent安装APK文件
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.addCategory(Intent.CATEGORY_DEFAULT);
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        i.setDataAndType(Uri.parse("file://" + apkfile.toString()), "application/vnd.android.package-archive");
        startActivity(i);
        AppManager.getInstance().AppExit(context);
    }


    /* 更新进度条 */
    private ProgressBar mProgress;
    private TextView update_text;
    private Dialog mDownloadDialog;

    /**
     * 显示软件下载对话框
     */
    private void showDownloadDialog(final AppUpdate appUpdate) {
        final String filePath = Environment.getExternalStorageDirectory().getAbsolutePath() + "/download";
        // 构造软件下载对话框
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        // 给下载对话框增加进度条
        final LayoutInflater inflater = LayoutInflater.from(context);
        View v = inflater.inflate(R.layout.softupdate_progress, null);
        mProgress = (ProgressBar) v.findViewById(R.id.update_progress);
        update_text = (TextView) v.findViewById(R.id.update_text);
        builder.setView(v);
        builder.setCancelable(false);

        final FileDownLoadObserver observer = new FileDownLoadObserver() {

            @Override
            public void onDownLoadSuccess(Object o) {
                cancel();
                mDownloadDialog.dismiss();
                installApk(filePath, appUpdate.getName());
            }

            @Override
            public void onDownLoadFail(Throwable throwable) {
                mDownloadDialog.dismiss();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        showTips(R.drawable.jd_toast_jg, "更新失败");
                    }
                });
                MyLog.i("TAG---", "下载失败");
            }

            @Override
            public void onProgress(final int progress, long total) {
                mProgress.setProgress(progress);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        update_text.setText("正在下载：" + progress + "%");
                    }
                });
            }
        };

        // 取消更新
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                observer.onCancel();
            }
        });
        mDownloadDialog = builder.create();
        mDownloadDialog.show();

        updateApp(appUpdate.getDownLoadUrl(), filePath, appUpdate.getName(), observer);

    }


    @Override
    protected void initData() {

        httpService.updateDetection()
                .compose(new CommonTransformer())
                .subscribe(new BaseObserver<AppUpdate>(null) {
                    @Override
                    protected void onSuccess(final AppUpdate appUpdate) {
                        //需要更新
                        if (appUpdate.getVersionCode() > getVersionCode(context)) {
                            AlertDialog.Builder builder = new AlertDialog.Builder(context);
                            builder.setTitle("版本更新!");
                            builder.setCancelable(false);
                            builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                }
                            });
                            builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface d, int which) {
                                    showDownloadDialog(appUpdate);
                                }

                            });
                            builder.show();

                        }
                    }
                });


        if (!TextUtils.isEmpty(SPUtils.getInstance().getString("loginname"))) {
            edLoginName.setText(SPUtils.getInstance().getString("loginname"));
        }

        if (!TextUtils.isEmpty(SPUtils.getInstance().getString("password"))) {
            edLoginPassword.setText(SPUtils.getInstance().getString("password"));
        }

        //设置
        if (!TextUtils.isEmpty(SPUtils.getInstance().getString("ipaddress"))) {
            ed_ipAddress.setText(SPUtils.getInstance().getString("ipaddress"));
        }

        btn_login.setEnabled(false);
        Observable.combineLatest(RxTextView.textChanges(edLoginName),
                RxTextView.textChanges(edLoginPassword),
                new BiFunction<CharSequence, CharSequence, Boolean>() {
                    @Override
                    public Boolean apply(CharSequence charSequence, CharSequence charSequence2) throws Exception {
                        return charSequence.toString().length() > 0 && charSequence2.toString().length() > 0;
                    }
                })
                .subscribe(new Consumer<Boolean>() {
                    @Override
                    public void accept(Boolean aBoolean) throws Exception {
                        btn_login.setEnabled(aBoolean);
                    }
                });

        RxView.clicks(btn_login)
                .throttleFirst(2, TimeUnit.SECONDS)
                .subscribe(new Consumer<Object>() {
                    @Override
                    public void accept(Object b) throws Exception {
                        LoginActivity.this.login();
                    }
                });
    }


    @Override
    public int initResource() {
        return R.layout.acty_login;
    }

    protected boolean hasBackButton() {
        return false;
    }


    private void login() {

        if (!TextUtils.isEmpty(ed_ipAddress.getText())) {
            Constant.BASE_URL = ed_ipAddress.getText().toString();
        } else {
            showTips(R.drawable.jd_toast_jg, "服务器地址不能为空");
            return;
        }

        NetUtil.init();
        httpService = NetUtil.getHttpService();

        Map<String, String> blog = new HashMap<>();
        blog.put("loginname", edLoginName.getText().toString());
        blog.put("password", MD5Util.getMD5(edLoginPassword.getText().toString() + edLoginName.getText().toString()));

        httpService.login(blog)
                .compose(new CommonTransformer())
                .subscribe(new BaseObserver<BaseEntity<LoginBean>>(dialog) {
                    @Override
                    protected void onSuccess(BaseEntity<LoginBean> baseEntity) {

                        SPUtils.getInstance().put("ipaddress", ed_ipAddress.getText().toString());
                        SPUtils.getInstance().put("loginname", edLoginName.getText().toString());
                        SPUtils.getInstance().put("password", edLoginPassword.getText().toString());


                        Constant.EMPLOYEEID = baseEntity.getGlobal().getEMPLOYEEID();

                        SPUtils.getInstance().put("depart", baseEntity.getGlobal().getDEPART());

                        openActivity(MainActivity.class);
                        AppManager.getInstance().killActivity(LoginActivity.this);
                    }
                });
    }
}
