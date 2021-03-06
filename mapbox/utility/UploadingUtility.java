package com.example.vmann.mapbox.utility;

import android.app.ProgressDialog;
import android.content.Context;
import android.net.Uri;
import android.util.Log;

import com.augugrumi.ghioca.MyApplication;
import com.augugrumi.ghioca.R;
import com.augugrumi.ghioca.UploadingDialog;
import com.augugrumi.ghioca.listener.UploadingListener;

import io.filepicker.Filepicker;
import io.filepicker.FilepickerCallback;
import io.filepicker.models.FPFile;


public class UploadingUtility {
    public static final String MY_API_KEY =
            MyApplication.getAppContext().getString(R.string.FILESTACK_KEY);

    static {
        // TODO -> put in more appropriate place
        Filepicker.setKey(MY_API_KEY);
    }

    public static void uploadToServer(String path, final Context context, final UploadingListener listener) {
        Log.i("provaupload", "5 path:" + path);
        final ProgressDialog uploadFragment;
        uploadFragment = new UploadingDialog(context);
        uploadFragment.show();
        listener.onStart();
        Filepicker.uploadLocalFile(Uri.parse(path), context, new FilepickerCallback() {
            @Override
            public void onFileUploadSuccess(final FPFile fpFile) {
                Log.i("provaupload", "6" + fpFile.getUrl().toString());
                Log.i("provaupload", "7 url->" + fpFile.getUrl().toString());
                uploadFragment.dismiss();
                listener.onFinish(fpFile.getUrl());
            }

            @Override
            public void onFileUploadError(Throwable error) {
                Log.i("provaupload", "8 " + error.toString());
                uploadFragment.dismiss();
                listener.onFailure(error);
            }

            @Override
            public void onFileUploadProgress(Uri uri, float progress) {
                Log.i("provaupload", "--progress" + progress);
                listener.onProgressUpdate(Math.round(progress));
            }
        });
    }
}
