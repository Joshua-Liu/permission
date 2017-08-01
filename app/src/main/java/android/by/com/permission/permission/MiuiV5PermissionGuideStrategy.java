package android.by.com.permission.permission;

import android.annotation.TargetApi;
import android.by.com.permission.R;
import android.by.com.permission.constant.GuideConst;
import android.by.com.permission.model.ModelManager;
import android.by.com.permission.pref.PrefKeys;
import android.by.com.permission.util.PrefUtil;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.util.Log;


import java.util.ArrayList;

/**
 * Created by frankyang on 12/7/15.
 */
public class MiuiV5PermissionGuideStrategy extends IPermissionGuideStrategy {


    private static final String TAG = "MiuiV5Permission";
    public MiuiV5PermissionGuideStrategy(Context mContext) {
        super(mContext);
    }

    @Override
    protected void actionReadCalllog() {
        super.actionReadCalllog();
        actionTrustApplicationPermission(true);
    }

    @Override
    protected void actionReadContact() {
        super.actionReadContact();
        actionTrustApplicationPermission(true);
    }

    @TargetApi(Build.VERSION_CODES.BASE_1_1)
    @Override
    protected void actionTrustApplicationPermission(boolean showGuide) {
        super.actionTrustApplicationPermission(showGuide);
        try {
            Intent sysIntent = new Intent();
            sysIntent.putExtra("extra_package_uid", android.os.Process.myUid());
            sysIntent.setClassName(GuideConst.MIUI_V5_PERMISSION_PACKAGE_NAME, GuideConst.MIUI_V5_APP_PERMISSION_ACTIVITY_NAME);
            mContext.startActivity(sysIntent);
            Intent guideIntent = new Intent(mContext, OuterPermissionActivity.class);
            guideIntent.putExtra(OuterPermissionActivity.VIEWSTUB_ID, R.layout.miui_v5_trustapplication_permission);
            guideIntent.putExtra(OuterPermissionActivity.GUIDE_NO_BTN, true);
            mContext.startActivity(guideIntent);
        } catch (ActivityNotFoundException e) {
            Log.i(TAG,e.getMessage());
        }
    }

    @Override
    protected void actionAutoBootPermission() {
        super.actionAutoBootPermission();
        try {
            Intent sysIntent = new Intent();
            sysIntent.putExtra("extra_package_uid", android.os.Process.myUid());
            sysIntent.setClassName(GuideConst.MIUI_V5_PERMISSION_PACKAGE_NAME, GuideConst.MIUI_V5_APP_PERMISSION_ACTIVITY_NAME);
            mContext.startActivity(sysIntent);
            Intent guideIntent = new Intent(mContext, OuterPermissionActivity.class);
            guideIntent.putExtra(OuterPermissionActivity.VIEWSTUB_ID, R.layout.miui_v5_autoboot_permission);
            guideIntent.putExtra(OuterPermissionActivity.GUIDE_NO_BTN, true);
            mContext.startActivity(guideIntent);
        } catch (ActivityNotFoundException e) {
            Log.i(TAG,e.getMessage());
        }
    }

    @Override
    protected void actionBackgroundPermisssion() {
        super.actionBackgroundPermisssion();
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.addCategory(Intent.CATEGORY_HOME);
        mContext.startActivity(intent);
    }

