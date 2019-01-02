package com.centersoft.entity;

import java.io.Serializable;

/**
 * Created by liudong on 2017/11/2.
 */

public class QcmExaMaster implements Serializable {

    private String arrtime;     //到货时间;////创建检验单时间，
    private String autid;       //自增;//string
    private String billcode;    //单据编号;////不填
    private String billdate;    //创建时间;////当前时间
    private String checked;     //状态;////与细表一致
    private String pubillcode;  //采购订单;//string
    private String puremid;     //采购员;//string
    private String qcemid;      //质检员;//string
    private String remark;      //备注;//string
    private String stbillcode;  //入库单;//string
    private String stemid;      //收货人员;//string
    private String suid;        //供应商;//string

    public String getArrtime() {
        return arrtime;
    }

    public void setArrtime(String arrtime) {
        this.arrtime = arrtime;
    }

    public String getAutid() {
        return autid;
    }

    public void setAutid(String autid) {
        this.autid = autid;
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

    public String getChecked() {
        return checked;
    }

    public void setChecked(String checked) {
        this.checked = checked;
    }

    public String getPubillcode() {
        return pubillcode;
    }

    public void setPubillcode(String pubillcode) {
        this.pubillcode = pubillcode;
    }

    public String getPuremid() {
        return puremid;
    }

    public void setPuremid(String puremid) {
        this.puremid = puremid;
    }

    public String getQcemid() {
        return qcemid;
    }

    public void setQcemid(String qcemid) {
        this.qcemid = qcemid;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getStbillcode() {
        return stbillcode;
    }

    public void setStbillcode(String stbillcode) {
        this.stbillcode = stbillcode;
    }

    public String getStemid() {
        return stemid;
    }

    public void setStemid(String stemid) {
        this.stemid = stemid;
    }

    public String getSuid() {
        return suid;
    }

    public void setSuid(String suid) {
        this.suid = suid;
    }
}
