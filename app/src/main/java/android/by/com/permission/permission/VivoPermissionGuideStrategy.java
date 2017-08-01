package android.by.com.permission.permission;

import android.by.com.permission.R;
import android.by.com.permission.constant.GuideConst;
import android.by.com.permission.model.ModelManager;
import android.by.com.permission.util.PackageUtil;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;


import java.util.ArrayList;

/**
 * Created by frankyang on 12/9/15.
 */
public class VivoPermissionGuideStrategy extends IPermissionGuideStrategy {

    private static final String VIVO_WHITE_LIST_ACTIVITY = "com.iqoo.secure.ui.phoneoptimize.AddWhiteListActivity";
    private static final String VIVO_MAIN_ACTIVITY = "com.iqoo.secure.MainActivity";
    private static final String VIVO_POWER_MANAGER_PACKAGE = "com.vivo.abe";
    private static final String VIVO_POWER_MANAGER_ACTIVITY = "com.vivo.applicationbehaviorengine.ui.ExcessivePowerManagerActivity";
    private static final String VIVO_POWER_MANAGER_PACKAGE_IV4 = "com.vivo.abeui";
    private static final String VIVO_POWER_MANAGER_PACKAGE_IV4_1 = "com.vivo.abe";
    private static final String VIVO_POWER_MANAGER_ACTIVITY_IV4 =  "com.vivo.abeui.highpower.ExcessivePowerManagerActivity";
    private static final String VIVO_POWER_MANAGER_ACTIVITY_IV4_1 =  "com.vivo.applicationbehaviorengine.ui.ExcessivePowerManagerActivity";
    private static final String VIVO_SECURE_MANAGER_PACKAGE_IV4 = "com.iqoo.secure";
    private static final String VIVO_SECURE_MANAGER_ACTIVITY_IV4 = "com.iqoo.secure.safeguard.PurviewTabActivity";

    private static final String VIVO_SECURE_MANAGER_ACTIVITY_IV4_1 = "com.iqoo.secure.safeguard.SoftPermissionDetailActivity";


    private static final String VIVO_SECURE_PERMISSION_ATIVITY = "com.iqoo.secure.MainGuideActivity";


    //vivo手机i管家版本号;
    public enum I_VERSION {
        I_VERSION_X_1, //一台比较旧的vivo Xplay 4.2.2大屏手机，从2013年开始就在黄页组名下;
        I_VERSION_1,
        I_VERSION_2,
        I_VERSION_3,
        I_VERSION_3_2,
        I_VERSION_4,
        I_VERSION_4_1,
    };

    public static final int VERSION_1 = 0;
    public static final int VERSION_2_X = 1;
    public static final int VERSION_3 = 2;

    private int mVersion;
    private I_VERSION mIVersion;

    public VivoPermissionGuideStrategy(Context mContext) {
        super(mContext);
        mVersion = getVivoManagerVersion();
        mIVersion = getIVersion();
    }

