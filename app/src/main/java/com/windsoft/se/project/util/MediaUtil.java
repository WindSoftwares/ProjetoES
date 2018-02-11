package com.windsoft.se.project.util;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.media.ThumbnailUtils;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Environment;
import android.os.NetworkOnMainThreadException;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.ExecutionException;

/**
 * Created by GersonSales on 1/28/2018.
 */

public class MediaUtil {
    public static Bitmap loadImageFromInternet(String url) {

        final Bitmap[] result = {null};
            Thread network = new Thread(() -> {
                try {
                    InputStream inputStream = (InputStream) new URL(url).getContent();
                    result[0] =  BitmapFactory.decodeStream(inputStream);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });

            network.start();
            return result[0];
    }


    public static class NetworkAccess extends AsyncTask<String, Void, Bitmap> {
        @Override
        protected Bitmap doInBackground(String... src) {
            try {
                URL url = new URL(src[0]);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setDoInput(true);
                connection.connect();
                InputStream input = connection.getInputStream();
                Bitmap result = BitmapFactory.decodeStream(input);
                input.close();
                connection.disconnect();
                return result;
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
    }

    public static Bitmap getBitmapFromURL2(String url){
        try {
            return new NetworkAccess().execute(url).get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        return BitmapFactory.decodeStream(null);

    }

    public static Bitmap getBitmapFromURL(String src) {
        try {
            FileOutputStream out = null;
            Bitmap result = new NetworkAccess().execute(src).get();
            try {
                String path = Environment.getExternalStorageDirectory().toString();
                File file = new File(path + "/bitmap" + ".jpg");
                out = new FileOutputStream(file);
                result.copy(result.getConfig(), true)
                        .compress(Bitmap.CompressFormat.PNG, 100, out); // bmp is your Bitmap instance
                // PNG is a lossless format, the compression factor (100) is ignored
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    if (out != null) {
                        out.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            return result;
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return null;
    }


    public static Bitmap getBitmapFromDrawable(Drawable drawable) {
        if (drawable instanceof BitmapDrawable) {
            return ((BitmapDrawable)drawable).getBitmap();
        }

        Bitmap bitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
        drawable.draw(canvas);

        return bitmap;
    }
}
