package com.centersoft.entity;

import com.centersoft.base.BaseEntity;
import com.centersoft.enums.CheckState;

/**
 * Created by liudong on 2017/11/3.
 */

public class StorageIn extends BaseEntity {

    private String arrtime;//到货时间
    private String auxnums;//数量/辅
    private String auxunit;//单位/辅
    private String banchnum;//批次号
    private String barautoid;//条码
    private String barcode;//条码
    private String billcode;//单号

    private String bindnum;//捆绑号 状态
    private String dcqrcodes;// 发货条形码
    private String define1;//
    private String define2;//
    private String define3;//
    private String define4;//
    private String define5;//
    private String define6;//
    private String furtnum;//
    private String invnumber;//
    private String material;//   //物料名称
    private String materialid;// 物料ID
    private String nums;//数量
    private CheckState checked;//string	状态
    private String ocunums;//占用数
    private String outnums;//出库数
    private String paynumber;//支付金额
    private String price;//		number	含税价
    private String purautoid;//	number	采购单 自增
    private String purbillcode;//		string	采购单 单号
    private String qrcodes;//	string	二维码规则
    private String quastatus;//质量状态
    private String rate;//	number	转化率
    private String remark;//备注
    private String resttime;//入库时间
    private String seprice;//结算价
    private String sesprice;//结算价，不含税
    private String setime;//结算时间
    private String specmodel;//	string	规格
    private String sprice;//	number	不含税价
    private String stbin;//仓区
    private String stemid;//库管
    private String stid;//库房
    private String suid;//供应商
    private String tax;//number	税率
    private String tebillid;//	string	检验单号
    private String teemid;//	string	质检员
    private String tetime;//	string	检验时间
    private String unit;//string	单位
    private String unquanums;//不合格数
    private String unquareason;//不合格原因
    private String warranper;//
    private String warrantime;//
    private String purcher;
    private String productdate;


    public String getProductdate() {
        return productdate;
    }

    public void setProductdate(String productdate) {
        this.productdate = productdate;
    }

    public String getPurcher() {
        return purcher;
    }

    public void setPurcher(String purcher) {
        this.purcher = purcher;
    }

    public String getArrtime() {
        return arrtime;
    }

    public void setArrtime(String arrtime) {
        this.arrtime = arrtime;
    }

    public String getAuxnums() {
        return auxnums;
    }

    public void setAuxnums(String auxnums) {
        this.auxnums = auxnums;
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

    public String getBarautoid() {
        return barautoid;
    }

    public void setBarautoid(String barautoid) {
        this.barautoid = barautoid;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public String getBillcode() {
        return billcode;
    }

    public void setBillcode(String billcode) {
        this.billcode = billcode;
    }

    public String getBindnum() {
        return bindnum;
    }

    public void setBindnum(String bindnum) {
        this.bindnum = bindnum;
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

    public String getFurtnum() {
        return furtnum;
    }

    public void setFurtnum(String furtnum) {
        this.furtnum = furtnum;
    }

    public String getInvnumber() {
        return invnumber;
    }

    public void setInvnumber(String invnumber) {
        this.invnumber = invnumber;
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

    public CheckState getChecked() {
        return checked;
    }

    public void setChecked(CheckState checked) {
        this.checked = checked;
    }

    public String getOcunums() {
        return ocunums;
    }

    public void setOcunums(String ocunums) {
        this.ocunums = ocunums;
    }

    public String getOutnums() {
        return outnums;
    }

    public void setOutnums(String outnums) {
        this.outnums = outnums;
    }

    public String getPaynumber() {
        return paynumber;
    }

    public void setPaynumber(String paynumber) {
        this.paynumber = paynumber;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
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

    public String getQrcodes() {
        return qrcodes;
    }

    public void setQrcodes(String qrcodes) {
        this.qrcodes = qrcodes;
    }

    public String getQuastatus() {
        return quastatus;
    }

    public void setQuastatus(String quastatus) {
        this.quastatus = quastatus;
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getResttime() {
        return resttime;
    }

    public void setResttime(String resttime) {
        this.resttime = resttime;
    }

    public String getSeprice() {
        return seprice;
    }

    public void setSeprice(String seprice) {
        this.seprice = seprice;
    }

    public String getSesprice() {
        return sesprice;
    }

    public void setSesprice(String sesprice) {
        this.sesprice = sesprice;
    }

    public String getSetime() {
        return setime;
    }

    public void setSetime(String setime) {
        this.setime = setime;
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

    public String getStbin() {
        return stbin;
    }

    public void setStbin(String stbin) {
        this.stbin = stbin;
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

    public String getSuid() {
        return suid;
    }

    public void setSuid(String suid) {
        this.suid = suid;
    }

    public String getTax() {
        return tax;
    }

    public void setTax(String tax) {
        this.tax = tax;
    }

    public String getTebillid() {
        return tebillid;
    }

    public void setTebillid(String tebillid) {
        this.tebillid = tebillid;
    }

    public String getTeemid() {
        return teemid;
    }

    public void setTeemid(String teemid) {
        this.teemid = teemid;
    }

    public String getTetime() {
        return tetime;
    }

    public void setTetime(String tetime) {
        this.tetime = tetime;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getUnquanums() {
        return unquanums;
    }

    public void setUnquanums(String unquanums) {
        this.unquanums = unquanums;
    }

    public String getUnquareason() {
        return unquareason;
    }

    public void setUnquareason(String unquareason) {
        this.unquareason = unquareason;
    }

    public String getWarranper() {
        return warranper;
    }

    public void setWarranper(String warranper) {
        this.warranper = warranper;
    }

    public String getWarrantime() {
        return warrantime;
    }

    public void setWarrantime(String warrantime) {
        this.warrantime = warrantime;
    }



}
