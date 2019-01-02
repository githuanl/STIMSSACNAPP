package com.centersoft.entity;

import java.io.Serializable;

/**
 * Created by liudong on 2017/11/1.
 */

public class CashBaseData implements Serializable{

    private String id;
    private String supplyname;
    private String custname;
    private String store;
    private String sorts;
    private String department;
    private String manudept;
    private String psnname;

    public String getPsnname() {
        return psnname;
    }

    public void setPsnname(String psnname) {
        this.psnname = psnname;
    }

    public String getManudept() {
        return manudept;
    }

    public void setManudept(String manudept) {
        this.manudept = manudept;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getSorts() {
        return sorts;
    }

    public void setSorts(String sorts) {
        this.sorts = sorts;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSupplyname() {
        return supplyname;
    }

    public void setSupplyname(String supplyname) {
        this.supplyname = supplyname;
    }


    public String getCustname() {
        return custname;
    }

    public void setCustname(String custname) {
        this.custname = custname;
    }

    public String getStore() {
        return store;
    }

    public void setStore(String store) {
        this.store = store;
    }
}
