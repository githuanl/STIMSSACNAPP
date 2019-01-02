package com.centersoft.view;

import com.centersoft.base.BaseActivity;
import com.centersoft.stimsscanapp.R;

public class ProcessInspectionActy extends BaseActivity {


	@Override
	protected void setToolBarTitle() {
		toobarTitle.setText("工序检验");
	}

	@Override
	protected int initResource() {
		return R.layout.acty_process_inspection;
	}
}
