package android.by.com.permission.layout;

import android.annotation.TargetApi;
import android.app.Dialog;
import android.by.com.permission.R;
import android.by.com.permission.constant.TtfConst;
import android.by.com.permission.permission.TouchPalTypeface;
import android.by.com.permission.util.DimentionUtil;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RoundRectShape;
import android.os.Build;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;


import java.util.ArrayList;

/**
 * Dialog with TouchPal style.
 * 
 * @author Nick Yang (chengxiao.yang@cootek.cn)
 */
public class TDialog extends Dialog {

    public static final int STYLE_BUTTON_ZERO = 0;
    public static final int STYLE_BUTTON_ONE = 1;
    public static final int STYLE_BUTTON_TWO = 2;

    private final int mStyle;
    private final ViewGroup mDialog;
    private final LinearLayout mBoard;
    private final TextView mBanner;
    private final TextView mClose;
    private final TextView mTitle;
    private final View mTitleContainer;
    private final TextView mNegativeBtn;
    private final TextView mPositiveBtn;
    private TextView mTitleClose;
    private View mContainer = null;
    private boolean mUseSkin = true;
    private int mTag1 = -1;
    private Context mContext;
    private static final String TAG = "TDialog";

    /**
     * Create a Dialog window that uses a TouchPal dialog style.
     * 
     * @param context
     *            It's better be an {@code Activity} context, or you need set
     *            window token manually.
//     * @param buttonSytle
     *            one of {@link TDialog#STYLE_BUTTON_ZERO}
     *            {@link TDialog#STYLE_BUTTON_ONE}
     *            {@link TDialog#STYLE_BUTTON_TWO}
//     * @param   if dialog should use resource in skin package
     */
    public TDialog(Context context, int buttonStyle) {
        this(context, buttonStyle, true);
    }

