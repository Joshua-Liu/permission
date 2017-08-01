package android.by.com.permission.permission;

import android.by.com.permission.R;
import android.by.com.permission.constant.Constants;
import android.by.com.permission.constant.GuideConst;
import android.by.com.permission.model.ModelManager;
import android.by.com.permission.permission.IPermissionGuideStrategy;
import android.by.com.permission.util.PackageUtil;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;



import java.util.ArrayList;

/**
 * Created by herculewang on 16/6/27.
 */
public class ZtePermissionGuideStrategy extends IPermissionGuideStrategy {

    public static final int VERSION_6 = 1;
    public static final int VERSION_7 = 2;

    private int mVersion;

    public static int getPermissionManagerVersion(){
        int ret = -1;

        try {
            String versionName = PackageUtil.getVersionName(GuideConst.ZTE_PERMISSION_PACKAGE_NAME);
            String[] versionTmp = versionName.split("\\.");
            int mainVersion = Integer.valueOf(versionTmp[0]);
            if (mainVersion == 6) {
                ret = VERSION_6;
            } else if (mainVersion == 7) {
                ret = VERSION_7;
            }
        } catch (Exception e) {}

        return ret;
    }

    public ZtePermissionGuideStrategy(Context context) {
        super(context);
        mVersion = getPermissionManagerVersion();
    }

    @Override
    protected boolean supportGuide() {
        return false;
    }

