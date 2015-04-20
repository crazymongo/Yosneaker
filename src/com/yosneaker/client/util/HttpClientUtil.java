package com.yosneaker.client.util;

import android.graphics.Bitmap;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.yosneaker.client.app.YosneakerAppState;
import com.yosneaker.client.model.CommentDraft;

public class HttpClientUtil {

    private static AsyncHttpClient client = new AsyncHttpClient();
    
    static
    {
        client.setTimeout(Constants.HTTP_TIME_OUT);   //设置链接超时，如果不设置，默认为10s
    }

    private static void get(String url, AsyncHttpResponseHandler responseHandler) {
        client.get(getAbsoluteUrl(url), responseHandler);
    }
    
    private static void get(String url, RequestParams params, AsyncHttpResponseHandler responseHandler) {
        client.get(getAbsoluteUrl(url), params, responseHandler);
    }

    private static void post(String url, RequestParams params, AsyncHttpResponseHandler responseHandler) {
    	client.post(getAbsoluteUrl(url), params, responseHandler);
    }

    private static String getAbsoluteUrl(String relativeUrl) {
        return Constants.HTTP_BASE_URL + relativeUrl;
    }

    /**
     * 上传图片到服务器
     * @param bitmap
     * @param responseHandler
     */
    public static void uploadResources(Bitmap bitmap,AsyncHttpResponseHandler responseHandler) {
    	RequestParams params = new RequestParams();  
    	params.put("image", BitmapUtil.bitmap2Base64Str(YosneakerAppState.getInstance().getContext(), bitmap));
    	post("upload/resources/picture.json", params,responseHandler);
    }
    
    public static void publishComment(CommentDraft commentDraft,AsyncHttpResponseHandler responseHandler) {
    	
    }
    
}
