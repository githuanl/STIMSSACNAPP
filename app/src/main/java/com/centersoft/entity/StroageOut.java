package com.centersoft.entity;

/**
 * Created by liudong on 2017/10/27.
 */

public class StroageOut {


    /**
     * arrtime ;//2017-10-31 09:24:39
     * autoid ;//194
     * auxnums ;//1
     * auxunit ;//箱
     * barcode :
     * billcode ;//MET17-10310001
     * bindnum ;//1
     * checked ;//create
     * classifies ;//1
     * define1 ;//1
     * define2 ;//1
     * define3 ;//1
     * define4 ;//1
     * define5 ;//1
     * define6 ;//1
     * furtnum ;//1
     * invnumber ;//1
     * manuautoid ;//299
     * manubatchnum ;//PC171027001
     * manucost ;//1
     * manuid ;//1
     * material ;//Y3-80M1-2 定子线圈
     * materialcost ;//1
     * materialid ;//1408001013
     * nums ;//83125
     * ocunums ;//600
     * outnums ;//11850
     * paynumber ;//1
     * quastatus ;//1
     * rate ;//10
     * remark ;//测试内容4536
     * resttime ;//1
     * scqu ;//1710
     * seprice ;//1
     * sesprice ;//1
     * setime ;//1
     * specmodel ;//1
     * sprice ;//1
     * stbin ;//1
     * stemid ;//1
     * stid ;//1816
     * sumanuid ;//1901
     * tebillid ;//1
     * teemid ;//1
     * tetime ;//1
     * unit ;//件
     * unquanums ;//1
     * unquareason ;//1
     * warranper ;//1
     * warrantime ;//1
     */

    private String arrtime;         //      入库时间
    private String autoid;          //      库存序号
    private int auxnums;            //      数量/辅
    private String auxunit;         //  	单位/辅
    private String barcode;
    private String billcode;        //      入库单编号
    private int bindnum;
    private String checked;         //      状态
    private int classifies;
    private int define1;
    private int define2;
    private int define3;
    private int define4;
    private int define5;
    private int define6;
    private int furtnum;
    private int invnumber;
    private String manuautoid;      //      入库单序号
    private String manubatchnum;    //      批次号
    private int manucost;
    private int manuid;
    private String material;        //      物料名称
    private int materialcost;
    private String materialid;      //      物料编号
    private double nums;               //      数量	number	库存数=nums-ocunums-outnums
    private double ocunums;            //      占用数
    private double outnums;            //      出库数
    private int paynumber;
    private int quastatus;
    private double rate;               //      转换率/包装
    private String remark;          //      备注
    private String resttime;
    private String scqu;            //      期间
    private int seprice;
    private int sesprice;
    private int setime;
    private String specmodel;          //      规格型号
    private String sprice;
    private String stbin;
    private String stemid;
    private String stid;            //      库房
    private String sumanuid;        //      供货单位
    private int tebillid;
    private int teemid;
    private int tetime;
    private String unit;            //      单位
    private int unquanums;
    private int unquareason;
    private int warranper;
    private int warrantime;
    private String custid;          //

    public String getCustid() {
        return custid;
    }

    public void setCustid(String custid) {
        this.custid = custid;
    }

    public String getArrtime() {
        return arrtime;
    }

    public void setArrtime(String arrtime) {
        this.arrtime = arrtime;
    }

    public String getAutoid() {
        return autoid;
    }

    public void setAutoid(String autoid) {
        this.autoid = autoid;
    }

    public int getAuxnums() {
        return auxnums;
    }

    public void setAuxnums(int auxnums) {
        this.auxnums = auxnums;
    }

    public String getAuxunit() {
        return auxunit;
    }

