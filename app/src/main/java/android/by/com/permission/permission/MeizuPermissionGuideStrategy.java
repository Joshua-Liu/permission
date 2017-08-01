package android.by.com.permission.permission;

import android.by.com.permission.R;
import android.by.com.permission.constant.Constants;
import android.by.com.permission.constant.GuideConst;
import android.by.com.permission.model.ModelManager;
import android.by.com.permission.permission.IPermissionGuideStrategy;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.util.Log;


import java.util.ArrayList;

/**
 * Created by frankyang on 12/9/15.
 *
 * com.meizu.safe/com.meizu.safe.SecurityCenterActivity
 * com.meizu.safe/com.meizu.safe.cleaner.RubbishCleanMainActivity
 * com.meizu.safe/com.meizu.safe.cleaner.RubbishSettingActivity
 * com.meizu.safe/com.meizu.safe.cleaner.ProcServicesWhiteListActivity
 *
 * 魅族安全中心/手机管家2.2版本即使没有开悬浮窗权限，也能够使用WindowManager，并且设置type为WindowManager.LayoutParams.TYPE_TOAST来使得指定view出现;
 * 3.6版本(MX5, MX6等)的不能;
 */
public class MeizuPermissionGuideStrategy extends IPermissionGuideStrategy {

    private static final String MEIZU_SEC_PACKAGE_NAME = "com.meizu.safe";
    private static final String MEIZU_SEC_APP_SETTINGS_ACTIVITY = "com.meizu.safe.security.AppSecActivity";

    public static final int FLYME_V4 = 0;
    public static final int FLYME_V5 = 1;

    private enum VERSION {
        SEC_2_2, //魅族安全中心/手机管家版本号: 2.2.0922, 2.2.0310;
        SEC_3_4, //魅族安全中心/手机管家版本号: 3.4.0316;
        SEC_3_6, //魅族安全中心/手机管家版本号: 3.6.0802;
        SEC_4_1, //魅族安全中心/手机管家版本号: 4.1.10;
    };

    private int mVersion;

