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
 * Created by yimao on 16-8-27.
 */
public class Oppo_2_0_PermissionGuideStrategy extends IPermissionGuideStrategy {

    private static final String OPPO_SAFECENTER = "com.oppo.safe";
    private static final String OPPO_ANDROID_SEETING = "com.android.settings";
    private static final String OPPO_POWER_BACKGROUND = "com.oppo.purebackground";
    private final String TAG = "OppoColor";
    private boolean mAutoGuide;
    private VERSION mVersion;
    private HashMap<String, Object> accessisbilityMap = new HashMap<String, Object>();
    public static final String[] OPPO_AUTOBOOT_ACTIVITY_NAMES = new String[] {
            "com.oppo.safe.permission.startup.StartupAppListActivity",
    };

    public static final String[] OPPO_TRUST_APPLICATION_ACTIVITY_NAMES = new String[] {
            "com.oppo.safe.permission.PermissionSettingsActivity",
    };

    public static final String[] OPPO_TOAST_ACTIVITY_NAMES = new String[] {
            "com.color.safecenter.SecureSafeMainSettingsActivity"
    };

    private enum VERSION {
        V1, // <=V1.02
        V2, // >=V1.03.a21cc03_151215 <2
        V3, // >=2.0
    }

    private static final String OPPO_BACKGROUND_ACTIVITY_NAME = "com.oppo.purebackground.Purebackground_AddTrust_Activity";

    public Oppo_2_0_PermissionGuideStrategy(Context mContext,boolean auto ) {
        super(mContext);
        mAutoGuide = auto;
        mVersion = getVersion();
    }

    @Override
    protected void actionReadCalllog() {
        super.actionReadCalllog();
        funcTrustApplicationPermission();
    }

    @Override
    protected void actionReadContact() {
        super.actionReadContact();
        funcTrustApplicationPermission();
    }

    @Override
    protected void actionTrustApplicationPermission(boolean showGuide) {
        super.actionTrustApplicationPermission(showGuide);
        funcTrustApplicationPermission();
    }

