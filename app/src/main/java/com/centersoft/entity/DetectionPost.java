package com.centersoft.entity;

import java.io.Serializable;
import java.util.List;

/**
 * Created by liudong on 2017/11/2.
 */

public class DetectionPost implements Serializable {
    private List<Detection> qcmExaDetail;
    private QcmExaMaster qcmExaMaster;

    public List<Detection> getQcmExaDetail() {
        return qcmExaDetail;
    }

    public void setQcmExaDetail(List<Detection> qcmExaDetail) {
        this.qcmExaDetail = qcmExaDetail;
    }

    public QcmExaMaster getQcmExaMaster() {
        return qcmExaMaster;
    }

    public void setQcmExaMaster(QcmExaMaster qcmExaMaster) {
        this.qcmExaMaster = qcmExaMaster;
    }
}
