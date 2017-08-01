/**
 * Copyright(c) 2013 CooTek
 * All rights reserved.
 */
package android.by.com.permission.constant;

/**
 * Statistic keys in usage data.
 * 
 * @author ThomasYe
 */
public class StatConst {


    static final String USAGE_TYPE = "dialer_new";
    static final String TIMESTAMP = "timestamp";

    // NETWORK
    public static final String PATH_NETWORKUDP = "path_networkudp";
    public static final String NETWORK_NORMAL = "normal"; //tcp time
    public static final String NETWORK_UDP_IP_ADDRESS = "udp_ip"; // udp ip
    public static final String NETWORK_UDP_DURATION = "udp_duration"; // udp time
    public static final String NETWORK_CALL_TYPE = "call_type";
    public static final String NETWORK_DEVICE_INFO = "device_info";

    public static final String NETWORK_OPERATOR_SLOT1 = "network_operator_slot1";
    public static final String NETWORK_OPERATOR_SLOT2 = "network_operator_slot2";
    public static final String NETWORK_CALLERID_NUMBER = "callerid_number";
    public static final String NETWORK_UPD_VERSION = "udp_version";
    public static final String NETWORK_UPD_RESULT = "udp_result";
    public static final String NETWORK_UDP_START = "udp_start";
    public static final String NETWORK_UDP_END = "udp_end";
    public static final String NETWORK_TCP_RESULT = "tcp_result";
    public static final String NETWORK_TCP_START = "tcp_start";
    public static final String NETWORK_TCP_END = "tcp_end";
    public static final String NETWORK_TYPE_START = "network_type_start";
    public static final String NETWORK_TYPE_END = "network_type_end";
    public static final String NETWORK_AVAILABLE_START = "network_available_start";
    public static final String NETWORK_AVAILABLE_END = "network_available_end";
    public static final String NETWORK_CALLERID_ISDUAL = "network_isdual";
    public static final String NETWORK_UDP_EXCEPTION = "udp_exception";
    public static final String NETWORK_TCP_EXCEPTION = "tcp_exception";
    public static final String NETWORK_LOCATION = "location";
    public static final String NETWORK_TOAST_NEED_SHOW = "toast_need_show";
    public static final String NETWORK_TOAST_MAIN = "toast_main";
    public static final String NETWORK_TOAST_ALT = "toast_alt";
    public static final String NETWORK_TOAST_NOTE = "toast_note";
    public static final String NETWORK_TOAST_CALLERLOGO = "toast_callerlogo";
    public static final String NETWORK_TOAST_CALL_TIME = "call_time";
    public static final String NETWORK_TOAST_INCOMING_CONNECT_TIME = "incoming_connetct_time";
    public static final String NETWORK_TOAST_HANGUP_TIME = "hangup_time";
    public static final String NETWORK_CALLERID_COMPARE = "callerid_compare";
    public static final String NETWORK_START_CALL_TIME = "start_call_time";
    public static final String NETWORK_START_CLOUD_ANIMATION = "start_cloud_animation";
    public static final String NETWORK_SHOW_NO_NETWORK = "show_no_network";
    public static final String NETWORK_SHOW_NETWORK_POOL = "show_network_pool";
    public static final String NETWORK_SHOW_ONLINE_CALLERID_RESULT = "show_online_callerid_result";
    public static final String NETWORK_SHOW_NETWORK_ERROR = "show_network_error";
    public static final String NETWORK_SHOW_LOCAL_CALLERID_RESULT = "show_local_callerid_result";
    public static final String NETWORK_NO_SEARCH_RESULT = "no_search_result";
    public static final String TOAST_GUIDE_SHOW = "toast_guide_show";
    public static final String TOAST_GUIDE_OPEN = "toast_guide_open";
    public static final String TOAST_GUIDE_CANCEL = "toast_guide_cancel";


    // WebSearch
    public static final String PATH_WEBSEARCH_SCENARIO = "path_websearch_scenario";

    public static final String WEBSEARCH_POSITION_COST_PREFIX = "websearch_position_cost_2_"; //key + provider, value == cost
    public static final String WEBSEARCH_POSITION_COST_TIMEOUT = "websearch_position_cost_timeout_2"; //key, value == cost
    public static final String WEBSEARCH_POSITION_COST_FAILED = "websearch_position_cost_failed_2"; //key, value == cost

    public static final String PATH_COUPON_SHOWN_ON_CALL = "path_coupon_shown_on_call"; //coupon shown in calllog list, +1 on call
    public static final String PATH_COUPON_SHOWN_IN_DETAIL = "path_coupon_shown_in_detail"; //coupon shown in calllog detail
    public static final String COUPON_SHOWN_IN_DETAIL_LEVEL1 = "coupon_shown_in_detail_level1"; //item without no slots or commercial
    public static final String COUPON_SHOWN_IN_DETAIL_LEVEL2 = "coupon_shown_in_detail_level2"; //item include slots
    public static final String COUPON_SHOWN_IN_DETAIL_LEVEL3 = "coupon_shown_in_detail_level3"; //item include commercial
    public static final String PATH_COUPON_NAVIGATE_ONLINE = "path_coupon_navigate_online"; //coupon navigate to online, key = url, +1 when click slot

    // Check Areacode
    public static final String CHECK_AREACODE = "areacode"; // 猜到的区号
    public static final String PATH_LOCALEINFO = "path_localeinfo";
    public static final String CELL_CID = "cid"; // 基站信息
    public static final String CELL_LAC = "lac";// 基站信息
    public static final String CELL_BASE_ID = "base_id"; // 电信基站信息
    public static final String CHECK_SOURCE = "source";// 表示区号来源
    public static final String CHECK_CITY = "city"; // 猜到的城市
    public static final String CHECK_LATITUDE = "latitude";// 经纬度
    public static final String CHECK_LONGITUDE = "longitude";
    public static final String PATH_AREACODE_FAILED = "path_areacodefailed";
    @Deprecated // and change it to private
    @SuppressWarnings("unused")
    private static final String FAILED_REASON = "failed_reason";

    // MNC
    public static final String PATH_MNC = "path_mnc";
    public static final String SIM_ERROR = "sim_error";//sim 运营商
    public static final String NETWORK_ERROR = "network_error"; // 当前网络运营商

    //Toast
    public static final String PATH_TOAST = "path_toastshow";//
    public static final String START_RECORD = "start_record";//点录音
    public static final String FUNC_BAR_DISPLAY = "func_bar_display";// 点击功能栏收起或者展开
    public static final String CLOSE_TOAST = "close_toast";//点击关闭toast
    public static final String MOVE_TOAST = "move_toast";//是不是移动过toast
    public static final String LOCK_TOAST = "lock_toast";
    public static final String UNLOCK_TOAST = "unlock_toast";
    public static final String BUTTON_CLICK = "button_click";//统计保存联系人是不是点过
    public static final String TOAST_SETTING = "toast_setting"; // toast设置
    public static final String AUTO_RECORD = "auto_record";//自动录音设置
    public static final String IS_AUTO_RECORD = "is_auto_record";//录音时是否是自动录音
    public static final String RECORD_ENABLE = "record_enable";//是否开启录音功能
    public static final String TOAST_ENTER_NORMAL = "toast_enter_normal"; //从正常设置页面进入悬浮框设置页面
    public static final String TOAST_OPEN_NORMAL = "toast_open_normal";   // 从正常设置页面进入悬浮框设置页面,打开悬浮框设置总开关
    public static final String TOAST_FIRST_OPEN = "toast_first_open";

    //RECORD PLAY
    public static final String PATH_RECORD = "path_recordnew";//
    public static final String RECORD_PLAY = "record_play";//录音播放
    public static final String MORE_BUTTON = "more_button";//有没有点击更多
    public static final String RECORD_RESOURCE_FORBID = "record_resource_forbid";//录音的单双通道

    //upload authority app info
    public static final String PATH_MARKET = "path_market";
    public static final String AUTHORITY_APP = "authority_app";//权限管理应用

    //call note info
    public static final String PATH_CALLNOTE = "path_callnotenew";
    public static final String CONTENT = "content";

    //MARK CALLER
    public static final String PATH_MARKCALLER = "path_mark";
    public static final String MARK_BUTTON_CLICK = "mark_button_click";
    public static final String MANUAL_BUTTON_CLICK = "manual_button_click";
    public static final String MARK_ENTER = "mark_enter";
    public static final String MISS_MARK = "miss_mark";
    public static final String RE_MARK = "re_mark";
    public static final String CALLLOG_TYPE = "calllog_type";

    //notebook
    public static final String TODO_ENTRY = "todo_entry";
    public static final String TODO_ENTRY_DIALER_CONTACT = "todo_entry_dialer_contact";
    public static final String TODO_ENTRY_DIALER_STRANGER = "todo_entry_dialer_stranger";
    public static final String TODO_ENTRY_MAIN = "todo_entry_main";
    public static final String TODO_ENTRY_CONTACT = "todo_entry_contact";
    public static final String TODO_ENTRY_MISSED_CALL = "todo_entry_missed_call";
    public static final String TODO_ENTRY_NOTIFICATION = "todo_entry_notification";
    public static final String TODO_ENTRY_CALL_NOTE = "todo_entry_call_note";


    /* ==========voip========== */
    //add for Pro
    public static final String PATH_VOIP = "path_voip";

    //add for bug 32676
    public static final String VOIP_ONCALL_STATE = "voip_oncall_state";

    //add for 4.8.2.3
    public static final String VOIP_BUY_NOTIFICATION_CLICK = "voip_buy_notification_click";

    //add for recommend webview
    public static final String RECOMMEND_DOWNLOAD_APP_NAME = "recommend_file_download_app";
    public static final String RECOMMEND_DOWNLOAD_APP_RESULT = "recommend_file_download_app_result";

    public static final String VOIP_INAPP_SET_NETWORK_CLICK_OK = "voip_inapp_set_network_click_ok";
    public static final String VOIP_INAPP_SET_NETWORK_CLICK_CANCEL = "voip_inapp_set_network_click_cancel";
    public static final String VOIP_INAPP_SET_NETWORK_CLICK_BACK = "voip_inapp_set_network_click_back";

    public static final String VOIP_PKG_INFO_NETWROK_TYPE = "voip_pkg_info_network_type";

    public static final String VOIP_WITHOUT_NETWORK_ACTION = "voip_without_network_action";
    public static final String VOIP_FIRST_USE_WITHOUT_NETWORK_ACTION = "voip_first_without_network_action";
    public static final String VOIP_ACTION_SET_NETWORK = "voip_set_network";
    public static final String VOIP_ACTION_CHANGE_COMMON = "voip_action_change_common";
    public static final String VOIP_ACTION_CANCEL = "voip_action_cancel";

    public static final String VOIP_VERSION = "voip_version";
    public static final String VOIP_FIRST_USE_NETWORK = "voip_first_use_network";



    //add for c2c
    public static final String PATH_VOIP_C2C = "path_voipc2c";
    public static final String VOIP_NETWORK = "network";
    public static final String VOIP_NETNAME = "netname";
    public static final String VOIP_LOSS = "loss";
    public static final String VOIP_SELECT_EDGE = "select_edge";
    public static final String VOIP_ENDPOINT_TYPE = "endpoint";
    public static final String VOIP_CALL_TYPE = "calltype";
    public static final String VOIP_CALLBACK_SWITCH = "switch2callback";
    public static final String VOIP_INTL_ROAMING = "intl_roaming";
    public static final String VOIP_ENDPOINT_TYPE_CALLER = "caller";

    public static final String VOIP_C2C_CALL_RING = "voip_c2c_call_ring";
    public static final String VOIP_C2C_CALL_INCOMING_ANSWER = "voip_c2c_call_incoming_answer";
    public static final String VOIP_C2P_CALL_INIT = "voip_c2p_call_init";
    public static final String VOIP_C2P_CALL_RING = "voip_c2p_call_ring";
    public static final String VOIP_C2C_USER_HANGUP = "voip_c2c_user_hangup";
    public static final String VOIP_USER_CALL_CONFIRMED = "voip_user_call_confirmed";

