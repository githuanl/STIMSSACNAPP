package com.centersoft.utils;


import com.alibaba.fastjson.JSONObject;
import com.centersoft.base.BaseEntity;
import com.centersoft.entity.AppUpdate;
import com.centersoft.entity.CashBaseData;
import com.centersoft.entity.Customer;
import com.centersoft.entity.Detection;
import com.centersoft.entity.LoginBean;
import com.centersoft.entity.ManuIm;
import com.centersoft.entity.ManuImDetail;
import com.centersoft.entity.MaterialsTransit;
import com.centersoft.entity.MessageNum;
import com.centersoft.entity.PurchaseIm;
import com.centersoft.entity.StorageIn;
import com.centersoft.entity.StorageInCen;
import com.centersoft.entity.StorageOut2;
import com.centersoft.entity.StroageOut;

import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;
import retrofit2.http.Streaming;
import retrofit2.http.Url;

/**
 * Created by
 * 网络请求的接口都在这里
 */

public interface HttpService {

    //登录接口
    @POST("basicData/user/login")
    Observable<BaseEntity<LoginBean>> login(@QueryMap Map<String, String> options);

    // 重新登录
    @POST("basicData/user/login")
    Call<BaseEntity<LoginBean>> reLogin(
            @Query("loginname") String name,
            @Query("password") String pass
    );

    // 检测更新
    @Headers("Cache-Control: no-cache")
    @GET("apk/download/version.json")
    Observable<AppUpdate> updateDetection();

    @Streaming
    @GET
    Observable<ResponseBody> downloadAppFile(@Url String fileUrl);

    //缓存数据获取
    @POST("comm/gridComm/listComboDataForSp")
    Observable<BaseEntity<List<CashBaseData>>> listComboDataForSp(@Query("spName") String spName);


    //扫描在途物资中间表展示
    @GET("store/scan/getInfoDetail")
    Observable<MaterialsTransit> getInfoDetail(@Query("dcqrcodes") String dcqrcodes);

    //送检提交
    @POST("store/scan/submitStoOrder")
    Observable<StorageIn> submitStoOrder(@Query("qrcodes") String qrcodes,@Query("qcemid") String qcemid
            ,@Query("stid") String stid,@Query("nums") String nums);


    //扫描生成送检单
    @GET("store/storePurchaseim/getStoOrder")
    Observable<PurchaseIm> getStoOrder(@Query("qrcodes") String qrcodes);



    //外购收货及扫描收货 保存
    @POST("store/scan/saveStoOrder")
    Observable<BaseEntity> saveStoOrder(@Query("qrcodes") String qrcodes,@Query("qcemid") String qcemid,@Query("stid") String stid);


    //外检
    @POST("qmc/purMaterial/getQcmByStcc")
    Observable<Detection> getQcmByStcc(@Query("dcqrcodes") String dcqrcodes);


    //检验单提交
    @FormUrlEncoded
    @POST("qmc/purMaterial/terminalSubmit")
    Observable<BaseEntity<String>> terminalSubmit(@Field("detail") String qcmExaDetail);


    //扫描 外购入库 信息
    @GET("store/storePurchaseim/getDetailByDcqrcode")
    Observable<StorageInCen> getDetailByDcqrcode(@Query("dcqrcode") String dcqrcode);

    //外购 入库库房选择
    @GET("store/storePurchaseim/getCurrentManuDept")
    Observable<List<CashBaseData>> getCurrentManuDept();


    //外购入库 提交
    @FormUrlEncoded
    @POST("store/storePurchaseim/updatePutStorageStatus")
    Observable<BaseEntity<String>> updatePutStorageStatus(@Field("details") String details);


    //获取仓区
    @GET("basicData/baseStoreStBin/getQrcodesByStbin")
    Observable<BaseEntity<List<StorageInCen.Kw>>> getQrcodesByStbin(@Query("qrcodes") String qrcodes);

    /***------------------  销售出库 ---------------------------------- **/

    //通过扫描条码拿到物料明细
    //    manuautoid	是	number	入库单序号
    //    manubatchnum	是	string	批次号
    @GET("store/scanout/getMaterialByBarcode")
    Observable<BaseEntity<StroageOut>> getMaterialByBarcode(@Query("barcode") String barcode);


    //写入扫描数据
    @POST("store/scanout/insertIntoScan")
    Observable<BaseEntity<String>> insertIntoScan(@QueryMap Map<String, String> options);


