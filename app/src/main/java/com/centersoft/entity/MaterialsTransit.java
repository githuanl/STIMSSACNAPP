package com.centersoft.entity;

import com.centersoft.base.BaseEntity;
import com.centersoft.enums.MaterialState;

/**
 * Created by liudong on 2017/11/7.
 */

//在途物资
public class MaterialsTransit extends BaseEntity {

    private int autoid;//number
    private String auxunit;//单位/辅
    private String banchnum;//批次号
    private String billdate;//扫描时间
    private MaterialState checked;//状态 ：create 创建，save 保存，checking 检验中
    private String comefrom;//string
    private String custautoid;//订货自增
    private String custccid;//	客户协同
    private String custorderid;//订货单号
    private String dcmaterdate;//送货时间
    private String dcqrcodes;//送货单二维码
    private String define1;//
    private String define10;//   ;//
    private String define2;//
    private String define3;//  ;//
    private String define4;//
    private String define5;//
    private String define6;//
    private String define7;//
    private String define8;//
    private String define9;//
    private String defnum1;//
    private String defnum2;//
    private String defnum3;//
    private String defnum4;//
    private String defnum5;//
    private String material;//物料名称
    private String materialid;//物料编号
    private double numauxs;//数量/辅
    private double nums;//数量
    private String qrcodes;//二维码
    private String rate;//转化率
    private String recedate;//
    private String recemid;//
    private String remark;//
    private String resume;//
    private String specmodel;//规格型号
    private String stemid;//	库管
    private String suid;//供应商
    private String sustccid;//供应商协同
    private String unit;//单位
    private String unwayautoid;//送货单自增
    private String unwaybillcode;//送货单
    private String teemid;  //检验员
    private String stid;    //库房
    private String stbin;   //

    public String getStbin() {
        return stbin;
    }

    public void setStbin(String stbin) {
        this.stbin = stbin;
    }

    public String getStid() {
        return stid;
    }

    public void setStid(String stid) {
        this.stid = stid;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getUnwayautoid() {
        return unwayautoid;
    }

    public void setUnwayautoid(String unwayautoid) {
        this.unwayautoid = unwayautoid;
    }

    public String getUnwaybillcode() {
        return unwaybillcode;
    }

    public void setUnwaybillcode(String unwaybillcode) {
        this.unwaybillcode = unwaybillcode;
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

    public String getBanchnum() {
        return banchnum;
    }

    public void setBanchnum(String banchnum) {
        this.banchnum = banchnum;
    }

    public String getBilldate() {
        return billdate;
    }

    public void setBilldate(String billdate) {
        this.billdate = billdate;
    }

    public MaterialState getChecked() {
        return checked;
    }

    public void setChecked(MaterialState checked) {
        this.checked = checked;
    }

    public String getComefrom() {
        return comefrom;
    }

    public void setComefrom(String comefrom) {
        this.comefrom = comefrom;
    }

    public String getCustautoid() {
        return custautoid;
    }

    public void setCustautoid(String custautoid) {
        this.custautoid = custautoid;
    }

    public String getCustccid() {
        return custccid;
    }

    public void setCustccid(String custccid) {
        this.custccid = custccid;
    }

    public String getCustorderid() {
        return custorderid;
    }

    public void setCustorderid(String custorderid) {
        this.custorderid = custorderid;
    }

    public String getDcmaterdate() {
        return dcmaterdate;
    }

    public void setDcmaterdate(String dcmaterdate) {
        this.dcmaterdate = dcmaterdate;
    }

    public String getDcqrcodes() {
        return dcqrcodes;
    }

    public void setDcqrcodes(String dcqrcodes) {
        this.dcqrcodes = dcqrcodes;
    }

    public String getDefine1() {
        return define1;
    }

    public void setDefine1(String define1) {
        this.define1 = define1;
    }

    public String getDefine10() {
        return define10;
    }

    public void setDefine10(String define10) {
        this.define10 = define10;
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

    public String getDefine6() {
        return define6;
    }

    public void setDefine6(String define6) {
        this.define6 = define6;
    }

    public String getDefine7() {
        return define7;
    }

    public void setDefine7(String define7) {
        this.define7 = define7;
    }

    public String getDefine8() {
        return define8;
    }

    public void setDefine8(String define8) {
        this.define8 = define8;
    }

    public String getDefine9() {
        return define9;
    }

    public void setDefine9(String define9) {
        this.define9 = define9;
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

    public double getNumauxs() {
        return numauxs;
    }

    public void setNumauxs(double numauxs) {
        this.numauxs = numauxs;
    }

    public double getNums() {
        return nums;
    }

    public void setNums(double nums) {
        this.nums = nums;
    }

    public String getQrcodes() {
        return qrcodes;
    }

    public void setQrcodes(String qrcodes) {
        this.qrcodes = qrcodes;
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }

    public String getRecedate() {
        return recedate;
    }

    public void setRecedate(String recedate) {
        this.recedate = recedate;
    }

    public String getRecemid() {
        return recemid;
    }

    public void setRecemid(String recemid) {
        this.recemid = recemid;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getResume() {
        return resume;
    }

    public void setResume(String resume) {
        this.resume = resume;
    }

    public String getSpecmodel() {
        return specmodel;
    }

    public void setSpecmodel(String specmodel) {
        this.specmodel = specmodel;
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

    public String getSustccid() {
        return sustccid;
    }

    public void setSustccid(String sustccid) {
        this.sustccid = sustccid;
    }

    public String getTeemid() {
        return teemid;
    }

    public void setTeemid(String teemid) {
        this.teemid = teemid;
    }
}
