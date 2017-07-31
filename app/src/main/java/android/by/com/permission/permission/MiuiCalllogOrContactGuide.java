package android.by.com.permission.permission;

import android.by.com.permission.R;
import android.by.com.permission.base.TSkinActivity;
import android.by.com.permission.util.DimentionUtil;
import android.by.com.permission.util.OSUtil;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;



public class MiuiCalllogOrContactGuide extends TSkinActivity implements View.OnClickListener{


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (OSUtil.isMiuiV6() || OSUtil.isMiuiV7() || OSUtil.isMiuiV8()) {
            setContentView(R.layout.miui_v6_guide);
            if (Build.MODEL.startsWith("MI 3") || Build.MODEL.startsWith("MI NOTE") || Build.MODEL.startsWith("MI 4")) {
                View leftView = (View)findViewById(R.id.leftview);
                View rightbottomView = (View)findViewById(R.id.rightbottom);
                View highlight = (View)findViewById(R.id.highlight);
                leftView.getLayoutParams().width = DimentionUtil.getDimen(R.dimen.xiaomi_left_width);
                rightbottomView.getLayoutParams().width = DimentionUtil.getDimen(R.dimen.xiaomi_high_light);
                ((RelativeLayout.LayoutParams)rightbottomView.getLayoutParams()).rightMargin = DimentionUtil.getDimen(R.dimen.xiaomi_left_width);
                highlight.getLayoutParams().width = DimentionUtil.getDimen(R.dimen.xiaomi_high_light);
                ((RelativeLayout.LayoutParams)highlight.getLayoutParams()).rightMargin = DimentionUtil.getDimen(R.dimen.xiaomi_left_width);
            }
        } else if (OSUtil.isMiuiV5()) {
            setContentView(R.layout.miui_v5_guide);
        }

        Button confirm = (Button)findViewById(R.id.confirm);
        View highlight = (View)findViewById(R.id.highlight);
        confirm.setOnClickListener(this);
        highlight.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.confirm:
                finish();
            case R.id.highlight:
                finish();
        }
    }
}