    // share entries
    public static final String VOIP_C2C_SHARE_FROM_WEB = "web";
    public static final String VOIP_C2C_SHARE_FROM_DISCONNECT = "disconnect";
    public static final String VOIP_CALLBACK_SHARE_FROM_OUTGOING = "outgoing";
    public static final String VOIP_C2C_SHARE_FROM_SETTING = "setting";

    public static final String VOIP_AUTO_ANSWER_RESULT = "auto_answer_result";
    public static final String VOIP_AUTO_ANSWER_SUCCESS = "success";
    public static final String VOIP_AUTO_ANSWER_FAILED = "failed";
    public static final String VOIP_BUILD_VERSION = "version";
    public static final String VOIP_C2C_ERROR = "voip_c2c_error";


    public static final String VOIP_CALL_DIRECT = "voip_call_direct";
    public static final String VOIP_CALL_DIRECT_RING = "voip_call_direct_ring";
    public static final String VOIP_CALL_DIRECT_CONNECT = "voip_call_direct_connect";
    public static final String VOIP_CALL_SOUND_SETTING = "voip_call_sound_setting";
    public static final String VOIP_C2P_C2C_INFO = "voip_c2p_c2c_info";
    public static final String VOIP_C2P_C2C_INFO_RESPONSE = "voip_c2p_c2c_info_response";
    public static final String VOIP_CALL_STATE = "voip_call_state";

    public static final String VOIP_CALLBACK_INCOMINGCALL = "voip_callback_incomingcall";
    public static final String KEY_CALLID = "callId";
    public static final String KEY_DISPLAY = "display";
    public static final String KEY_TYPE = "type";
    public static final String KEY_ENABLE = "enable";
    public static final String KEY_ACCOUNT= "account";
    public static final String KEY_CALLER = "caller";
    public static final String KEY_CALLEE = "callee";
    public static final String KEY_INDENTIFY = "indentify";
    public static final String KEY_REASON = "reason";
    public static final String KEY_CALLSTATE= "state";
    public static final String KEY_NETWORK= "network";
    public static final String KEY_MODE = "mode";

    public static final String BUILD_MODEL = "model";
    public static final String BUILD_MANUFATOR = "manufacturer";

    /* ==========voip========== */

    //daoliu
    public static final String PATH_WEBPAGE_FLOW_BEFORE_DIALER_SHOW = "path_webpage_flow_before_dialer_show";
    public static final String PATH_WEBPAGE_FLOW_BEFORE_DIALER_CLICK = "path_webpage_flow_before_dialer_click";
    public static final String RECOMMENDATION_ID = "recommendation_id";
    public static final String RECOMMENDATION_TITLE = "recommendation_title";
    public static final String RECOMMENDATION_TYPE = "recommendation_type";
    public static final String RECOMMENDATION_LINK = "recommendation_link";


    /**
     * new add for preinstall v5.1.5
     */
    //path_toastshow
    public static final String INAPP_APP_UPDATE_SHOW_NETWORK_STATE = "inapp_app_update_show_network_state";
    public static final String INAPP_APP_UPDATE_CLICK_NETWORK_STATE = "inapp_app_update_click_network_state";
    public static final String INAPP_APP_UPDATE_CANCEL_NETWORK_STATE = "inapp_app_update_cancel_network_state";
    /**
     * end of 5.1.5
     */

    //add for VPNM
    public static final String PATH_VPMN = "path_vpmn";

    public static final String PATH_SMS_ANALYZE = "path_sms_analyze";
    public static final String SMS_ANALYZE_RECEIVED_CONTENT = "received_content";
    public static final String SMS_ANALYZE_NET_STATUS = "net_status";
    public static final String SMS_ANALYZE_SYNC_RESULT = "sync_result";
    public static final String SMS_ANALYZE_ERROR = "sms_analyze_error";
    public static final String SMS_ANALYZE_ERROR2 = "sms_analyze_error2";
    public static final String SMS_ANALYZE_DATASEND_ERROR = "sms_analyze_datasend_error";

    //sms send strategy change
    public static final String PATH_MESSAGE_QUERY ="path_message_query";
    public static final String MESSAGE_QUERY_V2= "message_query_v2";

    public static final String PATH_UPLOAD_CALLLOG = "path_upload_calllog";
    public static final String DATE_DURATION_SAME = "date_duration_same";

    public static final String PATH_UPDATE = "path_date";
    public static final String INFO_FROM_PRESENTATION = "info_from_presentation";
    public static final String DOWNLOAD_START = "download_start";
    public static final String DOWNLOAD_FINISH = "download_finish";
    public static final String MANUAL_CHECK_UPDATE = "manual_check_update";

    public static final String PATH_PRE_SUPER_SEARCH = "path_presearch";
    public static final String DIALER_TAB_CALL = "dialer_tab_call";
    public static final String CONTACT_TAB_CALL = "contact_tab_call";


    public static final String PATH_CALLERID = "path_callerid";
    public static final String CALLERID_DB_COUNT = "callerid_db_count";


    //personal center
    public static final String PATH_PERSONAL_CERNTER = "path_personal_center";
    public static final String OPERATION_CLICK = "operation_click";
    public static final String CENTER_CLICK_LOGOUT_CONFIRM = "center_click_logout_confirm";
    public static final String CENTER_LOGOUT_NUMBER = "center_logout_number";
    public static final String CENTER_GETAUTH_CODE = "center_getauth_code";

    public static final String PATH_LOGIN = "path_login";
    public static final String LOGIN_LAUNCH = "launch";
    public static final String LOGIN_GREETING = "greeting";
    public static final String LOGIN_START_USE = "login_start_use";
    public static final String LOGIN_FROM = "from";
    public static final String LOGIN_BACK = "back";
    public static final String LOGIN_BACK_DIALOG_CONTINUE = "dialog_continue";
    public static final String LOGIN_BACK_DIALOG_EXIT = "dialog_exit";
    public static final String LOGIN_BACK_DIALOG_CANCEL = "dialog_cancel";
    public static final String LOGIN_INPUT_PHONE = "input_phone";
    public static final String LOGIN_AUTO_PHONE = "auto_phone";
    public static final String LOGIN_INPUT_AUTHCODE = "input_authcode";
    public static final String LOGIN_GET_AUTHCODE = "get_authcode";
    public static final String LOGIN_GET_AUTHCODE_RESULT = "get_authcode_result";
    public static final String LOGIN_GET_VOICE_AUTHCODE = "get_voice_authcode";
    public static final String LOGIN_GET_VOICE_AUTHCODE_RESULT = "get_voice_authcode_result";
    public static final String LOGIN_SUBMIT = "submit";
    public static final String LOGIN_RESULT = "result";

    //personal center v6
    public static final String PATH_PERSONCENTRE_VERSIONSiXLATER = "path_personcentre_versonsixlater";
    public static final String PATH_PERSONCENTRE_VERSIONSiXLATER_SHOW = "path_personcentre_versonsixlater_show";
    public static final String PATH_PERSONCENTRE_VERSIONSiXLATER_BANNERSHOW = "path_personcentre_versonsixlater_bannershow";
    public static final String PATH_PERSONCENTRE_VERSIONSiXLATER_MINIBANNERSHOW = "path_personcentre_versonsixlater_minibannershow";
    public static final String PATH_PERSONCENTRE_VERSIONSiXLATER_SECTIONSHOW = "path_personcentre_versonsixlater_sectionshow";
    public static final String PATH_PERSONCENTRE_VERSIONSiXLATER_SECTIONCLICK = "path_personcentre_versonsixlater_sectionclick";
    public static final String PATH_PERSONCENTRE_VERSIONSiXLATER_BANNERCLICK = "path_personcentre_versonsixlater_bannerclick";
    public static final String PATH_PERSONCENTRE_VERSIONSiXLATER_MINIBNNERCLICK = "path_personcentre_versonsixlater_minibannerclick";


    //activity center
    public static final String PATH_ACTIVITY_CENTER = "path_personal_center";
    public static final String ACTIVITY_CENTER_ENTRANCE = "activity_center_entrance";

    public static final String PATH_OFFLINE_CALLERID = "path_offline_callerid";
    public static final String DOWNLOAD_IN_FREE_TIME = "download_in_free_time";
    public static final String CALLERID_PACKAGE_NUMBER = "callerid_package_number";

    //supersearch
    public static final String PATH_SUPERSEARCH = "path_supersearch";
    public static final String PATH_WEBLOAD = "path_webload";
    public static final String SUPERSEARCH_ENTER = "supersearch_enter";
    public static final String SUPERSEARCH_INPUT = "supersearch_input";
    public static final String SUPERSEARCH_ENTER_FROM = "supersearch_enter_from";
    public static final String CONTACT_CLICK = "contact_click";
    public static final String CONTACT_PULLDOWN = "contact_pulldown";
    public static final String DIALER_CLICK = "dialer_click";
    public static final String DIALER_PULLDOWN = "dialer_pulldown";
    public static final String WEBSEARCH_CLICK = "websearch_click";
    public static final String WEBSEARCH_PULLDOWN = "websearch_pulldown";
    public static final String WEBSEARCH_JS = "websearch_js";
    public static final String LISTITEM_CLICK = "listitem_click";
    public static final String CONTACT_OR_CALLLOG = "contact_or_calllog";
    public static final String SHOP_INFO = "shop_info";
    public static final String APP = "app";
    public static final String SERVICE_INFO = "service_info";
    public static final String RECOMEND_TITLE_CLICK = "recomend_title_click";
    public static final String SUPERSEARCH_STAY_TIME = "supersearch_stay_time";
    public static final String SUPERSEARCH_HAS_SERVICE_SELECT = "supersearch_has_service_select";
    public static final String NETWORK_TYPE = "network_type";
    public static final String SHOP_CLICK_EXTRA_INFO = "shop_click_extra_info";
    public static final String SERVICE_CLICK_EXTRA_INFO = "service_click_extra_info";
    public static final String CHILD_POSITION = "child_position";
    public static final String SUPERSEARCH_KEY = "supersearch_key";
    public static final String REMOTE_SEARCH_INFO = "remote_search_info";
    public static final String SEARCH_TIME = "search_time";
    public static final String RESULT_EMPTY = "result_empty";
    public static final String SEARCH_SEQUENCE_KEY = "search_sequence_key";
    public static final String SUPERSEARCH_ERROR = "supersearch_error";
    public static final String SEARCH_ERROR_CODE = "search_error_code";
    public static final String SEARCH_CITY = "search_city";
    public static final String LINK = "link";
    public static final String EXTERNAL_LINK = "external_link";

    //takeover
    public static final String PATH_TAKEOVER = "path_takeover";
    public static final String TAKEOVER_DIALER_ON_IN_TAKEOVERWIZARD = "takeover_dialer_on_in_takeoverwizard";
    public static final String TAKEOVER_DIALER_CLOSE_IN_TAKEOVERWIZARD = "takeover_dialer_close_in_takeoverwizard";
    public static final String TAKEOVER_DIALER_ON_IN_ACTIVITYCENTER = "takeover_dialer_on_in_activitycenter";
    public static final String TAKEOVER_DIALER_ON_IN_DEFAULTACTIVITY = "takeover_dialer_on_in_defaultactivity";
    public static final String TAKEOVER_DIALER_OFF_IN_DEFAULTACTIVITY = "takeover_dialer_off_in_defaultactivity";
    public static final String TAKEOVER_DIALER_ON_IN_NOTIFICATION = "takeover_dialer_on_in_notification";
    public static final String TAKEOVER_DIALER_OFF_IN_NOTIFICATION = "takeover_dialer_off_in_notification";
    public static final String TAKEOVER_CONTACT_ON_IN_DEFAULTACTIVITY = "takeover_contact_on_in_defaultactivity";
    public static final String TAKEOVER_CONTACT_OFF_IN_DEFAULTACTIVITY = "takeover_contact_off_in_defaultactivity";
    public static final String TAKEOVER_CONTACT_ON_IN_NOTIFICATION = "takeover_contact_on_in_notification";
    public static final String TAKEOVER_CONTACT_OFF_IN_NOTIFICATION = "takeover_contact_off_in_notification";
    public static final String TAKEOVER_DIALER_WHEN_HANGUP_OUTGOING = "takeover_dialer_when_hangup_outgoing";