    //获取销售出库单明细
    @GET("store/saleInvoice/getDetailList")
    Observable<BaseEntity<JSONObject>> getDetailList(@Query("billcode") String billcode);


    /***------------------  销售出库 --end-------------------------------- **/


    /***------------------  成品扫描入库 ---------------------------------- **/


    //成品扫描入库
    @POST("mes/manuIm/manuImByScan")
    Observable<BaseEntity<ManuImDetail>> manuImByScan(@Query("barcode") String barcode);

    //成品扫描入库
    @POST("mes/manuIm/receiveByScan")
    Observable<BaseEntity<String>> receiveByScan(@QueryMap Map<String, String> options);


    /***------------------  成品扫描入库 --end-------------------------------- **/


    /***------------------  领料出库 ---------------------------------- **/


    //领料(无单)出库
    @POST("store/scanout/insertIntoScanByManu")
    Observable<BaseEntity<String>> insertIntoScanByManu(@QueryMap Map<String, String> options);


    /***------------------  领料出库 --end-------------------------------- **/

    //获取消息个数
    @GET("basicData/user/getTipMsg")
    Observable<BaseEntity<List<MessageNum>>> getTipMsg();

    //获取质检员
    @GET("store/scan/getCurrentQcmEmid")
    Observable<BaseEntity<List<CashBaseData>>> getCurrentQcmEmid();


    // 中汽 成品入库
    @GET("zq/recout/getManuImByCode")
    Observable<ManuIm> getManuImByCode(@Query("code") String code);


    // 中汽 缴库
    @POST("zq/recout/saveManuImByCode")
    Observable<ManuIm> saveManuImByCode(@Query("code") String code,@Query("recedept") String recedept);

    // 成品半成品库房
    @GET("basicData/baseManuDeptDetail/getStroesBySD")
    Observable<BaseEntity<List<CashBaseData>>> getStoresBySD();


    // 采购库房
    @GET("store/storePurchaseim/getAllManuDeptByPur")
    Observable<BaseEntity<List<CashBaseData>>> getAllManuDeptByPur();


    // 特定二维码识别收货
    @GET("store/scan/getQrcodesCreateList")
    Observable<StorageIn> getQrcodesCreateList(@Query("qrcodes") String qrcodes);


    //  autoid	是	number	销售出库单明细序号
    //  emid	是	string	员工
    //  stid	是	string	库房
    // 特定二维码识别收货
    @POST("store/saleInvoice/deliver/confirmDeliverByScan")
    Observable<BaseEntity<String>> confirmDeliverByScan(@QueryMap Map<String, String> options);


    // 获取销售出库单明细 STO17-10310003
    @GET("store/saleInvoice/getMasterAndDetailsByScan")
    Observable<BaseEntity<StorageOut2>> getMasterAndDetailsByScan(@Query("billcode") String billcode);


    // 领料单 根据明细发货
    @POST("store/manuRequire/deliver/confirmDeliverByScan")
    Observable<BaseEntity<String>> confirmWareHouseDeliverByScan(@QueryMap Map<String, String> options);

    // 扫描领料单获取所有明细
    @GET("store/manuRequire/deliver/getMasterAndDetailsByScan")
    Observable<BaseEntity<StorageOut2>> getWareHouseMasterAndDetailsByScan(@Query("billcode") String billcode);

    // 获取物料的默认客户
    @GET("basicData/baseSouceIdDetail/getDefaultCustomerByMaterialid")
    Observable<BaseEntity<Customer>> getDefaultCustomerByMaterialid(@Query("materialid") String materialid);


    //获取仓区库位
    @GET("basicData/baseStoreStBin/getStbinByStbinName")
    Observable<BaseEntity<List<String>>> getStbinByStbinName(@Query("stbinCode") String stbinCode);



    //通过扫描拿到物料明细
    @GET("store/scanout/getMaterialByBatchcode")
    Observable<BaseEntity<StorageIn>> getMaterialByBatchcode(@Query("batchcode") String batchcode,@Query("stid") String stid);


    /**
     *
     * 功能描述:  领料出库(无单) 扫描获取物料
     *
     * @param:
     * @return:
     * @auther: lh
     * @date: 2019/1/2 上午9:26
     */
    @GET("store/scanout/getMaterialByScan")
    Observable<BaseEntity<StroageOut>> getMaterialByScan(@Query("batchcode") String batchcode);


}