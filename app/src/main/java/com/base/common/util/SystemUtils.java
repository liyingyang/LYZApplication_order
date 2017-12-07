package com.base.common.util;

import android.os.Build;

/**
 * @author lyy
 * @created 2017/12/6
 * @editor lyy
 * @edited 2017/12/6
 * @type 控制器()
 * @layout
 */
public class SystemUtils {
    /**
     * ICE_CREAM_SANDWICH_MR1 (Android 4.1) +
     */
    public static boolean v15() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH_MR1;
    }

    /**
     * JELLY_BEAN (Android 4.2) +
     */
    public static boolean v16() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN;
    }

    /**
     * JELLY_BEAN_MR1
     */
    public static boolean v17() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1;
    }

    /**
     * KITKAT (Android 4.4) +
     */
    public static boolean v19() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT;
    }

    /**
     * LOLLIPOP (Android 5.0) +
     * @return
     */
    public static boolean v21() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP;
    }

    /**
     * M (Android 6.0) +
     * @return
     */
    public static boolean v23() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.M;
    }

    /**
     * N (Android 7.0) +
     * @return
     */
    public static boolean v24_N() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.N;
    }

    /**
     * N (Android 7.1) +
     * @return
     */
    public static boolean v25_N_MR1() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.N_MR1;
    }
}
