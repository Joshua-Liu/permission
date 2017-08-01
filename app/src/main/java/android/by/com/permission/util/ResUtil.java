package android.by.com.permission.util;

import android.by.com.permission.MyApplication;
import android.by.com.permission.model.ModelManager;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.StringRes;
import android.text.TextUtils;
import android.util.Base64;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;



import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Method;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.TreeMap;
import java.util.UUID;
import java.util.regex.Pattern;

public class ResUtil {
    /**
     * layout类型
     */
    public static final String LAYOUT = "layout";

    /**
     * id类型
     */
    public static final String ID = "id";

    /**
     * drawable类型
     */
    public static final String DRAWABLE = "drawable";

    /**
     * string类型
     */
    public static final String STRING = "string";

    /**
     * color类型
     */
    public static final String COLOR = "color";

    /**
     * style类型
     */
    public static final String STYLE = "style";

    /**
     * array类型
     */
    public static final String ARRAY = "array";

    /**
     * dimen类型
     */
    public static final String DIMEN = "dimen";

    /**
     * raw类型
     */
    public static final String RAW = "raw";


    /**
     *
     * 利用反射机制获取资源id
     *
     * @param context Context
     * @param name id名称
     * @param type 资源类型
     * @return 资源id
     * @author wujian
     */
    public static int getResouceId(Context context, String name, String type)
    {
        return context.getResources().getIdentifier(name,
                type,
                context.getPackageName());
    }

    public static int getStringId(Context context, String name)
    {
        return getResouceId(context,
                name,
                ResUtil.STRING);
    }

    public static int getTypeId(Context context, String name)
    {
        return getResouceId(context,
                name,
                ResUtil.ID);
    }

    public static int getColorId(Context context, String name)
    {
        return getResouceId(context,
                name,
                ResUtil.COLOR);
    }

    public static int getDrawableId(Context context, String name)
    {
        return getResouceId(context,
                name,
                ResUtil.DRAWABLE);
    }

    public static int getLayoutId(Context context, String name)
    {
        return getResouceId(context,
                name,
                ResUtil.LAYOUT);
    }

    public static int getDimenId(Context context, String name)
    {
        return getResouceId(context,
                name,
                ResUtil.DIMEN);
    }
    /**
     *
     * 获取字符串资源
     *
     * @param context Context
     * @param name 资源名称
     * @return 字符串资源
     * @author wujian
     */
    public static String getString(Context context, String name)
    {
        int id = getResouceId(context,
                name,
                ResUtil.STRING);
        return context.getString(id);
    }

