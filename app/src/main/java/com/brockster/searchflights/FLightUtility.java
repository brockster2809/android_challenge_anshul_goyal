package com.brockster.searchflights;

import android.app.ProgressDialog;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by user on 01-04-2017.
 */

public class FLightUtility {

    private static ProgressDialog mProgressDialog;
    private static Context context;


    public static void showProgressBar(Context context, String msg) {
        hideProgressBar();
        if (mProgressDialog != null) {
            if (mProgressDialog.isShowing()) {
                mProgressDialog.setMessage(msg);
                return;
            } else {
                mProgressDialog.setMessage(msg);
                mProgressDialog.show();
            }
        } else {
            try {
                mProgressDialog = new ProgressDialog(context);
                mProgressDialog.setProgressStyle(android.R.style.Widget_ProgressBar_Large);
                mProgressDialog.setMessage(msg);
                //mProgressDialog.setIndeterminateDrawable(context.getDrawable(R.drawable.notify_truck_coming));
                mProgressDialog.setIndeterminate(true);
                mProgressDialog.setCancelable(false);
                mProgressDialog.show();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void hideProgressBar() {
        Log.d("progress", "call hide");
        if (mProgressDialog != null && mProgressDialog.isShowing()) {
            try {
                mProgressDialog.dismiss();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        mProgressDialog = null;
    }

    public static void setContext(Context applicationContext) {
        context = applicationContext;
    }

}
