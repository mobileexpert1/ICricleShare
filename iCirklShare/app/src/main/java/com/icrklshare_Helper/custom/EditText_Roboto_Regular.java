package com.icrklshare_Helper.custom;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.EditText;

import com.icrklshare_Helper.util.Fonts;

public class EditText_Roboto_Regular extends EditText {
    public EditText_Roboto_Regular(Context context) {
        super(context);
        init();
    }

    public EditText_Roboto_Regular(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public EditText_Roboto_Regular(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    private void init() {
        if (isInEditMode()) {
            return;
        }

        setTypeface(Fonts.setRobotoRegular(getContext()));

    }
}