    public static final String PATH_WS2_PROXY = "path_proxy";
    public static final String HOST_NAME_ERROR = "host_name_error";
    public static final String HOST_NAME_STATUS_ERROR = "host_name_status_error";

    public static final String PATH_SMS_OBSOLETE = "path_sms_obsolete";
    public static final String OBSOLETE_COUNT = "count";
    public static final String FIRST_TIME = "first_time";

    public static final String PATH_DISCONNECT_COMMERCIAL = "path_disconnect_commercial";
    public static final String PATH_P2P_DISCONNECT_COMMERCIAL = "path_p2p_disconnect_commercial";
    public static final String PATH_FEEDS_STARTUP_COMMERCIAL = "path_feeds_startup_commercial";
    public static final String PATH_MULTI_MEDIA_COMMERCIAL = "path_multi_media_commercial";
    public static final String PATH_DIAL_COMMERCIAL = "path_dial_commercial";
    public static final String PATH_DIRECT_COMMERCIAL = "path_direct_commercial";
    public static final String PATH_STARTUP_COMMERCIAL = "path_startup_commercial";
    public static final String COMMERCIAL_SHOW = "commercial_show";
    public static final String COMMERCIAL_CLICK = "commercial_click";
    public static final String COMMERCIAL_DURATION = "commercial_duration";
    public static final String COMMERCIAL_REQUEST = "commercial_request";
    public static final String COMMERCIAL_APP_DOWNLOADED = "commercial_app_downloaded";
    public static final String FINISH = "finish";
    public static final String ERROR_SHOW = "error_show";
    public static final String CALLBACK = "callback";
    public static final String COMMERCIAL_LOCATION = "commercial_location";
    public static final String COMMERCIAL_CITY = "commercial_city";
    public static final String COMMERCIAL_ADDRESS = "commercial_address";
    public static final String COMMERCIAL_LATITUDE = "commercial_latitude";
    public static final String COMMERCIAL_LONGITUDE = "commercial_longitude";
    public static final String COMMERCIAL_PRESHOW_ID = "commercial_preshow_id";
    public static final String COMMERCIAL_PRESHOW_URI_VALID = "commercial_preshow_uri_valid";


    public static final String PATH_VOIP_ONGOING_COMMERCIAL = "path_voip_ongoing_commercial";

    public static final String PATH_PERMISSION = "path_permission";
    public static final String PATH_PERMISSION_DENIAL = "path_permission_denial";
    public static final String ROM = "rom";
    public static final String ROM_HUAWEI = "huawei";
    public static final String ROM_MIUIV5 = "miuiv5";
    public static final String ROM_MIUIV6 = "miuiv6";
    public static final String INSTALL_TYPE = "install_type";
    public static final String PERMISSION_GUIDE_VISITED = "permission_guide_visited";
    public static final String PERMISSION_EXIT_STATE = "permission_exit_state";
    public static final String STARTUP_CONFIRM_DIALOG = "startup_confirm_dialog";
    public static final int STARTUP_CONFIRM_DIALOG_ACTION_NEGTIVE = 1;
    public static final int STARTUP_CONFIRM_DIALOG_ACTION_POSITIVE = 2;
    public static final int STARTUP_CONFIRM_DIALOG_ACTION_CANCEL = 3;
    public static final String CALL_PERMISSION_DENIAL = "call_permission_denial";
    public static final String CALLLOG_PERMISSION_DENIAL = "calllog_permission_denial";
    public static final String CONTACT_PERMISSOIN_DENIAL = "contact_permission_denial";

    public static final String PATH_DB_UPGRADE_ERROR = "path_db_upgrade_error";
    public static final String OLD_VERSION = "old_version";
    public static final String OLD_APP_VERSION = "old_app_version";
    public static final String OLD_DB_COLUMNS = "old_db_columns";

    public static final String PATH_CALLBACK = "path_callback";
    public static final String CALLBACK_OK = "callback_ok";
    public static final String CALLBACK_CANCEL = "callback_cancel";

    public static final String PATH_VOIP_IPC = "path_voip_ipc";
    public static final String UPDATE_BONUS = "update_bonus";

    //Event Sequence analysis
    public static final String PATH_EVENT_SEQUENCE = "path_event_sequence";
    public static final String PATH_USAGE_SEQUENCE = "path_usage_sequence";
    public static final String KEY_USAGE_SEQUENCE = "usage_id";
    public static final String ID_OF_CLICK_ICON = "000";  // 点击打开
    public static final String ID_OF_FIRST_GUIDE = "009";  // 结束首次引导
    public static final String ID_OF_STARTUP_AD = "019";  // 展示开屏广告
    public static final String ID_OF_SKIP_REGISTER = "028";  // 跳过注册
    public static final String ID_OF_FINISH_LOGIN = "029";  // 完成注册
    public static final String ID_OF_APPEAR_PERMISSION = "037"; //出现权限引导
    public static final String ID_OF_SKIP_PERMISSION = "038"; //跳过权限引导
    public static final String ID_OF_PERMISSION_GUIDE = "039";  // 完成权限引导
    public static final String ID_OF_DUALSIM_POPUP = "045";  // 双卡弹框
    public static final String ID_OF_DUALSIM_MANUAL_CLOUD_ADAPTION = "047";  // 双卡手动云识别
    public static final String ID_OF_DUALSIM_MANUAL_ADAPTION = "049";  // 双卡手动本地适配
    public static final String ID_OF_CALLLOG_ZERO_CLICK = "059";  // 点击无通话记录引导
    public static final String ID_OF_CONTACT_ZERO_CLICK = "069";  // 点击无联系人引导
    public static final String ID_OF_FREE_CALL = "079";  // 拨打免费电话
    public static final String ID_OF_CRASH = "089";  // 发生crash
    public static final String ID_OF_APPLICATION_BACKGROUND = "099";  // 退到后台


    // registered or new register
    public static final String PATH_REGISTER_TIME = "path_register_time";
    public static final String IS_REZGISTERED = "is_registered";
    public static final String IS_NEW_REGISTER = "is_new_register";


    public static final String PATH_LOGOUT = "path_logout";
    public static final String POPUP_API = "popup_api";
    public static final String POPUP_TOKEN = "popup_token";
    public static final String POPUP_RESULT = "popup_result";
    public static final String POPUP_RESPONSE = "popup_response";
    public static final String MANUAL_LOGOUT = "manual_logout";
    public static final String LOGOUT_FAILURE_TOKEN = "logout_failure_token";
    public static final String LOGOUT_FAILURE_CODE = "logout_failure_code";


    public static final String PATH_KEYBOARD_SEARCH = "path_keyboard_search";
    public static final String SEARCH_SELECT = "search_select";
    public static final String SHOW_GUIDE_TYPE = "show_guide_type";
    public static final String SKIP_GUIDE_TYPE = "skip_guide_type";
    public static final String GUIDE_TYPE_CONTACT = "contact";
    public static final String GUIDE_TYPE_MERCHANT = "merchant";

    public static final String PATH_ENCRYPT_LOGIN = "path_encrypt_login";
    public static final String ENCRYPT_LOGIN_SUCCESS = "encrypt_login_success";
    public static final String ENCRYPT_LOGIN_HTTPS_FAILED = "encrypt_login_https_failed";


    public static final String OUTGOING_PAGE_FINISH_KEY = "finish";
    public static final String OUTGOING_PAGE_FINISH_ON_SERVICE_CONNECTED_CRASH = "service_connected_crash";
    public static final String OUTGOING_PAGE_FINISH_ON_HANGUP_CRASH = "hangup_crash";
    public static final String OUTGOING_PAGE_FINISH_COMMENT = "comment";
    public static final String OUTGOING_PAGE_FINISH_START_DISCONNECT_PAGE = "start_disconnect_page";
    public static final String OUTGOING_PAGE_CLICK_ADD_RELATIVE_PHONE = "click_add_relative_phone";


    public static final String OUTGOING_COMMENT_KEY = "comment";
    public static final String OUTGOING_COMMENT_ALREADY_FINISH = "already_finish";

    public static final String PATH_ONLINE_SKIN = "path_online_skin";
    public static final String ONLINE_SKIN_DOWNLOADED_ITEM = "online_skin_downloaded_item";
    public static final String ONLINE_SKIN_USED_ITEM = "online_skin_used_item";
    public static final String SKIN_WEB_TAB = "skin_web_tab"; //切到在线主题
    public static final String SKIN_LOCAL_TAB = "skin_local_tab"; //切到已下载
    public static final String SKIN_LOCAL_SKIN_USE = "skin_local_skin_use";// 已下载点击启用按钮
    public static final String SKIN_SHOW_ANIMATION = "skin_show_animation"; //显示皮肤动画


    public static final String PATH_CRASH = "path_crash";
    public static final String CRASH_TIMESTAMP = "crash_timestamp";
    public static final String CRASH_TYPE = "crash_type";
    public static final String CRASH_MESSAGE = "crash_message";

    public static final String PATH_PAY = "path_pay";
    public static final String ALI_PAY = "ali_pay";

    public static final String PATH_CONTACT = "path_contact";
    public static final String CONTACT_FAVOURITE = "contact_favourite";

    public static final String PATH_CONTACT_OR_CALLOG_ZERO = "path_contact_or_callog_zero";
    public static final String CONTACT_WEB_SHOW = "contact_web_show";
    public static final String CALLLOG_WEB_SHOW = "callog_web_show";

    public static final String PATH_LOCATION = "path_location";
    public static final String CITY = "city";
    public static final String LATITUDE = "latitude";
    public static final String LONGITUDE = "longitude";
    public static final String PROVIDER = "provider";
    public static final String HAS_ACCURACY = "has_accuracy";
    public static final String ACCURACY = "accuracy";

    //share package 576
    public static final String PATH_NEW_SHARE = "path_share_package";
    public static final String SHARE_QUERY_PACKAGE_ID = "package_id";
    public static final String SHARE_QUERY_ACTION = "action";
    public static final String SHARE_QUERY_ACTION_SUCCESS = "success";
    public static final String SHARE_QUERY_ACTION_FAIL = "fail";
    public static final String SHARE_QUERY_ACTION_CANCEL = "cancel";
    public static final String SHARE_QUERY_ACTION_CLICK_SHARE = "click_share";
    public static final String SHARE_QUERY_ACTION_CLICK_CANCEL = "click_cancel";
    public static final String SHARE_QUERY_SOURCE = "source";
    public static final String SHARE_QUERY_SOURCE_CANCEL = "cancel";

    //commercial call
    public static final String PATH_COMMERCIAL_CALL = "path_adudp";
    public static final String COMMERCIAL_CALL_CHANNEL = "c";
    public static final String COMMERCIAL_CALL_VERSION = "v";
    public static final String COMMERCIAL_CALL_TIMESTAMP = "prt";
    public static final String COMMERCIAL_CALL_MATERIAL_TYPE = "at";
    public static final String COMMERCIAL_CALL_AD_ID = "tu";
    public static final String COMMERCIAL_CALL_TEMPLATE_NAME = "tn";
    public static final String COMMERCIAL_CALL_CLIENT_IP = "ip";
    public static final String COMMERCIAL_CALL_AD_NUMBER = "adn";
    public static final String COMMERCIAL_CALL_TOKEN = "token";
    public static final String COMMERCIAL_CALL_AD_CLASS = "adclass";
    public static final String COMMERCIAL_CALL_NETWORK_TYPE = "nt";
    public static final String COMMERCIAL_CALL_REQUEST_TYPE = "rt";
    public static final String COMMERCIAL_CALL_WIDTH = "w";
    public static final String COMMERCIAL_CALL_HEIGHT = "h";
    public static final String COMMERCIAL_CALL_CITY = "city";
    public static final String COMMERCIAL_CALL_ADDRESS = "addr";
    public static final String COMMERCIAL_CALL_LONGITUDE = "longtitude";
    public static final String COMMERCIAL_CALL_LATITUDE = "latitude";
    public static final String COMMERCIAL_CALL_PHONE_NUMBER = "other_phone";
    public static final String COMMERCIAL_CALL_CALL_TYPE = "call_type";
    public static final String COMMERCIAL_CALL_SEARCHID = "s";
    public static final String COMMERCIAL_CALL_ADID = "adid";
    public static final String COMMERCIAL_CALL_UDP_RESPONSE_TIME = "ts";
    public static final String COMMERCIAL_CALL_ERROR_CODE = "err";
    public static final String COMMERCIAL_CALL_RESPONSE_TIME = "rs";