    public void funcTrustApplicationPermission() {
        try {
            int startupIndex = getPermissionPackageIndex();
            Intent sysIntent = new Intent();
            sysIntent.setClassName(
                    PackageUtil.OPPO_2_0_PERMISSION_SETTING_PACKAGE_NAMES[startupIndex],
                    OPPO_TRUST_APPLICATION_ACTIVITY_NAMES[startupIndex]);
            mContext.startActivity(sysIntent);
            if (!mAutoGuide) {
                showGuideMaskTrustApplicationPermission();
            }
        } catch (ActivityNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void showGuideMaskTrustApplicationPermission() {
        Intent intent = new Intent(mContext, OuterTwoStepPermissionActivity.class);
        intent.putExtra(OuterTwoStepPermissionActivity.GUIDE_TEXT_ROW_ONE, mContext.getString(R.string.permission_oppo_1107_step_1));
        intent.putExtra(OuterTwoStepPermissionActivity.GUIDE_TEXT_ROW_TWO, mContext.getString(R.string.permission_oppo_1107_step_2));
        intent.putExtra(OuterTwoStepPermissionActivity.GUIDE_TEXT_ROW_THREE, mContext.getString(R.string.permission_oppo_1107_step_3));
        intent.putExtra(OuterTwoStepPermissionActivity.GUIDE_IMG_ROW_ONE, R.drawable.permission_oppo_1107_step_1);
        intent.putExtra(OuterTwoStepPermissionActivity.GUIDE_IMG_ROW_TWO, R.drawable.permission_oppo_1107_step_2);
        intent.putExtra(OuterTwoStepPermissionActivity.GUIDE_IMG_ROW_THREE, R.drawable.permission_oppo_1107_step_3);
        intent.putExtra(OuterTwoStepPermissionActivity.GUIDE_NEED_GESTURE_ONE, true);
        intent.putExtra(OuterTwoStepPermissionActivity.GUIDE_NEED_GESTURE_MARGIN_LEFT_ONE, R.dimen.permission_oppo_trust_gesture_maring_left_step1);
        mContext.startActivity(intent);

    }

    @Override
    protected void actionAutoBootPermission() {
        super.actionAutoBootPermission();
        try {
            int startupIndex = getPermissionPackageIndex();
            Intent sysIntent = new Intent();
            sysIntent.setClassName(
                    PackageUtil.OPPO_2_0_PERMISSION_SETTING_PACKAGE_NAMES[startupIndex],
                    OPPO_AUTOBOOT_ACTIVITY_NAMES[startupIndex]);
            mContext.startActivity(sysIntent);
            if (!mAutoGuide) {
                Intent guideIntent = new Intent(mContext, OuterPermissionActivity.class);
                guideIntent.putExtra(OuterPermissionActivity.VIEWSTUB_ID, R.layout.general_permission_guide_mask);
                guideIntent.putExtra(OuterPermissionActivity.GUIDEPIC_ID, R.drawable.oppo_v20_permission_mask_autoboot);
                guideIntent.putExtra(OuterPermissionActivity.GUIDE_HINTONE, mContext.getString(R.string.miui_permission_guide_template_one, mContext.getString(R.string.app_name)));
                guideIntent.putExtra(OuterPermissionActivity.GUIDE_HINTTWO, mContext.getString(R.string.miui_permission_action_switch_on));
                guideIntent.putExtra(OuterPermissionActivity.GUIDE_NO_BTN, true);
                mContext.startActivity(guideIntent);
            }
        } catch (ActivityNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void actionBackgroundPermisssion() {
        super.actionBackgroundPermisssion();
        Intent sysIntent = new Intent(Settings.ACTION_APPLICATION_SETTINGS);
        sysIntent.putExtra("packageName", Constants.PACKAGE_NAME);
        mContext.startActivity(sysIntent);
        if (!mAutoGuide) {
            Intent guideIntent = new Intent(mContext, OuterPermissionActivity.class);
            guideIntent.putExtra(OuterPermissionActivity.VIEWSTUB_ID, R.layout.general_permission_guide_mask);
            guideIntent.putExtra(OuterPermissionActivity.GUIDEPIC_ID, R.drawable.oppo_v20_permission_mask_background);
            guideIntent.putExtra(OuterPermissionActivity.GUIDE_HINTONE, mContext.getString(R.string.miui_permission_guide_template_one, mContext.getString(R.string.app_name)));
            guideIntent.putExtra(OuterPermissionActivity.GUIDE_HINTTWO, mContext.getString(R.string.permission_mask_background_protection_oppo));
            guideIntent.putExtra(OuterPermissionActivity.GUIDE_INDICATOR_RIGHT_MARGIN, (int) mContext.getResources().getDisplayMetrics().density * 80);
            guideIntent.putExtra(OuterPermissionActivity.GUIDE_NO_BTN, true);
            mContext.startActivity(guideIntent);
        }
    }

    @Override
    protected void actionBackgroundRuningPermission() {
        super.actionBackgroundRuningPermission();
        try {
            Intent sysIntent = new Intent();
            sysIntent.setClassName(OPPO_POWER_BACKGROUND, OPPO_BACKGROUND_ACTIVITY_NAME);
            mContext.startActivity(sysIntent);
        } catch (Exception e) {
            PrefUtil.setKey(IPermissionGuideStrategy.PREF_KEY_DONE_PREFIX +
                    GuideConst.BACKGROUND_RUNNING_PERMISSION, true);
        }
    }

    @Override
    protected void actionToastPermission() {
        super.actionToastPermission();
        try {
            int startupIndex = getPermissionPackageIndex();
            Intent sysIntent = new Intent();
            sysIntent.setClassName(
                    PackageUtil.OPPO_2_0_PERMISSION_SETTING_PACKAGE_NAMES[startupIndex],
                    OPPO_TOAST_ACTIVITY_NAMES[startupIndex]);
            mContext.startActivity(sysIntent);
//            ScenarioCollector.customEvent("native oppo_v20_has_start_toast_suc");
        } catch (ActivityNotFoundException e) {
//            ScenarioCollector.customEvent("native oppo_v20_has_start_toast_failed");
            e.printStackTrace();
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
    protected int getColor() {
        return super.getColor();
    }

    @Override
    protected int getPressedColor() {
        return super.getPressedColor();
    }

    @Override
    public ArrayList<String> getPermissionList(int type) {
        ArrayList<String> ret = new ArrayList<String>();
        if (type == IPermissionGuideStrategy.ACCESSIBLITY_UPGRADE) {
            ret.add(GuideConst.AUTOBOOT_PERMISSION);
            if (mVersion == VERSION.V2 || mVersion == VERSION.V3) {
                ret.add(GuideConst.BACKGROUND_RUNNING_PERMISSION);
            }
            ret.add(GuideConst.BACKGROUND_PROTECT_PERMISSION);
        }else if (type == IPermissionGuideStrategy.TUTORIAL_TYPE) {
            ret.add(GuideConst.AUTOBOOT_PERMISSION);
            if (mVersion == VERSION.V2 || mVersion == VERSION.V3) {
                ret.add(GuideConst.BACKGROUND_RUNNING_PERMISSION);
            }
            ret.add(GuideConst.BACKGROUND_PROTECT_PERMISSION);
        } else if (type == IPermissionGuideStrategy.INAPP_TYPE) {
            ret.add(GuideConst.AUTOBOOT_PERMISSION);
            ret.add(GuideConst.BACKGROUND_PROTECT_PERMISSION);
        } else if (type == IPermissionGuideStrategy.START_UP_TYPE){
            ret.add(GuideConst.TRUST_APPLICATION_PERMISSION);
        } else if (type == IPermissionGuideStrategy.TOAST_TYPE){
            ret.add(GuideConst.TOAST_PERMISSION);
        }
        return ret;
    }

    @Override
    protected String getPermissionTitle() {
        return ModelManager.getContext().getString(R.string.permission_guide_title, "OPPO");
    }

    private VERSION getVersion() {
        try {
            PackageManager manager = mContext.getPackageManager();
            PackageInfo info = manager.getPackageInfo(OPPO_SAFECENTER, 0);
            String versionStr = info.versionName;
            Log.d(TAG,"is2Version =" + versionStr);

            try {
                PrefUtil.setKey(PrefKeys.OPPO_COLOR_OS_VERSION, versionStr.replace(" ", ""));
            } catch (Exception e) {
                e.printStackTrace();
            }

            if(versionStr != null && versionStr.startsWith("2")) {
                 return VERSION.V3;
            } else if (versionStr != null && versionStr.startsWith("V1.03")) {
                 return VERSION.V2;
            } else {
                return VERSION.V1;
            }
        } catch (Exception e) {
        }
        return VERSION.V1;
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
        return 0;
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void handleAccessbilityEvent(AccessibilityEvent event, AccessibilityService service) {
        Log.d(TAG,"handleAccessbilityEvent = " + event.getPackageName() + ",type=" + event.getEventType());
        if ((event.getEventType() == AccessibilityEvent.TYPE_VIEW_SCROLLED ||
                event.getEventType() == AccessibilityEvent.TYPE_WINDOW_STATE_CHANGED)
                && event.getPackageName() != null &&
                (event.getPackageName().equals(OPPO_SAFECENTER) ||
                event.getPackageName().equals(OPPO_POWER_BACKGROUND))) {
            AccessibilityNodeInfo nodeInfo = service.getRootInActiveWindow();
            if (nodeInfo == null) {
                return;
            }
            String tag = mContext.getString(R.string.accessiblity_permission_startup_1_2);
            if (VERSION.V3 == mVersion) {
                tag = mContext.getString(R.string.accessiblity_permission_startup);
            } else if(VERSION.V2 == mVersion) {
                tag = mContext.getString(R.string.accessiblity_permission_startup_1_3);
            }
            List<AccessibilityNodeInfo> list = nodeInfo.findAccessibilityNodeInfosByText(tag);
            if (list != null && list.size() > 0) {
                performSwitch(service, nodeInfo,
                        mContext.getString(R.string.accessiblity_permission_touchpal),"auto_startup_1");
                PrefUtil.setKey(IPermissionGuideStrategy.PREF_KEY_DONE_PREFIX +
                        GuideConst.AUTOBOOT_PERMISSION, true);
                return;
            }

            list = nodeInfo.findAccessibilityNodeInfosByText(
                    mContext.getString(R.string.accessiblity_permission_add_app));
            if (list != null && list.size() > 0) {
                if(performSwitch(service, nodeInfo,
                        mContext.getString(R.string.accessiblity_permission_touchpal), false, true, "background_running_1")) {
                    performClick(service, nodeInfo,
                            mContext.getString(R.string.accessiblity_permission_confirm), false);
                    PrefUtil.setKey(IPermissionGuideStrategy.PREF_KEY_DONE_PREFIX +
                            GuideConst.BACKGROUND_RUNNING_PERMISSION, true);
                }
                return;
            }
        } else if ((event.getEventType() == AccessibilityEvent.TYPE_WINDOW_STATE_CHANGED ||
                event.getEventType() == AccessibilityEvent.TYPE_VIEW_FOCUSED ||
                event.getEventType() == AccessibilityEvent.TYPE_WINDOW_CONTENT_CHANGED)
                && event.getPackageName() != null
                && event.getPackageName().equals(OPPO_ANDROID_SEETING))
        {
            AccessibilityNodeInfo nodeInfo = service.getRootInActiveWindow();
            if (nodeInfo == null) {
                return;
            }
            String tag = mContext.getString(R.string.accessiblity_permission_app_program);
            if (VERSION.V3 == mVersion) {
                tag = mContext.getString(R.string.accessiblity_permission_app_mananer);
            }
            List<AccessibilityNodeInfo> list = nodeInfo.findAccessibilityNodeInfosByText(tag);
            if(list != null && list.size() > 0) {
                performLock(service,nodeInfo,
                        mContext.getString(R.string.accessiblity_permission_touchpal),true,true);
                PrefUtil.setKey(IPermissionGuideStrategy.PREF_KEY_DONE_PREFIX +
                        GuideConst.BACKGROUND_PROTECT_PERMISSION, true);
                return;
            }
            list = nodeInfo.findAccessibilityNodeInfosByText(mContext.getString(R.string.accessiblity_permission_service));
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
    private void performClick(AccessibilityService service, AccessibilityNodeInfo nodeInfo, String tagText, boolean back) {
        List<AccessibilityNodeInfo> list = nodeInfo.findAccessibilityNodeInfosByText(tagText);
        if (list == null || list.size() == 0) {
            return;
        }
        Log.d(TAG,"onAccessibilityEvent performClick= " + list.size());
        AccessibilityNodeInfo info = list.get(0);
        info.getParent().performAction(AccessibilityNodeInfo.ACTION_CLICK);
        if (back) {
            service.performGlobalAction(GLOBAL_ACTION_BACK);
        }
    }


    private boolean performSwitch(AccessibilityService service,AccessibilityNodeInfo nodeInfo,String tagText,String key) {
        return performSwitch(service,nodeInfo,tagText,true,true,key);
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    private void performLock(AccessibilityService service, AccessibilityNodeInfo nodeInfo, String tagText, boolean back, boolean target) {
        List<AccessibilityNodeInfo> list = nodeInfo.findAccessibilityNodeInfosByText(tagText);
        Log.d(TAG,"onAccessibilityEvent performSwitch= " + list.size());
        if (list != null && list.size() > 0) {
            AccessibilityNodeInfo parent = list.get(0).getParent();
            if (parent != null ) {
                int count = parent.getChildCount();
                for (int j = 0; j < count; j++) {
                    AccessibilityNodeInfo info = parent.getChild(j);
                    if (info.getClassName().equals("android.widget.CheckBox")) {
                        if (info.isChecked() != target) {
                            info.performAction(AccessibilityNodeInfo.ACTION_CLICK);
                        }
                        break;
                    }
                }
            }
            if (back) {
                service.performGlobalAction(GLOBAL_ACTION_BACK);
            }
        } else {
            AccessibilityNodeInfo info = recycle(nodeInfo);
            if (info!= null) {
                info.performAction(AccessibilityNodeInfo.ACTION_SCROLL_FORWARD);
            }
        }
    }
    
    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    private boolean performSwitch(AccessibilityService service, AccessibilityNodeInfo nodeInfo,
                                  String tagText, boolean back, boolean target, String key) {
        List<AccessibilityNodeInfo> list = nodeInfo.findAccessibilityNodeInfosByText(tagText);
        if (list != null && list.size() > 0) {
            if (!accessisbilityMap.containsKey(key)) {
                accessisbilityMap.put(key, 1);
                Log.d(TAG, "onAccessibilityEvent performSwitch= " + back + ",target=" + target);
                AccessibilityNodeInfo parent = list.get(0).getParent();
                if (parent != null && isEndable(parent) != target) {
                    parent.performAction(AccessibilityNodeInfo.ACTION_CLICK);
                }
                if (back) {
                    service.performGlobalAction(GLOBAL_ACTION_BACK);
                }
                return true;
            } else if (back) {
                service.performGlobalAction(GLOBAL_ACTION_BACK);
            }
        } else {
            AccessibilityNodeInfo info = recycle(nodeInfo);
            if (info != null) {
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
        Log.d(TAG,"configAccessbility...");
        AccessibilityServiceInfo accessibilityServiceInfo = new AccessibilityServiceInfo();
        accessibilityServiceInfo.packageNames = new String[]{
                OPPO_SAFECENTER,
                OPPO_POWER_BACKGROUND,
                OPPO_ANDROID_SEETING
        };
        accessibilityServiceInfo.eventTypes = AccessibilityEvent.TYPES_ALL_MASK;
        accessibilityServiceInfo.feedbackType = AccessibilityServiceInfo.FEEDBACK_GENERIC;
        accessibilityServiceInfo.notificationTimeout = 1000;
        sevice.setServiceInfo(accessibilityServiceInfo);
        sevice.performGlobalAction(GLOBAL_ACTION_BACK);
    }
}
