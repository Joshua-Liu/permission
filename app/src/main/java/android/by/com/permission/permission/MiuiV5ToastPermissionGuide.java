package android.by.com.permission.permission;

import android.by.com.permission.R;
import android.by.com.permission.base.TPBaseActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;



/**
 * Created by hercule on 2015/6/27.
 */
public class MiuiV5ToastPermissionGuide extends TPBaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View root = LayoutInflater.from(this).inflate(R.layout.scr_miui_permission_toast_guide, null);
        setContentView(root);
        root.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        TextView guide = (TextView) findViewById(R.id.guide_text);
        guide.setText(Html.fromHtml(getString(R.string.miui_permission_action_switch_on)));
    }
}
