package com.centersoft.view;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.centersoft.adapter.SuperBaseAdapter;
import com.centersoft.base.BaseActivity;
import com.centersoft.base.BaseEntity;
import com.centersoft.base.BaseHolder;
import com.centersoft.base.BaseObserver;
import com.centersoft.entity.StorageOut2;
import com.centersoft.entity.StroageOutDetail;
import com.centersoft.enums.CashType;
import com.centersoft.stimsscanapp.R;
import com.centersoft.transformer.CommonTransformer;
import com.centersoft.utils.NetCashUtil;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SaleStorageOut2Acty extends BaseActivity<StroageOutDetail> {


    SaleStorageOutAdapter adapter;

    @BindView(R.id.lv_list)
    ListView lv_list;

    @BindView(R.id.scmid)
    TextView scmid;

    @BindView(R.id.contact)
    TextView contact;

    @BindView(R.id.checked)
    TextView checked;

    @BindView(R.id.amount)
    TextView amount;

    @BindView(R.id.billdate)
    TextView billdate;

    @Override
    protected void setToolBarTitle() {
        toobarTitle.setText("销售出库(单)");
    }

    @Override
    protected int initResource() {
        return R.layout.acty_salestorageout2;
    }

    @Override
    protected void reqData() {
        scmid.setText(barcodeStr);
        httpService.getMasterAndDetailsByScan(barcodeStr)
                .compose(new CommonTransformer())
                .subscribe(new BaseObserver<BaseEntity<StorageOut2>>(dialog) {
                    @Override
                    protected void onSuccess(BaseEntity<StorageOut2> be) {

                        StorageOut2 storageOut2 = be.getData();

                        if (!TextUtils.isEmpty(storageOut2.getMaster().getContact())) {
                            NetCashUtil.getBaseCash(CashType.Customer, storageOut2.getMaster().getContact(), new NetCashUtil.CallBack<String>() {
                                @Override
                                public void success(String result) {
                                    contact.setText(result);
                                }
                            });
                        }
                        amount.setText(storageOut2.getMaster().getAmount());
                        checked.setText(storageOut2.getMaster().getChecked().getText());
                        billdate.setText(storageOut2.getMaster().getBilldate());


                        listDatas.clear();
                        listDatas.addAll(storageOut2.getDetails());
                        adapter.notifyDataSetChanged();
                    }
                });
    }

    @Override
    protected void onResume() {
        if (!TextUtils.isEmpty(barcodeStr)) {
            reqData();
        }
        super.onResume();
    }



    @Override
    protected void initData() {
        adapter = new SaleStorageOutAdapter(listDatas);
        lv_list.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        lv_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                StroageOutDetail stroageOutDetail = listDatas.get(i);

                Bundle bl = new Bundle();
                bl.putSerializable("StroageOutDetail", stroageOutDetail);
                openActivity(SaleStorageOut2DetailsActy.class, bl);
            }
        });
    }

    public class SaleStorageOutAdapter extends SuperBaseAdapter<StroageOutDetail> {

        public SaleStorageOutAdapter(List<StroageOutDetail> datas) {
            super(datas);
        }

        @Override
        protected BaseHolder<StroageOutDetail> getHolder() {
            return new SaleStorageOutHod();
        }
    }

    public class SaleStorageOutHod extends BaseHolder<StroageOutDetail> {


        @BindView(R.id.Material)
        TextView Material;

        @BindView(R.id.specmodel)
        TextView specmodel;

        @BindView(R.id.tv_nums)
        TextView tv_nums;


        @BindView(R.id.materialid)
        TextView materialid;

        @BindView(R.id.checked)
        TextView checked;

        @Override
        protected View initView() {
            View v = View.inflate(context, R.layout.process_item, null);
            ButterKnife.bind(this, v);
            return v;
        }

        @Override
        protected void refreshUI(StroageOutDetail data) {
            Material.setText(data.getMaterial());
            specmodel.setText(data.getSpecmodel());
            tv_nums.setText(data.getNums());
            materialid.setText(data.getMaterialid());
            checked.setText(data.getChecked().getText());
        }

    }
}
