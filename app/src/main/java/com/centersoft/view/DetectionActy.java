package com.centersoft.view;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.centersoft.base.BaseActivity;
import com.centersoft.base.BaseEntity;
import com.centersoft.base.BaseObserver;
import com.centersoft.entity.Detection;
import com.centersoft.enums.CashType;
import com.centersoft.enums.CheckState;
import com.centersoft.stimsscanapp.R;
import com.centersoft.transformer.CommonTransformer;
import com.centersoft.utils.NetCashUtil;
import com.centersoft.utils.PreciseCompute;
import com.jakewharton.rxbinding2.widget.RxTextView;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.functions.Consumer;

//外检
public class DetectionActy extends BaseActivity {


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

    @BindView(R.id.bt_confi)
    Button bt_confi;


    @BindView(R.id.allnums)
    TextView allnums; //收货数

    @BindView(R.id.ed_bhg)
    EditText ed_bhg;    //不合格数

    @BindView(R.id.ed_hg)
    EditText ed_hg;     //合格数

    @BindView(R.id.batchcode)
    TextView batchcode;

    @BindView(R.id.suid)
    TextView suid;

    @BindView(R.id.tv_checked)
    TextView tv_checked;


    @Override
    protected void setToolBarTitle() {
        toobarTitle.setText("外检");
    }

    @Override
    protected int initResource() {
        return R.layout.acty_detection;
    }

    @Override
    protected void initData() {

        RxTextView.textChanges(ed_bhg)
                .subscribe(new Consumer<CharSequence>() {
                    @Override
                    public void accept(CharSequence charSequence) throws Exception {
                        if (detection == null) {
                            return;
                        }
                        if (!TextUtils.isEmpty(charSequence.toString())) {
                            double hgs = PreciseCompute.sub(detection.getAllnums(), Double.parseDouble(charSequence.toString()));
                            if (hgs > 0) {
                                ed_hg.setText(PreciseCompute.round(hgs, 2) + "");
                            } else {
                                showTips(R.drawable.jd_toast_jg, "不合格数不能大于入库数");
                                ed_bhg.setText("");
                                ed_hg.setText(PreciseCompute.round(detection.getAllnums(), 2) + "");
                            }
                        } else {
                            ed_hg.setText(PreciseCompute.round(detection.getAllnums(), 2) + "");
                        }
                    }
                });
    }

    Detection detection;

    @Override
    protected void reqData() {
        tv_checked.setText("");
        detection = null;
        httpService.getQcmByStcc(barcodeStr)//POD17-1031001
                .compose(new CommonTransformer())
                .subscribe(new BaseObserver<Detection>(dialog) {
                    @Override
                    protected void onSuccess(Detection de) {

                        scmid.setText(de.getQrcodes());
                        materialid.setText(de.getMaterialid());
                        material.setText(de.getMaterial());
                        specmodel.setText(de.getSpecmodel());
                        batchcode.setText(de.getBatchcode());
                        suid.setText(de.getSuid());
                        tv_checked.setText(de.getChecked().getText());
                        allnums.setText(PreciseCompute.round(de.getAllnums(), 2) + " (" + de.getUnit() + ")");

                        ed_bhg.setText("");
                        ed_bhg.setEnabled(true);

                        //显示供应商
                        if(!TextUtils.isEmpty(de.getSuid())) {
                            NetCashUtil.getBaseCash(CashType.Supply, de.getSuid(), new NetCashUtil.CallBack<String>() {
                                @Override
                                public void success(String result) {
                                    supply.setText(result);
                                }
                            });
                        }

                        if (de.getChecked() == CheckState.checking) {
                            bt_confi.setVisibility(View.VISIBLE);
                            ed_hg.setText(PreciseCompute.round(de.getAllnums(), 2) + "");
                        } else {
                            ed_hg.setText(PreciseCompute.round(de.getEnnums(), 2) + "");
                            ed_bhg.setText(PreciseCompute.round(de.getUnnums(), 2) + "");
                        }

                        detection = de;
                    }
                });

    }

    boolean hegeBl = true;
    EditText editText;

    @OnClick(R.id.bt_confi)
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_confi:

                if (detection == null) {
                    showTips(R.drawable.jd_toast_jg, "请先扫描数据");
                    return;
                }

                if (!detection.getChecked().toString().equals(CheckState.checking.toString())) {
                    showTips(R.drawable.jd_toast_ok, "状态为" + CheckState.checking.getText() + "不能进行检验!");
                    return;
                }

                double unNum = 0;
                if (!TextUtils.isEmpty(ed_bhg.getText())) {
                    unNum = Double.valueOf(ed_bhg.getText().toString());
                }

                detection.setEnnums(Double.valueOf(ed_hg.getText().toString()));//合格数
                detection.setSubnums(detection.getAllnums());//交检数
                detection.setUnnums(unNum); //不合格数

//                final QcmExaMaster qcmExaMaster = new QcmExaMaster();
//                qcmExaMaster.setQcemid(Constant.EMPLOYEEID);//登录人id
//                qcmExaMaster.setStbillcode(detection.getStimbillcode());
//                qcmExaMaster.setBillcode(detection.getBillcode());
//                qcmExaMaster.setChecked(detection.getChecked().toString());
//                qcmExaMaster.setPuremid(detection.getPuremid());
//                qcmExaMaster.setSuid(detection.getSuid());
//                qcmExaMaster.setStemid(detection.getStemid());

                if (unNum > 0) {//需要填写不合格原因
                    detection.setConclusion("disQua");
                    hegeBl = false;
                } else {
                    detection.setConclusion("qua");
                    hegeBl = true;
                }

                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("外检确认");
                if (!hegeBl) {
                    View view = View.inflate(context, R.layout.detection_dialog, null);
                    editText = view.findViewById(R.id.ed_remark);
                    builder.setView(view);
                }

                builder.setCancelable(false);
                builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                // 更新
                builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface d, int which) {

                        if (!hegeBl) {
                            if (TextUtils.isEmpty(editText.getText())) {
                                showTips(R.drawable.jd_toast_jg, "请输入不合格原因");
                                return;
                            } else {
                                detection.setResons(editText.getText().toString());
                            }
                        }

                        String str = JSON.toJSONString(detection);
                        httpService.terminalSubmit(str)
                                .compose(new CommonTransformer())
                                .subscribe(new BaseObserver<BaseEntity<String>>(dialog) {
                                    @Override
                                    protected void onSuccess(BaseEntity<String> stringBaseEntity) {
                                        tv_checked.setText("已检验");
                                        showTips(R.drawable.jd_toast_ok, "检验完成");
                                        bt_confi.setVisibility(View.GONE);
                                        detection = null;
                                    }
                                });
                    }
                });

                builder.show();
                break;
        }
    }


}
