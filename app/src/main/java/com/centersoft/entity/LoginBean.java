package com.centersoft.entity;

import java.io.Serializable;

/**
 * Created by liudong on 2017/11/2.
 */

public class LoginBean implements Serializable{
    //
//      \"EMPLOYEEID\": \"00110\",
//              \"CURRENTDATE\": \"2017-11-02\",
//              \"USERNAME\": \"SYSTEM\",
//              \"EMPLOYEENAME\": \"SYSTEM\",
//              \"DEPART\": \"22\"
    private String EMPLOYEEID;
    private String CURRENTDATE;
    private String USERNAME;
    private String EMPLOYEENAME;
    private String DEPART;

    public String getEMPLOYEEID() {
        return EMPLOYEEID;
    }

    public void setEMPLOYEEID(String EMPLOYEEID) {
        this.EMPLOYEEID = EMPLOYEEID;
    }

    public String getCURRENTDATE() {
        return CURRENTDATE;
    }

    public void setCURRENTDATE(String CURRENTDATE) {
        this.CURRENTDATE = CURRENTDATE;
    }

    public String getUSERNAME() {
        return USERNAME;
    }

    public void setUSERNAME(String USERNAME) {
        this.USERNAME = USERNAME;
    }

    public String getEMPLOYEENAME() {
        return EMPLOYEENAME;
    }

    public void setEMPLOYEENAME(String EMPLOYEENAME) {
        this.EMPLOYEENAME = EMPLOYEENAME;
    }

    public String getDEPART() {
        return DEPART;
    }

    public void setDEPART(String DEPART) {
        this.DEPART = DEPART;
    }
}
