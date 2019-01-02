package com.centersoft.view;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.centersoft.adapter.SuperBaseAdapter;
import com.centersoft.base.BaseActivity;
import com.centersoft.base.BaseHolder;
import com.centersoft.entity.CashBaseData;
import com.centersoft.enums.CashType;
import com.centersoft.enums.SelectType;
import com.centersoft.stimsscanapp.R;
import com.centersoft.transformer.CommonTransformer;
import com.centersoft.utils.NetCashUtil;
import com.centersoft.utils.NetUtil;
import com.jakewharton.rxbinding2.widget.RxTextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.functions.Consumer;

import static java.util.concurrent.TimeUnit.MILLISECONDS;

//选择
public class SelectDataActy extends BaseActivity<CashBaseData> {

    Bundle bundle;
    SelectType selectType;

    @BindView(R.id.lv_list)
    ListView lv_list;

    @BindView(R.id.ed_search)
    EditText ed_search;

    @Override
    protected void setToolBarTitle() {
        bundle = getIntent().getExtras();
        selectType = (SelectType) bundle.getSerializable("SelectType");
        toobarTitle.setText("选择" + selectType.getName());
    }

    SelectAdapter adapter;


    List<CashBaseData> cashBaseDataList = new ArrayList<>();

    void refListData(List<CashBaseData> list) {
        listDatas.clear();
        listDatas.addAll(list);
        cashBaseDataList.clear();
        cashBaseDataList.addAll(listDatas);
        adapter.notifyDataSetChanged();
    }

    @Override
    protected void initData() {
        adapter = new SelectAdapter(listDatas);
        lv_list.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        switch (selectType) {
            case Customer:
                NetCashUtil.getBaseCash(CashType.Customer, CashType.Customer.toString(), new NetCashUtil.CallBack<String>() {
                    @Override
                    public void success(String result) {
                        refListData(NetCashUtil.c_List_Map.get(CashType.Customer.toString()));
                    }
                });
                break;
            case Department:
                NetCashUtil.getBaseCash(CashType.Department, CashType.Department.toString(), new NetCashUtil.CallBack<String>() {
                    @Override
                    public void success(String result) {
                        refListData(NetCashUtil.c_List_Map.get(CashType.Department.toString()));
                    }
                });
                break;
            case StorageCp:     //成品 库
                NetCashUtil.getStoresCash(CashType.StorageCp, "-1", new NetCashUtil.CallBack<List<CashBaseData>>() {
                    @Override
                    public void success(List<CashBaseData> result) {
                        refListData(result);
                    }
                });
                break;
            case Inspector:     //  检验员
                NetCashUtil.getInspector(CashType.Inspector, "-1", new NetCashUtil.CallBack<List<CashBaseData>>() {
                    @Override
                    public void success(List<CashBaseData> list) {
                        refListData(list);
                    }
                });
                break;
            case StorageCg:     //  采购库
                NetCashUtil.getAllManuDeptByPur(CashType.StorageCg, "-1", new NetCashUtil.CallBack<List<CashBaseData>>() {
                    @Override
                    public void success(List<CashBaseData> list) {
                        refListData(list);
                    }
                });
                break;
        }


        lv_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                CashBaseData cashBaseData = listDatas.get(i);
                Intent intent = new Intent();
                intent.putExtra("data", JSON.toJSONString(cashBaseData));
                setResult(selectType.getCode(), intent);
                goBack();
            }
        });


        RxTextView.textChanges(ed_search)
                .debounce(350, MILLISECONDS)
                .compose(new CommonTransformer())
                .subscribe(new Consumer<CharSequence>() {
                    @Override
                    public void accept(CharSequence charSequence) throws Exception {
                        if (cashBaseDataList.size() == 0) {
                            return;
                        }
                        listDatas.clear();
                        if (TextUtils.isEmpty(charSequence.toString())) {
                            listDatas.addAll(cashBaseDataList);
                        } else {
                            List<CashBaseData> tmpList = new ArrayList<>();
                            for (CashBaseData cashBaseData : cashBaseDataList) {
                                switch (selectType) {
                                    case Customer:
                                        if (cashBaseData.getCustname().contains(charSequence.toString())) {
                                            tmpList.add(cashBaseData);
                                        }
                                        break;
                                    case Department:
                                        if (cashBaseData.getDepartment().contains(charSequence.toString())) {
                                            tmpList.add(cashBaseData);
                                        }
                                        break;
                                    case Inspector:
                                        if (cashBaseData.getPsnname().contains(charSequence.toString())) {
                                            tmpList.add(cashBaseData);
                                        }
                                        break;
                                    case StorageCp:
                                    case StorageCg:
                                    case Storage:
                                        if (cashBaseData.getManudept().contains(charSequence.toString())) {
                                            tmpList.add(cashBaseData);
                                        }
                                        break;
                                }
                            }
                            listDatas.addAll(tmpList);
                        }
                        adapter.notifyDataSetChanged();
                    }
                });
    }

    public class SelectAdapter extends SuperBaseAdapter<CashBaseData> {


        public SelectAdapter(List<CashBaseData> datas) {
            super(datas);
        }

        @Override
        protected BaseHolder<CashBaseData> getHolder() {
            return new SelectHolder();
        }
    }

    public class SelectHolder extends BaseHolder<CashBaseData> {


        @BindView(R.id.tv_name)
        TextView tv_name;

        @Override
        protected View initView() {
            View v = View.inflate(context, R.layout.acty_select_data_list_item, null);
            ButterKnife.bind(this, v);
            return v;
        }

        @Override
        protected void refreshUI(CashBaseData data) {
            switch (selectType) {
                case Customer:
                    tv_name.setText(data.getCustname());
                    break;
                case Department:
                    tv_name.setText(data.getDepartment());
                    break;
                case Inspector:
                    tv_name.setText(data.getPsnname());
                    break;
                case StorageCp:
                case StorageCg:
                case Storage:
                    tv_name.setText(data.getManudept());
                    break;
            }
        }
    }

    @Override
    protected int initResource() {
        return R.layout.acty_select_data;
    }
}
