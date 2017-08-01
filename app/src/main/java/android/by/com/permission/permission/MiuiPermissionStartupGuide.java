package android.by.com.permission.permission;

import android.by.com.permission.R;
import android.by.com.permission.base.TPBaseActivity;
import android.by.com.permission.constant.Constants;
import android.by.com.permission.constant.GuideConst;
import android.by.com.permission.constant.StatConst;
import android.by.com.permission.layout.TDialog;
import android.by.com.permission.model.ModelManager;
import android.by.com.permission.model.StartupVerifier;
import android.by.com.permission.util.FileUtils;
import android.by.com.permission.util.PrefUtil;
import android.by.com.permission.util.SingleThreadExecutor;
import android.by.com.permission.util.StrUtil;
import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;



import java.util.HashMap;

/**
 * Created by junhao on 6/24/15.
 */
public class MiuiPermissionStartupGuide extends TPBaseActivity {

    private enum PermissionType {
        CALL_PERMISSION,
        AUTO_BOOT_PERMISSION,
        DATA_PERMISSION
    }

    public static final String START_MAIN_SCREEN_WHEN_EXIT = "start_main_screen_when_exit";
    private static final int CALL_PERMISSION_DONE = 1;
    private static final int AUTO_BOOT_PERMISSION_DONE = 2;
    private static final int DATA_PERMISSION_DONE = 4;
    private static final String TAG = "MiuiPermission";

    private static final int BONUS_FREE_MIUITE = 500;

    private int mCurrentState;
    private int mFinishState;

    private HashMap<String, Object> mStatMap = new HashMap<String, Object>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mFinishState = PrefUtil.getKeyInt(StartupVerifier.INSTALL_TYPE, StartupVerifier.INSTALL_TYPE_NEW) == StartupVerifier.INSTALL_TYPE_NEW ?
                CALL_PERMISSION_DONE | AUTO_BOOT_PERMISSION_DONE | DATA_PERMISSION_DONE : AUTO_BOOT_PERMISSION_DONE;

        setContentView(R.layout.scr_miui_permission_guide);

        refreshPermissionState();

        setupUI();

