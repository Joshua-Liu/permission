package android.by.com.permission.permission;

import android.by.com.permission.R;
import android.by.com.permission.base.TPBaseActivity;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Html;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;


/**
 * Created by junhao on 6/26/15.
 */
public class MiuiPermissionGeneralGuide extends TPBaseActivity {

    public static final int CALLLOG_PERMISSION_GUIDE = 1;
    public static final int CONTACT_PERMISSION_GUIDE = 2;
    public static final int AUTO_START_PERMISSION_GUIDE = 3;

    public static final String EXTRA_PERMISSION_TYPE = "extra_permission_type";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View root = LayoutInflater.from(this).inflate(R.layout.scr_miui_permission_general_guide, null);
        setContentView(root);
        root.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        int permissionType = getIntent().getIntExtra(EXTRA_PERMISSION_TYPE, 0);
        if (permissionType == 0) {
            throw new IllegalArgumentException();
        }
        Drawable guidePic = null;
        String lineOneContent = "";
        CharSequence lineTwoContent = "";
        switch (permissionType) {
            case CALLLOG_PERMISSION_GUIDE:
                guidePic = getResources().getDrawable(R.drawable.miui_calllog_permission_guide_pic);
                lineOneContent = getString(R.string.miui_permission_guide_template_one, getString(R.string.miui_permission_calllog));
                lineTwoContent = Html.fromHtml(getString(R.string.miui_permission_action_set_allowed));
                break;
            case CONTACT_PERMISSION_GUIDE:
                guidePic = getResources().getDrawable(R.drawable.miui_contact_permission_guide_pic);
                lineOneContent = getString(R.string.miui_permission_guide_template_one, getString(R.string.miui_permission_contact));
                lineTwoContent = Html.fromHtml(getString(R.string.miui_permission_action_set_allowed));
                break;
            case AUTO_START_PERMISSION_GUIDE:
                guidePic = getResources().getDrawable(R.drawable.miui_auto_start_guide);
                lineOneContent = getString(R.string.miui_permission_guide_template_one, getString(R.string.app_name));
                lineTwoContent = Html.fromHtml(getString(R.string.miui_permission_action_switch_on));
                break;
            default:
                break;
        }
        TextView lineOneView = (TextView) findViewById(R.id.line_one);
        lineOneView.setText(lineOneContent);
        ImageView guidePicView = (ImageView)findViewById(R.id.guide_pic);
        DisplayMetrics metrics = getResources().getDisplayMetrics();
        int picWidth = (int)(metrics.widthPixels - 12 * metrics.density);
        int picHeight = picWidth * guidePic.getIntrinsicHeight() / guidePic.getIntrinsicWidth();
        guidePicView.getLayoutParams().height = picHeight;
        guidePicView.getLayoutParams().width = picWidth;
        guidePicView.setImageDrawable(guidePic);
        TextView lineTwoView = (TextView) findViewById(R.id.line_two);
        lineTwoView.setText(lineTwoContent);
        if (permissionType == AUTO_START_PERMISSION_GUIDE) {
            lineTwoView.setCompoundDrawables(null, null, null, null);
        }

    }
}
