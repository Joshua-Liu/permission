package android.by.com.permission.permission;

import android.by.com.permission.constant.GuideConst;
import android.by.com.permission.model.ModelManager;
import android.by.com.permission.model.StartupVerifier;
import android.by.com.permission.pref.PrefKeys;
import android.by.com.permission.util.OSUtil;
import android.by.com.permission.util.PackageUtil;
import android.by.com.permission.util.PrefUtil;
import android.os.Build;
import android.text.TextUtils;
import android.text.format.DateUtils;



/**
 * Created by frankyang on 12/18/15.
 */
public class InAppPermissionGuideStrategy {
    private static  final long THRESHOLD_TIME_FOR_GUIDE_SHOW = (long) (DateUtils.DAY_IN_MILLIS *4);


    public static boolean isTargetManufacturerForAppPermission() {
        String manufacturer = Build.MANUFACTURER;
        if (TextUtils.isEmpty(manufacturer)) return false;
        boolean isTargetMatched = false;
        if (PackageUtil.isPackageInstalled(PackageUtil.OPPO_PERMISSION_SETTING_PACKAGE_NAMES)) {
                isTargetMatched = true;
        } else if (OSUtil.isMiuiV6() || OSUtil.isMiuiV7() || OSUtil.isMiuiV5() || OSUtil.isMiuiV8()) {
            isTargetMatched = true;
        } else if (PackageUtil.isPackageInstalled(PackageUtil.MEIZU_PHONE_MANAGER)) {
            isTargetMatched = true;
        } else if (PackageUtil.isPackageInstalled(PackageUtil.HUAWEI_PHONE_MANAGER)){
            isTargetMatched = true;
        } else if (PackageUtil.isPackageInstalled(PackageUtil.VIVO_PHONE_MANAGER)) {
            isTargetMatched = true;
        } else if (PackageUtil.isPackageInstalled(PackageUtil.SMARTISANOS_CENTER)) {
            isTargetMatched = true;
        } else if (PackageUtil.isPackageInstalled(PackageUtil.SAMSUNG_PERMISSION_SETTINGS_PACKAGE_NAMES)) {
            isTargetMatched = true;
        } else if (PackageUtil.isPackageInstalled(GuideConst.ZTE_PERMISSION_PACKAGE_NAME)
                && ZtePermissionGuideStrategy.getPermissionManagerVersion() >= ZtePermissionGuideStrategy.VERSION_6) {
            isTargetMatched = true;
        }
        return isTargetMatched;
    }


    private static boolean isMatchAutoBootGuideShowCondition(){
        long oldTime = PrefUtil.getKeyLong(PrefKeys.APP_INSTALL_UPDATE_START_TIME, System.currentTimeMillis());
        return ((PrefUtil.getKeyBoolean(PrefKeys.SHOULD_SHOW_AUTOBOOT_GUIDE_FOR_UPGRADE_USER,false)
                && PrefUtil.getKeyInt(StartupVerifier.INSTALL_TYPE, StartupVerifier.INSTALL_TYPE_NEW) == StartupVerifier.INSTALL_TYPE_UPGRADE)
                || PrefUtil.getKeyInt(StartupVerifier.INSTALL_TYPE,StartupVerifier.INSTALL_TYPE_UPGRADE) == StartupVerifier.INSTALL_TYPE_NEW)
                && (System.currentTimeMillis() - oldTime > THRESHOLD_TIME_FOR_GUIDE_SHOW);
//by修改
//                && C2CUtil.isVoipModeOn();
    }

    public static boolean checkAutoStartupPermissionView() {
        if (!isTargetManufacturerForAppPermission()
                || !isMatchAutoBootGuideShowCondition()) {
            return false;
        }

        IPermissionGuideStrategy strategy = PermissionGuideGenerator.generateGuideStratagy(ModelManager.getContext());
        return !strategy.allOfTheGivenPermissionsSetted(strategy.getPermissionList(IPermissionGuideStrategy.INAPP_TYPE));
    }

    public static boolean shouldShowInAppToastPermissionGuide() {
        if (!PermissionGuideUtil.shouldShowToastPermissionEntry()
                || !PrefUtil.getKeyBoolean(PrefKeys.SHOW_IN_APP_TOAST_PERMISSION, true)) {
            return false;
        }
        IPermissionGuideStrategy strategy = PermissionGuideGenerator.generateGuideStratagy(ModelManager.getContext());
        return !strategy.allOfTheGivenPermissionsSetted(strategy.getPermissionList(IPermissionGuideStrategy.TOAST_TYPE));
//by修改
//                && CallerHistoryProvider.getInst().getRecognizeCount() > 0;
    }
}
