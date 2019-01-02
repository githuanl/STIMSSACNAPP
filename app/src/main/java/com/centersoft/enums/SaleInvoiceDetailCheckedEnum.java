package com.centersoft.enums;

/**
 * Created by liudong on 2017/12/11.
 * 销售出库单 枚举状态
 */
public enum SaleInvoiceDetailCheckedEnum {

    create("新增"),
    submit("提交"),
    DC("配货"),
    DE("发货"),
    sign("签单"),
    reject("拒绝");

    private String text;

    public String getText() {
        return text;
    }

    SaleInvoiceDetailCheckedEnum(String text) {
        this.text = text;
    }

}
