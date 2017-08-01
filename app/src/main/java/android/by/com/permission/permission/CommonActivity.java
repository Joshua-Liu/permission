package android.by.com.permission.permission;

import android.by.com.permission.base.TPBaseActivity;
import android.by.com.permission.constant.GuideConst;
import android.by.com.permission.model.ModelManager;
import android.by.com.permission.other.XiaomiGuide;
import android.by.com.permission.util.OSUtil;
import android.by.com.permission.util.PackageUtil;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;




/**
 * Created by frankyang on 6/14/15.
 */
public class CommonActivity extends TPBaseActivity {
    private static final String TAG = "CommonActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        if (OSUtil.isMiuiV6() || OSUtil.isMiuiV7() || OSUtil.isMiuiV8()
//                || OSUtil.isMiuiV5()
//                || PackageUtil.isPackageInstalled(PackageUtil.HUAWEI_PHONE_MANAGER)
//                || PackageUtil.isPackageInstalled(PackageUtil.OPPO_PERMISSION_SETTING_PACKAGE_NAMES)) {
//            PermissionGuideGenerator.generateGuideStratagy(this).clickCalllogOrContactPermission();
//        }
        if (OSUtil.isMiuiV6() || OSUtil.isMiuiV7() || OSUtil.isMiuiV8()) {
            try {
                Intent sysIntent = new Intent();
                sysIntent.putExtra("extra_pkgname", getPackageName());
                sysIntent.setClassName(GuideConst.MIUI_V6_PERMISSION_PACKAGE_NAME, GuideConst.MIUI_V6_APP_PERMISSION_ACTIVITY_NAME);
                startActivity(sysIntent);
                ModelManager.getInst().getHandler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        //by修改
                        String activityName = null;
//                        String activityName = Build.VERSION.SDK_INT >= 20 ? BalloonLauncher.getTopPackagesInLollipop() : BalloonLauncher.getTopActivityPreLollipop();
                        if ((Build.VERSION.SDK_INT < 20 && GuideConst.MIUI_V6_APP_PERMISSION_ACTIVITY_NAME.equals(activityName)) || (Build.VERSION.SDK_INT >= 20 && GuideConst.MIUI_V6_PERMISSION_PACKAGE_NAME.equals(activityName))) {
                            Intent guideIntent = new Intent(CommonActivity.this, XiaomiGuide.class);
                            startActivity(guideIntent);
                        }
                    }
                }, 2000);
            } catch (ActivityNotFoundException e) {

            }
        } else if (OSUtil.isMiuiV5()) {
            try {
                Intent sysIntent = new Intent();
                sysIntent.putExtra("extra_package_uid", android.os.Process.myUid());
                sysIntent.setClassName(GuideConst.MIUI_V5_PERMISSION_PACKAGE_NAME, GuideConst.MIUI_V5_APP_PERMISSION_ACTIVITY_NAME);
                startActivity(sysIntent);
                Intent guideIntent = new Intent(CommonActivity.this, XiaomiGuide.class);
                startActivity(guideIntent);
            } catch (ActivityNotFoundException e) {

            }
        } else if (PackageUtil.isPackageInstalled(PackageUtil.HUAWEI_PHONE_MANAGER)) {
            PermissionGuideGenerator.generateGuideStratagy(this).clickCalllogOrContactPermission();
        }
        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // 行为序列：完成权限引导
//        StatRecorder.record(StatConst.PATH_USAGE_SEQUENCE, StatConst.KEY_USAGE_SEQUENCE, StatConst.ID_OF_PERMISSION_GUIDE);
    }
}
