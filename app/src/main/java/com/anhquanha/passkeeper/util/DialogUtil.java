package com.anhquanha.passkeeper.util;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.anhquanha.passkeeper.R;

/**
 * Created by anhquan.ha on 3/6/2018.
 */

public class DialogUtil {
    private static Dialog progressDialog;
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

    public static void showLogOutConfirmationDialog(Context context){
        final Dialog dialog = new Dialog(context);
        dialog.setContentView(R.layout.custom_dialog);

        TextView titleDialog = dialog.findViewById(R.id.title_dialog);
        TextView okButtonDialog = dialog.findViewById(R.id.yesTv);
        TextView cancelButtonDialog = dialog.findViewById(R.id.noTv);

        titleDialog.setText(StringUtil.getStringResource(R.string.log_out_confirm));
        okButtonDialog.setOnClickListener(v->{

        });
        cancelButtonDialog.setOnClickListener(v->{
            dialog.dismiss();
        });
        dialog.setCancelable(true);
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();
    }

}
