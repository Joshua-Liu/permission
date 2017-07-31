package android.by.com.permission.permission;

import android.accessibilityservice.AccessibilityService;
import android.by.com.permission.MyApplication;
import android.by.com.permission.R;
import android.by.com.permission.constant.GuideConst;
import android.by.com.permission.layout.WindowManagerLayout;
import android.by.com.permission.util.LayoutParaUtil;
import android.by.com.permission.util.PackageUtil;
import android.by.com.permission.util.PrefUtil;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.graphics.PixelFormat;
import android.os.Build;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.accessibility.AccessibilityEvent;

import android.by.com.permission.model.ModelManager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Created by frankyang on 12/3/15.
 */
public abstract class IPermissionGuideStrategy {

    private static final String PREF_KEY_PREFIX = "setted_";
    public static final String PREF_KEY_DONE_PREFIX = "done_setted_";

    public static final String PACKAGE_NAME_TEXT = "packageName";
    final public static int NONE_TYPE = -1;
    final public static int TUTORIAL_TYPE = 0;
    final public static int INAPP_TYPE = 1;
    final public static int START_UP_TYPE = 2;
    final public static int TOAST_TYPE = 3;
    final public static int SECOND_TYPE = 4; //进阶权限;
    final public static int INAPP_SECOND_GUIDE_TYPE = 5;
    final public static int ACCESSIBLITY_UPGRADE = 6;

    public static Map<String, Integer> mainTextMap = new HashMap<String, Integer>() {
        {
            put(GuideConst.AUTOBOOT_PERMISSION, R.string.autoboot_permission_main);
            put(GuideConst.TRUST_APPLICATION_PERMISSION_MIUI5, R.string.trust_application_permission_main_miui5);
            put(GuideConst.TRUST_APPLICATION_PERMISSION_MIUI6, R.string.trust_application_permission_main_miui6);
            put(GuideConst.TRUST_APPLICATION_PERMISSION, R.string.trust_application_permission_main);
            put(GuideConst.DATA_PERMISSION, R.string.data_permission_main);
            put(GuideConst.TOAST_PERMISSION, R.string.toast_permission_main);
            put(GuideConst.TRUST_APPLICATION_PERMISSION_COOLPAD, R.string.application_permission);
            put(GuideConst.BACKGROUND_PROTECT_PERMISSION, R.string.background_protect_permission_main);
            put(GuideConst.MIUI_BACKGROUND_PROTECT_PERMISSION, R.string.background_protect_permission_miui);
            put(GuideConst.VIVO_BACKGROUND_PROTECT_PERMISSION, R.string.vivo_background_protect_permission_main);
            put(GuideConst.HUAWEI_V4_BACKGROUND_PROTECT_PERMISSION, R.string.huawei_v4_background_protection_main);
            put(GuideConst.MEIZU_WHITE_LIST, R.string.vivo_background_protect_permission_main);
            put(GuideConst.WHITE_LIST, R.string.white_list_permission_main);
            put(GuideConst.ZTE_WHITE_LIST, R.string.white_list_permission_main_zte);
            put(GuideConst.SMARTISION_BACKGROUND_PROTECT_PERMISSION, R.string.smartision_background_protect_permission_main);
            put(GuideConst.OPEN_NOTIFICATION, R.string.permission_open_notification);
            put(GuideConst.CALL_PHONE_PERMISSION, R.string.permission_call_phone);
            put(GuideConst.VIVO_BACKGROUND_USE_CPU_PERMISSION, R.string.permission_vivo_backgroup_cpu_protect_title);
            put(GuideConst.BACKGROUND_FROZEN_PERMISSION, R.string.background_frozen_permission_main);
            put(GuideConst.OPPO_BACKGROUND_FROZEN_PERMISSION, R.string.oppo_background_frozen_permission_main);
            put(GuideConst.BACKGROUND_RUNNING_PERMISSION,R.string.oppo_background_runging_permission_main);
        }
    };

