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
import com.centersoft.base.BaseEntity;
import com.centersoft.base.BaseObserver;
import com.centersoft.entity.CashBaseData;
import com.centersoft.entity.MaterialsTransit;
import com.centersoft.entity.StorageIn;
import com.centersoft.enums.CashType;
import com.centersoft.enums.MaterialState;
import com.centersoft.enums.SelectType;
import com.centersoft.stimsscanapp.R;
import com.centersoft.transformer.CommonTransformer;
import com.centersoft.utils.NetCashUtil;
import com.centersoft.utils.PreciseCompute;

import butterknife.BindView;
import butterknife.OnClick;

//外购收货
public class WgShouHuoActy extends BaseActivity {


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
    TextView allnums; //收货数

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
        toobarTitle.setText("外购收货");
    }

    @Override
    protected int initResource() {
        return R.layout.acty_outsourcing_received;
    }


    MaterialsTransit materialsTransit;

    @Override
    protected void reqData() {
        tv_checked.setText("");
        scmid.setText(barcodeStr);
        materialsTransit = null;
        httpService.getInfoDetail(barcodeStr)//POD17-1031001
                .compose(new CommonTransformer())
                .subscribe(new BaseObserver<MaterialsTransit>(dialog) {
                    @Override
                    protected void onSuccess(MaterialsTransit de) {

                        if(de.getChecked() == MaterialState.noRevice){
                            allnums.setEnabled(true);
                        }else{
                            allnums.setEnabled(false);
                        }

                        materialsTransit = de;
                        materialid.setText(de.getMaterialid());
                        material.setText(de.getMaterial());
                        specmodel.setText(de.getSpecmodel());
                        batchcode.setText(de.getBanchnum());
                        tv_checked.setText(de.getChecked().getText());

                        allnums.setText(PreciseCompute.round(de.getNums(), 2) + " (" + de.getUnit() + ")");
                        suid.setText(de.getSuid());

                        //显示供应商
                        if (!TextUtils.isEmpty(materialsTransit.getSuid())) {
                            NetCashUtil.getBaseCash(CashType.Supply, materialsTransit.getSuid(), new NetCashUtil.CallBack<String>() {
                                @Override
                                public void success(String result) {
                                    supply.setText(result);
                                }
                            });
                        }

                        psnId = materialsTransit.getTeemid();

                        //显示 库房
                        if (!TextUtils.isEmpty(materialsTransit.getStid())) {
                            kf_id = materialsTransit.getStid();
                            NetCashUtil.getAllManuDeptByPur(CashType.StorageCg, materialsTransit.getStid(), new NetCashUtil.CallBack<String>() {
                                @Override
                                public void success(String result) {
                                    tv_kf.setText(result);
                                }
                            });
                        }

                        //显示质检人员
                        if (!TextUtils.isEmpty(psnId)) {
                            NetCashUtil.getInspector(CashType.Inspector, materialsTransit.getTeemid(), new NetCashUtil.CallBack<String>() {
                                @Override
                                public void success(String result) {
                                    tv_inspector.setText(result);
                                }
                            });
                        }

                        if (materialsTransit.getChecked() == MaterialState.noRevice) {
//                            bt_confi.setVisibility(View.VISIBLE);
                            bt_submit.setVisibility(View.VISIBLE);
                        } else if (materialsTransit.getChecked() == MaterialState.save) {
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

                if (materialsTransit == null) {
                    showTips(R.drawable.jd_toast_jg, "请先扫描数据");
                    return;
                }

                if (!materialsTransit.getChecked().toString().equals(MaterialState.noRevice.toString())) {
                    showTips(R.drawable.jd_toast_ok, "状态为" + materialsTransit.getChecked().getText() + "不能进行保存!");
                    return;
                }

                if (TextUtils.isEmpty(kf_id)) {
                    showTips(R.drawable.jd_toast_jg, "请选择库房");
                    return;
                }

                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("收货确认");
                builder.setCancelable(false);
                builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface d, int which) {

                        httpService.saveStoOrder(barcodeStr, psnId, kf_id)
                                .compose(new CommonTransformer())
                                .subscribe(new BaseObserver<BaseEntity>(dialog) {
                                    @Override
                                    protected void onSuccess(BaseEntity stringBaseEntity) {
                                        tv_checked.setText("保存");
                                        showTips(R.drawable.jd_toast_ok, stringBaseEntity.getMsg());
//                                        bt_confi.setVisibility(View.GONE);
                                    }
                                });
                    }
                });

                builder.show();
                break;
            case R.id.btn_select2:
                Bundle b = new Bundle();
                b.putSerializable("SelectType", SelectType.StorageCg);
                openActivityForResult(SelectDataActy.class, b);
                break;
            case R.id.btn_select:           //检验员
                Bundle bl = new Bundle();
                bl.putSerializable("SelectType", SelectType.Inspector);
                openActivityForResult(SelectDataActy.class, bl);
                break;
            case R.id.bt_submit:

                if (materialsTransit == null) {
                    showTips(R.drawable.jd_toast_jg, "请先扫描数据");
                    return;
                }

                if (TextUtils.isEmpty(tv_inspector.getText().toString()) || TextUtils.isEmpty(psnId)) {
                    showTips(R.drawable.jd_toast_jg, "质检人员不能为空");
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

                if (materialsTransit.getChecked() != MaterialState.save && materialsTransit.getChecked() != MaterialState.noRevice) {
                    showTips(R.drawable.jd_toast_ok, "状态为" + materialsTransit.getChecked().getText() + "不能进行操作!");
                    return;
                }

                AlertDialog.Builder bud = new AlertDialog.Builder(context);
                bud.setTitle("送检提交");
                bud.setCancelable(false);
                bud.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                bud.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface d, int which) {

                        httpService.submitStoOrder(barcodeStr, psnId, kf_id,allnums.getText().toString())
                                .compose(new CommonTransformer())
                                .subscribe(new BaseObserver<StorageIn>(dialog) {
                                    @Override
                                    protected void onSuccess(StorageIn storageIn) {
                                        allnums.setEnabled(false);
                                        tv_checked.setText(storageIn.getChecked().getText());
                                        showTips(R.drawable.jd_toast_ok, "已提交送检");
//                                        bt_confi.setVisibility(View.GONE);
                                        bt_submit.setVisibility(View.GONE);
                                        materialsTransit = null;
                                    }
                                });
                    }
                });

                bud.show();

                break;
        }
    }


}
