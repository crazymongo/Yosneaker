package com.yosneaker.client.util;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.ThumbnailUtils;
import android.net.Uri;
import android.util.Log;

import com.loopj.android.http.Base64;
import com.yosneaker.client.EditCommentActivity;

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

	/**
	 * 加载缩略图，防止内存溢出
	 * @param uri
	 * @param width
	 * @param height
	 * @return
	 * @throws FileNotFoundException 
	 */
	public static Bitmap getImageThumbnails(Context context,String uri, int width, int height) throws FileNotFoundException {
		Bitmap bitmap = null;
		BitmapFactory.Options options = new BitmapFactory.Options();
		options.inJustDecodeBounds = true;
		bitmap = BitmapFactory.decodeStream(context.getContentResolver().openInputStream(Uri.parse(uri)),null,options);
		options.inJustDecodeBounds = false;
		int beWidth = options.outWidth / width;
		int beHeight = options.outHeight / height;
		int be = 1;
		if (beWidth < beHeight) {
			be = beWidth;
		} else {
			be = beHeight;
		}
		if (be <= 0) {
			be = 1;
		}
		options.inSampleSize = be;
		bitmap = BitmapFactory.decodeStream(context.getContentResolver().openInputStream(Uri.parse(uri)),null,options);
		bitmap = ThumbnailUtils.extractThumbnail(bitmap, width, height,
				ThumbnailUtils.OPTIONS_RECYCLE_INPUT);
		return bitmap;
	}
	
}