    public static Map<String, Integer> SUB_TEXT_MAP = new HashMap<String, Integer>(){
        {
            put(GuideConst.AUTOBOOT_PERMISSION, R.string.autoboot_permission_sub_title);
            put(GuideConst.TRUST_APPLICATION_PERMISSION_MIUI5, R.string.trust_application_permission_sub_title);
            put(GuideConst.TRUST_APPLICATION_PERMISSION, R.string.trust_application_permission_sub_title);
            put(GuideConst.TOAST_PERMISSION, R.string.toast_permission_sub_title);
            put(GuideConst.BACKGROUND_PROTECT_PERMISSION, R.string.background_protect_permission_sub_title);
            put(GuideConst.MIUI_BACKGROUND_PROTECT_PERMISSION,R.string.background_protect_permission_miui_sub_title);
            put(GuideConst.VIVO_BACKGROUND_PROTECT_PERMISSION,R.string.background_protect_permission_sub_title);
            put(GuideConst.OPEN_NOTIFICATION, R.string.permission_open_notification_sub_title);
            put(GuideConst.CALL_PHONE_PERMISSION, R.string.permission_call_phone_sub_title);
            put(GuideConst.VIVO_BACKGROUND_USE_CPU_PERMISSION, R.string.permission_vivo_backgroup_cpu_protect_sub_title);
            put(GuideConst.BACKGROUND_FROZEN_PERMISSION, R.string.background_frozen_permission_sub_title);
            put(GuideConst.OPPO_BACKGROUND_FROZEN_PERMISSION, R.string.background_frozen_permission_oppo_sub_title);
            put(GuideConst.BACKGROUND_RUNNING_PERMISSION, R.string.background_frozen_permission_oppo_sub_title);
        }
    };

    public static Map<String, Integer> statusMap = new HashMap<String, Integer>() {
        {
            put(GuideConst.AUTOBOOT_PERMISSION, GuideConst.AUTOBOOT_PERMISSION_DONE);
            put(GuideConst.DEFAULT_PERMISSION, GuideConst.AUTOBOOT_PERMISSION_DONE);
            put(GuideConst.DATA_PERMISSION, GuideConst.DATA_PERMISSION_DONE);
            put(GuideConst.TRUST_APPLICATION_PERMISSION, GuideConst.TRUST_APPLICATION_PERMISSION_DONE);
            put(GuideConst.TRUST_APPLICATION_PERMISSION_MIUI5, GuideConst.TRUST_APPLICATION_PERMISSION_DONE);
            put(GuideConst.TRUST_APPLICATION_PERMISSION_MIUI6, GuideConst.TRUST_APPLICATION_PERMISSION_DONE);
            put(GuideConst.TOAST_PERMISSION, GuideConst.TOAST_PERMISSION_DONE);
            put(GuideConst.TRUST_APPLICATION_PERMISSION_COOLPAD, GuideConst.TRUST_APPLICATION_PERMISSION_DONE);
            put(GuideConst.BACKGROUND_PROTECT_PERMISSION, GuideConst.BACKGROUND_PROTECT_PERMISSION_DONE);
            put(GuideConst.MIUI_BACKGROUND_PROTECT_PERMISSION, GuideConst.BACKGROUND_PROTECT_PERMISSION_DONE);
            put(GuideConst.VIVO_BACKGROUND_PROTECT_PERMISSION, GuideConst.BACKGROUND_PROTECT_PERMISSION_DONE);
            put(GuideConst.HUAWEI_V4_BACKGROUND_PROTECT_PERMISSION, GuideConst.BACKGROUND_PROTECT_PERMISSION_DONE);
            put(GuideConst.MEIZU_WHITE_LIST, GuideConst.WHITE_LIST_PERMISSION_DONE);
            put(GuideConst.ZTE_WHITE_LIST, GuideConst.WHITE_LIST_PERMISSION_DONE);
            put(GuideConst.WHITE_LIST, GuideConst.WHITE_LIST_PERMISSION_DONE);
            put(GuideConst.SMARTISION_BACKGROUND_PROTECT_PERMISSION, GuideConst.BACKGROUND_PROTECT_PERMISSION_DONE);
            put(GuideConst.OPEN_NOTIFICATION, GuideConst.OPEN_NOTIFICATION_DONE);
            put(GuideConst.CALL_PHONE_PERMISSION, GuideConst.CALL_PHONE_PERMISSION_DONE);
            put(GuideConst.VIVO_BACKGROUND_USE_CPU_PERMISSION, GuideConst.BACKGROUNP_USE_CPU_PERMISSION_DONE);
            put(GuideConst.BACKGROUND_FROZEN_PERMISSION, GuideConst.BACKGOUND_FROZEN_PERMISSION_DONE);
            put(GuideConst.OPPO_BACKGROUND_FROZEN_PERMISSION, GuideConst.BACKGOUND_FROZEN_PERMISSION_DONE);
        }
    };

