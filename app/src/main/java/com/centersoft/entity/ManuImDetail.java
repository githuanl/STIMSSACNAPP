package com.centersoft.entity;

import com.centersoft.base.BaseEntity;
import com.centersoft.enums.ManuImDetailCheckedEnum;

import java.util.List;

/**
 * 生产缴库单明细
 *
 * @author huangluping
 * @Date Created in 2017/7/28
 */
public class ManuImDetail extends BaseEntity {

    private String assemflag;//string
    private int autoid;//缴库单序号;//number
    private String auxUnit;//单位/辅;//string
    private double badNums;//number
    private String barcode;//string
    private String batchcode;//批次号;//string
    private String billcode;//单据编号;//string
    private String checkImNums;//
    private ManuImDetailCheckedEnum checked;//状态;//string
    private String comefrom;//string
    private double goodNums;//number
    private String manuCosts;//
    private String manuEmid;//string
    private String manubillcode;//string
    private String manudept;//string
    private String material;//物料名称;//string
    private String materialCosts;//
    private String materialid;//物料编号;//string
    private double moveNums;//缴库数;//number
    private String moveTime;//缴库时间;//string
    private String movesort;//缴库类型;//string
    private String rate;//转换率;//number
    private String receEmid;//接收人;//string
    private String receNums;//收货数;//number
    private String recedept;//接收部门;//string
    private String receiveTime;//接收时间;//string
    private String remark;//备注;//string
    private String repairNums;//
    private String scqu;//期间;//string
    private String specmodel;//规格型号;//string
    private String sprice;//
    private String stMatchBillid;//string
    private String startNums;//number
    private String suid;//供应商;//string
    private String unit;//单位;//string


    public String getAssemflag() {
        return assemflag;
    }

    public void setAssemflag(String assemflag) {
        this.assemflag = assemflag;
    }

    public int getAutoid() {
        return autoid;
    }

    public void setAutoid(int autoid) {
        this.autoid = autoid;
    }

    public String getAuxUnit() {
        return auxUnit;
    }

    public void setAuxUnit(String auxUnit) {
        this.auxUnit = auxUnit;
    }

    public double getBadNums() {
        return badNums;
    }

    public void setBadNums(double badNums) {
        this.badNums = badNums;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public String getBatchcode() {
        return batchcode;
    }

    public void setBatchcode(String batchcode) {
        this.batchcode = batchcode;
    }

    public String getBillcode() {
        return billcode;
    }

    public void setBillcode(String billcode) {
        this.billcode = billcode;
    }

    public String getCheckImNums() {
        return checkImNums;
    }

    public void setCheckImNums(String checkImNums) {
        this.checkImNums = checkImNums;
    }

    public ManuImDetailCheckedEnum getChecked() {
        return checked;
    }

    public void setChecked(ManuImDetailCheckedEnum checked) {
        this.checked = checked;
    }

    public String getComefrom() {
        return comefrom;
    }

    public void setComefrom(String comefrom) {
        this.comefrom = comefrom;
    }

    public double getGoodNums() {
        return goodNums;
    }

    public void setGoodNums(double goodNums) {
        this.goodNums = goodNums;
    }

    public String getManuCosts() {
        return manuCosts;
    }

    public void setManuCosts(String manuCosts) {
        this.manuCosts = manuCosts;
    }

    public String getManuEmid() {
        return manuEmid;
    }

    public void setManuEmid(String manuEmid) {
        this.manuEmid = manuEmid;
    }

    public String getManubillcode() {
        return manubillcode;
    }

    public void setManubillcode(String manubillcode) {
        this.manubillcode = manubillcode;
    }

    public String getManudept() {
        return manudept;
    }

    public void setManudept(String manudept) {
        this.manudept = manudept;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public String getMaterialCosts() {
        return materialCosts;
    }

    public void setMaterialCosts(String materialCosts) {
        this.materialCosts = materialCosts;
    }

    public String getMaterialid() {
        return materialid;
    }

    public void setMaterialid(String materialid) {
        this.materialid = materialid;
    }

    public double getMoveNums() {
        return moveNums;
    }

    public void setMoveNums(double moveNums) {
        this.moveNums = moveNums;
    }

    public String getMoveTime() {
        return moveTime;
    }

    public void setMoveTime(String moveTime) {
        this.moveTime = moveTime;
    }

    public String getMovesort() {
        return movesort;
    }

    public void setMovesort(String movesort) {
        this.movesort = movesort;
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }

    public String getReceEmid() {
        return receEmid;
    }

    public void setReceEmid(String receEmid) {
        this.receEmid = receEmid;
    }

    public String getReceNums() {
        return receNums;
    }

    public void setReceNums(String receNums) {
        this.receNums = receNums;
    }

    public String getRecedept() {
        return recedept;
    }

    public void setRecedept(String recedept) {
        this.recedept = recedept;
    }

    public String getReceiveTime() {
        return receiveTime;
    }

    public void setReceiveTime(String receiveTime) {
        this.receiveTime = receiveTime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getRepairNums() {
        return repairNums;
    }

    public void setRepairNums(String repairNums) {
        this.repairNums = repairNums;
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

    public String getSprice() {
        return sprice;
    }

    public void setSprice(String sprice) {
        this.sprice = sprice;
    }

    public String getStMatchBillid() {
        return stMatchBillid;
    }

    public void setStMatchBillid(String stMatchBillid) {
        this.stMatchBillid = stMatchBillid;
    }

    public String getStartNums() {
        return startNums;
    }

    public void setStartNums(String startNums) {
        this.startNums = startNums;
    }

    public String getSuid() {
        return suid;
    }

    public void setSuid(String suid) {
        this.suid = suid;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }
}