    /**
     * Create a Dialog window that uses a TouchPal dialog style and use skin resource as specified
     * @param context
     * @param buttonStyle
     * @param useSkin
     */
    public TDialog(Context context, int buttonStyle, boolean useSkin) {
        super(context, R.style.dlg_standard_theme);
        mContext = context;
        mUseSkin = useSkin;

        mDialog = (ViewGroup) LayoutInflater.from(context).inflate(R.layout.dlg_standard_frame, null);
        mBoard = (LinearLayout)mDialog.findViewById(R.id.board);
        mBoard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //block root view event
            }
        });
        mBanner = (TextView) mDialog.findViewById(R.id.banner);
        mClose = (TextView)mDialog.findViewById(R.id.banner_close);
        mTitleContainer = mDialog.findViewById(R.id.title_container);
        mTitle = (TextView) mDialog.findViewById(R.id.title);
        mTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //block root view event
            }
        });
        mTitleClose = (TextView) mDialog.findViewById(R.id.title_close);
        mTitleClose.setTypeface(TouchPalTypeface.ICON1_V6);
        mTitleClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        mStyle = buttonStyle;
        mNegativeBtn = (TextView) mDialog.findViewById(R.id.negativeBtn);
        mPositiveBtn = (TextView) mDialog.findViewById(R.id.positiveBtn);
        View bottomContainer = mDialog.findViewById(R.id.bottom);
        if(buttonStyle == STYLE_BUTTON_ZERO) {
            bottomContainer.setVisibility(View.GONE);
            mBoard.setPadding(0, 0, 0, DimentionUtil.getDimen(R.dimen.dlg_standard_layout_padding_style_zero_button));
        } else if (buttonStyle == STYLE_BUTTON_ONE) { //去除左边的按钮的背景、文字，这样对用户不可见;
            mNegativeBtn.setVisibility(View.GONE);
            bottomContainer.findViewById(R.id.mid_empty).setVisibility(View.GONE);
        }

        this.setCanceledOnTouchOutside(buttonStyle == STYLE_BUTTON_ZERO);
    }

    @Override
    public void setCancelable(boolean flag) {
        super.setCancelable(flag);
    }

    @Override
    public void setCanceledOnTouchOutside(boolean cancel) {
        if (cancel) {
            super.setCancelable(true);
            mDialog.setOnClickListener(new View.OnClickListener() {
                
                @Override
                public void onClick(View v) {
                    TDialog.this.onBackPressed();
                }
            });
        } else {
            mDialog.setOnClickListener(null);
        }
        
    }

    public TDialog setCancelOnTouchOutside(boolean cancel) {
        setCanceledOnTouchOutside(cancel);
        return this;
    }

    @Override
    public void dismiss() {
        try {
            super.dismiss();
        } catch (IllegalArgumentException e) {
            // dialog has been deleted.
        } finally {
            sManagedDialogs.remove(this);
        }
    }

    @Override
    public void setContentView(int contentViewID) {
        View contentView = null;

        contentView = LayoutInflater.from(getContext()).inflate(contentViewID, null);
        /* bug#31995 is caused by this method call. The method will override the padding setting
           in xml with background drawable's padding. It's recommended to set content views' background
           in xml files.                             
        contentView.setBackgroundResource(R.drawable.dlg_standard_board_bg);*/
        this.setContentView(contentView);
    }

    @Override
    public void setContentView(View contentView) {
        View dialogView = initialDialogView(contentView, null);
        super.setContentView(dialogView);
        this.getWindow().setLayout(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
    }

    @Override
    public void setContentView(View contentView, LayoutParams params) {
        View dialogView = initialDialogView(contentView, params);
        super.setContentView(dialogView);
        this.getWindow().setLayout(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
    }

    private View initialDialogView(View contentView, LayoutParams params) {
        if (mContainer != null) {
            mDialog.removeView(mContainer);
        }

        if (contentView != null) {
            TDialogLayout layout = (TDialogLayout) mDialog;
            layout.addContainer(contentView, params);
            mContainer = contentView;
        }
        setTranslucentStatusBar();
        return mDialog;
    }

    private void setTranslucentStatusBar() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
    }
    
    /**
     * used to hide the dialog title
     */
    public void hideTitle() {
    	mTitleContainer.setVisibility(View.GONE);
    }

    @Override
    public void setTitle(CharSequence title) {
        mTitle.setText(title);
    }

    @Override
    public void setTitle(int titleId) {
        mTitle.setText(titleId);
    }

    /**
     * Set the title icon for this dialog's window.
     * 
     * @param icon
     *            the new icon to display in the title.
     */
    public void setTitleIcon(Drawable icon) {
        mTitle.setCompoundDrawablesWithIntrinsicBounds(icon, null, null, null);
    }

    /**
     * Set the title icon for this dialog's window. The icon is retrieved from
     * the resources with the supplied identifier.
     * 
     * @param iconId
     *            the title's icon resource identifier
     */
    public void setTitleIcon(int iconId) {
        this.setTitleIcon(mContext.getResources().getDrawable(iconId));
    }

    /**
     * @return the content view you added earlier.
     */
    public View getContainer() {
        return mContainer;
    }

    /**
     * Set the enabled state of negative button.<br />
     * It's effective only if {@link TDialog#STYLE_BUTTON_TWO} has been set.
     * 
     * @param enabled
     */
    public void setNegativeBtnEnable(boolean enabled) {
        if (mStyle == STYLE_BUTTON_TWO) {
            mNegativeBtn.setEnabled(enabled);
        } else {
            throw new UnsupportedOperationException("Make sure you've set a valid dialog style.");
        }
    }
    
    /**
     * Returns the enabled status for negative button.
     * 
     * @return True if this view is enabled, false otherwise.
     */
    public boolean isNegativeBtnEnable() {
        if (mStyle == STYLE_BUTTON_TWO) {
            return mNegativeBtn.isEnabled();
        } else {
            throw new UnsupportedOperationException("Make sure you've set a valid dialog style.");
        }
    }

    /**
     * Set the text for negative button.<br />
     * It's effective only if {@link TDialog#STYLE_BUTTON_TWO} has been set.
     * 
     * @param text
     *            the new text to display on the button.
     */
    public TDialog setNegativeBtnText(CharSequence text) {
        if (mStyle == STYLE_BUTTON_TWO) {
            mNegativeBtn.setText(text);
        } else {
            throw new UnsupportedOperationException("Make sure you've set a valid dialog style.");
        }
        return this;
    }

    /**
     * Set the text for negative button.<br />
     * It's effective only if {@link TDialog#STYLE_BUTTON_TWO} has been set.
     * 
     * @param textId
     *            the text's resource identifier
     */
    public TDialog setNegativeBtnText(int textId) {
        if (mStyle == STYLE_BUTTON_TWO) {
            mNegativeBtn.setText(textId);
        } else {
            throw new UnsupportedOperationException("Make sure you've set a valid dialog style.");
        }
        return this;
    }
    
    /**
     * Set the background for negative button.<br />
     * It's effective only if {@link TDialog#STYLE_BUTTON_TWO} has been set.
     * 
     * @param resId
     *            the background's resource identifier
     */
    public void setNegativeBtnBackground(int resId) {
    	if(mStyle == STYLE_BUTTON_ZERO){
    		mNegativeBtn.setBackgroundDrawable(mContext.getResources().getDrawable(resId));
    	} else {
    		throw new UnsupportedOperationException("Make sure you've set a valid dialog style.");
    	}
    }
    
    /**
     * Set the text color for negative button.<br />
     * It's effective only if {@link TDialog#STYLE_BUTTON_TWO} has been set.
     * 
     * @param color
     *            the negative button text color
     */
    public void setNegativeBtnTextColor(int color) {
    	if(mStyle == STYLE_BUTTON_ZERO){
    		mNegativeBtn.setTextColor(color);
    	} else {
    		throw new UnsupportedOperationException("Make sure you've set a valid dialog style.");
    	}
    }

    /**
     * Register a callback to be invoked when negative button is clicked.<br />
     * It's effective only if {@link TDialog#STYLE_BUTTON_TWO} has been set.
     * 
     * @param l
     *            the callback that will run
     */
    public void setOnNegativeBtnClickListener(View.OnClickListener l) {
        if (mStyle == STYLE_BUTTON_TWO) {
            mNegativeBtn.setOnClickListener(l);
        } else {
            throw new UnsupportedOperationException("Make sure you've set a valid dialog style.");
        }
    }

    /**
     * Set the enabled state of positive button.<br />
     * It's effective only if the button style isn't
     * {@link TDialog#STYLE_BUTTON_ZERO}.
     * 
     * @param enabled
     */
    public void setPositiveBtnEnable(boolean enabled) {
        if (mStyle != STYLE_BUTTON_ZERO) {
            mPositiveBtn.setEnabled(enabled);
        } else {
            throw new UnsupportedOperationException("Make sure you've set a valid dialog style.");
        }
    }
    
    /**
     * Returns the enabled status for positive button.
     * 
     * @return True if this view is enabled, false otherwise.
     */
    public boolean isPositiveBtnEnable() {
        if (mStyle != STYLE_BUTTON_ZERO) {
            return mPositiveBtn.isEnabled();
        } else {
            throw new UnsupportedOperationException("Make sure you've set a valid dialog style.");
        }
    }

    /**
     * Set the text for positive button.<br />
     * It's effective only if the button style isn't
     * {@link TDialog#STYLE_BUTTON_ZERO}.
     * 
     * @param text
     *            the new text to display on the button.
     */
    public TDialog setPositiveBtnText(CharSequence text) {
        if (mStyle != STYLE_BUTTON_ZERO) {
            mPositiveBtn.setText(text);
        } else {
            throw new UnsupportedOperationException("Make sure you've set a valid dialog style.");
        }
        return this;
    }

    /**
     * Set the text for positive button.<br />
     * It's effective only if the button style isn't
     * {@link TDialog#STYLE_BUTTON_ZERO}.
     * 
     * @param textId
     *            the text's resource identifier
     */
    public TDialog setPositiveBtnText(int textId) {
        if (mStyle != STYLE_BUTTON_ZERO) {
            mPositiveBtn.setText(textId);
        } else {
            throw new UnsupportedOperationException("Make sure you've set a valid dialog style.");
        }
        return this;
    }

    /**
     * Set the text size for positive button.<br />
     * It's effective only if the button style isn't
     * {@link TDialog#STYLE_BUTTON_ZERO}.
     *
     * @param size
     *            the text size in pixel
     */
    public void setPositiveBtnTextSize(float size) {
        if (mStyle != STYLE_BUTTON_ZERO) {
            mPositiveBtn.setTextSize(TypedValue.COMPLEX_UNIT_PX, size);
        } else {
            throw new UnsupportedOperationException("Make sure you've set a valid dialog style.");
        }
    }
    /**
     * Set the text size for negative button.<br />
     * It's effective only if the button style isn't
     * {@link TDialog#STYLE_BUTTON_ZERO}.
     *
     * @param size
     *            the text size in pixel
     */
    public void setNegativeBtnTextSize(float size) {
        if (mStyle != STYLE_BUTTON_ZERO) {
            mNegativeBtn.setTextSize(TypedValue.COMPLEX_UNIT_PX, size);
        } else {
            throw new UnsupportedOperationException("Make sure you've set a valid dialog style.");
        }
    }
    /**
     * Set the background for positive button.<br />
     * It's effective only if the button style isn't
     * {@link TDialog#STYLE_BUTTON_ZERO}.
     * 
     * @param resId
     *            the background's resource identifier
     */
    public void setPositiveBtnBackground(int resId) {
    	if(mStyle != STYLE_BUTTON_ZERO){
    		mPositiveBtn.setBackgroundDrawable(mContext.getResources().getDrawable(resId));
    	} else {
    		throw new UnsupportedOperationException("Make sure you've set a valid dialog style.");
    	}
    }
    
    /**
     * Set the text color for positive button.<br />
     * It's effective only if the button style isn't
     * {@link TDialog#STYLE_BUTTON_ZERO}.
     * 
     * @param color
     *            the positive button text color
     */
    public void setPositiveBtnTextColor(int color) {
    	if(mStyle != STYLE_BUTTON_ZERO){
    		mPositiveBtn.setTextColor(color);
    	} else {
    		throw new UnsupportedOperationException("Make sure you've set a valid dialog style.");
    	}
    }

    /**
     * Register a callback to be invoked when positive button is clicked.<br />
     * It's effective only if the button style isn't
     * {@link TDialog#STYLE_BUTTON_ZERO}.
     * 
     * @param l
     *            the callback that will run
     */
    public TDialog setOnPositiveBtnClickListener(View.OnClickListener l) {
        if (mStyle != STYLE_BUTTON_ZERO) {
            mPositiveBtn.setOnClickListener(l);
        } else {
            throw new UnsupportedOperationException("Make sure you've set a valid dialog style.");
        }
        return this;
    }

    /**
     * {@inheritDoc}
     * <br/><br/>
     * WARNING: You should call {@code setContentView} with a view first.
     */
    @TargetApi(Build.VERSION_CODES.CUPCAKE)
    public void show() {
        if (mContainer == null) {
            throw new UnsupportedOperationException("You should call setContentView with a view first.");
        }
        try {
            this.getWindow().getAttributes().windowAnimations = 0;
            this.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
            this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
            this.getWindow().setWindowAnimations(R.style.DialogAnimation);
            super.show();
            sManagedDialogs.add(this);
        } catch (Exception e) {
            //  Runtime or Badtoken, ignore
            Log.w(TAG, e.getMessage());
        }
    }

    /**
     * Get a TouchPal style dialog view with SkinManager.
     * 
     * @param context
     * @param contentView
     * @param params
     * @return
     */
    public static TDialogLayout inflate(Context context, View contentView, LayoutParams params) {
        TDialogLayout layout = (TDialogLayout) LayoutInflater.from(context).inflate(R.layout.dlg_standard_frame,null);
        layout.addContainer(contentView, params);
        return layout;
    }

    /**
     * Get a TouchPal style dialog view with SkinManager
     * 
     * @param context
     * @param contentView
     * @return
     */
    public static TDialogLayout inflate(Context context, View contentView) {
        return TDialog.inflate(context, contentView, null);
    }

    /**
     * Get a TouchPal style dialog view with SkinManager
     * 
     * @param context
     * @param contentViewId
     * @return
     */
    public static TDialogLayout inflate(Context context, int contentViewId) {
        return TDialog.inflate(context, LayoutInflater.from(context).inflate(contentViewId,null));
    }

    /**
     * Get a TouchPal style dialog view whether with SkinManager
     *
     * @param context
     * @param contentViewId
     * @return
     */
    public static TDialogLayout inflate(Context context, int contentViewId, boolean isSkined) {
            return TDialog.inflate(context, LayoutInflater.from(context).inflate(contentViewId , null));
    }
    
    /**
     * Get a default dialog with a single TextView.
     * 
     * @param context
//     * @param buttonSytle
     *            one of {@link TDialog#STYLE_BUTTON_ZERO}
     *            {@link TDialog#STYLE_BUTTON_ONE}
     *            {@link TDialog#STYLE_BUTTON_TWO}
     * @param title
     *            text will be set on title.
     * @param message
     *            text will be showed on TextView.
     * @return
     */
    public static TDialog getDefaultDialog(Context context, int buttonStyle, String title, CharSequence message) {
        TDialog dialog = new TDialog(context, buttonStyle);
        dialog.setContentView(LayoutInflater.from(context).inflate(R.layout.dlg_standard_container,null));
        dialog.setTitle(title);
        TextView msg = (TextView) dialog.getContainer().findViewById(R.id.msg);
        msg.setText(message);
        return dialog;
    }

    /**
     * Get a default dialog with a single TextView.
     * 
     * @param context
     * @param buttonStyle
     *            one of {@link TDialog#STYLE_BUTTON_ZERO}
     *            {@link TDialog#STYLE_BUTTON_ONE}
     *            {@link TDialog#STYLE_BUTTON_TWO}
     * @param titleId
     *            text's resource identifier which will be set on title.
     * @param messageId
     *            text's resource identifier which will be showed on TextView.
     * @return
     */
    public static TDialog getDefaultDialog(Context context, int buttonStyle, int titleId, int messageId) {
        return TDialog.getDefaultDialog(context, buttonStyle, context.getString(titleId), context.getString(messageId));
    }

    public static TDialog getDefaultDialog(Context context, int buttonStyle, int titleId, String message) {
        return TDialog.getDefaultDialog(context, buttonStyle, context.getString(titleId), message);
    }

    /* Dialog management */
    private static ArrayList<TDialog> sManagedDialogs = new ArrayList<TDialog>();

    public static void closeManagedDialogs() {
        if (sManagedDialogs != null && sManagedDialogs.size() > 0) {
            for (int index = sManagedDialogs.size() - 1; index >= 0; index--) {
                sManagedDialogs.get(index).dismiss();
            }
        }
    }
    
    public void setTitleVisible(boolean isVisible) {
        mTitle.setVisibility(isVisible ? View.VISIBLE : View.GONE);
    }

    public void setBanner(String content, int color){
        float r = DimentionUtil.getDimen(R.dimen.dlg_standard_layout_corner);
        float[] bannerRadius = new float[]{r,r,r,r,0,0,0,0};
        RoundRectShape forBanner = new RoundRectShape(bannerRadius,null,null);
        ShapeDrawable bannerShape = new ShapeDrawable(forBanner);
        bannerShape.getPaint().setColor(color);
        mBanner.setText(content);
        mBanner.setBackgroundDrawable(bannerShape);

        mClose.setTypeface(TouchPalTypeface.ICON2);
        mClose.setText(TtfConst.ICON2_CLOSE);

        mBanner.setVisibility(View.VISIBLE);
        mClose.setVisibility(View.VISIBLE);
    }

    public void changeBoard(Drawable drawable){
        mBoard.setBackgroundDrawable(drawable);
    }

    public void changeBoard(int resid){
        mBoard.setBackgroundResource(resid);
    }

    public void changeBoardColor(int color){
        mBoard.setBackgroundColor(color);
    }

    public TDialog setTitleCloseVisible(boolean visible) {
        mTitleClose.setVisibility(visible ? View.VISIBLE : View.GONE);
        return this;
    }

    public void changeBoardMargin(int left, int top, int right ,int bottom){
        LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams)mBoard.getLayoutParams();
        lp.leftMargin = left;
        lp.topMargin = top;
        lp.rightMargin = right;
        lp.bottomMargin = bottom;
        mBoard.setLayoutParams(lp);
    }

    public void setRootClickListener(View.OnClickListener l) {
        mDialog.setOnClickListener(l);
    }

    public void setTag1(int tag) {
        mTag1 = tag;
    }

    public int getTag1() {
        return mTag1;
    }

