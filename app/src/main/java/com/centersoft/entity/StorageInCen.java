package com.centersoft.entity;

import com.centersoft.base.BaseEntity;

import java.util.List;

/**
 * Created by liudong on 2017/10/31.
 * 外检
 */

public class StorageInCen extends BaseEntity<StorageIn> {

    private boolean stBinFlag;

    public boolean isStBinFlag() {
        return stBinFlag;
    }

    public void setStBinFlag(boolean stBinFlag) {
        this.stBinFlag = stBinFlag;
    }

    private List<Kw> listSto;

    public List<Kw> getListSto() {
        return listSto;
    }

    public void setListSto(List<Kw> listSto) {
        this.listSto = listSto;
    }

    //库位
    public static class Kw{

        /**
         * acreage : 123
         * autoid : 8
         * checked : yes
         * classic :
         * cubage : 123
         * havenum : 0
         * manudept : 00902
         * nums : 236
         * qrcodes : 8665656
         * remark :
         * sorts : M01
         * stbincode : 2233
         * stbinsn : A02
         * unit : 框
         * weights : 122
         */

        private int acreage;
        private int autoid;
        private String checked;
        private String classic;
        private int cubage;
        private int havenum;
        private String manudept;
        private int nums;
        private String qrcodes;
        private String remark;
        private String sorts;
        private String stbincode;
        private String stbinsn;
        private String unit;
        private int weights;

        public int getAcreage() {
            return acreage;
        }

        public void setAcreage(int acreage) {
            this.acreage = acreage;
        }

        public int getAutoid() {
            return autoid;
        }

        public void setAutoid(int autoid) {
            this.autoid = autoid;
        }

        public String getChecked() {
            return checked;
        }

        public void setChecked(String checked) {
            this.checked = checked;
        }

        public String getClassic() {
            return classic;
        }

        public void setClassic(String classic) {
            this.classic = classic;
        }

        public int getCubage() {
            return cubage;
        }

        public void setCubage(int cubage) {
            this.cubage = cubage;
        }

        public int getHavenum() {
            return havenum;
        }

        public void setHavenum(int havenum) {
            this.havenum = havenum;
        }

        public String getManudept() {
            return manudept;
        }

        public void setManudept(String manudept) {
            this.manudept = manudept;
        }

        public int getNums() {
            return nums;
        }

        public void setNums(int nums) {
            this.nums = nums;
        }

        public String getQrcodes() {
            return qrcodes;
        }

        public void setQrcodes(String qrcodes) {
            this.qrcodes = qrcodes;
        }

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }

        public String getSorts() {
            return sorts;
        }

        public void setSorts(String sorts) {
            this.sorts = sorts;
        }

        public String getStbincode() {
            return stbincode;
        }

        public void setStbincode(String stbincode) {
            this.stbincode = stbincode;
        }

        public String getStbinsn() {
            return stbinsn;
        }

        public void setStbinsn(String stbinsn) {
            this.stbinsn = stbinsn;
        }

        public String getUnit() {
            return unit;
        }

        public void setUnit(String unit) {
            this.unit = unit;
        }

        public int getWeights() {
            return weights;
        }

        public void setWeights(int weights) {
            this.weights = weights;
        }
    }
}
