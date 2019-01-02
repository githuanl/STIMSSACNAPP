package com.centersoft.view;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.centersoft.base.BaseActivity;
import com.centersoft.base.BaseObserver;
import com.centersoft.entity.CashBaseData;
import com.centersoft.entity.StorageIn;
import com.centersoft.enums.CashType;
import com.centersoft.enums.CheckState;
import com.centersoft.enums.SelectType;
import com.centersoft.stimsscanapp.R;
import com.centersoft.transformer.CommonTransformer;
import com.centersoft.utils.NetCashUtil;

import butterknife.BindView;
import butterknife.OnClick;

//扫描收货
public class ScanShouhuoActy extends BaseActivity {


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

    @BindView(R.id.supply)
    TextView supply;

//    @BindView(R.id.bt_confi)
//    Button bt_confi;

    @BindView(R.id.suid)
    TextView suid;

    @BindView(R.id.allnums)
    EditText allnums; //收货数

    @BindView(R.id.batchcode)
    TextView batchcode;

    @BindView(R.id.tv_inspector)
    TextView tv_inspector;

    @BindView(R.id.bt_submit)
    Button bt_submit;

    @BindView(R.id.tv_kf)
    TextView tv_kf;

    @Override
    protected void setToolBarTitle() {
        toobarTitle.setText("扫描收货");
    }

    @Override
    protected int initResource() {
        return R.layout.acty_scan_shouhuo;
    }


    StorageIn storageIn;

    @Override
    protected void reqData() {

        tv_checked.setText("");
        kf_id = "";
        scmid.setText(barcodeStr);
        storageIn = null;

        httpService.getQrcodesCreateList(barcodeStr)         //D1365161230A043141A2
                .compose(new CommonTransformer())
                .subscribe(new BaseObserver<StorageIn>(dialog) {
                    @Override
                    protected void onSuccess(StorageIn s) {

                        if(s.getChecked() == CheckState.noRevice){
                            allnums.setEnabled(true);
                        }else{
                            allnums.setEnabled(false);
                        }

                        storageIn = s;

                        materialid.setText(storageIn.getMaterialid());
                        material.setText(storageIn.getMaterial());
                        specmodel.setText(storageIn.getSpecmodel());
                        batchcode.setText(storageIn.getBanchnum());
                        tv_checked.setText(storageIn.getChecked().getText());
                        allnums.setText(storageIn.getNums());

                        suid.setText(storageIn.getSuid());

                        psnId = storageIn.getTeemid();

                        if (!TextUtils.isEmpty(storageIn.getSuid())) {
                            //显示供应商
                            NetCashUtil.getBaseCash(CashType.Supply, storageIn.getSuid(), new NetCashUtil.CallBack<String>() {
                                @Override
                                public void success(String result) {
                                    supply.setText(result);
                                }
                            });
                        }

                        kf_id = storageIn.getStid();
                        //显示 库房
                        if (!TextUtils.isEmpty(kf_id)) {

                            NetCashUtil.getAllManuDeptByPur(CashType.StorageCg, kf_id, new NetCashUtil.CallBack<String>() {
                                @Override
                                public void success(String result) {
                                    tv_kf.setText(result);
                                }
                            });
                        }

                        //显示质检人员
                        if (!TextUtils.isEmpty(psnId)) {
                            NetCashUtil.getInspector(CashType.Inspector, psnId, new NetCashUtil.CallBack<String>() {
                                @Override
                                public void success(String result) {
                                    tv_inspector.setText(result);
                                }
                            });
                        }

                        if (storageIn.getChecked() == CheckState.save) {
                            bt_submit.setVisibility(View.VISIBLE);
                        } else if (storageIn.getChecked() == CheckState.noRevice) { // 未收货
//                            bt_confi.setVisibility(View.VISIBLE);
                            bt_submit.setVisibility(View.VISIBLE);
                        } else {
//                            bt_confi.setVisibility(View.GONE);
                            bt_submit.setVisibility(View.GONE);
                        }

                    }
                });
    }