    @Override
    protected void actionTrustApplicationPermission(boolean showGuide) {
        super.actionTrustApplicationPermission(showGuide);
        try {
            Intent sysIntent = new Intent();
            sysIntent.setClassName(GuideConst.ZTE_PERMISSION_PACKAGE_NAME,
                    mVersion == VERSION_6 ? GuideConst.ZTE_PERMISSION_ACTIVITY_V6 : GuideConst.ZTE_PERMISSION_ACTIVITY_V7);
            if (mVersion == VERSION_7) {
                Bundle bundle = new Bundle();
                bundle.putString("apkname", Constants.PACKAGE_NAME);
                bundle.putString("appname", mContext.getString(R.string.app_name));
                sysIntent.putExtra("AppPermsCtrl", bundle);
            }
            mContext.startActivity(sysIntent);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void actionAutoBootPermission() {
        super.actionAutoBootPermission();
        try {
            Intent sysIntent = new Intent();
            sysIntent.setClassName(mVersion == VERSION_6 ? GuideConst.ZTE_PERMISSION_PACKAGE_AUTOBOOT_V6 : GuideConst.ZTE_PERMISSION_PACKAGE_NAME,
                    mVersion == VERSION_6 ? GuideConst.ZTE_AUTOBOOT_ACTIVITY_V6 : GuideConst.ZTE_AUTOBOOT_ACTIVITY_V7);
            mContext.startActivity(sysIntent);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void actionBackgroundPermisssion() {
        super.actionBackgroundPermisssion();
        try {
            Intent sysIntent = new Intent();
            sysIntent.setClassName(GuideConst.ZTE_PERMISSION_PACKAGE_NAME,
                    GuideConst.ZTE_BACKGROUND_ACTIVITY_V7);
            mContext.startActivity(sysIntent);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void actionWhiteListPermisssion() {
        super.actionWhiteListPermisssion();
        try {
            Intent sysIntent = new Intent();
            sysIntent.setClassName(GuideConst.ZTE_PERMISSION_PACKAGE_NAME,
                     GuideConst.ZTE_WHITELIST_ACTIVITY_V6);
            mContext.startActivity(sysIntent);
        } catch (Exception e) {
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
    public ArrayList<String> getPermissionList(int type) {
        ArrayList<String> ret = new ArrayList<String>();
        if (type == IPermissionGuideStrategy.TUTORIAL_TYPE) {
            ret.add(GuideConst.TRUST_APPLICATION_PERMISSION);
            ret.add(GuideConst.AUTOBOOT_PERMISSION);
            ret.add(mVersion == VERSION_6 ? GuideConst.ZTE_WHITE_LIST : GuideConst.BACKGROUND_PROTECT_PERMISSION);
        } else if (type == IPermissionGuideStrategy.INAPP_TYPE) {
            ret.add(GuideConst.AUTOBOOT_PERMISSION);
            ret.add(mVersion == VERSION_6 ? GuideConst.ZTE_WHITE_LIST : GuideConst.BACKGROUND_PROTECT_PERMISSION);
        } else if (type == IPermissionGuideStrategy.START_UP_TYPE){
            ret.add(GuideConst.TRUST_APPLICATION_PERMISSION);
        }

        return ret;
    }

    @Override
    protected String getPermissionTitle() {
        return mContext.getString(R.string.permission_guide_title, mContext.getString(R.string.zte_manufacturer));
    }

    @Override
    protected String getPermissionTitle(String permission, int type) {
        String os = mContext.getString(R.string.zte_manufacturer);
        if (type == IPermissionGuideStrategy.START_UP_TYPE) {
            return mContext.getString(R.string.permission_guide_title, os);
        } else if (type == IPermissionGuideStrategy.INAPP_TYPE) {
            return mContext.getString(R.string.important_permission_guide_title, os);
        } else if (type == IPermissionGuideStrategy.TUTORIAL_TYPE) {
            return mContext.getString(R.string.permission_title_tutorial);
        } else if (GuideConst.AUTOBOOT_PERMISSION.equals(permission)) {
            return mContext.getString(R.string.autoboot_permission_title);
        } else if (GuideConst.ZTE_WHITE_LIST.equals(permission)) {
            return mContext.getString(R.string.white_list_permission_title_zte);
        } else if (GuideConst.TRUST_APPLICATION_PERMISSION.equals(permission)) {
            return mContext.getString(R.string.permission_title_call);
        } else if (GuideConst.BACKGROUND_PROTECT_PERMISSION.equals(permission)) {
            return mContext.getString(R.string.background_permission_title_zte);
        }

        return getPermissionTitle();
    }

    @Override
    protected PermissionGuideStepItem getPermissionGuideStepItem(String permission, int type) {
        PermissionGuideStepItem stepItem = null;
        if (GuideConst.TRUST_APPLICATION_PERMISSION.equals(permission)) {
            stepItem = new PermissionGuideStepItem(
                    type == IPermissionGuideStrategy.START_UP_TYPE
                            ? R.string.permission_trust_title_miuiv6
                            : type == IPermissionGuideStrategy.TUTORIAL_TYPE ? R.string.permission_trust_title_tutorial : -1,
                    mVersion == VERSION_6 ? new int[] {R.string.permission_trust_step_1_ztev6, R.string.permission_trust_step_2_ztev6}
                                          : new int[] {R.string.permission_trust_step_1_ztev7},
                    mVersion == VERSION_6 ? new int[][] {new int[]{R.drawable.zte_trust_permission_v6_1},
                                                         new int[]{R.drawable.zte_trust_permission_v6_2, R.drawable.zte_trust_permission_v6_3, R.drawable.zte_trust_permission_v6_4}}
                                          : new int[][] {new int[]{R.drawable.zte_trust_permission_v7_1, R.drawable.zte_trust_permission_v7_2}}
            );
        } else if (GuideConst.AUTOBOOT_PERMISSION.equals(permission)) {
            stepItem = new PermissionGuideStepItem(
                    type == IPermissionGuideStrategy.TUTORIAL_TYPE ? R.string.permission_others_title_tutorial : -1,
                    new int[]{R.string.autoboot_permission_hint},
                    mVersion == VERSION_6
                            ? new int[][]{new int[]{R.drawable.zte_autoboot_permission_v6}}
                            : new int[][]{new int[]{R.drawable.zte_autoboot_permission_v7}}
            );
        } else if (GuideConst.ZTE_WHITE_LIST.equals(permission)) {
            stepItem = new PermissionGuideStepItem(
                    -1,
                    new int[]{R.string.permission_white_list_zte},
                    new int[][]{new int[]{R.drawable.zte_whitelist_permission}}
            );
        } else if (GuideConst.BACKGROUND_PROTECT_PERMISSION.equals(permission)) {
            stepItem = new PermissionGuideStepItem(
                    -1,
                    new int[]{R.string.background_permission_zte},
                    new int[][]{new int[]{R.drawable.zte_background_permission}}
            );
        }
        return stepItem;
    }
}
