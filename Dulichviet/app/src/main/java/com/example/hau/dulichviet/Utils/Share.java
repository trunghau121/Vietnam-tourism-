package com.example.hau.dulichviet.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.widget.Toast;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.share.Sharer;
import com.facebook.share.model.ShareLinkContent;
import com.facebook.share.widget.ShareDialog;

/**
 * Created by HAU on 12/3/2015.
 */
public class Share {
    private Context mContext;
    private Activity activity;
    CallbackManager callbackManager;
    ShareDialog shareDialog;

    public Share(Context mContext,Activity activity, CallbackManager callbackManager) {
        this.mContext = mContext;
        this.activity=activity;
        FacebookSdk.sdkInitialize(mContext);
        callbackManager = CallbackManager.Factory.create();
        this.callbackManager=callbackManager;
    }

    public static void shareURL(String url, Context mContext){
        Intent share =new Intent(Intent.ACTION_SEND);
        share.setType("text/plain");
        share.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        share.putExtra(Intent.EXTRA_SUBJECT,"Du Lịch Việt");
        share.putExtra(Intent.EXTRA_TEXT,url);
        mContext.startActivity(share);
    }
    public void createFaceBookShare() {
        shareDialog = new ShareDialog(activity);
        // this part is optional
        shareDialog.registerCallback(callbackManager, new FacebookCallback<Sharer.Result>() {
            @Override
            public void onSuccess(Sharer.Result result) {
                if (result.getPostId().length() > 0) {
                    Toast.makeText(mContext, "Chia sẻ thành công !", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onCancel() {

            }

            @Override
            public void onError(FacebookException error) {
                Toast.makeText(mContext, "Có lỗi xảy ra không chia sẻ được !", Toast.LENGTH_LONG).show();
            }
        });
    }
    public void shareFaceBook(String url){
        ShareLinkContent content =new ShareLinkContent.Builder().setContentUrl(Uri.parse(url)).build();
        if (ShareDialog.canShow(ShareLinkContent.class)) {
            shareDialog.show(content);
        }
    }
}
