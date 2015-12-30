package com.icrklshare_Helper.util;

import android.app.Activity;

/**
 * Created by root on 28/12/15.
 */
public class FinishAnimation {

    public static void overidePendingTransition(Activity activity) {
        activity.overridePendingTransition(0, 0);
    }


    public static void overidePendingTransitionSwitchActivity(Activity activity) {
        activity.overridePendingTransition(android.support.v7.appcompat.R.anim.abc_fade_in, android.support.v7.appcompat.R.anim.abc_fade_out);
    }




}
