package android.by.com.permission.permission;

import android.accessibilityservice.AccessibilityService;
import android.accessibilityservice.AccessibilityServiceInfo;
import android.by.com.permission.R;
import android.by.com.permission.constant.GuideConst;
import android.by.com.permission.model.ModelManager;
import android.by.com.permission.util.PrefUtil;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.util.Log;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static android.accessibilityservice.AccessibilityService.GLOBAL_ACTION_BACK;

/**
 * Created by frankyang on 12/3/15.
 */
public class HuaweiPermissionGuideStrategy extends IPermissionGuideStrategy {

    private int mVersion;
    private boolean mAutoGuide;
    private boolean isGoback;
    private boolean flagClickTag;
    private boolean isFirstArriveInSmanagerAtV6;
    private boolean isFirstArriveInSmanagerAtV5;
    private final static String TAG = "HuaweiPermission";
    private HashMap<String, Object> accessisbilityMap = new HashMap<String, Object>();

    private static final String HUAWEI_SYSTEMMANAGER = "com.huawei.systemmanager";
    private static final String HUAWEI_PACKAGEINSTALLER = "com.android.packageinstaller";

    public HuaweiPermissionGuideStrategy(Context mContext,boolean auto) {
        super(mContext);
        mVersion = getHuaweiSystemManagerVersion(mContext);
        mAutoGuide = auto;
        isFirstArriveInSmanagerAtV6 = false;
        isFirstArriveInSmanagerAtV5 = false;
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

    @Override
    protected void actionTrustApplicationPermission(boolean showGuide) {
        super.actionTrustApplicationPermission(showGuide);
        try{
            Intent sysIntent = new Intent();
            Intent guideIntent = null;
            if (mVersion == 2 || mVersion == 3 || mVersion == 4 ) {
                sysIntent.setClassName(GuideConst.HUAWEI_PERMISSION_PACKAGE_NAME,
                        GuideConst.HUAWEI_APP_PERMISSION_ACTIVITY_NAME);
                flagClickTag = false;
                mContext.startActivity(sysIntent);
                if(!mAutoGuide) {
                    final Intent guideIntent1 = new Intent(mContext, OuterPermissionActivity.class);
                    guideIntent1.putExtra(OuterPermissionActivity.VIEWSTUB_ID, R.layout.huawei_app_permission_guide_v2);
                    ModelManager.getInst().getHandler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            mContext.startActivity(guideIntent1);
                        }
                    }, 100);
                }

            } else if (mVersion == 1) {
                sysIntent.setClassName(GuideConst.HUAWEI_PERMISSION_PACKAGE_NAME,
                        GuideConst.HUAWEI_APP_PERMISSION_ACTIVITY_NAME);
                mContext.startActivity(sysIntent);
                if(!mAutoGuide) {
                    guideIntent = new Intent(mContext, OuterPermissionActivity.class);
                    guideIntent.putExtra(OuterPermissionActivity.VIEWSTUB_ID, R.layout.huawei_app_permission_guide_v1);
                    mContext.startActivity(guideIntent);
                }
            } else if(mVersion == 5) {
                sysIntent.setClassName(GuideConst.HUAWEI_PERMISSION_PACKAGE_NAME,
                        GuideConst.HUAWEI_APP_PERMISSION_ACTIVITY_NAME);
                mContext.startActivity(sysIntent);
                if(!mAutoGuide) {
                    final Intent guideIntent2 = new Intent(mContext, OuterTwoStepPermissionActivity.class);
                    guideIntent2.putExtra(OuterTwoStepPermissionActivity.GUIDE_TEXT_ROW_ONE, mContext.getString(R.string.huawei_permission_5_trust_step_1_text));
                    guideIntent2.putExtra(OuterTwoStepPermissionActivity.GUIDE_TEXT_ROW_TWO, mContext.getString(R.string.huawei_permission_5_trust_step_2_text));
                    guideIntent2.putExtra(OuterTwoStepPermissionActivity.GUIDE_TEXT_ROW_THREE, mContext.getString(R.string.huawei_permission_5_trust_step_3_text));
                    guideIntent2.putExtra(OuterTwoStepPermissionActivity.GUIDE_IMG_ROW_ONE, R.drawable.huawei_permission_5_step_1);
                    guideIntent2.putExtra(OuterTwoStepPermissionActivity.GUIDE_IMG_ROW_TWO, R.drawable.huawei_permission_5_step_2);
                    guideIntent2.putExtra(OuterTwoStepPermissionActivity.GUIDE_IMG_ROW_THREE, R.drawable.huawei_permission_5_step_3);
                    guideIntent2.putExtra(OuterTwoStepPermissionActivity.GUIDE_NEED_GESTURE_ONE, true);
                    guideIntent2.putExtra(OuterTwoStepPermissionActivity.GUIDE_NEED_GESTURE_MARGIN_LEFT_ONE, R.dimen.permission_huawei_trust_gesture_maring_left_step1);
                    ModelManager.getInst().getHandler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            mContext.startActivity(guideIntent2);
                        }
                    }, 100);

                }
            } else if (mVersion == 6) {
                sysIntent.setClassName(GuideConst.HUAWEI_PERMISSION_PACKAGE_NAME, GuideConst.HUAWEI_APP_PERMISSION_ACTIVITY_NAME);
                mContext.startActivity(sysIntent);
                if(!mAutoGuide){
                    final Intent guideIntent2 = new Intent(mContext, OuterTwoStepPermissionActivity.class);
                    guideIntent2.putExtra(OuterTwoStepPermissionActivity.GUIDE_TEXT_ROW_ONE, mContext.getString(R.string.huawei_permission_5_0_autoboot_step_1_text));
                    guideIntent2.putExtra(OuterTwoStepPermissionActivity.GUIDE_TEXT_ROW_TWO, mContext.getString(R.string.huawei_permission_5_0_autoboot_step_2_text));
                    guideIntent2.putExtra(OuterTwoStepPermissionActivity.GUIDE_TEXT_ROW_THREE, mContext.getString(R.string.huawei_permission_5_0_trust_application_step_3_text));
                    guideIntent2.putExtra(OuterTwoStepPermissionActivity.GUIDE_IMG_ROW_ONE, R.drawable.huawei_permission_5_0_step_1);
                    guideIntent2.putExtra(OuterTwoStepPermissionActivity.GUIDE_IMG_ROW_TWO, R.drawable.huawei_permission_5_0_step_2);
                    guideIntent2.putExtra(OuterTwoStepPermissionActivity.GUIDE_IMG_ROW_THREE, R.drawable.huawei_permission_5_0_trust_application_step_3);
                    guideIntent2.putExtra(OuterTwoStepPermissionActivity.GUIDE_NEED_GESTURE_THREE, true);
                    guideIntent2.putExtra(OuterTwoStepPermissionActivity.GUIDE_NEED_GESTURE_MARGIN_LEFT_THREE, R.dimen.permission_oppo_trust_gesture_maring_left_step3);
                    ModelManager.getInst().getHandler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            mContext.startActivity(guideIntent2);
                        }
                    }, 100);
                }
            }
        }catch (ActivityNotFoundException e ){
            Log.i(TAG,e.getMessage());
        }catch (SecurityException e) {
            Log.i(TAG,e.getMessage());
        }

    }

    @Override
    protected void actionAutoBootPermission() {
        super.actionAutoBootPermission();
        try {
            Intent sysIntent = new Intent();
            if (mVersion == 4 || mVersion == 5) {
                sysIntent.setClassName(GuideConst.HUAWEI_PERMISSION_PACKAGE_NAME,
                        GuideConst.HUAWEI_AUTO_START_PERMISSION_ACTIVITY_NAME_V2);
                mContext.startActivity(sysIntent);
                sysIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                if(!mAutoGuide) {
                    Intent guideIntent = new Intent(mContext, OuterPermissionActivity.class);
                    guideIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    guideIntent.putExtra(OuterPermissionActivity.VIEWSTUB_ID, R.layout.huawei_permission_general_guide);
                    guideIntent.putExtra(OuterPermissionActivity.HUAWEI_GENERAL_GUIDE_HINTONE, mContext.getString(R.string.miui_permission_guide_template_one, mContext.getString(R.string.app_name)));
                    guideIntent.putExtra(OuterPermissionActivity.HUAWEI_GENERAL_GUIDE_HINTTWO, mContext.getString(R.string.huawei_autoboot_protected_hinttwo_v2));
                    guideIntent.putExtra(OuterPermissionActivity.HUAWEI_GENERAL_GUIDE_ALT, mContext.getString(R.string.huawei_autoboot_permission_alt_v2));
                    guideIntent.putExtra(OuterPermissionActivity.HUAWEI_GENERAL_GUIDE_VERSION, mVersion);
                    mContext.startActivity(guideIntent);
                }
            } else if (mVersion == 6) {
                sysIntent.setClassName(GuideConst.HUAWEI_PERMISSION_PACKAGE_NAME, GuideConst.HUAWEI_APP_PERMISSION_ACTIVITY_NAME);
                mContext.startActivity(sysIntent);

                if (!mAutoGuide) {
                    final Intent guideIntent = new Intent(mContext, OuterTwoStepPermissionActivity.class);
                    guideIntent.putExtra(OuterTwoStepPermissionActivity.GUIDE_TEXT_ROW_ONE, mContext.getString(R.string.huawei_permission_5_0_autoboot_step_1_text));
                    guideIntent.putExtra(OuterTwoStepPermissionActivity.GUIDE_TEXT_ROW_TWO, mContext.getString(R.string.huawei_permission_5_0_autoboot_step_2_text));
                    guideIntent.putExtra(OuterTwoStepPermissionActivity.GUIDE_TEXT_ROW_THREE, mContext.getString(R.string.huawei_permission_5_0_autoboot_step_3_text));
                    guideIntent.putExtra(OuterTwoStepPermissionActivity.GUIDE_IMG_ROW_ONE, R.drawable.huawei_permission_5_0_step_1);
                    guideIntent.putExtra(OuterTwoStepPermissionActivity.GUIDE_IMG_ROW_TWO, R.drawable.huawei_permission_5_0_step_2);
                    guideIntent.putExtra(OuterTwoStepPermissionActivity.GUIDE_IMG_ROW_THREE, R.drawable.huawei_permission_5_0_autoboot_step_3);
                    guideIntent.putExtra(OuterTwoStepPermissionActivity.GUIDE_NEED_GESTURE_THREE, true);
                    guideIntent.putExtra(OuterTwoStepPermissionActivity.GUIDE_NEED_GESTURE_MARGIN_LEFT_THREE, R.dimen.permission_oppo_trust_gesture_maring_left_step3);
                    ModelManager.getInst().getHandler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            mContext.startActivity(guideIntent);
                        }
                    }, 100);
                }

            } else {
                sysIntent.setClassName(GuideConst.HUAWEI_PERMISSION_PACKAGE_NAME,
                        GuideConst.HUAWEI_AUTO_START_PERMISSION_ACTIVITY_NAME);
                mContext.startActivity(sysIntent);

                if(!mAutoGuide) {
                    Intent guideIntent = new Intent(mContext, OuterPermissionActivity.class);
                    guideIntent.putExtra(OuterPermissionActivity.VIEWSTUB_ID, R.layout.huawei_permission_general_guide);
                    guideIntent.putExtra(OuterPermissionActivity.HUAWEI_GENERAL_GUIDE_HINTONE, mContext.getString(R.string.miui_permission_guide_template_one, mContext.getString(R.string.app_name)));
                    if (mVersion == 3 || mVersion == 2) {
                        guideIntent.putExtra(OuterPermissionActivity.HUAWEI_GENERAL_GUIDE_HINTTWO, mContext.getString(R.string.huawei_autoboot_protected_hinttwo_v2));
                    } else if (mVersion == 1) {
                        guideIntent.putExtra(OuterPermissionActivity.HUAWEI_GENERAL_GUIDE_HINTTWO, mContext.getString(R.string.huawei_autoboot_protected_hinttwo_v1));
                    }
                    guideIntent.putExtra(OuterPermissionActivity.HUAWEI_GENERAL_GUIDE_HINTTWO, mContext.getString(R.string.huawei_autoboot_protected_hinttwo_v1));
                    guideIntent.putExtra(OuterPermissionActivity.HUAWEI_GENERAL_GUIDE_ALT, mContext.getString(R.string.huawei_permission_allowed));
                    guideIntent.putExtra(OuterPermissionActivity.HUAWEI_GENERAL_GUIDE_VERSION, mVersion);
                    mContext.startActivity(guideIntent);
                }
            }

        } catch (ActivityNotFoundException|SecurityException e) {
            Log.i(TAG,e.getMessage());
        } catch (Exception e) {
            Log.i(TAG,e.getMessage());
        }

    }

    @Override
    protected void actionToastPermission() {
        super.actionToastPermission();
        try {
            int version = getHuaweiSystemManagerVersion(mContext);
            if (version == 2 || version == 4 || version == 5) {
                Intent sysIntent = new Intent();
                sysIntent.setClassName(GuideConst.HUAWEI_PERMISSION_PACKAGE_NAME,
                        GuideConst.HUAWEI_TOAST_PERMISSION_ACTIVITY_NAME_V2);
                mContext.startActivity(sysIntent);
                if(!mAutoGuide) {
                    Intent guideIntent = new Intent(mContext, OuterPermissionActivity.class);
                    guideIntent.putExtra(OuterPermissionActivity.VIEWSTUB_ID, R.layout.huawei_permission_general_guide);
                    guideIntent.putExtra(OuterPermissionActivity.HUAWEI_GENERAL_GUIDE_HINTONE, mContext.getString(R.string.miui_permission_guide_template_one, mContext.getString(R.string.app_name)));
                    guideIntent.putExtra(OuterPermissionActivity.HUAWEI_GENERAL_GUIDE_HINTTWO, mContext.getString(R.string.huawei_permission_action_switch_on_v2));
                    guideIntent.putExtra(OuterPermissionActivity.HUAWEI_GENERAL_GUIDE_ALT, mContext.getString(R.string.huawei_permission_allowed));
                    guideIntent.putExtra(OuterPermissionActivity.HUAWEI_GENERAL_GUIDE_VERSION, version);
                    mContext.startActivity(guideIntent);
                }
            } else if (version == 1) {
                Intent sysIntent = new Intent();
                sysIntent.setClassName(GuideConst.HUAWEI_PERMISSION_PACKAGE_NAME,
                        GuideConst.HUAWEI_TOAST_PERMISSION_ACTIVITY_NAME_V1);
                mContext.startActivity(sysIntent);
                if(!mAutoGuide){
                    Intent guideIntent = new Intent(mContext, OuterPermissionActivity.class);
                    guideIntent.putExtra(OuterPermissionActivity.VIEWSTUB_ID, R.layout.huawei_toast_permission_guide_v1);
                    mContext.startActivity(guideIntent);
                }
            } else if (version == 3) {
                Intent sysIntent = new Intent();
                sysIntent.setClassName(GuideConst.HUAWEI_PERMISSION_PACKAGE_NAME,
                        GuideConst.HUAWEI_TOAST_PERMISSION_ACTIVITY_NAME_V3);
                mContext.startActivity(sysIntent);
                if(!mAutoGuide) {
                    final Intent guideIntent = new Intent(mContext, OuterPermissionActivity.class);
                    guideIntent.putExtra(OuterPermissionActivity.VIEWSTUB_ID, R.layout.huawei_toast_permission_guide_v3);
                    ModelManager.getInst().getHandler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            mContext.startActivity(guideIntent);
                        }
                    }, 100);
                }
            }
        } catch (ActivityNotFoundException e) {
            Log.i(TAG,e.getMessage());
            try {
                Intent it = new Intent();
                it.setClassName(GuideConst.HUAWEI_PERMISSION_PACKAGE_NAME,
                        GuideConst.HUAWEI_TOAST_PERMISSION_ACTIVITY_NAME_TWO);
                mContext.startActivity(it);
            } catch (ActivityNotFoundException ae) {
                Log.i(TAG,ae.getMessage());
            } catch (SecurityException se) {
                Log.i(TAG,se.getMessage());
            }
        } catch (SecurityException e) {
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
    protected void actionBackgroundPermisssion() {
        super.actionBackgroundPermisssion();
//        if (mVersion == 5) {
//            Intent intent = new Intent(Intent.ACTION_MAIN);
//            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//            intent.addCategory(Intent.CATEGORY_HOME);
//            mContext.startActivity(intent);
//        } else {
            try {
                Intent sysIntent = new Intent();
                sysIntent.setClassName(GuideConst.HUAWEI_PERMISSION_PACKAGE_NAME,
                        GuideConst.HUAWEI_BACKGROUND_PROTECT_PERMISSION_ACTIVITY_NAME);
                mContext.startActivity(sysIntent);
                if(!mAutoGuide) {
                    final Intent guideIntent = new Intent(mContext, OuterPermissionActivity.class);
                    guideIntent.putExtra(OuterPermissionActivity.VIEWSTUB_ID, R.layout.general_permission_guide_mask);
                    guideIntent.putExtra(OuterPermissionActivity.GUIDEPIC_ID, R.drawable.huawei_background_permission_item);
                    guideIntent.putExtra(OuterPermissionActivity.GUIDE_HINTONE, mContext.getString(R.string.miui_permission_guide_template_one, mContext.getString(R.string.app_name)));
                    guideIntent.putExtra(OuterPermissionActivity.GUIDE_HINTTWO, mContext.getString(R.string.huawei_background_protected_hinttwo_v2));
                    mContext.startActivity(guideIntent);
                }
            } catch (ActivityNotFoundException e) {
                Log.i(TAG,e.getMessage());
            } catch (SecurityException e) {
                Log.i(TAG,e.getMessage());
            }
//        }
    }

    @Override
    protected void actionWhiteListPermisssion() {
        super.actionWhiteListPermisssion();
        try {
            Intent sysIntent = new Intent();
            sysIntent.setClassName(GuideConst.HUAWEI_PERMISSION_PACKAGE_NAME,
                    GuideConst.HUAWEI_PERMISSION_MAIN_ACTIVITY_NAME);
            mContext.startActivity(sysIntent);
        } catch (ActivityNotFoundException e) {
            Log.i(TAG,e.getMessage());
        } catch (SecurityException e) {
            Log.i(TAG,e.getMessage());
        }
    }

    @Override
    protected void actionPermissionDeny(int type) {
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
        actionTrustApplicationPermission(true);
    }

    @Override
    public ArrayList<String> getPermissionList(int type) {
        ArrayList<String> ret = new ArrayList<String>();
        Log.e(TAG,"type="+type);
        if(type == ACCESSIBLITY_UPGRADE){
//            if(mVersion ==5) {
                ret.add(GuideConst.TRUST_APPLICATION_PERMISSION);
//            }
            if (mVersion <5 && Build.VERSION.SDK_INT >= 19 && mVersion !=3) {
                ret.add(GuideConst.TOAST_PERMISSION);
            }
            if(mVersion != 5) {
                ret.add(GuideConst.AUTOBOOT_PERMISSION);
            }
            if (mVersion >= 1 ) {
                ret.add(GuideConst.BACKGROUND_PROTECT_PERMISSION);
            }
        }
        else if (type == IPermissionGuideStrategy.TUTORIAL_TYPE) {
            ret.add(GuideConst.TRUST_APPLICATION_PERMISSION);

            if (mVersion <5 && Build.VERSION.SDK_INT >= 19 && mVersion !=3) {
                ret.add(GuideConst.TOAST_PERMISSION);
            }
            if(mVersion != 5) {
                ret.add(GuideConst.AUTOBOOT_PERMISSION);
            }
            if (mVersion >= 1 ) {
                ret.add(GuideConst.BACKGROUND_PROTECT_PERMISSION);
            }
        } else if (type == IPermissionGuideStrategy.INAPP_TYPE) {
            ret.add(GuideConst.AUTOBOOT_PERMISSION);
            if (mVersion > 1 && mVersion < 5) {
                ret.add(GuideConst.BACKGROUND_PROTECT_PERMISSION);
            }
        } else if (type == IPermissionGuideStrategy.START_UP_TYPE){
            ret.add(GuideConst.TRUST_APPLICATION_PERMISSION);
        } else if (type == IPermissionGuideStrategy.TOAST_TYPE){
            ret.add(GuideConst.TOAST_PERMISSION);
        } else if (type == IPermissionGuideStrategy.INAPP_SECOND_GUIDE_TYPE) {
            return super.getSecondGuidePermissionList();
        }

        return ret;
    }

    @Override
    protected String getPermissionTitle() {
        return ModelManager.getContext().getString(R.string.permission_guide_title, mContext.getString(R.string.huawei_manufacturer));
    }

    @Override
    protected String getPermissionTitle(String permission, int type) {
        String os = mContext.getString(R.string.huawei_manufacturer);
        if (type == IPermissionGuideStrategy.START_UP_TYPE) {
            return ModelManager.getContext().getString(R.string.permission_guide_title, os);
        } else if (type == IPermissionGuideStrategy.INAPP_TYPE) {
            return ModelManager.getContext().getString(R.string.important_permission_guide_title, os);
        } else if (type == IPermissionGuideStrategy.TUTORIAL_TYPE) {
            return ModelManager.getContext().getString(R.string.permission_guide_title_new, os);
        } else if (GuideConst.AUTOBOOT_PERMISSION.equals(permission)) {
            return ModelManager.getContext().getString(R.string.autoboot_permission_title);
        } else if (GuideConst.WHITE_LIST.equals(permission)) {
            return ModelManager.getContext().getString(R.string.white_list_permission_title);
        } else if (GuideConst.TOAST_PERMISSION.equals(permission)) {
            return ModelManager.getContext().getString(R.string.permission_title_toast, os);
        } else if (GuideConst.TRUST_APPLICATION_PERMISSION.equals(permission)) {
            return ModelManager.getContext().getString(R.string.permission_title_call);
        } else if (GuideConst.HUAWEI_V4_BACKGROUND_PROTECT_PERMISSION.equals(permission)) {
            return ModelManager.getContext().getString(R.string.permission_background_protection_huawei_v4_title);
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
                    mVersion == 1 ? new int[]{R.string.permission_trust_step_1_huawei_v1, R.string.permission_trust_step_2_huawei_v1}
                                  : mVersion < 5 ? new int[]{R.string.permission_trust_step_1_huawei_v3, R.string.permission_trust_step_2_huawei_v3}
                                                 : new int[]{R.string.permission_trust_step_1_huawei_v4, R.string.permission_trust_step_2_huawei_v4},
                    mVersion == 1 ? new int[][]{new int[]{R.drawable.huawei_trust_permission_v1_02, R.drawable.huawei_trust_permission_v1_03},
                                                new int[]{R.drawable.huawei_trust_permission_v1_04}}
                                  : mVersion < 5
                                            ? new int[][]{new int[]{R.drawable.huawei_trust_permission_v3_01, R.drawable.huawei_trust_permission_v3_02},
                                                          new int[]{R.drawable.huawei_trust_permission_v3_03}}
                                            : new int[][]{new int[]{R.drawable.huawei_trust_permission_v4_1, R.drawable.huawei_trust_permission_v4_2, R.drawable.huawei_trust_permission_v3_02},
                                                          new int[]{R.drawable.huawei_trust_permission_v4_3}}
            );
        } else if (GuideConst.AUTOBOOT_PERMISSION.equals(permission)) {
            stepItem = new PermissionGuideStepItem(
                    type == IPermissionGuideStrategy.TUTORIAL_TYPE
                            ? R.string.permission_others_title_tutorial
                            : mVersion == 1 ? R.string.permission_autoboot_meizu4_title : -1,
                    mVersion == 1? new int[]{R.string.permission_autoboot_huawei_v1} : new int[]{R.string.autoboot_permission_hint},
                    mVersion == 1 ? new int[][]{new int[]{R.drawable.huawei_autoboot_permission_v1}}
                                  : mVersion == 5 ? new int[][]{new int[]{ R.drawable.huawei_autoboot_permission_v4, R.drawable.huawei_autoboot_permission_v3_2}}
                                                  : new int[][]{new int[]{R.drawable.huawei_autoboot_permission_v3_1, R.drawable.huawei_autoboot_permission_v3_2}}
            );
        } else if (GuideConst.WHITE_LIST.equals(permission)) {
            stepItem = new PermissionGuideStepItem(
                    -1,
                    new int[]{R.string.permission_white_list_huawei_v4},
                    new int[][]{new int[]{R.drawable.huawei_white_list_1, R.drawable.huawei_white_list_2, R.drawable.huawei_white_list_3, R.drawable.huawei_autoboot_permission_v3_2}}
            );
        } else if (GuideConst.TOAST_PERMISSION.equals(permission)) {
            stepItem = new PermissionGuideStepItem(
                    type == IPermissionGuideStrategy.TUTORIAL_TYPE
                            ? R.string.permission_toast_title_tutorial
                            : R.string.permission_title_toast_single,
                    mVersion == 1 ? new int[]{R.string.permission_toast_step_1_huawei_v1, R.string.permission_toast_step_2_huawei_v1}
                                  : mVersion == 3 ? new int[]{R.string.permission_toast_step_1_huawei_v30, R.string.permission_toast_step_2_huawei_v30}
                                                  : new int[]{R.string.permission_toast_huawei},
                    mVersion == 1 ? new int[][]{new int[]{R.drawable.huawei_toast_permission_v1_1}, new int[]{R.drawable.huawei_toast_permission_v1_2, R.drawable.huawei_toast_permission_v1_3}}
                                  : mVersion == 3 ? new int[][]{new int[]{R.drawable.huawei_toast_permission_v30_1}, new int[]{R.drawable.huawei_toast_permission_v30_2}}
                                            : mVersion == 5 ? new int[][]{new int[]{R.drawable.huawei_toast_permission_v4, R.drawable.huawei_autoboot_permission_v3_2}}
                                                            : new int[][]{new int[]{R.drawable.huawei_toast_permission_v31}}
            );
        } else if (GuideConst.BACKGROUND_PROTECT_PERMISSION.equals(permission)) {
            stepItem = new PermissionGuideStepItem(
                    -1,
                    new int[]{R.string.permission_background_huawei_v3},
                    new int[][]{new int[]{R.drawable.huawei_background_protect_v3}}
            );
        } else if (GuideConst.HUAWEI_V4_BACKGROUND_PROTECT_PERMISSION.equals(permission)) {
            stepItem = new PermissionGuideStepItem(
                    -1,
                    new int[]{R.string.permission_background_protection_huawei_v4},
                    new int[][]{new int[]{R.drawable.huawei_background_protection_v4}}
            );
        }
        return stepItem;
    }


    public static int getHuaweiSystemManagerVersion(Context context) {
        int version = 0;
        int versionNum = 0;
        int thirdPartFirtDigit = 0;
        try {
            PackageManager manager = context.getPackageManager();
            PackageInfo info = manager.getPackageInfo(GuideConst.HUAWEI_PERMISSION_PACKAGE_NAME, 0);
            Log.e(TAG,"manager info = "+info.toString());
            String versionStr = info.versionName;
            String versionTmp[] = versionStr.split("\\.");
            if (versionTmp.length >= 2) {
                if (Integer.parseInt(versionTmp[0]) == 5) {
                    versionNum = 500;
                } else if (Integer.parseInt(versionTmp[0]) == 4) {
                    versionNum = Integer.parseInt(versionTmp[0]+versionTmp[1]+versionTmp[2]);
                } else {
                    versionNum = Integer.parseInt(versionTmp[0] + versionTmp[1]);
                }

            }
            if (versionTmp.length >= 3) {
                thirdPartFirtDigit = Integer.valueOf(versionTmp[2].substring(0, 1));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (versionNum >= 330) {
            if (versionNum >= 500) {
                version = 6;
            } else if (versionNum >= 400) {
                version = 5;
            } else if (versionNum >= 331) {
                version = 4;
            } else {
                version = (thirdPartFirtDigit == 6 || thirdPartFirtDigit == 4 || thirdPartFirtDigit == 2 )? 3 : 2;
            }
        }  else if (versionNum != 0 ) {
            version = 1;
        }
        return version;
    }

    @Override
    public void handleAccessbilityEvent(AccessibilityEvent event, AccessibilityService service){
        int eventType = event.getEventType();
        Log.e(TAG,"EventType = " + eventType+"\nmVersion"+mVersion);
        if((eventType == AccessibilityEvent.TYPE_VIEW_SCROLLED
                || (eventType == AccessibilityEvent.TYPE_WINDOW_CONTENT_CHANGED && mVersion == 1)
                || eventType == AccessibilityEvent.TYPE_WINDOW_STATE_CHANGED)
                && event.getPackageName() != null && !event.getPackageName().equals("com.android.settings")) {

            AccessibilityNodeInfo nodeInfo = service.getRootInActiveWindow();

            if (event.getPackageName().equals(HUAWEI_SYSTEMMANAGER)) {
                if (nodeInfo == null) {
                    return;
                }
                List<AccessibilityNodeInfo> list = nodeInfo.findAccessibilityNodeInfosByText(
                        mContext.getString(R.string.accessiblity_permission_huawei_system_manager) );

                if (list != null && list.size() > 0 && mVersion == 1){

                    Log.i(TAG,"case 1");

                    if (accessisbilityMap.containsKey("mVersion_1_toast_step1")) {
                        Log.i(TAG,"case 1-1");
                        service.performGlobalAction(GLOBAL_ACTION_BACK);
                        return;
                    }
                    AccessibilityNodeInfo specialToolNode = findAccessibilityNodeinfoByContentDesc(nodeInfo);

                    if (specialToolNode != null) {
                        if(specialToolNode.performAction(AccessibilityNodeInfo.ACTION_CLICK)){
                            Log.i(TAG,"case 1-2");
                            accessisbilityMap.put("mVersion_1_toast_step1", 1);
                        }
                    }
                    return;
                }


                list = nodeInfo.findAccessibilityNodeInfosByText(
                        mContext.getString(R.string.accessiblity_permission_huawei_autoboot));
                if (list != null && list.size() > 0 &&
                        (nodeInfo.findAccessibilityNodeInfosByText(
                                mContext.getString(R.string.accessiblity_permission_huawei_monitor_app))).isEmpty()
                        && (nodeInfo.findAccessibilityNodeInfosByText(
                                mContext.getString(R.string.accessiblity_permission_huawei_trust_app))).isEmpty() ){

                    Log.i(TAG,"case 2");
                    performSwitch(service,nodeInfo,
                            mContext.getString(R.string.accessiblity_permission_touchpal),
                            "huawei_permission_autoboots");
                    PrefUtil.setKey(IPermissionGuideStrategy.PREF_KEY_DONE_PREFIX + GuideConst.AUTOBOOT_PERMISSION, true);
                    return;
                }

                list = nodeInfo.findAccessibilityNodeInfosByText(
                        mContext.getString(R.string.accessiblity_permission_huawei_notifications));
                if(list != null && list.size() > 0
                        && !(nodeInfo.findAccessibilityNodeInfosByText(
                                mContext.getString(
                                        R.string.accessiblity_permission_huawei_dropzones))).isEmpty() ){
                    Log.i(TAG,"case 3");

                    list = nodeInfo.findAccessibilityNodeInfosByText(
                            mContext.getString(R.string.accessiblity_permission_huawei_dropzones));
                    if(! list.get(0).isSelected()) {
                        Log.i(TAG,"case 3-1");
                        performTab(nodeInfo, mContext.getString(R.string.accessiblity_permission_huawei_dropzones), "huawei_permission_v3_toast_0");
                    } else {
                        Log.i(TAG,"case 3-2  v3 toast");
                        performSwitch(service, nodeInfo,
                                mContext.getString(R.string.accessiblity_permission_touchpal),
                                "huawei_toast_permission_v3");
                        PrefUtil.setKey(IPermissionGuideStrategy.PREF_KEY_DONE_PREFIX + GuideConst.TOAST_PERMISSION, true);
                        return;
                    }
                    return;
                }


                list = nodeInfo.findAccessibilityNodeInfosByText(
                        mContext.getString(R.string.accessiblity_permission_huawei_special_tools));
                if (list != null && list.size() > 0){
                    Log.i(TAG,"case 4");
                    performMore(service, nodeInfo,
                            mContext.getString(R.string.accessiblity_permission_huawei_toast_management),
                            "huawei_special_tools");
                    return;
                }

                list = nodeInfo.findAccessibilityNodeInfosByText(
                        mContext.getString(R.string.accessiblity_permission_huawei_toast_management));
                if (list != null && list.size() > 0) {

                    Log.i(TAG,"case 5");
                    performSwitch(service, nodeInfo,
                            mContext.getString(R.string.accessiblity_permission_touchpal),
                            "huawei_toast_permission");
                    PrefUtil.setKey(IPermissionGuideStrategy.PREF_KEY_DONE_PREFIX + GuideConst.TOAST_PERMISSION, true);
                    return;
                }


                list = nodeInfo.findAccessibilityNodeInfosByText(
                        mContext.getString(R.string.accessiblity_permission_huawei_trust_app));
                if (list != null && !list.isEmpty()) {

                    Log.i(TAG,"case 6");
                    performSwitch(service, nodeInfo,
                            mContext.getString(R.string.accessiblity_permission_huawei_trust_app)
                            , false, true, "huawei_permission_step_trust");
                        PrefUtil.setKey(IPermissionGuideStrategy.PREF_KEY_DONE_PREFIX + GuideConst.TRUST_APPLICATION_PERMISSION, true);

                    if (mVersion == 2 || mVersion == 3) {
                        list = nodeInfo.findAccessibilityNodeInfosByText(
                                mContext.getString(R.string.accessiblity_permission_huawei_autoboot));
                        if(list != null && !list.isEmpty()) {
                            Log.i(TAG,"case 6-1");
                            performSwitch(service, nodeInfo,
                                    mContext.getString(R.string.accessiblity_permission_huawei_autoboot), false, true, "huawei_permission_step_autoboot");
                            PrefUtil.setKey(IPermissionGuideStrategy.PREF_KEY_DONE_PREFIX + GuideConst.AUTOBOOT_PERMISSION, true);
                        }

                    }
                    if (mVersion > 3) {
                        Log.i(TAG,"case 6-2");
                        list = nodeInfo.findAccessibilityNodeInfosByText(
                                mContext.getString(R.string.accessiblity_permission_huawei_toast_window));
                        if(list!=null && !list.isEmpty()) {
                            Log.i(TAG,"case 6-2-1");
                            performSwitch(service, nodeInfo,
                                    mContext.getString(R.string.accessiblity_permission_huawei_toast_window),
                                    false, true, "huawei_permission_step_toast");
                            PrefUtil.setKey(IPermissionGuideStrategy.PREF_KEY_DONE_PREFIX + GuideConst.TOAST_PERMISSION, true);
                        }
                        performSwitch(service, nodeInfo,
                                mContext.getString(R.string.accessiblity_permission_huawei_secondary_launch),
                                false, true, "huawei_permission_step_secondary_launch");

                        list = nodeInfo.findAccessibilityNodeInfosByText(
                                mContext.getString(R.string.accessiblity_permission_huawei_app_autoboot));
                        if(list != null && !list.isEmpty()) {
                            Log.i(TAG,"case 6-2-2");
                            performSwitch(service, nodeInfo,
                                    mContext.getString(R.string.accessiblity_permission_huawei_app_autoboot),
                                    false, true, "huawei_permission_step_autoboot");
                            PrefUtil.setKey(IPermissionGuideStrategy.PREF_KEY_DONE_PREFIX + GuideConst.AUTOBOOT_PERMISSION, true);
                        }
                    }

                    service.performGlobalAction(GLOBAL_ACTION_BACK);
                    return;
                }


                list = nodeInfo.findAccessibilityNodeInfosByText(
                        mContext.getString(R.string.accessiblity_permission_huawei_background_protect));
                if (list != null && list.size() > 0) {
                    Log.i(TAG,"case 7");
                    performSwitch(service, nodeInfo,
                            mContext.getString(R.string.accessiblity_permission_touchpal),
                            "huawei_background_protect");
                    PrefUtil.setKey(IPermissionGuideStrategy.PREF_KEY_DONE_PREFIX + GuideConst.BACKGROUND_PROTECT_PERMISSION, true);

                    return;
                }

                list = nodeInfo.findAccessibilityNodeInfosByText(
                        mContext.getString(R.string.accessiblity_permission_huawei_lock_cleanup));
                if (list != null && list.size() > 0) {
                    Log.i(TAG,"case 8");
                    performSwitch(service, nodeInfo,
                            mContext.getString(R.string.accessiblity_permission_touchpal),
                            true, false, "huawei_lock_cleanup");
                    PrefUtil.setKey(IPermissionGuideStrategy.PREF_KEY_DONE_PREFIX + GuideConst.BACKGROUND_PROTECT_PERMISSION, true);
                        return;
                }

                list = nodeInfo.findAccessibilityNodeInfosByText(
                        mContext.getString(R.string.accessiblity_permission_huawei_permission));
                if (list != null && !list.isEmpty()
                        && !( (nodeInfo.findAccessibilityNodeInfosByText(
                                mContext.getString(R.string.accessiblity_permission_huawei_app) ) ).isEmpty()
                        || (nodeInfo.findAccessibilityNodeInfosByText(
                                mContext.getString(R.string.accessiblity_permission_huawei_rights) ) ).isEmpty()
                )) {
                    Log.i(TAG,"case 9 \n" + accessisbilityMap.containsKey("huawei_permission_step_1") +"  "+accessisbilityMap.containsKey("huawei_permission_step_2"));

                    List<AccessibilityNodeInfo> mList = nodeInfo.findAccessibilityNodeInfosByText(
                            mContext.getString(R.string.accessiblity_permission_huawei_app)
                    );
                    if(mList != null && ! mList.isEmpty()){
                        AccessibilityNodeInfo node = mList.get(0);
                        if(! node.isSelected() && !node.isChecked()){
                            Log.e(TAG,"case 9-1 ");
                            performTab(nodeInfo,
                                    mContext.getString(R.string.accessiblity_permission_huawei_app),
                                    "huawei_permission_step_1");
                        } else {
                            Log.e(TAG,"case 9-2 ");
                            performMore(service,nodeInfo,
                                    mContext.getString(R.string.accessiblity_permission_touchpal),
                                    "huawei_permission_step_2");
                        }
                    }
                }
            } else if (event.getPackageName().equals(HUAWEI_PACKAGEINSTALLER)) {
                if (nodeInfo == null) {
                    return;
                }
                List<AccessibilityNodeInfo> list = nodeInfo.findAccessibilityNodeInfosByText(
                        mContext.getString(R.string.accessiblity_permission_huawei_app_permission));
                if (list != null && list.size() > 0) {
                    Log.i(TAG,"case 10");
                    performMore(service, nodeInfo,
                            mContext.getString(R.string.accessiblity_permission_huawei_single_permission), "huawei_permission_auto_step_2");
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
                    mContext.getString(R.string.accessiblity_permission_huawei_service));
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
    private AccessibilityNodeInfo recycle(AccessibilityNodeInfo info){
        return recycle(info,"");
    }

    private AccessibilityNodeInfo recycle(AccessibilityNodeInfo info,String key) {
        Log.i(TAG,"SDK_INT = "+ Build.VERSION.SDK_INT);
        if (info!= null && info.getClassName() != null
                && (info.getClassName().equals("android.widget.ListView")
                    || info.getClassName().equals("android.widget.GridView")) ){
            if(key.equals("huawei_permission_step_2")) {
                if (info.findAccessibilityNodeInfosByText(
                        mContext.getString(R.string.accessiblity_permission_huawei_permission)
                ).isEmpty() && mVersion != 1)
                    return null;
                else if (mVersion == 1) {
                    if ((info.findAccessibilityNodeInfosByViewId("com.huawei.systemmanager:id/AllAppIcon")).isEmpty())
                        return null;
                }
                return info;
            } else
                return info;
        }
        if (info == null || info.getChildCount() == 0) {
            return null;
        }
        for (int i = 0; i < info.getChildCount(); i++) {
            AccessibilityNodeInfo item = recycle(info.getChild(i),key);
            if (item != null) {
                return item;
            }
        }
        return null;
    }
    private void performMore(AccessibilityService service,AccessibilityNodeInfo nodeInfo, String tagText,String key) {
        if (accessisbilityMap.containsKey(key)) {
            Log.e(TAG,"case A");
            service.performGlobalAction(GLOBAL_ACTION_BACK);
            return;
        }
        List<AccessibilityNodeInfo> list = nodeInfo.findAccessibilityNodeInfosByText(tagText);
        if (list == null || list.size() == 0) {
            AccessibilityNodeInfo info = recycle(nodeInfo,key);
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

    private boolean performSwitch(AccessibilityService service,AccessibilityNodeInfo nodeInfo,String tagText,String key) {
        return performSwitch(service,nodeInfo,tagText,true,true,key);
    }

    private AccessibilityNodeInfo getSwitch(AccessibilityNodeInfo node){

        AccessibilityNodeInfo item= null;

        while(node!=null){
//            TLog.e(TAG,"getSwitch = "+node);
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
//            TLog.e(TAG,"       getSwitchCycle = "+node.getChild(i));
            AccessibilityNodeInfo item = getSwitchCycle(node.getChild(i));
            if(item != null){
                return item;
            }
        }

        return null;
    }

    private boolean performSwitch(AccessibilityService service,AccessibilityNodeInfo nodeInfo,String tagText,boolean back,boolean target,String key) {
        List<AccessibilityNodeInfo> list = nodeInfo.findAccessibilityNodeInfosByText(tagText);
        if (list != null && list.size() > 0 ) {
            if (!accessisbilityMap.containsKey(key)) {
                Log.e(TAG,"perfoemSwitch list = \n"+list);
                for(int i=0; i<list.size(); i++) {
                    AccessibilityNodeInfo parent = list.get(i).getParent();
                    if(! parent.isVisibleToUser()){
                        break;
                    }
                    try {
                        AccessibilityNodeInfo nodeSwitch = getSwitch(list.get(i));
                        if (parent != null && target != nodeSwitch.isChecked()) {
                            if (parent.performAction(AccessibilityNodeInfo.ACTION_CLICK)) {
                                accessisbilityMap.put(key, 1);
                            }
                        }
                    } catch (Exception e) {
                        if (parent != null && isEndable(parent) != target) {
                            parent.performAction(AccessibilityNodeInfo.ACTION_CLICK);
                            accessisbilityMap.put(key, 1);
                        }
                    }
                    break;
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
            return true;

        } else {
            AccessibilityNodeInfo info = recycle(nodeInfo);
            if (info!= null) {
                info.performAction(AccessibilityNodeInfo.ACTION_SCROLL_FORWARD);
            }
        }
        return false;
    }

    private boolean performTab(AccessibilityNodeInfo nodeInfo, String tagText, String key) {
        if (accessisbilityMap.containsKey(key)){
            return true;
        }
        List<AccessibilityNodeInfo> list = nodeInfo.findAccessibilityNodeInfosByText(tagText);
        if (list == null || list.size() == 0) {
            return true;
        }
        if (!accessisbilityMap.containsKey(key)) {
            AccessibilityNodeInfo tagNode = list.get(0);
            while(!tagNode.isClickable()){
                tagNode = tagNode.getParent();
            }
            if(tagNode.performAction(AccessibilityNodeInfo.ACTION_CLICK)){
                accessisbilityMap.put(key,1);
                return true;
            }
        }
        return false;
    }

    private AccessibilityNodeInfo findAccessibilityNodeinfoByContentDesc(AccessibilityNodeInfo node){

        if(node != null && node.getClassName().equals("android.widget.TextView")
                && node.getContentDescription()!=null ) {
            if ( node.getContentDescription().toString().contains(
                    mContext.getString(R.string.accessiblity_permission_huawei_special_tools))) {
                return node;
            }
        }

        if(node == null || node.getChildCount() == 0){
            return null;
        }

        for(int i=0;i < node.getChildCount(); i++){
            AccessibilityNodeInfo item = findAccessibilityNodeinfoByContentDesc(node.getChild(i));
            if(item != null){
                return item;
            }
        }

        return null;
    }

    private boolean isEndable(AccessibilityNodeInfo parent) {
        if (parent != null ) {
            int count = parent.getChildCount();
            for (int j = 0; j < count; j++) {
                AccessibilityNodeInfo info = parent.getChild(j);
                Log.d(TAG,"onAccessibilityEvent isEndable= " + info.getText() + ",cls=" + info.getClassName() );
                if (info.isChecked()
                        || !(info.findAccessibilityNodeInfosByText(
                                mContext.getString(R.string.accessiblity_permission_huawei_switch_allowed)).isEmpty())) {
                    return true;
                }
            }
        }
        return false;
    }


    @Override
    public void configAccessbility(AccessibilityService sevice) {
        AccessibilityServiceInfo accessibilityServiceInfo = new AccessibilityServiceInfo();
        accessibilityServiceInfo.packageNames = new String[]{
                HUAWEI_SYSTEMMANAGER,
                "com.android.packageinstaller",
                "com.android.settings"
        };
        accessibilityServiceInfo.eventTypes = AccessibilityEvent.TYPES_ALL_MASK;
        accessibilityServiceInfo.feedbackType = AccessibilityServiceInfo.FEEDBACK_GENERIC;
        accessibilityServiceInfo.notificationTimeout = 1000;
        sevice.setServiceInfo(accessibilityServiceInfo);
        sevice.performGlobalAction(GLOBAL_ACTION_BACK);
    }
}
