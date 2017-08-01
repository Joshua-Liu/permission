package android.by.com.permission.permission;

import android.app.Activity;
import android.by.com.permission.R;
import android.by.com.permission.constant.GuideConst;
import android.by.com.permission.model.ModelManager;
import android.content.Context;
import android.content.Intent;


import java.util.ArrayList;

/**
 * Created by chao.yuan on 12/9/15.
 */
public class SmartisanosPermissionGuideStrategy extends IPermissionGuideStrategy {

    public SmartisanosPermissionGuideStrategy(Context mContext) {
        super(mContext);
    }

    @Override
    protected void actionDataPermission() {
        super.actionDataPermission();
        super.actionDataPermission();
        ModelManager.getInst().registerContentObserver(ModelManager.getContext(), true);
//        ModelManager.getInst().getSMSMessage().syncObsoleteSms();
        ((Activity)mContext).finish();
    }

    @Override
    protected void actionBackgroundPermisssion() {
        super.actionBackgroundPermisssion();
        Intent it = new Intent("com.smartisanos.security.action.MAIN");
        it.putExtra("from_settings", true);
        it.setClassName("com.android.settings", "com.android.settings.applications.ManagerApplicationsRunningActivity");
        mContext.startActivity(it);
        Intent guideIntent = new Intent(mContext, OuterPermissionActivity.class);
        guideIntent.putExtra(OuterPermissionActivity.VIEWSTUB_ID, R.layout.scr_smartisanos_background_permission);
        mContext.startActivity(guideIntent);
    }



    @Override
    public ArrayList<String> getPermissionList(int type) {
        ArrayList<String> ret = new ArrayList<String>();
        if (type == IPermissionGuideStrategy.TUTORIAL_TYPE) {
            ret.add(GuideConst.SMARTISION_BACKGROUND_PROTECT_PERMISSION);
            ret.add(GuideConst.DATA_PERMISSION);
        } else if (type == IPermissionGuideStrategy.INAPP_TYPE) {
            ret.add(GuideConst.SMARTISION_BACKGROUND_PROTECT_PERMISSION);
        }
        return ret;
    }

    @Override
    protected String getPermissionTitle() {
        return ModelManager.getContext().getString(R.string.permission_guide_title,"锤子");
    }

}
