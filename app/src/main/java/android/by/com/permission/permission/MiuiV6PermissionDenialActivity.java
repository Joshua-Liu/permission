package android.by.com.permission.permission;

import android.by.com.permission.R;
import android.by.com.permission.base.TPBaseActivity;
import android.by.com.permission.constant.Constants;
import android.by.com.permission.constant.GuideConst;
import android.by.com.permission.constant.StatConst;
import android.by.com.permission.layout.TDialog;
import android.by.com.permission.model.ModelManager;
import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;



import java.util.HashMap;
import java.util.Map;

/**
 * Created by junhao on 6/26/15.
 */
public class MiuiV6PermissionDenialActivity extends TPBaseActivity {
    public static final int CALLLOG_PERMISSION = 1;
    public static final int CONTACT_PERMISSON = 2;
    public static final int CALL_PERMISSION = 3;

    public static final String EXTRA_PERMISSION_DENIAL_TYPE = "extra_permission_denial_type";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final int type = getIntent().getIntExtra(EXTRA_PERMISSION_DENIAL_TYPE, 0);
        if (type == 0) {
            throw new IllegalArgumentException();
        }
        String statKey = Constants.EMPTY_STR;
        String permissionText = Constants.EMPTY_STR;
        switch (type) {
            case CALLLOG_PERMISSION:
                permissionText = getString(R.string.miui_permission_calllog);
                statKey = StatConst.CALLLOG_PERMISSION_DENIAL;
                break;
            case CONTACT_PERMISSON:
                permissionText = getString(R.string.miui_permission_contact);
                statKey = StatConst.CONTACT_PERMISSOIN_DENIAL;
                break;
            case CALL_PERMISSION:
                permissionText = getString(R.string.miui_permission_call);
                statKey = StatConst.CALL_PERMISSION_DENIAL;
                break;
            default:
                break;
        }

        final String statKeyFinal = statKey;
        final TDialog dialog = new TDialog(this, TDialog.STYLE_BUTTON_ZERO);
        View view = LayoutInflater.from(this).inflate(R.layout.dlg_miui_permission_denial,null);
        dialog.setContentView(view);
        TextView content = (TextView) view.findViewById(R.id.content);
        content.setText(Html.fromHtml(getString(R.string.miui_permission_denial_dlg_content, permissionText) + permissionText));
        View confirm = view.findViewById(R.id.confirm);
        dialog.setTitle(R.string.miui_permission_denial_dlg_title);
        final Map<String, Object> map = new HashMap<String, Object>();
        map.put(StatConst.ROM, StatConst.ROM_MIUIV6);
        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                map.put(statKeyFinal, true);
//                StatRecorder.record(StatConst.PATH_PERMISSION_DENIAL, map);
                dialog.dismiss();
                startPermissionSetting(GuideConst.MIUI_V6_APP_PERMISSION_ACTIVITY_NAME, type);
            }
        });
        dialog.show();
        dialog.setCanceledOnTouchOutside(false);
        dialog.setCancelable(true);
        dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                finish();
            }
        });
        dialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialog) {
                map.put(statKeyFinal, false);
//                StatRecorder.record(StatConst.PATH_PERMISSION_DENIAL, map);
            }
        });
    }

    private void startPermissionSetting(String activityName, final int type) {
        try {
            Intent sysIntent = new Intent();
            sysIntent.putExtra("extra_pkgname", getPackageName());
            sysIntent.setClassName(GuideConst.MIUI_V6_PERMISSION_PACKAGE_NAME,
                    activityName);
            startActivity(sysIntent);
            ModelManager.getInst().getHandler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    //by修改
                    String activityName = null;
//                    String activityName = Build.VERSION.SDK_INT >= 20 ?
//                            BalloonLauncher.getTopPackagesInLollipop()
//                            : BalloonLauncher.getTopActivityPreLollipop();
                    if ((Build.VERSION.SDK_INT < 20
                            && GuideConst.MIUI_V6_APP_PERMISSION_ACTIVITY_NAME.equals(activityName))
                            || (Build.VERSION.SDK_INT >= 20
                            && GuideConst.MIUI_V6_PERMISSION_PACKAGE_NAME.equals(activityName))) {
                        Intent guideIntent = null;
                        switch (type) {
                            case CALLLOG_PERMISSION:
                                guideIntent = new Intent(MiuiV6PermissionDenialActivity.this, MiuiPermissionGeneralGuide.class);
                                guideIntent.putExtra(MiuiPermissionGeneralGuide.EXTRA_PERMISSION_TYPE, MiuiPermissionGeneralGuide.CALLLOG_PERMISSION_GUIDE);
                                break;
                            case CONTACT_PERMISSON:
                                guideIntent = new Intent(MiuiV6PermissionDenialActivity.this, MiuiPermissionGeneralGuide.class);
                                guideIntent.putExtra(MiuiPermissionGeneralGuide.EXTRA_PERMISSION_TYPE, MiuiPermissionGeneralGuide.CONTACT_PERMISSION_GUIDE);
                                break;
                            case CALL_PERMISSION:
                                guideIntent = new Intent(MiuiV6PermissionDenialActivity.this, MiuiGuide.class);
                                break;
                            default:
                                break;
                        }
                        startActivity(guideIntent);
                    }
                }
            }, 0);
        } catch (ActivityNotFoundException e) {
            String developerActivity = "com.miui.permcenter.permissions.PermissionsEditorActivity";
            if (!developerActivity.equals(activityName)) {
                startPermissionSetting(developerActivity, type);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
