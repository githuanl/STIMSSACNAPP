package com.centersoft.view;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.centersoft.base.BaseActivity;
import com.centersoft.base.BaseObserver;
import com.centersoft.entity.CashBaseData;
import com.centersoft.entity.ManuIm;
import com.centersoft.enums.CashType;
import com.centersoft.enums.SelectType;
import com.centersoft.stimsscanapp.R;
import com.centersoft.transformer.CommonTransformer;
import com.centersoft.utils.NetCashUtil;

import butterknife.BindView;
import butterknife.OnClick;

//生产缴库
public class CjStorageJkActy extends BaseActivity {


    @BindView(R.id.scmid)
    TextView scmid;

    @BindView(R.id.tv_checked)
    TextView tv_checked;

    @BindView(R.id.materialid)
    TextView materialid;

    @BindView(R.id.material)
    TextView material;

    @BindView(R.id.specmodel)
    TextView specmodel;

    @BindView(R.id.bt_confi)
    Button bt_confi;


    @BindView(R.id.allnums)
    TextView allnums; //收货数

    @BindView(R.id.batchcode)
    TextView batchcode;


    @BindView(R.id.tv_kf)
    TextView tv_kf;

    @Override
    protected void setToolBarTitle() {
        toobarTitle.setText("车间缴库");
    }

    @Override
    protected int initResource() {
        return R.layout.acty_cpstorage_in;
    }


    ManuIm manuIm;

    @Override
    protected void reqData() {
        kf_id = "";
        tv_kf.setText("");
        tv_checked.setText("");
        scmid.setText(barcodeStr);

        httpService.getManuImByCode(barcodeStr)//PC171103001-313
                .compose(new CommonTransformer())
                .subscribe(new BaseObserver<ManuIm>(dialog) {
                    @Override
                    protected void onSuccess(ManuIm m) {

                        manuIm = m;
                        materialid.setText(manuIm.getMaterialid());
                        material.setText(manuIm.getMaterial());
                        specmodel.setText(manuIm.getSpecmodel());
                        batchcode.setText(manuIm.getBatchcode());
                        allnums.setText(manuIm.getMoveNums() + "");
                        kf_id = manuIm.getRecedept();
                        if (!TextUtils.isEmpty(kf_id)) {
                            NetCashUtil.getStoresCash(CashType.StorageCp, kf_id, new NetCashUtil.CallBack<String>() {
                                @Override
                                public void success(String result) {
                                    tv_kf.setText(result);
                                }
                            });
                        }

                        bt_confi.setVisibility(View.VISIBLE);
                    }
                });

    }

    //设置库房
    String kf_id = "";

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode > 0 && resultCode == SelectType.StorageCp.getCode()) {
            CashBaseData cashBaseData = JSON.parseObject(data.getStringExtra("data"), CashBaseData.class);
            kf_id = cashBaseData.getId();
            tv_kf.setText(cashBaseData.getManudept());
        }
    }


    @OnClick({R.id.bt_confi, R.id.btn_select})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_confi:

                if (manuIm == null || barcodeStr == "") {
                    showTips(R.drawable.jd_toast_jg, "请先扫描数据");
                    return;
                }

                if (TextUtils.isEmpty(kf_id)) {
                    showTips(R.drawable.jd_toast_jg, "请选择库房");
                    return;
                }

                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("成品入库");
                builder.setCancelable(true);
                builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface d, int which) {

                    }
                });
                builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface d, int which) {


                        httpService.saveManuImByCode(barcodeStr, kf_id)
                                .compose(new CommonTransformer())
                                .subscribe(new BaseObserver<ManuIm>(dialog) {
                                    @Override
                                    protected void onSuccess(ManuIm m) {
                                        tv_checked.setText("已入库");
                                        tv_kf.setText("");
                                        kf_id = "";
                                        showMessage("操作成功");
                                        bt_confi.setVisibility(View.GONE);
                                        manuIm = null;

                                    }
                                });
                    }
                });

                builder.show();
                break;
            case R.id.btn_select:
                Bundle bl = new Bundle();
                bl.putSerializable("SelectType", SelectType.StorageCp);
                openActivityForResult(SelectDataActy.class, bl);
                break;
        }
    }


}
