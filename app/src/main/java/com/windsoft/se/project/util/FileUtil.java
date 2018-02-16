package com.windsoft.se.project.util;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Environment;
import android.os.storage.StorageManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;

import com.windsoft.se.project.Manifest;
import com.windsoft.se.project.model.series.Series;
import com.windsoft.se.project.view.SplashScreenActivity;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import static android.Manifest.permission.RECORD_AUDIO;
import static android.media.MediaRecorder.VideoSource.CAMERA;
import static android.support.v4.content.PermissionChecker.checkSelfPermission;
import static com.windsoft.se.project.util.Constant.JPG;
import static com.windsoft.se.project.util.Constant.PNG;

/**
 * Created by GersonSales on 2/16/2018.
 */

public class FileUtil{

    private static SharedPreferences sharedPreferences;
    private static Context context;
    private static Activity activity;

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

    public static void setSharedPreferences(SharedPreferences sharedPreferences) {
        FileUtil.sharedPreferences = sharedPreferences;
    }

    public static boolean isPermissionGranted() {
        return PackageManager.PERMISSION_GRANTED == checkSelfPermission(context, android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
                && PackageManager.PERMISSION_GRANTED == checkSelfPermission(context, android.Manifest.permission.READ_EXTERNAL_STORAGE);
    }

    public static void requestPermission() {
        String[] permissions = {android.Manifest.permission.WRITE_EXTERNAL_STORAGE, android.Manifest.permission.READ_EXTERNAL_STORAGE};
        ActivityCompat.requestPermissions(activity, permissions, 0);
    }


    public static void setContext(Context context) {
        FileUtil.context = context;

    }

    public static void setActivity(Activity activity) {
        FileUtil.activity = activity;
    }

    public static File createImageFile(String fileName) {
        return createMediaFile(context.getExternalFilesDir(Environment.DIRECTORY_PICTURES),fileName,  PNG);
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
        }
    }
}
