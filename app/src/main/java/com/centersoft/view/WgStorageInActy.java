package com.centersoft.view;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.centersoft.base.BaseActivity;
import com.centersoft.base.BaseEntity;
import com.centersoft.base.BaseObserver;
import com.centersoft.entity.CashBaseData;
import com.centersoft.entity.StorageIn;
import com.centersoft.entity.StorageInCen;
import com.centersoft.enums.CashType;
import com.centersoft.enums.CheckState;
import com.centersoft.stimsscanapp.R;
import com.centersoft.transformer.CommonTransformer;
import com.centersoft.utils.NetCashUtil;

import java.util.Arrays;

import butterknife.BindView;
import butterknife.OnClick;

public class WgStorageInActy extends BaseActivity<CashBaseData> {

    @BindView(R.id.scmid)
    TextView scmid;

    @BindView(R.id.materialid)
    TextView materialid;

    @BindView(R.id.material)
    TextView material;

    @BindView(R.id.specmodel)
    TextView specmodel;

    @BindView(R.id.supply)
    TextView supply;


    @BindView(R.id.allnums)
    TextView allnums; //收货数

    @BindView(R.id.bt_conf)
    Button bt_conf;


    @BindView(R.id.batchcode)
    TextView batchcode;

    @BindView(R.id.suid)
    TextView suid;

    @BindView(R.id.tv_kf)
    TextView tv_kf;
    @BindView(R.id.tv_checked)
    TextView tv_checked;

    @BindView(R.id.r_t_kw)
    TextView r_t_kw;

    @Override
    protected void setToolBarTitle() {
        toobarTitle.setText("外购入库");
    }

    @Override
    protected int initResource() {
        return R.layout.acty_outsourcing_storage_in;
    }

    StorageIn storageIn;


    String defaultKw = "";  //默认库位
    StorageInCen storageInCen;

    @Override
    protected void reqData() {
        defaultKw = "";
        //如果是要匹配库位
        if (isShow) {
            if (storageInCen != null && storageInCen.isStBinFlag()) {
                //判断扫描的条码或者二维码 是不是 正确的
                _scimd.setText("");
                boolean isContain = false;
                for (StorageInCen.Kw sk : storageInCen.getListSto()) {
                    if (sk.getQrcodes().equals(barcodeStr)) {
                        _scimd.setText(sk.getStbinsn());
                        defaultKw = sk.getStbincode();
                        isContain = true;
                        break;
                    }
                }
                if (!isContain) {
                    showTips(R.drawable.jd_toast_jg, "库位不匹配");
                    return;
                }
            }
            return;
        }

        tv_checked.setText("");
        storageIn = null;
        httpService.getDetailByDcqrcode(barcodeStr)//POD17-1031001
                .compose(new CommonTransformer())
                .subscribe(new BaseObserver<StorageInCen>(dialog) {
                    @Override
                    protected void onSuccess(StorageInCen s) {

                        storageInCen = s;
                        storageIn = s.getContent();

                        scmid.setText(storageIn.getQrcodes());
                        materialid.setText(storageIn.getMaterialid());
                        material.setText(storageIn.getMaterial());
                        specmodel.setText(storageIn.getSpecmodel());
                        batchcode.setText(storageIn.getBanchnum());
                        suid.setText(storageIn.getSuid());

                        if (TextUtils.isEmpty(storageIn.getUnit())) {
                            allnums.setText(storageIn.getNums());
                        } else {
                            allnums.setText(storageIn.getNums() + " (" + storageIn.getUnit() + ")");
                        }

                        tv_checked.setText(storageIn.getChecked().getText());
                        r_t_kw.setText(storageIn.getStbin());
                        defaultKw = storageIn.getStbin();
                        NetCashUtil.getBaseCash(CashType.Supply, storageIn.getSuid(), new NetCashUtil.CallBack<String>() {
                            @Override
                            public void success(String result) {
                                supply.setText(result);
                            }
                        });

                        NetCashUtil.getBaseCash(CashType.Storage, storageIn.getStid(), new NetCashUtil.CallBack<String>() {
                            @Override
                            public void success(String result) {
                                tv_kf.setText(result);
                            }
                        });

                        if (storageIn.getChecked().toString().equals(CheckState.checkout.toString())) {
                            bt_conf.setVisibility(View.VISIBLE);
                        }
                    }
                });
    }

    TextView _scimd;
    boolean isShow = false;

    @OnClick({R.id.bt_conf})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_conf:

                if (storageIn == null) {
                    showTips(R.drawable.jd_toast_jg, "请先扫描数据");
                    return;
                }

                if (!storageIn.getChecked().toString().equals(CheckState.checkout.toString())) {
                    showTips(R.drawable.jd_toast_ok, "状态为" + storageIn.getChecked().getText() + "不能入库");
                    return;
                }

                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("入库确认!");
                if (storageInCen.isStBinFlag()) {
                    View view = LayoutInflater.from(context).inflate(R.layout.outsourcing_confir, null);
                    builder.setView(view);
                    builder.setCancelable(false);
                    _scimd = (TextView) view.findViewById(R.id._scimd);

                    //设备默认库位
                    _scimd.setText(defaultKw);
                }


                builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        isShow = false;
                    }
                });
                isShow = true;

                builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface d, int which) {

                        isShow = false;

                        if (storageInCen.isStBinFlag() && TextUtils.isEmpty(defaultKw)) { // 库位为空时
                            showTips(R.drawable.jd_toast_jg, "请扫描库位");
                            return;
                        }

                        storageIn.setStbin(defaultKw);

                        httpService.updatePutStorageStatus(JSON.toJSONString(Arrays.asList(storageIn)))
                                .compose(new CommonTransformer())
                                .subscribe(new BaseObserver<BaseEntity<String>>(dialog) {
                                    @Override
                                    protected void onSuccess(BaseEntity<String> de) {
                                        tv_checked.setText("已入库");
                                        showTips(R.drawable.jd_toast_ok, de.getMsg());
                                        storageIn = null;
                                        bt_conf.setVisibility(View.GONE);
                                    }
                                });
                    }
                });
                builder.show();

                break;
        }
    }
}
