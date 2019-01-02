package com.centersoft.base;

import com.alibaba.fastjson.JSON;
import com.centersoft.entity.StorageInCen;

import java.io.Serializable;
import java.util.List;

/**
 * Created by liudong on 2017/10/27.
 */

public class BaseEntity<T> implements Serializable {

    private int id;
    private int flag;
    private String msg;
    private T data;
    private T content;
    private T global;
    private int autoid;

    private List<StorageInCen.Kw> stbins;   //对应所有仓区
    public List<StorageInCen.Kw> getStbins() {
        return stbins;
    }

    public void setStbins(List<StorageInCen.Kw> stbins) {
        this.stbins = stbins;
    }
    private boolean ifStbin;    //	是否需要选择仓区


    public boolean isIfStbin() {
        return ifStbin;
    }

    public void setIfStbin(boolean ifStbin) {
        this.ifStbin = ifStbin;
    }

    public int getAutoid() {
        return autoid;
    }

    public void setAutoid(int autoid) {
        this.autoid = autoid;
    }

    public T getGlobal() {
        return global;
    }

    public void setGlobal(T global) {
        this.global = global;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public T getContent() {
        return content;
    }

    public void setContent(T content) {
        this.content = content;
    }

    @Override
    public String toString() {

        return "BaseEntity{" +
                "msg='" + msg + '\'' +
                ", content='" + JSON.toJSONString(content) + '\'' +
                '}';
    }
}