    public static Map<String, String> PERMISSION_NAME_MAP = new HashMap<String, String>() {
        {
            put(GuideConst.TRUST_APPLICATION_PERMISSION_MIUI5, GuideConst.TRUST_APPLICATION_PERMISSION);
            put(GuideConst.VIVO_BACKGROUND_PROTECT_PERMISSION, GuideConst.BACKGROUND_PROTECT_PERMISSION);
            put(GuideConst.MIUI_BACKGROUND_PROTECT_PERMISSION, GuideConst.BACKGROUND_PROTECT_PERMISSION);
            put(GuideConst.OPPO_BACKGROUND_FROZEN_PERMISSION, GuideConst.BACKGROUND_FROZEN_PERMISSION);
        }
    };

    public Context mContext = null;
    private WindowManager mWindowManager;

    public IPermissionGuideStrategy(Context context) {
        this.mContext = context;
    }

    //是否支持第二次引导，即inapp引导;
    public boolean supportSecondGuide() {
        if (supportGuide() == false) {
            Log.i("ycsss", "do not support guide, so not support second guide");
            return false;
        }

        ArrayList<String> permissons = getPermissionList(IPermissionGuideStrategy.INAPP_SECOND_GUIDE_TYPE);
        Log.i("ycsss", "support second guide: " + (permissons.size() > 0));
        return permissons.size() > 0;
    }

    protected void actionReadCalllog() {
        PrefUtil.setKey(PREF_KEY_PREFIX + GuideConst.READ_CALLOG_PERMISSION, true);
    }

    protected void actionReadContact() {
        PrefUtil.setKey(PREF_KEY_PREFIX + GuideConst.READ_CONTACT_PERMISSION, true);
    }

    public boolean supportGuideExport() {
        return supportGuide();
    }

    protected boolean supportGuide() {
        return true;
    }

    protected void actionTrustApplicationPermission(boolean showGuide) {
        PrefUtil.setKey(PREF_KEY_PREFIX + GuideConst.TRUST_APPLICATION_PERMISSION, true);
    }

    protected void actionAutoBootPermission() {
        PrefUtil.setKey(PREF_KEY_PREFIX + GuideConst.AUTOBOOT_PERMISSION, true);
    }

    protected void actionToastPermission() {
        PrefUtil.setKey(PREF_KEY_PREFIX + GuideConst.TOAST_PERMISSION, true);
    }

    protected void actionDataPermission() {
    }

    protected void actionBackgroundUseCPUPermission(){
        PrefUtil.setKey(PREF_KEY_PREFIX + GuideConst.VIVO_BACKGROUND_USE_CPU_PERMISSION, true);
    }

    protected void actionOpenNotification() {
        PrefUtil.setKey(PREF_KEY_PREFIX + GuideConst.OPEN_NOTIFICATION, true);
    }

    protected void actionCallPhone() {
        PrefUtil.setKey(PREF_KEY_PREFIX + GuideConst.CALL_PHONE_PERMISSION, true);
    }

    protected void actionBackgroundPermisssion() {
        PrefUtil.setKey(PREF_KEY_PREFIX + GuideConst.BACKGROUND_PROTECT_PERMISSION, true);
    }

