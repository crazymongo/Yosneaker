package com.yosneaker.client.util;

import java.io.ByteArrayOutputStream;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;

import com.loopj.android.http.Base64;
import com.yosneaker.client.define.Constants;

public class BitmapUtil {

	public static String bitmap2Base64Str(final Context context,Bitmap bitmap) {
		
		try {  
            ByteArrayOutputStream baos = new ByteArrayOutputStream();  
              
            //将bitmap一字节流输出 Bitmap.CompressFormat.PNG 压缩格式，100：压缩率，baos：字节流  
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);  
            baos.close();  
            byte[] buffer = baos.toByteArray();  
            Log.d(Constants.TAG, "bitmap size:"+buffer.length);   
            //将图片的字节流数据加密成base64字符输出  
            String base64Str = Base64.encodeToString(buffer, 0, buffer.length,Base64.DEFAULT);
            return base64Str;
		} catch (Exception e) {  
            e.printStackTrace();  
        }
		return "";

	}
	
}
