package android.by.com.permission.permission;

import android.by.com.permission.R;
import android.by.com.permission.base.TPBaseActivity;
import android.by.com.permission.layout.FuncBarSecondaryView;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;



/**
 * Created by wanqing on 2015/6/30.
 */
public class VivoPermissionGeneralGuide extends TPBaseActivity {

    private FuncBarSecondaryView mfbView;
    private ImageView mImageView;
    public static final String VIVO_PERMISSION_TITLE = "vivo_permission_title";
    public static final String VIVO_PERMISSION_IMAGE_ID = "vivo_permission_image_id";
    public static final String VIVO_PACKAGE_NAME = "com.iqoo.secure";
    public static final String VIVO_GUIDE_ACTIVITY_NAME = "com.iqoo.secure.MainGuideActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        int picId = getIntent().getIntExtra(VIVO_PERMISSION_IMAGE_ID,-1);
        if (picId != -1) {
            View root = LayoutInflater.from(this).inflate(R.layout.scr_vivo_permission_general_guide, null);
            setContentView(root);
            mfbView = (FuncBarSecondaryView) findViewById(R.id.funcbar_secondary);
            String title = getIntent().getStringExtra(VIVO_PERMISSION_TITLE);
            mfbView.setTitleString(getResources().getString(R.string.vivo_permission_general_guide_title, title));
            mImageView = (ImageView)findViewById(R.id.guide_pic);
            mImageView.setImageDrawable(getResources().getDrawable(picId));
            View back = mfbView.findViewById(R.id.funcbar_back);
            back.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    finish();
                }
            });

            TextView btn = (TextView)findViewById(R.id.btn);
            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    try {
                        Intent intent = new Intent();
                        intent.setClassName(VIVO_PACKAGE_NAME, VIVO_GUIDE_ACTIVITY_NAME);
                        startActivity(intent);
                    } catch (ActivityNotFoundException e) {
                        e.printStackTrace();
                    }
                    finish();
                }
            });
        } else {
            try{
                Intent intent = new Intent();
                intent.setClassName(VIVO_PACKAGE_NAME, VIVO_GUIDE_ACTIVITY_NAME);
                startActivity(intent);
            }catch (ActivityNotFoundException e) {
                e.printStackTrace();
            }
            finish();
        }

    }
}
