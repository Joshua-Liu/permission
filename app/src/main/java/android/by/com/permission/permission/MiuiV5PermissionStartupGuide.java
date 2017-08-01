package android.by.com.permission.permission;

import android.by.com.permission.R;
import android.by.com.permission.base.TPBaseActivity;
import android.by.com.permission.constant.StatConst;
import android.by.com.permission.layout.TDialog;
import android.by.com.permission.model.StartupVerifier;
import android.by.com.permission.util.PrefUtil;
import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;



import java.util.HashMap;

/**
 * Created by junhao on 6/24/15.
 */
public class MiuiV5PermissionStartupGuide extends TPBaseActivity {

    private final static String TAG = "MiuiV5Permission";
    private enum PermissionType {
        APP_PERMISSION,
        TOAST_PERMISSION
    }

    public static final String START_MAIN_SCREEN_WHEN_EXIT = "start_main_screen_when_exit";
    private static final int APP_PERMISSION_DONE = 1;
    private static final int TOAST_PERMISSION_DONE = 2;

    private static final int BONUS_FREE_MIUITE = 500;

    private int mCurrentState = 0;
    private int mFinishState;
    private HashMap<String, Object> mStatMap = new HashMap<String, Object>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mFinishState = APP_PERMISSION_DONE | TOAST_PERMISSION_DONE;

        setContentView(R.layout.scr_miuiv5_permission_guide);
        TextView lineTwoWithoutLogin = (TextView) findViewById(R.id.line_two);
        SpannableString spannableString = new SpannableString(getString(R.string.miuiv5_permission_guide_line_two));
        ForegroundColorSpan colorSpan = new ForegroundColorSpan(getResources().getColor(R.color.deep_orange_500));
        spannableString.setSpan(colorSpan, 1, 3, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        lineTwoWithoutLogin.setText(spannableString);

        refreshPermissionState();
        View appButton = findViewById(R.id.app_permission);
        appButton.setOnClickListener(mClickListener);
        View toastButton = findViewById(R.id.toast_permission);
        toastButton.setOnClickListener(mClickListener);
        View back = findViewById(R.id.funcbar_back);
        back.setOnClickListener(mClickListener);

        mStatMap.put(StatConst.ROM, StatConst.ROM_MIUIV5);
        mStatMap.put(StatConst.INSTALL_TYPE, PrefUtil.getKeyInt(StartupVerifier.INSTALL_TYPE, StartupVerifier.INSTALL_TYPE_NEW));
        mStatMap.put(StatConst.PERMISSION_GUIDE_VISITED, 1);
    }

    private void setPermissionState(PermissionType type, boolean done, boolean enabled) {
        Button permissionButton = null;
        switch (type) {
            case APP_PERMISSION:
                permissionButton = (Button)findViewById(R.id.app_permission);
                break;
            case TOAST_PERMISSION:
                permissionButton = (Button)findViewById(R.id.toast_permission);
                break;
            default:
                throw new IllegalArgumentException();
        }
        permissionButton.setEnabled(enabled);
        permissionButton.setSelected(done);
    }


