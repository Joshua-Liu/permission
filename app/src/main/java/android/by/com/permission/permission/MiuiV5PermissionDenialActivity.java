package android.by.com.permission.permission;

import android.by.com.permission.R;
import android.by.com.permission.base.TPBaseActivity;
import android.by.com.permission.constant.Constants;
import android.by.com.permission.constant.StatConst;
import android.by.com.permission.layout.TDialog;
import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
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
public class MiuiV5PermissionDenialActivity extends TPBaseActivity {
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
                permissionText = getString(R.string.miuiv5_permission_denial_dlg_content_calllog);
                statKey = StatConst.CALLLOG_PERMISSION_DENIAL;
                break;
            case CONTACT_PERMISSON:
                permissionText = getString(R.string.miuiv5_permission_denial_dlg_content_contact);
                statKey = StatConst.CONTACT_PERMISSOIN_DENIAL;
                break;
            case CALL_PERMISSION:
                permissionText = getString(R.string.miuiv5_permission_denial_dlg_content_call);
                statKey = StatConst.CALL_PERMISSION_DENIAL;
                break;
            default:
                break;
        }

        final String statKeyFinal = statKey;
        final Map<String, Object> map = new HashMap<String, Object>();
        map.put(StatConst.ROM, StatConst.ROM_MIUIV5);
        final TDialog dialog = new TDialog(this, TDialog.STYLE_BUTTON_ZERO);
        View view = LayoutInflater.from(this).inflate(R.layout.dlg_miui_permission_denial,null);
        dialog.setContentView(view);
        TextView content = (TextView) view.findViewById(R.id.content);
        content.setText(Html.fromHtml(permissionText));
        View confirm = view.findViewById(R.id.confirm);
        dialog.setTitle(R.string.miui_permission_denial_dlg_title);
        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    map.put(statKeyFinal, true);
//                    StatRecorder.record(StatConst.PATH_PERMISSION_DENIAL, map);
                    Intent guideIntent = new Intent(MiuiV5PermissionDenialActivity.this, CommonActivity.class);
                    startActivity(guideIntent);
                    dialog.dismiss();
                } catch (ActivityNotFoundException e) {

                }
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
}
