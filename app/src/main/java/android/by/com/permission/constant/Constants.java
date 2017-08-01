package android.by.com.permission.constant;

public class Constants {
	public static final String PACKAGE_NAME = "android.by.com.permission";

    public static final String PACKAGE_ANDES_PREFIX = "com.cootek.andes";
	
	public static final String ANDROID_OS_NAME = "Android";
	
	public static final String COOTEK_APP_NAME = "cootek.contactplus.android.public";
    public static final String COOTEK_APP_NAME_EXTERNAL = "cootek.contactplus.android.external";
	
	public static final String EMPTY_STR = "";
	public static final String STR_ENTER = "\n";


	//xiaomi default set IN_VISIBLE_GROUP to 0
    //device manufacturer
	public static final String XIAOMI = "Xiaomi";
    public static final String COOLPAD = "YuLong";
    public static final String COOLPAD_ALT = "coolpad";
    public static final String COOLPAD_8279 = "Coolpad";
    public static final String SMARTISAN = "smartisan";
    public static final String SAMSUNG = "samsung";
    public static final String MEIZU = "Meizu";
    public static final String HUAWEI = "huawei";
    public static final String HONGMI = "hongmi";
    public static final String HM_NOTE = "gucci";
    public static final String HONGMI_NOTE = "hm_note";
    public static final String XIAOMI_MI = "mi";
    public static final String MI_NOTE = "mi_note";
    public static final String MANUFACTURER_OPPO = "OPPO";
    public static final String MANUFACTURER_HTC = "HTC";
    public static final String NUBIA = "nubia";
    public static final String MANUFACTURER_VIVO = "VIVO";
    public static final String ASUS = "ASUS";

    //device model
    public static final String MODEL_OPPO_X9007 = "X9007";
	public static final String MODEL_NEXUS_5 = "Nexus 5";
	public static final String MODEL_VIVO_X5S_L = "vivo X5S L";
    public static final String XIAOMI_4C = "Mi-4c";
    public static final String XIAOMI_NOTE_PRO = "MI NOTE Pro";

    //reject type device info
    public static final String LGG3 = "LGE_g3";
    

	public static final int CONTACTED_TIMES_WEIGHT_MAX = 30;
	public static final int CONTACTED_COUNT_BEFORE_MAX = 30;
	

	public static final String VIEW_EXTRA_CONTACT_ID = "view_extra_contact_id";
	//View Detail Extra
	public static final String ACTION_VIEW_DETAIL = "com.cootek.smartdialer.action.VIEW_DETAIL";
	public static final String VIEW_DETAIL_EXTRA = "view_detail_extra";
	public static final String VIEW_DETAIL_NAME = "view_detail_name";
	public static final String VIEW_DETAIL_EXTRA_UNKNOWN_NUMBER = "view_detail_extra_unknown_number";
	public static final String VIEW_DETAIL_EXTRA_NORMALIZED_NUMBER = "view_detail_extra_normalized_number";
	public static final int VIEW_DETAIL_REQUEST = 2000;
	public static final String CLEAR_INTPUT_IN_DIALER_SCREEN = "clear_input_in_dialer_screen";
	public static final String VIEW_DETAIL_EXTRA_PHOTO_CHANGE_ID = "view_detail_extra_photo_change_id";
	//View History Extra
	public static final String ACTION_VIEW_HISTORY = "com.cootek.smartdialer.action.VIEW_HISTORY";
	public static final String VIEW_HISTORY_EXTRA = "view_history_extra";
	public static final String VIEW_HISTORY_EXTRA_UNKNOWN_NUMBER = "view_history_extra_unknown_number";
	public static final String VIEW_HISTORY_EXTRA_NORMALIZED_NUMBER = "view_history_extra_normalized_number";
	public static final String ACTION_VIEW_QUICK_CONTACT = "com.android.contacts.action.QUICK_CONTACT";
	//View Call Note
    public static final String ACTION_VIEW_CALL_NOTE = "com.cootek.smartdialer.action.VIEW_CALL_NOTE";
    public static final String VIEW_CALL_NOTE_EXTRA_UNKNOWN_NUMBER = "view_call_note_extra_unknown_number";
    public static final String VIEW_CALL_NOTE_EXTRA_NORMALIZED_NUMBER = "view_call_note_extra_normalized_number";