    //private contact
    public static final String PATH_PRIVATE_CONTACT = "path_private_contact";
    public static final String PRIVATE_CONTACT_PAGE_VISITED = "private_contact_page_visited";
    public static final String PRIVATE_CONTACT_REGISTER_STATE = "private_contact_register_state";
    public static final String PRIVATE_CONTACT_REGISTER_STATE_GUIDE = "guide";
    public static final String PRIVATE_CONTACT_REGISTER_STATE_PASSWORD = "password";
    public static final String PRIVATE_CONTACT_REGISTER_STATE_CONFIRM_PASSWORD = "confirm_password";
    public static final String PRIVATE_CONTACT_REGISTER_STATE_SECURITY = "security";
    public static final String PRIVATE_CONTACT_REGISTER_STATE_DIALER_GUIDE = "dialer_guide";
    public static final String PRIVATE_CONTACT_REGISTER_STATE_DIALER_GUIDE_ENTER = "dialer_guide_enter";
    public static final String PRIVATE_CONTACT_VPN_STATE = "private_contact_vpn_state";
    public static final String PRIVATE_CONTACT_ENTRANCE_STATE = "private_contact_entrance_state";
    public static final String PRIVATE_CONTACT_COUNT = "private_contact_count";
    public static final String PRIVATE_CONTACT_NAME = "private_contact_name";
    public static final String PRIVATE_CONTACT_TOAST_NAME = "private_contact_toast_name";
    public static final String PRIVATE_CONTACT_CALL_TYPE = "call_type";
    public static final String PRIVATE_CONTACT_CALL_TYPE_INCOMING = "incoming";
    public static final String PRIVATE_CONTACT_CALL_TYPE_OUTGOING = "outgoing";
    public static final String PRIVATE_CONTACT_CALL_TYPE_MISSED = "missed";
    public static final String PRIVATE_CONTACT_CALL_TIME = "call_time";
    public static final String PRIVATE_CONTACT_CALL_NAME = "call_name";
    public static final String PRIVATE_CONTACT_CALL_TOAST_NAME = "call_toast_name";
    public static final String PRIVATE_CONTACT_CALL_NUMBER = "call_number";
    public static final String PRIVATE_CONTACT_CALL_DURATION = "call_duration";
    public static final String PRIVATE_CONTACT_CALL_MEAN = "call_mean";
    public static final String PRIVATE_CONTACT_CALL_MEAN_NORMAL = "normal";
    public static final String PRIVATE_CONTACT_CALL_MEAN_VOIP = "voip";
    public static final String PRIVATE_CONTACT_CALL_MEAN_VPN = "vpn";
    public static final String PRIVATE_CONTACT_NOTIFICATION = "private_contact_notification";
    public static final String PRIVATE_CONTACT_EXISTS_IN_SYSTEM = "private_contact_exists_in_system";
    public static final String PRIVATE_CONTACT_INCOMING_CALL_OPERATION = "private_contact_incoming_call_operation";
    public static final String PRIVATE_CONTACT_ENTRANCE_NAME = "entrance_name";
    public static final String PRIVATE_CONTACT_VIEW_FEATURE = "view_feature";
    public static final String PRIVATE_CONTACT_VIEW_FEATURE_GUIDE = "guide";
    public static final String PRIVATE_CONTACT_VIEW_FEATURE_INAPP = "inapp";
    public static final String PRIVATE_CONTACT_VIEW_FEATURE_SETTING = "setting";
    public static final String PRIVATE_CONTACT_DELETE_CALLLOG_ADD_GUIDE = "delete_calllog_add_guide";
    public static final String PRIVATE_CONTACT_DELETE_CALLLOG_ADD_GUIDE_YES = "yes";
    public static final String PRIVATE_CONTACT_DELETE_CALLLOG_ADD_GUIDE_NO = "no";
    public static final String PRIVATE_CONTACT_DELETE_CALLLOG_ADD_GUIDE_CANCEL = "cancel";
    public static final String PRIVATE_CONTACT_DELETE_CALLLOG_ADD_GUIDE_ADDED = "added";

    public static final String PATH_BASE_INFO = "path_base_info";
    public static final String BASE_INSTALL_TYPE = "base_build_type"; //1:新装 2:升级
    public static final String BASE_MANUFACTURE = "base_manufacture";
    public static final String BASE_MODEL = "base_model";

    public static final String PATH_SURVEY = "path_survey";
    public static final String SURVEY_NUMBER = "survey_number";
    public static final String SURVEY_TAG = "survey_tag";
    public static final String SURVEY_NAME = "survey_name";

    public static final String PATH_DISCONNECT_INIT = "path_disconnect_init";
    public static final String INIT_PATH = "init_path";
    public static final int INIT_PATH_STEP_1 = 1;
    public static final int INIT_PATH_STEP_2 = 2;
    public static final int INIT_PATH_STEP_3 = 3;
    public static final int INIT_PATH_STEP_4 = 4;


    public static final String PATH_NETWORK_INFO = "path_network_info";
    public static final String INFO_REQUEST = "info_request";
    public static final String INFO_RESPONSE = "info_response";

    public static final String PATH_MAKE_CALL = "path_make_call";
    public static final String CALL_TYPE = "call_type";
    public static final String CALL_TYPE_VOIP = "voip";
    public static final String CALL_TYPE_NORMAL = "normal";
    public static final String INCOMING_CALL_TYPE = "incoming_call";
    public static final String MISSING_CALL = "missing_call";
    public static final String HANGUP_CALL = "hangup_call";
    public static final String C2C_INCOMING_CALL = "c2c_incoming_call";

    public static final String PATH_BLOCK = "path_block";
    public static final String BLOCK_SETTING = "block_setting";
    public static final String BLOCK_ACTION = "block_action";

    public static final String UPGRADE_BALANCE_EXCEPTION = "upgrade_balance_exception";

    public static final String PATH_COMMERCIAL_VIEW_CRASH = "path_commercial_view_crash";
    public static final String COMMERCIAL_CRASH_TYPE = "commercial_crash_type";
    public static final String COMMERCIAL_CRASH_MANUFACTURER = "commercial_crash_manufacturer";

    //in app startup permission view
    public static final String IN_APP_AUTO_STARTUP_CLICK = "in_app_auto_startup_click";
    public static final String PERMISSION_TYPE = "permission_type";
    public static final String ABOUT_PERMISSION_LINK = "about_permission_link";
    public static final String IN_APP_AUTO_STARTUP_SHOWN = "in_app_auto_startup_shown";

    //path_gesture
    public static final String PATH_GESTURE = "path_gesture";
    public static final String ENTER_GESTURE = "enter_gesture";
    public static final String ENABLE_GESTURE = "enable_gesture";
    public static final String SAVE_GESTURE = "save_gesture";
    public static final String USE_GESTURE_TO_DIAL = "use_gesture_to_dial";

    public static final String PATH_SECRET = "path_secret";
    public static final String SHOW_STARTUP_GUIDE_NEW = "show_startup_guide_new";
    public static final String SHOW_LOGIN_DIALOG_NEW = "show_login_dialog_new";
    public static final String INSTALL_SECRET = "install_secret";

    public static final String PATH_MERCHANT = "path_merchant";
    public static final String CLICK_MERCHANT_TYPE = "click_merchant_type";
    public static final int TYPE_LIST_ITEM = 0;
    public static final int TYPE_DIALOG = 1;
    public static final String CLICK_MERCHANT_NAME = "click_merchant_name";

    //path_sync
    public static final String PATH_SYNC = "path_sync";
    public static final String SYNC_SOURCE = "sync_source";
    public static final String SYNC_PERFORM_CALL = "sync_perform_call";

    //path_package
    public static final String PACKAGE_NAME = "package_name";

    //call from h5
    public static final String PATH_HTML_FEATURE_GUIDE_RECORDER = "path_html_feature_guide_recorder";
    public static final String LAUNCH_FROM_WEBVIEW = "webview";
    public static final String HTML_GUIDE_FEATURE_USED = "html_guide_feature_used";
    public static final String HTML_GUIDE_FEATURE_TURN_ON = "html_guide_feature_turn_on";
    public static final String ACTIVITY_LAUNCH_FROM = "activity_launch_from";

    public static final String EDEN_PATH_ACTIVE_IN = "eden_path_active_in";
    public static final String EDEN_PATH_ACTIVE_OUT = "eden_path_active_out";
    public static final String PAGE_NAME = "page_name";

    public static final String PATH_CONTACT_SNAPSHOT = "path_contact_snapshort";
    public static final String CONTACT_SNAPSHOT_METHOD1 = "contact_snapshot_method1";
    public static final String CONTACT_SNAPSHOT_METHOD2 = "contact_snapshot_method2";

    //path_feedback
    public static final String PATH_FEEDBACK_STEPS = "path_feedback_steps";
    public static final String ENTER_FEEDBACK = "enter_feedback";
    public static final String FEEDBACK_TYPE = "feedback_type";
    public static final String NEED_QUESTION = "need_question";
    public static final String NEED_FEEDBACK = "need_feedback";
    public static final String NEED_SUBMIT = "need_submit";

    //path_start time
    public static final String PATH_START_DURATION = "path_start_duration";
    public static final String MAINPAGE_DURATION = "mainpage_duration";
    public static final String CALLLOG_DURATION = "calllog_duration";

    //path_feedback
    public static final String PATH_FEEDBACK = "path_feedback";
    public static final String APP_FEEDBACK = "app_feedback";

    //path_noah_crash
    public static final String PATH_NOAH_CRASH = "path_noah_crash";
    public static final String APP_CRASH = "app_crash";

    //path app download recorder
    public static final String PATH_APP_DOWNLOAD = "path_app_download";
    public static final String APP_DOWNLOAD_START = "app_download_start";
    public static final String APP_DOWNLOAD_FINISHED = "app_download_finished";
    public static final String APP_DOWNLOAD_INSTALL_SUCCEED = "app_download_install_succeed";

    //contacts shift
    public static final String PATH_CONTACTS_SHIFT = "path_contacts_shift";
    public static final String CONTACTS_SHIFT_ENTRANCE_CLICK = "contacts_shift_entrance_click";
    public static final String CONTACTS_SHIFT_SEND_CLICK = "contacts_shift_send_click";
    public static final String CONTACTS_SHIFT_RECEIVE_CLICK = "contacts_shift_receive_click";
    public static final String CONTACTS_SHIFT_SEND_CONNECTION = "contacts_shift_send_connection";
    public static final String CONTACTS_SHIFT_SEND_CONNECT_SUCCESS = "contacts_shift_send_connect_success";
    public static final String CONTACTS_SHIFT_SEND_CONNECT_FAILED = "contacts_shift_send_connect_failed";
    public static final String CONTACTS_SHIFT_SEND_SUCCESS = "contacts_shift_send_success";
    public static final String CONTACTS_SHIFT_SEND_FAILED = "contacts_shift_send_failed";
    public static final String CONTACTS_SHIFT_RECEIVE_CONNECTION = "contacts_shift_receive_connection";
    public static final String CONTACTS_SHIFT_RECEIVE_CONNECT_SUCCESS = "contacts_shift_receive_connect_success";
    public static final String CONTACTS_SHIFT_RECEIVE_CONNECT_FAILED = "contacts_shift_receive_connect_failed";
    public static final String CONTACTS_SHIFT_RECEIVE_SUCCESS = "contacts_shift_receive_success";
    public static final String CONTACTS_SHIFT_RECEIVE_FAILED = "contacts_shift_receive_failed";
    public static final String CONTACTS_SHIFT_RETRY_INSERT_SUCCESS = "contacts_shift_retry_insert_success";
    public static final String CONTACTS_SHIFT_RETRY_INSERT_FAILED = "contacts_shift_retry_insert_failed";
    public static final String CONTACTS_SHIFT_INSERT_SUCCESS = "contacts_shift_insert_success";
    public static final String CONTACTS_SHIFT_INSERT_FAILED = "contacts_shift_insert_failed";
    public static final String CONTACTS_SHIFT_TIMEOUT = "contacts_shift_timeout";
    public static final String CONTACTS_SHIFT_WRONG_CONTACT_COUNTS = "contacts_shift_wrong_contact_counts";
    public static final String CONTACTS_SHIFT_WRONG_CONTENT_FORMAT = "contacts_shift_wrong_contact_format";
    public static final String CONTACTS_SHIFT_NO_SECURITY = "contacts_shift_no_security";
    public static final String CONTACTS_SHIFT_UNKNOWN_WRONG = "contacts_shift_unknown_wrong";
    public static final String CONTACTS_SHIFT_ERROR_INTERRUPT = "contacts_shift_error_interrupt";
    public static final String CONTACTS_SHIFT_INSERT_TIME = "contacts_shift_insert_time";

