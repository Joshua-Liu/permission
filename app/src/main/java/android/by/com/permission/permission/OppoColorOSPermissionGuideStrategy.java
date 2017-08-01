package android.by.com.permission.permission;

import android.accessibilityservice.AccessibilityService;
import android.accessibilityservice.AccessibilityServiceInfo;
import android.annotation.TargetApi;
import android.by.com.permission.R;
import android.by.com.permission.constant.Constants;
import android.by.com.permission.constant.GuideConst;
import android.by.com.permission.model.ModelManager;
import android.by.com.permission.pref.PrefKeys;
import android.by.com.permission.util.PackageUtil;
import android.by.com.permission.util.PrefUtil;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;
import android.util.Log;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;



import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static android.accessibilityservice.AccessibilityService.GLOBAL_ACTION_BACK;


/**
 * Created by yimao on 25/8/16.
 */
public class OppoColorOSPermissionGuideStrategy extends IPermissionGuideStrategy {

    public enum VERSION {
        V1, // <=3.0.2266
        V2, // >=3.0.2267 && Build.DISPLAY < 170000
        V3, // >=3.0.2267 && Build.DISPLAY >= 170000
        V4, // >=3.0.2267 && Build.DISPLAY >= 170500
        V5, // >=3.0.2267 && Build.DISPLAY >= 170603
        V6, // Build.DISPLAY >= 170613
    }
    private final String TAG = "OppoPermission";

    private static final String TYPE_READ_CALLLOG = "type_read_calllog";
    private static final String TYPE_READ_CONTACT = "type_read_contact";

    private static final String OPPO_COLOROS_NOTIFICATION_PACKAGER = "com.coloros.notificationmanager";
    private static final String OPPO_COLOROS_NOTIFICATION_ACTIVITY = "com.coloros.notificationmanager.NotificationCenterActivity";
    private static final String OPPO_COLOROS_NOTIFICATION_PACKAGER_V4 = "com.android.settings";
    private static final String OPPO_COLOROS_NOTIFICATION_ACTIVITY_V4 = "com.android.settings.applications.InstalledAppDetails";
    private static final String PACKAGE_NAME = "com.coloros.safecenter";
    private static final String AUTOBOOT_ACTIVITY_NAME_V2 = "com.coloros.safecenter.startupapp.StartupAppListActivity";
    private static final String TOAST_ACTIVITY_NAME_V2 = "com.coloros.safecenter.sysfloatwindow.FloatWindowListActivity";
    private static final String AUTOBOOT_ACTIVITY_NAME_V4 = "com.coloros.privacypermissionsentry.PermissionTopActivity";
    private static final String PERMISSION_TOP_ACTIVITY_NAME_V2 = "com.coloros.privacypermissionsentry.PermissionTopActivity";
    private static final String SAFE_CENTER_MAINACTIVITY_NAME_V2 = "com.coloros.safecenter.MainActivity";
    private static final String OPPO_COLOROS_POWER_PACKAGE = "com.coloros.oppoguardelf";
    private static final String OPPO_COLOROS_POWER_ACTIVITY = "com.coloros.powermanager.fuelgaue.PowerConsumptionActivity";


    public static final String[] OPPO_AUTOBOOT_ACTIVITY_NAMES = new String[] {
            "com.coloros.safecenter.permission.startup.StartupAppListActivity"
    };

    public static final String[] OPPO_TRUST_APPLICATION_ACTIVITY_NAMES = new String[] {
            "com.coloros.safecenter.permission.PermissionManagerActivity"
    };

    public static final String[] OPPO_READ_CONTACT_CALLLOG_ACTIVITY_NAMES = new String[] {
            "com.coloros.safecenter.permission.singlepage.PermissionSinglePageActivity"
    };

    public static final String[] OPPO_READ_CONTACT_CALLLOG_DEFAULT_ACTIVITY_NAMES = new String[] {
            "com.coloros.safecenter.MainActivity"
    };

    public static final String[] OPPO_TOAST_ACTIVITY_NAMES = new String[] {
            "com.coloros.safecenter.permission.floatwindow.FloatWindowListActivity"
    };

    private static final String OPPO_SAFECENTER = "com.coloros.safecenter";
    private static final String OPPO_ANDROID_SEETING = "com.android.settings";

    private VERSION mVersion;
    private boolean mAutoGuide;
    private boolean isGoback;
    private HashMap<String, Object> accessisbilityMap = new HashMap<String, Object>();

    public OppoColorOSPermissionGuideStrategy(Context mContext, boolean auto) {
        super(mContext);
        mVersion = getVersion();
        mAutoGuide = auto;
    }

    @Override
    protected void actionTrustApplicationPermission(boolean showGuide) {
        super.actionTrustApplicationPermission(showGuide);
        funcTrustApplicationPermission();
    }