	//XML namespace
	public static final String DIALER_XML_NS_PREFIX = "";
	public static final String DIALER_XML_NAMESPACE = "http://www.cootek.com/smartdialer/4.0";
	
	//black list
	public static final int END_CALL = 0;
	public static final int TURN_SILENCE = 1;

	public static final int BLOCK_ONLY_BLACK = 0;
	public static final int ALLOW_ONLY_CONTACT = 1;
	public static final int ALLOW_ONLY_CONTACT_AND_WHITE = 2;
	public static final int ALLOW_ONLY_WHITE = 3;
	public static final int BLOCK_NONE = 4;
	
	public static final String BUNDLE_BLACK_STATE = "blackstate";
	public static final String BUNDLE_CONTACTID = "contact_id";
	public static final String BUNDLE_ISVOICEMAIL = "contact_is_voicemail";
	public static final String BUNDLE_NUMBER = "number";
	public static final String BUNDLE_TAB_NUMBER = "tab_number";

	public static final String INTENT_KEY_DATA = "data";
	
	//preference
	public static final String ACTION_PREF_BLOCK_SETTING = "com.cootek.smartdialer.action.PREF_BLOCK_SETTING";
	
	//gesture
	public final static String USER_GESTURE_LIB_NAME = "gestures";
	
	public final static String VIEW_GESTURE_CONTACT_ID = "view_gesture_contact_id";

	public final static int FREQUENT_CONTACT_COUNT = 5;


	public final static String ACTIVATION_CODE = "touchpal";

    //Default update packagenames
    public final static String DEFAULT_UPDATE_MARKET_LIST = "com.baidu.appsearch|com.tencent.android.qqdownloader|com.xiaomi.market|com.hiapk.marketpho|com.dragon.android.pandaspace|com.wandoujia.phoenix2";
    public final static String DEFAULT_RATE_MARKET_LIST = "com.tencent.android.qqdownloader|com.baidu.appsearch|com.qihoo.appstore|com.xiaomi.market|com.huawei.appmarket|com.oppo.market|com.meizu.mstore";

	public final static String JUNHAO = "Junhao";
	public final static String Frank = "Frank";
	
	public final static String BOOK_TICKET_NUMBER  = "95105105";
	
	public final static String ONLINE_APPKEY = "4fa878d7527015728d00007a";
	public final static String OEM_APPKEY = "502371095270151a4d000001";
    public final static String OEM_MODULE_APPKEY = "50e6899c5270155a55000031";
    
    public final static double BIG_SCREEN_DIVIDING_SIZE = 3.8;

    public final static int BIG_PHOTO_SDK = 14;
    public final static int THUMB_NAIL_SIZE = 96;
    public final static char FAVORITE_HEART_CHAR = '\u2661';


    public static final int FIRST_START_TIMES = 5;
    
    public static final int DEFAULT_PHOTO_LOAD_SIZE = 38;
    
    public static final String ALIYUN_PREFIX_ADDRESS = "http://dialer.cdn.cootekservice.com";
    public static final String WS2_SERVER_ADDRESS = "ws2.cootekservice.com";
//    public static final String DIALER_SERVER_ADDRESS = Configs.ENABLE_DEBUG_SERVER ?
//            "http://" + Configs.DEBUG_SERVER + ":"  + Configs.DEBUG_SERVER_HTTP_PORT :
//            "http://dialer-voip.cootekservice.com";

    public static final String DIALER_SERVER_API_REWARD_CENTER = "/voip/reward_center";
    public static final int PLATFORM_ANDROID = 1;


    public static final int WHAT_LOCAL = 1;
    public static final int WHAT_REMOTE = 2;
    public static final int WHAT_VERIFY = 3;
    

    public static final String SUPER_SEARCH_SERVICE_FOLDER = "service_folder";

    public static final int TEXT_SIZE_NORMAL_TAG = 0;
    public static final int TEXT_SIZE_LARGE_TAG = 1;
    public static final int TEXT_SIZE_SUPERLARGE_TAG = 2;

	public static final String BUGLY_APPID = "900004036";


    public static final String ACTION_VOIP_OFF = "com.cootek.smartdialer.action.VOIP_OFF";
    public static final String ACTION_VOIP_ON = "com.cootek.smartdialer.action.VOIP_ON";
    public static final String UNINSTALL_FOLDER = "sequence";
    public static final String UNINSTALL_FILE = "uni.txt";