    private View.OnClickListener mClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.app_permission:
                    launchPermissionGuide(PermissionType.APP_PERMISSION);
                    break;
                case R.id.toast_permission:
                    launchPermissionGuide(PermissionType.TOAST_PERMISSION);
                    break;
                case R.id.funcbar_back:
                    confirmExit();
                default:
                    break;
            }
        }
    };

    private void confirmExit() {
        if (mCurrentState != (APP_PERMISSION_DONE | TOAST_PERMISSION_DONE)) {
            final TDialog dialog = new TDialog(this, TDialog.STYLE_BUTTON_ZERO, false);
            View content = LayoutInflater.from(this).inflate(R.layout.dlg_permission_guide_exit_confirm, null);
            dialog.setContentView(content);
            dialog.setCanceledOnTouchOutside(false);
            dialog.setTitle(R.string.permission_guide_exit_confirm_title);
            Button cancel = (Button)content.findViewById(R.id.cancel);
            Button confirm = (Button)content.findViewById(R.id.confirm);
            cancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mStatMap.put(StatConst.STARTUP_CONFIRM_DIALOG, StatConst.STARTUP_CONFIRM_DIALOG_ACTION_NEGTIVE);
                    dialog.dismiss();
                    finish();
                }
            });
            confirm.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mStatMap.put(StatConst.STARTUP_CONFIRM_DIALOG, StatConst.STARTUP_CONFIRM_DIALOG_ACTION_POSITIVE);
                    dialog.dismiss();
                }
            });
            dialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
                @Override
                public void onCancel(DialogInterface dialog) {
                    mStatMap.put(StatConst.STARTUP_CONFIRM_DIALOG, StatConst.STARTUP_CONFIRM_DIALOG_ACTION_CANCEL);
                }
            });
            dialog.show();
        } else {
            finish();
        }
    }


    @Override
    public void onBackPressed() {
        confirmExit();
    }

    @Override
    public void finish() {
        if (getIntent().getBooleanExtra(START_MAIN_SCREEN_WHEN_EXIT, false)) {
            //by修改
//            startActivity(IntentUtil.getStartupIntentClearTop(this));
        }
        mStatMap.put(StatConst.PERMISSION_EXIT_STATE, mCurrentState);
//        StatRecorder.record(StatConst.PATH_PERMISSION, mStatMap);
        super.finish();

    }

    private void refreshPermissionState() {
        if ((mCurrentState & APP_PERMISSION_DONE) == 0 && ((mFinishState & APP_PERMISSION_DONE) == APP_PERMISSION_DONE)) {
            setPermissionState(PermissionType.APP_PERMISSION, false, true);
            setPermissionState(PermissionType.TOAST_PERMISSION, false, false);
        } else if ((mCurrentState & TOAST_PERMISSION_DONE) == 0 && ((mFinishState & TOAST_PERMISSION_DONE) == TOAST_PERMISSION_DONE)) {
            setPermissionState(PermissionType.APP_PERMISSION, true, true);
            setPermissionState(PermissionType.TOAST_PERMISSION, false, true);

        } else {
            setPermissionState(PermissionType.APP_PERMISSION, true, true);
            setPermissionState(PermissionType.TOAST_PERMISSION, true, true);

        }
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        refreshPermissionState();
        if (mCurrentState == mFinishState) {
            finish();
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    private void launchPermissionGuide(PermissionType type) {
        int newState = 0;
        switch (type) {
            case APP_PERMISSION:
                launchAppPermissionSetting();
                newState = APP_PERMISSION_DONE;
                mCurrentState |= newState;
                break;
            case TOAST_PERMISSION:
                launchToastSetting();
                newState = TOAST_PERMISSION_DONE;
                mCurrentState |= newState;
                break;
            default:
                throw new IllegalArgumentException();
        }
    }


    private void launchToastSetting() {
        try {
            int i = Build.VERSION.SDK_INT;
            Intent sysIntent = new Intent();
            String packageName = getPackageName();
            if (i > 8) {
                sysIntent.setAction("android.settings.APPLICATION_DETAILS_SETTINGS");
                sysIntent.setData(Uri.fromParts("package",
                        packageName, null));
            } else {
                sysIntent.setAction("android.intent.action.VIEW");
                sysIntent.setClassName("com.android.settings",
                        "com.android.settings.InstalledAppDetails");
                sysIntent.putExtra("pkg", packageName);
            }
            startActivity(sysIntent);
            Intent guideIntent = new Intent(MiuiV5PermissionStartupGuide.this, MiuiV5ToastPermissionGuide.class);
            startActivity(guideIntent);
        } catch (ActivityNotFoundException e) {
            Log.i(TAG,e.getMessage());
        }
    }

    private void launchAppPermissionSetting() {
        try {
            Intent guideIntent = new Intent(MiuiV5PermissionStartupGuide.this, CommonActivity.class);
            startActivity(guideIntent);
        } catch (ActivityNotFoundException e) {
            Log.i(TAG,e.getMessage());
        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
