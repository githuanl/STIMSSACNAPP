package com.centersoft.entity;

import java.io.Serializable;

/**
 * Created by liudong on 2017/12/1.
 */

public class MessageNum implements Serializable {


    /**
     * content : 您{num}条检验单已送检,请及时处理..
     * icon : null
     * id : 1
     * msgId : purMaterial
     * name : 您有提示信息
     * num : 1
     * tipImg : null
     * url : app.view.qcm.purMaterial.main.purMaterialMainView
     */

    private String content;
    private String id;
    private String msgId;
    private String name;
    private String num;
    private String url;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMsgId() {
        return msgId;
    }

    public void setMsgId(String msgId) {
        this.msgId = msgId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
