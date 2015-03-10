package com.yosneaker.client.util;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.yosneaker.client.define.Constants;

public class AsyncHttpClientUtil {

    private static AsyncHttpClient client = new AsyncHttpClient();
    
    static
    {
        client.setTimeout(Constants.HTTP_TIME_OUT);   //设置链接超时，如果不设置，默认为10s
    }

    public static void get(String url, RequestParams params, AsyncHttpResponseHandler responseHandler) {
        client.get(getAbsoluteUrl(url), params, responseHandler);
    }

    public static void post(String url, RequestParams params, AsyncHttpResponseHandler responseHandler) {
        client.post(getAbsoluteUrl(url), params, responseHandler);
    }

    private static String getAbsoluteUrl(String relativeUrl) {
        return Constants.HTTP_BASE_URL + relativeUrl;
    }
	
}