    //invite
    public static final String PATH_INVITE = "path_invite";
    public static final String INVITE_UNKNOWN_PERSON_CLICK = "invite_unknown_person_click";
    public static final String INVITE_KNOWNN_PERSON_CLICK = "invite_known_person_click";
    public static final String INVITE_VOIP_FRIEND_ACTIVITY = "invite_voip_friend_activity";

    //invite page starter
    public static final String PATH_INVITE_PAGE = "path_invite_page";
    public static final String INVITE_PAGE_FROM = "invite_page_from";


    //voip oversea
    public static final String PATH_VOIP_OVERSEA = "path_voip_oversea";
    public static final String VOIP_OVERSEA_VERIFY_RESULT = "verify_result";
    public static final String VOIP_OVERSEA_CALL_NUMBER = "call_number";
    public static final String VOIP_OVERSEA_MAIN_PAGE_VISITED = "main_page_visited";

    public static final String NOAH_INFO = "noah_info";
    public static final String NOAH_REVERSED_50 = "noah_reserve_50";
    public static final String PACKAGE_REMOVED = "removed";
    public static final String PACKAGE_ADDED = "added";

    //vip 576
    public static final String PATH_VIP = "path_vip";
    public static final String VIP_CLICK_ACTION = "action";
    public static final String VIP_CLICK_FREE_GET = "free_get";
    public static final String VIP_CLICK_LEARN_MORE = "learn_more";
    public static final String VIP_CLICK_GET_VIP = "get_vip";
    public static final String VIP_CLICK_RENEWAL = "renewal";
    public static final String VIP_DIRECTLY_CALL = "directly_call";
    public static final String VIP_CALL_BACK = "call_back";
    public static final String VIP_TASK_AD_ID = "ad_id";
    public static final String VIP_TASK_ACTION = "action";
    public static final String VIP_TASK_ACTION_QUIT = "quit";
    public static final String VIP_TASK_ACTION_CONTINUE = "continue";
    public static final String VIP_TASK_REDIAL = "redial";
    public static final String VIP_TASK_CALL_TYPE = "call_type";

    public static final String PATH_TASK_BONUS = "path_task_bonus";
    public static final String TASK_AD_ID = "ad_id";
    public static final String APPLY_TASK_BONUS_RESULT_CODE = "apply_bonus_result_code";
    public static final String APPLY_TASK_BONUS_RESULT = "apply_bonus_result";
    public static final String TASK_DOWNLOAD_APP_BEGIN = "download_begin";
    public static final String TASK_DOWNLOAD_APP_FINISH = "download_finish";
    public static final String TASK_RAW_DATA = "task_raw_data";

    public static final String PATH_TASK_REQUEST = "path_task_request";
    public static final String TASK_REQUEST_RESULT = "task_request_result";

    public static final String PATH_TASK_SHOW = "path_task_show";
    public static final String TASK_SHOW_STATE = "task_show_state";
    public static final String TASK_SHOW_STATE_PREPARE = "prepare";
    public static final String TASK_SHOW_STATE_READY = "ready";
    public static final String TASK_SHOW_STATE_SHOWN = "shown";


    /**
     * disconnect page entry since 5760
     */
    public static final String PATH_START_DISCONNECT_ENTRY = "path_start_disconnect_entry";
    public static final String START_DISCONNECT_ENTRY_HIDE_KEY = "hide";
    public static final String START_DISCONNECT_ENTRY_CALLBACK_FINISH_KEY = "callback_finish";
    public static final String START_DISCONNECT_ENTRY_COMMENT_STEP_FIRST_KEY = "comment_step_first";
    public static final String START_DISCONNECT_ENTRY_COMMENT_STEP_SECOND_KEY = "comment_step_second";
    public static final String START_DISCONNECT_ENTRY_COMMENT_STEP_THIRD_KEY = "comment_step_third";
    public static final String START_DISCONNECT_ENTRY_COMMENT_STEP_FORTH_KEY = "comment_step_forth";

    public static final String PATH_PERMISSION_GUIDE = "path_permission_guide";
    public static final String PERMISSION_GUIDE_CLICK_INDEX = "permission_guide_click_index";
    public static final String PERMISSION_GUIDE_MODEL = "permission_guide_model";
    public static final String PERMISSION_GUIDE_ENTER_MAIN = "permission_guide_enter_main";
    public static final String PERMISSION_GUIDE_OS_NAME = "permission_guide_os_name";
    public static final String PERMISSION_GUIDE_PERMISSION_LIST = "permission_guide_permission_list";
    public static final String PERMISSION_GUIDE_PERMISSION_TYPE = "permission_guide_permission_type";

    public static final String PATH_REGISTER_OPTIMIZE = "path_register_optimize";
    public static final String REGISTER_OPTIMIZE_LANDINGPAGE_START_USE = "start_use";
    public static final String REGISTER_OPTIMIZE_LANDINGPAGE_START_USE_SUCCESS = "start_use_success";
    public static final String REGISTER_OPTIMIZE_INAPP_UNREGISTER_REGISTER_SUCCESS = "inpp_unregister_register_success";
    public static final String REGISTER_OPTIMIZE_NORMAL_CALL_REGISTER = "normal_call_register";
    public static final String REGISTER_OPTIMIZE_NORMAL_CALL_REGISTER_SHOW = "normal_call_register_show";
    public static final String REGISTER_OPTIMIZE_NORMAL_CALL_REGISTER_SUCCESS = "normal_call_register_success";

    public static final String PATH_HANGUP_INVITE_MORE_SHARE = "path_hangup_invite_more_share";
    public static final String KEY_SHARE = "key_share";/*caller_callee_timestamp*/
    public static final String SOURCE = "source";/*weixi,QQ,cancel*/
    public static final String RESULT_SHARE = "result_share";/*0:cancel,1:fialed,2:success*/
    /**
     * new dual sim
     */
    public static final String PATH_NEW_DUAL_SIM = "path_new_dual_sim";
    public static final String DEVICE = "device";
    public static final String SINGLE_SIM_BUILTIN_SUCCESS = "single_sim_builtin_success";  // bulitin single card
    public static final String SINGLE_SIM_CLOUD_SUCCESS = "single_sim_cloud_success";  // cloud single card
    public static final String SINGLE_SIM_DEFAULT = "single_sim_default";  // auto detection single card
    public static final String DUAL_SIM_BUILTIN_PENDING = "dual_sim_builtin_pending";  // builtin dual slot
    public static final String DUAL_SIM_CLOUD_PENDING = "dual_sim_cloud_pending";  // cloud dual slot
    public static final String DUAL_SIM_AUTO_PENDING = "dual_sim_auto_pending";  // auto detection dual slot
    public static final String DUAL_SIM_SUCCESS = "dual_sim_success";  // dual sim successful
    public static final String DUAL_SIM_MANUAL_CLOUD_RESULT = "dual_sim_manual_cloud_result";  // manually cloud sync successful
    public static final String DUAL_SIM_MANUAL_RESULT = "dual_sim_manual_result";  // manual detection successful
    public static final String DUAL_SIM_PENDING_DIALOG_SHOW = "dual_sim_pending_dialog_show";
    public static final String DUAL_SIM_PENDING_DIALOG_UNSHOW = "dual_sim_pending_dialog_unshow";


    /**
     * dial setting
     */

    public static final String PATH_DIAL_SETTING = "path_dial_setting";
    public static final String ENTER_DIAL_SETTING = "enter_dial_setting";
    public static final String CLICK_TYPE_SINGLESIM = "click_type_singlesim";
    public static final String CLICK_TYPE_DUALSIM = "click_type_dualsim";
    public static final String SWIPE_LEFT_SINGLESIM = "swipe_left_singlesim";
    public static final String SWIPE_RIGHT_SINGLESIM = "swipe_right_singlesim";
    public static final String SWIPE_LEFT_DUALSIM = "swipe_left_dualsim";
    public static final String SWIPE_RIGHT_DUALSIM = "swipe_right_dualsim";
    public static final String ENTER_RIGHT_LEFT_SETTING = "enter_right_left_setting";
    public static final String OPEN_RIGHT_LEFT = "open_right_left";
    public static final String DIALER_ACTION = "action";

    public static final String PHONEPAD_HEIGHT = "phonepad_height"; //拨号盘尺寸
    public static final String HAPTIC_FREEBACK_LEN = "haptic_freeback_len"; //按键震动
    public static final String SAC_PREF_CLICK = "sac_pref_click";//单击条目操作
    public static final String CALLLOG_MERGE = "calllog_merge"; //通话记录合并

    public static final String SOUND_FEEDBACK = "sound_feedback"; //按键声音
    public static final String CALLLOG_SHOW_PHOTO = "calllog_show_photo"; //列表中显示头像
    public static final String CONTACT_PHONENUM = "contact_phonenum"; //列表中显示联系人归属地
    public static final String FORMAT_NUMBER = "format_number"; //规范化号码
    public static final String HANGUP_VIBRATE = "hangup_vibrate";// 挂断震动
    public static final String CALL_FORWARD = "call_forward"; // 来电转接
    public static final String SPEED_DIAL = "speed_dial"; //快速拨号

    public static final String ERROR_RECOGNIZE = "error_recognize"; //单卡重新识别为双卡

    public static final String EDIT_SIM_TWO = "edit_sim_two";// 编辑卡2

    public static final String REVERSE_CALLSLOT = "reverse_slot"; //拨号卡槽颠倒
    public static final String REVERSE_CALLOG_SLOT = "reverse_calllog_slot"; //通话记录卡槽颠倒
    public static final String DUALSIM_SINGLECARD = "dualsim_singlecard"; // 还原为单卡

    //sim contacts
    public static final String PATH_SIM_CONTACTS = "path_sim_contacts";
    public static final String HAS_SIM_CONTACTS = "has_sim_contacts";
    public static final String ENTER_SIM_CONTACT_DETAIL = "enter_sim_contact_detail";

    /**
     * network traffic stat
     */
    public static final String PATH_TRAFFIC_STAT = "path_traffic_stat";
    public static final String TRAFFIC_STAT_BYTES_RECEIVED = "bytes_received";
    public static final String TRAFFIC_STAT_BYTES_TRANSMITTED = "bytes_transmitted";
    public static final String TRAFFIC_STAT_BEGIN_TIMESTAMP = "begin_timestamp";
    public static final String TRAFFIC_STAT_TYPE = "type";
    public static final String TRAFFIC_STAT_TYPE_CONNECTIVITY_CHANGE = "connectivity_change";
    public static final String TRAFFIC_STAT_TYPE_PERIOD = "period";

    /**
     * launch time stat
     */
    public static final String PATH_LAUNCH_TIME_STAT = "path_launch_time_stat_v65";
    public static final String LAUNCH_TYPE = "launch_type";
    public static final String TIME_TYPE = "time_type";
    public static final String LAUNCH_TIME = "launch_time";
    public static final String LAUNCH_ID = "launch_id";

    /**
     * memory stat
     */
    public static final String PATH_MEMORY_STAT = "path_memory_stat";
    public static final String MEMORY_STAT_PSS = "pss";
    public static final String MEMORY_STAT_PRIVATE_DIRTY = "private_dirty";
    public static final String MEMORY_STAT_MOMENT = "moment";

