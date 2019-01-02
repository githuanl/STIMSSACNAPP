package com.centersoft.enums;

/**
 * Created by liudong on 2017/11/17.
 */

public enum ManuImDetailCheckedEnum {

    create("新增"), //新增
    submit("已提交"), //提交
    Done("已收货"), //收货
    Reject("已拒绝"), //拒绝
    back("退回接收"); //退回接收

    private String text;

    public String getText() {
        return text;
    }

    ManuImDetailCheckedEnum(String text) {
        this.text = text;
    }

}
