# Yosneaker 
## 一、图片处理ImageLoader
ImageLoader.getInstance().displayImage(imageUri, v);//可以有其他参数,如图片加载的回调等
imageUri格式:
String imageUri = "http://site.com/image.png"; // from Web  
String imageUri = "file:///mnt/sdcard/image.png"; // from SD card  
String imageUri = "content://media/external/audio/albumart/13"; // from content provider  
String imageUri = "assets://image.png"; // from assets  
String imageUri = "drawable://" + R.drawable.image; // from drawables (only images, non-9patch)

## 二、网络请求AsyncHttpClient
详询HttpClientUtil.java