    /**
     * storage stat
     */
    public static final String PATH_STORAGE_STAT = "path_storage_stat";
    public static final String STORAGE_STAT_SDCARD = "sdcard";
    public static final String STORAGE_STAT_DATA = "data";

    //commercial
    public static final String PATH_C2C_DISCONNECT = "path_c2c_disconnect";
    public static final String PATH_C2C_CALLBACK = "path_c2c_callback";
    public static final String PATH_P2P_DISCONNECT = "path_p2p_disconnect";
    public static final String PATH_TEXT_COMMERCIAL = "path_text_commercial";
    public static final String PATH_MULTI_MEDIA = "path_multi_media";
    public static final String PATH_START_UP = "path_start_up";
    public static final String REQUEST_COMMERCIAL = "request_commercial";
    public static final String C2C_DISCONNECT_ERROR_CODE = "c2c_disconnect_error_code";
    public static final String SHOW_COMMERCIAL = "show_commercial";
    public static final String SHOW_COMMERCIAL_BAIDU = "show_commercial_baidu";
    public static final String SHOW_COMMERCIAL_TENCENT = "show_commercial_tencent";
    public static final String COMMERCIAL_POSITION = "commercial_position";
    public static final String SHOW_LOCAL_GUIDE = "show_local_guide";
    public static final String SHOW_ERROR_VIEW = "show_error_view";
    public static final String CLICK_COMMERCIAL = "click_commercial";

    public static final String CALLLOG_COUNT = "callog_count";

    public static final String PATH_DIALOG_GUIDE = "path_dialog_guide";
    public static final String DIALOG_GUIDE_SHOW = "dialog_guide_show";
    public static final String DIALOG_GUIDE_MAIN_CLICK = "dialog_guide_click";
    public static final String DIALOG_GUIDE_LEFT_CLICK = "dialog_guide_left_click";
    public static final String DIALOG_GUIDE_RIGHT_CLICK = "dialog_guide_right_click";
    public static final String DIALOG_GUIDE_CLOSE = "dialog_guide_close";
    public static final String DIALOG_GUIDE_ACTIVITY_ID = "dialog_guide_activity_id";

    public static final String PATH_KEYBOARD = "path_keyboard";
    public static final String KEYBOARD_BOARD_TYPE = "keyboard_board_type";

    public static final String PATH_LONG_PRESS = "path_long_press";
    public static final String CALLLOG_ACTION = "calllog_action";
    public static final String CONTACT_ACTION = "contact_action";
    public static final String MARK = "mark"; //标记
    public static final String SAVE = "save"; //保存
    public static final String DELETE = "delete"; //删除
    public static final String CLEAR = "clear"; //清除
    public static final String SMS = "sms"; //发信息
    public static final String COPY = "copy"; //复制号码
    public static final String SHARE = "share"; //分享号码
    public static final String ADD_IP = "add_ip"; //添加IP前缀
    public static final String WHITE_BLACK = "white_black";// 黑白名单
    public static final String EDIT = "edit"; // 标记号码
    public static final String FAVOURITE_SAVE = "favourite_save"; // 添加收藏
    public static final String FAVOURITE_CANCEL = "favourite_cancel"; //取消收藏
    public static final String DIAL = "dial"; //拨打电话
    public static final String GROUP = "group"; //分组
    public static final String IMPORT_TO_PHONE = "import_to_phone"; //导入手机

    public static final String PATH_NORMAL_HANGUP_AB_TEST = "path_normal_hup_abtest";
    public static final String PATH_OFFICIAL_PUSH = "path_official_push";
    public static final String PUSH_REQUEST = "push_request";
    public static final String PUSH_DEVICE_INFO = "push_device_info";
    public static final String DEVICE_HUAWEI = "huawei";
    public static final String DEVICE_XIAOMI = "xiaomi";
    public static final String PUSH_RECEIVE_THROUGH = "push_receive_through";
    public static final String PUSH_RECEIVE_TOKEN = "push_receive_token";

    public static final String VPMN_DIALOG_CLICK_SAVE = "vpmn_dialog_click_save";
    public static final String VPMN_DIALOG_CLICK_CANCEL = "vpmn_dialog_click_cancel";
    public static final String VPMN_SAVE_FROM_SCRDIALER = "vpmn_save_from_dialer";
    public static final String VPMN_SAVE_FROM_CALLLOG = "vpmn_save_from_calllog";
    public static final String VPMN_SAVE_FROM_TPERSONNEW = "vpmn_save_from_tpersonnew";
    public static final String VPMN_SAVED_CONTACT_CALL = "vpmn_saved_contact_call";

    public static final String FEEDBACK_CLICK = "feedback_click";

    public static final String WEBPAGE_FLOW_RECOMMENDATION_SHOW = "recommendation_show";
    public static final String WEBPAGE_FLOW_RECOMMENDATION_DATA = "recommendation_data";
    public static final String WEBPAGE_FLOW_RECOMMENDATION_FROM_CACHE = "recommendation_from_cache";
    public static final String PATH_NORMAL_PHONE_AD = "path_normal_phone_ad_red_packet";

    // normal call diconnect show ad dialog
    public static final String PATH_SHOW_NORMAL_CALL_DISCONNECT_DIALOG = "path_show_normal_call_disconnect_dialog";
    public static final String SHOW_NORMAL_CALL_DISCONNECT_DIALOG = "show_normal_call_disconnect_dialog";
    public static final String CLICK_SETTING_IN_NORMAL_CALL_DISCONNECT_DIALOG = "click_setting_in_normal_call_disconnect_dialog";


    public static final String PATH_SEARCH_PERFORMANCE = "path_search_performance";
    public static final String READ_CONTACTS_TIME = "read_contacts_time";
    public static final String CONTACTS_COUNT = "contacts_count";
    public static final String INDEX_CONTACTS_TIME = "index_contacts_time";
    public static final String FIRST_SEARCH_CONTACT_TIME = "first_search_contact_time";

    public static final String PATH_LOGIN_NUMBER_FILL = "path_login_number_fill";
    public static final String ENTER_LOGIN_PAGE = "enter_login_page";
    public static final String NUMBER_AUTO_FILL = "number_auto_fill";
    public static final String PATH_PERMISSION_INAPP_GUIDE = "new_path_perm_inapp_guide";
    public static final String PATH_CALL_LOG = "path_call_log";
    public static final String SAMSUNG_CALL_LOG_COLUMN = "samsung_call_log_column";
    public static final String PATH_FREE_CALL_GUIDE = "new_path_free_call_guide";
    public static final String PATH_SKIN_AD = "path_skin_ad";
    public static final String PATH_OOM_BITMAP_URL = "path_oom_bitmap_url";

    //sms
    public static final String PATH_ACTION_SEND_SMS = "path_action_send_sms";

    // prefetch ads
    public static final String PATH_PREFETCH_ADS = "path_prefetch_ads";
    public static final String PREFETCH_SEND_SHOW_AD_URL = "prefetch_send_show_ad_url";
    public static final String PREFETCH_SEND_EDURL = "prefetch_send_edurl";
    public static final String PATH_HARDWARE_COLLECT = "path_hardware_collect";

    // almark ads
    public static final String PATH_ALMARK_ADS = "path_almark_ads";
    public static final String ALMARK_OUTGOING_STEP_NAME = "almark_outgoing_step_name";
    public static final String ALMARK_OUTGOING_STEP_VALUE = "almark_outgoing_step_value";
    public static final String ALMARK_OUTGOING_ONCREATE = "almark_outgoing_oncreate";
    public static final String ALMARK_OUTGOING_FINISH = "almark_outgoing_finish";
    public static final String ALMARK_OUTGOING_HANGUP_FAILED = "almark_outgoing_hangup_failed";
    public static final String ALMARK_START_FETCH_ADS = "almark_start_fetch_ads";
    public static final String ALMARK_SKIP_FETCH_ADS = "almark_skip_fetch_ads";
    public static final String ALMARK_AD_CK = "almark_ad_ck";
    public static final String ALMARK_REQUEST_FETCH_ADS = "almark_request_fetch_ads";
    public static final String ALMARK_FETCH_ADS_RESULT = "almark_fetch_ads_result";
    public static final String ALMARK_AD_SAVE_HTML_TO_FILE_RESULT = "almark_ad_save_html_to_file_result";
    public static final String ALMARK_AD_NO_CONTENT = "almark_ad_no_content";
    public static final String ALMARK_OUTGOING_CALLBACK = "almark_outgoing_callback";
    public static final String ALMARK_START_SHOW_ADS = "almark_start_show_ads";
    public static final String ALMARK_START_SHOW_CALLBACK_ADS = "almark_start_show_callback_ads";
    public static final String ALMARK_ADS_STATE_WHEN_SHOW = "almark_ads_state_when_show";
    public static final String ALMARK_ADS_STATE_WHEN_SHOW_2 = "almark_ads_state_when_show_2";
    public static final String ALMARK_CALLBACK_ADS_STATE_WHEN_SHOW = "almark_callback_ads_state_when_show";
    public static final String ALMARK_CALLBACK_ADS_STATE_WHEN_SHOW_2 = "almark_callback_ads_state_when_show_2";
    public static final String ALMARK_C2C_DISCONNECT_ON_CREATE = "almark_c2c_disconnect_on_create";
    public static final String ALMARK_C2C_DISCONNECT_SHOW_NORMAL = "almark_c2c_disconnect_show_normal";
    public static final String ALMARK_C2C_DISCONNECT_SHOW_LOCAL_AD = "almark_c2c_disconnect_show_local_ad";
    public static final String ALMARK_C2C_DISCONNECT_SHOW_COOTEK_AD = "almark_c2c_disconnect_show_cootek_ad";
    public static final String ALMARK_C2C_DISCONNECT_STEP = "almark_c2c_disconnect_step";
    public static final String ALMARK_C2C_DISCONNECT_CLOSE = "almark_c2c_disconnect_close";
    public static final String ALMARK_END_SHOW_ADS = "almark_end_show_ads";
    public static final String ALMARK_UUID = "almark_uuid";
    public static final String ALMARK_SIPCALLID = "sipcallid";

    // startup commercial
    public static final String PATH_STARTUP_COMMERCIAL_CUSTOM_EVENT = "path_startup_commercial_custom_event";
    public static final String STARTUP_COMMERCIAL_CUSTOM_EVENT_STEP_NAME = "startup_commercial_custom_event_step_name";
    public static final String STARTUP_COMMERCIAL_CUSTOM_EVENT_STEP_VALUE = "startup_commercial_custom_event_step_value";
    public static final String STARTUP_COMMERCIAL_CUSTOM_EVENT_TSTARTUP_ONCREATE = "startup_commercial_custom_event_tstartup_oncreate";
    public static final String STARTUP_COMMERCIAL_CUSTOM_EVENT_ACTIVITY_ONCREATE = "startup_commercial_custom_event_activity_oncreate";
    public static final String STARTUP_COMMERCIAL_CUSTOM_EVENT_CAN_SHOW = "startup_commercial_custom_event_can_show";

    public static final String PATH_CALL_QUALITY = "new_path_call_quality";

    public static final String PATH_STORAGE = "path_storage";
    public static final String STORAGE_SD_TOTAL = "storage_sd_total";
    public static final String STORAGE_SD_AVAILABLE = "storage_sd_available";
    public static final String STORAGE_ROM_TOTAL = "storage_rom_total";
    public static final String STORAGE_ROM_AVAILABLE = "storage_rom_available";

    public static final String PATH_CUSTOM_EVENT = "path_custom_event";
    public static final String CUSTOM_EVENT_NAME = "event_name";
    public static final String CUSTOM_EVENT_VALUE = "event_value";
    // event names of path_custom_event
    public static final String CUSTOM_EVENT_LOGIN = "event_android_login";
    public static final String CUSTON_EVENT_CLICK_FAMILY_BUTTON = "event_click_family_button";

