package com.centersoft.view;

import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.centersoft.adapter.SuperBaseAdapter;
import com.centersoft.base.BaseActivity;
import com.centersoft.base.BaseEntity;
import com.centersoft.base.BaseHolder;
import com.centersoft.base.BaseObserver;
import com.centersoft.entity.MessageNum;
import com.centersoft.enums.ModelEnum;
import com.centersoft.stimsscanapp.R;
import com.centersoft.transformer.CommonTransformer;
import com.centersoft.utils.AppManager;
import com.centersoft.utils.NetCashUtil;
import com.centersoft.utils.NetUtil;
import com.zltd.industry.ScannerManager;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends BaseActivity<ModelEnum> implements ScannerManager.IScannerStatusListener {


    private long mExitTime;
    private HomeAdapter adapter;

    @BindView(R.id.gv_home_list)
    GridView gv_home_list;

    @Override
    protected void setToolBarTitle() {

    }

    @Override
    public int initResource() {
        return R.layout.acty_main;
    }

    protected boolean hasBackButton() {
        return false;
    }

    @Override
    protected void reqData() {
//
//        if (barcodeStr.length() > 3) {
//            String prefix = barcodeStr.substring(0, 3);
//            switch (prefix.toUpperCase()) {
//                case "QCM":  // 外检
//                    openActivity(DetectionActy.class);
//                    break;
//            }
//        }

    }

//    purStoreOrderMng//外购收货 ------》暂不处理
//    purchasingStorage //外购入库
//            purMaterial// 外检
//                    manuImDetail// 成品入库
//    manuRequireDeliver//领料出库
//            //销售出库

    Map<String, String> msgMap = new HashMap<>();

    @Override
    protected void onResume() {
        super.onResume();
        msgMap.clear();
        httpService.getTipMsg()
                .compose(new CommonTransformer())
                .subscribe(new BaseObserver<BaseEntity<List<MessageNum>>>(null) {
                    @Override
                    protected void onSuccess(BaseEntity<List<MessageNum>> baseEntity) {
                        for (MessageNum mgn : baseEntity.getData()) {
                            msgMap.put(mgn.getMsgId(), mgn.getNum());
                        }
                        if (adapter != null) {
                            adapter.notifyDataSetChanged();
                        }
                    }
                });
    }

    @Override
    protected void initData() {

        for (ModelEnum modelEnum : ModelEnum.values()) {
            listDatas.add(modelEnum);
        }

        adapter = new HomeAdapter(listDatas);
        gv_home_list.setAdapter(adapter);
        gv_home_list.setSelector(R.drawable.button);
        gv_home_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                ModelEnum modelEnum = listDatas.get(i);
                openActivity(modelEnum.getmClass());
            }
        });
        adapter.notifyDataSetChanged();

        new Thread(new Runnable() {
            @Override
            public void run() {
                mScannerManager.addScannerStatusListener(MainActivity.this);
                mScannerManager.connectDecoderSRV();
                mScannerManager.startContinuousScan();
            }
        }).start();

    }

    private class HomeAdapter extends SuperBaseAdapter<ModelEnum> {

        public HomeAdapter(List<ModelEnum> datas) {
            super(datas);
        }

        @Override
        protected BaseHolder<ModelEnum> getHolder() {
            return new HomeHolder();
        }

    }

    public class HomeHolder extends BaseHolder<ModelEnum> {

        @BindView(R.id.iv_home_item)
        ImageView iv;

        @BindView(R.id.tv_home_item)
        TextView tv;

        @BindView(R.id.tv_num)
        TextView tv_num;

        @Override
        protected View initView() {
            View v = View.inflate(context, R.layout.home_item, null);
            ButterKnife.bind(this, v);
            return v;
        }

        @Override
        protected void refreshUI(ModelEnum data) {
            iv.setImageResource(data.getDrawble());
            tv.setText(data.getName());

            if (!TextUtils.isEmpty(data.getMsgType()) && !TextUtils.isEmpty(msgMap.get(data.getMsgType()))) {
                tv_num.setText(msgMap.get(data.getMsgType()));
                tv_num.setVisibility(View.VISIBLE);
            } else {
                tv_num.setVisibility(View.GONE);
            }
        }
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if ((System.currentTimeMillis() - mExitTime) > 2000) {
                showTips(R.drawable.jd_toast_jg, "再按一次退出应用!");
                mExitTime = System.currentTimeMillis();
            } else {
                AppManager.getInstance().AppExit(context);
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        NetCashUtil.c_Map.clear();
        NetCashUtil.c_List_Map.clear();
        NetUtil.init();
//        mScannerManager.disconnectDecoderSRV();
    }

    @Override
    public void onScannerStatusChanage(int i) {

    }

    @Override
    public void onScannerResultChanage(byte[] bytes) {
        BaseActivity activity = (BaseActivity) AppManager.getInstance().getTopActivity();
        activity.onScanner(bytes);
    }


    @OnClick({R.id.iv_logout})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_logout:
                AppManager.getInstance().killActivity(this);
                openActivity(LoginActivity.class);
                break;
        }

    }

}
