package android.by.com.permission.permission;

import android.accessibilityservice.AccessibilityService;
import android.accessibilityservice.AccessibilityServiceInfo;
import android.annotation.TargetApi;
import android.by.com.permission.R;
import android.by.com.permission.constant.GuideConst;
import android.by.com.permission.model.ModelManager;
import android.by.com.permission.pref.PrefKeys;
import android.by.com.permission.util.PackageUtil;
import android.by.com.permission.util.PrefUtil;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;
import android.util.Log;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;



import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static android.accessibilityservice.AccessibilityService.GLOBAL_ACTION_BACK;

/**
 * Created by frankyang on 12/7/15.
 *
 * 通过手里有的测试机发现，尝试打开"com.miui.permcenter.permissions.AppPermissionsEditorActivity"的话有几个情况，
 * 正式版会打开"com.miui.permcenter.permissions.AppPermissionsEditorActivity" 或者 "com.miui.permcenter.permissions.RealAppPermissionsEditorActivity"，
 * 6.9打头的机器是开发版本，会打开“com.android.settings.applications.InstalledAppDetails”即前一级界面
 */
public class MiuiV6PermissionGuideStrategy extends IPermissionGuideStrategy {

    private static final String APP_PERMISSION_ACTIVITY_NAME = "com.miui.permcenter.permissions.PermissionsEditorActivity";
    private static final String APP_V813_PERMISSION_ACTIVITY_NAME = "com.miui.permcenter.permissions.AppPermissionsEditorActivity";
    private static final String MIUI_SECERITYENTER = "com.miui.securitycenter";

    private final String TAG = "MiuiV6";
    private HashMap<String, Object> accessisbilityMap = new HashMap<String, Object>();


    private enum VERSION {
        COMMON,
        SPECIAL,
        SPECIAL_2,
    };

    private VERSION mVersion;
    private boolean mAutoGuide;

    public MiuiV6PermissionGuideStrategy(Context mContext, boolean auto) {
        super(mContext);
        String miuiVersion = Build.VERSION.INCREMENTAL;
        mAutoGuide = auto;
        try {
            String versionName = PackageUtil.getVersionName("com.miui.securitycenter");
            if (versionName.startsWith("2.0") || versionName.startsWith("2.1") || versionName.startsWith("2.2")){
                mVersion = VERSION.SPECIAL_2;
                return;
            } else if (versionName.startsWith("1.9") ) {
                mVersion = VERSION.SPECIAL;
                return;
            }
        }catch (Exception e){

        }


        if (miuiVersion.startsWith("6.9.29") ){
            mVersion = VERSION.SPECIAL_2;
        } else if (miuiVersion.startsWith("6.9") || miuiVersion.startsWith("V8.1.5") || miuiVersion.startsWith("V8.1.3")) {
            mVersion = VERSION.SPECIAL;
        } else if (miuiVersion.startsWith("V8.1.1") || miuiVersion.startsWith("V8.2") || miuiVersion.startsWith("7.1") ) {
            mVersion = VERSION.SPECIAL_2;
        } else {
            mVersion = VERSION.COMMON;
        }
    }

    private String getMiuiVersionName(){
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
        return verName == null ? null : verName.trim();
    }

