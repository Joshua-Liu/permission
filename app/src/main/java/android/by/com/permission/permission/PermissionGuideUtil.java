package android.by.com.permission.permission;

import android.by.com.permission.BuildConfig;
import android.by.com.permission.constant.GuideConst;
import android.by.com.permission.pref.PrefKeys;
import android.by.com.permission.util.OSUtil;
import android.by.com.permission.util.PackageUtil;
import android.by.com.permission.util.PrefUtil;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.provider.CallLog;




import java.util.List;

/**
 * Created by herculewang on 16/4/21.
 */
public class PermissionGuideUtil {

    public static boolean shouldShowStartUpGuide() {
//        if (BuildConfig.EMBEDDED_IN_ROM) {
//            return false;
//        }
        return OSUtil.isMiuiV6() || OSUtil.isMiuiV7() || OSUtil.isMiuiV5() || OSUtil.isMiuiV8()
                || PackageUtil.isPackageInstalled(PackageUtil.HUAWEI_PHONE_MANAGER)
                || PackageUtil.isPackageInstalled(PackageUtil.OPPO_PERMISSION_SETTING_PACKAGE_NAMES)
                || PackageUtil.isPackageInstalled(PackageUtil.VIVO_PHONE_MANAGER)
                || (PackageUtil.isPackageInstalled(GuideConst.ZTE_PERMISSION_PACKAGE_NAME) && ZtePermissionGuideStrategy.getPermissionManagerVersion() >= ZtePermissionGuideStrategy.VERSION_6);
    }

    public static boolean shouldShowToastPermissionEntry() {
//        if (BuildConfig.EMBEDDED_IN_ROM) {
//            return false;
//        }
        return OSUtil.isMiuiV5()
                || PackageUtil.isPackageInstalled(PackageUtil.HUAWEI_PHONE_MANAGER)
                || PackageUtil.isPackageInstalled(PackageUtil.OPPO_COLOROS_PERMISSION_SETTING_PACKAGE_NAMES)
                || PackageUtil.isPackageInstalled(PackageUtil.OPPO_PERMISSION_SETTING_PACKAGE_NAMES)
                || (PackageUtil.isPackageInstalled(PackageUtil.VIVO_PHONE_MANAGER) && VivoPermissionGuideStrategy.getVivoManagerVersion() == VivoPermissionGuideStrategy.VERSION_3)
                || PackageUtil.isPackageInstalled(PackageUtil.MEIZU_PHONE_MANAGER);
    }

    public static void launchSpecificPermissionGuide(Context context, int permissionType) {
        if (permissionType == IPermissionGuideStrategy.TOAST_TYPE) {
            if (PackageUtil.isPackageInstalled(PackageUtil.OPPO_COLOROS_PERMISSION_SETTING_PACKAGE_NAMES)) {
                OppoColorOSPermissionGuideStrategy strategy = new OppoColorOSPermissionGuideStrategy(context,false);
                strategy.actionToastPermission();
                return;
            }
            if (PackageUtil.isPackageInstalled(PackageUtil.OPPO_PERMISSION_SETTING_PACKAGE_NAMES)) {
                OppoPermissionGuideStrategy strategy = new OppoPermissionGuideStrategy(context,false);
                strategy.actionToastPermission();
                return;
            }
        }

        Intent permissionIntent = new Intent(context, SpecificPermissionActivity.class);
        List<String> permissionList = PermissionGuideGenerator.generateGuideStratagy(context)
                .getPermissionList(permissionType);
        if (permissionList == null || permissionList.size() <= 0)
            return;

        String[] permissions = new String[permissionList.size()];
        for (int i = 0; i < permissionList.size(); i++) {
            permissions[i] = permissionList.get(i);
        }
        permissionIntent.putExtra(PermissionGuideActivity.PERMISSION_LIST, permissions);
        permissionIntent.putExtra(PermissionGuideActivity.PERMISSION_LIST_TYPE, IPermissionGuideStrategy.NONE_TYPE);
        context.startActivity(permissionIntent);
    }

    public static void requestCalllogPermissionOnce(final ContentResolver contentResolver) {
        if (PrefUtil.getKeyBoolean(PrefKeys.FIRST_REQUEST_CALLLOG_PERMISSION, true)) {
            Cursor c = null;
            try {
                c = contentResolver.query(CallLog.CONTENT_URI, null, null, null, "limit 1");
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (c != null) {
                    try {
                        c.close();
                    } catch (Exception e) {}
                }
            }

            PrefUtil.setKey(PrefKeys.FIRST_REQUEST_CALLLOG_PERMISSION, false);
        }
    }
}
