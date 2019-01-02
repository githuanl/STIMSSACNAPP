package com.centersoft.utils;

import android.support.v4.util.ArrayMap;

import com.centersoft.base.BaseEntity;
import com.centersoft.base.BaseObserver;
import com.centersoft.entity.CashBaseData;
import com.centersoft.enums.CashType;
import com.centersoft.transformer.CommonTransformer;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by liudong on 2017/11/1.
 */

public class NetCashUtil {


    public interface CallBack<T> {

        void success(T result);

    }

    public static ArrayMap<String, String> c_Map = new ArrayMap<>();

    public static ArrayMap<String, List<CashBaseData>> c_List_Map = new ArrayMap<>();

    private static HttpService getHttpService() {
        return NetUtil.getHttpService();
    }

    //获取质检人员
    public static void getInspector(final CashType spName, final String id, final CallBack callBack) {

        final String prefiex = spName.toString();
        final String key = prefiex + "-" + id;

        if (c_Map.containsKey(prefiex)) {
            if (id.equals("-1")) {
                callBack.success(c_List_Map.get(prefiex));
            } else {
                callBack.success(c_Map.get(key));
            }
        } else {
            getHttpService().getCurrentQcmEmid()
                    .compose(new CommonTransformer())
                    .subscribe(new BaseObserver<BaseEntity<List<CashBaseData>>>(null) {
                        @Override
                        protected void onSuccess(BaseEntity<List<CashBaseData>> be) {

                            c_Map.put(prefiex, prefiex);

                            List<CashBaseData> list = be.getData();
                            if (list == null) {
                                list = new ArrayList<>();
                            }

                            for (CashBaseData cd : list) {
                                c_Map.put(prefiex + "-" + cd.getId(), cd.getPsnname());
                            }
                            c_List_Map.put(prefiex, list);

                            if (id.equals("-1")) {
                                callBack.success(list);
                            } else {
                                callBack.success(c_Map.get(key));
                            }
                        }
                    });
        }
    }

    //获取  供应商 员工渲染 客户信息 库房信息 等等
    public static void getBaseCash(final CashType spName, final String id, final CallBack callBack) {

        final String prefiex = spName.toString();
        final String key = prefiex + "-" + id;
        if (c_Map.containsKey(prefiex)) {
            callBack.success(c_Map.get(key));
        } else {
            getHttpService().listComboDataForSp(spName.getSpName())
                    .compose(new CommonTransformer())
                    .subscribe(new BaseObserver<BaseEntity<List<CashBaseData>>>(null) {
                        @Override
                        protected void onSuccess(BaseEntity<List<CashBaseData>> be) {
                            c_Map.put(prefiex, prefiex);

                            List<CashBaseData> list = be.getData();
                            if (list == null) {
                                list = new ArrayList<>();
                            }

                            c_List_Map.put(prefiex, list);
                            switch (spName) {
                                case Supply:
                                    for (CashBaseData cs : list) {
                                        c_Map.put(prefiex + "-" + cs.getId(), cs.getSupplyname());
                                    }
                                    break;
                                case Employee:
                                    for (CashBaseData cs : list) {
                                        c_Map.put(prefiex + "-" + cs.getId(), cs.getPsnname());
                                    }
                                    break;
                                case Customer:
                                    for (CashBaseData cs : list) {
                                        c_Map.put(prefiex + "-" + cs.getId(), cs.getCustname());
                                    }
                                    break;
                                case Storage:
                                    for (CashBaseData cs : list) {
                                        c_Map.put(prefiex + "-" + cs.getId(), cs.getStore());
                                    }
                                    break;
                                case CheckState:
                                    for (CashBaseData cs : list) {
                                        c_Map.put(prefiex + "-" + cs.getId(), cs.getSorts());
                                    }
                                    break;
                                case Department:
                                    for (CashBaseData cs : list) {
                                        c_Map.put(prefiex + "-" + cs.getId(), cs.getDepartment());
                                    }
                                    break;
                            }
                            callBack.success(c_Map.get(key));
                        }
                    });
        }
    }

    //获取 采购库房
    public static void getAllManuDeptByPur(final CashType spName, final String id, final CallBack callBack) {


        final String prefiex = spName.toString();
        final String key = prefiex + "-" + id;

        if (c_Map.containsKey(prefiex)) {
            if (id.equals("-1")) {
                callBack.success(c_List_Map.get(prefiex));
            } else {
                callBack.success(c_Map.get(key));
            }
        } else {
            getHttpService().getAllManuDeptByPur()
                    .compose(new CommonTransformer())
                    .subscribe(new BaseObserver<BaseEntity<List<CashBaseData>>>(null) {
                        @Override
                        protected void onSuccess(BaseEntity<List<CashBaseData>> baseEntity) {
                            c_Map.put(prefiex, prefiex);
                            List<CashBaseData> list = baseEntity.getData();
                            if (list == null) {
                                list = new ArrayList<>();
                            }
                            c_List_Map.put(prefiex, list);
                            for (CashBaseData cs : list) {
                                c_Map.put(prefiex + "-" + cs.getId(), cs.getManudept());
                            }
                            if (id.equals("-1")) {
                                callBack.success(list);
                            } else {
                                callBack.success(c_Map.get(key));
                            }
                        }
                    });
        }

    }


    //获取 获取成品半成品库房
    public static void getStoresCash(final CashType spName, final String id, final CallBack callBack) {


        final String prefiex = spName.toString();
        final String key = prefiex + "-" + id;

        if (c_Map.containsKey(prefiex)) {
            if (id.equals("-1")) {
                callBack.success(c_List_Map.get(prefiex));
            } else {
                callBack.success(c_Map.get(key));
            }
        } else {
            getHttpService().getStoresBySD()
                    .compose(new CommonTransformer())
                    .subscribe(new BaseObserver<BaseEntity<List<CashBaseData>>>(null) {
                        @Override
                        protected void onSuccess(BaseEntity<List<CashBaseData>> baseEntity) {
                            c_Map.put(prefiex, prefiex);
                            List<CashBaseData> list = baseEntity.getContent();
                            if (list == null) {
                                list = new ArrayList<>();
                            }
                            c_List_Map.put(prefiex, list);
                            for (CashBaseData cs : list) {
                                c_Map.put(prefiex + "-" + cs.getId(), cs.getManudept());
                            }
                            if (id.equals("-1")) {
                                callBack.success(list);
                            } else {
                                callBack.success(c_Map.get(key));
                            }
                        }
                    });
        }

    }
}
