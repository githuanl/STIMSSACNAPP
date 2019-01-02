package com.centersoft.utils;

import com.blankj.utilcode.util.SPUtils;
import com.centersoft.ScanApplication;
import com.centersoft.base.BaseEntity;
import com.centersoft.convert.FastJsonConverterFactory;
import com.centersoft.entity.LoginBean;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.*;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;


/**
 * Created by liudong on 2017/10/25.
 */

public class NetUtil {


    private static OkHttpClient client;
    private static HttpService httpService;
    private static volatile Retrofit retrofit;

    private static String TAG = "NetUtil===>";

    /**
     * @return retrofit的底层利用反射的方式, 获取所有的api接口的类
     */
    public static HttpService getHttpService() {
        if (httpService == null) {
            httpService = getRetrofit().create(HttpService.class);
        }
        return httpService;
    }

    public static void init() {
        httpService = null;
        retrofit = null;
    }


    /**
     * 设置公共参数
     */
    private static Interceptor addQueryParameterInterceptor() {
        Interceptor addQueryParameterInterceptor = new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request originalRequest = chain.request();
                Request request;
                HttpUrl modifiedUrl = originalRequest.url().newBuilder()
                        // Provide your custom parameter here
                        .addQueryParameter("phoneSystem", "")
                        .addQueryParameter("phoneModel", "")
                        .build();
                request = originalRequest.newBuilder().url(modifiedUrl).build();
                return chain.proceed(request);
            }
        };
        return addQueryParameterInterceptor;
    }

    /**
     * 设置头
     */
    private static Interceptor addHeaderInterceptor() {
        Interceptor headerInterceptor = new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {

                Request req = chain.request();

                Response res = chain.proceed(req);

                List<String> list = res.headers("sessionstatus");

                if (list != null && list.size() > 0 && list.get(0).equals("timeout")) {
                    MyLog.i(TAG, "重新登录开始---------------》");
                    String loginname = SPUtils.getInstance().getString("loginname");
                    String password = SPUtils.getInstance().getString("password");
                    Call<BaseEntity<LoginBean>> call = httpService.reLogin(loginname, MD5Util.getMD5(password + loginname));
                    BaseEntity<LoginBean> b = call.execute().body();
                    if (b.getId() > 0) {
                        MyLog.i(TAG, "重新登录成功---------------》");
                        Constant.EMPLOYEEID = b.getGlobal().getEMPLOYEEID();
                        return chain.proceed(req);
                    }
                }

                return res;

//                /**通过如下的办法曲线取到请求完成的数据
//                 *
//                 * 原本想通过  originalResponse.body().string()
//                 * 去取到请求完成的数据,但是一直报错,不知道是okhttp的bug还是操作不当
//                 *
//                 * 然后去看了okhttp的源码,找到了这个曲线方法,取到请求完成的数据后,根据特定的判断条件去判断token过期
//                 */
//                ResponseBody responseBody = originalResponse.body();
//                BufferedSource source = responseBody.source();
//                source.request(Long.MAX_VALUE); // Buffer the entire body.
//                Buffer buffer = source.buffer();
//                Charset charset = UTF8;
//                MediaType contentType = responseBody.contentType();
//                if (contentType != null) {
//                    charset = contentType.charset(UTF8);
//                }
//                String bodyString = buffer.clone().readString(charset);
//                MyLog.i(TAG, bodyString);
//                判断是否过期 然后登录 重新请求 再加入token
//
//                Request.Builder requestBuilder = request.newBuilder()
////                        .header("token", (String) SpUtils.get("token", ""))
//                        .header("token", "11111111111111")
//                        .method(request.method(), request.body());
//                Request req = requestBuilder.build();
//                return chain.proceed(req);
            }
        };
        return headerInterceptor;
    }


    public static Retrofit getRetrofit() {
        if (retrofit == null) {
            synchronized (NetUtil.class) {
                if (retrofit == null) {
                    //添加一个log拦截器,打印所有的log
                    HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
                    //可以设置请求过滤的水平,body,basic,headers
                    httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

                    //设置 请求的缓存的大小跟位置
                    File cacheFile = new File(ScanApplication.getmContext().getCacheDir(), "cache");
                    Cache cache = new Cache(cacheFile, 1024 * 1024 * 50); //50Mb 缓存的大小
                    client = new OkHttpClient
                            .Builder()
//                            .addInterceptor(addQueryParameterInterceptor())  //参数添加
                            .addInterceptor(addHeaderInterceptor()) // token
                            .addInterceptor(httpLoggingInterceptor) //日志,所有的请求响应度看到
//                            .cache(cache)  //添加缓存
                            .cookieJar(new CookieManger())
                            .connectTimeout(10l, TimeUnit.SECONDS)
                            .readTimeout(60l, TimeUnit.SECONDS)
                            .writeTimeout(60l, TimeUnit.SECONDS)
                            .build();

                    // 获取retrofit的实例
                    retrofit = new Retrofit
                            .Builder()
//                            .baseUrl(Constant.BASE_URL)
                            .baseUrl("http://" + Constant.BASE_URL + "/")  //自己配置
                            .client(client)
                            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                            .addConverterFactory(FastJsonConverterFactory.create()) //这里是用的fastjson的
                            .build();
                }
            }
        }
        return retrofit;
    }

}