    @Override
    protected void actionReadCalllog() {
        super.actionReadCalllog();
        if (getVersion() == VERSION.V1 || getVersion() == VERSION.V2){
            funcTrustApplicationPermission();
        } else {
            try {
                Intent sysIntent = new Intent();
                sysIntent.setAction("android.settings.APPLICATION_DETAILS_SETTINGS");
                sysIntent.setData(Uri.parse("package:" + mContext.getPackageName()));
                mContext.startActivity(sysIntent);
                showGuideMaskReadCalllogPermission();
            }catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    protected void actionReadContact() {
        super.actionReadContact();
        if (getVersion() == VERSION.V1 || getVersion() == VERSION.V2){
            funcTrustApplicationPermission();
        }else {
            try {
                Intent sysIntent = new Intent();
                sysIntent.setAction("android.settings.APPLICATION_DETAILS_SETTINGS");
                sysIntent.setData(Uri.parse("package:" + mContext.getPackageName()));
                mContext.startActivity(sysIntent);
                showGuidMaskReadContactPermission();
            }catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void funcTrustApplicationPermission() {
        try {
            int startupIndex = getPermissionPackageIndex();
            Intent sysIntent = new Intent();
            sysIntent.setClassName(
                    PackageUtil.OPPO_COLOROS_PERMISSION_SETTING_PACKAGE_NAMES[startupIndex],
                    OPPO_TRUST_APPLICATION_ACTIVITY_NAMES[startupIndex]);
            mContext.startActivity(sysIntent);

            if (!mAutoGuide) {
                showGuideMaskTrustApplicationPermission();
            }
        } catch (ActivityNotFoundException e) {
            e.printStackTrace();
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public void showGuideMaskReadCalllogPermission() {
        Intent intent = new Intent(mContext, OuterTwoStepPermissionActivity.class);
        intent.putExtra(OuterTwoStepPermissionActivity.GUIDE_TEXT_ROW_ONE, mContext.getString(R.string.permission_oppo_v3_readcalllog_contact_step_1));
        intent.putExtra(OuterTwoStepPermissionActivity.GUIDE_TEXT_ROW_TWO, mContext.getString(R.string.permission_oppo_v3_readcalllog_step_2));
        intent.putExtra(OuterTwoStepPermissionActivity.GUIDE_TEXT_ROW_THREE, mContext.getString(R.string.permission_oppo_v3_readcalllog_contact_step_3));
        intent.putExtra(OuterTwoStepPermissionActivity.GUIDE_IMG_ROW_ONE, R.drawable.permission_oppo_v3_readcalllog_contact_step_1);
        intent.putExtra(OuterTwoStepPermissionActivity.GUIDE_IMG_ROW_TWO, R.drawable.permission_oppo_v3_readcalllog_step_2);
        intent.putExtra(OuterTwoStepPermissionActivity.GUIDE_IMG_ROW_THREE, R.drawable.permission_oppo_v3_readcalllog_contact_step_3);
        intent.putExtra(OuterTwoStepPermissionActivity.GUIDE_NEED_GESTURE_THREE, true);
        intent.putExtra(OuterTwoStepPermissionActivity.GUIDE_NEED_GESTURE_MARGIN_LEFT_THREE, R.dimen.permission_oppo_trust_gesture_maring_left_step3);
        mContext.startActivity(intent);
    }

    public void showGuidMaskReadContactPermission() {
        Intent intent = new Intent(mContext, OuterTwoStepPermissionActivity.class);
        intent.putExtra(OuterTwoStepPermissionActivity.GUIDE_TEXT_ROW_ONE, mContext.getString(R.string.permission_oppo_v3_readcalllog_contact_step_1));
        intent.putExtra(OuterTwoStepPermissionActivity.GUIDE_TEXT_ROW_TWO, mContext.getString(R.string.permission_oppo_v3_readcontact_step_2));
        intent.putExtra(OuterTwoStepPermissionActivity.GUIDE_TEXT_ROW_THREE, mContext.getString(R.string.permission_oppo_v3_readcalllog_contact_step_3));
        intent.putExtra(OuterTwoStepPermissionActivity.GUIDE_IMG_ROW_ONE, R.drawable.permission_oppo_v3_readcalllog_contact_step_1);
        intent.putExtra(OuterTwoStepPermissionActivity.GUIDE_IMG_ROW_TWO, R.drawable.permission_oppo_v3_readcontact_step_2);
        intent.putExtra(OuterTwoStepPermissionActivity.GUIDE_IMG_ROW_THREE, R.drawable.permission_oppo_v3_readcalllog_contact_step_3);
        intent.putExtra(OuterTwoStepPermissionActivity.GUIDE_NEED_GESTURE_THREE, true);
        intent.putExtra(OuterTwoStepPermissionActivity.GUIDE_NEED_GESTURE_MARGIN_LEFT_THREE, R.dimen.permission_oppo_trust_gesture_maring_left_step3);
        mContext.startActivity(intent);
    }

    private void showGuideMaskTrustApplicationPermission() {
        Intent intent = new Intent(mContext, OuterTwoStepPermissionActivity.class);
        intent.putExtra(OuterTwoStepPermissionActivity.GUIDE_TEXT_ROW_ONE, mContext.getString(R.string.permission_oppo_coloros_toast_step1_text));
        intent.putExtra(OuterTwoStepPermissionActivity.GUIDE_TEXT_ROW_TWO, mContext.getString(R.string.permission_oppo_coloros_toast_step2_text));
        intent.putExtra(OuterTwoStepPermissionActivity.GUIDE_IMG_ROW_ONE, R.drawable.permission_oppo_coloros_toast_step1);
        intent.putExtra(OuterTwoStepPermissionActivity.GUIDE_IMG_ROW_TWO, R.drawable.permission_oppo_coloros_toast_step2);
        intent.putExtra(OuterTwoStepPermissionActivity.GUIDE_NEED_GESTURE_TWO, true);
        intent.putExtra(OuterTwoStepPermissionActivity.GUIDE_NEED_GESTURE_MARGIN_LEFT_TWO, R.dimen.permission_oppo_coloros_trust_gesture_maring_left_step2);
        mContext.startActivity(intent);
    }

    @Override
    protected void actionAutoBootPermission() {
        super.actionAutoBootPermission();
        try {
            if (mVersion == VERSION.V1) {
                int startupIndex = getPermissionPackageIndex();
                Intent sysIntent = new Intent();
                sysIntent.setClassName(
                        PackageUtil.OPPO_COLOROS_PERMISSION_SETTING_PACKAGE_NAMES[startupIndex],
                        OPPO_AUTOBOOT_ACTIVITY_NAMES[startupIndex]);
                mContext.startActivity(sysIntent);
                if (!mAutoGuide) {
                    Intent guideIntent = new Intent(mContext, OuterPermissionActivity.class);
                    guideIntent.putExtra(OuterPermissionActivity.VIEWSTUB_ID, R.layout.general_permission_guide_mask);
                    guideIntent.putExtra(OuterPermissionActivity.GUIDEPIC_ID, R.drawable.oppo_coloros_permission_mask_autoboot);
                    guideIntent.putExtra(OuterPermissionActivity.GUIDE_HINTONE, mContext.getString(R.string.miui_permission_guide_template_one, mContext.getString(R.string.app_name)));
                    guideIntent.putExtra(OuterPermissionActivity.GUIDE_HINTTWO, mContext.getString(R.string.oppo_coloros_permission_autoboot_action_switch_on));
                    guideIntent.putExtra(OuterPermissionActivity.GUIDE_NO_BTN, true);
                    mContext.startActivity(guideIntent);
                }
            } else if (mVersion == VERSION.V2 || mVersion == VERSION.V3) {
                Intent sysIntent = new Intent();
                sysIntent.setClassName(PACKAGE_NAME, AUTOBOOT_ACTIVITY_NAME_V2);
                mContext.startActivity(sysIntent);
                if (!mAutoGuide) {
                    Intent guideIntent = new Intent(mContext, OuterPermissionActivity.class);
                    guideIntent.putExtra(OuterPermissionActivity.VIEWSTUB_ID, R.layout.general_permission_guide_mask);
                    guideIntent.putExtra(OuterPermissionActivity.GUIDEPIC_ID, R.drawable.oppo_coloros_permission_mask_autoboot);
                    guideIntent.putExtra(OuterPermissionActivity.GUIDE_HINTONE, mContext.getString(R.string.miui_permission_guide_template_one, mContext.getString(R.string.app_name)));
                    guideIntent.putExtra(OuterPermissionActivity.GUIDE_HINTTWO, mContext.getString(R.string.oppo_coloros_permission_autoboot_action_switch_on));
                    guideIntent.putExtra(OuterPermissionActivity.GUIDE_NO_BTN, true);
                    mContext.startActivity(guideIntent);
                }
            }else  if(mVersion == VERSION.V4){
                Intent sysIntent = new Intent();
                sysIntent.setClassName(PACKAGE_NAME, AUTOBOOT_ACTIVITY_NAME_V4);
                mContext.startActivity(sysIntent);
                if (!mAutoGuide) {
                    Intent intent = new Intent(mContext, OuterPermissionActivityForOppo.class);
                    intent.putExtra(OuterTwoStepPermissionActivity.GUIDE_TEXT_ROW_ONE, mContext.getString(R.string.oppo_permission_guide_auto_one));
                    intent.putExtra(OuterTwoStepPermissionActivity.GUIDE_TEXT_ROW_TWO, mContext.getString(R.string.oppo_permission_guide_auto_two));
                    intent.putExtra(OuterTwoStepPermissionActivity.GUIDE_IMG_ROW_ONE, R.drawable.permission_oppo_coloros_auto_v4_step1);
                    intent.putExtra(OuterTwoStepPermissionActivity.GUIDE_IMG_ROW_TWO, R.drawable.permission_oppo_coloros_auto_v4_step2);
                    intent.putExtra(OuterTwoStepPermissionActivity.GUIDE_NEED_GESTURE_TWO, true);
                    intent.putExtra(OuterTwoStepPermissionActivity.GUIDE_NEED_GESTURE_MARGIN_LEFT_TWO, R.dimen.permission_oppo_coloros_trust_gesture_maring_left_step2);
                    mContext.startActivity(intent);
                }
            }else  if(mVersion == VERSION.V5){
                Intent sysIntent = new Intent();
                sysIntent.setClassName(PACKAGE_NAME, AUTOBOOT_ACTIVITY_NAME_V2);
                mContext.startActivity(sysIntent);
                if (!mAutoGuide) {
                    Intent intent = new Intent(mContext, OuterPermissionActivityForOppo.class);
                    intent.putExtra(OuterTwoStepPermissionActivity.GUIDE_TEXT_ROW_ONE, mContext.getString(R.string.oppo_permission_guide_auto_one));
                    intent.putExtra(OuterTwoStepPermissionActivity.GUIDE_TEXT_ROW_TWO, mContext.getString(R.string.oppo_permission_guide_auto_two));
                    intent.putExtra(OuterTwoStepPermissionActivity.GUIDE_IMG_ROW_ONE, R.drawable.permission_oppo_coloros_auto_v4_step1);
                    intent.putExtra(OuterTwoStepPermissionActivity.GUIDE_IMG_ROW_TWO, R.drawable.permission_oppo_coloros_auto_v4_step2);
                    intent.putExtra(OuterTwoStepPermissionActivity.GUIDE_NEED_GESTURE_TWO, true);
                    intent.putExtra(OuterTwoStepPermissionActivity.GUIDE_NEED_GESTURE_MARGIN_LEFT_TWO, R.dimen.permission_oppo_coloros_trust_gesture_maring_left_step2);
                    mContext.startActivity(intent);
                }
            }else  if(mVersion == VERSION.V6){
                Intent sysIntent = new Intent();
                sysIntent.setClassName(PACKAGE_NAME, AUTOBOOT_ACTIVITY_NAME_V4);
                mContext.startActivity(sysIntent);
                if (!mAutoGuide) {
                    Intent intent = new Intent(mContext, OuterPermissionActivityForOppo.class);
                    intent.putExtra(OuterTwoStepPermissionActivity.GUIDE_TEXT_ROW_ONE, mContext.getString(R.string.oppo_permission_guide_auto_one));
                    intent.putExtra(OuterTwoStepPermissionActivity.GUIDE_TEXT_ROW_TWO, mContext.getString(R.string.oppo_permission_guide_auto_two));
                    intent.putExtra(OuterTwoStepPermissionActivity.GUIDE_IMG_ROW_ONE, R.drawable.permission_oppo_coloros_auto_v4_step1);
                    intent.putExtra(OuterTwoStepPermissionActivity.GUIDE_IMG_ROW_TWO, R.drawable.permission_oppo_coloros_auto_v4_step2);
                    intent.putExtra(OuterTwoStepPermissionActivity.GUIDE_NEED_GESTURE_TWO, true);
                    intent.putExtra(OuterTwoStepPermissionActivity.GUIDE_NEED_GESTURE_MARGIN_LEFT_TWO, R.dimen.permission_oppo_coloros_trust_gesture_maring_left_step2);
                    mContext.startActivity(intent);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void actionBackgroundPermisssion() {
        super.actionBackgroundPermisssion();
        try {
            Intent sysIntent = new Intent(Settings.ACTION_APPLICATION_SETTINGS);
            sysIntent.putExtra("packageName", Constants.PACKAGE_NAME);
            mContext.startActivity(sysIntent);
        } catch (Exception e) {}

        if (!mAutoGuide) {
            Intent guideIntent = new Intent(mContext, OuterPermissionActivity.class);
            guideIntent.putExtra(OuterPermissionActivity.VIEWSTUB_ID, R.layout.general_permission_guide_mask);
            guideIntent.putExtra(OuterPermissionActivity.GUIDEPIC_ID, R.drawable.oppo_permission_mask_background);
            guideIntent.putExtra(OuterPermissionActivity.GUIDE_HINTONE, mContext.getString(R.string.miui_permission_guide_template_one, mContext.getString(R.string.app_name)));
            guideIntent.putExtra(OuterPermissionActivity.GUIDE_HINTTWO, mContext.getString(R.string.permission_mask_background_protection_oppo));
            guideIntent.putExtra(OuterPermissionActivity.GUIDE_INDICATOR_RIGHT_MARGIN, (int)mContext.getResources().getDisplayMetrics().density * 80);
            mContext.startActivity(guideIntent);
        }
    }

    @Override
    protected void actionToastPermission() {
        super.actionToastPermission();
        try {
            if (mVersion == VERSION.V1) {
                int startupIndex = getPermissionPackageIndex();
                Intent sysIntent = new Intent();
                sysIntent.setClassName(
                        PackageUtil.OPPO_COLOROS_PERMISSION_SETTING_PACKAGE_NAMES[startupIndex],
                        OPPO_TOAST_ACTIVITY_NAMES[startupIndex]);
                mContext.startActivity(sysIntent);
                if (!mAutoGuide) {
                    Intent guideIntent = new Intent(mContext, OuterPermissionActivity.class);
                    guideIntent.putExtra(OuterPermissionActivity.VIEWSTUB_ID, R.layout.general_permission_guide_mask);
                    guideIntent.putExtra(OuterPermissionActivity.GUIDEPIC_ID, R.drawable.oppo_coloros_permission_mask_autoboot);
                    guideIntent.putExtra(OuterPermissionActivity.GUIDE_HINTONE, mContext.getString(R.string.miui_permission_guide_template_one, mContext.getString(R.string.app_name)));
                    guideIntent.putExtra(OuterPermissionActivity.GUIDE_HINTTWO, mContext.getString(R.string.oppo_coloros_permission_toast_switch_on));
                    mContext.startActivity(guideIntent);
                }
            } else if (mVersion == VERSION.V2  || mVersion == VERSION.V3) {


                Intent sysIntent2 = new Intent();
                sysIntent2.setClassName(PACKAGE_NAME, TOAST_ACTIVITY_NAME_V2);
                mContext.startActivity(sysIntent2);


                if (!mAutoGuide) {
                    Intent guideIntent = new Intent(mContext, OuterPermissionActivity.class);
                    guideIntent.putExtra(OuterPermissionActivity.VIEWSTUB_ID, R.layout.general_permission_guide_mask);
                    guideIntent.putExtra(OuterPermissionActivity.GUIDEPIC_ID, R.drawable.oppo_coloros_permission_mask_autoboot);
                    guideIntent.putExtra(OuterPermissionActivity.GUIDE_HINTONE, mContext.getString(R.string.miui_permission_guide_template_one, mContext.getString(R.string.app_name)));
                    guideIntent.putExtra(OuterPermissionActivity.GUIDE_HINTTWO, mContext.getString(R.string.oppo_coloros_permission_toast_switch_on));
                    guideIntent.putExtra(OuterPermissionActivity.GUIDE_NO_BTN, true);
                    mContext.startActivity(guideIntent);
                }
            }else if(mVersion == VERSION.V4){
//                Intent sysIntent1 = new Intent();
//                sysIntent1.setClassName(PACKAGE_NAME, SAFE_CENTER_MAINACTIVITY_NAME_V2);
//                mContext.startActivity(sysIntent1);


                Intent sysIntent = new Intent();
                sysIntent.setClassName(PACKAGE_NAME, AUTOBOOT_ACTIVITY_NAME_V4);
                mContext.startActivity(sysIntent);
                if (!mAutoGuide) {
                    Intent intent = new Intent(mContext, OuterPermissionActivityForOppo.class);
                    intent.putExtra(OuterTwoStepPermissionActivity.GUIDE_TEXT_ROW_ONE, mContext.getString(R.string.oppo_permission_guide_toast_v4_one));
                    intent.putExtra(OuterTwoStepPermissionActivity.GUIDE_TEXT_ROW_TWO, mContext.getString(R.string.oppo_permission_guide_toast_v4_two));
                    intent.putExtra(OuterTwoStepPermissionActivity.GUIDE_IMG_ROW_ONE, R.drawable.permission_oppo_toast_v4_step1);
                    intent.putExtra(OuterTwoStepPermissionActivity.GUIDE_IMG_ROW_TWO, R.drawable.permission_oppo_toast_v4_step2);
                    intent.putExtra(OuterTwoStepPermissionActivity.GUIDE_NEED_GESTURE_TWO, true);
                    intent.putExtra(OuterTwoStepPermissionActivity.GUIDE_NEED_GESTURE_MARGIN_LEFT_TWO, R.dimen.permission_oppo_coloros_trust_gesture_maring_left_step2);
                    mContext.startActivity(intent);
                }
            }else if(mVersion == VERSION.V5){
//                Intent sysIntent1 = new Intent();
//                sysIntent1.setClassName(PACKAGE_NAME, SAFE_CENTER_MAINACTIVITY_NAME_V2);
//                mContext.startActivity(sysIntent1);


//                Intent sysIntent = new Intent();
//                sysIntent.setClassName(PACKAGE_NAME, AUTOBOOT_ACTIVITY_NAME_V4);
//                mContext.startActivity(sysIntent);

                Intent sysIntent = new Intent();
                sysIntent.setClassName(PACKAGE_NAME, TOAST_ACTIVITY_NAME_V2);
                mContext.startActivity(sysIntent);
                if (!mAutoGuide) {
                    Intent intent = new Intent(mContext, OuterPermissionActivityForOppo.class);
                    intent.putExtra(OuterTwoStepPermissionActivity.GUIDE_TEXT_ROW_ONE, mContext.getString(R.string.oppo_permission_guide_toast_v4_one));
                    intent.putExtra(OuterTwoStepPermissionActivity.GUIDE_TEXT_ROW_TWO, mContext.getString(R.string.oppo_permission_guide_toast_v4_two));
                    intent.putExtra(OuterTwoStepPermissionActivity.GUIDE_IMG_ROW_ONE, R.drawable.permission_oppo_toast_v4_step1);
                    intent.putExtra(OuterTwoStepPermissionActivity.GUIDE_IMG_ROW_TWO, R.drawable.permission_oppo_toast_v4_step2);
                    intent.putExtra(OuterTwoStepPermissionActivity.GUIDE_NEED_GESTURE_TWO, true);
                    intent.putExtra(OuterTwoStepPermissionActivity.GUIDE_NEED_GESTURE_MARGIN_LEFT_TWO, R.dimen.permission_oppo_coloros_trust_gesture_maring_left_step2);
                    mContext.startActivity(intent);
                }
            }else if(mVersion == VERSION.V6){
                Log.i("weyl","试图进入toast页面" );
                Intent sysIntent = new Intent();
                sysIntent.setClassName(PACKAGE_NAME, AUTOBOOT_ACTIVITY_NAME_V4);
                mContext.startActivity(sysIntent);
                if (!mAutoGuide) {
                    Intent intent = new Intent(mContext, OuterPermissionActivityForOppo.class);
                    intent.putExtra(OuterTwoStepPermissionActivity.GUIDE_TEXT_ROW_ONE, mContext.getString(R.string.oppo_permission_guide_toast_v4_one));
                    intent.putExtra(OuterTwoStepPermissionActivity.GUIDE_TEXT_ROW_TWO, mContext.getString(R.string.oppo_permission_guide_toast_v4_two));
                    intent.putExtra(OuterTwoStepPermissionActivity.GUIDE_IMG_ROW_ONE, R.drawable.permission_oppo_toast_v4_step1);
                    intent.putExtra(OuterTwoStepPermissionActivity.GUIDE_IMG_ROW_TWO, R.drawable.permission_oppo_toast_v4_step2);
                    intent.putExtra(OuterTwoStepPermissionActivity.GUIDE_NEED_GESTURE_TWO, true);
                    intent.putExtra(OuterTwoStepPermissionActivity.GUIDE_NEED_GESTURE_MARGIN_LEFT_TWO, R.dimen.permission_oppo_coloros_trust_gesture_maring_left_step2);
                    mContext.startActivity(intent);
                }
            }
        } catch (ActivityNotFoundException e) {
            Log.e("weyl",e.getStackTrace()+"") ;
        } catch (SecurityException e2) {
        } catch (Exception e3) {
        }
    }

    @Override
    protected void actionDataPermission() {
        super.actionDataPermission();
        ModelManager.getInst().registerContentObserver(ModelManager.getContext(), true);
//        ModelManager.getInst().getSMSMessage().syncObsoleteSms();
    }

    @Override
    protected void actionCallogOrContactPermission() {
        super.actionCallogOrContactPermission();
        actionTrustApplicationPermission(true);
    }

    @Override
    protected void actionOpenNotification() {
        super.actionOpenNotification();
        try {
            if (mVersion == VERSION.V4 || mVersion == VERSION.V6){
                Intent sysIntent = new Intent();
                sysIntent.setAction("android.settings.APPLICATION_DETAILS_SETTINGS");
                sysIntent.setData(Uri.parse("package:" + mContext.getPackageName()));
                mContext.startActivity(sysIntent);
                if (!mAutoGuide) {
                    showGuideMaskOpenNotification();
                }
            } else {
                Intent sysIntent = new Intent();
                sysIntent.setClassName(OPPO_COLOROS_NOTIFICATION_PACKAGER, OPPO_COLOROS_NOTIFICATION_ACTIVITY);
                mContext.startActivity(sysIntent);
                if (!mAutoGuide) {
                    showGuideMaskOpenNotification();
                }
            }
        } catch (ActivityNotFoundException e) {
        } catch (SecurityException e2) {
        } catch (Exception e3) {
        }
    }

    @Override
    protected void actionBackgroundFrozenPermission() {
        super.actionBackgroundFrozenPermission();
        try {
            Intent sysIntent = new Intent();
            sysIntent.setClassName(OPPO_COLOROS_POWER_PACKAGE, OPPO_COLOROS_POWER_ACTIVITY);
            mContext.startActivity(sysIntent);
            if (!mAutoGuide) {
                Intent guideIntent = new Intent(mContext, OuterTwoStepPermissionActivity.class);
                if (mVersion == VERSION.V1 || mVersion == VERSION.V2) {
                    guideIntent.putExtra(OuterTwoStepPermissionActivity.GUIDE_TEXT_ROW_ONE, mContext.getString(R.string.permission_oppo_background_frozen_step1_text));
                    guideIntent.putExtra(OuterTwoStepPermissionActivity.GUIDE_IMG_ROW_ONE, R.drawable.permission_oppo_background_frozen_step1);
                } else {
                    guideIntent.putExtra(OuterTwoStepPermissionActivity.GUIDE_TEXT_ROW_ONE, mContext.getString(R.string.permission_oppo_v3_background_frozen_step1_text));
                    guideIntent.putExtra(OuterTwoStepPermissionActivity.GUIDE_IMG_ROW_ONE, R.drawable.permission_oppo_v3_background_frozen_step1);
                }
                guideIntent.putExtra(OuterTwoStepPermissionActivity.GUIDE_TEXT_ROW_TWO, mContext.getString(R.string.permission_oppo_background_frozen_step2_text));
                if(mVersion != VERSION.V4) {
                    guideIntent.putExtra(OuterTwoStepPermissionActivity.GUIDE_TEXT_ROW_THREE, mContext.getString(R.string.permission_oppo_background_frozen_step3_text));
                    guideIntent.putExtra(OuterTwoStepPermissionActivity.GUIDE_IMG_ROW_THREE, R.drawable.permission_oppo_background_frozen_step3);

                }else{
                    guideIntent.putExtra(OuterTwoStepPermissionActivity.GUIDE_TEXT_ROW_THREE, mContext.getString(R.string.permission_oppo_background_frozen_v4_step3_text));
                    guideIntent.putExtra(OuterTwoStepPermissionActivity.GUIDE_IMG_ROW_THREE, R.drawable.permission_oppo_background_frozen_v4_step3);

                }

                guideIntent.putExtra(OuterTwoStepPermissionActivity.GUIDE_IMG_ROW_TWO, R.drawable.permission_oppo_background_frozen_step2);
                guideIntent.putExtra(OuterTwoStepPermissionActivity.GUIDE_NEED_GESTURE_THREE, true);
                guideIntent.putExtra(OuterTwoStepPermissionActivity.GUIDE_NEED_GESTURE_MARGIN_LEFT_THREE, R.dimen.permission_oppo_coloros_trust_gesture_maring_left_step2);
                mContext.startActivity(guideIntent);
            }
        } catch (ActivityNotFoundException e) {
        } catch (SecurityException e2) {
        } catch (Exception e3) {
        }
    }

    private void showGuideMaskOpenNotification() {
        if(mVersion != VERSION.V4 && mVersion != VERSION.V6) {
            Intent intent = new Intent(mContext, OuterTwoStepPermissionActivity.class);
            intent.putExtra(OuterTwoStepPermissionActivity.GUIDE_TEXT_ROW_ONE, mContext.getString(R.string.permission_oppo_coloros_toast_step1_text));
            intent.putExtra(OuterTwoStepPermissionActivity.GUIDE_TEXT_ROW_TWO, mContext.getString(R.string.permission_oppo_coloros_open_notification_step2_text));
            intent.putExtra(OuterTwoStepPermissionActivity.GUIDE_IMG_ROW_ONE, R.drawable.permission_oppo_coloros_open_notification_step1);
            intent.putExtra(OuterTwoStepPermissionActivity.GUIDE_IMG_ROW_TWO, R.drawable.permission_oppo_coloros_open_notification_step2);
            intent.putExtra(OuterTwoStepPermissionActivity.GUIDE_NEED_GESTURE_TWO, true);
            intent.putExtra(OuterTwoStepPermissionActivity.GUIDE_NEED_GESTURE_MARGIN_LEFT_TWO, R.dimen.permission_oppo_coloros_trust_gesture_maring_left_step2);
            mContext.startActivity(intent);
        }else{
            Intent intent = new Intent(mContext, OuterTwoStepPermissionActivity.class);
            intent.putExtra(OuterTwoStepPermissionActivity.GUIDE_TEXT_ROW_ONE, mContext.getString(R.string.permission_oppo_coloros_notification_v4_step1_text));
            intent.putExtra(OuterTwoStepPermissionActivity.GUIDE_TEXT_ROW_TWO, mContext.getString(R.string.permission_oppo_coloros_notification_v4_step2_text));
            intent.putExtra(OuterTwoStepPermissionActivity.GUIDE_IMG_ROW_ONE, R.drawable.permission_oppo_coloros_open_notification_v4_step1);
            intent.putExtra(OuterTwoStepPermissionActivity.GUIDE_IMG_ROW_TWO, R.drawable.permission_oppo_coloros_open_notification_v4_step2);
            intent.putExtra(OuterTwoStepPermissionActivity.GUIDE_NEED_GESTURE_TWO, true);
            intent.putExtra(OuterTwoStepPermissionActivity.GUIDE_NEED_GESTURE_MARGIN_LEFT_TWO, R.dimen.permission_oppo_coloros_trust_gesture_maring_left_step2);
            mContext.startActivity(intent);
        }
    }

    @Override
    public ArrayList<String> getPermissionList(int type) {
        ArrayList<String> ret = new ArrayList<String>();
        if (type == ACCESSIBLITY_UPGRADE) {
            ret.add(GuideConst.AUTOBOOT_PERMISSION);
            ret.add(GuideConst.OPPO_BACKGROUND_FROZEN_PERMISSION);
            ret.add(GuideConst.TOAST_PERMISSION);
        } else if (type == IPermissionGuideStrategy.TUTORIAL_TYPE) {
            if (mVersion == VERSION.V1 || mVersion == VERSION.V2) {
                ret.add(GuideConst.TRUST_APPLICATION_PERMISSION);
            }
            ret.add(GuideConst.AUTOBOOT_PERMISSION);
            ret.add(GuideConst.OPPO_BACKGROUND_FROZEN_PERMISSION);
            ret.add(GuideConst.TOAST_PERMISSION);
        } else if (type == IPermissionGuideStrategy.INAPP_TYPE) {
            ret.add(GuideConst.AUTOBOOT_PERMISSION);
            ret.add(GuideConst.OPPO_BACKGROUND_FROZEN_PERMISSION);
            ret.add(GuideConst.BACKGROUND_PROTECT_PERMISSION);
        } else if (type == IPermissionGuideStrategy.START_UP_TYPE) {
            if (mVersion == VERSION.V1 || mVersion == VERSION.V2) {
                ret.add(GuideConst.TRUST_APPLICATION_PERMISSION);
            }
        } else if (type == IPermissionGuideStrategy.TOAST_TYPE) {
            ret.add(GuideConst.TOAST_PERMISSION);
        } else if (type == IPermissionGuideStrategy.SECOND_TYPE) {
            ret.add(GuideConst.OPEN_NOTIFICATION);
        } else if (type == IPermissionGuideStrategy.INAPP_SECOND_GUIDE_TYPE) {
            return super.getSecondGuidePermissionList();
        }
        return ret;
    }

    @Override
    protected String getPermissionTitle() {
        return ModelManager.getContext().getString(R.string.permission_guide_title, "OPPO");
    }

    @Override
    protected String getPermissionTitle(String permission, int type) {
        String os = "OPPO";
        if (type == IPermissionGuideStrategy.START_UP_TYPE) {
            return ModelManager.getContext().getString(R.string.permission_guide_title, os);
        } else if (type == IPermissionGuideStrategy.INAPP_TYPE) {
            return ModelManager.getContext().getString(R.string.important_permission_guide_title, os);
        } else if (type == IPermissionGuideStrategy.TUTORIAL_TYPE) {
            return ModelManager.getContext().getString(R.string.permission_title_tutorial);
        } else if (GuideConst.AUTOBOOT_PERMISSION.equals(permission)) {
            return ModelManager.getContext().getString(R.string.autoboot_permission_title);
        } else if (GuideConst.BACKGROUND_PROTECT_PERMISSION.equals(permission)) {
            return ModelManager.getContext().getString(R.string.background_permission_title);
        } else if (GuideConst.TRUST_APPLICATION_PERMISSION.equals(permission)) {
            return ModelManager.getContext().getString(R.string.permission_title_call);
        } else if (GuideConst.TOAST_PERMISSION.equals(permission)) {
            return ModelManager.getContext().getString(R.string.permission_title_toast, os);
        } else if (type == IPermissionGuideStrategy.INAPP_SECOND_GUIDE_TYPE) {
            return mContext.getResources().getString(R.string.permission_second_guide_title);
        }

        return getPermissionTitle();
    }

    @Override
    protected PermissionGuideStepItem getPermissionGuideStepItem(String permission, int type) {
        PermissionGuideStepItem stepItem = null;
        if (GuideConst.TRUST_APPLICATION_PERMISSION.equals(permission)) {
            stepItem = new PermissionGuideStepItem(
                    type == IPermissionGuideStrategy.START_UP_TYPE
                            ? R.string.permission_trust_title
                            : type == IPermissionGuideStrategy.TUTORIAL_TYPE ? R.string.permission_trust_title_tutorial : -1,
                    new int[]{R.string.permission_trust_step_1_oppo, R.string.permission_trust_step_2_oppo},
                    new int[][]{new int[]{R.drawable.oppo_trust_permission_01, R.drawable.oppo_trust_permission_02}, new int[]{R.drawable.oppo_trust_permission_03}}
            );
        } else if (GuideConst.TOAST_PERMISSION.equals(permission)) {
            stepItem = new PermissionGuideStepItem(
                    type == IPermissionGuideStrategy.TUTORIAL_TYPE ? R.string.permission_toast_title_tutorial : R.string.permission_title_toast_single,
                    new int[]{R.string.permission_toast_step_1_oppo, R.string.permission_toast_step_2_oppo},
                    new int[][]{new int[]{R.drawable.oppo_toast_permission_01}, new int[]{R.drawable.oppo_toast_permission_02, R.drawable.oppo_toast_permission_03}}
            );
        } else if (GuideConst.BACKGROUND_PROTECT_PERMISSION.equals(permission)) {
            stepItem = new PermissionGuideStepItem(
                    -1,
                    new int[]{R.string.permission_background_protection_oppo},
                    new int[][]{new int[]{R.drawable.oppo_background_protection_permission}}
            );
        } else if (GuideConst.AUTOBOOT_PERMISSION.equals(permission)) {
            stepItem = new PermissionGuideStepItem(
                    type == IPermissionGuideStrategy.TUTORIAL_TYPE ? R.string.permission_others_title_tutorial : -1,
                    new int[]{R.string.autoboot_permission_hint},
                    new int[][]{new int[]{R.drawable.oppo_autoboot_permission}}
            );
        }
        return stepItem;
    }

   private int getPermissionPackageIndex() {
        int pos = -1;
        for(int index = 0; index < PackageUtil.OPPO_COLOROS_PERMISSION_SETTING_PACKAGE_NAMES.length; index++) {
            if (PackageUtil.isPackageInstalled(PackageUtil.OPPO_COLOROS_PERMISSION_SETTING_PACKAGE_NAMES[index])) {
                pos = index;
                break;
            }
        }
        return pos;
    }

    private VERSION getVersion() {
        try {
            PackageManager manager = mContext.getPackageManager();
            PackageInfo info = manager.getPackageInfo(PACKAGE_NAME, 0);
            String versionStr = info.versionName; //3.0.2266;
            Log.i(TAG, "version =" + versionStr);

            try {
                PrefUtil.setKey(PrefKeys.OPPO_COLOR_OS_VERSION, versionStr.replace(" ", ""));
            } catch (Exception e) {
                e.printStackTrace();
            }

            versionStr = versionStr.replace(".", "");
            int version = Integer.parseInt(versionStr);
            if (version <= 302266) {
                return VERSION.V1;
            } else {
                try {
                    /**update 17-4-19 By Junxiang Cheng
                     * v3_displayVersion:>=161228(before:170000)*/
                    String display = Build.DISPLAY;
                    Log.i(TAG, "display=" + display);
                    String[] tmp = display.split("_");
                    String displayVersion = tmp[tmp.length - 1];
                    if (Integer.parseInt(displayVersion) >= 170613){
                        return VERSION.V6;
                    }
                    if (Integer.parseInt(displayVersion) >= 170603){
                        return VERSION.V5;
                    }
                    if (Integer.parseInt(displayVersion) >= 170500){
                        return VERSION.V4;
                    }
                    if (Integer.parseInt(displayVersion) >= 161228/*170000*/) {
                        return VERSION.V3;
                    }
                } catch (Exception e) {
                }
                return VERSION.V2;
            }
        } catch (Exception e) {
        }
        return VERSION.V1;
    }

    @TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH)
    private void printAllChild(AccessibilityNodeInfo node, int depth){

        String prefix = "";
        for (int j=0;j<depth;j++){
            prefix = prefix + "    ";
        }
        if (node.getChildCount() == 0){
            Log.i("weyl", prefix+node.getClassName()+" leaf" + (node.getText()==null?"":node.getText())+"#");
        }else{
            Log.i("weyl", prefix+node.getClassName()+" has "+node.getChildCount()+" child" + (node.getText()==null?"":node.getText()));
            for (int i=0;i<node.getChildCount();i++){
                printAllChild(node.getChild(i), depth+1);
            }
        }

    }

    @TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH)
    private boolean pageContains(AccessibilityNodeInfo nodeInfo, String keyword) {
        List list = nodeInfo.findAccessibilityNodeInfosByText(keyword);

        if (list != null && list.size() > 0) {
            Log.i("weyl","页面包含 "+keyword);
            return true;
        }else {
            Log.i("weyl","页面不包含 "+keyword);
            return false;
        }
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void handleAccessbilityEvent(AccessibilityEvent event,AccessibilityService service) {
//        Log.i("weyl","event type:"+event.getEventType() + ", package name"+event.getPackageName());
        if ((event.getEventType() == AccessibilityEvent.TYPE_VIEW_SCROLLED ||
                event.getEventType() == AccessibilityEvent.TYPE_WINDOW_STATE_CHANGED ||
                event.getEventType() == AccessibilityEvent.TYPE_VIEW_FOCUSED)
                && event.getPackageName() != null && !event.getPackageName().equals(OPPO_ANDROID_SEETING)){
            if (event.getPackageName().equals(OPPO_SAFECENTER)) {
                Log.i("weyl","在oppo手机管家中");

                AccessibilityNodeInfo nodeInfo = service.getRootInActiveWindow();
                if (nodeInfo == null) {
                    return;
                }

                List<AccessibilityNodeInfo> list;


                if (pageContains(nodeInfo, mContext.getString(R.string.accessiblity_permission_privacy)) ) {
                    Log.i("weyl","当前是权限隐私首页");

                    if (!PrefUtil.getKeyBoolean(IPermissionGuideStrategy.PREF_KEY_DONE_PREFIX +
                            GuideConst.ENTER_AUTOBOOT_PERMISSION_PAGE, false)){
                        Log.i("weyl","1");
                        list = nodeInfo.findAccessibilityNodeInfosByText(
                                mContext.getString(R.string.accessiblity_permission_startup));

                        if (list!=null && list.size() > 0){
                            boolean success = list.get(0).getParent().performAction(AccessibilityNodeInfo.ACTION_CLICK);
                            try {
                                Thread.sleep(1000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            PrefUtil.setKey(IPermissionGuideStrategy.PREF_KEY_DONE_PREFIX +
                                    GuideConst.ENTER_AUTOBOOT_PERMISSION_PAGE, true);
                            Log.i("weyl","进自启动页面"+success);
                        }

                        return;
                    }else if (!PrefUtil.getKeyBoolean(IPermissionGuideStrategy.PREF_KEY_DONE_PREFIX +
                            GuideConst.ENTER_FLOAT_PERMISSION_PAGE, false)){
                        Log.i("weyl","2");
                        list = nodeInfo.findAccessibilityNodeInfosByText(
                                mContext.getString(R.string.accessiblity_permission_toast));

                        if (list!=null  && list.size() > 0){
                            boolean success = list.get(0).getParent().performAction(AccessibilityNodeInfo.ACTION_CLICK);

                            PrefUtil.setKey(IPermissionGuideStrategy.PREF_KEY_DONE_PREFIX +
                                    GuideConst.ENTER_FLOAT_PERMISSION_PAGE, true);
                            try {
                                Thread.sleep(1000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            Log.i("weyl","进悬浮窗页面" +success);
                        }

                        return;
                    }else{
                        Log.i("weyl","回退" );
                        service.performGlobalAction(GLOBAL_ACTION_BACK);

                        return;
                    }
                }


                list = nodeInfo.findAccessibilityNodeInfosByText(
                        mContext.getString(R.string.accessiblity_permission_startup));
                if (list != null && list.size() > 0 ) {
//                    printAllChild(list.get(0).getParent().getParent().getParent(),0);
                    performSwitch(service, nodeInfo,
                            mContext.getString(R.string.accessiblity_permission_touchpal), "auto_startup_1");
                    PrefUtil.setKey(IPermissionGuideStrategy.PREF_KEY_DONE_PREFIX +
                            GuideConst.AUTOBOOT_PERMISSION, true);
                    Log.i("weyl","自启动页面 权限已开启");

                    return;
                }

                if (pageContains(nodeInfo, mContext.getString(R.string.accessiblity_permission_toast)) &&
                        pageContains(nodeInfo,mContext.getString(R.string.accessiblity_permission_touchpal))) {
//                    printAllChild(list.get(0));
//                    printAllChild(list.get(0).getParent().getParent().getParent(),0);
                    performSwitch(service, nodeInfo,
                            mContext.getString(R.string.accessiblity_permission_touchpal), "auto_toast_1");
                    PrefUtil.setKey(IPermissionGuideStrategy.PREF_KEY_DONE_PREFIX +
                            GuideConst.TOAST_PERMISSION, true);
                    Log.i("weyl","悬浮窗页面 权限已开启");

                    return;
                }

                list = nodeInfo.findAccessibilityNodeInfosByText(
                        mContext.getString(R.string.accessiblity_permission_trust_app));
                if (list != null && list.size() > 0) {
                    performSwitch(service, nodeInfo,
                            mContext.getString(R.string.accessiblity_permission_trust_app), "trust_step_2");
                    PrefUtil.setKey(IPermissionGuideStrategy.PREF_KEY_DONE_PREFIX +
                            GuideConst.TRUST_APPLICATION_PERMISSION, true);
                    return;
                }

                list = nodeInfo.findAccessibilityNodeInfosByText(
                        mContext.getString(R.string.accessiblity_permission_app));
                if (list != null && list.size() > 0) {
                    performMore(service, nodeInfo,
                            mContext.getString(R.string.accessiblity_permission_touchpal),"trust_step_1");
                    return;
                }


            } else if (event.getPackageName().equals("com.coloros.oppoguardelf")) {
                AccessibilityNodeInfo nodeInfo = service.getRootInActiveWindow();
                if (nodeInfo == null) {
                    return;
                }

//                Log.i("weyl","＝＝＝＝＝＝＝＝＝＝＝＝＝＝,当前页面：");
//                printAllChild(nodeInfo,0);
                List<AccessibilityNodeInfo> list = null;
                if (getVersion() == VERSION.V6){
                    list = nodeInfo.findAccessibilityNodeInfosByText(
                            mContext.getString(R.string.accessiblity_permission_power_oppo_v6));
                }else{
                    list = nodeInfo.findAccessibilityNodeInfosByText(
                            mContext.getString(R.string.accessiblity_permission_power));
                }

                if (list != null && list.size() > 0) {
                    Log.i("weyl","当前是电池管理首页");
                    list = nodeInfo.findAccessibilityNodeInfosByText(
                            mContext.getString(R.string.accessiblity_permission_power_protect));
                    if (list != null && list.size() > 0) {
                        Log.i("weyl","点击进入耗电保护");
                        performMore(service, nodeInfo,
                                mContext.getString(R.string.accessiblity_permission_power_protect),"power_step_1");
                        return;
                    }
                    list = nodeInfo.findAccessibilityNodeInfosByText(
                            mContext.getString(R.string.accessiblity_permission_touchpal));
                    if (list != null && list.size() > 0) {
                        Log.i("weyl","点击进入触宝电话");
                        performMore(service, nodeInfo,
                                mContext.getString(R.string.accessiblity_permission_touchpal),"power_step_1");
                        return;
                    }
                    list = nodeInfo.findAccessibilityNodeInfosByText(
                            mContext.getString(R.string.accessiblity_permission_other));
                    if (list != null && list.size() > 0) {
                        performMore(service, nodeInfo, mContext.getString(R.string.accessiblity_permission_other),"power_step_1");
                        return;
                    }
                }

                if (pageContains(nodeInfo,mContext.getString(R.string.accessiblity_permission_bacground)) ||
                        pageContains(nodeInfo,mContext.getString(R.string.accessiblity_permission_bacground_1_2))){
                    Log.i("weyl","开启后台冻结+ 耗电异常自动优化");
                    performSwitch(service, nodeInfo,
                            mContext.getString(R.string.accessiblity_permission_bacground), false, false,"power_step_3");
                    performSwitch(service, nodeInfo,
                            mContext.getString(R.string.accessiblity_permission_bacground_1_2), false, false,"power_step_3");
                    if (getVersion()==VERSION.V6){
                        performSwitch(service, nodeInfo,
                                mContext.getString(R.string.accessiblity_permission_sleep_oppo_v6), false, false,"power_step_5");

                        performSwitch(service, nodeInfo,
                                mContext.getString(R.string.accessiblity_permission_exception_oppo_v6), true, false,"power_step_4");


                    }else{
                        performSwitch(service, nodeInfo,
                                mContext.getString(R.string.accessiblity_permission_exception), true, false,"power_step_4");
                        performSwitch(service, nodeInfo,
                                mContext.getString(R.string.accessiblity_permission_exception_1_2), true, false,"power_step_4");

                    }

                    PrefUtil.setKey(IPermissionGuideStrategy.PREF_KEY_DONE_PREFIX + GuideConst.OPPO_BACKGROUND_FROZEN_PERMISSION, true);
                    return;
                }

                list = nodeInfo.findAccessibilityNodeInfosByText(
                        mContext.getString(R.string.accessiblity_permission_power_protect));
                if (list != null && list.size() > 0) {
                    Log.i("weyl","点击进入触宝电话");
                    performMore(service, nodeInfo, mContext.getString(R.string.accessiblity_permission_touchpal)
                            ,"power_step_2");
                    return;
                }
                list = nodeInfo.findAccessibilityNodeInfosByText(
                        mContext.getString(R.string.accessiblity_permission_other));
                if (list != null && list.size() > 0) {
                    Log.i("weyl","点击进入触宝电话2");
                    performMore(service, nodeInfo, mContext.getString(R.string.accessiblity_permission_touchpal)
                            ,"power_step_2");
                    return;
                }



            } else if (event.getPackageName().equals("com.coloros.notificationmanager")) {
                AccessibilityNodeInfo nodeInfo = service.getRootInActiveWindow();
                if (nodeInfo == null) {
                    return;
                }
                List<AccessibilityNodeInfo> list = nodeInfo.findAccessibilityNodeInfosByText(
                        mContext.getString(R.string.accessiblity_permission_notification));
                if (list != null && list.size() > 0) {
                    performMore(service, nodeInfo,
                            mContext.getString(R.string.accessiblity_permission_touchpal),"notification_step_1");
                    return;
                }
                list = nodeInfo.findAccessibilityNodeInfosByText(
                        mContext.getString(R.string.accessiblity_permission_touchpal));
                if (list != null && list.size() > 0) {
                    performSwitch(service, nodeInfo,
                            mContext.getString(R.string.accessiblity_permission_notification_allow), "notification_step_2");
                    PrefUtil.setKey(IPermissionGuideStrategy.PREF_KEY_DONE_PREFIX + GuideConst.OPEN_NOTIFICATION, true);
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
            List<AccessibilityNodeInfo> list = nodeInfo.findAccessibilityNodeInfosByText(
                    mContext.getString(R.string.accessiblity_permission_service));
            if (list != null && list.size() > 0) {
                String txt = mContext.getString(R.string.permission_accessiblity_title);
                list = nodeInfo.findAccessibilityNodeInfosByText(txt);
                if (list != null && list.size() > 0) {
                    service.performGlobalAction(GLOBAL_ACTION_BACK);
                    return;
                }
            }
            String txt = mContext.getString(R.string.permission_accessiblity_title);
            list = nodeInfo.findAccessibilityNodeInfosByText(txt);
            if (list != null && list.size() > 0) {
                service.performGlobalAction(GLOBAL_ACTION_BACK);
                return;
            }

        }
    }


    @TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH)
    private AccessibilityNodeInfo recycle(AccessibilityNodeInfo info) {
        if (info!= null && info.getClassName() != null
                && info.getClassName().equals("android.widget.ListView") ){
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

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    private void performMore(AccessibilityService service, AccessibilityNodeInfo nodeInfo, String tagText, String key) {
        List<AccessibilityNodeInfo> list = nodeInfo.findAccessibilityNodeInfosByText(tagText);
        if (list == null || list.size() == 0) {
            AccessibilityNodeInfo info = recycle(nodeInfo);
            if(info != null) {
                info.performAction(AccessibilityNodeInfo.ACTION_SCROLL_FORWARD);
            }
            return;
        }
        AccessibilityNodeInfo parent = list.get(0).getParent();
        if (parent != null) {
            Log.d(TAG, "onAccessibilityEvent performMore= " + list.size() + ",isback=" + isGoback);
            if (!accessisbilityMap.containsKey(key)) {
                accessisbilityMap.put(key,1);
                isGoback = false;
                parent.performAction(AccessibilityNodeInfo.ACTION_CLICK);
                Log.i("weyl","performMore "+ parent.getClassName()+"");
            } else {
                service.performGlobalAction(GLOBAL_ACTION_BACK);
            }
        }
    }

    private boolean performSwitch(AccessibilityService service,AccessibilityNodeInfo nodeInfo,String tagText,String key) {
        return performSwitch(service,nodeInfo,tagText,true,true,key);
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    private boolean performSwitch(AccessibilityService service, AccessibilityNodeInfo nodeInfo, String tagText, boolean back, boolean target, String key) {
        List<AccessibilityNodeInfo> list = nodeInfo.findAccessibilityNodeInfosByText(tagText);
        if (list != null && list.size() > 0) {
            if (!accessisbilityMap.containsKey(key)) {
                accessisbilityMap.put(key, 1);
                Log.d(TAG,"onAccessibilityEvent performSwitch= " + back + ",target=" + target);
                if (getVersion() == VERSION.V6){
                    AccessibilityNodeInfo parent = list.get(0).getParent();
//                    AccessibilityNodeInfo v = parent.getChild(2);
                    Log.i("weyl","******"+parent.getChild(1).getText()+"");
                    Log.i("weyl","******"+list.get(0).getText()+"");

                    for (int i=0;i<parent.getChildCount();i++){
                        AccessibilityNodeInfo v = parent.getChild(i);
                        if (v != null && isEndable(parent) != target && v.getClassName().toString().contains("Switch")) {
                            v.performAction(AccessibilityNodeInfo.ACTION_CLICK);
                        }
                    }

                }else{
                    AccessibilityNodeInfo parent = list.get(0).getParent();
                    if (parent != null && isEndable(parent) != target) {
                        boolean sucess = parent.performAction(AccessibilityNodeInfo.ACTION_CLICK);
                        Log.i("weyl", "click "+sucess);
                        printAllChild(parent,0);
                    }
                }

                if (back) {
                    isGoback = back;
                    service.performGlobalAction(GLOBAL_ACTION_BACK);
                }
                return true;
            } else if (back) {
                isGoback = back;
                service.performGlobalAction(GLOBAL_ACTION_BACK);
            }

        } else {
            AccessibilityNodeInfo info = recycle(nodeInfo);
            if (info!= null) {
                info.performAction(AccessibilityNodeInfo.ACTION_SCROLL_FORWARD);
            }
        }
        return false;
    }

    @TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH)
    private boolean isEndable(AccessibilityNodeInfo parent) {
        if (parent != null ) {
            int count = parent.getChildCount();
            for (int j = 0; j < count; j++) {
                AccessibilityNodeInfo info = parent.getChild(j);
                Log.d(TAG,"onAccessibilityEvent isEndable= " + info.getText() + ",cls=" + info.getClassName() );
                if (info.isChecked()) {
                    return true;
                }
            }
        }
        return false;
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void configAccessbility(AccessibilityService sevice) {
        AccessibilityServiceInfo accessibilityServiceInfo = new AccessibilityServiceInfo();
        accessibilityServiceInfo.packageNames = new String[]{
                OPPO_SAFECENTER,
                "com.coloros.oppoguardelf",
                "com.coloros.notificationmanager",
                OPPO_ANDROID_SEETING
        };
        accessibilityServiceInfo.eventTypes = AccessibilityEvent.TYPES_ALL_MASK;
        accessibilityServiceInfo.feedbackType = AccessibilityServiceInfo.FEEDBACK_GENERIC;
        accessibilityServiceInfo.notificationTimeout = 1000;
        sevice.setServiceInfo(accessibilityServiceInfo);
        sevice.performGlobalAction(GLOBAL_ACTION_BACK);
    }


}