    public void setAuxunit(String auxunit) {
        this.auxunit = auxunit;
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

    public int getBindnum() {
        return bindnum;
    }

    public void setBindnum(int bindnum) {
        this.bindnum = bindnum;
    }

    public String getChecked() {
        return checked;
    }

    public void setChecked(String checked) {
        this.checked = checked;
    }

    public int getClassifies() {
        return classifies;
    }

    public void setClassifies(int classifies) {
        this.classifies = classifies;
    }

    public int getDefine1() {
        return define1;
    }

    public void setDefine1(int define1) {
        this.define1 = define1;
    }

    public int getDefine2() {
        return define2;
    }

    public void setDefine2(int define2) {
        this.define2 = define2;
    }

    public int getDefine3() {
        return define3;
    }

    public void setDefine3(int define3) {
        this.define3 = define3;
    }

    public int getDefine4() {
        return define4;
    }

    public void setDefine4(int define4) {
        this.define4 = define4;
    }

    public int getDefine5() {
        return define5;
    }

    public void setDefine5(int define5) {
        this.define5 = define5;
    }

    public int getDefine6() {
        return define6;
    }

    public void setDefine6(int define6) {
        this.define6 = define6;
    }

    public int getFurtnum() {
        return furtnum;
    }

    public void setFurtnum(int furtnum) {
        this.furtnum = furtnum;
    }

    public int getInvnumber() {
        return invnumber;
    }

    public void setInvnumber(int invnumber) {
        this.invnumber = invnumber;
    }

    public String getManuautoid() {
        return manuautoid;
    }

    public void setManuautoid(String manuautoid) {
        this.manuautoid = manuautoid;
    }

    public String getManubatchnum() {
        return manubatchnum;
    }

    public void setManubatchnum(String manubatchnum) {
        this.manubatchnum = manubatchnum;
    }

    public int getManucost() {
        return manucost;
    }

    public void setManucost(int manucost) {
        this.manucost = manucost;
    }

    public int getManuid() {
        return manuid;
    }

    public void setManuid(int manuid) {
        this.manuid = manuid;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public int getMaterialcost() {
        return materialcost;
    }

    public void setMaterialcost(int materialcost) {
        this.materialcost = materialcost;
    }

    public String getMaterialid() {
        return materialid;
    }

    public void setMaterialid(String materialid) {
        this.materialid = materialid;
    }

    public double getNums() {
        return nums;
    }

    public void setNums(double nums) {
        this.nums = nums;
    }

    public double getOcunums() {
        return ocunums;
    }

    public void setOcunums(double ocunums) {
        this.ocunums = ocunums;
    }

    public double getOutnums() {
        return outnums;
    }

    public void setOutnums(int outnums) {
        this.outnums = outnums;
    }

    public int getPaynumber() {
        return paynumber;
    }

    public void setPaynumber(int paynumber) {
        this.paynumber = paynumber;
    }

    public int getQuastatus() {
        return quastatus;
    }

    public void setQuastatus(int quastatus) {
        this.quastatus = quastatus;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
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

    public String getScqu() {
        return scqu;
    }

    public void setScqu(String scqu) {
        this.scqu = scqu;
    }

    public int getSeprice() {
        return seprice;
    }

    public void setSeprice(int seprice) {
        this.seprice = seprice;
    }

    public int getSesprice() {
        return sesprice;
    }

    public void setSesprice(int sesprice) {
        this.sesprice = sesprice;
    }

    public int getSetime() {
        return setime;
    }

    public void setSetime(int setime) {
        this.setime = setime;
    }

    public String getSpecmodel() {
        return specmodel;
    }

    public void setSpecmodel(String specmodel) {
        this.specmodel = specmodel;
    }

    public void setOutnums(double outnums) {
        this.outnums = outnums;
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

    public String getSumanuid() {
        return sumanuid;
    }

    public void setSumanuid(String sumanuid) {
        this.sumanuid = sumanuid;
    }

    public int getTebillid() {
        return tebillid;
    }

    public void setTebillid(int tebillid) {
        this.tebillid = tebillid;
    }

    public int getTeemid() {
        return teemid;
    }

    public void setTeemid(int teemid) {
        this.teemid = teemid;
    }

    public int getTetime() {
        return tetime;
    }

    public void setTetime(int tetime) {
        this.tetime = tetime;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public int getUnquanums() {
        return unquanums;
    }

    public void setUnquanums(int unquanums) {
        this.unquanums = unquanums;
    }

    public int getUnquareason() {
        return unquareason;
    }

    public void setUnquareason(int unquareason) {
        this.unquareason = unquareason;
    }

    public int getWarranper() {
        return warranper;
    }

    public void setWarranper(int warranper) {
        this.warranper = warranper;
    }

    public int getWarrantime() {
        return warrantime;
    }

    public void setWarrantime(int warrantime) {
        this.warrantime = warrantime;
    }
}