    @Override
    protected void actionReadCalllog() {
        super.actionReadCalllog();
        try {

            final Intent intent = new Intent(mContext, OuterTwoStepPermissionActivity.class);
            if (mVersion == VERSION.COMMON) {
                Intent sysIntent = new Intent();
                sysIntent.putExtra("extra_pkgname", mContext.getPackageName());
                sysIntent.setClassName(GuideConst.MIUI_V6_PERMISSION_PACKAGE_NAME, GuideConst.MIUI_V6_APP_PERMISSION_ACTIVITY_NAME);
                mContext.startActivity(sysIntent);
                intent.putExtra(OuterTwoStepPermissionActivity.GUIDE_TEXT_ROW_ONE, mContext.getString(R.string.permission_miui_v6_readcalllog_step_1));
                intent.putExtra(OuterTwoStepPermissionActivity.GUIDE_TEXT_ROW_TWO, mContext.getString(R.string.permission_miui_v6_readcalllog_step_2));
                intent.putExtra(OuterTwoStepPermissionActivity.GUIDE_IMG_ROW_ONE, R.drawable.permission_miui_v6_readcalllog_step_1);
                intent.putExtra(OuterTwoStepPermissionActivity.GUIDE_IMG_ROW_TWO, R.drawable.permission_miui_vcommon_readcalllog_step_2);
                intent.putExtra(OuterTwoStepPermissionActivity.GUIDE_NEED_GESTURE_MARGIN_LEFT_TWO, R.dimen.permission_oppo_coloros_trust_gesture_maring_left_step2);
                ModelManager.getInst().getHandler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mContext.startActivity(intent);
                    }
                }, 300);
            } else if (mVersion == VERSION.SPECIAL) {
                Intent sysIntent = new Intent();
                sysIntent.putExtra("extra_pkgname", mContext.getPackageName());
                sysIntent.setClassName(GuideConst.MIUI_V6_PERMISSION_PACKAGE_NAME,
                        APP_V813_PERMISSION_ACTIVITY_NAME);
                mContext.startActivity(sysIntent);
                intent.putExtra(OuterTwoStepPermissionActivity.GUIDE_TEXT_ROW_ONE, mContext.getString(R.string.permission_miui_v813_readcallog_contact_step_1));
                intent.putExtra(OuterTwoStepPermissionActivity.GUIDE_TEXT_ROW_TWO, mContext.getString(R.string.permission_miui_v813_readcallog_step_2));
                intent.putExtra(OuterTwoStepPermissionActivity.GUIDE_TEXT_ROW_THREE, mContext.getString(R.string.permission_miui_v813_readcallog_contact_step_3));
                intent.putExtra(OuterTwoStepPermissionActivity.GUIDE_IMG_ROW_ONE, R.drawable.permission_miui_v813_readcalllog_contact_step_1);
                intent.putExtra(OuterTwoStepPermissionActivity.GUIDE_IMG_ROW_TWO, R.drawable.permission_miui_v6_readcalllog_step_1);
                intent.putExtra(OuterTwoStepPermissionActivity.GUIDE_IMG_ROW_THREE, R.drawable.permission_miui_v6_readcalllog_step_2);

                ModelManager.getInst().getHandler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mContext.startActivity(intent);
                    }
                }, PrefUtil.getKeyBoolean(PrefKeys.MIUI_V6_PERMISSION_CLICK, false) ? 800 : 1500);
                PrefUtil.setKey(PrefKeys.MIUI_V6_PERMISSION_CLICK, true);
            } else {
                Intent sysIntent = new Intent();
                sysIntent.putExtra("extra_pkgname", mContext.getPackageName());
                sysIntent.setClassName(GuideConst.MIUI_V6_PERMISSION_PACKAGE_NAME,
                        APP_PERMISSION_ACTIVITY_NAME);
                mContext.startActivity(sysIntent);
                intent.putExtra(OuterTwoStepPermissionActivity.GUIDE_TEXT_ROW_ONE, mContext.getString(R.string.permission_miui_v6_readcalllog_step_1));
                intent.putExtra(OuterTwoStepPermissionActivity.GUIDE_TEXT_ROW_TWO, mContext.getString(R.string.permission_miui_v6_readcalllog_step_2));
                intent.putExtra(OuterTwoStepPermissionActivity.GUIDE_IMG_ROW_ONE, R.drawable.permission_miui_v6_readcalllog_step_1);
                intent.putExtra(OuterTwoStepPermissionActivity.GUIDE_IMG_ROW_TWO, R.drawable.permission_miui_v6_readcalllog_step_2);
                intent.putExtra(OuterTwoStepPermissionActivity.GUIDE_NEED_GESTURE_MARGIN_LEFT_TWO, R.dimen.permission_oppo_coloros_trust_gesture_maring_left_step2);

                ModelManager.getInst().getHandler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mContext.startActivity(intent);
                    }
                }, 300);
            }

        } catch (ActivityNotFoundException e) {
            Log.e(TAG,e.getMessage());
        }
    }

    @Override
    protected void actionReadContact() {
        super.actionReadContact();
        try {
            final Intent intent = new Intent(mContext, OuterTwoStepPermissionActivity.class);
            if (mVersion == VERSION.COMMON) {
                Intent sysIntent = new Intent();
                sysIntent.putExtra("extra_pkgname", mContext.getPackageName());
                sysIntent.setClassName(GuideConst.MIUI_V6_PERMISSION_PACKAGE_NAME, GuideConst.MIUI_V6_APP_PERMISSION_ACTIVITY_NAME);
                mContext.startActivity(sysIntent);
                intent.putExtra(OuterTwoStepPermissionActivity.GUIDE_TEXT_ROW_ONE, mContext.getString(R.string.permission_miui_v6_readcontact_step_1));
                intent.putExtra(OuterTwoStepPermissionActivity.GUIDE_TEXT_ROW_TWO, mContext.getString(R.string.permission_miui_v6_readcontact_step_2));
                intent.putExtra(OuterTwoStepPermissionActivity.GUIDE_IMG_ROW_ONE, R.drawable.permission_miui_v6_readcontact_step_1);
                intent.putExtra(OuterTwoStepPermissionActivity.GUIDE_IMG_ROW_TWO, R.drawable.permission_miui_vcommon_readcalllog_step_2);
                intent.putExtra(OuterTwoStepPermissionActivity.GUIDE_NEED_GESTURE_MARGIN_LEFT_TWO, R.dimen.permission_oppo_coloros_trust_gesture_maring_left_step2);

                ModelManager.getInst().getHandler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mContext.startActivity(intent);
                    }
                }, 300);
            } else if (mVersion == VERSION.SPECIAL) {
                Intent sysIntent = new Intent();
                sysIntent.putExtra("extra_pkgname", mContext.getPackageName());
                sysIntent.setClassName(GuideConst.MIUI_V6_PERMISSION_PACKAGE_NAME,
                        APP_V813_PERMISSION_ACTIVITY_NAME);
                mContext.startActivity(sysIntent);
                intent.putExtra(OuterTwoStepPermissionActivity.GUIDE_TEXT_ROW_ONE, mContext.getString(R.string.permission_miui_v813_readcallog_contact_step_1));
                intent.putExtra(OuterTwoStepPermissionActivity.GUIDE_TEXT_ROW_TWO, mContext.getString(R.string.permission_miui_v813_readcaontact_step_2));
                intent.putExtra(OuterTwoStepPermissionActivity.GUIDE_TEXT_ROW_THREE, mContext.getString(R.string.permission_miui_v813_readcallog_contact_step_3));
                intent.putExtra(OuterTwoStepPermissionActivity.GUIDE_IMG_ROW_ONE, R.drawable.permission_miui_v813_readcalllog_contact_step_1);
                intent.putExtra(OuterTwoStepPermissionActivity.GUIDE_IMG_ROW_TWO, R.drawable.permission_miui_v6_readcontact_step_1);
                intent.putExtra(OuterTwoStepPermissionActivity.GUIDE_IMG_ROW_THREE, R.drawable.permission_miui_v6_readcalllog_step_2);

                ModelManager.getInst().getHandler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mContext.startActivity(intent);
                    }
                }, PrefUtil.getKeyBoolean(PrefKeys.MIUI_V6_PERMISSION_CLICK, false) ? 800 : 1500);
                PrefUtil.setKey(PrefKeys.MIUI_V6_PERMISSION_CLICK, true);
            } else {
                Intent sysIntent = new Intent();
                sysIntent.putExtra("extra_pkgname", mContext.getPackageName());
                sysIntent.setClassName(GuideConst.MIUI_V6_PERMISSION_PACKAGE_NAME,
                        APP_PERMISSION_ACTIVITY_NAME);
                mContext.startActivity(sysIntent);
                intent.putExtra(OuterTwoStepPermissionActivity.GUIDE_TEXT_ROW_ONE, mContext.getString(R.string.permission_miui_v6_readcontact_step_1));
                intent.putExtra(OuterTwoStepPermissionActivity.GUIDE_TEXT_ROW_TWO, mContext.getString(R.string.permission_miui_v6_readcontact_step_2));
                intent.putExtra(OuterTwoStepPermissionActivity.GUIDE_IMG_ROW_ONE, R.drawable.permission_miui_v6_readcontact_step_1);
                intent.putExtra(OuterTwoStepPermissionActivity.GUIDE_IMG_ROW_TWO, R.drawable.permission_miui_v6_readcalllog_step_2);
                intent.putExtra(OuterTwoStepPermissionActivity.GUIDE_NEED_GESTURE_MARGIN_LEFT_TWO, R.dimen.permission_oppo_coloros_trust_gesture_maring_left_step2);

                ModelManager.getInst().getHandler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mContext.startActivity(intent);
                    }
                }, 300);
            }


        } catch (ActivityNotFoundException e) {
            Log.e(TAG,e.getMessage().toString());
        }
    }
    @Override
    protected void actionTrustApplicationPermission(boolean showGuide) {
        super.actionTrustApplicationPermission(showGuide);
        try {
            Intent sysIntent = new Intent();
            sysIntent.putExtra("extra_pkgname", mContext.getPackageName());
            sysIntent.setClassName(GuideConst.MIUI_V6_PERMISSION_PACKAGE_NAME,
                    APP_PERMISSION_ACTIVITY_NAME);
            mContext.startActivity(sysIntent);

            if (!mAutoGuide) {
                final Intent guideIntent = new Intent(mContext, OuterPermissionActivity.class);
                guideIntent.putExtra(OuterPermissionActivity.VIEWSTUB_ID, R.layout.miui6_trustapplication_permission);
                ModelManager.getInst().getHandler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mContext.startActivity(guideIntent);
                    }
                }, 100);
            }
        } catch (ActivityNotFoundException e) {
            Log.e(TAG,e.getMessage());
        }
    }

    @Override
    protected void actionAutoBootPermission() {
        super.actionAutoBootPermission();
        try {
            Intent sysIntent = new Intent();
            sysIntent.setClassName(GuideConst.MIUI_V6_PERMISSION_PACKAGE_NAME,
                    GuideConst.MIUI_V6_AUTO_START_PERMISSION_ACTIVITY_NAME);
            mContext.startActivity(sysIntent);

            if(!mAutoGuide) {
                final Intent guideIntent = new Intent(mContext, OuterPermissionActivity.class);
                guideIntent.putExtra(OuterPermissionActivity.VIEWSTUB_ID, R.layout.general_permission_guide_mask);
                guideIntent.putExtra(OuterPermissionActivity.GUIDEPIC_ID, R.drawable.miui6_permission_autoboot);
                guideIntent.putExtra(OuterPermissionActivity.GUIDE_HINTONE, mContext.getString(R.string.miui_permission_guide_template_one, mContext.getString(R.string.app_name)));
                guideIntent.putExtra(OuterPermissionActivity.GUIDE_HINTTWO, mContext.getString(R.string.miui_permission_action_switch_on));
                guideIntent.putExtra(OuterPermissionActivity.GUIDE_NO_BTN, true);
                ModelManager.getInst().getHandler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mContext.startActivity(guideIntent);
                    }
                }, 100);
            }

        } catch (ActivityNotFoundException e) {
            Log.e(TAG,e.getMessage());
        } catch (SecurityException e) {
            Log.e(TAG,e.getMessage());
        }
    }

    @Override
    protected void actionOpenNotification() {
        super.actionOpenNotification();
        try{
            Intent sysIntent = new Intent();
            sysIntent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
            Uri uri = Uri.fromParts("package",mContext.getPackageName(),null);
            sysIntent.setData(uri);
            mContext.startActivity(sysIntent);
            String miuiVersion = Build.VERSION.INCREMENTAL;
            if(!mAutoGuide) {
                final Intent guideIntent2 = new Intent(mContext,OuterTwoStepPermissionActivity.class);
                guideIntent2.putExtra(OuterTwoStepPermissionActivity.GUIDE_TEXT_ROW_ONE,mContext.getString(R.string.permission_miuiv6_open_notification_step1));
                guideIntent2.putExtra(OuterTwoStepPermissionActivity.GUIDE_TEXT_ROW_TWO,mContext.getString(R.string.permission_miuiv6_open_notification_step2));
                if (miuiVersion.startsWith("V6.4.1")) {
                    guideIntent2.putExtra(OuterTwoStepPermissionActivity.GUIDE_IMG_ROW_ONE, R.drawable.miui_8_permission_notification_01_2);
                    guideIntent2.putExtra(OuterTwoStepPermissionActivity.GUIDE_IMG_ROW_TWO, R.drawable.miui_8_permission_notification_02_2);
                }else {
                    guideIntent2.putExtra(OuterTwoStepPermissionActivity.GUIDE_IMG_ROW_ONE, R.drawable.miui_8_permission_notification_01);
                    guideIntent2.putExtra(OuterTwoStepPermissionActivity.GUIDE_IMG_ROW_TWO, R.drawable.miui_8_permission_notification_02);
                    guideIntent2.putExtra(OuterTwoStepPermissionActivity.GUIDE_IMG_ROW_THREE, R.drawable.miui_8_permission_notification_03);
                }
                guideIntent2.putExtra(OuterTwoStepPermissionActivity.GUIDE_NEED_MARGIN_BOTTOM_TWO,6);
                ModelManager.getInst().getHandler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mContext.startActivity(guideIntent2);
                    }
                }, 100);

            }

        } catch (ActivityNotFoundException e) {
            Log.e(TAG,e.getMessage());
        } catch (SecurityException e) {
            Log.e(TAG,e.getMessage());
        }
    }

        @Override
    protected void actionToastPermission() {
        super.actionToastPermission();
        try {
            if (mVersion == VERSION.COMMON) {
                Intent sysIntent = new Intent();
                sysIntent.putExtra("extra_pkgname", mContext.getPackageName());
                sysIntent.setClassName(GuideConst.MIUI_V6_PERMISSION_PACKAGE_NAME, GuideConst.MIUI_V6_APP_PERMISSION_ACTIVITY_NAME);
                mContext.startActivity(sysIntent);
                if (!mAutoGuide) {
                    final Intent guideIntent = new Intent(mContext, OuterPermissionActivity.class);
                    guideIntent.putExtra(OuterPermissionActivity.VIEWSTUB_ID, R.layout.general_permission_guide_mask);
                    guideIntent.putExtra(OuterPermissionActivity.GUIDEPIC_ID, R.drawable.miui6_permission_toast);
                    guideIntent.putExtra(OuterPermissionActivity.GUIDE_HINTONE, mContext.getString(R.string.miui_permission_guide_toast));
                    guideIntent.putExtra(OuterPermissionActivity.GUIDE_HINTTWO, mContext.getString(R.string.miui_permission_guide_toast_2));
                    guideIntent.putExtra(OuterPermissionActivity.GUIDE_NO_BTN, true);
                    ModelManager.getInst().getHandler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            mContext.startActivity(guideIntent);
                        }
                    }, 100);
                }

            } else if (mVersion == VERSION.SPECIAL) {
                Log.i("ycsss", "miui special version");
                Intent sysIntent = new Intent();
                sysIntent.putExtra("extra_pkgname", mContext.getPackageName());
                sysIntent.setClassName(GuideConst.MIUI_V6_PERMISSION_PACKAGE_NAME, GuideConst.MIUI_V6_APP_PERMISSION_ACTIVITY_NAME);
                mContext.startActivity(sysIntent);

                if (!mAutoGuide) {
                    final Intent intent = new Intent(mContext, OuterTwoStepPermissionActivity.class);
                    intent.putExtra(OuterTwoStepPermissionActivity.GUIDE_TEXT_ROW_ONE, mContext.getString(R.string.permission_miuiv6_toast_step1_text));
                    intent.putExtra(OuterTwoStepPermissionActivity.GUIDE_TEXT_ROW_TWO, mContext.getString(R.string.permission_miuiv6_toast_step2_text));
                    intent.putExtra(OuterTwoStepPermissionActivity.GUIDE_IMG_ROW_ONE, R.drawable.permission_miui_6_step_1);
                    intent.putExtra(OuterTwoStepPermissionActivity.GUIDE_IMG_ROW_TWO, R.drawable.miui6_permission_toast);
                    intent.putExtra(OuterTwoStepPermissionActivity.GUIDE_NEED_GESTURE_TWO, true);
                    intent.putExtra(OuterTwoStepPermissionActivity.GUIDE_NEED_GESTURE_MARGIN_LEFT_TWO, R.dimen.permission_miui_v6_call_gesture_maring_left_step2);
                    ModelManager.getInst().getHandler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            mContext.startActivity(intent);
                        }
                    }, 300);
                }
            } else if (mVersion == VERSION.SPECIAL_2) {
                Log.i("ycsss", "miui special_2 version");
                Intent sysIntent = new Intent();
                sysIntent.putExtra("extra_pkgname", mContext.getPackageName());
                sysIntent.setClassName(GuideConst.MIUI_V6_PERMISSION_PACKAGE_NAME, APP_PERMISSION_ACTIVITY_NAME);
                mContext.startActivity(sysIntent);

                if (!mAutoGuide) {
                    final Intent intent = new Intent(mContext, OuterTwoStepPermissionActivity.class);
                    intent.putExtra(OuterTwoStepPermissionActivity.GUIDE_TEXT_ROW_ONE, mContext.getString(R.string.permission_miuiv6_toast_special_text));
                    intent.putExtra(OuterTwoStepPermissionActivity.GUIDE_IMG_ROW_ONE, R.drawable.miui6_permission_toast);
                    intent.putExtra(OuterTwoStepPermissionActivity.GUIDE_NEED_GESTURE_ONE, true);
                    intent.putExtra(OuterTwoStepPermissionActivity.GUIDE_NEED_GESTURE_MARGIN_LEFT_ONE, R.dimen.permission_miui_v6_call_gesture_maring_left_step2);
                    intent.putExtra(OuterTwoStepPermissionActivity.GUIDE_ROW_MARGIN_TOP_ONE, R.dimen.permission_miui_v6_call_row1_top_margin);
                    ModelManager.getInst().getHandler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            mContext.startActivity(intent);
                        }
                    }, 100);
                }
            }
        } catch (Exception e) {
        }
    }

    @Override
    protected void actionCallPhone() {
        super.actionCallPhone();
        try {
            if (mVersion == VERSION.COMMON) {
                final Intent sysIntent = new Intent();
                sysIntent.putExtra("extra_pkgname", mContext.getPackageName());
                sysIntent.setClassName(GuideConst.MIUI_V6_PERMISSION_PACKAGE_NAME, GuideConst.MIUI_V6_APP_PERMISSION_ACTIVITY_NAME);

                mContext.startActivity(sysIntent);

                if (!mAutoGuide) {

                    final Intent guideIntent = new Intent(mContext, OuterPermissionActivity.class);
                    guideIntent.putExtra(OuterPermissionActivity.VIEWSTUB_ID, R.layout.miui6_call_phone_permission);
                    ModelManager.getInst().getHandler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            mContext.startActivity(guideIntent);
                        }
                    }, 100);
                }
            } else if (mVersion == VERSION.SPECIAL) {
                Log.i("ycsss", "miui special version");
                Intent sysIntent = new Intent();
                sysIntent.putExtra("extra_pkgname", mContext.getPackageName());
                sysIntent.setClassName(GuideConst.MIUI_V6_PERMISSION_PACKAGE_NAME, GuideConst.MIUI_V6_APP_PERMISSION_ACTIVITY_NAME);
                mContext.startActivity(sysIntent);
                if (!mAutoGuide) {
                    final Intent intent = new Intent(mContext, OuterTwoStepPermissionActivity.class);
                    intent.putExtra(OuterTwoStepPermissionActivity.GUIDE_TEXT_ROW_ONE, mContext.getString(R.string.permission_miuiv6_toast_step1_text));
                    intent.putExtra(OuterTwoStepPermissionActivity.GUIDE_TEXT_ROW_TWO, mContext.getString(R.string.permission_miuiv6_call_phone_step2_text));
                    intent.putExtra(OuterTwoStepPermissionActivity.GUIDE_IMG_ROW_ONE, R.drawable.permission_miui_6_step_1);
                    intent.putExtra(OuterTwoStepPermissionActivity.GUIDE_IMG_ROW_TWO, R.drawable.miui6_permission_call);
                    intent.putExtra(OuterTwoStepPermissionActivity.GUIDE_NEED_GESTURE_TWO, true);
                    intent.putExtra(OuterTwoStepPermissionActivity.GUIDE_NEED_GESTURE_MARGIN_LEFT_TWO, R.dimen.permission_miui_v6_call_gesture_maring_left_step2);
                    ModelManager.getInst().getHandler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            mContext.startActivity(intent);
                        }
                    }, PrefUtil.getKeyBoolean(PrefKeys.MIUI_V6_PERMISSION_CLICK, false) ? 800 : 1500);
                    PrefUtil.setKey(PrefKeys.MIUI_V6_PERMISSION_CLICK, true);
                }
            } else if (mVersion == VERSION.SPECIAL_2) {
                Log.i("ycsss", "miui special_2 version");
                Intent sysIntent = new Intent();
                sysIntent.putExtra("extra_pkgname", mContext.getPackageName());
                sysIntent.setClassName(GuideConst.MIUI_V6_PERMISSION_PACKAGE_NAME, APP_PERMISSION_ACTIVITY_NAME);
                mContext.startActivity(sysIntent);

                if (!mAutoGuide) {
                    final Intent intent = new Intent(mContext, OuterTwoStepPermissionActivity.class);
                    intent.putExtra(OuterTwoStepPermissionActivity.GUIDE_TEXT_ROW_ONE, mContext.getString(R.string.permission_miuiv6_call_phone_special_text));
                    intent.putExtra(OuterTwoStepPermissionActivity.GUIDE_IMG_ROW_ONE, R.drawable.miui6_permission_call);
                    intent.putExtra(OuterTwoStepPermissionActivity.GUIDE_NEED_GESTURE_ONE, true);
                    intent.putExtra(OuterTwoStepPermissionActivity.GUIDE_NEED_GESTURE_MARGIN_LEFT_ONE, R.dimen.permission_miui_v6_call_gesture_maring_left_step2);
                    intent.putExtra(OuterTwoStepPermissionActivity.GUIDE_ROW_MARGIN_TOP_ONE, R.dimen.permission_miui_v6_call_row1_top_margin);
                    ModelManager.getInst().getHandler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            mContext.startActivity(intent);
                        }
                    }, 100);
                }
            }
        } catch (Exception e) {
            Log.i(TAG,"s4");
            e.printStackTrace();
        }
    }

    @Override
    protected void actionBackgroundPermisssion() {
        super.actionBackgroundPermisssion();
        Intent intent = new Intent(mContext, Miui8BackgroundPermissionAnimationActivity.class);
        mContext.startActivity(intent);
    }

    @Override
    protected void actionDataPermission() {
        super.actionDataPermission();
        //by修改，不知道作用
//        ModelManager.getInst().registerContentObserver(ModelManager.getContext(), true);
//        ModelManager.getInst().getSMSMessage().syncObsoleteSms();
    }

    @Override
    protected void actionPermissionDeny(int type) {
        if (GuideConst.CALL_PERMISSION != type
                &&  GuideConst.CALLLOG_PERMISSION != type
                && GuideConst.CONTACT_PERMISSON != type ) {
            throw new IllegalArgumentException();
        }
        try {
            Intent sysIntent = new Intent();
            sysIntent.putExtra("extra_pkgname", mContext.getPackageName());
            sysIntent.setClassName(GuideConst.MIUI_V6_PERMISSION_PACKAGE_NAME,
                    GuideConst.MIUI_V6_APP_PERMISSION_ACTIVITY_NAME);
            mContext.startActivity(sysIntent);

            if (!mAutoGuide) {
                Intent guideIntent = new Intent(mContext, OuterPermissionActivity.class);
                switch (type) {
                    case GuideConst.CALL_PERMISSION:
                        guideIntent.putExtra(OuterPermissionActivity.VIEWSTUB_ID, R.layout.miui6_trustapplication_permission);
                        break;
                    case GuideConst.CALLLOG_PERMISSION:
                        guideIntent.putExtra(OuterPermissionActivity.VIEWSTUB_ID, R.layout.general_permission_guide_mask);
                        guideIntent.putExtra(OuterPermissionActivity.GUIDEPIC_ID, R.drawable.miui_calllog_permission_guide_pic);
                        guideIntent.putExtra(OuterPermissionActivity.GUIDE_HINTONE, mContext.getString(R.string.miui_permission_guide_template_one, mContext.getString(R.string.deny_permission_calllog)));
                        guideIntent.putExtra(OuterPermissionActivity.GUIDE_HINTTWO, mContext.getString(R.string.miui_permission_action_set_allowed));
                        break;
                    case GuideConst.CONTACT_PERMISSON:
                        guideIntent.putExtra(OuterPermissionActivity.VIEWSTUB_ID, R.layout.general_permission_guide_mask);
                        guideIntent.putExtra(OuterPermissionActivity.GUIDEPIC_ID, R.drawable.miui_contact_permission_guide_pic);
                        guideIntent.putExtra(OuterPermissionActivity.GUIDE_HINTONE, mContext.getString(R.string.miui_permission_guide_template_one, mContext.getString(R.string.deny_permission_contact)));
                        guideIntent.putExtra(OuterPermissionActivity.GUIDE_HINTTWO, mContext.getString(R.string.miui_permission_action_set_allowed));
                        break;
                }
                mContext.startActivity(guideIntent);
            }
        } catch (ActivityNotFoundException e) {
            Log.e(TAG,e.getMessage());
        } catch (SecurityException e) {
            Log.e(TAG,e.getMessage());
        }
        super.actionPermissionDeny(type);
    }

    @Override
    protected void actionCallogOrContactPermission() {
        super.actionCallogOrContactPermission();
        try {
            Intent sysIntent = new Intent();
            sysIntent.putExtra("extra_pkgname", mContext.getPackageName());
            sysIntent.setClassName(GuideConst.MIUI_V6_PERMISSION_PACKAGE_NAME,
                    GuideConst.MIUI_V6_APP_PERMISSION_ACTIVITY_NAME);
            mContext.startActivity(sysIntent);

            if (!mAutoGuide) {
                final Intent guideIntent = new Intent(mContext, MiuiCalllogOrContactGuide.class);
                ModelManager.getInst().getHandler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mContext.startActivity(guideIntent);
                    }
                }, 200);
            }

        } catch (ActivityNotFoundException e) {
            Log.e(TAG,e.getMessage());
        }
    }

    @Override
    public ArrayList<String> getPermissionList(int type) {
        ArrayList<String> ret = new ArrayList<String>();
        Log.e(TAG,"type = "+type);
        if(type == ACCESSIBLITY_UPGRADE){
            ret.add(GuideConst.CALL_PHONE_PERMISSION);
            ret.add(GuideConst.AUTOBOOT_PERMISSION);
            ret.add(GuideConst.OPEN_NOTIFICATION);
            ret.add(GuideConst.TOAST_PERMISSION);

        }else if (type == IPermissionGuideStrategy.TUTORIAL_TYPE) {
            ret.add(GuideConst.CALL_PHONE_PERMISSION);
            ret.add(GuideConst.AUTOBOOT_PERMISSION);
            ret.add(GuideConst.OPEN_NOTIFICATION);
            ret.add(GuideConst.TOAST_PERMISSION);
        } else if (type == IPermissionGuideStrategy.INAPP_TYPE) {
            ret.add(GuideConst.AUTOBOOT_PERMISSION);
        } else if (type == IPermissionGuideStrategy.START_UP_TYPE) {
            ret.add(GuideConst.TRUST_APPLICATION_PERMISSION_MIUI6);
        } else if (type == IPermissionGuideStrategy.SECOND_TYPE) {
            //ret.add(GuideConst.MIUI_BACKGROUND_PROTECT_PERMISSION); //暂时不加;
        } else if (type == IPermissionGuideStrategy.INAPP_SECOND_GUIDE_TYPE) {
            //小米机型由旧版本升级上来不展示inapp引导;
            if (PrefUtil.getKeyBoolean(PrefKeys.INSTALL_IN, false) == false) {
                Log.i("ycsss", "upgrade user");
                return ret;
            } else {
                return super.getSecondGuidePermissionList();
            }
        }
        return ret;
    }

    @Override
    protected String getPermissionTitle() {
        return  ModelManager.getContext().getString(R.string.permission_guide_title_new, "MIUI");
    }

    @Override
    protected String getPermissionTitle(String permission, int type) {
        String os = "MIUI";
        if (type == IPermissionGuideStrategy.START_UP_TYPE) {
            return ModelManager.getContext().getString(R.string.permission_guide_title, os);
        } else if (type == IPermissionGuideStrategy.INAPP_TYPE) {
            return ModelManager.getContext().getString(R.string.important_permission_guide_title, os);
        } else if (type == IPermissionGuideStrategy.TUTORIAL_TYPE) {
            return ModelManager.getContext().getString(R.string.permission_guide_title_new, os);
        } else if (GuideConst.AUTOBOOT_PERMISSION.equals(permission)) {
            return ModelManager.getContext().getString(R.string.autoboot_permission_title);
        } else if (GuideConst.MIUI_BACKGROUND_PROTECT_PERMISSION.equals(permission)) {
            return ModelManager.getContext().getString(R.string.background_permission_title);
        } else if (GuideConst.TRUST_APPLICATION_PERMISSION_MIUI6.equals(permission)) {
            return ModelManager.getContext().getString(R.string.permission_title_call);
        } else if (type == IPermissionGuideStrategy.INAPP_SECOND_GUIDE_TYPE) {
            return mContext.getResources().getString(R.string.permission_second_guide_title);
        }

        return getPermissionTitle();
    }

    @Override
    protected PermissionGuideStepItem getPermissionGuideStepItem(String permission, int type) {
        PermissionGuideStepItem stepItem = null;
        if (GuideConst.TRUST_APPLICATION_PERMISSION_MIUI6.equals(permission)) {
            stepItem = new PermissionGuideStepItem(
                    type == IPermissionGuideStrategy.START_UP_TYPE
                            ? R.string.permission_trust_title_miuiv6
                            : type == IPermissionGuideStrategy.TUTORIAL_TYPE ? R.string.permission_trust_title_tutorial : -1,
                    new int[]{R.string.permission_trust_step_1_miuiv6, R.string.permission_trust_step_2_miuiv6},
                    new int[][]{new int[]{R.drawable.trust_miui6_01}, new int[]{R.drawable.trust_miui6_02}}
            );
        } else if (GuideConst.AUTOBOOT_PERMISSION.equals(permission)) {
            stepItem = new PermissionGuideStepItem(
                    type == IPermissionGuideStrategy.TUTORIAL_TYPE ? R.string.permission_others_title_tutorial : -1,
                    new int[]{R.string.autoboot_permission_hint},
                    new int[][]{new int[]{R.drawable.autoboot_miui6_01}}
            );
        } else if (GuideConst.MIUI_BACKGROUND_PROTECT_PERMISSION.equals(permission)) {
            stepItem = new PermissionGuideStepItem(
                    -1,
                    new int[]{R.string.background_protection_step_1_miui, R.string.background_protection_step_2_miui},
                    new int[][]{new int[]{R.drawable.background_protection_miui_01}, new int[]{R.drawable.background_protection_miui_02}}
            );
        }
        return stepItem;
    }

    @TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH)
    @Override
    public void handleAccessbilityEvent(AccessibilityEvent event, AccessibilityService service) {
        int eventType = event.getEventType();
        Log.d(TAG,"eventType = "+eventType);
        Log.e(TAG,"pkgname = "+event.getPackageName());
        Log.e(TAG,"clsname"+event.getClassName());
        if((eventType == AccessibilityEvent.TYPE_VIEW_SCROLLED
                || (eventType == AccessibilityEvent.TYPE_WINDOW_CONTENT_CHANGED )
                || eventType == AccessibilityEvent.TYPE_WINDOW_STATE_CHANGED)
            && event.getPackageName() != null && !event.getPackageName().equals("com.android.settings") ) {
            AccessibilityNodeInfo nodeInfo = service.getRootInActiveWindow();

            if(event.getPackageName().equals(GuideConst.MIUI_V6_PERMISSION_PACKAGE_NAME)) {
                if(nodeInfo == null) {
                    return;
                }
                List<AccessibilityNodeInfo> list = nodeInfo.findAccessibilityNodeInfosByText(mContext.getString(R.string.accessibility_permission_miuiv6_autoboot));
                if(list != null && list.size() > 0) {
                    Log.i(TAG,"case 3");
                    if(performSwitch(service, nodeInfo,
                            mContext.getString(R.string.accessiblity_permission_touchpal),
                            "miui_v6_autoboot_step1")) {
                        PrefUtil.setKey(IPermissionGuideStrategy.PREF_KEY_DONE_PREFIX + GuideConst.AUTOBOOT_PERMISSION, true);
                    }
                    return;
                }

                list = nodeInfo.findAccessibilityNodeInfosByText(
                        mContext.getString(R.string.accessiblity_permission_touchpal));
                if(list != null && list.size() > 0
                        && !accessisbilityMap.containsKey("miui_v6_callphone_step1")) {

                    Log.i(TAG,"case 1");
                    performMore(service, nodeInfo,
                            mContext.getString(R.string.accessibility_permission_miuiv6_callphone),
                            "miui_v6_callphone_step1");
                    return;
                }

                list = nodeInfo.findAccessibilityNodeInfosByText(
                        mContext.getString(R.string.accessiblity_permission_touchpal));
                if(list != null && list.size() > 0) {
                    Log.i(TAG,"case 2");
                    performMore(service, nodeInfo,
                            mContext.getString(R.string.accessibility_permission_miuiv6_toast),
                            "miui_v6_toast_step1");
                    return;
                }


            }
            if ( event.getClassName().equals("miui.app.AlertDialog")
//                    || (nodeInfo.findAccessibilityNodeInfosByText(mContext.getString(R.string.accessibility_permission_miuiv6_allowed))).size()>0
                    ) {

                if(nodeInfo.findAccessibilityNodeInfosByText(mContext.getString(R.string.accessibility_permission_miuiv6_callphone_detial)).size()>0) {
                    clickByText(nodeInfo,
                            mContext.getString(R.string.accessibility_permission_miuiv6_allowed));

                    PrefUtil.setKey(IPermissionGuideStrategy.PREF_KEY_DONE_PREFIX + GuideConst.CALL_PHONE_PERMISSION, true);
                    return;
                } else if (nodeInfo.findAccessibilityNodeInfosByText(mContext.getString(R.string.accessibility_permission_miuiv6_toast_detial)).size()>0) {
                    clickByText(nodeInfo,
                            mContext.getString(R.string.accessibility_permission_miuiv6_allowed));

                        PrefUtil.setKey(IPermissionGuideStrategy.PREF_KEY_DONE_PREFIX + GuideConst.TOAST_PERMISSION, true);

                    return;
                }
            }


        } else if (event.getEventType() == AccessibilityEvent.TYPE_WINDOW_STATE_CHANGED ||
                event.getEventType() == AccessibilityEvent.TYPE_VIEW_FOCUSED ||
                event.getEventType() == AccessibilityEvent.TYPE_WINDOW_CONTENT_CHANGED) {
            AccessibilityNodeInfo nodeInfo = service.getRootInActiveWindow();
            if (nodeInfo == null) {
                return;
            }
            List<AccessibilityNodeInfo> list = nodeInfo.findAccessibilityNodeInfosByText(mContext.getString(R.string.accessibility_permission_miuiv6_accessibility));
            if (list != null && list.size() > 0) {
                list = nodeInfo.findAccessibilityNodeInfosByText(mContext.getString(R.string.permission_accessiblity_title));
                if (list != null && list.size() > 0) {
                    service.performGlobalAction(GLOBAL_ACTION_BACK);
                    return;
                }
            }
            list = nodeInfo.findAccessibilityNodeInfosByText(mContext.getString(R.string.permission_accessiblity_title));
            if (list != null && list.size() > 0) {
                service.performGlobalAction(GLOBAL_ACTION_BACK);
                return;
            }

            if(mVersion == VERSION.SPECIAL) {

                list = nodeInfo.findAccessibilityNodeInfosByText(mContext.getString(R.string.accessibility_permission_miuiv6_application));
                if (list != null && list.size() > 0 && !accessisbilityMap.containsKey("miui_v6_callphone_step1")) {
                    Log.i(TAG, "case 00");
                    performMore(service, nodeInfo,
                            mContext.getString(R.string.accessibility_permission_miuiv6_permission_manager),
                            "miui_v6_callphone_step0");
                    return;
                }

                list = nodeInfo.findAccessibilityNodeInfosByText(mContext.getString(R.string.accessibility_permission_miuiv6_application));
                if (list != null && list.size() > 0
                        && !accessisbilityMap.containsKey("miui_v6_notification_step1")
                        && accessisbilityMap.containsKey("miui_v6_callphone_step0")
                        && !accessisbilityMap.containsKey("miui_v6_details_backed")) {
                    Log.i(TAG, "case 00-x");

                    accessisbilityMap.put("miui_v6_details_backed", 1);
                    service.performGlobalAction(GLOBAL_ACTION_BACK);
                    return;
                }
            }

            list = nodeInfo.findAccessibilityNodeInfosByText(mContext.getString(R.string.accessibility_permission_miuiv6_application));
            if(list != null && list.size() > 0) {
                Log.i(TAG,"case 01");
                performMore(service, nodeInfo,
                        mContext.getString(R.string.accessibility_permission_miuiv6_notification),
                        "miui_v6_notification_step1");
                return;
            }

            list = nodeInfo.findAccessibilityNodeInfosByText(mContext.getString(R.string.accessiblity_permission_touchpal));
            if(list != null && list.size() > 0 && !event.getPackageName().equals("com.miui.securitycenter")) {
                Log.i(TAG,"case 02");
                if(performSwitch(service, nodeInfo,
                        mContext.getString(R.string.accessibility_permission_miuiv6_notification_open),
                        false, true,"miui_v6_notification_step2_1")) {
                    performSwitch(service, nodeInfo,
                            mContext.getString(R.string.accessibility_permission_miuiv6_notification_desktop),
                            false, true,"miui_v6_notification_step2_2");
                    performSwitch(service, nodeInfo,
                            mContext.getString(R.string.accessibility_permission_miuiv6_notification_toast),
                            false, true, "miui_v6_notification_step2_3");
                    performSwitch(service, nodeInfo,
                            mContext.getString(R.string.accessibility_permission_miuiv6_notification_lock),
                            false, true,"miui_v6_notification_step2_4");

                    service.performGlobalAction(GLOBAL_ACTION_BACK);
                    PrefUtil.setKey(IPermissionGuideStrategy.PREF_KEY_DONE_PREFIX + GuideConst.OPEN_NOTIFICATION, true);
                }
                return;
            }
        }
    }

    private AccessibilityNodeInfo recycle(AccessibilityNodeInfo info) {
        Log.i(TAG,"SDK_INT = "+ Build.VERSION.SDK_INT);
        if (info!= null && info.getClassName() != null
                && (info.getClassName().equals("android.widget.ListView")
                || info.getClassName().equals("android.widget.GridView")) ){
                return info;
        }
        if (info == null || info.getChildCount() == 0) {
            return null;
        }
        for (int i = 0; i < info.getChildCount(); i++) {
            AccessibilityNodeInfo item = recycle(info.getChild(i));
            if (item != null) {
                return item;
            }
        }
        return null;
    }

    private void performMore(AccessibilityService service, AccessibilityNodeInfo nodeInfo, String tagText, String key) {
        if (accessisbilityMap.containsKey(key)) {
            service.performGlobalAction(GLOBAL_ACTION_BACK);
            return;
        }
        List<AccessibilityNodeInfo> list = nodeInfo.findAccessibilityNodeInfosByText(tagText);
        if (list == null || list.size() == 0) {
            AccessibilityNodeInfo info = recycle(nodeInfo);
            Log.e(TAG,"case B info="+info);
            if(info != null) {
                info.performAction(AccessibilityNodeInfo.ACTION_SCROLL_FORWARD);
            }
            return;
        }
        AccessibilityNodeInfo parent = list.get(0).getParent();
        if (parent != null) {
            Log.e(TAG,"case C  "+accessisbilityMap.containsKey(key));
            if (!accessisbilityMap.containsKey(key)) {
                if( parent.performAction(AccessibilityNodeInfo.ACTION_CLICK)){
                    accessisbilityMap.put(key,1);
                }
            }
        }
    }

    private void clickByText(AccessibilityNodeInfo nodeInfo, String target) {
        if (null != nodeInfo) {
            List<AccessibilityNodeInfo> list = nodeInfo.findAccessibilityNodeInfosByText(target);
            if (null != list && list.size() > 0) {
                AccessibilityNodeInfo node = list.get(list.size() - 1);
                Log.e(TAG,"click node = "+node.toString());
                Log.e(TAG,"click node = "+node.getParent().toString());
                Log.e(TAG,"clickable = "+node.isClickable());

                if (node.isClickable()) {
                    node.performAction(AccessibilityNodeInfo.ACTION_CLICK);
                }
                else {
                    AccessibilityNodeInfo parentNode = node;
                    for (int i = 0; i < 4; i++) {
                        if (null != parentNode) {
                            parentNode = parentNode.getParent();
                            if (null != parentNode && parentNode.isClickable()) {
                                Log.e(TAG,"click case2 parentnode"+parentNode.toString());
                                parentNode.performAction(AccessibilityNodeInfo.ACTION_CLICK);
                            }
                        }
                    }
                }
            }
        }
        return;
    }

    private boolean performSwitch(AccessibilityService service, AccessibilityNodeInfo nodeInfo, String tagText, String key) {
        return performSwitch(service, nodeInfo, tagText, true, true, key);
    }

    private boolean performSwitch(AccessibilityService service, AccessibilityNodeInfo nodeInfo, String tagText, boolean back, boolean target, String key) {
        List<AccessibilityNodeInfo> list = nodeInfo.findAccessibilityNodeInfosByText(tagText);
        boolean checked = false;
        if(list != null && list.size() > 0) {
            if(!accessisbilityMap.containsKey(key)) {
                for(int i=0; i<list.size(); i++) {
                    AccessibilityNodeInfo parent = list.get(i).getParent();
                    Log.e(TAG,"parent = " + parent.toString());
                    AccessibilityNodeInfo nodeSwitch = getSwitch(list.get(i));
                    Log.e(TAG,"switch = " + nodeSwitch.toString());
                    if (parent != null && target != nodeSwitch.isChecked()) {
                        try {
                            Log.e(TAG, "Action List = " + nodeSwitch.getActionList().toString());
                            if (nodeSwitch.getActionList().contains(AccessibilityNodeInfo.AccessibilityAction.ACTION_CLICK)) {
                                Log.d(TAG, "nS 1");
                                nodeSwitch.performAction(AccessibilityNodeInfo.ACTION_CLICK);
                                accessisbilityMap.put(key, 1);
                            } else {
                                Log.d(TAG,"nS 2");
                                parent.performAction(AccessibilityNodeInfo.ACTION_CLICK);
                                accessisbilityMap.put(key, 1);
                            }
                        } catch (NoSuchMethodError e) {
                            nodeSwitch.performAction(AccessibilityNodeInfo.ACTION_CLICK);
                            accessisbilityMap.put(key, 1);
                        } catch (Exception e) {
                        }
                    }
                    if(key.equals("miui_v6_autoboot_step1") && nodeSwitch.isChecked()){
                        checked = true;
                    }
                    break;
                }
            }
            if(back) {
                service.performGlobalAction(GLOBAL_ACTION_BACK);
            }
            if(key.equals("miui_v6_autoboot_step1")){
                return checked;
            }
            return true;
        } else {
            AccessibilityNodeInfo info = recycle(nodeInfo);
            if (info!= null) {
                info.performAction(AccessibilityNodeInfo.ACTION_SCROLL_FORWARD);
            }
        }
        return false;
    }

    private AccessibilityNodeInfo getSwitch(AccessibilityNodeInfo node){

        AccessibilityNodeInfo item= null;
        int tem = 0;

        while(tem < 3){
            tem++;
            item = getSwitchCycle(node);
            if (item == null)
                node = node.getParent();
            else
                return item;
        }
        return null;
    }

    private AccessibilityNodeInfo getSwitchCycle(AccessibilityNodeInfo node){
        if(node != null && ( node.getClassName().equals("android.widget.Switch")
                || node.getClassName().equals("android.widget.CheckBox")) ){
            return node;
        }

        if(node == null || node.getChildCount() == 0){
            return null;
        }

        for(int i=0;i < node.getChildCount(); i++){
            AccessibilityNodeInfo item = getSwitchCycle(node.getChild(i));
            if(item != null){
                return item;
            }
        }

        return null;
    }

    @Override
    public void configAccessbility(AccessibilityService sevice) {
        AccessibilityServiceInfo accessibilityServiceInfo = new AccessibilityServiceInfo();
        accessibilityServiceInfo.packageNames = new String[]{
                GuideConst.MIUI_V6_PERMISSION_PACKAGE_NAME,
                "com.android.packageinstaller",
                "com.android.settings"
        };
        Log.e(TAG,"configAccessibility service = " + sevice.toString());
        accessibilityServiceInfo.eventTypes = AccessibilityEvent.TYPES_ALL_MASK;
        accessibilityServiceInfo.feedbackType = AccessibilityServiceInfo.FEEDBACK_GENERIC;
        accessibilityServiceInfo.notificationTimeout = 1000;
        sevice.setServiceInfo(accessibilityServiceInfo);
        sevice.performGlobalAction(GLOBAL_ACTION_BACK);
    }

}
