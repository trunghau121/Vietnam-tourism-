package com.example.hau.dulichviet.ui.dialog;

import android.app.ProgressDialog;
import android.content.Context;


/**
 * Created by TRUNGHAU on 5/1/2016.
 */
public abstract class DialogFacetory {

    public static ProgressDialog createLoadingDialog(Context context, String message) {
        ProgressDialog dialog = new ProgressDialog(context);
        dialog.setMessage(message);
        dialog.setCancelable(false);
        return dialog;
    }
}
