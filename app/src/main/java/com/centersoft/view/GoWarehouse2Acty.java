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
import com.centersoft.stimsscanapp.R;
import com.centersoft.transformer.CommonTransformer;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

// 有单 领料出库
public class GoWarehouse2Acty extends BaseActivity <StroageOutDetail>{

    WarehouseAdapter adapter;

    @BindView(R.id.lv_list)
    ListView lv_list;

    @BindView(R.id.scmid)
    TextView scmid;


    @BindView(R.id.checked)
    TextView checked;

    @BindView(R.id.amount)
    TextView amount;

    @BindView(R.id.billdate)
    TextView billdate;

    @Override
    protected void setToolBarTitle() {
        toobarTitle.setText("领料出库(单)");
    }

    @Override
    protected int initResource() {
        return R.layout.acty_gowarehouse2;
    }

    @Override
    protected void reqData() {
        scmid.setText(barcodeStr);
        httpService.getWareHouseMasterAndDetailsByScan(barcodeStr)
                .compose(new CommonTransformer())
                .subscribe(new BaseObserver<BaseEntity<StorageOut2>>(dialog) {
                    @Override
                    protected void onSuccess(BaseEntity<StorageOut2> be) {

                        StorageOut2 storageOut2 = be.getData();
                        StorageOut2.StorageOutMaster master = storageOut2.getMaster();
                        amount.setText(master.getAmount());
                        checked.setText(master.getChecked().getText());
                        billdate.setText(master.getBilldate());

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
        adapter = new WarehouseAdapter(listDatas);
        lv_list.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        lv_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                StroageOutDetail stroageOutDetail = listDatas.get(i);

                Bundle bl = new Bundle();
                bl.putSerializable("StroageOutDetail", stroageOutDetail);
                openActivity(GoWarehouse2DetailActy.class, bl);
            }
        });
    }

    public class WarehouseAdapter extends SuperBaseAdapter<StroageOutDetail> {

        public WarehouseAdapter(List<StroageOutDetail> datas) {
            super(datas);
        }

        @Override
        protected BaseHolder<StroageOutDetail> getHolder() {
            return new WarehouseHod();
        }
    }

    public class WarehouseHod extends BaseHolder<StroageOutDetail> {


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

