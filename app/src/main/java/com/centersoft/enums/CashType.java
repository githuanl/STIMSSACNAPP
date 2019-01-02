package com.centersoft.enums;

/**
 * Created by liudong on 2017/11/1.
 */

public enum CashType {

    Supply("Sp_BaseSupply_Combox()"),   //供应商
    Employee("Sp_BaseEmp_all"),         //员工渲染
    Customer("Sp_FunBase_Customer"),    //客户信息
    Storage("Sp_Fun_Base_Store"),       //库房信息
    CheckState("Sp_DataClass('qcmCheck')"),//检验状态
    Department("Sp_FunBase_Department()"),//部门或者工作中心
    Inspector("质检员"),
    StorageCp("成品库"),
    StorageCg("采购库房"),;

    private String spName;

    public String getSpName() {
        return spName;
    }

    CashType(String spName) {
        this.spName = spName;
    }
}
