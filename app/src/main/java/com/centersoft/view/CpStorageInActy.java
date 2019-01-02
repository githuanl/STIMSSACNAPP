package com.centersoft.view;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.blankj.utilcode.util.SPUtils;
import com.centersoft.base.BaseActivity;
import com.centersoft.base.BaseEntity;
import com.centersoft.base.BaseObserver;
import com.centersoft.entity.ManuImDetail;
import com.centersoft.entity.StorageInCen;
import com.centersoft.enums.ManuImDetailCheckedEnum;
import com.centersoft.stimsscanapp.R;
import com.centersoft.transformer.CommonTransformer;
import com.centersoft.utils.Constant;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

//成品入库
public class CpStorageInActy extends BaseActivity {


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

    @BindView(R.id.bt_Reject)
    Button bt_Reject;

    @BindView(R.id.bt_submit)
    Button bt_submit;


    @BindView(R.id.allnums)
    TextView allnums; //收货数

    @BindView(R.id.batchcode)
    TextView batchcode;

    @Override
    protected void setToolBarTitle() {
        toobarTitle.setText("成品入库");
    }

    @Override
    protected int initResource() {
        return R.layout.acty_cpstorage_in_bf;
    }


    ManuImDetail manuImDetail;


    boolean isShowDialog = false; //dialog 状态
    String kwStr = "";

    boolean ifStbin = false;

    @Override
    protected void reqData() {

        kwStr = "";
        if (isShowDialog && ifStbin) {
            boolean isContain = false;
            for (StorageInCen.Kw kw : kwList) {
                if (kw.getQrcodes().equals(barcodeStr)) {
                    _scimd.setText(kw.getStbinsn());
                    kwStr = kw.getStbincode();
                    isContain = true;
                    reqMap.put("stbincode", kwStr);  //仓区
                    break;
                }
            }

            if (!isContain) {
                showTips(R.drawable.jd_toast_jg, "库位不匹配");
                return;
            }
            return;
        }

        ifStbin = false;
        tv_checked.setText("");
        scmid.setText(barcodeStr);


        manuImDetail = null;

        httpService.manuImByScan(barcodeStr)//PC171103001-313
                .compose(new CommonTransformer())
                .subscribe(new BaseObserver<BaseEntity<ManuImDetail>>(dialog) {
                    @Override
                    protected void onSuccess(BaseEntity<ManuImDetail> detailBaseEntity) {
                        ifStbin = detailBaseEntity.isIfStbin();
                        manuImDetail = detailBaseEntity.getData();

                        if (!SPUtils.getInstance().getString("depart").equals(manuImDetail.getRecedept())) {
                            showMessage("你不是当前物料的库管");
                            return;
                        }

                        materialid.setText(manuImDetail.getMaterialid());
                        material.setText(manuImDetail.getMaterial());
                        specmodel.setText(manuImDetail.getSpecmodel());
                        batchcode.setText(manuImDetail.getBatchcode());
                        tv_checked.setText(manuImDetail.getChecked().getText());

                        if (ifStbin && detailBaseEntity.getStbins() != null) {
                            kwList.addAll(detailBaseEntity.getStbins());
                        }

                        allnums.setText(manuImDetail.getMoveNums() + "");

                        if (manuImDetail.getChecked() == ManuImDetailCheckedEnum.submit) {
                            bt_submit.setVisibility(View.VISIBLE);
                            bt_Reject.setVisibility(View.VISIBLE);
                        } else {
                            bt_submit.setVisibility(View.GONE);
                            bt_Reject.setVisibility(View.GONE);
                        }
                    }
                });

    }

    List<StorageInCen.Kw> kwList = new ArrayList<>();

    TextView _scimd;

    @OnClick({R.id.bt_Reject, R.id.bt_submit})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_submit:

                if (manuImDetail == null) {
                    showTips(R.drawable.jd_toast_jg, "请先扫描数据");
                    return;
                }

                if (manuImDetail.getChecked() != ManuImDetailCheckedEnum.submit) {
                    showTips(R.drawable.jd_toast_ok, "状态为" + manuImDetail.getChecked().getText() + "不能进行拒绝入库!");
                    return;
                }


                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("同意成品入库");
                builder.setCancelable(true);

                if (ifStbin) {   //开启了
                    View view = LayoutInflater.from(context).inflate(R.layout.outsourcing_confir, null);
                    builder.setView(view);
                    builder.setCancelable(false);
                    _scimd = (TextView) view.findViewById(R.id._scimd);
                    _scimd.setText(kwStr);
                }

                builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface d, int which) {
                        isShowDialog = false;
                    }
                });

                isShowDialog = true;

                builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface d, int which) {
                        isShowDialog = false;

                        if (ifStbin && TextUtils.isEmpty(kwStr)) {
                            showTips(R.drawable.jd_toast_jg, "仓区位不能为空");
                            return;
                        }

                        reqMap.put("emid", Constant.EMPLOYEEID);
                        reqMap.put("sort", "Done");
                        reqMap.put("autoid", manuImDetail.getAutoid());
                        reqMap.put("stid", manuImDetail.getRecedept());  //	库房编号
                        reqMap.put("stbincode", kwStr);  //	仓区库位编号

                        httpService.receiveByScan(reqMap)
                                .compose(new CommonTransformer())
                                .subscribe(new BaseObserver<BaseEntity>(dialog) {
                                    @Override
                                    protected void onSuccess(BaseEntity stringBaseEntity) {
                                        tv_checked.setText("已入库");
                                        showMessage("已入库");
                                        bt_submit.setVisibility(View.GONE);
                                        bt_Reject.setVisibility(View.GONE);
                                        manuImDetail = null;
                                    }
                                });
                    }
                });
                builder.show();
                break;
            case R.id.bt_Reject:

                if (manuImDetail == null) {
                    showTips(R.drawable.jd_toast_jg, "请先扫描数据");
                    return;
                }

                if (manuImDetail.getChecked() != ManuImDetailCheckedEnum.submit) {
                    showTips(R.drawable.jd_toast_ok, "状态为" + manuImDetail.getChecked().getText() + "不能进行入库!");
                    return;
                }

                AlertDialog.Builder bd = new AlertDialog.Builder(context);
                bd.setTitle("拒绝成品入库");

                View view = View.inflate(context, R.layout.detection_dialog, null);
                final EditText editText = view.findViewById(R.id.ed_remark);
                bd.setView(view);

                bd.setCancelable(true);
                bd.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface d, int which) {

                    }
                });

                bd.setPositiveButton("拒绝", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface d, int which) {

                        if (TextUtils.isEmpty(editText.getText().toString())) {
                            showTips(R.drawable.jd_toast_jg, "请输入拒绝的原因");
                            return;
                        }

                        reqMap.put("rejectReason", editText.getText().toString());
                        reqMap.put("emid", Constant.EMPLOYEEID);
                        reqMap.put("sort", "Reject");
                        reqMap.put("autoid", manuImDetail.getAutoid());

                        httpService.receiveByScan(reqMap)
                                .compose(new CommonTransformer())
                                .subscribe(new BaseObserver<BaseEntity>(dialog) {
                                    @Override
                                    protected void onSuccess(BaseEntity stringBaseEntity) {
                                        tv_checked.setText("已拒绝");
                                        showTips(R.drawable.jd_toast_ok, "已拒绝");
                                        bt_submit.setVisibility(View.GONE);
                                        bt_Reject.setVisibility(View.GONE);
                                        manuImDetail = null;

                                    }
                                });
                    }
                });

                bd.show();
                break;
        }
    }


}
