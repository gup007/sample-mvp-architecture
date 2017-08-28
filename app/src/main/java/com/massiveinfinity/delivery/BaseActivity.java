package com.massiveinfinity.delivery;

import android.app.ProgressDialog;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;

/**
 * Created by SurvivoR on 7/31/2017.
 * {@link BaseActivity}
 */

public class BaseActivity extends AppCompatActivity {

    private ProgressDialog mProcessDialog;

    protected void showLoadingDialog() {
        if (isFinishing()) {
            return;
        }
        if (mProcessDialog == null) {
            mProcessDialog = ProgressDialog.show(this, null, null);
        }
        mProcessDialog.show();
    }

    protected void hideLoadingDialog() {
        if (isFinishing()) {
            return;
        }
        if (mProcessDialog != null && mProcessDialog.isShowing()) {
            mProcessDialog.dismiss();
        }
    }

    protected void showSnackbar(String message, View view) {
        showSnackbar(message, view, null, null);
    }

    protected void showSnackbar(String message, View view, String action, View.OnClickListener listener) {
        if (TextUtils.isEmpty(message) || view == null) {
            return;
        }
        Snackbar snackbar = Snackbar.make(view, message, Snackbar.LENGTH_SHORT);
        if(!TextUtils.isEmpty(action) && listener != null){
            snackbar.setAction(action, listener);
        }
        snackbar.show();
    }
}
