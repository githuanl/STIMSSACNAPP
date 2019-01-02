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
import com.centersoft.base.BaseEntity;
import com.centersoft.base.BaseObserver;
import com.centersoft.entity.CashBaseData;
import com.centersoft.entity.Customer;
import com.centersoft.entity.StroageOut;
import com.centersoft.enums.CashType;
import com.centersoft.enums.SelectType;
import com.centersoft.stimsscanapp.R;
import com.centersoft.transformer.CommonTransformer;
import com.centersoft.utils.Constant;
import com.centersoft.utils.NetCashUtil;
import com.centersoft.utils.PreciseCompute;
import com.jakewharton.rxbinding2.widget.RxTextView;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.functions.Consumer;

public class SaleStorageActy extends BaseActivity {

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

    @BindView(R.id.customerName)
    TextView customerName;

    @BindView(R.id.custid)
    TextView custid;

    @BindView(R.id.rate)
    TextView rate;

    @BindView(R.id.batchcode)
    TextView batchcode;

    @Override
    protected void setToolBarTitle() {
        toobarTitle.setText("销售出库");
    }

    @Override
    protected int initResource() {
        return R.layout.acty_salestorage;
    }

    double outNumD = 0.0;
    double fzNumD = 0.0;

    @Override
    protected void initData() {

        RxTextView.textChanges(fzNum)
                .subscribe(new Consumer<CharSequence>() {
                    @Override
                    public void accept(CharSequence charSequence) throws Exception {
                        if (stroageOut == null) {
                            return;
                        }
                        if (!TextUtils.isEmpty(charSequence.toString())) {
                            double currentValue = Double.parseDouble(charSequence.toString());
                            if (fzNumD != currentValue) {
                                outNumD = (int) PreciseCompute.mul(stroageOut.getRate(), currentValue);
                                outnums.setText(outNumD + "");
                            }
                        } else {
                            outnums.setText("");
                            bt_confirm.setVisibility(View.GONE);
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

                        if (!TextUtils.isEmpty(charSequence.toString())) {
                            double currentValue = Double.parseDouble(charSequence.toString());
                            if (currentValue > ylNum) {
                                showTips(R.drawable.jd_toast_jg, "出库数量不能大于库存余量!");
                                outnums.setText("");
                                fzNum.setText("");
                                outNumD = 0.0;
                                fzNumD = 0.0;
                                bt_confirm.setVisibility(View.GONE);
                            } else {
                                if (outNumD != currentValue) {
                                    fzNumD = PreciseCompute.div(currentValue, stroageOut.getRate());
                                    fzNum.setText(fzNumD + "");
                                }
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
        httpService.getMaterialByBarcode(barcodeStr)
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
                        rate.setText(stroageOut.getRate() + "");
                        ylNum = PreciseCompute.sub(PreciseCompute.sub(stroageOut.getNums(), stroageOut.getOcunums()), stroageOut.getOutnums());
                        yuliang.setText(ylNum + "");
                        specmodel.setText(stroageOut.getSpecmodel());
                        custid.setText(stroageOut.getCustid());
                        batchcode.setText(stroageOut.getManubatchnum());

                        if (!TextUtils.isEmpty(stroageOut.getCustid())) {
                            NetCashUtil.getBaseCash(CashType.Customer, stroageOut.getCustid(), new NetCashUtil.CallBack<String>() {
                                @Override
                                public void success(String result) {
                                    customerName.setText(result);
                                }
                            });
                        } else {
                            //根据物料 设定 默认的客户
                            httpService.getDefaultCustomerByMaterialid(stroageOut.getMaterialid())
                                    .compose(new CommonTransformer())
                                    .subscribe(new BaseObserver<BaseEntity<Customer>>(null) {
                                        @Override
                                        protected void onSuccess(BaseEntity<Customer> baseEntity) {
                                            Customer customer = baseEntity.getData();
                                            custid.setText(customer.getId());
                                            customerName.setText(customer.getCustname());
                                            reqMap.put("custid", customer.getId());
                                        }
                                    });
                        }

                        outNumD = ylNum;
                        fzNumD = PreciseCompute.div(ylNum, stroageOut.getRate());

                        //本次出库数量
                        outnums.setText(ylNum + "");
                        fzNum.setText(fzNumD + "");

                        reqMap.put("custid", stroageOut.getCustid());
                    }
                });

    }

    @OnClick({R.id.bt_confirm, R.id.btn_select})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_confirm:
                if (reqMap.get("custid") == null || TextUtils.isEmpty(reqMap.get("custid").toString())) {
                    showTips(R.drawable.jd_toast_jg, "请选择客户");
                    return;
                }
                submitData();
                break;
            case R.id.btn_select:
                Bundle bl = new Bundle();
                bl.putSerializable("SelectType", SelectType.Customer);
                openActivityForResult(SelectDataActy.class, bl);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode > 0 && resultCode == SelectType.Customer.getCode()) {
            CashBaseData cashBaseData = JSON.parseObject(data.getStringExtra("data"), CashBaseData.class);
            custid.setText(cashBaseData.getId());
            reqMap.put("custid", cashBaseData.getId());
            customerName.setText(cashBaseData.getCustname());
        }
    }

    void submitData() {

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("出库确认!");
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
                    reqMap.put("auxnums", "0");
                }

                reqMap.put("emid", Constant.EMPLOYEEID);

                reqMap.put("manuautoid", stroageOut.getManuautoid());
                reqMap.put("manubatchnum", stroageOut.getManubatchnum());
                reqMap.put("materialid", stroageOut.getMaterialid());
                reqMap.put("nums", outnums.getText().toString());

                httpService.insertIntoScan(reqMap)
                        .compose(new CommonTransformer())
                        .subscribe(new BaseObserver<BaseEntity<String>>(dialog) {
                            @Override
                            protected void onSuccess(BaseEntity<String> obj) {
//                                showTips(R.drawable.jd_toast_ok, obj.getMsg());

                                showMessage(obj.getMsg());

                                bt_confirm.setVisibility(View.GONE);
                                stroageOut = null;
                                outnums.setText("");
                                fzNum.setText("");
                            }
                        });
            }
        });
        builder.show();


    }
}
