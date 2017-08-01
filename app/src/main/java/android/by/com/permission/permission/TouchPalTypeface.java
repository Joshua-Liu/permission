package android.by.com.permission.permission;

import android.by.com.permission.model.ModelManager;
import android.graphics.Typeface;
import android.text.TextUtils;
import android.util.Log;


import java.io.File;
import java.util.Hashtable;

/**
 * TouchPalTypeface is used for create and provide Typeface for widgets.
 */
public class TouchPalTypeface {

	/** The default font used in keyboard. */
	public static Typeface KEYBOARD = Typeface.DEFAULT;
	/** The font used for icon. */
	public static Typeface ICON1 = Typeface.DEFAULT_BOLD;
	public static Typeface ICON2 = Typeface.DEFAULT_BOLD;
	public static Typeface ICON3 = Typeface.DEFAULT_BOLD;
	public static Typeface ICON4 = Typeface.DEFAULT_BOLD;
    public static Typeface YP_ICON3 = Typeface.DEFAULT_BOLD;
    public static Typeface ICON1_V6 = Typeface.DEFAULT_BOLD;
    public static Typeface ICON2_V6 = Typeface.DEFAULT_BOLD;
    public static Typeface ICON3_V6 = Typeface.DEFAULT_BOLD;
	public static Typeface ICON4_V6 = Typeface.DEFAULT_BOLD;

    public static Typeface YP_INDEX_FONT = Typeface.DEFAULT_BOLD;
	
	private static final String PATH_KEYBOARD = "fonts/keyboard.ttf";
	private static final String PATH_ICON1 = "fonts/icon1.ttf";
	private static final String PATH_ICON2 = "fonts/icon2.ttf";
	private static final String PATH_ICON3 = "fonts/icon3.ttf";
	private static final String PATH_ICON4 = "fonts/icon4.ttf";
	private static final String PATH_ICON1_V6 = "fonts/dialer_icon1.ttf";
	private static final String PATH_ICON2_V6 = "fonts/dialer_icon2.ttf";
	private static final String PATH_ICON3_V6 = "fonts/dialer_icon3.ttf";
	private static final String PATH_ICON4_V6 = "fonts/dialer_icon4.ttf";
	private static final String PATH_YP_ICON3 = "fonts/yellowpage_icon3.ttf";

	private static final String TTF_NAME_ICON1 = "icon1";
	private static final String TTF_NAME_ICON2 = "icon2";
	private static final String TTF_NAME_ICON3 = "icon3";
	private static final String TTF_NAME_ICON4 = "icon4";
	private static final String TTF_NAME_ICON1_V6 = "icon1_v6";
	private static final String TTF_NAME_ICON2_V6 = "icon2_v6";
	private static final String TTF_NAME_ICON3_V6 = "icon3_v6";
	private static final String TTF_NAME_ICON4_V6 = "icon4_v6";
	private static final String TTF_NAME_YP_ICON3 = "yp_icon3";
    private static final String TTF_NAME_YP_INDEX_FONT = "yp_index";

    public static final String PATH_YP_INDEX_FONT = "webpages/yp_index.ttf";
    public static final String YP_INDEX_FONT_NAME = "yp_index.ttf";

	private static Hashtable<String, Typeface> sFontTable = new Hashtable<>();
	private static final String TAG = "TouchPalTypeface";

	/**
	 * This function should be called when dialer is started or the skin is
	 * changed.
	 * 
	 * @param skinContext
	 *            the current skin context.
	 * @param defaultContext
	 *            the default main package context.
	 */
	public static void setupTypeface(IPackage skinContext,
			IPackage defaultContext) {
		KEYBOARD = getTypeface(skinContext, defaultContext, PATH_KEYBOARD,
				Typeface.DEFAULT);
		ICON1 = getTypeface(skinContext, defaultContext, PATH_ICON1,
				Typeface.DEFAULT_BOLD);
		ICON2 = getTypeface(skinContext, defaultContext, PATH_ICON2,
				Typeface.DEFAULT_BOLD);
		ICON3 = getTypeface(skinContext, defaultContext, PATH_ICON3,
				Typeface.DEFAULT_BOLD);
		ICON4 = getTypeface(skinContext, defaultContext, PATH_ICON4,
				Typeface.DEFAULT_BOLD);
		ICON1_V6 = getTypeface(skinContext, defaultContext, PATH_ICON1_V6,
				Typeface.DEFAULT_BOLD);
		ICON2_V6 = getTypeface(skinContext, defaultContext, PATH_ICON2_V6,
				Typeface.DEFAULT_BOLD);
		ICON3_V6 = getTypeface(skinContext, defaultContext, PATH_ICON3_V6,
				Typeface.DEFAULT_BOLD);
		ICON4_V6 = getTypeface(skinContext, defaultContext, PATH_ICON4_V6,
				Typeface.DEFAULT_BOLD);
        YP_ICON3 = getTypeface(skinContext, defaultContext, PATH_YP_ICON3,
                Typeface.DEFAULT_BOLD);

//        setupYPIndexTypeface();
		Log.e("chao", "finish setup type face");
	}

