package com.centersoft.utils;

import com.centersoft.ScanApplication;

import java.util.List;

import okhttp3.Cookie;
import okhttp3.CookieJar;
import okhttp3.HttpUrl;

/**
 * Created by liudong on 2017/10/26.
 */

public class CookieManger implements CookieJar {


    private static PersistentCookieStore cookieStore;

    public CookieManger() {
        if (cookieStore == null) {
            cookieStore = new PersistentCookieStore(ScanApplication.getmContext());
        }
    }


    @Override
    public void saveFromResponse(HttpUrl url, List<Cookie> cookies) {
        if (cookies != null && cookies.size() > 0) {
            for (Cookie item : cookies) {
                cookieStore.add(url, item);
            }
        }
    }

    @Override
    public List<Cookie> loadForRequest(HttpUrl url) {
        List<Cookie> cookies = cookieStore.get(url);
        return cookies;
    }


}
