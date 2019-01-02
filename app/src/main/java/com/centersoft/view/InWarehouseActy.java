package com.centersoft.view;

import com.centersoft.base.BaseActivity;
import com.centersoft.stimsscanapp.R;

public class InWarehouseActy extends BaseActivity {


//	@ViewById // 条码	状态		转移单号	产品编号	 产品名称	发货单位	 转移数量	  接受数量	接收单位	 转移时间   入库时间
//            TextView scmid,tv_checked,sbid,  souceid, ljmcc,measure,manudept,movenum, recenum, recedept, movetime,receivetime;

    @Override
    protected void setToolBarTitle() {
        toobarTitle.setText("生产入库");
    }

    @Override
    protected int initResource() {
        return R.layout.acty_inwarhouse;
    }


}
