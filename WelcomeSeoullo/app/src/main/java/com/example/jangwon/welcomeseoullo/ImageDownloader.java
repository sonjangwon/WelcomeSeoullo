package com.example.jangwon.welcomeseoullo;

import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.widget.ImageView;

import java.lang.ref.WeakReference;
import java.util.HashMap;

public class ImageDownloader
{
	public static final int IMGAE_CACHE_LIMIT_SIZE = 60;
	public static HashMap<String, Bitmap> mImageCache = new HashMap<String, Bitmap>();
	
	//�̹��� ����ó�� �޼ҵ�
	public static Bitmap getRoundedCornerBitmap(Bitmap bitmap) {
	    Bitmap output = Bitmap.createBitmap(bitmap.getWidth(), 
	        bitmap.getHeight(), Config.ARGB_8888); 
	    Canvas canvas = new Canvas(output); 

	    final int color = 0xff424242; 
	    final Paint paint = new Paint(); 
	    final Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight()); 
	    final RectF rectF = new RectF(rect); 
	    final float roundPx = 12; 
	    paint.setAntiAlias(true); 
	    canvas.drawARGB(0, 0, 0, 0); 
	    paint.setColor(color); 
	    canvas.drawRoundRect(rectF, roundPx, roundPx, paint); 
	    paint.setXfermode(new PorterDuffXfermode(Mode.SRC_IN)); 
	    canvas.drawBitmap(bitmap, rect, rect, paint); 
	    return output; 
	  }
	
	//public static Bitmap cachedImage;
	public static void download(String url, ImageView imageView)
	{
		Bitmap cachedImage = mImageCache.get(url);
		//cachedImage = mImageCache.get(url);
//		if(cachedImage != null)
//		{
//			//Log.e("cachedImage != null","cachedImage != null");
//			 Bitmap resize = Bitmap.createScaledBitmap(cachedImage, 60, 60, true); //��Ʈ���̹��� ������ d��
//			imageView.setImageBitmap(getRoundedCornerBitmap(resize)); //��������� �̹��� ����ó���Ŀ� �̹����ȿ� ��=
//		}
//		else if(cancelPotentialDownload(url, imageView))
//		{
			//Log.e("cachedImage == null","cachedImage == null");
			if(mImageCache.size() > IMGAE_CACHE_LIMIT_SIZE)
			{
				mImageCache.clear();
			}
			ImageDownloaderTask task = new ImageDownloaderTask(url, imageView);
			DownloadedDrawable downloadedDrawable = new DownloadedDrawable(task);
			imageView.setImageDrawable(downloadedDrawable);
			task.execute(url);
//		} 
	}
//   //url�� �̹��� �ٿ�(ĳ�� ���X)
//	public static Bitmap getdownloadimg(String url)
//	{
//		Bitmap cachedImage = mImageCache.get(url);
//		
//		if(cachedImage != null)
//		{
//			Log.e("cachedImage != null","cachedImage != null");
//			return cachedImage;
//		}
//		else
//		{
//			Log.e("cachedImage == null","cachedImage == null");
//			
//			if(mImageCache.size() > IMGAE_CACHE_LIMIT_SIZE)
//			{
//				mImageCache.clear();
//			}
//			
//			return ImageDownloaderTask.downloadBitmap(url);
//		}
//	}
	
	public static Bitmap getBitmap(String url)
	{
		Bitmap cachedImage = mImageCache.get(url);
		if(cachedImage != null)
		{
			Log.e("getBitmap != null","getBitmap != null");
			return mImageCache.get(url);
		}
		else
		{
			Log.e("getBitmap == null","getBitmap == null");
			return ImageDownloaderTask.downloadBitmap(url);
		}
	}
	
	private static boolean cancelPotentialDownload(String url, ImageView imageView)
	{
		ImageDownloaderTask bitmapDownloaderTask = getBitmapDownloaderTask(imageView);

		if(bitmapDownloaderTask != null)
		{
			String bitmapUrl = bitmapDownloaderTask.url;
			if((bitmapUrl == null) || (!bitmapUrl.equals(url)))
			{
				bitmapDownloaderTask.cancel(true);
			}
			else
			{
				return false;
			}
		}
		return true;
	}

	private static ImageDownloaderTask getBitmapDownloaderTask(ImageView imageView)
	{
		if(imageView != null)
		{
			Drawable drawable = imageView.getDrawable();
			if(drawable instanceof DownloadedDrawable)
			{
				DownloadedDrawable downloadedDrawable = (DownloadedDrawable) drawable;
				return downloadedDrawable.getBitmapDownloaderTask();
			}
		}
		return null;
	}

	static class DownloadedDrawable extends ColorDrawable
	{
		private final WeakReference<ImageDownloaderTask> bitmapDownloaderTaskReference;

		public DownloadedDrawable(ImageDownloaderTask bitmapDownloaderTask)
		{
			super(Color.TRANSPARENT);
			bitmapDownloaderTaskReference = new WeakReference<ImageDownloaderTask>(bitmapDownloaderTask);
		}

		public ImageDownloaderTask getBitmapDownloaderTask()
		{
			return bitmapDownloaderTaskReference.get();
		}
	}
}