    // commercial custom event
    public static final String CUSTOM_EVENT_MULTI_MEDIA_ADS_CLIKE = "event_multi_media_ads_clike";
    public static final String CUSTOM_EVENT_MULTI_MEDIA_AD_STATE = "event_multi_media_ads_state";
    public static final String CUSTOM_EVENT_MULTI_MEDIA_AD_STATE_DETAIL = "event_multi_media_ads_state_detail";
    public static final String CUSTOM_EVENT_MULTI_MEDIA_AD_WEBVIEW_LOAD_SUC = "event_multi_media_ads_webview_load_suc";
    public static final String CUSTOM_EVENT_MULTI_MEDIA_AD_START_REQUEST = "event_multi_media_ad_start_request";
    public static final String CUSTOM_EVENT_MULTI_MEDIA_AD_REQUEST_SKIP = "event_multi_media_ad_request_skip";
    public static final String CUSTOM_EVENT_MULTI_MEDIA_AD_REQUEST = "event_multi_media_ad_request";
    public static final String CUSTOM_EVENT_MULTI_MEDIA_AD_REQUEST_RESULT = "event_multi_media_ad_request_result";
    public static final String CUSTOM_EVENT_MULTI_MEDIA_AD_SAVE_HTML = "event_multi_media_ad_save_html";
    public static final String CUSTOM_EVENT_MULTI_MEDIA_AD_NO_CONTENT = "event_multi_media_ad_no_content";

    public static final String CUSTOM_EVENT_NORMALCALL_DISCONNECT_COMMERCIAL_STATE = "event_normalcall_disconnect_commercial_state";
    public static final String CUSTOM_EVENT_NORMALCALL_DISCONNECT_ON_HANGUP_OUTGOINGING_CALL = "event_normalcall_disconnect_on_hangup_outgoinging_call";
    public static final String CUSTOM_EVENT_NORMALCALL_DISCONNECT_CAN_SHOW_AD = "event_normalcall_disconnect_on_hangup_outgoinging_call_can_show_ad";
    public static final String CUSTOM_EVENT_NORMALCALL_DISCONNECT_HAS_AD = "event_normalcall_disconnect_on_hangup_outgoinging_call_has_ad";
    public static final String CUSTOM_EVENT_NORMALCALL_DISCONNECT_HAS_REWARD = "event_normalcall_disconnect_on_hangup_outgoinging_call_has_rewards";
    public static final String CUSTOM_EVENT_NORMALCALL_DISCONNECT_SHOW_REDPACKET = "event_normalcall_disconnect_on_hangup_outgoinging_call_show_redpacket";
    public static final String CUSTOM_EVENT_NORMALCALL_DISCONNECT_OP_REDPACKET = "event_normalcall_disconnect_on_hangup_outgoinging_call_op_redpacket";
    public static final String CUSTOM_EVENT_NORMALCALL_DISCONNECT_SHOW_AD = "event_normalcall_disconnect_on_hangup_outgoinging_call_show_ad";
    public static final String CUSTOM_EVENT_NORMALCALL_DISCONNECT_CLOSE_AD = "event_normalcall_disconnect_on_hangup_outgoinging_close_ad";// 点击

    public static final String CUSTOM_EVENT_NORMALCALL_DISCONNECT_AD_START_REQUEST = "event_normalcall_disconnect_ad_start_request";
    public static final String CUSTOM_EVENT_NORMALCALL_DISCONNECT_AD_REQUEST_SKIP = "event_normalcall_disconnectad_request_skip";
    public static final String CUSTOM_EVENT_NORMALCALL_DISCONNECT_AD_REQUEST = "event_normalcall_disconnect_ad_request";
    public static final String CUSTOM_EVENT_NORMALCALL_DISCONNECT_AD_REQUEST_RESULT = "event_normalcall_disconnect_ad_request_result";
    public static final String CUSTOM_EVENT_NORMALCALL_DISCONNECT_AD_SAVE_HTML = "event_normalcall_disconnect_ad_save_html";
    public static final String CUSTOM_EVENT_NORMALCALL_DISCONNECT_AD_NO_CONTENT = "event_normalcall_disconnect_ad_no_content";
    public static final String CUSTOM_EVENT_AD_STATE_DETAIL = "event_ads_state_detail";

    public static final String CUSTOM_EVENT_AD_TU1 = "event_ad_tu_1";  // => PATH_ALMARK_ADS

    public static final String CUSTOM_EVENT_DELETE_SYNC_ACCOUNT = "event_delete_sync_account";

    public static final String CUSTOM_EVENT_DISCOVERY_REFRESH_SHOW = "event_discovery_refresh_show";
    public static final String CUSTOM_EVENT_DISCOVERY_REFRESH_CLICK = "event_discovery_refresh_click";

    public static final String PATH_SCAN_BUSINESS_CARD = "path_scan_business_card";
    public static final String ENTER = "enter";
    public static final String SCAN_RESULT = "scan_result";
    public static final String SCAN_RESULT_SUCCESS = "success";
    public static final String SCAN_RESULT_NO_PERMISSION = "no_permission";
    public static final String SCAN_RESULT_NO_NETWORK = "no_network";
    public static final String SCAN_RESULT_POOR_NETWORK = "poor_network";
    public static final String SCAN_RESULT_NO_CHANCE = "no_chance";
    public static final String SCAN_RESULT_UNRECOGNISE = "unrecognise";
    public static final String CUSTOM_EVENT_RETRY_ED_URL = "event_retry_ed_url";
    public static final String CUSTOM_EVENT_RETRY_ED_URL_FAILED = "event_retry_ed_url_failed";

    public static final String CUSTOM_EVENT_MULTI_MEDIA_TEMP_PATH = "event_multi_media_temp_path";
    public static final String CUSTOM_EVENT_MULTI_MEDIA_TEMP_PATH_KEY = "event_multi_media_temp_path_key";
    public static final String CUSTOM_EVENT_MULTI_MEDIA_TEMP_PATH_VALUE = "event_multi_media_temp_path_value";
    public static final String CUSTOM_EVENT_MULTI_MEDIA_TEMP_PATH_RETRY = "event_multi_media_temp_path_retry";

    // resource downloading event
    public static final String CUSTOM_EVENT_FILE_DOWNLOAD_SIZE = "event_file_download_size";
    public static final String CUSTOM_EVENT_FILE_DOWNLOAD_PATH = "store_path";
    public static final String CUSTOM_EVENT_FILE_DOWNLOAD_TAG = "file_tag";
    public static final String CUSTOM_EVENT_FILE_DOWNLOAD_NETWORK = "network";

    public static final String PATH_UPLOAD_PROC_STAT_INFO = "path_upload_proc_stat_info";
    public static final String PATH_UPLOAD_BACK_UP_PROCESS = "path_upload_back_up_process";
    public static final String PATH_CHECK_VOIPSERVICE_NOT_RUN = "path_check_voipservice_not_run";

    public static final String PATH_LIST_NEWS = "path_list_news";
    public static final String PATH_DETAIL_NEWS = "path_detail_news";
    public static final String PATH_FEEDS_REQUEST = "path_feeds_request";

    /*accessiblity*/
    public static final String CUSTOM_EVENT_ACCESSIBILITY_GUIDE_SHOW = "event_accessiblity_guide_show";
    public static final String CUSTOM_EVENT_ACCESSIBILITY_GUIDE_SUCCESS = "event_accessiblity_guide_success";
    public static final String CUSTOM_EVENT_ACCESSIBILITY_GUIDE_FAILED = "event_accessiblity_guide_failed";
    public static final String CUSTOM_EVENT_ACCESSIBILITY_GUIDE_CLICK= "event_accessiblity_guide_click";
    public static final String CUSTOM_EVENT_ACCESSIBILITY_GUIDE_CALLLOG= "event_accessiblity_guide_callog";
    public static final String CUSTOM_EVENT_ACCESSIBILITY_GUIDE_STARTUP= "event_accessiblity_guide_startup";
    public static final String CUSTOM_EVENT_ACCESSIBILITY_GUIDE_RESTART= "event_accessiblity_guide_restart";
    public static final String CUSTOM_EVENT_ACCESSIBILITY_GUIDE_ADVICE_GIVEUP= "event_accessiblity_guide_advice_giveup";
    public static final String CUSTOM_EVENT_ACCESSIBILITY_GUIDE_ADVICE_ACCEPT= "event_accessiblity_guide_advice_accept";
    public static final String CUSTOM_EVENT_ACCESSIBILITY_GUIDE_WARNING_GIVEUP= "event_accessiblity_guide_warning_giveup";
    public static final String CUSTOM_EVENT_ACCESSIBILITY_GUIDE_WARNING_ACCEPT= "event_accessiblity_guide_warning_accept";
    public static final String CUSTOM_EVENT_ACCESSIBILITY_GUIDE_OVERVIEW_TIME= "event_accessiblity_guide_overview_time";
    public static final String CUSTOM_EVENT_ACCESSIBILITY_GUIDE_HAND_OPEN= "event_accessiblity_guide_hand_open";
    public static final String CUSTOM_EVENT_ACCESSIBILITY_GUIDE_DIALOG_GIVEUP= "event_accessiblity_guide_dialog_giveup";
    public static final String CUSTOM_EVENT_ACCESSIBILITY_GUIDE_DIALOG_ACCEPT= "event_accessiblity_guide_dialog_accept";
    public static final String CUSTOM_EVENT_ACCESSIBILITY_GUIDE_MIUI8_SUCCESS= "event_accessiblity_guide_miui8_success";
    public static final String CUSTOM_EVENT_ACCESSIBILITY_GUIDE_AUTO_OPEN= "event_accessiblity_guide_auto_open_permission";




    public static final String CUSTOM_EVENT_ACCESSIBILITY_GUIDE_GIVEUP= "event_accessiblity_guide_giveup";

    public static final String CUSTOM_EVENT_ACCESSIBILITY_SERVICE_ENABLE= "event_accessiblity_service_enable";
    public static final String CUSTOM_EVENT_ACCESSIBILITY_PERMISSION_ENABLE= "event_accessiblity_permission_enable";

    public static final String CUSTOM_EVENT_ACCESSIBILITY_GUIDE_ACCEPT= "event_accessiblity_guide_accept";
    public static final String CUSTON_EVENT_ACCESSIBILITY_GUIDE_USERBACKED="event_accessiblity_guide_userbacked";

    /*fuwuhao*/
    public static final String EVENT_FUWUHAO_ACTION = "event_fuwuhao_action";

    public static final String EV_PROFILE = "EV_PROFILE";
    public static final String EV_PROFILE_ICON_CLICK = "EV_PROFILE_ICON_CLICK";
    public static final String EV_PROFILE_LEFT_DRAWER_CLICK = "EV_PROFILE_LEFT_DRAWER_CLICK";
    public static final String EV_PROFILE_LEFT_DRAWER_SHOW = "EV_PROFILE_LEFT_DRAWER_SHOW";

    public static final String EV_WALLET = "EV_WALLET";
    public static final String EV_WALLET_ICON_CLICK = "EV_WALLET_ICON_CLICK";
    public static final String EV_WALLET_REDPACKET_BUTTON_CLICK = "EV_WALLET_REDPACKET_BUTTON_CLICK";
    public static final String EV_WALLET_CONTENT_CLICK = "EV_WALLET_CONTENT_CLICK";
    public static final String EV_WALLET_CONTENT_SHOW = "EV_WALLET_CONTENT_SHOW";

    public static final String EV_WALLET_REDPACKET_SHOW = "wallet_redpacket_show";
    public static final String EV_WALLET_REDPACKET_CLICK = "wallet_redpacket_click";
    public static final String EV_WALLET_REDPACKET_SUCCESS = "wallet_redpacket_success";

    /*display*/
    public static final String EVENT_SHOW_MISSED_CALLOG_GUIDE = "event_show_missed_calllog_guide";
    public static final String EVENT_CLICK_MISSED_CALLOG_GUIDE = "event_click_missed_calllog_guide";
    public static final String EVENT_ENABLE_MISSED_CALLOG_GUIDE = "event_enable_missed_calllog_guide";

    public static final String PATH_DISCOVER_SUB_PAGE = "path_discover_sub_page";
    public static final String PATH_NEW_DISCOVER_SUB_PAGE = "path_new_discover_sub_page";
    public static final String PAGE_INDEX = "page_index";

