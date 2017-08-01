package android.by.com.permission.layout;

import android.annotation.SuppressLint;
import android.by.com.permission.R;
import android.by.com.permission.constant.TtfConst;
import android.by.com.permission.permission.TouchPalTypeface;
import android.by.com.permission.util.DimentionUtil;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;



public class FuncBarSecondaryView extends LinearLayout {
    private String mTitleString = "";
    private int mTitleSize = 0;
    private int mTitleIcon = 0;
    private String mRightString = "";
    private String mRightIcon = "";
    private String mRightIconTf = "";
    private int mTitleColor = 0;
    private Typeface mBackTypeface = null;
    private float mRightSize = 0;
    private View mRoot;

    private TextView mBackBtn;
    private TextView mTitle;
    private ImageView mTitleImage;
    private View mTitleImageLayout;
    private TextView mRightBtn;
    private TextView mIcon;
    private boolean mSkinable;
    private ColorStateList mIconColor;


    public FuncBarSecondaryView(Context context) {
        super(context);
        initAttr(null, 0);
        initView(context);
    }

    public FuncBarSecondaryView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initAttr(attrs, 0);
        initView(context);
    }

    @SuppressLint("NewApi")
    public FuncBarSecondaryView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initAttr(attrs, defStyle);
        initView(context);
    }


    private void initAttr(AttributeSet attrs, int defStyle) {
        final TypedArray a = getContext().obtainStyledAttributes(
                attrs, R.styleable.FuncBarSecondaryView, defStyle, 0);

        mSkinable = a.getBoolean(R.styleable.FuncBarSecondaryView_funcbarSkinable, true);

        mTitleString = a.getString(
                R.styleable.FuncBarSecondaryView_funcbarTitleText);
        mTitleColor = a.getColor(
                R.styleable.FuncBarSecondaryView_funcbarTitleColor,
                mTitleColor);
        mTitleSize = a.getDimensionPixelSize(R.styleable.FuncBarSecondaryView_funcbarTitleSize, mTitleSize);

        mTitleIcon = a.getResourceId(R.styleable.FuncBarSecondaryView_funcbarTitleIcon, mTitleIcon);

        mIconColor = a.getColorStateList(R.styleable.FuncBarSecondaryView_funcbarIconColor);

        mRightString = a.getString(
                R.styleable.FuncBarSecondaryView_funcbarRightText);
        mRightIcon = a.getString(
                R.styleable.FuncBarSecondaryView_funcbarRightIcon);
        mRightIconTf = a.getString(
                R.styleable.FuncBarSecondaryView_funcbarRightIconTf);
        int iconSize = DimentionUtil.getTextSize(R.dimen.funcbar_icon_text_size);
        int defaultRightSize = !TextUtils.isEmpty(mRightIconTf) ? iconSize : (int) mRightSize;
        mRightSize = a.getDimensionPixelSize(
                R.styleable.FuncBarSecondaryView_funcbarRightSize,
                defaultRightSize);


        a.recycle();

        Log.e("custom func","style title: "+ mTitleString +" color: " + mTitleColor
                + " right: "  + mRightString  + " size: " + mRightSize);
    }

    private void initView(Context context) {
        Log.e("custom func", "init");
        mRoot = mSkinable ? LayoutInflater.from(context).inflate( R.layout.scr_secondary_funcbar,null)
                : LayoutInflater.from(context).inflate(R.layout.scr_secondary_funcbar, null);
        mBackBtn = (TextView) mRoot.findViewById(R.id.funcbar_back);
        mBackBtn.setTypeface(TouchPalTypeface.ICON1_V6);
        mBackBtn.setText(TtfConst.ICON1_V6_BACK);

        mTitle = (TextView) mRoot.findViewById(R.id.funcbar_title);
        mTitleImage = (ImageView) mRoot.findViewById(R.id.funcbar_title_icon);
        mTitleImageLayout = mRoot.findViewById(R.id.funcbar_title_icon_layout);
        mTitle.setText(mTitleString != null ? mTitleString : "");

        if (mTitleColor != 0) {
            mTitle.setTextColor(mTitleColor);
        }

        if (mTitleIcon != 0) {
            mTitle.setVisibility(GONE);
            mTitleImageLayout.setVisibility(VISIBLE);
            mTitleImage.setBackgroundResource(mTitleIcon);
        }

        mRightBtn = (TextView) mRoot.findViewById(R.id.funcbar_right);
        float scale = getContext().getResources().getDisplayMetrics().density;

        if (mTitleSize != 0) {
            mTitle.setTextSize(mTitleSize / scale + 0.5f);
        }

        if (!TextUtils.isEmpty(mRightString)) {
            mRightBtn.setText(mRightString);
            mRightBtn.setVisibility(View.VISIBLE);
            if (mRightSize > 0) {
                mRightBtn.setTextSize(mRightSize / scale + 0.5f);
            }
        } else if (!TextUtils.isEmpty(mRightIcon)) {
            if (!TextUtils.isEmpty(mRightIconTf)) {
                mRightBtn.setTypeface(TouchPalTypeface.getTypefacWithName(mRightIconTf));
            }

            mRightBtn.setText(mRightIcon);
            mRightBtn.setTextSize(mRightSize / scale + 0.5f);
            float iconBtnWidth = getContext().getResources().getDimension(R.dimen.funcbar_rightside_btn_width);
            mRightBtn.setWidth((int) iconBtnWidth);
            mRightBtn.setVisibility(View.VISIBLE);

            if (mRightSize > 0) {
                mRightBtn.setTextSize(mRightSize / scale + 0.5f);
            }
        } else {
            mRightBtn.setVisibility(View.GONE);
        }

        mIcon = (TextView) mRoot.findViewById(R.id.funcbar_icon);

        if (getBackground() != null) {
            mRoot.setBackgroundDrawable(getBackground());
        }
        addView(mRoot, LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);

        if (mIconColor != null) {
            mBackBtn.setTextColor(mIconColor);
            mIcon.setTextColor(mIconColor);
            mRightBtn.setTextColor(mIconColor);
        }

        Log.e("custom func","title: "+ mTitleString +" color: " + mTitleColor
                + " right: "  + mRightString  + " size: " + mRightSize);
    }


    public void setTitleString(String title) {
        mTitle.setText(title);
    }

    public void setTitleString(int titleRes) {
        mTitle.setText(titleRes);
    }

    public void setTitleColor(int color) {
        mTitle.setTextColor(color);
    }

    public void setTitleLeftPadding(int paddingLeft) {
        mTitle.setPadding(paddingLeft, mTitle.getPaddingTop(), mTitle.getPaddingRight(), mTitle.getPaddingBottom());
    }

    public void setRightBtnString(String right) {
        if (!TextUtils.isEmpty(right)) {
            mRightBtn.setVisibility(VISIBLE);
            mRightBtn.setText(right);
            int padding_left = getResources().getDimensionPixelSize(R.dimen.funcbar_sidetxt_padding);
            int padding_right = getResources().getDimensionPixelSize(R.dimen.funcbar_sidetxt_padding);
            mRightBtn.setPadding(padding_left,0,padding_right,0);
        }
    }

    public void setRightBtnNoneDividerBG() {
        int padding_left = getResources().getDimensionPixelSize(R.dimen.funcbar_sidetxt_padding);
        int padding_right = getResources().getDimensionPixelSize(R.dimen.funcbar_sidetxt_padding);
        mRightBtn.setPadding(padding_left,0,padding_right,0);
    }


    public void setRightBtnIcon(Typeface tf, String right, float size) {
        if (!TextUtils.isEmpty(right)) {
            mRightBtn.setTypeface(tf);
            mRightBtn.setVisibility(VISIBLE);
            mRightBtn.setText(right);
            float scale = getContext().getResources().getDisplayMetrics().density;
            mRightBtn.setTextSize(size / scale + 0.5f);
            float iconBtnWidth = getContext().getResources().getDimension(R.dimen.funcbar_rightside_btn_width);
            mRightBtn.setWidth((int) iconBtnWidth);
        }
    }

    public void setRightColor(int color) {
        mRightBtn.setTextColor(color);
    }

    public void setTextIcon(Typeface tf, String icon, int color) {
        mIcon.setTypeface(tf);
        mIcon.setText(icon);
        mIcon.setTextColor(color);
    }

    public void setFuncBarBG(Drawable bg) {
        mRoot.setBackgroundDrawable(bg);
    }

    public void setFuncBarBG(int color) {
        mRoot.setBackgroundColor(color);
    }

}
