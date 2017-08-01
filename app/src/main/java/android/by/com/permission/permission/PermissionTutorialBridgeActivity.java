package android.by.com.permission.permission;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

/**
 * Created by herculewang on 16/4/25.
 */
public class PermissionTutorialBridgeActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = new Intent(this, SpecificPermissionActivity.class);
        intent.putExtra(PermissionGuideActivity.PERMISSION_LIST_TYPE, IPermissionGuideStrategy.TUTORIAL_TYPE);
        startActivity(intent);
        finish();
    }
}
