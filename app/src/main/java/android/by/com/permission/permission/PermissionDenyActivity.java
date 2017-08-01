package android.by.com.permission.permission;

import android.by.com.permission.R;
import android.by.com.permission.base.TPBaseActivity;
import android.by.com.permission.constant.Constants;
import android.by.com.permission.constant.GuideConst;
import android.by.com.permission.layout.TDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;



/**
 * Created by frankyang on 12/18/15.
 */
public class PermissionDenyActivity extends TPBaseActivity {

    public static final String EXTRA_PERMISSION_DENIAL_TYPE = "extra_permission_denial_type";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final int type = getIntent().getIntExtra(EXTRA_PERMISSION_DENIAL_TYPE, 0);
        if (type == 0) {
            throw new IllegalArgumentException();
        }

        String permissionText = Constants.EMPTY_STR;
        switch (type) {
            case GuideConst.CALLLOG_PERMISSION:
                permissionText = getString(R.string.deny_permission_calllog);
                break;
            case GuideConst.CONTACT_PERMISSON:
                permissionText = getString(R.string.deny_permission_contact);
                break;
            case GuideConst.CALL_PERMISSION:
                permissionText = getString(R.string.deny_permission_call);
                break;
            default:
                break;
        }

        final TDialog dialog = new TDialog(this, TDialog.STYLE_BUTTON_ZERO);
        View view = LayoutInflater.from(this).inflate( R.layout.dlg_permission_denial,null);
        dialog.setContentView(view);
        TextView content = (TextView) view.findViewById(R.id.content);
        content.setText(Html.fromHtml(getString(R.string.permission_denial_dlg_content, permissionText) + permissionText));
        View confirm = view.findViewById(R.id.confirm);
        dialog.setTitle(R.string.permission_denial_dlg_title);
        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PermissionGuideGenerator.generateGuideStratagy(PermissionDenyActivity.this).clickPermissionDeny(type);
                dialog.dismiss();
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

            }
        });
}

}