    public static final String EXTRA_PHONE = "phone";

    public static final String UPLOAD_DIR = "sequence";
    public static final String UPLOAD_FILE = "sequence.txt";

    public static final int SSL_INVALID_ERROR = 5;
    public static final String DUALSIM_VOIP = "voip";

    public static final int USER_LOGOUT = 1;
    public static final int USER_LOGIN_NEW  = 2;
    public static final int USER_LOGIN_OLD = 3;

    //baidu ad sdk, gdt ad sdk;
    public static final String GDT_APP_ID = "1104688960";
    public static final int PLATFORM_ID_DAVINCI_AD = 1;
    public static final int PLATFORM_ID_BAIDU = 100;
    public static final int PLATFORM_ID_GDT = 101;

    public static final String WS2_HOST = "http://ws2.cootekservice.com";
    public static final String WS2_DOMAIN = "ws2.cootekservice.com";
    public static final String AD_VOIP_PATH = "/ad/voip";

    // constants used for BiBi
    public static final String BIBI_PACKAGE_NAME = "com.cootek.walkietalkie";
    public static final String BIBI_SERVICE = "com.cootek.andes.voip.MicroCallService";
    public static final String START_BIBI_ACTION = "com.cootek.andes.voip.action.MICROCALL_CORE_INIT";
    public static final String INFORM_BIBI_C2C_CHANGE_ACTION = "com.cootek.andes.voip.action.ON_C2C_STATUS_CHANGE";
    public static final String INFORM_BIBI_C2C_CHANGE_EXTRA = "c2c_busy_status";
    public static final String INFORM_BIBI_C2C_CHANGE_BUNDLE = "bundle";
    public static final String INFORM_BIBI_C2C_ALIVE_ACTION = "com.cootek.andes.voip.action.ON_C2C_KEEP_ALIVE";
    public static final long INFORM_BIBI_C2C_ALIVE_INTERVAL = 30000;

    public static final String VERIFY_CODE_RESULT = "code_result";

    public static final String CATEGOYR_LAUNCHER = "android.intent.category.LAUNCHER";
    public static final String ACTION_MAIN = "android.intent.action.MAIN";

    public static final String TRUE_TEXT = "true";
    public static final String FALSE_TEXT = "false";

    public static final String FAMILY_PAGE_DETAIL_URL = "http://dialer.cdn.cootekservice.com/web/internal/activities/family-num-bind/bind-detail.html";
    public static final String FAMILY_PAGE_INDEX_URL = "http://dialer.cdn.cootekservice.com/web/internal/activities/family-num-bind/index.html";

    public static final String UNKNOWN = "unknown";

    public static final String WECHAT_INVITE_ICON_URL = "http://tl.chubaobeijing.cn/web/internal/activities/invite_rewards_visualization/img/share-icon.png";
    public static final String REDPACKET_PACKAGE_WECHAT_INVITE_ICON_URL = "http://dialer.cdn.cootekservice.com/web/internal/activities/red-package-center/img/share-icon.png";
    public static final String INVITE_SHARE_URL = "http://dialer.cdn.cootekservice.com/web/internal/activities/invite_rewards_visualization/share-page.html";
    public static final String REDPACKET_SHARE_PAGE_URL = "http://dialer.cdn.cootekservice.com/web/internal/activities/red-package-center/index.html";
    public static final String REDPACKET_CENTER_URL = "http://dialer.cdn.cootekservice.com/web/internal/activities/red-package-center/red-center.html";
    public static final String REDPACKET_GROWTH_URL = "http://dialer.cdn.cootekservice.com/web/internal/activities/red-package-center/packets-growth.html";
    //debug: "http://10.0.15.103:8080/templates/dialer/get_mobile_data.html";
    public static final String REDPACKET_PACKAGE_SHARE_URL = "http://dialer.cdn.cootekservice.com/web/external/get_mobile_data/get_mobile_data.html";

    //view detail styles
    public static final String VIEW_DETAIL1 = "view_detail_1";
    public static final String VIEW_DETAIL2 = "view_detail_2";
    public static final String VIEW_DETAIL3 = "view_detail_3";
    public static final String VIEW_DETAIL4 = "view_detail_4";
    public static final String VIEW_DETAIL5 = "view_detail_5";
    public static final String VIEW_DETAIL6 = "view_detail_6";
}
