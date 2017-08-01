package android.by.com.permission.permission;

import android.app.Activity;
import android.by.com.permission.R;
import android.by.com.permission.constant.GuideConst;
import android.by.com.permission.model.ModelManager;
import android.by.com.permission.model.StartupVerifier;
import android.by.com.permission.permission.IPermissionGuideStrategy;
import android.by.com.permission.util.PrefUtil;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.util.Log;


import java.util.ArrayList;

import static android.by.com.permission.constant.Constants.PACKAGE_NAME;

/**
 * Created by frankyang on 12/9/15.
 */
public class CoolpadPermissionGuideStrategy extends IPermissionGuideStrategy {

    public CoolpadPermissionGuideStrategy(Context mContext) {
        super(mContext);
    }

    private static String TAG = "CoolpadPermission";
    @Override
    protected void actionDataPermission() {
        super.actionDataPermission();
        ModelManager.getInst().registerContentObserver(ModelManager.getContext(), true);
//        ModelManager.getInst().getSMSMessage().syncObsoleteSms();
    }

    @Override
    protected void actionTrustApplicationPermission(boolean showGuide) {
        super.actionTrustApplicationPermission(showGuide);
        try {
            Intent sysIntent = new Intent();
            sysIntent.setClassName("com.yulong.android.seccenter",
                    "com.yulong.android.seccenter.dataprotection.ui.PkgPermActivity");
            sysIntent.putExtra("pkgName", PACKAGE_NAME);
            mContext.startActivity(sysIntent);
            Intent guideIntent = new Intent(ModelManager.getContext(),OuterPermissionActivity.class);
            guideIntent.putExtra(OuterPermissionActivity.VIEWSTUB_ID, R.layout.scr_coolpad_application_permission_guide);
            mContext.startActivity(guideIntent);
        } catch (ActivityNotFoundException e) {
            Log.i(TAG,e.getMessage());
        } catch (SecurityException e) {
            Log.i(TAG,e.getMessage());
        }
    }

    @Override
    protected int getColor() {
        return mContext.getResources().getColor(R.color.coolpad);
    }

    @Override
    public ArrayList<String> getPermissionList(int type) {
        ArrayList<String> ret = new ArrayList<String>();
        if (type == IPermissionGuideStrategy.TUTORIAL_TYPE) {
            ret.add(GuideConst.TRUST_APPLICATION_PERMISSION_COOLPAD);
            if (!(PrefUtil.getKeyInt(StartupVerifier.INSTALL_TYPE, StartupVerifier.INSTALL_TYPE_NEW) == StartupVerifier.INSTALL_TYPE_UPGRADE)) {
                ret.add(GuideConst.DATA_PERMISSION);
            }
        } else if (type == IPermissionGuideStrategy.INAPP_TYPE) {
            ret.add(GuideConst.TRUST_APPLICATION_PERMISSION_COOLPAD);
        }

        return ret;
    }

    @Override
    protected String getPermissionTitle() {
        return ModelManager.getContext().getString(R.string.permission_guide_title, "酷派");
    }


}
