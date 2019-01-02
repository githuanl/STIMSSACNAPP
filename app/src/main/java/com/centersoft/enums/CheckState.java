package com.centersoft.enums;

/**
 * Created by liudong on 2017/11/2.
 */

public enum CheckState {

    noRevice("未收货"),
    save("保存"),
    qua("合格"),
    checking("检验中"),
    putSto("已入库"),
    waitcheck("待检验"),//	待检验
    disQua("不合格"),
    create("新增"),
    Done("已用完"),//已用完毕
    checkout("已检验"),
    cancel("作废"),
    finish("完成"),
    storage("已入库"),
    estimate("审批中");

    private String text;

    public String getText() {
        return text;
    }

    CheckState(String text) {
        this.text = text;
    }
}
