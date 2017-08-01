package android.by.com.permission.permission;

import android.by.com.permission.R;
import android.by.com.permission.base.TPBaseActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.TextView;



/**
 * Created by wanqing on 2015/6/24.
 */
public class MiuiGuide extends TPBaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.miui_dial_sercurity_guide);

        TextView content = (TextView) findViewById(R.id.content);
        content.setText(Html.fromHtml(getString(R.string.miui_dial_sercurity_guide_content)));
        findViewById(R.id.guide).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

}