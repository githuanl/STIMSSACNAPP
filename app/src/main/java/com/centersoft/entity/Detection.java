package com.centersoft.entity;

import com.centersoft.base.BaseEntity;
import com.centersoft.enums.CheckState;

/**
 * Created by liudong on 2017/10/31.
 * 外检
 */

public class Detection extends BaseEntity{

    private double allnums;//收货数;//number
    private String authdate;//批准时间
    private int autoid;//自增变量;//number
    private String auxunit;//单位/辅;//string
    private String batchcode;//批次号;//string
    private String billcode;//检验单单号
    private String billdate;//创建时间;//string
    private CheckState checked;//状态;//string
    private String chkdate;//检验时间
    private String classifies;//物料分类
    private String conclusion;//结论
    private String dcqrcodes;//二维码;//string
    private String degrees;//重要程度
    private double ennums;//合格数
    private String examfiles;//检测依据
    private String exeBillCode;//处置单号
    private String expnums;//实验消耗
    private String incmethod;//整改意见
    private String material;//物料名称;//string
    private String materialid;//物料编号;//string
    private String purautoid;//采购自增
    private String purbillcode;//采购单号
    private String puremid;//采购员
    private String qcemid;//检验员
    private String qrcodes;//规定二维码;//string
    private String remark;//备注
    private String resons;//原因
    private String senums;//待检数
    private String simpara;//外观及包装
    private String simparb;//检测内容A
    private String simparc;//检测内容B
    private String simpard;//检测内容C
    private String specmodel;//规格;//string
    private String sprice;//不含税价;//number
    private String sqebillcode;//seq单号
    private String stemid;//收货员
    private String stimautoid;//入库单自增;//number
    private String stimbillcode;//入库单单号;//string
    private double subnums;//交检数
    private String suid;//供应商;//string
    private String unconformA;//
    private String unconformB;//
    private String unconformC;//
    private String unit;//单位;//string
    private double unnums;//不合格数
    private String workflow;//流程审批
    private String matercomfrom;//PUR 采购类型


    public String getMatercomfrom() {
        return matercomfrom;
    }

    public void setMatercomfrom(String matercomfrom) {
        this.matercomfrom = matercomfrom;
    }

    public double getAllnums() {
        return allnums;
    }

    public void setAllnums(double allnums) {
        this.allnums = allnums;
    }

    public String getAuthdate() {
        return authdate;
    }

    public void setAuthdate(String authdate) {
        this.authdate = authdate;
    }

    public int getAutoid() {
        return autoid;
    }

    public void setAutoid(int autoid) {
        this.autoid = autoid;
    }

    public String getAuxunit() {
        return auxunit;
    }

    public void setAuxunit(String auxunit) {
        this.auxunit = auxunit;
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

    public String getBilldate() {
        return billdate;
    }

    public void setBilldate(String billdate) {
        this.billdate = billdate;
    }

    public CheckState getChecked() {
        return checked;
    }

    public void setChecked(CheckState checked) {
        this.checked = checked;
    }

    public String getChkdate() {
        return chkdate;
    }

    public void setChkdate(String chkdate) {
        this.chkdate = chkdate;
    }

    public String getClassifies() {
        return classifies;
    }

    public void setClassifies(String classifies) {
        this.classifies = classifies;
    }

    public String getConclusion() {
        return conclusion;
    }

    public void setConclusion(String conclusion) {
        this.conclusion = conclusion;
    }

    public String getDcqrcodes() {
        return dcqrcodes;
    }

    public void setDcqrcodes(String dcqrcodes) {
        this.dcqrcodes = dcqrcodes;
    }

    public String getDegrees() {
        return degrees;
    }

    public void setDegrees(String degrees) {
        this.degrees = degrees;
    }

    public double getEnnums() {
        return ennums;
    }

    public void setEnnums(double ennums) {
        this.ennums = ennums;
    }

    public String getExamfiles() {
        return examfiles;
    }

    public void setExamfiles(String examfiles) {
        this.examfiles = examfiles;
    }

    public String getExeBillCode() {
        return exeBillCode;
    }

    public void setExeBillCode(String exeBillCode) {
        this.exeBillCode = exeBillCode;
    }

    public String getExpnums() {
        return expnums;
    }

    public void setExpnums(String expnums) {
        this.expnums = expnums;
    }

    public String getIncmethod() {
        return incmethod;
    }

    public void setIncmethod(String incmethod) {
        this.incmethod = incmethod;
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

    public String getPurautoid() {
        return purautoid;
    }

    public void setPurautoid(String purautoid) {
        this.purautoid = purautoid;
    }

    public String getPurbillcode() {
        return purbillcode;
    }

    public void setPurbillcode(String purbillcode) {
        this.purbillcode = purbillcode;
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

    public String getQrcodes() {
        return qrcodes;
    }

    public void setQrcodes(String qrcodes) {
        this.qrcodes = qrcodes;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getResons() {
        return resons;
    }

    public void setResons(String resons) {
        this.resons = resons;
    }

    public String getSenums() {
        return senums;
    }

    public void setSenums(String senums) {
        this.senums = senums;
    }

    public String getSimpara() {
        return simpara;
    }

    public void setSimpara(String simpara) {
        this.simpara = simpara;
    }

    public String getSimparb() {
        return simparb;
    }

    public void setSimparb(String simparb) {
        this.simparb = simparb;
    }

    public String getSimparc() {
        return simparc;
    }

    public void setSimparc(String simparc) {
        this.simparc = simparc;
    }

    public String getSimpard() {
        return simpard;
    }

    public void setSimpard(String simpard) {
        this.simpard = simpard;
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

    public String getSqebillcode() {
        return sqebillcode;
    }

    public void setSqebillcode(String sqebillcode) {
        this.sqebillcode = sqebillcode;
    }

    public String getStemid() {
        return stemid;
    }

    public void setStemid(String stemid) {
        this.stemid = stemid;
    }

    public String getStimautoid() {
        return stimautoid;
    }

    public void setStimautoid(String stimautoid) {
        this.stimautoid = stimautoid;
    }

    public String getStimbillcode() {
        return stimbillcode;
    }

    public void setStimbillcode(String stimbillcode) {
        this.stimbillcode = stimbillcode;
    }

    public double getSubnums() {
        return subnums;
    }

    public void setSubnums(double subnums) {
        this.subnums = subnums;
    }

    public String getSuid() {
        return suid;
    }

    public void setSuid(String suid) {
        this.suid = suid;
    }

    public String getUnconformA() {
        return unconformA;
    }

    public void setUnconformA(String unconformA) {
        this.unconformA = unconformA;
    }

    public String getUnconformB() {
        return unconformB;
    }

    public void setUnconformB(String unconformB) {
        this.unconformB = unconformB;
    }

    public String getUnconformC() {
        return unconformC;
    }

    public void setUnconformC(String unconformC) {
        this.unconformC = unconformC;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public double getUnnums() {
        return unnums;
    }

    public void setUnnums(double unnums) {
        this.unnums = unnums;
    }

    public String getWorkflow() {
        return workflow;
    }

    public void setWorkflow(String workflow) {
        this.workflow = workflow;
    }
}
