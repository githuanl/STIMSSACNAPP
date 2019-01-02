package com.centersoft.entity;

import com.centersoft.base.BaseEntity;
import com.centersoft.enums.SaleInvoiceDetailCheckedEnum;

/**
 * Created by liudong on 2017/10/27.
 */

public class StroageOutDetail extends BaseEntity {


    private String arriveDate;  //	签收时间	string
    private int autoid;      //序号	number
    private double auxnums;     //数量/辅	number
    private String auxunit;     //单位/辅	string
    private String batchnum;    //批次号	string
    private String billcode;    //单据编号	string
    private String billdate;    //制单时间	string
    private String billemid;    //经手人	string
    private String billsort;    //单据类型	string
    private SaleInvoiceDetailCheckedEnum checked;     //状态	string
    private String comfrom;     //数据来源	string
    private String custid;      //客户	string
    private String dcdate;      //
    private String define1;     //string
    private String define2;     //string
    private String define3;     //string
    private String define4;     //string
    private String define5;     //string
    private String defnum1;     //number
    private String defnum2;     //number
    private String defnum3;     //number
    private String defnum4;     //number
    private String defnum5;     //number
    private double excnums;     //执行数	number
    private String manudept;    //部门	string
    private String manuemid;    //员工	string
    private String material;    //物料名称	string
    private String materialid;  //物料编码	string
    private String nums;        //数量	number
    private String numsdc;      //实发数	number
    private double numsdcas;    //实发数/辅	number
    private String planDate;    //需求时间	string
    private double planums;     //计划数	number
    private double price;       //单价	number
    private String projectcode; //		string
    private double rate;        //	转换率/包装	number
    private String recedate;    //
    private String receemid;    //	string
    private String receipt;     //	string
    private String remark;      //	string
    private String renums;      //
    private String reprice;     //
    private String resprice;    //
    private String retax;       //
    private String scancode;    //string
    private String scqu;        //期间	string
    private String specmodel;   //规格型号	string
    private double sprice;      //不含税价
    private String stemid;      //	string
    private String stid;        //库房	string
    private double stnums;      //	库存数	number
    private String stxh;        //string
    private double subnums;     //	占用数	number
    private String tax;         //税率
    private String uinitcub;    //	单位体积
    private String unit;        //单位	string
    private String unitwgt;     //	单位重量


    public String getArriveDate() {
        return arriveDate;
    }

    public void setArriveDate(String arriveDate) {
        this.arriveDate = arriveDate;
    }

    public int getAutoid() {
        return autoid;
    }

    public void setAutoid(int autoid) {
        this.autoid = autoid;
    }

    public double getAuxnums() {
        return auxnums;
    }

    public void setAuxnums(double auxnums) {
        this.auxnums = auxnums;
    }

    public String getAuxunit() {
        return auxunit;
    }

    public void setAuxunit(String auxunit) {
        this.auxunit = auxunit;
    }

    public String getBatchnum() {
        return batchnum;
    }

    public void setBatchnum(String batchnum) {
        this.batchnum = batchnum;
    }

    public String getBillcode() {
        return billcode;
    }

    public void setBillcode(String billcode) {
        this.billcode = billcode;
    }

    public String getBilldate() {
        return billdate;
    }

    public void setBilldate(String billdate) {
        this.billdate = billdate;
    }

    public String getBillemid() {
        return billemid;
    }

    public void setBillemid(String billemid) {
        this.billemid = billemid;
    }

    public String getBillsort() {
        return billsort;
    }

    public void setBillsort(String billsort) {
        this.billsort = billsort;
    }

    public SaleInvoiceDetailCheckedEnum getChecked() {
        return checked;
    }

    public void setChecked(SaleInvoiceDetailCheckedEnum checked) {
        this.checked = checked;
    }

    public String getComfrom() {
        return comfrom;
    }

    public void setComfrom(String comfrom) {
        this.comfrom = comfrom;
    }

    public String getCustid() {
        return custid;
    }

    public void setCustid(String custid) {
        this.custid = custid;
    }

    public String getDcdate() {
        return dcdate;
    }

    public void setDcdate(String dcdate) {
        this.dcdate = dcdate;
    }

    public String getDefine1() {
        return define1;
    }

    public void setDefine1(String define1) {
        this.define1 = define1;
    }

    public String getDefine2() {
        return define2;
    }

    public void setDefine2(String define2) {
        this.define2 = define2;
    }

    public String getDefine3() {
        return define3;
    }

    public void setDefine3(String define3) {
        this.define3 = define3;
    }

    public String getDefine4() {
        return define4;
    }

    public void setDefine4(String define4) {
        this.define4 = define4;
    }

