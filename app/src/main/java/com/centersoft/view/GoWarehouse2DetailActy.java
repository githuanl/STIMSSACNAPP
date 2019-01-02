package com.centersoft.view;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.blankj.utilcode.util.SPUtils;
import com.centersoft.base.BaseActivity;
import com.centersoft.base.BaseEntity;
import com.centersoft.base.BaseObserver;
import com.centersoft.entity.StorageIn;
import com.centersoft.entity.StroageOutDetail;
import com.centersoft.enums.CashType;
import com.centersoft.enums.SaleInvoiceDetailCheckedEnum;
import com.centersoft.stimsscanapp.R;
import com.centersoft.transformer.CommonTransformer;
import com.centersoft.utils.Constant;
import com.centersoft.utils.NetCashUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

// 有单 领料出库
public class GoWarehouse2DetailActy extends BaseActivity {


    @BindView(R.id.tv_checked)
    TextView tv_checked;

    @BindView(R.id.materialid)
    TextView materialid;

    @BindView(R.id.material)
    TextView material;


    @BindView(R.id.specmodel)
    TextView specmodel;

    @BindView(R.id.nums)
    TextView nums;

    @BindView(R.id.batchnum)
    TextView batchnum;

    @BindView(R.id.manudept)
    TextView manudept;

    @BindView(R.id.bt_confirm)
    Button bt_confirm;

    @BindView(R.id.define2)
    TextView define2;

    @Override
    protected void setToolBarTitle() {
        toobarTitle.setText("领料出库明细");
    }

    @Override
    protected int initResource() {
        return R.layout.acty_gowarehouse_details;
    }

    StroageOutDetail stroageOutDetail;


    List<String> bachNums = new ArrayList<>();

    @Override
    protected void initData() {
        Bundle bl = getIntent().getExtras();
        stroageOutDetail = (StroageOutDetail) bl.getSerializable("StroageOutDetail");
        bachNums = new ArrayList<String>(Arrays.asList(stroageOutDetail.getBatchnum().split(";")));

        if (bachNums == null) {
            bachNums = new ArrayList<>();
        }

        //展示仓区库位
        if (!TextUtils.isEmpty(stroageOutDetail.getDefine2())) {
            httpService.getStbinByStbinName(stroageOutDetail.getDefine2())
                    .compose(new CommonTransformer())
                    .subscribe(new BaseObserver<BaseEntity<List<String>>>(null) {
                        @Override
                        protected void onSuccess(BaseEntity<List<String>> baseEntity) {
                            List<String> strings = baseEntity.getData();
                            String textStr = "";
                            for (String s : strings) {
                                textStr += s + ",";
                            }
                            define2.setText(textStr);
                        }
                    });
        }


        tv_checked.setText(stroageOutDetail.getChecked().getText());
        materialid.setText(stroageOutDetail.getMaterialid());
        material.setText(stroageOutDetail.getMaterial());
        specmodel.setText(stroageOutDetail.getSpecmodel());
        nums.setText(stroageOutDetail.getNums());
        batchnum.setText(stroageOutDetail.getBatchnum());

        if (!TextUtils.isEmpty(stroageOutDetail.getManudept())) {
            NetCashUtil.getBaseCash(CashType.Department, stroageOutDetail.getManudept(), new NetCashUtil.CallBack<String>() {
                @Override
                public void success(String result) {
                    manudept.setText(result);
                }
            });
        }

        if (stroageOutDetail.getChecked() == SaleInvoiceDetailCheckedEnum.DC) {
            bt_confirm.setVisibility(View.VISIBLE);
        } else {
            bt_confirm.setVisibility(View.GONE);
        }

    }

    String batchnumStr = "";

    @Override
    protected void reqData() {
        if (stroageOutDetail.getChecked() != SaleInvoiceDetailCheckedEnum.DC) {
            return;
        }
        if (bachNums.size() > 0) {
            httpService.getMaterialByBatchcode(barcodeStr,stroageOutDetail.getStid())
                    .compose(new CommonTransformer())
                    .subscribe(new BaseObserver<BaseEntity<StorageIn>>(dialog) {
                        @Override
                        protected void onSuccess(BaseEntity<StorageIn> baseEntity) {

                            StorageIn storageIn = baseEntity.getData();
                            batchnumStr = storageIn.getBanchnum();

                            if (bachNums.contains(batchnumStr)) {
                                bachNums.remove(batchnumStr);
                            } else {
                                if (stroageOutDetail.getBatchnum().contains(batchnumStr)) {
                                    showTips(R.drawable.jd_toast_jg, "该批次已核对");
                                } else {
                                    showTips(R.drawable.jd_toast_jg, "批次错误");
                                }
                                return;
                            }
                            if (bachNums.size() == 0) {
                                showTips(R.drawable.jd_toast_ok, "批次全部核对成功");
                            }

                        }
                    });

        } else {
            showTips(R.drawable.jd_toast_ok, "批次已全部核对成功,不需要再次核对");
        }
    }

    @OnClick(R.id.bt_confirm)
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_confirm:

                if (stroageOutDetail.getBatchnum() != null && bachNums.size() > 0) {
                    showTips(R.drawable.jd_toast_jg, "请扫描指定的批次");
                    return;
                }

                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("出库确认");
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

                        reqMap.put("autoid", stroageOutDetail.getAutoid());
                        reqMap.put("emid", Constant.EMPLOYEEID);
                        reqMap.put("stid", SPUtils.getInstance().getString("depart"));

                        httpService.confirmWareHouseDeliverByScan(reqMap)
                                .compose(new CommonTransformer())
                                .subscribe(new BaseObserver<BaseEntity<String>>(dialog) {
                                    @Override
                                    protected void onSuccess(BaseEntity<String> stringBaseEntity) {
                                        tv_checked.setText("发货");
                                        showTips(R.drawable.jd_toast_ok, "出库完成");
                                        bt_confirm.setVisibility(View.GONE);
                                        stroageOutDetail.setChecked(SaleInvoiceDetailCheckedEnum.DC);
                                    }
                                });
                    }
                });

                builder.show();
                break;
        }
    }

}

