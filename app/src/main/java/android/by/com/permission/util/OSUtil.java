package android.by.com.permission.util;

import android.by.com.permission.constant.Constants;
import android.os.Build;
import android.text.TextUtils;
import android.util.Log;
import android.view.WindowManager;


import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by junhao.zhang on 3/31/15.
 */
public class OSUtil {

    private static final String TAG = "OSUtil";

    private static int sType = -1;

    private static int sTakeoverToastType = -1;

    private static String sOSName;

    private static String MIUI_PREFIX = "miui";
    public static final String VERSION_NAME_MIUI_V5 = "V5";
    public static final String VERSION_NAME_MIUI_V6 = "V6";
    public static final String VERSION_NAME_MIUI_V7 = "V7";
    public static final String VERSION_NAME_MIUI_V8 = "V8";

    public static final int VERSION_TYPE_MIUI_NONE = 0;
    public static final int VERSION_TYPE_MIUI_V5 = 1;
    public static final int VERSION_TYPE_MIUI_V6 = 2;

    /**
     * get proper toast type for toast
     * @return window type of toast
     */
    public static int getProperToastType() {
        Log.e(TAG, "manufacturer = "+ Build.MANUFACTURER+ "model = "+Build.MODEL);
        if (sType <= 0) {
            synchronized (OSUtil.class) {
                if (sType <= 0) {
                    if ((isMiui() && !isMiuiV5())
                            || Build.MANUFACTURER.equalsIgnoreCase(Constants.SMARTISAN) || (Build.MANUFACTURER.equals(Constants.MANUFACTURER_OPPO) && Build.VERSION.SDK_INT >= 20)) {
                        sType = WindowManager.LayoutParams.TYPE_TOAST;
                    } else {
                        sType = WindowManager.LayoutParams.TYPE_SYSTEM_ERROR;
                    }
                }
            }
        }
        return sType;
    }


    /**
     * get proper toast type for taking over system dialer
     * @return
     */
    public static int getProperTakeoverToastType() {
        if (sTakeoverToastType <= 0) {
            synchronized (OSUtil.class) {
                if (sTakeoverToastType <= 0) {
                    if ((isMiui() && !isMiuiV5())
                            || Build.MANUFACTURER.equalsIgnoreCase(Constants.SMARTISAN) ||
                            (Build.MANUFACTURER.equals(Constants.MANUFACTURER_OPPO) && Build.VERSION.SDK_INT >= 20)) {
                        sTakeoverToastType = WindowManager.LayoutParams.TYPE_TOAST;
                    } else {
                        sTakeoverToastType = WindowManager.LayoutParams.TYPE_SYSTEM_ERROR;
                    }
                }
            }
        }
        return sTakeoverToastType;
    }

    /**
     * indicate if the device's ROM is MIUI
     * @return
     */
    public static boolean isMiui() {
        boolean result = false;
        try {
            Class clazz = Class.forName("miui.os.Build");
            if (clazz != null) {
                result = true;
            }
        } catch (ClassNotFoundException e) {

        }
        return result;
    }

    /**
     * indicate if the device's ROM is MIUI
     * @return
     */
    public static boolean isHuawei() {
        return PackageUtil.isPackageInstalled(PackageUtil.HUAWEI_PHONE_MANAGER);
    }


    /**
     * get name of the operating system if there is. Otherwise, return null.
     * @return
     */
    public static String getOSName() {
        if (sOSName == null) {
            synchronized (OSUtil.class) {
                if (sOSName == null) {
                    String result = "";
                    if (isMiui()) {
                        String miuiVersion = Build.VERSION.INCREMENTAL;
                        if (!TextUtils.isEmpty(miuiVersion)) {
                            int i = 0;
                            for (i = 0;i < miuiVersion.length();i++) {
                                char character = miuiVersion.charAt(i);
                                boolean isDigit = character >= '0' && character <= '9';
                                if (isDigit) {
                                    break;
                                }
                            }
                            miuiVersion = miuiVersion.substring(i);
                        }
                        result = MIUI_PREFIX + "_" + miuiVersion;
                    } else {
                        try {
                            Class clazz = Class.forName("android.os.SystemProperties");
                            Method method = clazz.getMethod("get", String.class);
                            Object resultObj = method.invoke(null, "ro.build.version.emui");
                            if (resultObj != null && resultObj instanceof String) {
                                result = (String)resultObj;
                            }
                        } catch (ClassNotFoundException e) {

                        } catch (NoSuchMethodException e) {

                        } catch (IllegalAccessException e) {

                        } catch (InvocationTargetException e) {

                        }
                    }
                    sOSName = result;
                }
            }
        }
        Log.e(TAG, "getOSName %s"+ sOSName);
        return sOSName;
    }

