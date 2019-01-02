package com.centersoft.entity;


import com.centersoft.base.BaseEntity;

/**
 * Description    更新
 * FileName       AppUpdate
 * CopyRight      CenterSoft
 * Author         LH
 * Createdate     2016/12/14 下午4:02
 * ------------------------------
 * updateAuthor   <修改人员>
 * updateDate     <修改日期>
 * updateNeedNum  <需求单号>
 * updateContent  <修改内容>
 * ------------------------------
 */
public class AppUpdate extends BaseEntity {

    private int versionCode;                 //版本号
    private Boolean force;                  //是否强制更新
    private String name;                    //
    private String content;                 //
    private String downLoadUrl;             //下载地址


    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getVersionCode() {
        return versionCode;
    }

    public void setVersionCode(int versionCode) {
        this.versionCode = versionCode;
    }

    public Boolean getForce() {
        return force;
    }

    public void setForce(Boolean force) {
        this.force = force;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDownLoadUrl() {
        return downLoadUrl;
    }

    public void setDownLoadUrl(String downLoadUrl) {
        this.downLoadUrl = downLoadUrl;
    }
}