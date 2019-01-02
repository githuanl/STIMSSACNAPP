package com.centersoft;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;

import com.blankj.utilcode.util.Utils;
import com.centersoft.utils.AppManager;
import com.centersoft.utils.CrashHandler;
import com.centersoft.utils.Tools;
import com.iflytek.cloud.SpeechConstant;
import com.iflytek.cloud.SpeechSynthesizer;
import com.iflytek.cloud.SpeechUtility;

import java.io.File;
import java.io.IOException;

import static android.content.Intent.FLAG_ACTIVITY_NEW_TASK;


/**
 * Created by liudong on 2017/10/25.
 */

public class ScanApplication extends Application implements Application.ActivityLifecycleCallbacks {

    private static Context mContext;
    private static int mainTid;
    private static Handler handler;

    public static SpeechSynthesizer mTts;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this;
        mainTid = android.os.Process.myTid();
        handler = new Handler();

        Utils.init(this);

        registerActivityLifecycleCallbacks(this);

        new Thread(new Runnable() {
            @Override
            public void run() {

                CrashHandler crashHandler = CrashHandler.getInstance();
                crashHandler.init(mContext);


                if (!Tools.isAvilible("com.iflytek.speechcloud")) {
                    if (Tools.copyApkFromAssets("iflytek.apk", Environment.getExternalStorageDirectory().getAbsolutePath() + "/download")) {
                        Intent intent = new Intent(Intent.ACTION_VIEW);
                        intent.setFlags(FLAG_ACTIVITY_NEW_TASK);
                        intent.setDataAndType(Uri.parse("file://" + Environment.getExternalStorageDirectory().getAbsolutePath() + "/download/iflytek.apk"),
                                "application/vnd.android.package-archive");
                        startActivity(intent);
                    }
                }

                File file = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/speechcloud");
                //如果目标目录不存在 重新解压
                if (!file.exists()) {
                    try {
                        Tools.unZip("speechcloud.zip", Environment.getExternalStorageDirectory().getAbsolutePath());
                    } catch (IOException e) {

                    }
                }


                // 初始化语音引擎
                SpeechUtility.createUtility(ScanApplication.this, SpeechConstant.APPID + "=54b8bca3");
                mTts = SpeechSynthesizer.createSynthesizer(mContext, null);
//                云端支持发音人：小燕（xiaoyan）、小宇（xiaoyu）、凯瑟琳（Catherine）、 亨利（henry）、
//                玛丽（vimary）、小研（vixy）、小琪（vixq）、 小峰（vixf）、小梅（vixm）、小莉（vixl）、
//                小蓉（vixr）、 小芸（vixyun）、小坤（vixk）、小强（vixqa）、小莹（vixying）、
//                小新（vixx）、 楠楠（vinn）、老孙（vils）。
                mTts.setParameter(SpeechConstant.VOICE_NAME, "xiaoyan");//设置发音人
                mTts.setParameter(SpeechConstant.SPEED, "50");//设置语速，范围0~100
                mTts.setParameter(SpeechConstant.PITCH, "50");//设置语调，范围0~100
                mTts.setParameter(SpeechConstant.VOLUME, "100");//设置音量，范围0~100
                //设置引擎类型为本地
                mTts.setParameter(SpeechConstant.ENGINE_TYPE, SpeechConstant.TYPE_LOCAL);
            }
        }).start();
    }

    /**
     * @return 全局的上下文
     */
    public static Context getmContext() {
        return mContext;
    }

    @Override
    public void onActivityCreated(Activity activity, Bundle bundle) {
        AppManager.getInstance().addActivity(activity);
    }

    @Override
    public void onActivityStarted(Activity activity) {

    }

    @Override
    public void onActivityResumed(Activity activity) {

    }

    @Override
    public void onActivityPaused(Activity activity) {

    }

    @Override
    public void onActivityStopped(Activity activity) {

    }

    @Override
    public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {

    }

    @Override
    public void onActivityDestroyed(Activity activity) {
        AppManager.getInstance().killActivity(activity);
    }




}

