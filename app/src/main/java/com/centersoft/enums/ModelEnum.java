package com.centersoft.enums;

import com.centersoft.stimsscanapp.R;
import com.centersoft.view.*;

/**
 * Created by liudong on 2017/10/31.
 */

public enum ModelEnum {
    ScanShouHuo("扫描收货", R.drawable.scan_sh, ScanShouhuoActy.class, ""),
    OutsourcingReceived("外购收货", R.drawable.wx, WgShouHuoActy.class, ""),
    Detection("外检", R.drawable.wj, DetectionActy.class, "purMaterial"),//外检
    OutsourcingStorageIn("外购入库", R.drawable.wgrk, WgStorageInActy.class, "purchasingStorage"),
    CpStorageIn("车间缴库", R.drawable.gcjy_03, CjStorageJkActy.class, ""),
    CpStorageInbf("成品入库", R.drawable.scrk, CpStorageInActy.class, "manuImDetail"),
    GoWarehouse("领料出库", R.drawable.llck, GoWarehouseActy.class, "manuRequireDeliver"),
    GoWarehouse2("领料出库(单)", R.drawable.llck, GoWarehouse2Acty.class, "manuRequireDeliver"),
//    ProcessInspection("工序检验", R.drawable.gcjy, ProcessInspectionActy.class),
//    CallLibrary("调库", R.drawable.wx, CallLibraryActy.class),
//    UnqualifiedInspection("返修品检验", R.drawable.fp, InWarehouseActy.class),
//    InWarehouse("生产入库", R.drawable.scrk, InWarehouseActy.class),
    SaleStorageOut("销售出库", R.drawable.xsck, SaleStorageActy.class, ""),
    SaleStorageOut2("销售出库(单)", R.drawable.xsck, SaleStorageOut2Acty.class, "");


    private String name;
    private int drawble;
    private Class mClass;
    private String msgType;

    public String getName() {
        return name;
    }

    public int getDrawble() {
        return drawble;
    }

    public Class getmClass() {
        return mClass;
    }

    public String getMsgType() {
        return msgType;
    }

    ModelEnum(String name, int dw, Class cla, String msgType) {
        this.name = name;
        this.drawble = dw;
        this.mClass = cla;
        this.msgType = msgType;
    }
}
