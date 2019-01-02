package com.centersoft.entity;

import com.centersoft.base.BaseEntity;
import com.centersoft.enums.SaleInvoiceDetailCheckedEnum;

import java.util.List;

/**
 * Created by liudong on 2017/12/11.
 */

public class StorageOut2 extends BaseEntity {

    private List<StroageOutDetail> details;

    public List<StroageOutDetail> getDetails() {
        return details;
    }

    public void setDetails(List<StroageOutDetail> details) {
        this.details = details;
    }

    public StorageOutMaster getMaster() {
        return master;
    }

    public void setMaster(StorageOutMaster master) {
        this.master = master;
    }

    private StorageOutMaster master;

    public class StorageOutMaster {
        private String amount;//300,
        private String arriveDate;//null,
        private String autoid;//null,
        private String billcode;//"STO17-12110001",
        private String billdate;//"2017-12-11 00:00:00",
        private String billemid;//"0202002",
        private String billsort;//"",
        private SaleInvoiceDetailCheckedEnum checked;//"DC",
        private String contact;//"004",
        private String contactphone;//"85121917",
        private String contactsort;//"",
        private String creditlimit;//100000,
        private String define1;//"",
        private String define10;//"",
        private String define2;//"",
        private String define3;//"",
        private String define4;//"",
        private String define5;//"",
        private String define6;//"",
        private String define7;//"",
        private String define8;//"",
        private String define9;//"",
        private String depart;//"1904",
        private String destination;//"天府新谷",
        private String discenter;//"张XX",
        private String disfree;//null,
        private String dissort;//"",
        private String manucenter;//"",
        private String planDate;//null,
        private String primecost;//null,
        private String projectcode;//"",
        private String remark;//"",
        private String scqu;//"1712",
        private String store;//""

        public String getAmount() {
            return amount;
        }

        public void setAmount(String amount) {
            this.amount = amount;
        }

        public String getArriveDate() {
            return arriveDate;
        }

        public void setArriveDate(String arriveDate) {
            this.arriveDate = arriveDate;
        }

        public String getAutoid() {
            return autoid;
        }

        public void setAutoid(String autoid) {
            this.autoid = autoid;
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

        public String getContact() {
            return contact;
        }

        public void setContact(String contact) {
            this.contact = contact;
        }

        public String getContactphone() {
            return contactphone;
        }

        public void setContactphone(String contactphone) {
            this.contactphone = contactphone;
        }

        public String getContactsort() {
            return contactsort;
        }

        public void setContactsort(String contactsort) {
            this.contactsort = contactsort;
        }

        public String getCreditlimit() {
            return creditlimit;
        }

        public void setCreditlimit(String creditlimit) {
            this.creditlimit = creditlimit;
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

        public String getDepart() {
            return depart;
        }

        public void setDepart(String depart) {
            this.depart = depart;
        }

        public String getDestination() {
            return destination;
        }

        public void setDestination(String destination) {
            this.destination = destination;
        }

        public String getDiscenter() {
            return discenter;
        }

        public void setDiscenter(String discenter) {
            this.discenter = discenter;
        }

        public String getDisfree() {
            return disfree;
        }

        public void setDisfree(String disfree) {
            this.disfree = disfree;
        }

        public String getDissort() {
            return dissort;
        }

        public void setDissort(String dissort) {
            this.dissort = dissort;
        }

        public String getManucenter() {
            return manucenter;
        }

        public void setManucenter(String manucenter) {
            this.manucenter = manucenter;
        }

        public String getPlanDate() {
            return planDate;
        }

        public void setPlanDate(String planDate) {
            this.planDate = planDate;
        }

        public String getPrimecost() {
            return primecost;
        }

        public void setPrimecost(String primecost) {
            this.primecost = primecost;
        }

        public String getProjectcode() {
            return projectcode;
        }

        public void setProjectcode(String projectcode) {
            this.projectcode = projectcode;
        }

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }

        public String getScqu() {
            return scqu;
        }

        public void setScqu(String scqu) {
            this.scqu = scqu;
        }

        public String getStore() {
            return store;
        }

        public void setStore(String store) {
            this.store = store;
        }
    }

}
