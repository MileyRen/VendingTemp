package com.page.vendingtemp.CommonUtil;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

/**
 * 获取网络图片
 * Created by Miley_Ren on 2017/7/2.
 */

public class BitmapHelper {
    public static Bitmap getBitmapTask(final String path)throws Exception {
        FutureTask<Bitmap> task = new FutureTask<Bitmap>(
                new Callable<Bitmap>() {
                    @Override
                    public Bitmap call() throws Exception {
                        return getBitmap(path);
                    }
                }
        );
        new Thread(task).start();
        return  task.get();
    }
    public static Bitmap getBitmap(String imageUrl) {
        Bitmap mBitmap = null;
        try {
            URL url = new URL(imageUrl);
           InputStream is =  url.openStream();
           // HttpURLConnection conn = (HttpURLConnection) url.openConnection();
           // InputStream is = conn.getInputStream();
            mBitmap = BitmapFactory.decodeStream(is);

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return mBitmap;
    }
}