    @TargetApi(Build.VERSION_CODES.BASE_1_1)
    @Override
    protected void actionCallPhone() {
        super.actionCallPhone();
        try {
            Intent sysIntent = new Intent();
            sysIntent.putExtra("extra_package_uid", android.os.Process.myUid());
            sysIntent.setClassName(GuideConst.MIUI_V5_PERMISSION_PACKAGE_NAME, GuideConst.MIUI_V5_APP_PERMISSION_ACTIVITY_NAME);
            mContext.startActivity(sysIntent);
            final Intent guideIntent = new Intent(mContext, OuterPermissionActivity.class);
            guideIntent.putExtra(OuterPermissionActivity.VIEWSTUB_ID, R.layout.miui5_call_phone_permission);
            ModelManager.getInst().getHandler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    mContext.startActivity(guideIntent);
                }
            }, 100);
        } catch (Exception e) {
        }
    }

    @Override
    protected void actionToastPermission() {
        super.actionToastPermission();
        try {
            int i = Build.VERSION.SDK_INT;
            Intent sysIntent = new Intent();
            String packageName = mContext.getPackageName();
            if (i > 8) {
                sysIntent.setAction("android.settings.APPLICATION_DETAILS_SETTINGS");
                sysIntent.setData(Uri.fromParts("package",
                        packageName, null));
            } else {
                sysIntent.setAction("android.intent.action.VIEW");
                sysIntent.setClassName("com.android.settings",
                        "com.android.settings.InstalledAppDetails");
                sysIntent.putExtra("pkg", packageName);
            }
            mContext.startActivity(sysIntent);
            Intent guideIntent = new Intent(mContext, OuterPermissionActivity.class);
            guideIntent.putExtra(OuterPermissionActivity.VIEWSTUB_ID, R.layout.miui_v5_toast_permission);
            guideIntent.putExtra(OuterPermissionActivity.GUIDE_NO_BTN, true);
            mContext.startActivity(guideIntent);
        } catch (ActivityNotFoundException e) {
            Log.i(TAG,e.getMessage());
        }
    }

    @Override
    protected void actionDataPermission() {
        super.actionDataPermission();
        ModelManager.getInst().registerContentObserver(ModelManager.getContext(), true);
//        ModelManager.getInst().getSMSMessage().syncObsoleteSms();
    }

    @Override
    protected void actionPermissionDeny(int type) {
        super.actionPermissionDeny(type);
        if (GuideConst.CALL_PERMISSION != type
                &&  GuideConst.CALLLOG_PERMISSION != type
                && GuideConst.CONTACT_PERMISSON != type ) {
            throw new IllegalArgumentException();
        }
        actionTrustApplicationPermission(true);
    }

    @Override
    protected void actionCallogOrContactPermission() {
        super.actionCallogOrContactPermission();
        try {
            Intent sysIntent = new Intent();
            sysIntent.putExtra("extra_package_uid", android.os.Process.myUid());
            sysIntent.setClassName(GuideConst.MIUI_V5_PERMISSION_PACKAGE_NAME, GuideConst.MIUI_V5_APP_PERMISSION_ACTIVITY_NAME);
            mContext.startActivity(sysIntent);
            final Intent guideIntent = new Intent(mContext, MiuiCalllogOrContactGuide.class);
            ModelManager.getInst().getHandler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    mContext.startActivity(guideIntent);
                }
            }, 200);

        } catch (ActivityNotFoundException e) {
            Log.i(TAG,e.getMessage());
        }
    }

    @Override
    public ArrayList<String> getPermissionList(int type) {
        ArrayList<String> ret =  new ArrayList<String>();
        if (type == IPermissionGuideStrategy.TUTORIAL_TYPE) {
            ret.add(GuideConst.TRUST_APPLICATION_PERMISSION_MIUI5);
            ret.add(GuideConst.AUTOBOOT_PERMISSION);
            ret.add(GuideConst.TOAST_PERMISSION);
        } else if (type == IPermissionGuideStrategy.INAPP_TYPE) {
            ret.add(GuideConst.MIUI_BACKGROUND_PROTECT_PERMISSION);
        } else if (type == IPermissionGuideStrategy.START_UP_TYPE){
            ret.add(GuideConst.TRUST_APPLICATION_PERMISSION_MIUI5);
        } else if (type == IPermissionGuideStrategy.TOAST_TYPE){
            ret.add(GuideConst.TOAST_PERMISSION);
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
        return ModelManager.getContext().getString(R.string.permission_guide_title,"MIUI");
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
        if (GuideConst.TRUST_APPLICATION_PERMISSION_MIUI5.equals(permission)) {
            stepItem = new PermissionGuideStepItem(
                    type == IPermissionGuideStrategy.START_UP_TYPE
                            ? R.string.permission_trust_title
                            : type == IPermissionGuideStrategy.TUTORIAL_TYPE ? R.string.permission_trust_title_tutorial : -1,
                    new int[]{R.string.permission_trust_miuiv5},
                    new int[][]{new int[]{R.drawable.permission_trust_miuiv5}}
            );
        } else if (GuideConst.TOAST_PERMISSION.equals(permission)) {
            stepItem = new PermissionGuideStepItem(
                    type == IPermissionGuideStrategy.TUTORIAL_TYPE ? R.string.permission_toast_title_tutorial : R.string.permission_title_toast_single,
                    new int[]{R.string.permission_toast_miuiv5},
                    new int[][]{new int[]{R.drawable.permission_toast_miuiv5}}
            );
        } else if (GuideConst.MIUI_BACKGROUND_PROTECT_PERMISSION.equals(permission)) {
            stepItem = new PermissionGuideStepItem(
                    R.string.permission_others_title_tutorial,
                    new int[]{R.string.background_protection_step_1_miui, R.string.background_protection_step_2_miui},
                    new int[][]{new int[]{R.drawable.background_protection_miuiv5_01}, new int[]{R.drawable.background_protection_miui_02}}
            );
        }
        return stepItem;
    }
}
