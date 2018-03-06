package com.anhquanha.passkeeper.util;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ProgressBar;

import com.anhquanha.passkeeper.R;

/**
 * Created by anhquan.ha on 3/6/2018.
 */

public class DialogUtil {
    private static Dialog progressDialog;
    //private static DialogUtil dialogUtil;

//    private DialogUtil(Context context){
//        progressDialog = new Dialog(context);
//    }
//
//    public static DialogUtil getInstance(Context context){
//        if(dialogUtil == null){
//            dialogUtil = new DialogUtil(context);
//        }
//        return dialogUtil;
//    }

    public static void showProgressDialog(Context context){
        if(progressDialog != null && progressDialog.isShowing())
            return;
        try {
            progressDialog = new Dialog(context);
            progressDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            progressDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
            progressDialog.setContentView(R.layout.dialog_loading);
            progressDialog.getWindow().getAttributes().width = WindowManager.LayoutParams.WRAP_CONTENT;
            ProgressBar progressBar = (ProgressBar) progressDialog.findViewById(R.id.progressBar);
            progressBar.getIndeterminateDrawable().setColorFilter(context.getResources().getColor(R.color.colorPink), android.graphics.PorterDuff.Mode.SRC_ATOP);

            progressDialog.setCanceledOnTouchOutside(false);
            progressDialog.setCancelable(true);
            progressDialog.show();
        }
        catch (NullPointerException e) {
            Log.e("Done", "showProgressDialog() encountered NPE.");
        }
    }

    public static void hideProgressDialog(){
        if(progressDialog == null)
            return;
        progressDialog.dismiss();
        progressDialog = null;
    }
}