//    public TDialog setDelayDismiss(long dismissAfterDelayInMills, long interval) {
//        ((ViewStub) findViewById(R.id.count_down_timer)).inflate();
//        SkipViewCircle circle = (SkipViewCircle) findViewById(R.id.circle);
//		circle.setTextSize(getContext().getResources().getDimensionPixelSize(R.dimen.skip_text_size));
//		circle.setStrokeWidth(getContext().getResources().getDimensionPixelSize(R.dimen.dialog_skip_stroke_size));
//        new DelayDismissTimer(dismissAfterDelayInMills, interval, circle).start();
//        return this;
//    }

//    private class DelayDismissTimer extends CountDownTimer {
//
//        private long mDuration;
//        private long mlastV;
//        private BaseView mCountView;
//
//        public DelayDismissTimer(long millisInFuture, long countDownInterval, BaseView countView) {
//            super(millisInFuture, countDownInterval);
//            mDuration = millisInFuture;
//            mlastV = -1;
//            mCountView = countView;
//        }
//
//        @Override
//        public void onTick(long millisUntilFinished) {
//            float progress = millisUntilFinished / (float) mDuration;
//            long m = (millisUntilFinished - 1) / 1300 + 1;
//            if(m != mlastV && m > 0) {
//                mCountView.setText(String.valueOf(m));
//                mlastV = m;
//            }
//            mCountView.setProgress(progress,mDuration);
//        }
//
//        @Override
//        public void onFinish() {
//            mCountView.setProgress(0, mDuration);
//            dismiss();
//            cancel();
//        }
//    }
}