    protected void actionWhiteListPermisssion() {
        PrefUtil.setKey(PREF_KEY_PREFIX + GuideConst.WHITE_LIST, true);
    }

    protected void actionPermissionDeny(int type) {
    }

    protected void actionCallogOrContactPermission() {
    }

    protected void actionBackgroundFrozenPermission() {
        PrefUtil.setKey(PREF_KEY_PREFIX + GuideConst.BACKGROUND_FROZEN_PERMISSION, true);
    }

    protected void actionBackgroundRuningPermission() {
        PrefUtil.setKey(PREF_KEY_PREFIX + GuideConst.BACKGROUND_RUNNING_PERMISSION,true);
    }

    protected int getColor() {
        return mContext.getResources().getColor(R.color.highlight_color);
    }

    protected int getPressedColor() {
        return mContext.getResources().getColor(R.color.highlight_color);
    }

    //inapp二次引导出现的权限（第一次未点击过的权限 + 进阶权限）;
    protected ArrayList<String> getSecondGuidePermissionList() {
        ArrayList<String> ret = new ArrayList<>();
        if (supportGuide() == false) { //对于不支持权限引导的机型，返回空List;
            Log.i("ycsss", "not support guide, return empty second guide permission list");
            return ret;
        }

        ArrayList<String> basePermissions = getPermissionList(TUTORIAL_TYPE);
        boolean accessiblity = PackageUtil.isPackageInstalled(PackageUtil.OPPO_COLOROS_PERMISSION_SETTING_PACKAGE_NAMES);
        if(accessiblity){
            try {
                String display = Build.DISPLAY;
                String[] tmp = display.split("_");
                String displayVersion = tmp[tmp.length - 1];
                if (Integer.parseInt(displayVersion) >= 170500){
                    accessiblity = false;
                }
            } catch (Exception e) {
            }
        }
        for (String p : basePermissions) {
            String p2 = PERMISSION_NAME_MAP.containsKey(p) ? PERMISSION_NAME_MAP.get(p) : p;
            if (accessiblity && PrefUtil.getKeyBoolean(PREF_KEY_DONE_PREFIX + p2, false) == false) {
                ret.add(p);
            } else if (PrefUtil.getKeyBoolean(PREF_KEY_PREFIX + p2, false) == false) {
                ret.add(p);
            }
        }
        ArrayList<String> secondPermissions = getPermissionList(SECOND_TYPE);
        for (String p : secondPermissions) {
            ret.add(p);
        }
        return ret;
    }

    public ArrayList<String> getPermissionList(int type) {
        return null;
    }

    protected String getPermissionTitle() {
        return "";
    }

    protected String getPermissionTitle(String permission, int type) {
        return "";
    }

    protected PermissionGuideStepItem getPermissionGuideStepItem(String permission, int type) {
        return null;
    }