    @Override
    protected void actionTrustApplicationPermission(boolean showGuide) {
        super.actionTrustApplicationPermission(showGuide);
        try {
            if (showGuide) {
                if (mIVersion == I_VERSION.I_VERSION_1) {
                    Intent sysIntent = new Intent();
                    sysIntent.setClassName(PackageUtil.VIVO_PHONE_MANAGER, VIVO_SECURE_PERMISSION_ATIVITY);
                    mContext.startActivity(sysIntent);
                    final Intent intent = new Intent(mContext, OuterTwoStepPermissionActivity.class);
                    intent.putExtra(OuterTwoStepPermissionActivity.GUIDE_TEXT_ROW_ONE, mContext.getString(R.string.permission_vivo_readcalllog_contact_step_1));
                    intent.putExtra(OuterTwoStepPermissionActivity.GUIDE_TEXT_ROW_TWO, mContext.getString(R.string.permission_vivo_readcalllog_contact_step_2));
                    intent.putExtra(OuterTwoStepPermissionActivity.GUIDE_TEXT_ROW_THREE, mContext.getString(R.string.permission_vivo_readcalllog_contact_step_3));
                    intent.putExtra(OuterTwoStepPermissionActivity.GUIDE_IMG_ROW_ONE, R.drawable.permission_vivo_v1_readcalllog_contact_step_1);
                    intent.putExtra(OuterTwoStepPermissionActivity.GUIDE_IMG_ROW_ONE_TWO, R.drawable.permission_vivo_v1_readcalllog_contact_step_2);
                    intent.putExtra(OuterTwoStepPermissionActivity.GUIDE_IMG_ROW_TWO, R.drawable.permission_vivo_v1_readcalllog_contact_step_3);
                    intent.putExtra(OuterTwoStepPermissionActivity.GUIDE_IMG_ROW_THREE, R.drawable.permission_vivo_readcalllog_contact_step_4);
                    ModelManager.getInst().getHandler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            mContext.startActivity(intent);
                        }
                    }, 300);
                } else if (mIVersion == I_VERSION.I_VERSION_2)  {
                    Intent sysIntent = new Intent();
                    sysIntent.setClassName(PackageUtil.VIVO_PHONE_MANAGER, VIVO_SECURE_PERMISSION_ATIVITY);
                    mContext.startActivity(sysIntent);
                    final Intent intent = new Intent(mContext, OuterTwoStepPermissionActivity.class);
                    intent.putExtra(OuterTwoStepPermissionActivity.GUIDE_TEXT_ROW_ONE, mContext.getString(R.string.permission_vivo_readcalllog_contact_step_1));
                    intent.putExtra(OuterTwoStepPermissionActivity.GUIDE_TEXT_ROW_TWO, mContext.getString(R.string.permission_vivo_readcalllog_contact_step_2));
                    intent.putExtra(OuterTwoStepPermissionActivity.GUIDE_TEXT_ROW_THREE, mContext.getString(R.string.permission_vivo_readcalllog_contact_step_3));
                    intent.putExtra(OuterTwoStepPermissionActivity.GUIDE_IMG_ROW_ONE, R.drawable.permission_vivo_v2_readcalllog_contact_step_1);
                    intent.putExtra(OuterTwoStepPermissionActivity.GUIDE_IMG_ROW_ONE_TWO, R.drawable.permission_vivo_v2_readcalllog_contact_step_2);
                    intent.putExtra(OuterTwoStepPermissionActivity.GUIDE_IMG_ROW_TWO, R.drawable.permission_vivo_v2_readcalllog_contact_step_3);
                    intent.putExtra(OuterTwoStepPermissionActivity.GUIDE_IMG_ROW_THREE, R.drawable.permission_vivo_readcalllog_contact_step_4);
                    ModelManager.getInst().getHandler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            mContext.startActivity(intent);
                        }
                    }, 300);
                } else if (mIVersion == I_VERSION.I_VERSION_3 || mIVersion == I_VERSION.I_VERSION_3_2){
                    Intent i1 = new Intent();
                    i1.setClassName(PackageUtil.VIVO_PHONE_MANAGER, VIVO_MAIN_ACTIVITY);
                    mContext.startActivity(i1);
                    final Intent intent = new Intent(mContext, OuterTwoStepPermissionActivityForVIVOi3.class);
                    intent.putExtra(OuterTwoStepPermissionActivity.GUIDE_TEXT_ROW_ONE, mContext.getString(R.string.permission_vivo_readcalllog_contact_step_1));
                    intent.putExtra(OuterTwoStepPermissionActivity.GUIDE_TEXT_ROW_TWO, mContext.getString(R.string.permission_vivo_readcalllog_contact_step_2));
                    intent.putExtra(OuterTwoStepPermissionActivity.GUIDE_TEXT_ROW_THREE, mContext.getString(R.string.permission_vivo_readcalllog_contact_step_3));
                    intent.putExtra(OuterTwoStepPermissionActivity.GUIDE_IMG_ROW_ONE, R.drawable.permission_vivo_v3_readcalllog_contact_step_1);
                    intent.putExtra(OuterTwoStepPermissionActivity.GUIDE_IMG_ROW_ONE_TWO, R.drawable.permission_vivo_v3_readcalllog_contact_step_2);
                    intent.putExtra(OuterTwoStepPermissionActivity.GUIDE_IMG_ROW_TWO, R.drawable.permission_vivo_v3_readcalllog_contact_step_3);
                    intent.putExtra(OuterTwoStepPermissionActivity.GUIDE_IMG_ROW_THREE, R.drawable.permission_vivo_readcalllog_contact_step_4);
                    ModelManager.getInst().getHandler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            mContext.startActivity(intent);
                        }
                    }, 800);
                }
            }
        } catch (ActivityNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void actionBackgroundUseCPUPermission(){
        super.actionBackgroundUseCPUPermission();
        try {
            switch (mIVersion) {
                case I_VERSION_3_2:
                    //在i管家的版本号大于3.2的情况下我们打开后台高耗电的允许权限。问题在于跳到权限设置后无法弹出眼膜，否则会导致bug。
                    Intent sysIntent = new Intent();
                    sysIntent.setClassName(VIVO_POWER_MANAGER_PACKAGE, VIVO_POWER_MANAGER_ACTIVITY);
                    mContext.startActivity(sysIntent);
                    break;
                case I_VERSION_4_1:
                    Intent sysIntent1 = new Intent();
                    sysIntent1.setClassName(VIVO_POWER_MANAGER_PACKAGE_IV4_1, VIVO_POWER_MANAGER_ACTIVITY_IV4_1);
                    mContext.startActivity(sysIntent1);
                    break;
                case I_VERSION_4:
                    Intent sysIntent2 = new Intent();
                    sysIntent2.setClassName(VIVO_POWER_MANAGER_PACKAGE_IV4, VIVO_POWER_MANAGER_ACTIVITY_IV4);
                    mContext.startActivity(sysIntent2);
                    break;

            }
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    @Override
    protected void actionAutoBootPermission() {
        super.actionAutoBootPermission();
        try {
            switch (mIVersion) {
                case I_VERSION_1:
                case I_VERSION_2:
                    Intent sysIntent = new Intent();
                    //经过反编译i管家，即iqooSecure.apk(com.iqoo.secure)后发现，com.iqoo.secure.MainActivity无法被启动，因为其exported属性默认为false;
                    //但是com.iqoo.secure.MainGuideActivity有intent-filter可以被起来;
                    sysIntent.setClassName(PackageUtil.VIVO_PHONE_MANAGER, VIVO_SECURE_PERMISSION_ATIVITY);
                    mContext.startActivity(sysIntent);
                    final Intent guideIntent = new Intent(mContext, OuterTwoStepPermissionActivity.class);
                    guideIntent.putExtra(OuterTwoStepPermissionActivity.GUIDE_TEXT_ROW_ONE, mContext.getString(R.string.permission_vivo_2_0_autoboot_step1_text));
                    guideIntent.putExtra(OuterTwoStepPermissionActivity.GUIDE_TEXT_ROW_TWO, mContext.getString(R.string.permission_vivo_2_0_autoboot_step2_text));
                    guideIntent.putExtra(OuterTwoStepPermissionActivity.GUIDE_TEXT_ROW_THREE, mContext.getString(R.string.meizu_security_v3_background_permission_step3_text));
                    guideIntent.putExtra(OuterTwoStepPermissionActivity.GUIDE_IMG_ROW_ONE, R.drawable.permission_autoboot_vivo_2_0_step_1);
                    guideIntent.putExtra(OuterTwoStepPermissionActivity.GUIDE_IMG_ROW_TWO, R.drawable.permission_autoboot_vivo_2_0_step_2);
                    guideIntent.putExtra(OuterTwoStepPermissionActivity.GUIDE_IMG_ROW_THREE, R.drawable.permission_autoboot_vivo_2_0_step_3);
                    guideIntent.putExtra(OuterTwoStepPermissionActivity.GUIDE_NEED_GESTURE_ONE, true);
                    guideIntent.putExtra(OuterTwoStepPermissionActivity.GUIDE_NEED_GESTURE_THREE, true);
                    guideIntent.putExtra(OuterTwoStepPermissionActivity.GUIDE_NEED_GESTURE_MARGIN_LEFT_ONE, R.dimen.permission_autoboot_vivo_2_0_gesture_margin_left_step1);
                    guideIntent.putExtra(OuterTwoStepPermissionActivity.GUIDE_NEED_GESTURE_MARGIN_LEFT_THREE, R.dimen.permission_oppo_trust_gesture_maring_left_step3);
                    ModelManager.getInst().getHandler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            mContext.startActivity(guideIntent);
                        }
                    }, 300);
                    break;

                case I_VERSION_3:
                case I_VERSION_3_2:
                    Intent i1 = new Intent();
                    i1.setClassName(PackageUtil.VIVO_PHONE_MANAGER, VIVO_MAIN_ACTIVITY);
                    mContext.startActivity(i1);
                    final Intent g2 = new Intent(mContext, OuterTwoStepPermissionActivityForVIVOi3.class);
                    g2.putExtra(OuterTwoStepPermissionActivity.GUIDE_TEXT_ROW_ONE, mContext.getString(R.string.permission_vivo_2_0_autoboot_step1_text));
                    g2.putExtra(OuterTwoStepPermissionActivity.GUIDE_TEXT_ROW_TWO, mContext.getString(R.string.permission_vivo_2_0_autoboot_step2_text));
                    g2.putExtra(OuterTwoStepPermissionActivity.GUIDE_TEXT_ROW_THREE, mContext.getString(R.string.meizu_security_v3_background_permission_step3_text));
                    g2.putExtra(OuterTwoStepPermissionActivity.GUIDE_IMG_ROW_ONE, R.drawable.permission_autoboot_vivo_3_0_step_1);
                    g2.putExtra(OuterTwoStepPermissionActivity.GUIDE_IMG_ROW_TWO, R.drawable.permission_autoboot_vivo_3_0_step_2);
                    g2.putExtra(OuterTwoStepPermissionActivity.GUIDE_IMG_ROW_THREE, R.drawable.permission_autoboot_vivo_3_0_step_3);
                    g2.putExtra(OuterTwoStepPermissionActivity.GUIDE_NEED_GESTURE_ONE, true);
                    g2.putExtra(OuterTwoStepPermissionActivity.GUIDE_NEED_GESTURE_THREE, true);
                    g2.putExtra(OuterTwoStepPermissionActivity.GUIDE_NEED_GESTURE_MARGIN_LEFT_ONE, R.dimen.permission_autoboot_vivo_2_0_gesture_margin_left_step1);
                    g2.putExtra(OuterTwoStepPermissionActivity.GUIDE_NEED_GESTURE_MARGIN_LEFT_THREE, R.dimen.permission_oppo_trust_gesture_maring_left_step3);
                    ModelManager.getInst().getHandler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            mContext.startActivity(g2);
                        }
                    }, 300);
                    break;
                case I_VERSION_4:
                    Intent sysIntent2 = new Intent();
                    sysIntent2.setClassName(VIVO_SECURE_MANAGER_PACKAGE_IV4, VIVO_SECURE_MANAGER_ACTIVITY_IV4);
                    mContext.startActivity(sysIntent2);
                    final Intent guideIntent2 = new Intent(mContext, OuterPermissionActivity.class);
                    guideIntent2.putExtra(OuterPermissionActivity.VIEWSTUB_ID, R.layout.general_permission_guide_mask);
                    guideIntent2.putExtra(OuterPermissionActivity.GUIDEPIC_ID, R.drawable.permission_autoboot_vivo_4_0);
                    guideIntent2.putExtra(OuterPermissionActivity.GUIDE_HINTONE, mContext.getString(R.string.miui_permission_guide_template_one, mContext.getString(R.string.app_name)));
                    guideIntent2.putExtra(OuterPermissionActivity.GUIDE_HINTTWO, mContext.getString(R.string.permission_autoboot_vivo_4_0));
                    ModelManager.getInst().getHandler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            mContext.startActivity(guideIntent2);
                        }
                    }, 500);
                    break;
                case I_VERSION_4_1:
                    Intent sysIntent3 = new Intent();
                    sysIntent3.setClassName(VIVO_SECURE_MANAGER_PACKAGE_IV4, VIVO_SECURE_MANAGER_ACTIVITY_IV4_1);
                    sysIntent3.setAction("secure.intent.action.softPermissionDetail");
                    sysIntent3.putExtra("packagename", mContext.getPackageName());
                    mContext.startActivity(sysIntent3);

                    final Intent guideIntent3 = new Intent(mContext, OuterTwoStepPermissionActivity.class);
                    guideIntent3.putExtra(OuterTwoStepPermissionActivity.GUIDE_TEXT_ROW_ONE, mContext.getString(R.string.permission_autoboot_vivo_4_0));
                    guideIntent3.putExtra(OuterTwoStepPermissionActivity.GUIDE_IMG_ROW_ONE, R.drawable.permission_autoboot_vivo_4_0);
                    guideIntent3.putExtra(OuterTwoStepPermissionActivity.GUIDE_NEED_GESTURE_ONE, true);
                    guideIntent3.putExtra(OuterTwoStepPermissionActivity.GUIDE_NEED_GESTURE_MARGIN_LEFT_ONE, R.dimen.permission_autoboot_vivo_4_0_gesture_margin_left_step1);
                    ModelManager.getInst().getHandler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            mContext.startActivity(guideIntent3);
                        }
                    }, 500);
                    break;
            }
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
    protected void actionReadCalllog() {
        super.actionReadCalllog();
        try {
            if (mIVersion == I_VERSION.I_VERSION_4) {

                Intent sysIntent2 = new Intent();
                sysIntent2.setClassName(VIVO_SECURE_MANAGER_PACKAGE_IV4, VIVO_SECURE_MANAGER_ACTIVITY_IV4);
                mContext.startActivity(sysIntent2);

                final Intent intent = new Intent(mContext, OuterTwoStepPermissionActivity.class);
                intent.putExtra(OuterTwoStepPermissionActivity.GUIDE_TEXT_ROW_ONE, mContext.getString(R.string.permission_vivo_v4_readcalllog_contact_step_1));
                intent.putExtra(OuterTwoStepPermissionActivity.GUIDE_TEXT_ROW_TWO, mContext.getString(R.string.permission_vivo_v4_readcalllog_step_2));
                intent.putExtra(OuterTwoStepPermissionActivity.GUIDE_TEXT_ROW_THREE, mContext.getString(R.string.permission_vivo_v4_readcalllog_step_3));
                intent.putExtra(OuterTwoStepPermissionActivity.GUIDE_IMG_ROW_ONE, R.drawable.permission_vivo_v4_readcalllog_contact_step_1);
                intent.putExtra(OuterTwoStepPermissionActivity.GUIDE_IMG_ROW_TWO, R.drawable.permission_vivo_v4_readcalllog_step_2);
                intent.putExtra(OuterTwoStepPermissionActivity.GUIDE_IMG_ROW_THREE, R.drawable.permission_vivo_v4_readcalllog_contact_step_3);
                ModelManager.getInst().getHandler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mContext.startActivity(intent);
                    }
                }, 300);
            } else if (mIVersion == I_VERSION.I_VERSION_4_1){

                Intent sysIntent2 = new Intent();
                sysIntent2.putExtra("packagename", mContext.getPackageName());
                sysIntent2.setClassName(VIVO_SECURE_MANAGER_PACKAGE_IV4, VIVO_SECURE_MANAGER_ACTIVITY_IV4_1);
                sysIntent2.setAction("secure.intent.action.softPermissionDetail");
                mContext.startActivity(sysIntent2);

                final Intent intent = new Intent(mContext, OuterTwoStepPermissionActivity.class);
                intent.putExtra(OuterTwoStepPermissionActivity.GUIDE_TEXT_ROW_TWO, mContext.getString(R.string.permission_vivo_v4_1_readcalllog_step_1));
                intent.putExtra(OuterTwoStepPermissionActivity.GUIDE_TEXT_ROW_THREE, mContext.getString(R.string.permission_vivo_v4_1_readcalllog_step_2));
                intent.putExtra(OuterTwoStepPermissionActivity.GUIDE_IMG_ROW_TWO, R.drawable.permission_vivo_v4_readcalllog_step_2);
                intent.putExtra(OuterTwoStepPermissionActivity.GUIDE_IMG_ROW_THREE, R.drawable.permission_vivo_v4_readcalllog_contact_step_3);
                ModelManager.getInst().getHandler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mContext.startActivity(intent);
                    }
                }, 300);
            } else{
                actionTrustApplicationPermission(true);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    protected void actionReadContact() {
        super.actionReadContact();
        try {
            if (mIVersion == I_VERSION.I_VERSION_4) {

                Intent sysIntent2 = new Intent();
                sysIntent2.setClassName(VIVO_SECURE_MANAGER_PACKAGE_IV4, VIVO_SECURE_MANAGER_ACTIVITY_IV4);
                mContext.startActivity(sysIntent2);

                final Intent intent = new Intent(mContext, OuterTwoStepPermissionActivity.class);
                intent.putExtra(OuterTwoStepPermissionActivity.GUIDE_TEXT_ROW_ONE, mContext.getString(R.string.permission_vivo_v4_readcalllog_contact_step_1));
                intent.putExtra(OuterTwoStepPermissionActivity.GUIDE_TEXT_ROW_TWO, mContext.getString(R.string.permission_vivo_v4_readcalllog_step_2));
                intent.putExtra(OuterTwoStepPermissionActivity.GUIDE_TEXT_ROW_THREE, mContext.getString(R.string.permission_vivo_v4_readcalllog_step_3));
                intent.putExtra(OuterTwoStepPermissionActivity.GUIDE_IMG_ROW_ONE, R.drawable.permission_vivo_v4_readcalllog_contact_step_1);
                intent.putExtra(OuterTwoStepPermissionActivity.GUIDE_IMG_ROW_TWO, R.drawable.permission_vivo_v4_readcontact_step_2);
                intent.putExtra(OuterTwoStepPermissionActivity.GUIDE_IMG_ROW_THREE, R.drawable.permission_vivo_v4_readcalllog_contact_step_3);
                ModelManager.getInst().getHandler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mContext.startActivity(intent);
                    }
                }, 300);
            } else if (mIVersion == I_VERSION.I_VERSION_4_1) {
                Intent sysIntent2 = new Intent();
                sysIntent2.setClassName(VIVO_SECURE_MANAGER_PACKAGE_IV4, VIVO_SECURE_MANAGER_ACTIVITY_IV4_1);
                sysIntent2.putExtra("packagename", mContext.getPackageName());
                sysIntent2.setAction("secure.intent.action.softPermissionDetail");
                mContext.startActivity(sysIntent2);

                final Intent intent = new Intent(mContext, OuterTwoStepPermissionActivity.class);
                intent.putExtra(OuterTwoStepPermissionActivity.GUIDE_TEXT_ROW_TWO, mContext.getString(R.string.permission_vivo_v4_1_readcontact_step_1));
                intent.putExtra(OuterTwoStepPermissionActivity.GUIDE_TEXT_ROW_THREE, mContext.getString(R.string.permission_vivo_v4_1_readcontact_step_2));
                intent.putExtra(OuterTwoStepPermissionActivity.GUIDE_IMG_ROW_TWO, R.drawable.permission_vivo_v4_readcontact_step_2);
                intent.putExtra(OuterTwoStepPermissionActivity.GUIDE_IMG_ROW_THREE, R.drawable.permission_vivo_v4_readcalllog_contact_step_3);
                ModelManager.getInst().getHandler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mContext.startActivity(intent);
                    }
                }, 300);
            } else {
                actionTrustApplicationPermission(true);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
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
    protected void actionOpenNotification() {
        super.actionOpenNotification();

        try{
            switch (mIVersion) {
                case I_VERSION_4_1:
                    Intent sysIntent = new Intent();
                    sysIntent.setAction("android.settings.APPLICATION_DETAILS_SETTINGS");
                    sysIntent.setData(Uri.parse("package:"+mContext.getPackageName()));
                    mContext.startActivity(sysIntent);

                    final Intent guideIntent3 = new Intent(mContext, OuterTwoStepPermissionActivityForVivoV4.class);
                    guideIntent3.putExtra(OuterTwoStepPermissionActivity.GUIDE_TEXT_ROW_ONE, mContext.getString(R.string.permission_notificatioin_vivo_4_1_step_1));
                    guideIntent3.putExtra(OuterTwoStepPermissionActivity.GUIDE_TEXT_ROW_TWO, mContext.getString(R.string.permission_notificatioin_vivo_4_1_step_2));
                    guideIntent3.putExtra(OuterTwoStepPermissionActivity.GUIDE_IMG_ROW_ONE, R.drawable.permission_vivo_notification_v4_1_step_1);
                    guideIntent3.putExtra(OuterTwoStepPermissionActivity.GUIDE_IMG_ROW_TWO, R.drawable.permission_vivo_notification_v4_1_step_2);
                    guideIntent3.putExtra(OuterTwoStepPermissionActivity.GUIDE_IMG_ROW_TWO_TWO, R.drawable.permission_vivo_notification_v4_1_step_3);
                    ModelManager.getInst().getHandler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            mContext.startActivity(guideIntent3);
                        }
                    }, 500);
                    break;

            }
        }catch(Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    protected void actionBackgroundPermisssion() {
        super.actionBackgroundPermisssion();
        try {
            switch (mIVersion) {
                case I_VERSION_1:
                case I_VERSION_2:
                    Intent sysIntent = new Intent();
                    sysIntent.setClassName(PackageUtil.VIVO_PHONE_MANAGER, VIVO_WHITE_LIST_ACTIVITY);
                    mContext.startActivity(sysIntent);
                    Intent guideIntent = new Intent(mContext, OuterPermissionActivity.class);
                    guideIntent.putExtra(OuterPermissionActivity.VIEWSTUB_ID, R.layout.general_permission_guide_mask);
                    guideIntent.putExtra(OuterPermissionActivity.GUIDEPIC_ID, R.drawable.permission_trust_vivo_2_0);
                    guideIntent.putExtra(OuterPermissionActivity.GUIDE_HINTONE, mContext.getString(R.string.miui_permission_guide_template_one, mContext.getString(R.string.app_name)));
                    guideIntent.putExtra(OuterPermissionActivity.GUIDE_HINTTWO, mContext.getString(R.string.permission_trust_vivo_2_0));
                    mContext.startActivity(guideIntent);
                    break;

                case I_VERSION_3:
                    Intent s2 = new Intent();
                    s2.setClassName(PackageUtil.VIVO_PHONE_MANAGER, VIVO_WHITE_LIST_ACTIVITY);
                    mContext.startActivity(s2);
                    final Intent g2 = new Intent(mContext, OuterPermissionActivityForVivoi3.class);
                    g2.putExtra(OuterPermissionActivity.VIEWSTUB_ID, R.layout.general_permission_guide_mask);
                    g2.putExtra(OuterPermissionActivity.GUIDEPIC_ID, R.drawable.permission_trust_vivo_3_0);
                    g2.putExtra(OuterPermissionActivity.GUIDE_HINTONE, mContext.getString(R.string.miui_permission_guide_template_one, mContext.getString(R.string.app_name)));
                    g2.putExtra(OuterPermissionActivity.GUIDE_HINTTWO, mContext.getString(R.string.permission_trust_vivo_3_0));
                    ModelManager.getInst().getHandler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            mContext.startActivity(g2);
                        }
                    }, 300);
                    break;
                case I_VERSION_3_2:
                    Intent s3 = new Intent();
                    s3.setClassName(PackageUtil.VIVO_PHONE_MANAGER, VIVO_WHITE_LIST_ACTIVITY);
                    mContext.startActivity(s3);
                    final Intent g3 = new Intent(mContext, OuterPermissionActivityForVivoi3_2_7.class);
                    g3.putExtra(OuterPermissionActivity.VIEWSTUB_ID, R.layout.general_permission_guide_mask);
                    g3.putExtra(OuterPermissionActivity.GUIDEPIC_ID, R.drawable.permission_trust_vivo_3_0);
                    g3.putExtra(OuterPermissionActivity.GUIDE_HINTONE, mContext.getString(R.string.miui_permission_guide_template_one, mContext.getString(R.string.app_name)));
                    g3.putExtra(OuterPermissionActivity.GUIDE_HINTTWO, mContext.getString(R.string.permission_trust_vivo_3_0));
                    ModelManager.getInst().getHandler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            mContext.startActivity(g3);
                        }
                    }, 300);
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setToastPermission() {
        actionToastPermission();
    }
    @Override
    protected void actionToastPermission() {
        super.actionToastPermission();
        try {
            switch (mIVersion) {
                case I_VERSION_3:
                case I_VERSION_3_2:
                    Intent i1 = new Intent();
                    i1.setClassName(PackageUtil.VIVO_PHONE_MANAGER, VIVO_MAIN_ACTIVITY);
                    mContext.startActivity(i1);
                    final Intent guideIntent = new Intent(mContext, OuterTwoStepPermissionActivityForVIVOi3.class);
                    guideIntent.putExtra(OuterTwoStepPermissionActivity.GUIDE_TEXT_ROW_ONE, mContext.getString(R.string.permission_vivo_2_0_autoboot_step1_text));
                    guideIntent.putExtra(OuterTwoStepPermissionActivity.GUIDE_TEXT_ROW_TWO, mContext.getString(R.string.permission_vivo_3_0_toast_step2_text));
                    guideIntent.putExtra(OuterTwoStepPermissionActivity.GUIDE_TEXT_ROW_THREE, mContext.getString(R.string.meizu_security_v3_background_permission_step3_text));
                    guideIntent.putExtra(OuterTwoStepPermissionActivity.GUIDE_IMG_ROW_ONE, R.drawable.permission_autoboot_vivo_3_0_step_1);
                    guideIntent.putExtra(OuterTwoStepPermissionActivity.GUIDE_IMG_ROW_TWO, R.drawable.permission_toast_vivo_3_0_step_2);
                    guideIntent.putExtra(OuterTwoStepPermissionActivity.GUIDE_IMG_ROW_THREE, R.drawable.permission_autoboot_vivo_3_0_step_3);
                    guideIntent.putExtra(OuterTwoStepPermissionActivity.GUIDE_NEED_GESTURE_ONE, true);
                    guideIntent.putExtra(OuterTwoStepPermissionActivity.GUIDE_NEED_GESTURE_THREE, true);
                    guideIntent.putExtra(OuterTwoStepPermissionActivity.GUIDE_NEED_GESTURE_MARGIN_LEFT_ONE, R.dimen.permission_autoboot_vivo_2_0_gesture_margin_left_step1);
                    guideIntent.putExtra(OuterTwoStepPermissionActivity.GUIDE_NEED_GESTURE_MARGIN_LEFT_THREE, R.dimen.permission_oppo_trust_gesture_maring_left_step3);
                    ModelManager.getInst().getHandler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            mContext.startActivity(guideIntent);
                        }
                    }, 300);
                    break;
                case I_VERSION_4:
                    Intent sysIntent2 = new Intent();
                    sysIntent2.setClassName(VIVO_SECURE_MANAGER_PACKAGE_IV4, VIVO_SECURE_MANAGER_ACTIVITY_IV4);
                    mContext.startActivity(sysIntent2);
                    final Intent guideIntent2 = new Intent(mContext, OuterPermissionActivity.class);
                    guideIntent2.putExtra(OuterPermissionActivity.VIEWSTUB_ID, R.layout.general_permission_guide_mask);
                    guideIntent2.putExtra(OuterPermissionActivity.GUIDEPIC_ID, R.drawable.permission_toast_vivo_4_0);
                    guideIntent2.putExtra(OuterPermissionActivity.GUIDE_HINTONE, mContext.getString(R.string.miui_permission_guide_template_one, mContext.getString(R.string.app_name)));
                    guideIntent2.putExtra(OuterPermissionActivity.GUIDE_HINTTWO, mContext.getString(R.string.permission_toast_vivo_4_0));
                    ModelManager.getInst().getHandler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            mContext.startActivity(guideIntent2);
                        }
                    }, 500);
                    break;
                case I_VERSION_4_1:

                    Intent sysIntent3 = new Intent();
                    sysIntent3.setClassName(VIVO_SECURE_MANAGER_PACKAGE_IV4, VIVO_SECURE_MANAGER_ACTIVITY_IV4_1);
                    sysIntent3.setAction("secure.intent.action.softPermissionDetail");
                    sysIntent3.putExtra("packagename", mContext.getPackageName());
                    mContext.startActivity(sysIntent3);

                    final Intent guideIntent3 = new Intent(mContext, OuterTwoStepPermissionActivity.class);
                    guideIntent3.putExtra(OuterTwoStepPermissionActivity.GUIDE_TEXT_ROW_ONE, mContext.getString(R.string.permission_toast_vivo_4_0));
                    guideIntent3.putExtra(OuterTwoStepPermissionActivity.GUIDE_IMG_ROW_ONE, R.drawable.permission_toast_vivo_4_0);
                    guideIntent3.putExtra(OuterTwoStepPermissionActivity.GUIDE_NEED_GESTURE_ONE, true);
                    guideIntent3.putExtra(OuterTwoStepPermissionActivity.GUIDE_NEED_GESTURE_MARGIN_LEFT_ONE, R.dimen.permission_autoboot_vivo_4_0_gesture_margin_left_step1);
                    ModelManager.getInst().getHandler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            mContext.startActivity(guideIntent3);
                        }
                    }, 500);
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void actionWhiteListPermisssion() {
        super.actionWhiteListPermisssion();
        mContext.startActivity(new Intent(mContext, VivoPermissionGeneralGuide.class));
    }

    @Override
    public ArrayList<String> getPermissionList(int type) {
        ArrayList<String> ret = new ArrayList<String>();
        if (type == IPermissionGuideStrategy.TUTORIAL_TYPE) {
            ret.add(GuideConst.AUTOBOOT_PERMISSION);
            if(mIVersion != I_VERSION.I_VERSION_4 && mIVersion != I_VERSION.I_VERSION_4_1) {
                ret.add(GuideConst.VIVO_BACKGROUND_PROTECT_PERMISSION);
            }
            if(mIVersion == I_VERSION.I_VERSION_3_2 || mIVersion == I_VERSION.I_VERSION_4 || mIVersion == I_VERSION.I_VERSION_4_1){
                ret.add(GuideConst.VIVO_BACKGROUND_USE_CPU_PERMISSION);
            }
            if (mIVersion == I_VERSION.I_VERSION_4_1) {
                ret.add(GuideConst.OPEN_NOTIFICATION);
            }
            if (mIVersion == I_VERSION.I_VERSION_3 || mIVersion == I_VERSION.I_VERSION_3_2|| mIVersion == I_VERSION.I_VERSION_4 || mIVersion == I_VERSION.I_VERSION_4_1) {
                ret.add(GuideConst.TOAST_PERMISSION);
            }

        } else if (type == IPermissionGuideStrategy.INAPP_TYPE) {
            ret.add(GuideConst.AUTOBOOT_PERMISSION);
            if(mIVersion != I_VERSION.I_VERSION_4 && mIVersion != I_VERSION.I_VERSION_4_1) {
                ret.add(GuideConst.WHITE_LIST);
            }
        } else if (type == IPermissionGuideStrategy.START_UP_TYPE){
            if(mIVersion != I_VERSION.I_VERSION_4 && mIVersion != I_VERSION.I_VERSION_4_1) {
                ret.add(GuideConst.TRUST_APPLICATION_PERMISSION);
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
        return ModelManager.getContext().getString(R.string.permission_guide_title, "VIVO");
    }

    @Override
    protected String getPermissionTitle(String permission, int type) {
        String os = "VIVO";
        if (type == IPermissionGuideStrategy.START_UP_TYPE) {
            return ModelManager.getContext().getString(R.string.permission_guide_title, os);
        } else if (type == IPermissionGuideStrategy.INAPP_TYPE) {
            return ModelManager.getContext().getString(R.string.important_permission_guide_title, os);
        } else if (type == IPermissionGuideStrategy.TUTORIAL_TYPE) {
            return ModelManager.getContext().getString(R.string.permission_title_tutorial);
        } else if (GuideConst.AUTOBOOT_PERMISSION.equals(permission)) {
            return ModelManager.getContext().getString(R.string.autoboot_permission_title);
        } else if (GuideConst.WHITE_LIST.equals(permission)) {
            return ModelManager.getContext().getString(R.string.white_list_permission_title);
        } else if (GuideConst.TOAST_PERMISSION.equals(permission)) {
            return ModelManager.getContext().getString(R.string.permission_title_toast, os);
        } else if (GuideConst.TRUST_APPLICATION_PERMISSION.equals(permission)) {
            return ModelManager.getContext().getString(R.string.permission_title_call);
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
                    new int[]{R.string.permission_trust_step_1_vivo, R.string.permission_trust_step_2_vivo, R.string.permission_trust_step_3_vivo},
                    mVersion == VERSION_1
                            ? new int[][]{new int[]{R.drawable.vivo_app_permission_v1, R.drawable.vivo_trust_permission_v1_1},
                                          new int[]{R.drawable.vivo_trust_permission_v1_2, R.drawable.vivo_trust_permission_v1_3},
                                          new int[]{R.drawable.vivo_trust_permission_v1_4}}
                            : new int[][]{new int[]{R.drawable.vivo_app_permission, R.drawable.vivo_trust_permission_1},
                                          new int[]{R.drawable.vivo_trust_permission_2, R.drawable.vivo_trust_permission_3},
                                          new int[]{R.drawable.vivo_trust_permission_4}}
            );
        } else if (GuideConst.AUTOBOOT_PERMISSION.equals(permission)) {
            stepItem = new PermissionGuideStepItem(
                    type == IPermissionGuideStrategy.TUTORIAL_TYPE ? R.string.permission_others_title_tutorial : -1,
                    new int[]{R.string.permission_autoboot_step_1_vivo, R.string.permission_autoboot_step_2_vivo},
                    mVersion == VERSION_1
                            ? new int[][]{new int[]{R.drawable.vivo_app_permission_v1, R.drawable.vivo_autoboot_permission_v1_1}, new int[]{R.drawable.vivo_autoboot_permission_v1_2}}
                            : new int[][]{new int[]{R.drawable.vivo_app_permission, R.drawable.vivo_autoboot_permission_1}, new int[]{R.drawable.vivo_autoboot_permission_2}}
            );
        } else if (GuideConst.WHITE_LIST.equals(permission)) {
            stepItem = new PermissionGuideStepItem(
                    -1,
                    new int[]{R.string.permission_white_list_step_1_vivo,
                            mVersion == VERSION_1 ? R.string.permission_white_list_step_2_vivo_v1 : R.string.permission_white_list_step_2_vivo},
                    mVersion == VERSION_1
                            ? new int[][]{new int[]{R.drawable.vivo_white_list_permission_v1_1, R.drawable.vivo_white_list_permission_v1_2},
                                          new int[]{R.drawable.vivo_white_list_permission_v1_3}}
                            : new int[][]{new int[]{R.drawable.vivo_white_list_permission_1, R.drawable.vivo_white_list_permission_2},
                                          new int[]{R.drawable.vivo_white_list_permission_3, R.drawable.vivo_white_list_permission_4}}
            );
        } else if (GuideConst.TOAST_PERMISSION.equals(permission)) {
            stepItem = new PermissionGuideStepItem(
                    type == IPermissionGuideStrategy.TUTORIAL_TYPE
                            ? R.string.permission_toast_title_tutorial
                            : R.string.permission_title_toast_single,
                    new int[]{R.string.permission_toast_step_1_vivo, R.string.permission_toast_step_2_vivo},
                    new int[][]{new int[]{R.drawable.vivo_app_permission, R.drawable.vivo_toast_permission_1}, new int[]{R.drawable.vivo_toast_permission_2}}
            );
        }
        return stepItem;
    }

    public static int getVivoManagerVersion(){
        int ret = -1;
        String versionName = PackageUtil.getVersionName(PackageUtil.VIVO_PHONE_MANAGER);
        String[] versionTmp = versionName.split("\\.");
        int mainVersion = Integer.valueOf(versionTmp[0]);
        Log.i("ycsss", "version: " + versionName + "##" + mainVersion);

        if (mainVersion == 1) {
            ret = VERSION_1;
        } else if (mainVersion < 3) {
            ret = VERSION_2_X;
        } else {
            ret = VERSION_3;
        }

        return ret;
    }

    public static I_VERSION getIVersion() {
        String versionName = PackageUtil.getVersionName(PackageUtil.VIVO_PHONE_MANAGER);
        if (versionName.indexOf("2014") > 0) {
            return I_VERSION.I_VERSION_X_1; //4.2.2-eng.compiler.20140625.105741
        }

        String[] versionTmp = versionName.split("\\.");
        int v = Integer.valueOf(versionTmp[0]);
        Log.i("ycsss", "version: " + versionName + "##" + v);
        int v2 = 0,v3 = 0;//对小的版本号进行判断
        if(versionTmp.length >= 3){
            try {
                v2 = Integer.valueOf(versionTmp[1]);
                v3 = Integer.valueOf(versionTmp[2]);
            }catch (Exception  e){
                e.printStackTrace();
            }
        }
        if (v == 1) {
            return I_VERSION.I_VERSION_1;
        } else if (v == 2) {
            return I_VERSION.I_VERSION_2;
        } else if(v == 3){
            if (v2 >= 2){//对3.2.7以上的版本进行单独判断
                return I_VERSION.I_VERSION_3_2;
            }
            return I_VERSION.I_VERSION_3;
        } else {
            if (v2 == 1) {
                return I_VERSION.I_VERSION_4_1;
            } else {
                return I_VERSION.I_VERSION_4;
            }
        }
    }

    @Override
    protected boolean supportGuide() {
        return mIVersion != I_VERSION.I_VERSION_X_1;
    }
}
