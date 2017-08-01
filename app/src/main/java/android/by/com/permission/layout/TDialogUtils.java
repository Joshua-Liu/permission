package android.by.com.permission.layout;

import android.content.Context;
import android.util.DisplayMetrics;
import android.util.Log;

import java.lang.reflect.Field;

/**
 * Created by WEI ZHENG on 15/11/12.
 */

public class TDialogUtils {

    /**
     * getFullScreenAppHeight
     *
     * @return app height
     */
    private static int mFSAppHeight = 0;
    private static int mFSAppWidth = 0;

    public static int getFullScreenAppHeight(Context context) {
        int screenHeight = 0, statusBarHeight = 0;

        if (mFSAppHeight > 0) {
            return mFSAppHeight;
        }

        DisplayMetrics metric = context.getResources().getDisplayMetrics();
        screenHeight = metric.heightPixels;
        mFSAppWidth = metric.widthPixels;

        Class<?> c = null;
        Object obj = null;
        Field field = null;
        int x = 0;
        try {
            c = Class.forName("com.android.internal.R$dimen");
            obj = c.newInstance();
            field = c.getField("status_bar_height");
            x = Integer.parseInt(field.get(obj).toString());
            statusBarHeight = context.getResources().getDimensionPixelSize(x);
        } catch (Exception e1) {
            Log.e("TDialogUtils", "get status bar height fail");
            e1.printStackTrace();
        }

        mFSAppHeight = screenHeight - statusBarHeight;
        mFSAppHeight = mFSAppHeight < 0 ? 0 : mFSAppHeight;
        return mFSAppHeight;
    }

    public static int getFullScreenAppWidth(Context context) {
        if (mFSAppWidth > 0) {
            return mFSAppWidth;
        }
        DisplayMetrics metric = context.getResources().getDisplayMetrics();
        mFSAppWidth = metric.widthPixels;
        mFSAppWidth = mFSAppWidth < 0 ? 0 : mFSAppWidth;
        return mFSAppWidth;
    }
}