    public void generateButtonFunction(String type) {
        if (GuideConst.AUTOBOOT_PERMISSION.equals(type)) {
            actionAutoBootPermission();
        } else if (GuideConst.READ_CALLOG_PERMISSION.equals(type)){
            actionReadCalllog();
        } else if(GuideConst.READ_CONTACT_PERMISSION.equals(type)) {
            actionReadContact();
        }else if (GuideConst.TRUST_APPLICATION_PERMISSION.equals(type)
                || GuideConst.TRUST_APPLICATION_PERMISSION_MIUI5.equals(type)
                || GuideConst.TRUST_APPLICATION_PERMISSION_MIUI6.equals(type)
                || GuideConst.TRUST_APPLICATION_PERMISSION_COOLPAD.equals(type)) {
            actionTrustApplicationPermission(false);
        } else if (GuideConst.TOAST_PERMISSION.equals(type)) {
            actionToastPermission();
        } else if (GuideConst.DATA_PERMISSION.equals(type)) {
            actionDataPermission();
        } else if (GuideConst.BACKGROUND_PROTECT_PERMISSION.equals(type)
                || GuideConst.MIUI_BACKGROUND_PROTECT_PERMISSION.equals(type)
                || GuideConst.VIVO_BACKGROUND_PROTECT_PERMISSION.equals(type)
                || GuideConst.SMARTISION_BACKGROUND_PROTECT_PERMISSION.equals(type)
                || GuideConst.HUAWEI_V4_BACKGROUND_PROTECT_PERMISSION.equals(type)) {
            actionBackgroundPermisssion();
        } else if (GuideConst.MEIZU_WHITE_LIST.equals(type)
                || GuideConst.WHITE_LIST.equals(type)
                || GuideConst.ZTE_WHITE_LIST.equals(type)) {
            actionWhiteListPermisssion();
        } else if (GuideConst.OPEN_NOTIFICATION.equals(type)) {
            actionOpenNotification();
        } else if (GuideConst.CALL_PHONE_PERMISSION.equals(type)) {
            actionCallPhone();
        }else if(GuideConst.VIVO_BACKGROUND_USE_CPU_PERMISSION.equals(type)){
            actionBackgroundUseCPUPermission();
        } else if (GuideConst.BACKGROUND_FROZEN_PERMISSION.equals(type)
                || GuideConst.OPPO_BACKGROUND_FROZEN_PERMISSION.equals(type)) {
            actionBackgroundFrozenPermission();
        } else if(GuideConst.BACKGROUND_RUNNING_PERMISSION.equals(type)) {
            actionBackgroundRuningPermission();
        }
    }

    public boolean allOfTheGivenPermissionsSetted(List<String> permissions) {
        for (String permission : permissions) {
            if (GuideConst.AUTOBOOT_PERMISSION.equals(permission)) {
                permission = GuideConst.AUTOBOOT_PERMISSION;
            } else if (GuideConst.TRUST_APPLICATION_PERMISSION.equals(permission)
                    || GuideConst.TRUST_APPLICATION_PERMISSION_MIUI5.equals(permission)
                    || GuideConst.TRUST_APPLICATION_PERMISSION_MIUI6.equals(permission)
                    || GuideConst.TRUST_APPLICATION_PERMISSION_COOLPAD.equals(permission)) {
                permission = GuideConst.TRUST_APPLICATION_PERMISSION;
            } else if (GuideConst.TOAST_PERMISSION.equals(permission)) {
                permission = GuideConst.TOAST_PERMISSION;
            } else if (GuideConst.BACKGROUND_PROTECT_PERMISSION.equals(permission)
                    || GuideConst.MIUI_BACKGROUND_PROTECT_PERMISSION.equals(permission)
                    || GuideConst.VIVO_BACKGROUND_PROTECT_PERMISSION.equals(permission)
                    || GuideConst.SMARTISION_BACKGROUND_PROTECT_PERMISSION.equals(permission)
                    || GuideConst.HUAWEI_V4_BACKGROUND_PROTECT_PERMISSION.equals(permission)) {
                permission = GuideConst.BACKGROUND_PROTECT_PERMISSION;
            } else if (GuideConst.MEIZU_WHITE_LIST.equals(permission)
                    || GuideConst.WHITE_LIST.equals(permission)) {
                permission = GuideConst.WHITE_LIST;
            } else if (GuideConst.BACKGROUND_FROZEN_PERMISSION.equals(permission)
                    || GuideConst.OPPO_BACKGROUND_FROZEN_PERMISSION.equals(permission)) {
                permission = GuideConst.BACKGROUND_FROZEN_PERMISSION;
            }
            if (!PrefUtil.getKeyBoolean(PREF_KEY_PREFIX + permission, false))
                return false;
        }
        return true;
    }

    public boolean getPermissionDone(String key) {
        return PrefUtil.getKeyBoolean(PREF_KEY_DONE_PREFIX + key, false);
    }

    public boolean getPermissionClick(String key) {
        return PrefUtil.getKeyBoolean(PREF_KEY_PREFIX + key, false);
    }

    public boolean enablePermission(List<String> permissions) {
        if(PackageUtil.isAutoPermission()) {
            return allPermissionEnable(permissions);
        } else {
            return allManaulPermissionSetted(permissions);
        }
    }

