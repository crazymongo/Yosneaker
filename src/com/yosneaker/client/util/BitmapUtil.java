package com.yosneaker.client.util;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PaintFlagsDrawFilter;
import android.graphics.Rect;
import android.media.ThumbnailUtils;
import android.net.Uri;
import android.util.Log;

import com.loopj.android.http.Base64;

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
	 * 从uri获取bitmap
	 * @param context
	 * @param uri
	 * @return
	 * @throws FileNotFoundException
	 */
	public static Bitmap getBitmapFromUri(Context context,String uri){
		Bitmap bitmap = null;
		try {
			bitmap = BitmapFactory.decodeStream(context.getContentResolver().openInputStream(Uri.parse(uri)));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return bitmap;
	}

	/**
	 * 获取缩放后的图片
	 * @param context
	 * @param uri
	 * @param edgeLength 宽
	 * @param scale 高/宽 比例
	 * @return
	 * @throws FileNotFoundException
	 */
	public static Bitmap getScaleBitmap(Bitmap bitmap, int edgeLength,float scale) throws FileNotFoundException {

		if (null == bitmap || edgeLength <= 0 || scale == 0) {
			return null;
		}
		Bitmap result = bitmap;
		int widthOrg = bitmap.getWidth();
		int heightOrg = bitmap.getHeight();

		if (widthOrg > edgeLength && heightOrg > edgeLength*scale) {
			int scaledWidth = edgeLength;
			int scaledHeight = (int) (edgeLength*scale);
			int x = (widthOrg-scaledWidth)/2;
			int y = (heightOrg-scaledHeight)/2;
			if (Math.abs(x-y) > 10) {
				bitmap = Bitmap.createBitmap(bitmap, x, y, scaledWidth, scaledHeight); 
			}			
			try {
				result = Bitmap.createScaledBitmap(bitmap, scaledWidth,scaledHeight, true);
			} catch (Exception e) {
				return null;
			}
		}

		return result;
	}
	
}
