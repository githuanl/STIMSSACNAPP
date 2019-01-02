package com.centersoft.enums;

import java.io.Serializable;

/**
 * Created by liudong on 2017/11/7.
 */

public enum MaterialState implements Serializable {


    storage("已入库"),
    noRevice("未收货"),
    checkout("已检验"),
    create("创建"),
    save("保存"),
    Done("已收货"), //收货
    receive("已收货"),
    assign("在途"),
    checking("检验中");

    private String text;

    public String getText() {
        return text;
    }

    MaterialState(String text) {
        this.text = text;
    }

}
