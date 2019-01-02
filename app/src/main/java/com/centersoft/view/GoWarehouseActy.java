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
import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.StringUtils;
import com.centersoft.base.BaseActivity;
import com.centersoft.base.BaseEntity;
import com.centersoft.base.BaseObserver;
import com.centersoft.entity.CashBaseData;
import com.centersoft.entity.StroageOut;
import com.centersoft.enums.SelectType;
import com.centersoft.stimsscanapp.R;
import com.centersoft.transformer.CommonTransformer;
import com.centersoft.utils.Constant;
import com.centersoft.utils.PreciseCompute;
import com.jakewharton.rxbinding2.widget.RxTextView;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.functions.Consumer;


public class GoWarehouseActy extends BaseActivity {

    @BindView(R.id.scmid)
    TextView scmid;

    @BindView(R.id.materialid)
    TextView materialid;

    @BindView(R.id.material)
    TextView material;

    @BindView(R.id.yuliang)
    TextView yuliang;

    @BindView(R.id.specmodel)
    TextView specmodel;

    @BindView(R.id.fzNum)
    EditText fzNum;

    @BindView(R.id.outnums)
    EditText outnums;

    @BindView(R.id.bt_confirm)
    Button bt_confirm;

    @BindView(R.id.tv_dept)
    TextView tv_dept;

    @BindView(R.id.rate)
    TextView rate;

    @BindView(R.id.batchcode)
    TextView batchcode;

    @Override
    protected void setToolBarTitle() {
        toobarTitle.setText("领料出库");
    }

    @Override
    protected int initResource() {
        return R.layout.acty_gowarehouse;
    }

    boolean isOut = false;

    @Override
    protected void initData() {


        RxTextView.textChanges(fzNum)
                .subscribe(new Consumer<CharSequence>() {
                    @Override
                    public void accept(CharSequence charSequence) throws Exception {
                        if (stroageOut == null) {
                            return;
                        }
                        isOut = true;
                        if (!TextUtils.isEmpty(charSequence.toString())) {
                            try {
                                double outNum = PreciseCompute.mul(stroageOut.getRate(), Double.parseDouble(charSequence.toString()));
                                outnums.setText(outNum + "");
                            } catch (NumberFormatException e) {
                                outnums.setText("");
                            }
                        }
                    }
                });

        RxTextView.textChanges(outnums)
                .subscribe(new Consumer<CharSequence>() {
                    @Override
                    public void accept(CharSequence charSequence) throws Exception {
                        if (stroageOut == null) {
                            return;
                        }
                        if (!TextUtils.isEmpty(charSequence.toString()) && !charSequence.toString().equals(".")) {
                            if (Double.parseDouble(charSequence.toString()) > ylNum) {
                                showTips(R.drawable.jd_toast_jg, "出库数量不能大于库存余量!");
                                outnums.setText("");
                                fzNum.setText("");
                                bt_confirm.setVisibility(View.GONE);
                            } else {
                                bt_confirm.setVisibility(View.VISIBLE);
                            }
                        } else {
                            bt_confirm.setVisibility(View.GONE);
                        }
                    }
                });

    }

    StroageOut stroageOut;

    double ylNum = 0;

    @Override
    protected void reqData() {

        scmid.setText(barcodeStr);
        deptid = "";
        tv_dept.setText("");

        httpService.getMaterialByScan(barcodeStr) //扫描批次号
                .compose(new CommonTransformer())
                .subscribe(new BaseObserver<BaseEntity<StroageOut>>(dialog) {
                    @Override
                    protected void onSuccess(BaseEntity<StroageOut> baseEntity) {

                        if (baseEntity.getFlag() == 2) {
                            showMessage(baseEntity.getMsg());
                        }
                        stroageOut = baseEntity.getData();
                        materialid.setText(stroageOut.getMaterialid());
                        material.setText(stroageOut.getMaterial());
                        batchcode.setText(stroageOut.getManubatchnum());
                        rate.setText(stroageOut.getRate() + "");
                        ylNum = PreciseCompute.sub(PreciseCompute.sub(stroageOut.getNums(), stroageOut.getOcunums()), stroageOut.getOutnums());
                        yuliang.setText(ylNum + "");
                        specmodel.setText(stroageOut.getSpecmodel());
                    }
                });

    }

    @OnClick({R.id.bt_confirm, R.id.btn_selectdept})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_confirm:
                if (StringUtils.isEmpty(deptid)) {
                    showTips(R.drawable.jd_toast_jg, "请选择需求部门!");
                    return;
                }
                submitData();
                break;
            case R.id.btn_selectdept:
                Bundle b = new Bundle();
                b.putSerializable("SelectType", SelectType.Department);
                openActivityForResult(SelectDataActy.class, b);
                break;
        }
    }

    String deptid = "";//需求部门编号

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (resultCode > 0 && resultCode == SelectType.Department.getCode()) {
            CashBaseData cashBaseData = JSON.parseObject(data.getStringExtra("data"), CashBaseData.class);
            tv_dept.setText(cashBaseData.getDepartment());
            deptid = cashBaseData.getId();
        }
    }


    void submitData() {

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("领料出库确认!");
        builder.setCancelable(false);
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface d, int which) {

                if (fzNum.getText() != null && !TextUtils.isEmpty(fzNum.getText().toString())) {
                    reqMap.put("auxnums", fzNum.getText().toString());
                } else {
                    reqMap.put("auxnums", PreciseCompute.div(Double.parseDouble(outnums.getText().toString()), stroageOut.getRate()) + "");
                }

                reqMap.put("manubatchnum", barcodeStr);
                reqMap.put("materialid", stroageOut.getMaterialid());
                reqMap.put("stid", stroageOut.getStid());
                reqMap.put("manudept", deptid);
                reqMap.put("nums", outnums.getText().toString());

                httpService.insertIntoScanByManu(reqMap)
                        .compose(new CommonTransformer())
                        .subscribe(new BaseObserver<BaseEntity<String>>(dialog) {
                            @Override
                            protected void onSuccess(BaseEntity<String> obj) {
                                showTips(R.drawable.jd_toast_ok, obj.getMsg());
                                bt_confirm.setVisibility(View.GONE);
                                stroageOut = null;
                                outnums.setText("");
                                fzNum.setText("");
                                reqData();
                            }
                        });
            }
        });
        builder.show();

    }
}

