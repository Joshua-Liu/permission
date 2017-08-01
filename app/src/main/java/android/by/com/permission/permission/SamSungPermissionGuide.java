package android.by.com.permission.permission;
import android.annotation.SuppressLint;
import android.by.com.permission.R;
import android.by.com.permission.base.TPBaseActivity;
import android.by.com.permission.pref.PrefKeys;
import android.by.com.permission.util.PrefUtil;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.RelativeLayout;
import android.widget.TextView;




public class SamSungPermissionGuide extends TPBaseActivity {

	public static final int TYPE_DEFAULT = 0;
	public static final int TYPE_RECOGNIZE = 1;
	public static final int TYPE_TAKEOVER = 2;

	public static final int MODEL_DEFAULT = -1;
	public static final int MODEL_S6 = 1;
	public static final int MODEL_NOTE_4 = 2;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
        setContentView(R.layout.dlg_s6_guide);
        DisplayMetrics metric = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metric);
        if (metric.widthPixels <= 480) {
            View dlg = findViewById(R.id.dlg);
            RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(
					LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
            params.setMargins(20, 0, 20, 0);
            params.addRule(RelativeLayout.CENTER_IN_PARENT);
            dlg.setLayoutParams(params);
        }
		TextView close = (TextView) findViewById(R.id.close);
        close.setTypeface(TouchPalTypeface.ICON2);
		close.setOnClickListener(mClickListener);
		View confirm = findViewById(R.id.confirm);
		confirm.setOnClickListener(mClickListener);
	}
	
	private OnClickListener mClickListener = new OnClickListener() {
		
		@SuppressLint("InlinedApi")
        @Override
		public void onClick(View v) {
			switch (v.getId()) {
			case R.id.confirm:
                PrefUtil.setKey(PrefKeys.SAMSUNG_TOAST_SHOW_TYPE, TYPE_DEFAULT);
                PrefUtil.setKey(PrefKeys.SAMSUNG_TOAST_HAS_SHOW, true);
				Intent intent = new Intent();
                intent.setClassName("com.samsung.android.sm", "com.samsung.android.sm.app.dashboard.SmartManagerDashBoardActivity");
				try {
                    startActivity(intent);
                } catch (Exception e) {}
				finish();
			case R.id.close:
                PrefUtil.setKey(PrefKeys.SAMSUNG_TOAST_SHOW_TYPE, TYPE_DEFAULT);
                PrefUtil.setKey(PrefKeys.SAMSUNG_TOAST_HAS_SHOW, true);
				finish();
				break;
			}
		}
	};
	
	@Override
	protected void onStop() {
		super.onStop();
	};

	@Override
	public void onBackPressed() {
		finish();
	}
	
}
