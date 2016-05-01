package com.example.hau.dulichviet.ui.dialog;

import android.app.ProgressDialog;
import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hau.dulichviet.R;


/**
 * Created by TRUNGHAU on 5/1/2016.
 */
public abstract class DialogFactory {

    public static ProgressDialog createLoadingDialog(Context context, String message) {
        ProgressDialog dialog = new ProgressDialog(context);
        dialog.setMessage(message);
        dialog.setCancelable(false);
        return dialog;
    }

    public static void showTopPopup(Context context, String message ,String messageSecond) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View layout = inflater.inflate(R.layout.view_top_popup,
                null);
        TextView text = (TextView) layout.findViewById(R.id.textTop);
        TextView text1 = (TextView) layout.findViewById(R.id.textSecondTop);
        text.setText(message);
        text1.setText(messageSecond);
        Toast toast = new Toast(context);
        toast.setGravity(Gravity.TOP | Gravity.FILL_HORIZONTAL, 0, 0);
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.setView(layout);
        toast.show();
    }

    public static void showBottomPopup(Context context, String message) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View layout = inflater.inflate(R.layout.view_bottom_popup,
                null);
        TextView text = (TextView) layout.findViewById(R.id.text);
        text.setText(message);
        Toast toast = new Toast(context);
        toast.setGravity(Gravity.BOTTOM | Gravity.FILL_HORIZONTAL, 0, 0);
        toast.setDuration(Toast.LENGTH_LONG);
        toast.setView(layout);
        toast.show();
    }
}
