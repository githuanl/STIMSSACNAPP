package com.centersoft.view;

import com.centersoft.base.BaseActivity;
import com.centersoft.stimsscanapp.R;

public class GoWarehouseDetailsActy extends BaseActivity {


	@Override
	protected void setToolBarTitle() {
		toobarTitle.setText("出库明细");
	}

	@Override
	protected int initResource() {
		return R.layout.acty_gowarhousedetails;
	}
}
