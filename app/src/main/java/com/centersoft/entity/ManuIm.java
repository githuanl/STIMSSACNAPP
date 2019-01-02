package com.centersoft.entity;

import com.centersoft.base.BaseEntity;

/**
 * Created by liudong on 2017/12/2.
 */
// 成品 入库
public class ManuIm extends BaseEntity{


    /**
     * emptyIdentifier : 81362
     * autoid : 357
     * auxUnit : 1
     * auxnums : 1
     * batchcode : 65416415100000005
     * material : 凸轮轴
     * materialid : 5102299
     * moveNums : 200
     * moveTime : 2017-12-02 20:07:04
     * movesort : complete
     * rate : 1
     * recedept : 00801
     * remark : 1
     * specmodel : 24106547
     * unit : 件
     */

    private String emptyIdentifier;
    private int autoid;
    private String auxUnit;
    private String auxnums;
    private String batchcode;
    private String material;
    private String materialid;
    private String moveNums;
    private String moveTime;
    private String movesort;
    private String rate;
    private String recedept;    // 库房 id
    private String remark;
    private String specmodel;
    private String unit;




    public String getEmptyIdentifier() {
        return emptyIdentifier;
    }

    public void setEmptyIdentifier(String emptyIdentifier) {
        this.emptyIdentifier = emptyIdentifier;
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

    public String getAuxnums() {
        return auxnums;
    }

    public void setAuxnums(String auxnums) {
        this.auxnums = auxnums;
    }

    public String getBatchcode() {
        return batchcode;
    }

    public void setBatchcode(String batchcode) {
        this.batchcode = batchcode;
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

    public String getMoveNums() {
        return moveNums;
    }

    public void setMoveNums(String moveNums) {
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

    public String getRecedept() {
        return recedept;
    }

    public void setRecedept(String recedept) {
        this.recedept = recedept;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getSpecmodel() {
        return specmodel;
    }

    public void setSpecmodel(String specmodel) {
        this.specmodel = specmodel;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }
}