    public static String getString(@StringRes int id) {
        return MyApplication.getAppContext().getString(id);
    }

//
//    public static String getIconAbsolutePathIfExists(String iconLink) {
//        String path = getIconAbsolutePath(iconLink);
//        File file = new File(path);
//        if (file.exists() && file.isFile()) {
//            return path;
//        } else {
//            return null;
//        }
//    }
//
//    public static String getIconAbsolutePath(String iconLink) {
//        int idx = iconLink.indexOf("res/image");
//        if (idx > -1) {
//            return WebSearchLocalAssistant.getWebpagesPath() + File.separator + iconLink.substring(idx);
//        } else {
//            String name = Base64.encodeToString(iconLink.getBytes(),0);
//            String res = WebSearchLocalAssistant.getWebpagesPath() + File.separator + "res/image" + File.separator + name;
//            return res;
//        }
//    }
//
//    // if file not exist, return null;
//    public static Drawable getLocalDrawable(Context context, String path) {
//        String filePath = WebSearchLocalAssistant.getWebpagesPath() + path;
//        File file = new File(filePath);
//        if (!file.exists() || !file.isFile()) {
//            return null;
//        }
//        BitmapDrawable bmpDraw = new BitmapDrawable(context.getResources(), filePath);
//        if (bmpDraw == null) {
//            return null;
//        }
//        bmpDraw.setBounds(0, 0, bmpDraw.getIntrinsicWidth(), bmpDraw.getIntrinsicHeight());
//        return bmpDraw;
//    }
//
//    // if file not exist, return null;
//    public static Drawable getLocalDrawable(Context context, String path, float scaleSize) {
//        String filePath = WebSearchLocalAssistant.getWebpagesPath() + path;
//        File file = new File(filePath);
//        if (!file.exists() || !file.isFile()) {
//            return null;
//        }
//        BitmapDrawable bmpDraw = new BitmapDrawable(context.getResources(), filePath);
//        if (bmpDraw == null) {
//            return null;
//        }
//        int w = (int) (bmpDraw.getIntrinsicWidth() * scaleSize);
//        int h = (int) (bmpDraw.getIntrinsicHeight() * scaleSize);
//        bmpDraw.setBounds(0, 0, w, h);
//        return bmpDraw;
//    }
//
//    // if file not exist, return null;
//    public static Drawable getLocalDrawableWithAbsolutePath(Context context, String path) {
//        File file = new File(path);
//        if (!file.exists() || !file.isFile()) {
//            return null;
//        }
//        BitmapDrawable bmpDraw = new BitmapDrawable(context.getResources(), path);
//        if (bmpDraw == null) {
//            return null;
//        }
//        bmpDraw.setBounds(0, 0, bmpDraw.getIntrinsicWidth(), bmpDraw.getIntrinsicHeight());
//        return bmpDraw;
//    }
//
//    // if file not exist, return null;
//    public static Drawable getLocalDrawableWithAbsolutePath(Context context, String path, float scaleSize) {
//        File file = new File(path);
//        if (!file.exists() || !file.isFile()) {
//            return null;
//        }
//        BitmapDrawable bmpDraw = new BitmapDrawable(context.getResources(), path);
//        if (bmpDraw == null) {
//            return null;
//        }
//        int w = (int) (bmpDraw.getIntrinsicWidth() * scaleSize);
//        int h = (int) (bmpDraw.getIntrinsicHeight() * scaleSize);
//        bmpDraw.setBounds(0, 0, w, h);
//        return bmpDraw;
//    }
//
//    public static Bitmap getLocalBitmap(String path) {
//        String dir = WebSearchLocalAssistant.getFilePath() + File.separator + WebSearchLocalAssistant.LOCAL_DIR_WEBPAGE;
//        return getLocalBitmapWithAbsolutePath(dir + path);
//    }
//
//    public static Bitmap getLocalBitmapWithAbsolutePath(String path) {
//        if (TextUtils.isEmpty(path)) {
//            return null;
//        }
//
//        try {
//            FileInputStream fis = new FileInputStream(path);
//            return BitmapFactory.decodeStream(fis);
//        } catch (FileNotFoundException e) {
//        }
//        return null;
//    }
//
//    public static Bitmap loadImage(String imgPath) {
//        BitmapFactory.Options options;
//        try {
//            options = new BitmapFactory.Options();
//            options.inSampleSize = 2;
//            Bitmap bitmap = BitmapFactory.decodeFile(imgPath, options);
//            return bitmap;
//        } catch(Exception e) {
//        }
//        return null;
//    }
//
//    public static TranslateAnimation getUpTranslateAnim() {
//        TranslateAnimation anim = new TranslateAnimation(
//                Animation.RELATIVE_TO_SELF, 0,
//                Animation.RELATIVE_TO_SELF, 0,
//                Animation.RELATIVE_TO_SELF, 0,
//                Animation.RELATIVE_TO_SELF, -50);
//        anim.setDuration(2000);
//        return anim;
//    }
//
//    public static Bitmap transBigBitmap(Bitmap btm, float v) {
//        if (btm == null) {
//            return null;
//        }
//        Matrix r = new Matrix();
//        r.postScale(v, v);
//        Bitmap bp = Bitmap.createBitmap(btm, 0, 0, btm.getWidth(), btm.getHeight(), r, true);
//        return bp;
//    }
//
//    public static Bitmap transBigBitmap(Bitmap btm, int base) {
//        if (btm == null) {
//            return null;
//        }
//        float v = 1.0f * base / btm.getHeight();
//        Matrix r = new Matrix();
//        r.postScale(v, v);
//        Bitmap bp = Bitmap.createBitmap(btm, 0, 0, btm.getWidth(), btm.getHeight(), r, true);
//        return bp;
//    }
//
//    public static Bitmap transBigBitmap(Bitmap btm, int w, int h) {
//        int width = btm.getWidth();
//        int height = btm.getHeight();
//        Matrix r = new Matrix();
//        r.postScale((float) w / width, (float) h / height);
//        Bitmap bp = Bitmap.createBitmap(btm, 0, 0, width, height, r, true);
//        return bp;
//    }
//
//    public static RelativeLayout getLoadingView(Context ctx) {
//        RelativeLayout view = new RelativeLayout(ctx);
//        ImageView imageView = new ImageView(ctx);
//        imageView.setId(R.id.loading_circle_id);
//        imageView.setBackgroundResource(R.drawable.loading_circle);
//        RelativeLayout.LayoutParams l = new RelativeLayout.LayoutParams(
//                RelativeLayout.LayoutParams.WRAP_CONTENT,
//                RelativeLayout.LayoutParams.WRAP_CONTENT
//        );
//        l.addRule(RelativeLayout.CENTER_IN_PARENT);
//        view.addView(imageView, l);
//        TextView textView = new TextView(ctx);
//        l = new RelativeLayout.LayoutParams(
//                RelativeLayout.LayoutParams.WRAP_CONTENT,
//                RelativeLayout.LayoutParams.WRAP_CONTENT
//        );
//        l.addRule(RelativeLayout.CENTER_HORIZONTAL);
//        l.addRule(RelativeLayout.BELOW, R.id.loading_circle_id);
//        l.topMargin = (int) ctx.getResources().getDimension(R.dimen.loading_view_margin);
//        textView.setSingleLine();
//        textView.setText(ctx.getResources().getString(R.string.loading_text));
//        textView.setTextColor(ctx.getResources().getColor(R.color.loading_text_color));
//        textView.setTextSize(14);
//        view.addView(textView, l);
//
//        LinearLayout.LayoutParams l2 = new LinearLayout.LayoutParams(
//                LinearLayout.LayoutParams.MATCH_PARENT,
//                LinearLayout.LayoutParams.MATCH_PARENT
//        );
//        l2.topMargin = -DimentionUtil.getDimen(R.dimen.tl_index_bottom_margin);
//        view.setLayoutParams(l2);
//
//        return view;
//    }
//
//    public static void startRotateAnimation(View view) {
//        if (view == null) {
//            return;
//        }
//
//        RotateAnimation animation = new RotateAnimation(0f, 360f,
//                Animation.RELATIVE_TO_SELF, 0.5f,
//                Animation.RELATIVE_TO_SELF, 0.5f);
//        animation.setInterpolator(new LinearInterpolator());
//        animation.setDuration(1000);
//        animation.setRepeatCount(RotateAnimation.INFINITE);
//        ImageView v = (ImageView) view.findViewById(R.id.loading_circle_id);
//        if (v != null) {
//            v.startAnimation(animation);
//        }
//    }
//
//    public static void stopRotateAnimation(View view) {
//        if (view == null) {
//            return;
//        }
//
//        ImageView v = (ImageView) view.findViewById(R.id.loading_circle_id);
//        if (v != null) {
//            v.clearAnimation();
//        }
//    }
//
//    public static LinearLayout getErrorView(Context ctx) {
//        LinearLayout view = new LinearLayout(ctx);
//        view.setId(R.id.errorpage_container);
//        LinearLayout.LayoutParams l = new LinearLayout.LayoutParams(
//                LinearLayout.LayoutParams.MATCH_PARENT,
//                LinearLayout.LayoutParams.MATCH_PARENT
//        );
//        view.setLayoutParams(l);
//        view.setOrientation(LinearLayout.VERTICAL);
//
//        ImageView imageView = new ImageView(ctx);
//        l = new LinearLayout.LayoutParams(
//                LinearLayout.LayoutParams.WRAP_CONTENT,
//                LinearLayout.LayoutParams.WRAP_CONTENT
//        );
//        l.topMargin = DimentionUtil.getDimen(R.dimen.tl_index_error_page_margin_top);
//        l.gravity = Gravity.CENTER_HORIZONTAL;
//        imageView.setBackgroundResource(R.drawable.websearch_disconnected);
//        view.addView(imageView, l);
//
//        TextView textView = new TextView(ctx);
//        l = new LinearLayout.LayoutParams(
//                LinearLayout.LayoutParams.WRAP_CONTENT,
//                LinearLayout.LayoutParams.WRAP_CONTENT
//        );
//        l.topMargin = DimentionUtil.getDimen(R.dimen.tl_index_error_page_text_view_margin_top);
//        l.gravity = Gravity.CENTER;
//        textView.setText(ctx.getResources().getString(R.string.websearch_network_error));
//        textView.setTextColor(ctx.getResources().getColor(R.color.loading_text_color));
//        textView.setTextSize(TypedValue.COMPLEX_UNIT_PX, DimentionUtil.getTextSize(R.dimen.basic_text_size_5));
//        textView.setTextSize(16);
//        view.addView(textView, l);
//
//        TextView text1 = new TextView(ctx);
//        text1.setId(R.id.reloadPage);
//        l = new LinearLayout.LayoutParams(
//                LinearLayout.LayoutParams.WRAP_CONTENT,
//                LinearLayout.LayoutParams.WRAP_CONTENT
//        );
//        l.topMargin = DimentionUtil.getDimen(R.dimen.tl_index_error_page_text_view_margin_top);
//        l.gravity = Gravity.CENTER;
//        text1.setText(ctx.getResources().getString(R.string.websearch_network_retry));
//        text1.setTextColor(ctx.getResources().getColor(R.color.websearch_fail_page_text_color));
//        text1.setTextSize(TypedValue.COMPLEX_UNIT_PX, DimentionUtil.getTextSize(R.dimen.basic_text_size_3));
//        view.addView(text1, l);
//        return view;
//    }
//
//    public static final int VIEW_V6_SECTIONS = 1;
//    public static final int VIEW_ASSET = 2;
//
//    /*
//    * use for RecommendView, CategoryView
//    * add RelativeLayout.LayoutParams;
//    * */
//    public static TextView getHotNewView(Context ctx, String text, boolean isRecommend, int textLength) {
//        TextView view = new TextView(ctx);
//        view.setId(R.id.tl_index_hot_new_text);
//        view.setTextColor(ctx.getResources().getColor(R.color.tl_index_rectangle_text_color));
//        view.setText(text);
//        view.setTextSize(TypedValue.COMPLEX_UNIT_PX, DimentionUtil.getTextSize(R.dimen.tl_text_size_8));
//        view.setBackgroundResource(R.drawable.tl_index_hot_new_bg);
//        view.setGravity(Gravity.CENTER);
//
//        RelativeLayout.LayoutParams l = new RelativeLayout.LayoutParams(
//                RelativeLayout.LayoutParams.WRAP_CONTENT,
//                RelativeLayout.LayoutParams.WRAP_CONTENT
//        );
//        l.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
//        l.addRule(RelativeLayout.ALIGN_PARENT_TOP);
//        if (isRecommend) {
//            l.topMargin = (int) ctx.getResources().getDimension(R.dimen.tl_index_hot_new_margin_top);
//            l.rightMargin = (int) ctx.getResources().getDimension(R.dimen.tl_index_hot_new_margin_right);
//        } else {
//            switch (textLength) {
//                case 2:
//                    l.topMargin = (int) ctx.getResources().getDimension(R.dimen.tl_index_cate_hot_new_margin_top_2);
//                    l.rightMargin = (int) ctx.getResources().getDimension(R.dimen.tl_index_cate_hot_new_margin_right_2);
//                    break;
//                case 3:
//                    l.topMargin = (int) ctx.getResources().getDimension(R.dimen.tl_index_cate_hot_new_margin_top_3);
//                    l.rightMargin = (int) ctx.getResources().getDimension(R.dimen.tl_index_cate_hot_new_margin_right_3);
//                    break;
//                case 4:
//                    l.topMargin = (int) ctx.getResources().getDimension(R.dimen.tl_index_cate_hot_new_margin_top_4);
//                    l.rightMargin = (int) ctx.getResources().getDimension(R.dimen.tl_index_cate_hot_new_margin_right_4);
//                    break;
//                case 5:
//                    l.topMargin = (int) ctx.getResources().getDimension(R.dimen.tl_index_cate_hot_new_margin_top_5);
//                    l.rightMargin = (int) ctx.getResources().getDimension(R.dimen.tl_index_cate_hot_new_margin_right_5);
//                    break;
//                default:
//                    break;
//            }
//
//        }
//        view.setLayoutParams(l);
//        return view;
//    }
//
//    /*
//    * use for RecommendView, FindView rightTop;
//    * add RelativeLayout.LayoutParams for RecommendView;
//    * */
//    public static ImageView getRedPointView(Context ctx, int tag) {
//        ImageView view = new ImageView(ctx);
//        view.setId(R.id.tl_index_red_point);
//        view.setBackgroundResource(R.drawable.tl_index_red_point);
//        switch (tag) {
//            case VIEW_V6_SECTIONS:
//                break;
//
//            case VIEW_ASSET:
//                RelativeLayout.LayoutParams l3 = new RelativeLayout.LayoutParams(
//                        RelativeLayout.LayoutParams.WRAP_CONTENT,
//                        RelativeLayout.LayoutParams.WRAP_CONTENT
//                );
//                l3.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
//                l3.addRule(RelativeLayout.ALIGN_PARENT_TOP);
//                l3.topMargin = (int) ctx.getResources().getDimension(R.dimen.tl_index_asset_red_point_margin_top);
//                l3.rightMargin = (int) ctx.getResources().getDimension(R.dimen.tl_index_asset_red_point_margin_right);
//                view.setLayoutParams(l3);
//                break;
//
//            default:
//                break;
//        }
//
//        return view;
//    }
//
//    public static ImageView getUpdateRedPointView(Context ctx, int tag) {
//        ImageView view = new ImageView(ctx);
//        view.setId(R.id.tl_index_red_point);
//        view.setBackgroundResource(R.drawable.tl_index_red_point_2);
//        switch (tag) {
//            case VIEW_V6_SECTIONS:
//                break;
//
//            case VIEW_ASSET:
//                RelativeLayout.LayoutParams l3 = new RelativeLayout.LayoutParams(
//                        RelativeLayout.LayoutParams.WRAP_CONTENT,
//                        RelativeLayout.LayoutParams.WRAP_CONTENT
//                );
//                l3.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
//                l3.addRule(RelativeLayout.ALIGN_PARENT_TOP);
//                l3.topMargin = (int) ctx.getResources().getDimension(R.dimen.tl_index_asset_red_point_margin_top);
//                l3.rightMargin = (int) ctx.getResources().getDimension(R.dimen.tl_index_asset_red_point_margin_right);
//                view.setLayoutParams(l3);
//                break;
//
//            default:
//                break;
//        }
//
//        return view;
//    }
//
//    public static String getTextViewText(View view) {
//        if (view == null) {
//            return null;
//        }
//
//        try {
//            String text = (String) ((TextView) view).getText();
//            return text;
//        } catch (ClassCastException e) {
//            if (((TextView) view).getText() != null) { //android.text.SpannableString;
//                return ((TextView) view).getText().toString();
//            }
//        }
//        return null;
//    }
//
//    //是否是flyme系统;
//    public static boolean isFlymeOS() {
//        try {
//            final Method method = Build.class.getMethod("hasSmartBar");
//            return method != null;
//        } catch (NoSuchMethodException e) {
//            return false;
//        }
//    }
//
//    //是否支持smartbar;
//    public static boolean supportSmartBar() {
//        try {
//            final Method method = Build.class.getMethod("hasSmartBar");
//            return ((Boolean) method.invoke(null)).booleanValue();
//        } catch (Exception e) {
//        }
//        return false;
//    }
//
//    //2015-12-10banner、subbanner接入广告，需要附加一些native参数;
//    //存在这样的参数: network=CDMA EVDO，需要编码;
//    public static TreeMap<String, String> getNativeParams() {
//        TreeMap<String, String> params = new TreeMap<String, String>();
//        params.put("app_version", String.valueOf(TPApplication.getCurVersionCode()));
//        params.put("app_name", Constants.COOTEK_APP_NAME);
//        String nt = NetworkUtil.getNetName();
//        if (!TextUtils.isEmpty(nt)) {
//            try {
//                String nt2 = URLEncoder.encode(nt, TouchLifeConst.UTF_8);
//                params.put("network", nt2);
//            } catch (UnsupportedEncodingException e) {
//            }
//        }
//        String city = TouchLifeLocalStorage.getGeoCity();
//        if (!TextUtils.isEmpty(city)) {
//            try {
//                String city2 = URLEncoder.encode(city, TouchLifeConst.UTF_8);
//                params.put("geo_city", city2);
//            } catch (UnsupportedEncodingException e) {
//            }
//        }
//        city = TouchLifeLocalStorage.getCity();
//        if (!TextUtils.isEmpty(city)) {
//            params.put("city", city);
//        }
//        String addr = TouchLifeLocalStorage.getAddr();
//        if (!TextUtils.isEmpty(addr)) {
//            try {
//                String addr2 = URLEncoder.encode(addr, TouchLifeConst.UTF_8);
//                params.put("addr", addr2);
//            } catch (UnsupportedEncodingException e) {
//            }
//
//        }
//        String longitude = TouchLifeLocalStorage.getLongitude();
//        if (!TextUtils.isEmpty(longitude)) {
//            params.put("longitude", longitude);
//        }
//        String latitude = TouchLifeLocalStorage.getLatitude();
//        if (!TextUtils.isEmpty(latitude)) {
//            params.put("latitude", latitude);
//        }
//        return params;
//    }
//
//    public static TreeMap<String, String> getNativeParamsForDavinci() {
//        TreeMap<String, String> params = new TreeMap<String, String>();
//        params.put("v", String.valueOf(TPApplication.getCurVersionCode()));
//        params.put("ch", Constants.COOTEK_APP_NAME);
//
//        String city = TouchLifeLocalStorage.getGeoCity();
//        if (TextUtils.isEmpty(city)) {
//            city = TouchLifeLocalStorage.getCity();
//        }
//        if (!TextUtils.isEmpty(city)) {
//            try {
//                String city2 = URLEncoder.encode(city, TouchLifeConst.UTF_8);
//                params.put("city", city2);
//            } catch (UnsupportedEncodingException e) {
//                e.printStackTrace();
//            }
//        }
//
//        String addr = TouchLifeLocalStorage.getAddr();
//        if (!TextUtils.isEmpty(addr)) {
//            try {
//                String addr2 = URLEncoder.encode(addr, TouchLifeConst.UTF_8);
//                params.put("addr", addr2);
//            } catch (UnsupportedEncodingException e) {
//            }
//        }
//
//        String longitude = TouchLifeLocalStorage.getLongitude();
//        if (!TextUtils.isEmpty(longitude)) {
//            params.put("longitude", longitude);
//        }
//
//        String latitude = TouchLifeLocalStorage.getLatitude();
//        if (!TextUtils.isEmpty(latitude)) {
//            params.put("latitude", latitude);
//        }
//
//        return params;
//    }
//
//    public static TreeMap<String, String> generateNewsQueryString(String s,
//                                                                  int tu,
//                                                                  String mode,
//                                                                  boolean isKeyword,
//                                                                  String keyword,
//                                                                  String ctn,
//                                                                  String layout,
//                                                                  String ctid,
//                                                                  long starttime) {
//        TreeMap<String, String> params = ResUtil.getNativeParamsForDavinci();
//        params.put("noad", "1");
//        params.put("ctn", ctn);
//        params.put("ct", "MULTI");
//        params.put("ctclass", "EMBEDDED");
//        params.put("mode", mode);
//        params.put("token",WebSearchLocalAssistant.getAuthToken());
//        params.put("prt", String.valueOf(System.currentTimeMillis()));
//        params.put("tu", String.valueOf(tu));
//        params.put("rt", "JSON");
//        params.put("s", s);
//        params.put("layout", layout);
//        params.put("fid", String.valueOf(starttime));
//        if (isKeyword) {
//          params.put("keyword", keyword);
//        }
//        if (!StringUtils.isEmpty(ctid)) {
//            params.put("ctid", ctid);
//        }
//        String nt = NetworkUtil.getNetName();
//        if (!TextUtils.isEmpty(nt)) {
//            try {
//                String nt2 = URLEncoder.encode(nt, TouchLifeConst.UTF_8);
//                params.put("nt", nt2);
//            } catch (UnsupportedEncodingException e) {
//            }
//        }
//        return params;
//    }
//
//    public static TreeMap<String, String> generateDavinciAdQueryString(String s, int tu, int ftu) {
//        TreeMap<String, String> params = ResUtil.getNativeParamsForDavinci();
//        params.put("adn", "3");
//        params.put("w", "190");
//        params.put("h", "145");
//        params.put("at", "TUWEN");
//        params.put("adclass", "EMBEDDED");
//        params.put("mode", "1");
//        params.put("token", WebSearchLocalAssistant.getAuthToken());
//        params.put("prt", String.valueOf(System.currentTimeMillis()));
//        params.put("tu", String.valueOf(tu));
//        params.put("rt", "JSON");
//        params.put("s", s);
//        params.put("ftu", String.valueOf(ftu));
//        String nt = NetworkUtil.getNetName();
//        if (!TextUtils.isEmpty(nt)) {
//            try {
//                String nt2 = URLEncoder.encode(nt, TouchLifeConst.UTF_8);
//                params.put("nt", nt2);
//            } catch (UnsupportedEncodingException e) {
//            }
//        }
//        return params;
//    }
//
//    public static TreeMap<String, String> generateTabbarAdQueryString() {
//        TreeMap<String, String> params = ResUtil.getNativeParamsForDavinci();
//        params.put("adn", "1");
//        params.put("w", "190");
//        params.put("h", "190");
//        params.put("at", "IMG");
//        params.put("adclass", "EMBEDDED");
//        params.put("token", WebSearchLocalAssistant.getAuthToken());
//        params.put("prt", String.valueOf(System.currentTimeMillis()));
//        params.put("tu", "304");
//        params.put("rt", "JSON");
//        String nt = NetworkUtil.getNetName();
//        if (!TextUtils.isEmpty(nt)) {
//            try {
//                String nt2 = URLEncoder.encode(nt, TouchLifeConst.UTF_8);
//                params.put("nt", nt2);
//            } catch (UnsupportedEncodingException e) {
//            }
//        }
//        return params;
//    }
//
//    public static String generateDavinciUrl(String path, TreeMap<String, String> params) {
//        StringBuilder build = new StringBuilder();
//        build.append(path);
//        build.append("?");
//        String fk = params.firstKey();
//        String fv = params.get(fk);
//        build.append(String.format("%s=%s", fk, fv));
//        for (String k : params.keySet()) {
//            if (k.equals(fk)) {
//                continue;
//            }
//            String v = params.get(k);
//            build.append(String.format("&%s=%s", k, v));
//        }
//        return build.toString();
//    }
//
//    public static String generateUrlParams(TreeMap<String, String> params) {
//        StringBuilder build = new StringBuilder();
//        build.append("?");
//        String fk = params.firstKey();
//        String fv = params.get(fk);
//        build.append(String.format("%s=%s", fk, fv));
//        for (String k : params.keySet()) {
//            if (k.equals(fk)) {
//                continue;
//            }
//            String v = params.get(k);
//            build.append(String.format("&%s=%s", k, v));
//        }
//        return build.toString();
//    }
//
//    public static String generateRewardQueryParams(String eventName) {
//        StringBuilder build = new StringBuilder();
//        build.append("?app_name=cootek.contactplus.android.public");
//        build.append(String.format("&_token=%s", WebSearchLocalAssistant.getAuthToken()));
//        build.append(String.format("&app_version=%s", TPApplication.getCurVersionCode()));
//        build.append(String.format("&event_name=%s", eventName));
//        return build.toString();
//    }
//
//    public static String generateFamilyNumberParams() {
//        StringBuilder build = new StringBuilder();
//        build.append(String.format("?_token=%s", WebSearchLocalAssistant.getAuthToken()));
//        return build.toString();
//    }
//
//    public static String generateRegisterPushRequestParams() {
//        StringBuilder build = new StringBuilder();
//        build.append(String.format("?_token=%s", WebSearchLocalAssistant.getAuthToken()));
//        return build.toString();
//    }
//
//    public static String generateBindFamilyNumberParams(String contactName, String bindNumber, String label) {
//        StringBuilder build = new StringBuilder();
//        build.append(String.format("?_token=%s", WebSearchLocalAssistant.getAuthToken()));
//        build.append(String.format("&phone=%s", bindNumber));
//        build.append(String.format("&label=%s", label));
//        build.append("&is_binding_eachother=true");
//        if(contactName != null) {
//            build.append(String.format("&contacts_name=%s", contactName));
//        }
//        return build.toString();
//    }
//
//    public static String generateTipBeforVoipCallParams(String sourcePhone, String targetPhone) {
//        StringBuilder build = new StringBuilder();
//        build.append("?app_name=cootek.contactplus.android.public");
//        build.append(String.format("&_token=%s", WebSearchLocalAssistant.getAuthToken()));
//        build.append(String.format("&source_phone=%s", sourcePhone));
//        build.append(String.format("&target_phone=%s", targetPhone));
//        return build.toString();
//    }
//
//    public static String generateRewardSendParams(String eventName, String s, String rewardId, String ts, String type, String amount) {
//        StringBuilder build = new StringBuilder();
//        build.append("?");
//        build.append(String.format("&_token=%s", WebSearchLocalAssistant.getAuthToken()));
//        build.append(String.format("&app_version=%s", TPApplication.getCurVersionCode()));
//        build.append(String.format("&event_name=%s", eventName));
//        build.append(String.format("&s=%s", s));
//        build.append(String.format("&reward_id=%s", rewardId));
//        build.append(String.format("&ts=%s", ts));
//        build.append(String.format("&reward_type=%s", type));
//        build.append(String.format("&amount=%s", amount));
//        return build.toString();
//    }
//
//    public static boolean saveBitmap(String savePath, Bitmap bitmap) {
//        if (TextUtils.isEmpty(savePath)) {
//            return false;
//        }
//        try {
//            String path = savePath.substring(0, savePath.lastIndexOf("/"));
//            File dir = new File(path);
//            if (!dir.exists() || !dir.isDirectory()) {
//                TLog.i("ycs", "no dir " + path);
//                dir.mkdirs();
//            }
//
//            File file = new File(savePath);
//            boolean r = NetworkLocalImageUtil.saveFile(bitmap, file);
//            return r;
//        } catch (Exception e) {
//            TLog.i("ycs", "saveBitmap crash");
//            return false;
//        }
//    }
//
//    private static final String TL_INDEX_HIGHLIGHT_HIDDEN_KEY = "tl_highlight_hidden_";
//
//    public static String getHiddenHighLightKey(String identifier, long highlightStart) {
//        return TL_INDEX_HIGHLIGHT_HIDDEN_KEY + identifier + "_" + highlightStart;
//    }
//
//    public static View getDividerView(Context context) {
//        View divider = new View(context);
//        divider.setBackgroundColor(context.getResources().getColor(R.color.cootek_listitem_divider_color));
//        return divider;
//    }
//
//    private static final String TAG = "ycs";
//
//    public static void downloadApp(final Context ctx, final DownloadItem item, final boolean needIndexStat) {
//        TLog.i(TAG, "call downloadApp: " + item.mUrl);
//        final String packageName = item.mPackageName;
//        String defaultActivityName = TextUtils.isEmpty(item.mDefaultActivityName) ? "" : item.mDefaultActivityName;
//
//        if (!Constants.PACKAGE_NAME.equals(packageName) && PackageUtil.isPackageInstalled(packageName)) {
//            PackageUtil.launchApp(packageName, defaultActivityName);
//            return;
//        }
//
//        if (!NetworkUtil.isNetworkAvailable()) {
//            DialerToast.showMessage(ctx, R.string.websearch_network_error, Toast.LENGTH_LONG);
//        } else {
//            WebSearchJavascriptInterface.IAppDownloadCallback callback = new WebSearchJavascriptInterface.IAppDownloadCallback() {
//                @Override
//                public void onDownloadFinished(final String appId) {
//                    TLog.i(TAG, "onDownloadFinished appId: " + appId);
//                    if (needIndexStat) {
//                        ScenarioCollector.customEvent("index_native downloadFinished");
//                    }
//                }
//
//                @Override
//                public void onInstallFinished(final String appId) {
//                    TLog.i(TAG, "onInstallFinished appId: " + appId);
//                    if (needIndexStat) {
//                        ScenarioCollector.customEvent("index_native installFinished");
//                    }
//                }
//
//                @Override
//                public void onBonusRequestSended(final String appId) {
//                    TLog.i(TAG, "onBonusRequestSended appId: " + appId);
//                }
//
//                @Override
//                public void onBonusResult(final String appId, final int result) {
//                    TLog.i(TAG, "onBonusResult appId: " + appId + " result: " + result);
//                }
//            };
//
//            if (NetworkUtil.isWifi()) {
//                if (!DownloadManager.isInitialized()) {
//                    DownloadManager.init(ModelManager.getContext());
//                }
//                DownloadManager.getInstance().downloadWebViewApk(
//                        item.mAppId,
//                        item.mUrl,
//                        item.mNeedConfirm,
//                        item.mAppName,
//                        item.mAutoInstall,
//                        item.mBonusType,
//                        item.mPackageName,
//                        defaultActivityName,
//                        item.mAutoOpen,
//                        item.mWifiOnly,
//                        callback);
//            } else {
//                String title = ctx.getString(R.string.app_download_award_dialog_title);
//                String dialogContent = ctx.getString(R.string.app_download_award_dialog_content_no_network);
//                DialogUtil.showAppDownloadBonusDialog(
//                        title,
//                        dialogContent,
//                        ctx,
//                        item.mAppId,
//                        item.mUrl,
//                        item.mNeedConfirm,
//                        item.mAppName,
//                        item.mAutoInstall,
//                        item.mBonusType,
//                        item.mPackageName,
//                        defaultActivityName,
//                        item.mAutoOpen,
//                        item.mWifiOnly,
//                        callback);
//            }
//        }
//    }
//
//    public static final int TAG_BANNER = 1;
//    public static final int TAG_MINI_BANNER = 3;
//
//    public static void downBannerImage(final List<IndexItem> items, final int tag) {
//        if (items == null) { //items.size() == 0时也需要进行处理，否则当从有banner变无banner时会失效;
//            return;
//        }
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                for (IndexItem item : items) {
//                    String dirPath = WalletDataManager.getInst().getBannerDir();
//                    File dir = new File(dirPath);
//                    if (!dir.exists()) {
//                        dir.mkdirs();
//                    }
//
//                    String path = WalletDataManager.getInst().getBannerAbsolutePath(item.mImageLink);
//                    File file = new File(path);
//                    if (file.exists()) {
//                        continue;
//                    }
//
//                    TLog.i(TAG, String.format("ycs begin down banner link:%s, savePath:%s", item.mImageLink, path));
//
//                    try {
//                        byte[] imageBytes = NetworkLocalImageUtil.getImageFromNetwork(item.mImageLink);
//                        Bitmap bitmap = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.length);
//
//                        //检查mini_banner的尺寸是否是180*100，不是则转换，这里检查条件放宽到200*120;
//                        if (tag == TAG_MINI_BANNER) {
//                            int w = bitmap.getWidth();
//                            int h = bitmap.getHeight();
//                            if (w > 200 || h > 120) {
//                                bitmap = transBigBitmap(bitmap, 180, 100);
//                            }
//                        }
//
//                        NetworkLocalImageUtil.saveFile(bitmap, file);
//                    } catch (Exception e) {
//                    }
//                }
//
//                boolean flag = true;
//                for (IndexItem item : items) {
//                    String path = WalletDataManager.getInst().getBannerAbsolutePath(item.mImageLink);
//                    File file = new File(path);
//                    if (!file.exists()) {
//                        flag = false;
//                        break;
//                    }
//                }
//                if (flag) {
//                    switch (tag) {
//                        case TAG_BANNER:
//                            TLog.i("ycs", "call refreshListener  <------- banner download finished");
//                            TouchLifeManager.getInstance().callRefreshView(RefreshListener.BANNER_REFRESH);
//                            break;
//
//                        case TAG_MINI_BANNER:
//                            PrefUtil.setKey(TouchLifeConst.INDEX_MINI_BANNER_IMAGE_READY, true);
//                            TLog.i("ycs", "call refreshListener  <------- notice download finished");
//                            TouchLifeManager.getInstance().callRefreshView(RefreshListener.MINI_BANNER_REFRESH);
//                            TLNoticeDataManager.getInst().refreshMiniBannerTabbar();
//                            break;
//
//                        default:
//                            break;
//                    }
//
//                }
//            }
//        }).start();
//    }
//
//    public static Bitmap getRoundedCornerBitmap(Bitmap bitmap, float roundPx) {
//        Bitmap output = Bitmap.createBitmap(bitmap.getWidth(),
//                bitmap.getHeight(), Bitmap.Config.ARGB_8888);
//        Canvas canvas = new Canvas(output);
//
//        final int color = 0xff424242;
//        final Paint paint = new Paint();
//        final Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
//        final RectF rectF = new RectF(rect);
//
//        paint.setAntiAlias(true);
//        canvas.drawARGB(0, 0, 0, 0);
//        paint.setColor(color);
//        canvas.drawRoundRect(rectF, roundPx, roundPx, paint);
//
//        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
//        canvas.drawBitmap(bitmap, rect, rect, paint);
//
//        return output;
//    }
//
//    public static void downFullScreenAdImage(final List<IndexItem> items) {
//        if (items == null) { //items.size() == 0时也需要进行处理，否则当从有banner变无banner时会失效;
//            return;
//        }
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                for (IndexItem item : items) {
//                    String dirPath = WalletDataManager.getInst().getBannerDir();
//                    File dir = new File(dirPath);
//                    if (!dir.exists()) {
//                        dir.mkdirs();
//                    }
//
//                    String imagePath = WalletDataManager.getInst().getBannerAbsolutePath(item.mImageLink);
//                    File impageFile = new File(imagePath);
//
//                    String fullScreenAdImagePath = WalletDataManager.getInst().getBannerAbsolutePath(item.mFullScreenAdImageLink);
//                    File fullScreenAdImageFile = new File(fullScreenAdImagePath);
//                    if (!impageFile.exists()) {
//                        TLog.i(TAG, String.format("ycs begin down image link:%s, savePath:%s", item.mImageLink, imagePath));
//                        try {
//                            byte[] imageBytes = NetworkLocalImageUtil.getImageFromNetwork(item.mImageLink);
//                            Bitmap bitmap = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.length);
//                            NetworkLocalImageUtil.saveFile(bitmap, impageFile);
//                        } catch (Exception e) {
//                        }
//                    }
//
//                    if (!fullScreenAdImageFile.exists()) {
//                        TLog.i(TAG, String.format("ycs begin down full screen ad image link:%s, savePath:%s", item.mFullScreenAdImageLink, fullScreenAdImagePath));
//                        try {
//                            byte[] imageBytes = NetworkLocalImageUtil.getImageFromNetwork(item.mFullScreenAdImageLink);
//                            Bitmap bitmap = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.length);
//                            NetworkLocalImageUtil.saveFile(bitmap, fullScreenAdImageFile);
//
//                            TLNoticeDataManager.getInst().refreshFullScreenAdTabbar();
//                        } catch (Exception e) {
//                        }
//                    }
//                }
//            }
//        }).start();
//    }
//
//    //为8月会战的验证功能性版本增加的自定义事件统计方法;
//    public static void customEvent(String name) {
//        if (TextUtils.isEmpty(name) || name.indexOf(" ") == -1) {
//            return;
//        }
//
//        HashMap<String, Object> mp = new HashMap<String, Object>();
//        mp.put("id", 139);
//        mp.put("name", "custom_event");
//        mp.put("event", name);
//        mp.put("unique", UUID.randomUUID().toString());
//        RealTimeDataUpload.getInst().upLoadData(StatConst.PATH_WEBSEARCH_SCENARIO, mp);
//    }
//
//    //删除热门资讯快捷方式保存的图片;
//    //FIXME; 应该存放在一个单独目录，而不是res/image下;
//    public static void delNewsExpiredPic() {
//        long current = System.currentTimeMillis();
//        String deletePath = WebSearchLocalAssistant.getWebpagesPath() + File.separator + "res/image";
//        try {
//            File allF = new File(deletePath);
//            File[] files = allF.listFiles();
//            for (File f: files) {
//                if (f.isFile()) {
//                    if(f.getName().contains(".jpg") || f.getName().contains(".png")) {
//                        continue;
//                    } else {
//                        if (Math.abs(f.lastModified() - current) > 2 * 24 * 60 * 60 * 1000) {
//                            boolean delete = f.delete();
//                        }
//                    }
//                }
//            }
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }

    public static boolean isChineseSimpleEnv() {
        return ModelManager.getContext().getResources().getConfiguration().locale.getCountry().equals("CN");
    }

    public static boolean isChineseTraditionalEnv() {
        return ModelManager.getContext().getResources().getConfiguration().locale.getCountry().equals("TW");
    }

    public static boolean isEnglishEnv() {
        Locale locale = ModelManager.getContext().getResources().getConfiguration().locale;
        return locale.getCountry().equals("UK") ||
                locale.getCountry().equals("US");
    }


    public static boolean isIntegerString(String s) {
        Pattern pattern = Pattern.compile("[0-9]*");
        return pattern.matcher(s).matches();
    }

    public static String parseStrToMD5Lower32(String str) {
        String reStr = null;
        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            byte[] bytes = md5.digest(str.getBytes());
            StringBuffer stringBuffer = new StringBuffer();
            for (byte b : bytes){
                int bt = b&0xff;
                if (bt < 16){
                    stringBuffer.append(0);
                }
                stringBuffer.append(Integer.toHexString(bt));
            }
            reStr = stringBuffer.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return reStr;
    }

    public static String parseStrToMD5Upper32(String str) {
        String res = parseStrToMD5Lower32(str);
        if (!TextUtils.isEmpty(res)) {
            res = res.toUpperCase();
        }
        return res;
    }
}
