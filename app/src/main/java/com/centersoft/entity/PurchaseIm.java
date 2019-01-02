package com.centersoft.entity;

import com.centersoft.base.BaseEntity;

/**
 * Created by liudong on 2017/12/4.
 */

//扫描入库
public class PurchaseIm extends BaseEntity {


//
//    batchnum		string	批次号
//    lnum		string	炉号
//    material		string	物料名称
//    materialcode	不显示	string	物料类型
//    materialid		string	物料编码
//    num		string	数量
//    productDate		string	生产日期
//    specmodel		string	规格型号
//    suid	保存字段	string	供应商 id
//    suidcode	不显示	string	供应商类型
//    supplyname		string	供应商名称


    private String batchnum;
    private String lnum;
    private String material;
    private String materialcode;
    private String materialid;
    private String num;
    private String productDate;
    private String specmodel;
    private String suid;
    private String suidcode;
    private String supplyname;

    public String getBatchnum() {
        return batchnum;
    }

    public void setBatchnum(String batchnum) {
        this.batchnum = batchnum;
    }

    public String getLnum() {
        return lnum;
    }

    public void setLnum(String lnum) {
        this.lnum = lnum;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public String getMaterialcode() {
        return materialcode;
    }

    public void setMaterialcode(String materialcode) {
        this.materialcode = materialcode;
    }

    public String getMaterialid() {
        return materialid;
    }

    public void setMaterialid(String materialid) {
        this.materialid = materialid;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getProductDate() {
        return productDate;
    }

    public void setProductDate(String productDate) {
        this.productDate = productDate;
    }

    public String getSpecmodel() {
        return specmodel;
    }

    public void setSpecmodel(String specmodel) {
        this.specmodel = specmodel;
    }

    public String getSuid() {
        return suid;
    }

    public void setSuid(String suid) {
        this.suid = suid;
    }

    public String getSuidcode() {
        return suidcode;
    }

    public void setSuidcode(String suidcode) {
        this.suidcode = suidcode;
    }

    public String getSupplyname() {
        return supplyname;
    }

    public void setSupplyname(String supplyname) {
        this.supplyname = supplyname;
    }


}