    //质检人员的id
    String psnId = "";
    //设置库房
    String kf_id = "";

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (resultCode > 0 && resultCode == SelectType.Inspector.getCode()) {
            CashBaseData cashBaseData = JSON.parseObject(data.getStringExtra("data"), CashBaseData.class);
            tv_inspector.setText(cashBaseData.getPsnname());
            psnId = cashBaseData.getId();
        } else if (resultCode == SelectType.StorageCg.getCode()) {
            CashBaseData cashBaseData = JSON.parseObject(data.getStringExtra("data"), CashBaseData.class);
            tv_kf.setText(cashBaseData.getManudept());
            kf_id = cashBaseData.getId();
        }
    }


    @OnClick({R.id.bt_confi, R.id.btn_select, R.id.btn_select2, R.id.bt_submit})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_confi:

//                if (storageIn == null) {
//                    showTips(R.drawable.jd_toast_jg, "请先扫描数据");
//                    return;
//                }
//
//                if (storageIn.getChecked() != CheckState.noRevice) {
//                    showTips(R.drawable.jd_toast_ok, "状态为" + storageIn.getChecked().getText() + "不能进行保存!");
//                    return;
//                }
//
//                if (TextUtils.isEmpty(kf_id)) {
//                    showTips(R.drawable.jd_toast_jg, "请选择库房");
//                    return;
//                }
//
//                AlertDialog.Builder builder = new AlertDialog.Builder(context);
//                builder.setTitle("收货确认");
//                builder.setCancelable(false);
//                builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//
//                    }
//                });
//                builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface d, int which) {
//
//                        httpService.saveStoOrder(barcodeStr, psnId, kf_id)
//                                .compose(new CommonTransformer())
//                                .subscribe(new BaseObserver<BaseEntity>(dialog) {
//                                    @Override
//                                    protected void onSuccess(BaseEntity stringBaseEntity) {
//                                        tv_checked.setText("保存");
//                                        showTips(R.drawable.jd_toast_ok, stringBaseEntity.getMsg());
//                                        bt_confi.setVisibility(View.GONE);
//                                    }
//                                });
//                    }
//                });
//                builder.show();
                break;
            case R.id.btn_select2:
                Bundle b = new Bundle();
                b.putSerializable("SelectType", SelectType.StorageCg);
                openActivityForResult(SelectDataActy.class, b);
                break;
            case R.id.btn_select:
                Bundle bl = new Bundle();
                bl.putSerializable("SelectType", SelectType.Inspector);
                openActivityForResult(SelectDataActy.class, bl);
                break;
            case R.id.bt_submit:

                if (storageIn == null) {
                    showTips(R.drawable.jd_toast_jg, "请先扫描数据");
                    return;
                }

                if (TextUtils.isEmpty(kf_id)) {
                    showTips(R.drawable.jd_toast_jg, "请选择库房");
                    return;
                }

                //出库数量不能为空
                if (TextUtils.isEmpty(allnums.getText().toString())) {
                    showTips(R.drawable.jd_toast_jg, "请输入出库数量");
                    return;
                }

                if (TextUtils.isEmpty(tv_inspector.getText().toString()) || TextUtils.isEmpty(psnId)) {
                    showTips(R.drawable.jd_toast_jg, "质检人员不能为空");
                    return;
                }

//                if (storageIn.getChecked() != CheckState.noRevice && storageIn.getChecked() != CheckState.save) {
//                    showTips(R.drawable.jd_toast_ok, "状态为" + storageIn.getChecked().getText() + "不能进行提交!");
//                    return;
//                }

                AlertDialog.Builder bud = new AlertDialog.Builder(context);
                bud.setTitle("提交送检");
                bud.setCancelable(false);
                bud.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                bud.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface d, int which) {

                        httpService.submitStoOrder(barcodeStr, psnId, kf_id, allnums.getText().toString())
                                .compose(new CommonTransformer())
                                .subscribe(new BaseObserver<StorageIn>(dialog) {
                                    @Override
                                    protected void onSuccess(StorageIn storageIn) {
                                        allnums.setEnabled(false);
                                        tv_checked.setText(storageIn.getChecked().getText());
                                        showTips(R.drawable.jd_toast_ok, "已提交送检");
//                                        bt_confi.setVisibility(View.GONE);
                                        bt_submit.setVisibility(View.GONE);
                                        storageIn = null;
                                    }
                                });
                    }
                });
                bud.show();
                break;
        }
    }


}