    public static final String PATH_CONTACT_TOP_LEFT_ICON = "path_contact_top_left_icon";
    public static final String ICON_CLICK = "icon_click";
    public static final String ICON_SHOW = "icon_show";

    public static final String MAIN_TAB_SELECT = "main_tab_select";
    public static final String MAIN_PANDA = "main_panda";
    public static final String MAIN_PANDA_CLICK = "main_panda_click";
    public static final String MAIN_PANDA_SHOW = "main_panda_show";
    public static final String MAIN_PANDA_SUB_BTN = "main_panda_sub_btn";

    /*miui_version*/
    public static final String CUSTON_EVENT_ACCESSIBILITY_MIUI_VERSION="event_accessiblity_miui_version";


    /*voip switch*/
    public static final String EVENT_VOIP_CLOSE_UPGRADE = "event_voip_close_upgrade";
    public static final String EVENT_VOIP_SWITCH_ENABLE = "event_voip_switch_enable";
    public static final String EVENT_VOIP_SWITCH_OPEN_INIT = "event_voip_switch_open_init";

    /*app keep alive time*/
    public static final String APP_KEEP_PAGE_ACTIVE = "app_keep_page_active";
    //path_package
    public static final String PATH_PACKAGE = "path_package";
    public static final String PACKAGE_INFO = "package_info";

    public static final String DUAL_SIM_MANUFACTURE = "dual_sim_manufacture";
    public static final String DUAL_SIM_MODEL = "dual_sim_model";
    public static final String DUAL_SIM_HOST = "dual_sim_host";

    public static final String SHOW_RATE_DIALOG_ACTION = "dialog_action";
    public static final String RATE_DIALOG_LIKE = "like";
    public static final String RATE_DIALOG_DISLIKE = "dislike";
    public static final String RATE_DIALOG_CLOSE = "close";
    public static final String SHOW_RATE_DIALOG_FROM = "dialog_from";
    public static final String RATE_DIALOG_VOIP_CALL = "voip";
    public static final String RATE_DIALOG_SKIN = "skin";
    public static final String RATE_DIALOG_ACTIVITY = "activity";
    public static final String RATE_PAGE_ENTER = "rate_page_enter";
    public static final String RATE_SHARE_WECHAT = "rate_share_wechat";
    public static final String RATE_SHARE_TIMELINE = "rate_share_timeline";
    public static final String RATE_SHARE_QQ = "rate_share_qq";
    public static final String RATE_SHARE_QZONE = "rate_share_qzone";
    public static final String RATE_APP_CLICK = "rate_app_click";
    public static final String RATE_SHARE_SKIP = "rate_share_skip";
    public static final String RATE_SHARE_CLICK = "rate_share_click";
    public static final String PATH_RATE_NEW = "path_rate_new";
    public static final String RATE_SHARE_ENTER = "rate_share_enter";

    public static final String CUSTOM_EVENT_SCREENSHOT_OPT = "event_screenshot_opt";


    public static final String TOAST_SUCCESS_SHOW = "toast_success_show";
    public static final String ACTION_TOAST_SUCCESS_SHOW = "action_toast_success_show";

    public static final String TOAST_INCOMING_CALL_UNKNOWN = "toast_incoming_call_unknown";
    public static final String TOAST_INCOMING_CALL_ALL = "toast_incoming_call_all";
    public static final String TOAST_INCOMING_TOAST_SHOW_UNKNOWN= "toast_incoming_toast_show_unknown";
    public static final String TOAST_INCOMING_TOAST_SHOW_ALL= "toast_incoming_toast_show_all";

    public static final String TOAST_OUTGOING_CALL_UNKNOWN = "toast_outgoing_call_unknown";
    public static final String TOAST_OUTGOING_CALL_ALL = "toast_outgoing_call_unknown_all";
    public static final String TOAST_OUTGOING_TOAST_SHOW_UNKNOWN = "toast_outgoing_toast_show_unknown";
    public static final String TOAST_OUTGOING_TOAST_SHOW_ALL = "toast_outgoing_toast_show_all";

    // voice actor
    public static final String CUSTOM_EVENT_VOICE_ACTOR = "CUSTOM_EVENT_VOICE_ACTOR";
    public static final String CUSTOM_EVENT_VOICE_ACTOR_NAME = "CUSTOM_EVENT_VOICE_ACTOR_NAME";
    public static final String CUSTOM_EVENT_VOICE_ACTOR_VALUE = "CUSTOM_EVENT_VOICE_ACTOR_VALUE";
    //进入首页
    public static final String CUSTOM_EVENT_VOICE_ACTOR_HOME_PAGE = "CUSTOM_EVENT_VOICE_ACTOR_HOME_PAGE";
    //进入技能资质
    public static final String CUSTOM_EVENT_VOICE_ACTOR_SKILL_DETAIL = "CUSTOM_EVENT_VOICE_ACTOR_SKILL_DETAIL";
    //聊天页下单
    public static final String CUSTOM_EVENT_VOICE_ACTOR_CHAT_MAKE_ORDER = "CUSTOM_EVENT_VOICE_ACTOR_CHAT_MAKE_ORDER";
    //技能资质页下单
    public static final String CUSTOM_EVENT_VOICE_ACTOR_SKILL_MAKE_ORDER = "CUSTOM_EVENT_VOICE_ACTOR_SKILL_MAKE_ORDER";
    //技能资质页下单
    public static final String CUSTOM_EVENT_VOICE_ACTOR_SKILL_DO_CHAT = "CUSTOM_EVENT_VOICE_ACTOR_SKILL_DO_CHAT";
    //个人中心下单
    public static final String CUSTOM_EVENT_VOICE_ACTOR_PROFILE_MAKE_ORDER = "CUSTOM_EVENT_VOICE_ACTOR_PROFILE_MAKE_ORDER";
    //订单列表下单
    public static final String CUSTOM_EVENT_VOICE_ACTOR_ORDER_LIST_MAKE_ORDER = "CUSTOM_EVENT_VOICE_ACTOR_ORDER_LIST_MAKE_ORDER";
    //订单详情下单
    public static final String CUSTOM_EVENT_VOICE_ACTOR_ORDER_DETAIL_MAKE_ORDER = "CUSTOM_EVENT_VOICE_ACTOR_ORDER_DETAIL_MAKE_ORDER";
    //首页推荐位点击，value = skill_id
    public static final String CUSTOM_EVENT_VOICE_ACTOR_HOME_RECOMMEND = "CUSTOM_EVENT_VOICE_ACTOR_HOME_RECOMMEND";
    //进入声优列表
    public static final String CUSTOM_EVENT_VOICE_ACTOR_ACTOR_LIST = "CUSTOM_EVENT_VOICE_ACTOR_ACTOR_LIST";
    //声优列表每行点击，value = skill_id
    public static final String CUSTOM_EVENT_VOICE_ACTOR_ACTOR_LIST_ROW = "CUSTOM_EVENT_VOICE_ACTOR_ACTOR_LIST_ROW";
    //首页查看更多
    public static final String CUSTOM_EVENT_VOICE_ACTOR_HOME_SHOW_MORE = "CUSTOM_EVENT_VOICE_ACTOR_HOME_SHOW_MORE";
    //首页换一组
    public static final String CUSTOM_EVENT_VOICE_ACTOR_HOME_CHANGE_RECOMMEND = "CUSTOM_EVENT_VOICE_ACTOR_HOME_CHANGE_RECOMMEND";
    //技能资质页视频播放
    public static final String CUSTOM_EVENT_VOICE_ACTOR_SKILL_VIDEO = "CUSTOM_EVENT_VOICE_ACTOR_SKILL_VIDEO";
    //技能资质页音频播放
    public static final String CUSTOM_EVENT_VOICE_ACTOR_SKILL_AUDIO = "CUSTOM_EVENT_VOICE_ACTOR_SKILL_AUDIO";
    //技能资质页显示全部评论
    public static final String CUSTOM_EVENT_VOICE_ACTOR_SKILL_ALL_COMMENT = "CUSTOM_EVENT_VOICE_ACTOR_SKILL_ALL_COMMENT";
    //首页分类点击前缀
    public static final String CUSTOM_EVENT_VOICE_ACTOR_PREFIX = "CUSTOM_EVENT_VOICE_ACTOR_%s";
    public static final String PATH_TAB_CLICK = "path_tab_click";
    public static final String PATH_TAB = "path_tab";
    public static final String PATH_TAB_BROWSE = "path_tab_browse";
    public static final String PATH_TAB_DIAL_BROWSE = "path_tab_dial_browse";
    public static final String PATH_TAB_CONTACT_BROWSE = "path_tab_contact_browse";
    public static final String PATH_TAB_DISCOVERY_BROWSE = "path_tab_discovery_browse";
    public static final String PATH_TAB_BIBI_BROWSE = "path_tab_bibi_browse";
    public static final String PATH_DISCOVERY_TAB_CLICK = "path_discovery_tab_click";
    public static final String PATH_DISCOVERY_TAB_BROWSE = "path_discovery_tab_browse";
    public static final String PATH_DISCOVERY_TAB_NEWS_BROWSE = "path_discovery_tab_news_browse";
    public static final String PATH_DISCOVERY_TAB_MEET_BROWSE = "path_discovery_tab_meet_browse";
    public static final String PATH_DISCOVERY_TAB_ACTOR_BROWSE = "path_discovery_tab_actor_browse";
    public static final String PATH_DISCOVERY = "path_discovery";

    public static final String PATH_EXIT = "path_exit";
    public static final String PATH_EXIT_TAB = "path_exit_tab";

    // app state
    public static final String PATH_APP_KEEP_FORGROUND_ACTIVE = "app_keep_forground_active";
    public static final String APP_FOREGROUND_TIME = "second";

    public static final String PATH_PERMISSION_LIST_CLICK = "path_permission_list_click";
    public static final String PERMISSION_LIST_CLICK_CONTENT = "permission_list_click_content";
    public static final String PERMISSION_LIST_CLICK_SUM = "permission_list_click_sum";
    public static final String PERMISSION_LIST_CLICK_CLICKED_SUM = "permission_list_click_clicked_sum";


    public static final String PATH_PERMISSION_LIST_UPGRADE_CLICK = "path_permission_list_upgrade_click";
    public static final String PERMISSION_LIST_UPGRADE_CLICK_CONTENT = "permission_list_upgrade_click_content";
    public static final String PERMISSION_LIST_UPGRADE_CLICK_SUM = "permission_list_upgrade_click_sum";
    public static final String PERMISSION_LIST_UPGRADE_CLICK_CLICKED_SUM = "permission_list_upgrade_click_clicked_sum";

    public static final String PATH_REDPACKET = "path_redpacket";

    public static final String PATH_CALLLOG_CONTACT_PERMISSIONBUTTON = "path_calllog_contact_permissionbutton";
    public static final String CALLLOG_PERMISSIONBUTTON_SHOW = "calllog_permissionbutton_show";
    public static final String CALLLOG_PERMISSIONBUTTON_CLICK = "calllog_permissionbutton_click";
    public static final String CALLLOG_PERMISSIONBUTTON_UNSHOW = "calllog_permissionbutton_unshow";
    public static final String CONTANCT_PERMISSIONBUTTON_SHOW = "contact_permissionbutton_show";
    public static final String CONTANCT_PERMISSIONBUTTON_CLICK = "contact_permissionbutton_click";
    public static final String CONTANCT_PERMISSIONBUTTON_UNSHOW = "contact_permissionbutton_unshow";

    public static final String PATH_USER_PERMISSION = "path_user_permission";

    // for walkietalkie_sdk usage
    public static final String USAGE_TYPE_WTSDK = "walkietalkie_sdk";


    public static final String PATH_DISCOVER_TEN_PLUS = "path_discover_ten_plus";
    public static final String DISCOVER_TEN_PLUS_SHOW = "discover_ten_plus_show";
    public static final String DISCOVER_TEN_PLUS_HIDE = "discover_ten_plus_hide";

    public static final String PATH_FEEDS_PUSH = "path_feeds_push";
    public static final String FEEDS_PUSH = "feeds_push";

}

