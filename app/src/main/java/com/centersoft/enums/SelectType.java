package com.centersoft.enums;

import java.io.Serializable;

/**
 * Created by liudong on 2017/11/1.
 */

public enum SelectType implements Serializable {

    Supply("供应商",1),   //
    Employee("员工",2),
    Customer("客户",3),
    Storage("库房",4),
    Inspector("质检员",5),
    Department("部门",6),
    StorageCp("成品库房",7),
    StorageCg("采购库房",8);

    private String name;
    private int code;

    public int getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    SelectType(String name,int code) {
        this.name = name;
        this.code = code;
    }
}
