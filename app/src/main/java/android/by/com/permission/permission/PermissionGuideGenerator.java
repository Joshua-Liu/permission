package android.by.com.permission.permission;


import android.by.com.permission.util.OSUtil;
import android.content.Context;


/**
 * Created by frankyang on 12/2/15.
 */
public class PermissionGuideGenerator {
    public static IPermissionGuideStrategy generateGuideStratagy(Context context, boolean auto) {
        IPermissionGuideStrategy ret ;
        if (OSUtil.isMiuiV6() || OSUtil.isMiuiV7() || OSUtil.isMiuiV8()) {
            ret = new MiuiV6PermissionGuideStrategy(context, auto);
        }
//            StatRecorder.record(StatConst.PATH_PERMISSION_GUIDE, StatConst.PERMISSION_GUIDE_OS_NAME, "miui6/7");
//        } else if (OSUtil.isMiuiV5()) {
//            StatRecorder.record(StatConst.PATH_PERMISSION_GUIDE, StatConst.PERMISSION_GUIDE_OS_NAME, "miui5");
//            ret = new MiuiV5PermissionGuideStrategy(context);
//        } else if (PackageUtil.isPackageInstalled(PackageUtil.HUAWEI_PHONE_MANAGER)) {
//            StatRecorder.record(StatConst.PATH_PERMISSION_GUIDE, StatConst.PERMISSION_GUIDE_OS_NAME, "huawei");
//            ret = new HuaweiPermissionGuideStrategy(context, auto);
//        } else if (PackageUtil.isPackageInstalled(PackageUtil.OPPO_COLOROS_PERMISSION_SETTING_PACKAGE_NAMES)) {
//            StatRecorder.record(StatConst.PATH_PERMISSION_GUIDE, StatConst.PERMISSION_GUIDE_OS_NAME, "oppo_coloros");
//            ret = new OppoColorOSPermissionGuideStrategy(context, auto);
//        } else if (PackageUtil.isPackageInstalled(PackageUtil.OPPO_PERMISSION_SETTING_PACKAGE_NAMES)) {
//            StatRecorder.record(StatConst.PATH_PERMISSION_GUIDE, StatConst.PERMISSION_GUIDE_OS_NAME, "oppo");
//            ret = new OppoPermissionGuideStrategy(context, auto);
//        } else if (PackageUtil.isPackageInstalled(PackageUtil.OPPO_2_0_PERMISSION_SETTING_PACKAGE_NAMES)) {
//            StatRecorder.record(StatConst.PATH_PERMISSION_GUIDE, StatConst.PERMISSION_GUIDE_OS_NAME, "oppo2_0");
//            ret = new Oppo_2_0_PermissionGuideStrategy(context, auto);
//        } else if (PackageUtil.isPackageInstalled(PackageUtil.MEIZU_PHONE_MANAGER)) {
//            StatRecorder.record(StatConst.PATH_PERMISSION_GUIDE, StatConst.PERMISSION_GUIDE_OS_NAME, "meizu");
//            ret = new MeizuPermissionGuideStrategy(context);
//        } else if (PackageUtil.isPackageInstalled(PackageUtil.SAMSUNG_PERMISSION_SETTINGS_PACKAGE_NAMES)) {
//            StatRecorder.record(StatConst.PATH_PERMISSION_GUIDE, StatConst.PERMISSION_GUIDE_OS_NAME, "samsung");
//            ret = new SamsungPermissionGuideStrategy(context);
//        } else if (PackageUtil.isPackageInstalled(PackageUtil.VIVO_PHONE_MANAGER)) {
//            StatRecorder.record(StatConst.PATH_PERMISSION_GUIDE, StatConst.PERMISSION_GUIDE_OS_NAME, "vivo");
//            ret = new VivoPermissionGuideStrategy(context);
//        } else if (PackageUtil.isPackageInstalled(GuideConst.ZTE_PERMISSION_PACKAGE_NAME)
//                && ZtePermissionGuideStrategy.getPermissionManagerVersion() >= ZtePermissionGuideStrategy.VERSION_6) {
//            StatRecorder.record(StatConst.PATH_PERMISSION_GUIDE, StatConst.PERMISSION_GUIDE_OS_NAME, "zte");
//            ret = new ZtePermissionGuideStrategy(context);
//        }
        else {
//            StatRecorder.record(StatConst.PATH_PERMISSION_GUIDE, StatConst.PERMISSION_GUIDE_OS_NAME, "other");
            ret = new DefaultStrategy(context);
        }
        return ret;
    }

    public static IPermissionGuideStrategy generateGuideStratagy(Context context) {
        return generateGuideStratagy(context, false);
    }

}
