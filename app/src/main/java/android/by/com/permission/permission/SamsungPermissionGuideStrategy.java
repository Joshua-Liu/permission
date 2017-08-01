package android.by.com.permission.permission;

import android.by.com.permission.R;
import android.by.com.permission.constant.GuideConst;
import android.by.com.permission.model.ModelManager;
import android.by.com.permission.receiver.HomeButtonReceiver;
import android.by.com.permission.util.PackageUtil;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;




import java.util.ArrayList;

/**
 * Created by chao.yuan on 12/9/15.
 */
public class SamsungPermissionGuideStrategy extends IPermissionGuideStrategy {

    private ViewGroup mParentLayout;
    private HomeButtonReceiver mHomeButtonReceiver;
    private HomeButtonReceiver.HomeButtonCallback mHomeButtonCallback;


    public SamsungPermissionGuideStrategy(Context mContext) {
        super(mContext);
    }

    @Override
    protected void actionReadCalllog() {
        super.actionReadCalllog();
    }

    @Override
    protected void actionReadContact() {
        super.actionReadContact();
    }

    @Override
    protected void actionAutoBootPermission() {
        super.actionAutoBootPermission();

        try {
            int index = getPermissionPackageIndex();
            if (index == 0) {
                Intent sysIntent = new Intent();
                sysIntent.setClassName("com.samsung.memorymanager", "com.samsung.memorymanager.RamActivity");
                mContext.startActivity(sysIntent);

                ModelManager.getInst().getHandler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mHomeButtonCallback = new HomeButtonReceiver.HomeButtonCallback() {
                            @Override
                            public void onClick() {
                                try {
                                    if (mParentLayout !=  null) {
                                        WindowManager windowManager = (WindowManager)mContext.getSystemService(Context.WINDOW_SERVICE);
                                        windowManager.removeViewImmediate(mParentLayout);
                                        mContext.unregisterReceiver(mHomeButtonReceiver);
                                    }
                                } catch (Exception e) {

                                }
                            }
                        };
                        mHomeButtonReceiver = new HomeButtonReceiver(mHomeButtonCallback);
                        View view = LayoutInflater.from(mContext).inflate(R.layout.scr_sansung_permission_autoboot,null);
                        mParentLayout = popupWindow(view, mContext, mHomeButtonReceiver);
                        mContext.registerReceiver(mHomeButtonReceiver,new IntentFilter(Intent.ACTION_CLOSE_SYSTEM_DIALOGS));
                    }
                }, 1000);
            } else if (index == 1) {
                Intent intent = new Intent(mContext, SamSungPermissionGuide.class);
                mContext.startActivity(intent);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    @Override
    protected void actionDataPermission() {
        super.actionDataPermission();
        super.actionDataPermission();
        ModelManager.getInst().registerContentObserver(ModelManager.getContext(), true);
//        ModelManager.getInst().getSMSMessage().syncObsoleteSms();
    }

    @Override
    protected boolean supportGuide() {
        return false;
    }

    @Override
    protected int getColor() {
        return ModelManager.getContext().getResources().getColor(R.color.samsung);
    }

    @Override
    protected int getPressedColor() {
        return ModelManager.getContext().getResources().getColor(R.color.samsung_pressed);
    }

    @Override
    public ArrayList<String> getPermissionList(int type) {
        ArrayList<String> ret = new ArrayList<String>();
        if (type == IPermissionGuideStrategy.TUTORIAL_TYPE) {
        } else if (type == IPermissionGuideStrategy.INAPP_TYPE) {
            ret.add(GuideConst.AUTOBOOT_PERMISSION);
        }

        return ret;
    }

    @Override
    protected String getPermissionTitle() {
        return ModelManager.getContext().getString(R.string.permission_guide_title,mContext.getString(R.string.samsung_manufacturer));
    }

    @Override
    protected String getPermissionTitle(String permission, int type) {
        String os = mContext.getString(R.string.samsung_manufacturer);
        if (type == IPermissionGuideStrategy.INAPP_TYPE) {
            return ModelManager.getContext().getString(R.string.important_permission_guide_title, os);
        } else if (type == IPermissionGuideStrategy.TUTORIAL_TYPE) {
            return ModelManager.getContext().getString(R.string.permission_title_tutorial);
        } else if (GuideConst.AUTOBOOT_PERMISSION.equals(permission)) {
            return ModelManager.getContext().getString(R.string.important_permission_guide_title, os);
        }

        return getPermissionTitle();
    }

    @Override
    protected PermissionGuideStepItem getPermissionGuideStepItem(String permission, int type) {
        return new PermissionGuideStepItem(
                type == IPermissionGuideStrategy.TUTORIAL_TYPE ? R.string.permission_others_title_tutorial : R.string.permission_guide_hint_one,
                new int[]{R.string.permission_background_protection_samsung},
                new int[][]{new int[]{R.drawable.samsung_background_permission}});
    }

    private int getPermissionPackageIndex() {
        int pos = -1;
        for(int index = 0; index < PackageUtil.SAMSUNG_PERMISSION_SETTINGS_PACKAGE_NAMES.length; index++) {
            if (PackageUtil.isPackageInstalled(PackageUtil.SAMSUNG_PERMISSION_SETTINGS_PACKAGE_NAMES[index])) {
                pos = index;
                break;
            }
        }
        return pos;
    }

}
