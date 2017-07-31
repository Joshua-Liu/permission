package android.by.com.permission.permission;

import android.by.com.permission.R;
import android.by.com.permission.constant.GuideConst;
import android.by.com.permission.model.ModelManager;
import android.content.Context;



import java.util.ArrayList;

/**
 * Created by frankyang on 12/3/15.
 */
public class DefaultStrategy extends IPermissionGuideStrategy {

    public DefaultStrategy(Context mContext) {
        super(mContext);
    }

    @Override
    protected void actionDataPermission() {
        super.actionDataPermission();
        ModelManager.getInst().registerContentObserver(ModelManager.getContext(), true);
//        ModelManager.getInst().getSMSMessage().syncObsoleteSms();
    }

    @Override
    protected boolean supportGuide() {
        return false;
    }

    @Override
    public ArrayList<String> getPermissionList(int type) {
        ArrayList<String> ret = new ArrayList<String>();
        ret.add(GuideConst.DEFAULT_PERMISSION);
        return ret;
    }

    @Override
    protected String getPermissionTitle() {
        return ModelManager.getContext().getString(R.string.permission_guide_title,"");
    }

    @Override
    protected String getPermissionTitle(String permission, int type) {
        if (type == IPermissionGuideStrategy.START_UP_TYPE)
            return  ModelManager.getContext().getString(R.string.permission_guide_title, "");
        else if (type == IPermissionGuideStrategy.INAPP_TYPE)
            return  ModelManager.getContext().getString(R.string.important_permission_guide_title, "");
        else if (type == IPermissionGuideStrategy.TUTORIAL_TYPE)
            return  ModelManager.getContext().getString(R.string.permission_title_tutorial);

        return getPermissionTitle();
    }

    @Override
    protected PermissionGuideStepItem getPermissionGuideStepItem(String permission, int type) {
        return new PermissionGuideStepItem(
                R.string.permission_default_tutorial_title,
                new int[]{R.string.permission_default_tutorial_hint1, R.string.permission_default_tutorial_hint2, R.string.permission_default_tutorial_hint3},
                new int[][]{new int[]{R.drawable.default_permission_tutorial_01},
                            new int[]{R.drawable.default_permission_tutorial_02},
                            new int[]{R.drawable.default_permission_tutorial_03}},
                false
        );
    }
}
