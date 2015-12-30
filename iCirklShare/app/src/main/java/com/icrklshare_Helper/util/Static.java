package com.icrklshare_Helper.util;

import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;

/**
 * Created by root on 29/12/15.
 */
public class Static {



    public static boolean isValidEmail(String email) {
        return !TextUtils.isEmpty(email) && android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }


}
