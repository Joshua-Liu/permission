package android.by.com.permission.pref;

/**
 * Created by hengfengtian on 10/13/15.
 */

import java.util.HashSet;

/**
 *  Essential key will be saved in  SQLite and need be used by {@link android.by.com.permission.util.PrefEssentialUtil}
 *
 */

public class PrefEssentialKeys {

    public static final HashSet<String> sEssentialKeys = new HashSet<String>() {{
        add(SEATTLE_COOKIE);
        add(SEATTLE_SECRET);
        add(SEATTLE_VOIP_COOKIE);
        add(TOKEN_EDEN);
        add(SEATTLE_TICKET);
        add(SEATTLE_ACCESS_TOKEN);
        add(APK_VERSION);
        add(APK_LAST_VERSION);
        add(ENABLE_PRIVACY);
        add(NEED_SHOW_LANDING_PAGE);
        add(USER_IDENTIFIER);
        add(TOUCHPAL_PHONENUMBER_ACCOUNT);
        add(NETWORK_POSTKIDS_PAIR);
        add(ESSENTIAL_VERSION);
        add(VOIP_C2C_MODE_ON);
    }};

    public static final String SEATTLE_COOKIE = "seattle_tp_cookie";
    public static final String SEATTLE_SECRET = "seattle_tp_secret";
    // used by seattle
    public static final String SEATTLE_VOIP_COOKIE = "seattle_voip_cookie";
    public static final String TOKEN_EDEN = "eden_tp_cookie";

    public static final String SEATTLE_TICKET = "seattle_tp_ticket";
    public static final String SEATTLE_ACCESS_TOKEN = "seattle_tp_access_token";

    public static final String APK_VERSION = "apk_version";
    public static final String APK_LAST_VERSION = "apk_last_version";

    public static final String ENABLE_PRIVACY = "enable_privacy";
    public static final String NEED_SHOW_LANDING_PAGE = "need_show_landing_page";

    public static final String USER_IDENTIFIER = "user_identifier";

    public static final String TOUCHPAL_PHONENUMBER_ACCOUNT = "touchpal_phonenumber_account";

    public static final String NETWORK_POSTKIDS_PAIR = "network_postkids_pair";

    public static final String ESSENTIAL_VERSION = "essential_version";

    public static final String VOIP_C2C_MODE_ON = "voip_c2c_mode_on";
    public static final String HUAWEI_PUSH_TOKEN = "huawei_push_token";
    public static final String XIAOMI_PUSH_TOKEN = "xiaomi_push_token";
    public static final String CLIENT_ENV_HEADER = "client_env_header";

    public static final String USER_ID = "user_id";

    public static final String HAS_CHAT_LIST_MIGRATION = "has_chat_list_migration";



}
