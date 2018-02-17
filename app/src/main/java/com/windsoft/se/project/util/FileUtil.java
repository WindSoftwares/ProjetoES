package com.windsoft.se.project.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v4.content.FileProvider;

import com.windsoft.se.project.model.series.Series;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.windsoft.se.project.util.Constant.AUTHORITY;
import static com.windsoft.se.project.util.Constant.PNG;

/**
 * Created by GersonSales on 2/16/2018.
 */

public class FileUtil{


    private static Map<String, Bitmap> thumbnailMap;
    private static File externalFileDir;

    public static void persistSeries(String tag, List<Series> series)  {
        try {
            File file = new File(tag);
            FileOutputStream outputStream = new FileOutputStream(file);
            BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(outputStream);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(bufferedOutputStream);

            objectOutputStream.writeObject(series);

            objectOutputStream.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }





    @NonNull
    public static List<Series> readSeriesList(String tag) {
        List<Series> result = new ArrayList<>();

        try {
            File file = new File(tag);
            if (!(file.exists())) {
                return result;
            }

            FileInputStream fileInputStream = new FileInputStream(file);
            BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStream);
            ObjectInputStream objectInputStream = new ObjectInputStream(bufferedInputStream);

            Object object = objectInputStream.readObject();
            if (object == null) {
                return new ArrayList<>();
            }

            if (object instanceof List) {
                result.addAll((List)object);
            }
            objectInputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    private static Map<String, Bitmap> getAllThumbnails() {
        Map<String, Bitmap> result = new HashMap<>();

        File dir = externalFileDir;
        if (dir == null) return result;
        for (File file : dir.listFiles()) {
            result.put(file.getName(), getBitmapFromFile(file.getAbsolutePath()));
        }

        return result;
    }

    private static File createImageFile(String fileName) {
        return createMediaFile(externalFileDir,fileName,  PNG);
    }




    private static File createMediaFile(File storageDirectory, String fileName, String extension)  {
        File mediaFile = null;
        try {

            mediaFile = File.createTempFile(
                    fileName,  /* prefix */
                    "." + extension,         /* suffix */
                    storageDirectory      /* directory */
            );

        } catch (IOException e) {
            e.printStackTrace();
        }
        return mediaFile;
    }

    public static void persistBitmap(String fileName, Bitmap bitmap) {
        FileOutputStream out = null;
        try {
            out = new FileOutputStream(createImageFile(fileName));
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, out); // bmp is your Bitmap instance
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
            thumbnailMap = null;
        }
    }

    public static Uri getUriFromFile(Context context, File file) {
        return FileProvider.getUriForFile(context, AUTHORITY, file);
    }

    private static Bitmap getBitmapFromFile(String path) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inPreferredConfig = Bitmap.Config.ARGB_8888;
        return BitmapFactory.decodeFile(path, options);
    }


    public static Bitmap getSeriesThumbnail(String seriesName) {
        if (thumbnailMap == null) {
            thumbnailMap = getAllThumbnails();
        }

        for (String path: thumbnailMap.keySet()) {
            if (path.contains(seriesName) ) {
                return thumbnailMap.get(path);
            }
        }
        return null;
    }

    public static void setExternalFileDir(File externalFileDir) {
        FileUtil.externalFileDir = externalFileDir;
    }
}