    @Override
    protected void actionReadCalllog() {
        super.actionReadCalllog();
        try {
            Intent sysIntent = new Intent();
            sysIntent.setClassName(MEIZU_SEC_PACKAGE_NAME, MEIZU_SEC_APP_SETTINGS_ACTIVITY);
            sysIntent.putExtra(IPermissionGuideStrategy.PACKAGE_NAME_TEXT, Constants.PACKAGE_NAME);
            mContext.startActivity(sysIntent);


            final Intent intent = new Intent(mContext, OuterTwoStepPermissionActivityForMeizu.class);
            if (mSecVersion == VERSION.SEC_2_2) {
                intent.putExtra(OuterTwoStepPermissionActivity.GUIDE_TEXT_ROW_ONE, mContext.getString(R.string.permission_meizu_v2_readcalllog_step_1));
                intent.putExtra(OuterTwoStepPermissionActivity.GUIDE_IMG_ROW_ONE, R.drawable.permission_meizu_v2_readcalllog_step_1);
            } else {
                intent.putExtra(OuterTwoStepPermissionActivity.GUIDE_TEXT_ROW_ONE, mContext.getString(R.string.permission_meizu_v4_readcallog_step_1));
                intent.putExtra(OuterTwoStepPermissionActivity.GUIDE_TEXT_ROW_TWO, mContext.getString(R.string.permission_meizu_v4_readcalllog_contact_step_2));
                intent.putExtra(OuterTwoStepPermissionActivity.GUIDE_IMG_ROW_ONE, R.drawable.permission_meizu_v4_readcalllog_step_1);
                intent.putExtra(OuterTwoStepPermissionActivity.GUIDE_IMG_ROW_TWO, R.drawable.permission_meizu_v4_readcalllog_contact_step_2);
            }
            ModelManager.getInst().getHandler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    mContext.startActivity(intent);
                }
            }, 300);

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    protected void actionReadContact() {
        super.actionReadContact();
        try {
            Intent sysIntent = new Intent();
            sysIntent.setClassName(MEIZU_SEC_PACKAGE_NAME, MEIZU_SEC_APP_SETTINGS_ACTIVITY);
            sysIntent.putExtra(IPermissionGuideStrategy.PACKAGE_NAME_TEXT, Constants.PACKAGE_NAME);
            mContext.startActivity(sysIntent);

            final Intent intent = new Intent(mContext, OuterTwoStepPermissionActivityForMeizu.class);
            if (mSecVersion == VERSION.SEC_2_2) {
                intent.putExtra(OuterTwoStepPermissionActivity.GUIDE_TEXT_ROW_ONE, mContext.getString(R.string.permission_meizu_v2_readcontact_step_1));
                intent.putExtra(OuterTwoStepPermissionActivity.GUIDE_IMG_ROW_ONE, R.drawable.permission_meizu_v2_readcontact_step_1);
            } else {
                intent.putExtra(OuterTwoStepPermissionActivity.GUIDE_TEXT_ROW_ONE, mContext.getString(R.string.permission_meizu_v4_readcontact_step_1));
                intent.putExtra(OuterTwoStepPermissionActivity.GUIDE_TEXT_ROW_TWO, mContext.getString(R.string.permission_meizu_v4_readcalllog_contact_step_2));
                intent.putExtra(OuterTwoStepPermissionActivity.GUIDE_IMG_ROW_ONE, R.drawable.permission_meizu_v4_readcontact_step_1);
                intent.putExtra(OuterTwoStepPermissionActivity.GUIDE_IMG_ROW_TWO, R.drawable.permission_meizu_v4_readcalllog_contact_step_2);

            }
            ModelManager.getInst().getHandler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    mContext.startActivity(intent);
                }
            }, 300);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private VERSION mSecVersion;

    public MeizuPermissionGuideStrategy(Context mContext) {
        super(mContext);
        mVersion = getMeizuSystemManagerVersion();
        mSecVersion = getMeizuSecVersion();
    }

    @Override
    protected void actionAutoBootPermission() {
        super.actionAutoBootPermission();
        if (mSecVersion == VERSION.SEC_2_2) {
            try {
                Intent sysIntent = new Intent();
                sysIntent.setClassName(MEIZU_SEC_PACKAGE_NAME, MEIZU_SEC_APP_SETTINGS_ACTIVITY);
                sysIntent.putExtra(IPermissionGuideStrategy.PACKAGE_NAME_TEXT, Constants.PACKAGE_NAME);
                mContext.startActivity(sysIntent);
                Intent guideIntent = new Intent(mContext, OuterPermissionActivity.class);
                guideIntent.putExtra(OuterPermissionActivity.VIEWSTUB_ID, R.layout.meizu_autoboot_permission);
                guideIntent.putExtra(OuterPermissionActivity.GUIDE_NO_BTN, true);
                mContext.startActivity(guideIntent);
            } catch (Exception e) {
            }
        } else if (mSecVersion == VERSION.SEC_3_4 || mSecVersion == VERSION.SEC_3_6) {
            try {
                Intent sysIntent = new Intent();
                sysIntent.setClassName(MEIZU_SEC_PACKAGE_NAME, MEIZU_SEC_APP_SETTINGS_ACTIVITY);
                sysIntent.putExtra(IPermissionGuideStrategy.PACKAGE_NAME_TEXT, Constants.PACKAGE_NAME);
                mContext.startActivity(sysIntent);
                Intent guideIntent = new Intent(mContext, OuterPermissionActivityForMeizu.class);
                guideIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                guideIntent.putExtra(OuterPermissionActivity.VIEWSTUB_ID, R.layout.meizu_autoboot_permission);
                guideIntent.putExtra(OuterPermissionActivity.GUIDE_NO_BTN, true);
                mContext.startActivity(guideIntent);
            } catch (Exception e) {
            }
        }
    }

    @Override
    protected void actionToastPermission() {
        super.actionToastPermission();
        if (mSecVersion == VERSION.SEC_2_2) {
            try {
                Intent sysIntent = new Intent();
                sysIntent.setClassName(MEIZU_SEC_PACKAGE_NAME, MEIZU_SEC_APP_SETTINGS_ACTIVITY);
                sysIntent.putExtra(IPermissionGuideStrategy.PACKAGE_NAME_TEXT, Constants.PACKAGE_NAME);
                mContext.startActivity(sysIntent);
                Intent guideIntent = new Intent(mContext, OuterPermissionActivity.class);
                guideIntent.putExtra(OuterPermissionActivity.VIEWSTUB_ID, R.layout.meizu_toast_permission);
                guideIntent.putExtra(OuterPermissionActivity.GUIDE_NO_BTN, true);
                mContext.startActivity(guideIntent);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (mSecVersion == VERSION.SEC_3_4 || mSecVersion == VERSION.SEC_3_6) {
            try {
                Intent sysIntent = new Intent();
                sysIntent.setClassName(MEIZU_SEC_PACKAGE_NAME, MEIZU_SEC_APP_SETTINGS_ACTIVITY);
                sysIntent.putExtra(IPermissionGuideStrategy.PACKAGE_NAME_TEXT, Constants.PACKAGE_NAME);
                mContext.startActivity(sysIntent);
                Intent guideIntent = new Intent(mContext, OuterPermissionActivityForMeizu.class);
                guideIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                guideIntent.putExtra(OuterPermissionActivity.VIEWSTUB_ID, R.layout.meizu_security_v3_toast_permission);
                guideIntent.putExtra(OuterPermissionActivity.GUIDE_NO_BTN, true);
                mContext.startActivity(guideIntent);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (mSecVersion == VERSION.SEC_4_1) {
            try {
                Intent sysIntent = new Intent();
                sysIntent.setClassName(MEIZU_SEC_PACKAGE_NAME, MEIZU_SEC_APP_SETTINGS_ACTIVITY);
                sysIntent.putExtra(IPermissionGuideStrategy.PACKAGE_NAME_TEXT, Constants.PACKAGE_NAME);
                mContext.startActivity(sysIntent);
                final Intent guideIntent = new Intent(mContext, OuterPermissionActivityForMeizu.class);
                guideIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                guideIntent.putExtra(OuterPermissionActivity.VIEWSTUB_ID, R.layout.meizu_security_v4_toast_permission);
                guideIntent.putExtra(OuterPermissionActivity.GUIDE_NO_BTN, true);
                mContext.startActivity(guideIntent);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    protected void actionBackgroundPermisssion() {
        super.actionBackgroundPermisssion();
        if (mSecVersion == VERSION.SEC_2_2) {
            try {
                Intent sysIntent = new Intent();
                sysIntent.setClassName(MEIZU_SEC_PACKAGE_NAME, "com.meizu.safe.cleaner.RubbishCleanMainActivity");
                mContext.startActivity(sysIntent);
                Intent guideIntent = new Intent(mContext, OuterPermissionActivityForMeizu.class);
                guideIntent.putExtra(OuterPermissionActivity.VIEWSTUB_ID, R.layout.meizu_background_permission);
                guideIntent.putExtra(OuterPermissionActivity.GUIDE_NO_BTN, true);
                mContext.startActivity(guideIntent);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (mSecVersion == VERSION.SEC_3_4) {
            try {
                Intent sysIntent = new Intent();
                sysIntent.setClassName(MEIZU_SEC_PACKAGE_NAME, "com.meizu.safe.powerui.AppPowerManagerActivity");
                mContext.startActivity(sysIntent);
                Intent guideIntent = new Intent(mContext, OuterPermissionActivityForMeizu.class);
                guideIntent.putExtra(OuterPermissionActivity.VIEWSTUB_ID, R.layout.general_permission_guide_mask);
                guideIntent.putExtra(OuterPermissionActivity.GUIDEPIC_ID, R.drawable.meizu_security_v3_background_permission_step3);
                guideIntent.putExtra(OuterPermissionActivity.GUIDE_HINTONE, mContext.getString(R.string.miui_permission_guide_template_one, mContext.getString(R.string.app_name)));
                guideIntent.putExtra(OuterPermissionActivity.GUIDE_HINTTWO, mContext.getString(R.string.meizu_security_v3_background_permission_step3_text));
                mContext.startActivity(guideIntent);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (mSecVersion == VERSION.SEC_4_1) {
            try {
                Intent sysIntent = new Intent();
                sysIntent.setClassName(MEIZU_SEC_PACKAGE_NAME, MEIZU_SEC_APP_SETTINGS_ACTIVITY);
                sysIntent.putExtra(IPermissionGuideStrategy.PACKAGE_NAME_TEXT, Constants.PACKAGE_NAME);
                mContext.startActivity(sysIntent);
                final Intent intent = new Intent(mContext, OuterTwoStepPermissionActivityForMeizu.class);
                intent.putExtra(OuterTwoStepPermissionActivity.GUIDE_TEXT_ROW_ONE, mContext.getString(R.string.meizu_permission_background_v4_step_1));
                intent.putExtra(OuterTwoStepPermissionActivity.GUIDE_TEXT_ROW_TWO, mContext.getString(R.string.meizu_permission_background_v4_step_2));
                intent.putExtra(OuterTwoStepPermissionActivity.GUIDE_IMG_ROW_ONE, R.drawable.meizu_security_v4_background_step1);
                intent.putExtra(OuterTwoStepPermissionActivity.GUIDE_IMG_ROW_TWO, R.drawable.meizu_security_v4_background_step2);
                mContext.startActivity(intent);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    protected void actionDataPermission() {
        super.actionDataPermission();
        ModelManager.getInst().registerContentObserver(ModelManager.getContext(), true);
//        ModelManager.getInst().getSMSMessage().syncObsoleteSms();
        // if getting location authority and getting other authority occur at the same time, mx5 will anr
        ModelManager.getInst().getHandler().postDelayed(new Runnable() {
            @Override
            public void run() {
                //by修改
//                WebSearchLocateManager.getInst().getLatestInfo();
            }
        }, 500);
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
    protected void actionWhiteListPermisssion() {
        super.actionWhiteListPermisssion();
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.addCategory(Intent.CATEGORY_HOME);
        mContext.startActivity(intent);
    }

    @Override
    public ArrayList<String> getPermissionList(int type) {
        ArrayList<String> ret = new ArrayList<String>();
        if (type == IPermissionGuideStrategy.TUTORIAL_TYPE) {
            if (mSecVersion == VERSION.SEC_4_1) {
                ret.add(GuideConst.MIUI_BACKGROUND_PROTECT_PERMISSION); //使用了和miui一样的文案;
                ret.add(GuideConst.TOAST_PERMISSION);
            } else {
                ret.add(GuideConst.AUTOBOOT_PERMISSION);
                ret.add(GuideConst.TOAST_PERMISSION);
                if (mSecVersion == VERSION.SEC_2_2 || mSecVersion == VERSION.SEC_3_4) {
                    ret.add(GuideConst.BACKGROUND_PROTECT_PERMISSION);
                }
            }
        } else if (type == IPermissionGuideStrategy.INAPP_TYPE){
            ret.add(GuideConst.AUTOBOOT_PERMISSION);
            if (mVersion == FLYME_V5 && Build.VERSION.SDK_INT > 19) {
                ret.add(GuideConst.BACKGROUND_PROTECT_PERMISSION);
                ret.add(GuideConst.MEIZU_WHITE_LIST);
            }
        } else if (type == IPermissionGuideStrategy.TOAST_TYPE){
            ret.add(GuideConst.TOAST_PERMISSION);
        } else if (type == IPermissionGuideStrategy.INAPP_SECOND_GUIDE_TYPE) {
            return super.getSecondGuidePermissionList();
        }

        return ret;
    }

    @Override
    protected String getPermissionTitle() {
        return ModelManager.getContext().getString(R.string.permission_guide_title, mContext.getString(R.string.meizu_manufacturer));
    }

    @Override
    protected String getPermissionTitle(String permission, int type) {
        String os = mContext.getString(R.string.meizu_manufacturer);
        if (type == IPermissionGuideStrategy.START_UP_TYPE) {
            return ModelManager.getContext().getString(R.string.permission_guide_title, os);
        } else if (type == IPermissionGuideStrategy.INAPP_TYPE) {
            return ModelManager.getContext().getString(R.string.important_permission_guide_title, os);
        } else if (type == IPermissionGuideStrategy.TUTORIAL_TYPE) {
            return ModelManager.getContext().getString(R.string.permission_guide_title_new, os);
        } else if (GuideConst.AUTOBOOT_PERMISSION.equals(permission)) {
            return mVersion == FLYME_V4 ? mContext.getString(R.string.important_permission_guide_title, os)
                    : ModelManager.getContext().getString(R.string.autoboot_permission_title);
        } else if (GuideConst.BACKGROUND_PROTECT_PERMISSION.equals(permission)) {
            return ModelManager.getContext().getString(R.string.permission_background_power_meizu5_title);
        } else if (GuideConst.MEIZU_WHITE_LIST.equals(permission)) {
            return ModelManager.getContext().getString(R.string.background_permission_title);
        } else if (GuideConst.TOAST_PERMISSION.equals(permission)) {
            return ModelManager.getContext().getString(R.string.permission_title_toast, os);
        }

        return getPermissionTitle();
    }

    @Override
    protected PermissionGuideStepItem getPermissionGuideStepItem(String permission, int type) {
        PermissionGuideStepItem stepItem = null;
        if (GuideConst.AUTOBOOT_PERMISSION.equals(permission)) {
            stepItem = new PermissionGuideStepItem(
                    type == IPermissionGuideStrategy.TUTORIAL_TYPE
                            ? R.string.permission_others_title_tutorial
                            : mVersion == FLYME_V4 ? R.string.permission_autoboot_meizu4_title : -1,
                    new int[]{R.string.autoboot_permission_hint},
                    new int[][]{new int[]{mVersion == FLYME_V4 ? R.drawable.meizu4_autoboot_permission : R.drawable.meizu5_autoboot_permission}}
            );
        } else if (GuideConst.TOAST_PERMISSION.equals(permission)) {
            stepItem = new PermissionGuideStepItem(
                    type == IPermissionGuideStrategy.TUTORIAL_TYPE
                            ? R.string.permission_toast_title_tutorial
                            : R.string.permission_title_toast_single,
                    new int[]{mVersion == FLYME_V4 ? R.string.permission_toast_meizu4 : R.string.permission_toast_meizu5},
                    new int[][]{new int[]{mVersion == FLYME_V4 ? R.drawable.meizu4_toast_permission : R.drawable.meizu5_toast_permission}}
            );
        } else if (GuideConst.BACKGROUND_PROTECT_PERMISSION.equals(permission)) {
            stepItem = new PermissionGuideStepItem(
                    -1,
                    new int[]{R.string.permission_background_power_meizu5},
                    new int[][]{new int[]{R.drawable.meizu5_background_power_permission}}
            );
        } else if (GuideConst.MEIZU_WHITE_LIST.equals(permission)) {
            stepItem = new PermissionGuideStepItem(
                    -1,
                    new int[]{R.string.permission_white_list_meizu5_step1, R.string.permission_white_list_meizu5_step2},
                    new int[][]{new int[]{R.drawable.meizu5_background_lock_permission_01}, new int[]{R.drawable.meizu5_background_lock_permission_02}}
            );
        }
        return stepItem;
    }

    private int getMeizuSystemManagerVersion() {
        int version = 0;
        int versionNum = 0;
        try {
            PackageManager manager = mContext.getPackageManager();
            PackageInfo info = manager.getPackageInfo("com.meizu.safe", 0);
            String versionStr = info.versionName;
            String versionTmp[] = versionStr.split("\\.");
            versionNum = Integer.parseInt(versionTmp[0]);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (versionNum == 2 || versionNum == 1) {
            version = FLYME_V4;
        } else if (versionNum == 3) {
            version = FLYME_V5;
        }
        return version;
    }

    private VERSION getMeizuSecVersion() {
        VERSION v;
        try {
            PackageManager manager = mContext.getPackageManager();
            PackageInfo info = manager.getPackageInfo("com.meizu.safe", 0);
            String versionStr = info.versionName; //2.2.0922;
            Log.i("ycsss", versionStr);
            if (versionStr.startsWith("2")) {
                v = VERSION.SEC_2_2;
            } else if (versionStr.startsWith("3")) {
                int d = Integer.parseInt(versionStr.substring(2, 3));
                Log.i("ycsss", "d: " + d);
                if (d <= 4) {
                    v = VERSION.SEC_3_4;
                } else {
                    v = VERSION.SEC_3_6;
                }
            } else if (versionStr.startsWith("4")) {
                v = VERSION.SEC_4_1;
            } else {
                v = VERSION.SEC_4_1;
            }
        } catch (Exception e) {
            v = VERSION.SEC_4_1;
        }
        return v;
    }
}
