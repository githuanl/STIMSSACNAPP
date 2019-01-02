package com.centersoft.entity;

import java.io.Serializable;

/**
 * Created by liudong on 2017/11/28.
 */

public class StorageKw implements Serializable{

    /*原料库房:PUR,第三方代管库:RDC,半成品库:BQM,外协库房:OEM,制造部门:MANU,成品仓库:SD,来料加工:TEM,不良品库:BAD*/
//    PUR("原料库房"),
//    RDC("半成品库"),
//    BQM("半成品库"),
//    OEM("外协库房"),
//    MANU("制造部门"),
//    SD("成品仓库"),
//    TEM("来料加工"),
//    BAD("不良品库");

    //{"id":"00801","sort":"SD","store":"成品库"}
    private String id;//
    private String sort;//
    private String store;//

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public String getStore() {
        return store;
    }

    public void setStore(String store) {
        this.store = store;
    }
}