    /**
     * determine whether this os is HongMi.
     * It does not need to call {@link #isMiui()} before.
     * @return
     */
    public static boolean isHongMi() {
        boolean result = false;
        try {
            Class clazz = Class.forName("miui.os.Build");
            Field field = clazz.getField("IS_HONGMI");
            result = field.getBoolean(null);
            Field[] fs = clazz.getFields();
//            if (TLog.DBG) {
//                for(Field fd: fs) {
//                    TLog.d(TAG, "fd.name " + fd.getName());
//                }
//            }
        } catch (ClassNotFoundException e) {
        } catch (NoSuchFieldException e) {
        } catch (IllegalAccessException e) {
        }
        Log.d(TAG, "isHongMi " + result);
        return result;
    }
    /**
     * detect whether current miui os is miui v5.
     * It does not need to call {@link #isMiui()} before.
     * @return true if current os is miui V5.
     */
    public static boolean isMiuiV5() {
        return isMiui()
               && VERSION_NAME_MIUI_V5.equalsIgnoreCase(getMiuiVersionName());
    }

    /**
     * detect whether current miui os is miui V6.
     * It does not need to call {@link #isMiui()} before.
     * @return true if current os is miui V6.
     */
    public static boolean isMiuiV6() {
        return isMiui()
               && VERSION_NAME_MIUI_V6.equalsIgnoreCase(getMiuiVersionName());
    }

    public static boolean isMiuiV7() {
        return isMiui()
                && VERSION_NAME_MIUI_V7.equalsIgnoreCase(getMiuiVersionName());
    }

    public static boolean isMiuiV8() {
        return isMiui()
                && VERSION_NAME_MIUI_V8.equalsIgnoreCase(getMiuiVersionName());
    }


    public static int getMiuiVersionType() {
        String verName = getMiuiVersionName();
        int verType = VERSION_TYPE_MIUI_NONE;
        if (VERSION_NAME_MIUI_V5.equalsIgnoreCase(verName)) {
            verType = VERSION_TYPE_MIUI_V5;
        } else if (VERSION_NAME_MIUI_V6.equalsIgnoreCase(verName)) {
            verType = VERSION_TYPE_MIUI_V6;
        }
        return verType;
    }

    private static String getMiuiVersionName(){
        String verName = null;

        try {
            Class clazz = Class.forName("android.os.SystemProperties");
            Method method = clazz.getMethod("get", String.class);
            Object resultObj = method.invoke(null, "ro.miui.ui.version.name");
            if (resultObj != null && resultObj instanceof String) {
                verName = (String)resultObj;
            }
        } catch (ClassNotFoundException e) {

        } catch (NoSuchMethodException e) {

        } catch (IllegalAccessException e) {

        } catch (InvocationTargetException e) {

        }
        Log.d(TAG, "verName " + verName);
        return verName == null ? null : verName.trim();
    }


    public static boolean isAliYunOs() {
        try {
            Class clazz = Class.forName("android.os.SystemProperties");
            Method method = clazz.getMethod("get", String.class);
            Object resultObj = method.invoke(null, "ro.yunos.version");
            if (resultObj != null && resultObj instanceof String) {
                String version = (String) resultObj;
                return !TextUtils.isEmpty(version);
            }
        } catch (Exception e) {

        }
        return false;
    }

    public static String getVivoVersionName(){
        String verName = null;

        try {
            Class clazz = Class.forName("android.os.SystemProperties");
            Method method = clazz.getMethod("get", String.class);
            Object resultObj = method.invoke(null, "ro.vivo.product.version");
            if (resultObj != null && resultObj instanceof String) {
                verName = (String)resultObj;
            }
        } catch (ClassNotFoundException e) {

        } catch (NoSuchMethodException e) {

        } catch (IllegalAccessException e) {

        } catch (InvocationTargetException e) {

        }
        Log.d(TAG, "verName " + verName);
        return verName == null ? "" : verName.trim();
    }

    public static boolean isHuaweiAndroid6() {
        if (isHuawei() && Build.VERSION.SDK_INT > 22) {
            return true;
        }
        return false;
    }

}