    public String getDefine5() {
        return define5;
    }

    public void setDefine5(String define5) {
        this.define5 = define5;
    }

    public String getDefnum1() {
        return defnum1;
    }

    public void setDefnum1(String defnum1) {
        this.defnum1 = defnum1;
    }

    public String getDefnum2() {
        return defnum2;
    }

    public void setDefnum2(String defnum2) {
        this.defnum2 = defnum2;
    }

    public String getDefnum3() {
        return defnum3;
    }

    public void setDefnum3(String defnum3) {
        this.defnum3 = defnum3;
    }

    public String getDefnum4() {
        return defnum4;
    }

    public void setDefnum4(String defnum4) {
        this.defnum4 = defnum4;
    }

    public String getDefnum5() {
        return defnum5;
    }

    public void setDefnum5(String defnum5) {
        this.defnum5 = defnum5;
    }

    public double getExcnums() {
        return excnums;
    }

    public void setExcnums(double excnums) {
        this.excnums = excnums;
    }

    public String getManudept() {
        return manudept;
    }

    public void setManudept(String manudept) {
        this.manudept = manudept;
    }

    public String getManuemid() {
        return manuemid;
    }

    public void setManuemid(String manuemid) {
        this.manuemid = manuemid;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public String getMaterialid() {
        return materialid;
    }

    public void setMaterialid(String materialid) {
        this.materialid = materialid;
    }

    public String getNums() {
        return nums;
    }

    public void setNums(String nums) {
        this.nums = nums;
    }

    public String getNumsdc() {
        return numsdc;
    }

    public void setNumsdc(String numsdc) {
        this.numsdc = numsdc;
    }

    public double getNumsdcas() {
        return numsdcas;
    }

    public void setNumsdcas(double numsdcas) {
        this.numsdcas = numsdcas;
    }

    public String getPlanDate() {
        return planDate;
    }

    public void setPlanDate(String planDate) {
        this.planDate = planDate;
    }

    public double getPlanums() {
        return planums;
    }

    public void setPlanums(double planums) {
        this.planums = planums;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getProjectcode() {
        return projectcode;
    }

    public void setProjectcode(String projectcode) {
        this.projectcode = projectcode;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

    public String getRecedate() {
        return recedate;
    }

    public void setRecedate(String recedate) {
        this.recedate = recedate;
    }

    public String getReceemid() {
        return receemid;
    }

    public void setReceemid(String receemid) {
        this.receemid = receemid;
    }

    public String getReceipt() {
        return receipt;
    }

    public void setReceipt(String receipt) {
        this.receipt = receipt;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getRenums() {
        return renums;
    }

    public void setRenums(String renums) {
        this.renums = renums;
    }

    public String getReprice() {
        return reprice;
    }

    public void setReprice(String reprice) {
        this.reprice = reprice;
    }

    public String getResprice() {
        return resprice;
    }

    public void setResprice(String resprice) {
        this.resprice = resprice;
    }

    public String getRetax() {
        return retax;
    }

    public void setRetax(String retax) {
        this.retax = retax;
    }

    public String getScancode() {
        return scancode;
    }

    public void setScancode(String scancode) {
        this.scancode = scancode;
    }

    public String getScqu() {
        return scqu;
    }

    public void setScqu(String scqu) {
        this.scqu = scqu;
    }

    public String getSpecmodel() {
        return specmodel;
    }

    public void setSpecmodel(String specmodel) {
        this.specmodel = specmodel;
    }

    public double getSprice() {
        return sprice;
    }

    public void setSprice(double sprice) {
        this.sprice = sprice;
    }

    public String getStemid() {
        return stemid;
    }

    public void setStemid(String stemid) {
        this.stemid = stemid;
    }

    public String getStid() {
        return stid;
    }

    public void setStid(String stid) {
        this.stid = stid;
    }

    public double getStnums() {
        return stnums;
    }

    public void setStnums(double stnums) {
        this.stnums = stnums;
    }

    public String getStxh() {
        return stxh;
    }

    public void setStxh(String stxh) {
        this.stxh = stxh;
    }

    public double getSubnums() {
        return subnums;
    }

    public void setSubnums(double subnums) {
        this.subnums = subnums;
    }

    public String getTax() {
        return tax;
    }

    public void setTax(String tax) {
        this.tax = tax;
    }

    public String getUinitcub() {
        return uinitcub;
    }

    public void setUinitcub(String uinitcub) {
        this.uinitcub = uinitcub;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getUnitwgt() {
        return unitwgt;
    }

    public void setUnitwgt(String unitwgt) {
        this.unitwgt = unitwgt;
    }
}
