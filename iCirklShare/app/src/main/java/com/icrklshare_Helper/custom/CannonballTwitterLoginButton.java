package com.icrklshare_Helper.custom;

/**
 * Created by root on 2/11/15.
 */

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;

import com.icirklshare.R;
import com.twitter.sdk.android.core.identity.TwitterLoginButton;
public class CannonballTwitterLoginButton extends TwitterLoginButton {
    public CannonballTwitterLoginButton(Context context) {
        super(context);
        init();
    }

    public CannonballTwitterLoginButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CannonballTwitterLoginButton(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    private void init() {
        if (isInEditMode()){
            return;
        }

        setCompoundDrawables(null, null, null, null);
        setBackgroundResource(R.drawable.twttr_btn);
        setText("");

    }

}