	private static Typeface getTypeface(IPackage skinContext,
                                        IPackage defaultContext, String ttfFilepath, Typeface defTf) {
		Typeface skinTf = getTypeface(skinContext, ttfFilepath);
		if (skinTf != null) {
			return skinTf;
		}

		Typeface localTf = getTypeface(defaultContext, ttfFilepath);
		if (localTf != null) {
			return localTf;
		}

		return defTf;
	}

	private static Typeface getTypeface(IPackage context, String ttfFilepath) {
		String key = context.getPackageName() + ":" + ttfFilepath;
		Typeface typeface = sFontTable.get(key);
		if (typeface != null) {
			return typeface;
		}

		if (!TextUtils.isEmpty(ttfFilepath)) {
			try {
				typeface = Typeface.createFromAsset(context.getAssets(),
						ttfFilepath);
				sFontTable.put(key, typeface);
			} catch (Exception ex) {
				Log.w(TAG, "Font not found");
			}
		}

		return typeface;
	}

    public static Typeface getTypefacWithName(String path) {
        if (PATH_ICON1.equalsIgnoreCase(path)) {
            return TouchPalTypeface.ICON1;
        } else if (PATH_ICON1_V6.equalsIgnoreCase(path)) {
            return TouchPalTypeface.ICON1_V6;
        } else if (PATH_ICON2.equalsIgnoreCase(path)) {
            return TouchPalTypeface.ICON2;
        } else if (PATH_ICON2_V6.equalsIgnoreCase(path)) {
            return TouchPalTypeface.ICON2_V6;
        } else if (PATH_ICON3.equalsIgnoreCase(path)) {
            return TouchPalTypeface.ICON3;
        } else if (PATH_ICON3_V6.equalsIgnoreCase(path)) {
            return TouchPalTypeface.ICON3_V6;
		} else if (PATH_ICON4_V6.equalsIgnoreCase(path)) {
			return TouchPalTypeface.ICON4_V6;
        } else if (PATH_ICON4.equalsIgnoreCase(path)) {
			return TouchPalTypeface.ICON4;
        } else if (PATH_YP_ICON3.equalsIgnoreCase(path)) {
            return TouchPalTypeface.YP_ICON3;
		} else if (PATH_YP_INDEX_FONT.equalsIgnoreCase(path)) {
            return TouchPalTypeface.YP_INDEX_FONT;
        }
        return Typeface.DEFAULT;

    }

    public static Typeface getTypefacWithTtfName(String ttfName) {
        if (TTF_NAME_ICON1.equalsIgnoreCase(ttfName)) {
            return TouchPalTypeface.ICON1;
        } else if (TTF_NAME_ICON1_V6.equalsIgnoreCase(ttfName)) {
            return TouchPalTypeface.ICON1_V6;
        } else if (TTF_NAME_ICON2.equalsIgnoreCase(ttfName)) {
            return TouchPalTypeface.ICON2;
        } else if (TTF_NAME_ICON2_V6.equalsIgnoreCase(ttfName)) {
            return TouchPalTypeface.ICON2_V6;
        } else if (TTF_NAME_ICON3.equalsIgnoreCase(ttfName)) {
            return TouchPalTypeface.ICON3;
        } else if (TTF_NAME_ICON3_V6.equalsIgnoreCase(ttfName)) {
            return TouchPalTypeface.ICON3_V6;
        } else if (TTF_NAME_ICON4.equalsIgnoreCase(ttfName)) {
			return TouchPalTypeface.ICON4;
		} else if (TTF_NAME_ICON4_V6.equalsIgnoreCase(ttfName)) {
			return TouchPalTypeface.ICON4_V6;
        } else if (TTF_NAME_YP_ICON3.equalsIgnoreCase(ttfName)) {
            return TouchPalTypeface.YP_ICON3;
		} else if (TTF_NAME_YP_INDEX_FONT.equalsIgnoreCase(ttfName)) {
            return TouchPalTypeface.YP_INDEX_FONT;
        }
        return null;
    }

//    public static void setupYPIndexTypeface() {
//        Typeface typeface;
//        try {
//            //为避免zip更新不及时的情况，将下载的font放到zip包目录之外;
//            String path = WebSearchLocalAssistant.getIndexDataPath() + File.separator + YP_INDEX_FONT_NAME;
//            typeface = Typeface.createFromFile(path);
//        } catch (Exception e1) {
//            try {
//                String path = WebSearchLocalAssistant.getFilePath() + File.separator + PATH_YP_INDEX_FONT;
//                typeface = Typeface.createFromFile(path);
//            } catch (Exception e2) {
//                Log.w(TAG, "yp_index.ttf not found on webpages dir");
//                try {
//                    //安装启动的时候，assets/webpages还未解压，此时还是使用assets/里的文件;
//                    typeface = Typeface.createFromAsset(ModelManager.getContext().getAssets(),
//                            PATH_YP_INDEX_FONT);
//                } catch (Exception e3) {
//                    Log.w(TAG, "yp_index.ttf not found on assets");
//                    typeface = Typeface.DEFAULT_BOLD;
//                }
//            }
//        }
//        Log.i("ycs", "setupYPIndexTypeface step 3");
//        YP_INDEX_FONT = typeface;
//    }

}