    public boolean allManaulPermissionSetted(List<String> permissions) {
        if (permissions == null || permissions.size() ==0) {
            return true;
        } else {
            for (String permission :permissions) {
                boolean done = getPermissionClick(permission);
                if (!done) {
                    return done;
                }
            }
        }
        return true;
    }
    public boolean allPermissionEnable(List<String> permissions) {
        if(Build.VERSION.SDK_INT < 16) {
            return true;
        }
        if (permissions == null || permissions.size() ==0) {
            return true;
        } else {
            for (String permission :permissions) {
                boolean done = getPermissionDone(permission);
                if (!done) {
                    return done;
                }
            }
        }
        return true;
    }

    public void clickAutoBootPermission() {
        actionAutoBootPermission();
    }

    public void clickPermissionDeny(int type) {
        actionPermissionDeny(type);
    }

    public void clickCalllogOrContactPermission() {
        actionCallogOrContactPermission();
    }

    public void clickBackgroundPermission() {
        actionBackgroundPermisssion();
    }

    public void configAccessbility(AccessibilityService sevice) {

    }

    public void handleAccessbilityEvent(AccessibilityEvent event, AccessibilityService sevice) {

    }

    protected ViewGroup popupWindow(View view, final Context context, final BroadcastReceiver receiver) {
        WindowManager.LayoutParams windowManagerLayoutParams = new WindowManager.LayoutParams();
        windowManagerLayoutParams.type = WindowManager.LayoutParams.TYPE_TOAST;//LayoutParams.TYPE_PHONE;
        windowManagerLayoutParams.format = PixelFormat.RGBA_8888;
        windowManagerLayoutParams.flags = WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL
                | WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM;
        windowManagerLayoutParams.gravity = Gravity.TOP | Gravity.LEFT;
        windowManagerLayoutParams.x = 0;
        windowManagerLayoutParams.y = 0;
        windowManagerLayoutParams.height = WindowManager.LayoutParams.MATCH_PARENT;
        windowManagerLayoutParams.width = WindowManager.LayoutParams.MATCH_PARENT;
        final WindowManagerLayout layout = new WindowManagerLayout(mContext);
        layout.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_BACK) {
                    try {
                        getWindowManager().removeView(layout);
                        context.unregisterReceiver(receiver);
                    } catch (Exception e){}
                    return true;
                }
                return false;
            }
        });
        layout.addView(view, LayoutParaUtil.fullPara());
        view.findViewById(R.id.btn_setup).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    getWindowManager().removeView(layout);
                    context.unregisterReceiver(receiver);
                } catch (Exception e) {
                }
            }
        });
        getWindowManager().addView(layout, windowManagerLayoutParams);
        return layout;
    }

    //有些机型无法通过启OuterPermissionActivity来展示引导蒙板，这时可以尝试用WindowManager，但不保证有效，具体看机型;
    protected void popupWindow(final View view, final Context context, final long delayInMilli) {
        ModelManager.getInst().getHandler().postDelayed(new Runnable() {
            @Override
            public void run() {
                final WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
                final WindowManagerLayout layout = new WindowManagerLayout(context);
                layout.addView(view, new WindowManager.LayoutParams(
                        WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT));
                layout.setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View v, MotionEvent event) {
                        try {
                            windowManager.removeView(layout);
                        } catch (Exception e) {
                        }
                        return false;
                    }
                });

                WindowManager.LayoutParams wmParams = new WindowManager.LayoutParams();
                wmParams.format = PixelFormat.TRANSLUCENT;
                wmParams.width = WindowManager.LayoutParams.MATCH_PARENT;
                wmParams.height = WindowManager.LayoutParams.MATCH_PARENT;
                wmParams.gravity = Gravity.CENTER;
                windowManager.addView(layout, wmParams);
            }
        }, delayInMilli);
    }

    private WindowManager getWindowManager() {
        if (mWindowManager == null) {
            mWindowManager = (WindowManager) MyApplication.getAppContext()
                    .getSystemService(Context.WINDOW_SERVICE);
        }
        return mWindowManager;
    }
}