        View callButton = findViewById(R.id.call_permission);
        callButton.setOnClickListener(mClickListener);
        View autoBootButton = findViewById(R.id.autoboot_permission);
        autoBootButton.setOnClickListener(mClickListener);
        View dataButton = findViewById(R.id.data_permission);
        dataButton.setOnClickListener(mClickListener);
        View back = findViewById(R.id.funcbar_back);
        back.setOnClickListener(mClickListener);
        mStatMap.put(StatConst.ROM, StatConst.ROM_MIUIV6);
        mStatMap.put(StatConst.INSTALL_TYPE, PrefUtil.getKeyInt(StartupVerifier.INSTALL_TYPE, StartupVerifier.INSTALL_TYPE_NEW));
        mStatMap.put(StatConst.PERMISSION_GUIDE_VISITED, 1);
        SingleThreadExecutor.getInst().execute(new Runnable() {
            @Override
            public void run() {
                String result = StrUtil.convertStringToJson(StatConst.PATH_USAGE_SEQUENCE, StatConst.ID_OF_PERMISSION_GUIDE);
                FileUtils.writeFileByAppend(Constants.UPLOAD_DIR, Constants.UPLOAD_FILE, result);
            }
        });
    }

    private void setupUI() {
        int launchType = PrefUtil.getKeyInt(StartupVerifier.INSTALL_TYPE, StartupVerifier.INSTALL_TYPE_NEW);
        View dataPermission = findViewById(R.id.data_permission_container);
        dataPermission.setVisibility(launchType == StartupVerifier.INSTALL_TYPE_NEW ? View.VISIBLE : View.GONE);
        View callPermission = findViewById(R.id.call_permission);
        callPermission.setVisibility(launchType == StartupVerifier.INSTALL_TYPE_NEW ? View.VISIBLE : View.GONE);
    }

    private void setPermissionState(PermissionType type, boolean done, boolean enabled) {
        Button permissionButton = null;
        switch (type) {
            case CALL_PERMISSION:
                permissionButton = (Button)findViewById(R.id.call_permission);
                break;
            case AUTO_BOOT_PERMISSION:
                permissionButton = (Button)findViewById(R.id.autoboot_permission);
                break;
            case DATA_PERMISSION:
                permissionButton = (Button)findViewById(R.id.data_permission);
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
                case R.id.call_permission:
                    launchPermissionGuide(PermissionType.CALL_PERMISSION);
                    break;
                case R.id.autoboot_permission:
                    launchPermissionGuide(PermissionType.AUTO_BOOT_PERMISSION);
                    break;
                case R.id.data_permission:
                    launchPermissionGuide(PermissionType.DATA_PERMISSION);
                    break;
                case R.id.funcbar_back:
                    confirmExit();
                default:
                    break;
            }
        }
    };

    private void confirmExit() {
        if (mCurrentState != (CALL_PERMISSION_DONE | AUTO_BOOT_PERMISSION_DONE | DATA_PERMISSION_DONE)) {
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
        if ((mCurrentState & CALL_PERMISSION_DONE) == 0 && ((mFinishState & CALL_PERMISSION_DONE) == CALL_PERMISSION_DONE)) {
            setPermissionState(PermissionType.CALL_PERMISSION, false, true);
            setPermissionState(PermissionType.AUTO_BOOT_PERMISSION, false, false);
            setPermissionState(PermissionType.DATA_PERMISSION, false, false);
        } else if ((mCurrentState & AUTO_BOOT_PERMISSION_DONE) == 0 && ((mFinishState & AUTO_BOOT_PERMISSION_DONE) == AUTO_BOOT_PERMISSION_DONE)) {
            setPermissionState(PermissionType.CALL_PERMISSION, true, true);
            setPermissionState(PermissionType.AUTO_BOOT_PERMISSION, false, true);
            setPermissionState(PermissionType.DATA_PERMISSION, false, false);

        } else if ((mCurrentState & DATA_PERMISSION_DONE) == 0) {
            setPermissionState(PermissionType.CALL_PERMISSION, true, true);
            setPermissionState(PermissionType.AUTO_BOOT_PERMISSION, true, true);
            setPermissionState(PermissionType.DATA_PERMISSION, false, true);
        } else {
            setPermissionState(PermissionType.CALL_PERMISSION, true, true);
            setPermissionState(PermissionType.AUTO_BOOT_PERMISSION, true, true);
            setPermissionState(PermissionType.DATA_PERMISSION, true, true);

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
    protected void onResume() {
        super.onResume();
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
            case CALL_PERMISSION:
                launchCallPermissionSetting();
                newState = CALL_PERMISSION_DONE;
                mCurrentState |= newState;
                break;
            case AUTO_BOOT_PERMISSION:
                launchAutoStartSetting();
                newState = AUTO_BOOT_PERMISSION_DONE;
                mCurrentState |= newState;
                break;
            case DATA_PERMISSION:
                ModelManager.getInst().registerContentObserver(getApplicationContext(), true);
//                ModelManager.getInst().getSMSMessage().syncObsoleteSms();
                newState = DATA_PERMISSION_DONE;
                mCurrentState |= newState;
                refreshPermissionState();
                finish();
                break;
            default:
                throw new IllegalArgumentException();
        }
    }


    private void launchAutoStartSetting() {
        try {
            Intent sysIntent = new Intent();
            sysIntent.setClassName(GuideConst.MIUI_V6_PERMISSION_PACKAGE_NAME,
                    GuideConst.MIUI_V6_AUTO_START_PERMISSION_ACTIVITY_NAME);
            startActivity(sysIntent);
            Intent guideIntent = new Intent(MiuiPermissionStartupGuide.this, MiuiPermissionGeneralGuide.class);
            guideIntent.putExtra(MiuiPermissionGeneralGuide.EXTRA_PERMISSION_TYPE, MiuiPermissionGeneralGuide.AUTO_START_PERMISSION_GUIDE);
            startActivity(guideIntent);
        } catch (ActivityNotFoundException e) {
            Log.e(TAG,e.getMessage());
        } catch (SecurityException e) {
            Log.e(TAG,e.getMessage());
        }
    }

    private void launchCallPermissionSetting() {
        try {
            Intent sysIntent = new Intent();
            sysIntent.putExtra("extra_pkgname", getPackageName());
            sysIntent.setClassName(GuideConst.MIUI_V6_PERMISSION_PACKAGE_NAME,
                    GuideConst.MIUI_V6_APP_PERMISSION_ACTIVITY_NAME);
            startActivity(sysIntent);
            Intent guideIntent = new Intent(MiuiPermissionStartupGuide.this, MiuiGuide.class);
            startActivity(guideIntent);
        } catch (ActivityNotFoundException e) {
            Log.e(TAG,e.getMessage());
        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
