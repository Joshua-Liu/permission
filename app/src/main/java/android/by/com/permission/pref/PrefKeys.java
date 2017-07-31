package android.by.com.permission.pref;


public class PrefKeys {
    public static final String HAPTIC_FEEDBACK_LEN = "haptic_feedback_len";
    public static final String SOUND_FEEDBACK = "sound_feedback";
    public static final String SOUND_FEEDBACK_SETED = "sound_feedback_seted_key";
    public static final String SOUND_FEEDBACK_LEN = "sound_feedback_len";

    public static final String CONTACTS_WITHOUT_NUMBER = "contacts_without_number";

    public static final String FORMAT_NUMBER = "format_number";
    public static final String FORMAT_NUMBER_COUNTRY = "format_number_country";
    public static final String FORMAT_NUNBER_PRIVILEGE = "format_number_privilege";

    public static final String SORT_NAME_BY = "contact_sort";


    public static final String CONTACT_ACCOUNT_SYNC = "account_sync";
    public static final String CONTACT_IMPORT = "import";


    public static final String SHOW_EMPTY_CALLLOG_ANIMATION = "show_empty_calllog_animation";
    public static final String SHOW_EMPTY_CONTACT_ANIMATION = "show_empty_contact_animation";

    public static final String CREATE_SHORTCUT = "create_shortcut";

    //redpacket
    public static final String RED_PACKET_S = "red_packet_s";
    public static final String RED_PACKET_PST = "red_packet_pst";
    public static final String RED_PACKET_ADN = "red_packet_adn";
    public static final String RED_PACKET_CENTER = "red_packet_center";

    //personasset
    public static final String WALLET_PROFIT_CENTER_ID = "wallet_profit_center_id";

	//User Habits
	public static final String LAST_TIMEOUT_HOURS = "last_time_out_hours";
	
	public static final String CONNECTED_VIBRATE = "connected_vibrate";
	public static final String HANGUP_VIBRATE = "hangup_vibrate";

	public static final String INSERT_CALL_PERMISSION_DENIED = "insert_call_permission_deined";

	//Display
	public static final String CALLLOG_MERGE = "calllog_merge";
	public static final String TIME_STYLE_24 = "time_style_24";
	public static final String SKIN = "skin";

	//Phonen number attribute
	public static final String PNATTR_INCALL = "incoming_call_phonenum";
	public static final String PNATTR_INCALL_DISP_UNTIL = "incoming_display_until";
	public static final String PNATTR_OUTGOING = "outgoing_call_phonenum";
	public static final String PNATTR_OUTGOING_DISP_UNTIL = "outgoing_display_until";

    public static final String ONCALL_LOCDISPLAY_YTOAST_v2 = "oncalllocdisplay_toast_offset_y_v2";
    public static final String LOCDISPLAY_INCOMING_UNKNOWN_ONLY = "locdisplay_incoming_unknown_only";
    public static final String LOCDISPLAY_OUTGOING_UNKNOWN_ONLY = "locdisplay_outgoing_unknown_only";
    public static final String LOCDISPLAY_UNKNOWN_ONLY = "locdisplay_unknown_only";
    public static final String PNATTR_CONTACT = "contact_phonenum";
    public static final String TOAST_ALPHA = "toast_alpha";
    public static final String TOAST_ADJUST_DURATION = "toast_adjust_duration";
    public static final String TOAST_FIXED = "toast_fixed";

    //Enhanced Preference
    public static final String AREA_CODE = "main_areacode";
    public static final String NETWORK_MNC = "network_mnc";

    public static final String SAVER_ASSIST_ENABLE = "saver_assist_enable";
    public static final String SAVER_ASSIST_ENABLE_2 = "saver_assist_enable_2";
    public static final String SAVER_MNC_SET = "saver_mnc_set";
    public static final String SAVER_ASSIST_AUTO_APPLY = "saver_assist_auto_apply";
    public static final String SAVER_ASSIST_AUTO_APPLY_2 = "saver_assist_auto_apply_2";

    public static final String TOUCHPAL_REGISTER_ACCOUNT_SERVER = "touchpal_register_account_server";
    public static final String PUBLIC_NUMBER_TEMP_ACCOUNT = "public_number_temp_account";
    public static final String TOUCHPAL_PHONENUMBER_OEM_ACCOUNT = "touchpal_phonenumber_oem_account";

    // black list
    public final static String HANGUP_MODE = "hangupmode";
    public final static String BLOCK_SCENARIO = "blockscenario";
    public final static String BLOCK_SHOW_NOTIFICATION = "block_show_notification";
    public final static String BLOCK_HISTORY_READ_COUNT = "block_history_count";
    // since 4800, we have new block mode
    public final static String NORMAL_BLOCK_SETTINGS = "normal_block_settings";
    public final static String SMART_BLOCK_SETTINGS = "smart_block_settings";
    public final static String TIMING_BLOCK_SETTINGS = "timing_block_settings";
    public final static String BLOCK_SETTINGS_GUIDE_APPEAR_CLASSIFIES = "block_settings_guide_appear_classifies";

    public final static String UPDATE_APK_URL = "update_apk_url";
    public final static String UPDATE_APK_FILE_PATH = "update_apk_file_path";
    public final static String UPDATE_APK_DESCRIPTION = "update_apk_description";

    // secondary language
    public final static String PHONEPAD_LANGUAGE = "secondary_language";

    // phonepad height
    public static final String PHONEPAD_HEIGHT = "phonepad_height";

    // wall
    public static final String GESTURE_DIALING_ON = "gesture_dialing_status";
    public static final String GESTURE_SHOW_USE_NEW_HINT = "gesture_show_use_new_hint";

    public static final String KEYBOARD_TYPE = "keyboard_type";
    public static final String CALL_ACTIVATED = "call_activated";

    //yellow page
    public static final String YP_CONNECT_TO_CLOUD = "yp_connect_to_cloud";
    public static final String YP_SMART_JUDGE_INCOMING = "yp_smart_judge_incoming";
    public static final String YP_TIPS_TDIALER = "yp_tips_tdialer";
    /**
     * save the last notification city id.
     * {@link Constants#EMPTY_STR} means unused.
     */
    public static final String YP_CALLERID_TIPS_MKT = "yp_callerid_tips_mkt";

    public static final String TEST_VERSION_ACTIVATION_SUCCEED = "test_version_activation_succeed";

    //Swap and Click
    public static final String SAC_PREF_SWAP_LEFT = "sac_pref_swap_left";
    public static final String SAC_PREF_SWAP_RIGHT = "sac_pref_swap_right";
    public static final String SAC_PREF_CLICK = "sac_pref_click";
    public static final String SAC_PREF_SWAP_ENABLE = "sac_pref_swap_enable";
    public static final String SAC_PREF_VIBRATE_ENABLE = "sac_pref_vibrate_enable";

    public static final String LAST_CONTACT_SAVE_ACCOUNT_NAME = "last_contact_save_account_name";
    public static final String LAST_CONTACT_SAVE_ACCOUNT_TYPE = "last_contact_save_account_type";

    public static final String LAST_GROUP_ADD_ACCOUNT_NAME = "last_group_add_account_name";
    public static final String LAST_GROUP_ADD_ACCOUNT_TYPE = "last_group_add_account_type";

    public static final String INSTALLED_PLUGINS = "installed_plugin";
    public static final String INSTALLED_PLUGINS_VERSION = "installed_plugins_version";

    public static final String PLUGIN_NEW_MARK_PREFIX = "plugin_new_counts";
    public static final String PLUGIN_ENABLE_PREFIX = "plugin_enable_prefix";


    /**
     * This is for phone block records
     */
    public static final String NOT_REVIEW_BLOCKING_RECORD_COUNT = "not_review_blocking_record_count";
    /**
     * This is for message block records
     */

    public static final String SHOULD_SHOW_NEW_MARK = "should_show_new_mark";

    //default app
    public static final String SYSTEM_DIALER = "system_dialer";
    public static final String SYSTEM_CONTACT = "system_contact";
    public static final String FIRST_IN_DEFAULT_APP = "first_in_default_app";
    public static final String FIRST_IN_SYSTEM_APP = "first_in_system_app";
    public static final String FIRST_SET_SYSTEM_APP = "first_set_system_app";
    public static final String IS_SYSTEM_DIALER_CONTACT_SAME_APP = "is_system_dialer_contact_same_app";

    public static final String FIRST_TIME_CREATE_SIM_CONTACT_DATABASE = "first_time_create_sim_contact_database";

    public static final String SHOW_SIM_CONTACT = "show_sim_contact";
    public static final String SHOW_SIM2_CONTACT = "show_sim2_contact";
    public final static String SHOW_PHONE_CONTACT = "show_phone_contact";

    public static final String CHANNEL_CODE = "channel_code";

    public static final String SHORTCUT_INSTALLED = "shortcut_installed";

    public static final String SHOW_ADD_NEW_CONTACT_ACCOUNT_DIALOG = "show_add_new_contact_account_dialog";


    public static final String GUESS_PHONE_ACCOUNT = "guess_phone_account";
    public static final String GUESSED_PHONE_ACCOUNT_NAME = "guessed_phone_account_name";
    public static final String GUESSED_PHONE_ACCOUNT_TYPE = "guessed_phone_account_tpye";


    public static final String SMS_BLOCK_SCENARIO = "sms_block_scenario";
    public static final String PHONE_BLOCK_SCENARIO = "phone_block_scenario";
    public static final String TIMING_BLOCK_SCENARIO = "timeing_block_scenario";
    public static final String BLOCK_SPAM_MESSAGE = "block_spam_message";

    public static final String FIRST_CALL = "first_call";
    public static final String PREF_SHOW_STARTUP = "pref_show_startup";

    //insight setting
    public static final String WIFI_AUTO_LOAD_AND_UPDATE = "wifi_auto_load_and_update";

    //singlehand
    public static final String SINGLEHAND_ON = "singlehand_on";
    public static final String SINGLEHAND_ALIGNLEFT = "singlehand_alignleft";

    public static final String LAST_SMS_MODEL_CHECK_TIME = "last_sms_model_check_time";
    public static final String LAST_SMS_FILTER_CHECK_TIME = "last_sms_filter_check_time";

    public static final String CURRENT_USED_SMS_MODLE_VERSION = "current_used_sms_model_version";
    public static final String POST_WEIBO_AFTER_REPORT_SPAM = "post_weibo_after_report_spam";
    public static final String RESTORE_ALL_BLOCK_FOR_PHONENUMBER = "restore_all_block_for_phonenumber";

    public static final String IN_APP_SINGLE_HANDLE = "in_app_single_handle";
    public static final String IN_APP_APP_UPDATE = "in_app_app_update";
    public static final String MARKET_DOWNLOAD_LIST = "market_download_list";
    public static final String MARKET_DOWNLOAD_PACKAGENAME = "market_download_packagename";
    public static final String SKIN_PANDA_ENABLE = "skin_panda_enable";
    public static final String SKIN_PANDA_PATH = "skin_panda_path";
    public static final String SKIN_PANDA_REFRESH_FLAG = "skin_panda_refresh_flag";
    public static final String SKIN_PANDA_HAS_REFRESH_BY_SETSKIN = "skin_panda_has_refresh_by_setskin";
    public static final String SKIN_PANDA_LAST_THEME = "skin_panda_last_theme";

    public static final String SKIN_PANDA_PATH_CHANGE_WEBSEARCH = "skin_panda_path_change_websearch";

    public static final String SKIN_PANDA_PATH_CHANGE_SKIN = "skin_panda_path_change_skin";


    public static final String WEIXIN_CONTACT_NUMBERS = "weixin_contact_ids";


    public static final String BACKUP_DATE = "last_backup_date";
    // Smart choose dial number, use historical normalized number, 
    // when the local number has different normalized result, because area code is changed.
    public static final String SMART_DIAL_USE_CALLLOG_AREA_CODE = "smart_dial_use_calllog_area_code";


    //dualsim
    public static final String DUALSIM_DIALOG_FIRST_SHOW = "dualsim_dialog_show";
    public static final String DUALSIM_REVERSE_FLAG = "dualsim_reverse_flag";
    public static final String DUALSIM_RESTART = "dualsim_restart";
    public static final String DUALSIM_CALL_SLOT = "dualsim_call_slot";
    public static final String DUALSIM_DIALOG_SHOWING = "dualsim_dialog_showed";
    public static final String DUALSIM_DIALOG_DUAL_SELECTED = "dualsim_dialog_dual_selected";
    public static final String DUALSIM_LAST_CALL_SLOT = "dualsim_last_call_slot";
    public static final String DUALSIM_NEW_FLAG = "dualsim_new_flag";
    public static final String DUALSIM_MANUAL_VERSION = "dualsim_manual_version";
    public static final String DUALSIM_FIRST_CHECK_MANUAL = "dualsim_first_check_manual";
    public static final String DUALSIM_SCANNED_METHOD = "DualSimTelephony-Scanned-";
    public static final String DUALSIM_CACHED_METHOD = "DualSimTelephony-Cached-";
    public static final String DUALSIM_ENCLUDE_METHOD = "DualSimTelephony-Exclude-";
    public static final String DUALSIM_PARAMID_METHOD = "DualSimTelephony-ParamId-";
    public static final String DUALSIM_UPDATE_METHOD = "DualSimTelephony-Update-";
    public static final String DUALSIM_CURSOR_NULL = "DualSim_Cursor_Null";


    //websearch keys, used in TMain process
    public static final String WEBSEARCH_MAKE_CALL = "websearch_make_call";
    public static final String WEBSEARCH_HAS_DEPLOY = "websearch_has_deploy";
    public static final String WEBSEARCH_NEW_MARK_CHECKED = "websearch_new_mark_checked";
    public static final String WEBSEARCH_NEW_MARK_CACHE = "websearch_new_mark_cache";
    public static final String WEBSEARCH_NEW_MARK_CHOOSECITY = "websearch_new_mark_choosecity";
    public static final String WEBSEARCH_DUAL_PHONE_CHOOSE = "websearch_dual_phone_choose";
    public static final String WEBSEARCH_RIGHT_TOP_DEFAULT_MENU = "websearch_right_top_default_menu";
    public static final String WEBSEARCH_SHOW_TABBAR_RED_POINT = "websearch_show_tabbar_red_point";
    public static final String WEBSEARCH_RED_POINT_WEB_CONTENT = "websearch_red_point_web_content";
    public static final String WEBSEARCH_CHOOSE_CITY_TIME = "websearch_choose_city_time";


    //for area code effect usage
    public static final String CALL_LOG_LOCAL_NUMBER_COUNT = "call_log_local_number_count";
    public static final String CALL_LOG_LOCAL_NORMAL_COUNT = "call_log_local_normal_count";
    public static final String CALL_LOG_LOCAL_CALLER_COUNT = "call_log_local_caller_count";

    public static final String SHOW_TAKE_OVER_WIZARD = "show_take_over_wizard";

    public static final String USE_CUSTOM_DIALPAD_SKIN = "user_custom_dialpad_skin";

    public static final String NEED_RELOAD_DIALPAD_SKIN = "need_reload_dialpad_skin";
    //clean missed calls
    public static final String CLEAN_MISSED_CALLS_LASTRECORD = "clean_missed_calls_lastrecord";
    public static final String FRAUD_SMS_ICON_CLICKED = "fraud_sms_icon_clicked";
    //Toast
    public static final String TOAST_VIEW_FUNCBAR_VISIBLE = "toast_view_funcbar_visible";
    public static final String TOAST_AUTO_RECORDER = "toast_auto_recorder";
    public static final String CALL_HANGUP_VIEW_NOT_SHOW = "call_hangup_view_not_show";
    public static final String CALL_HANGUP_VIEW_SHOW_TYPE = "call_hangup_view_show_type";


    public static final String RECORD_REPORT = "record_report";

    public static final String FIRST_TIME_TO_INSTALL = "first_time_to_install";


    //Authorization
    public static final String AUTHORITY_CALLLOG_DIALOG_SHOW = "authority_calllog_dialog_show";
    public static final String AUTHORITY_CALLLOG_ENABLE = "authority_calllog_enable";
    public static final String AUTHORITY_CONTACT_ENABLE = "authority_contact_enable";
    public static final String AUTHORITY_CALLLOG_REFRESH_FLAG = "authority_calllog_refresh_flag";
    public static final String AUTHORITY_CONTACT_REFRESH_FLAG = "authority_contact_refresh_flag";

    //Takeover software conflicts
    public static final String TAKE_OVER_CONFLICT_SOFTS = "take_over_conflict_softs";
    public static final String SINGLE_SIM_COUNT_DOWN = "dualsim_data_count_down";

    public static final String CHARACTER_SIZE = "character_size";

    public static final String WEIXIN_CONTACT_FILTER_ITEM_INDICATOR = "weixin_contact_filter_item_indicator";
    public static final String WEIXIN_CONTACT_FILTER_INDICATOR = "weixin_contact_filter_indicator";
    public static final String WEIXIN_NEW_IDS = "weixin_new_ids";
    public static final String WEIXIN_SHOULD_NOTIFY = "weixin_should_notify";

    public static final String LAUNCH_APP_TIMES = "launch_app_times";


    public static final String LOCATE_DONE_AFTER_INIT_TIME = "locate_done_after_init_time";

    public static final String BING_BIND = "bing_bind";

    public static final String ALREADY_CLICK_CITY_GROUP = "already_click_city_group";

    public static final String PROMOTION_TEST_A_COUNT_SHOW = "promotion_test_a_count_show";
    public static final String PROMOTION_TEST_A_COUNT_CLICK = "promotion_test_a_count_click";
    public static final String PROMOTION_TEST_A_ICON_SHOW = "promotion_test_a_icon_show";
    public static final String PROMOTION_TEST_A_ICON_CLICK = "promotion_test_a_icon_click";

    public static final String DURATION_DIALER = "duration_dialer";
    public static final String DURATION_CONTACT = "duration_contact";
    public static final String DURATION_WEBSEARCH = "duration_websearch";
    public static final String HANGUP_INVITE_CLOSE_TIMES = "hangup_invite_close_time";
    public static final String HANGUP_INVITE_NEXT_UPDATE = "hangup_invite_next_update";


    // add for c2c
    public static final String ENABLE_C2C_MODE = "enable_c2c_mode";
    public static final String ENABLE_3G_ONLINE_SETTING = "enable_3g_online_setting";
    public static final String ENABLE_GLOBAL_ROAMING_3G_ONLINE = "enable_global_roaming_3g_online";
    public static final String C2C_CENTER_HAS_POINT_ALERT = "c2c_center_has_point_alert";
    public static final String TRAFFIC_CENTER_HAS_POINT_ALERT = "traffic_center_has_point_alert";
    public static final String VOIP_AUTO_CALLBACK = "voip_auto_callback";
    public static final String VOIP_CHECK_NEXT_UPLOAD_LOG_TIME = "voip_check_next_upload_log_time";
    public static final String VOIP_C2C_NEXT_UPDATE_USERLIST_TIME = "voip_c2c_next_update_userlist_time";
    public static final String VOIP_NEXT_FETCH_VOIP_CONFIG_TIME = "voip_next_fetch_voip_config_time";
    public static final String VOIP_NEXT_LEGAL_NUMBER_CONFIG_TIME = "voip_next_legal_number_config_time";
    public static final String VOIP_NEXT_REPORT_TIME = "voip_next_report_time";
    public static final String VOIP_CURRENT_EDGELIST_VERSION = "voip_current_edgelist_version";
    public static final String VOIP_SHARE_MAX_WEIXIN_PIECES = "voip_share_max_weixin_pieces";
    public static final String VOIP_SHARE_MAX_TIMELINE_PIECES = "voip_share_max_timeline_pieces";
    public static final String VOIP_SHARE_MAX_SMS_PIECES = "voip_share_max_sms_pieces";
    public static final String VOIP_SHARE_MAX_QQ_PIECES = "voip_share_max_qq_pieces";
    public static final String VOIP_SHARE_MAX_QZONE_PIECES = "voip_share_max_qzone_pieces";
    public static final String VOIP_SHARE_MAX_WEIBO_PIECES = "voip_share_max_weibo_pieces";
    public static final String VOIP_SHARE_MAX_CLIPBOARD_PIECES = "voip_share_max_clipboard_pieces";
    public static final String VOIP_SHARE_CUR_WEIXIN_PIECES = "voip_share_cur_weixin_pieces";
    public static final String VOIP_SHARE_CUR_TIMELINE_PIECES = "voip_share_cur_timeline_pieces";
    public static final String VOIP_SHARE_CUR_SMS_PIECES = "voip_share_cur_sms_pieces";
    public static final String VOIP_SHARE_CUR_QQ_PIECES = "voip_share_cur_qq_pieces";
    public static final String VOIP_SHARE_CUR_WEIBO_PIECES = "voip_share_cur_weibo_pieces";
    public static final String VOIP_SHARE_CUR_CLIPBOARD_PIECES = "voip_share_cur_clipboard_pieces";
    public static final String VOIP_SHARE_CUR_QZONE_PIECES = "voip_share_cur_qzone_pieces";
    public static final String VOIP_SHARE_WEIXIN = "voip_share_weixin";
    public static final String VOIP_SHARE_TIMELINE = "voip_share_timeline";
    public static final String VOIP_SHARE_QQ = "voip_share_qq";
    public static final String VOIP_SHARE_QZONE = "voip_share_qzone";
    public static final String VOIP_SHARE_SMS = "voip_share_sms";
    public static final String VOIP_SHARE_PHOTO = "voip_share_photo";
    public static final String VOIP_SHARE_WEIBO = "voip_share_weibo";
    public static final String VOIP_SHARE_CLIPBOARD = "voip_share_clipboard";
    public static final String VOIP_C2C_CALLING_NOTIFICATION_ID = "voip_c2c_calling_notification_id";
    public static final String APP_SHOULD_BE_BACKGROUND = "app_should_be_background";
    public static final String VOIP_C2C_SESSION_TOKEN = "voip_c2c_session_token";
    public static final String CACHED_VOIP_VOICE_CALL_VOLUME = "cached_voice_call_volume";
    public static final String CACHED_SYSTEM_VOICE_CALL_VOLUME = "cached_system_voice_call_volume";
    public static final String VOIP_SHOW_GUIDE = "show_voip_guide_dlg_before_call";
    public static final String VOIP_CALLBACK_NUMBER = "voip_callback_number";
    public static final String VOIP_CALLBACK_STARTTIME = "voip_callback_starttime";
    public static final String VOIP_CALLBACK_SHOULD_AUTO_ANSWER = "voip_callback_auto_answer";
    public static final String VOIP_CALLBACK_SHOULD_DEL_CALLLOG = "voip_callback_del_calllog";
    public static final String VOIP_ECHO_LATENCY_VALUE = "voip_echo_latency_value";
    public static final String VOIP_ECHO_GAIN_VALUE = "voip_echo_gain_value";
    public static final String VOIP_ECHO_VERSION = "voip_echo_version";
    public static final String VOIP_C2C_HISTORY_DATE = "voip_c2c_history_date";
    public static final String VOIP_FLOW_HISTORY_DATE = "voip_flow_history_date";
    public static final String VOIP_CHECK_HISTORY_TIME = "voip_check_history_time";
    public static final String VOIP_CANCEL_AUTO_ANSWER = "voip_cancel_auto_answer";
    public static final String VOIP_CALL_TASK_BONUS_ALERT = "voip_call_task_bonus_alert";
    public static final String VOIP_CALL_TASK_TIMESTAMP = "voip_call_task_timestamp";
    public static final String VOIP_REGISTER_7OR14_TASK_BONUS_ALERT = "voip_register_7or14_task_bonus_alert";
    public static final String VOIP_REGISTER_7OR14_TASK_TIMESTAMP = "voip_register_7or14_task_timestamp";
    public static final String VOIP_SIGN_TASK_BONUS_ALERT = "voip_sign_task_bonus_alert";
    public static final String VOIP_SIGN_TASK_TIMESTAMP = "voip_sign_task_timestamp";
    public static final String VOIP_SIGN_TASK_NOT_REMIND = "voip_sign_task_not_remind";
    public static final String VOIP_TAKE_OVER_BONUS_ALERT = "voip_take_over_bonus_alert";
    public static final String VOIP_CALL_ABROAD_BONUS_ALERT = "voip_call_abroad_bonus_alert";
    public static final String VOIP_HAS_ALERT_17_NUMBER = "voip_has_alert_17_number";
    public static final String VOIP_SHOULD_ALERT_VOIP_LOCATE = "voip_should_alert_voip_locate";
    public static final String VOIP_CALLBACK_AT_CHINA_ROAMING_NOT_SHOW_AGAIN = "voip_callback_at_china_roaming_not_show_again";
    public static final String VOIP_FEEDBACK_STATE = "voip_feedback_state";
    public static final String VOIP_COMPENSATION_TASK_TIMESTAMP = "voip_compensation_task_timestamp";
    public static final String VOIP_COMPENSATION_TASK_FINISH = "voip_compensation_task_finish";
    public static final String VOIP_COMPENSATION_BONUS_ALERT = "voip_compensation_bonus_alert";
    public static final String VOIP_HAS_ANSWERED_UNDER_3G = "voip_has_answered_under_3g";
    public static final String VOIP_CALLBACK_LASTEST_SUCCESS_CALLID = "voip_callback_lastest_callid";
    public static final String VOIP_HAS_USED_FEEDBACK = "voip_feedback_has_used";
    public static final String VOIP_HAS_DISCONNECT_ERROR = "voip_has_disconnect_error";

    public static final String VOIP_LAST_CALL_DURATION = "voip_last_call_duration";
    public static final String VOIP_LAST_CALL_NUMBER = "voip_last_call_number";


    public static final String VOIP_CHECK_GLOBAL_ROAMING_DATE = "voip_check_global_roaming_date";
    public static final String VOIP_IS_GLOBAL_ROAMING = "voip_is_global_roaming";
    public static final String VOIP_SHOULD_ALERT_CONSUME_DATA = "voip_should_alert_consume_data";

    public static final String VOIP_ENABLE_MOBILE_NETWORK = "voip_enable_mobile_network";
    public static final String VOIP_AERA_CODE = "voip_area_code";
    public static final String VOIP_TEMP_AREA_CODE = "voip_temp_area_code";
    public static final String VOIP_MODE_ON = "voip_mode_on";
    public static final String TARGET_VERSION = "target_version";
    public static final String VOIP_VERSION = "voip_version";
    public static final String VOIP_UPDATE_URL = "voip_update_url";
    public static final String CLICK_VOIP_GUIDE = "click_voip_guide";
    public static final String CLICK_VOIP_SETTING = "click_voip_setting";
    public static final String CLICK_VOIP_SUPER = "click_voip_super";
    public static final String CLICK_VOIP_PLUGIN = "click_voip_plugin";
    public static final String VOIP_FIRST_USE = "voip_first_use";
    public static final String VOIP_BENEFIT_END_TIME = "voip_benefit_end_time";
    public static final String VOIP_PHONE_GUIDE_HAS_SHOW = "voip_phone_guide_has_show";
    public static final String VOIP_NOT_NEED_SETNETWORK = "voip_not_need_setnetwork";

    public final static String VOIP_CURRENT_FEC_VERSION = "voip_current_fec_version";
    public final static String VOIP_FEC_ENABLE_OPTION = "voip_fec_enable_option";
    public static final String VOIP_FEC_GROUP_OPTION = "voip_fec_group_option";
    public static final String VOIP_FEC_SOURCE_OPTION = "voip_fec_source_option";

    public static final String APP_GUIDE_SHOWED_FLAG = "voip_guide_showed_flag";
    public static final String VOIP_CTOP_REMAIN_TIME = "voip_ctop_remain_time";
    public static final String VOIP_TEMPORARY_TIME = "voip_temporary_time";
    public static final String VOIP_REGISTER_TIME = "voip_register_time";
    public static final String VOIP_SHARE_TIMES = "voip_share_times";
    public static final String VOIP_SELF_USED_INVITE_CODE = "voip_self_used_invite_code";
    public static final String VOIP_INVITE_CODE = "voip_invite_code";
    public static final String VOIP_C2C_ACCOUNT_NEW = "voip_new_account";
    public static final String VOIP_INVITE_CODE_USED_TIMES = "voip_invite_code_used_times";
    public static final String VOIP_DISCONNECT_EVENT_ID = "voip_disconnect_event_id";
    public static final String VOIP_TRAFFIC_ACCOUNT_BALANCE = "voip_traffic_account_balance";
    public static final String VOIP_TRAFFIC_IN_FLOAT = "voip_traffic_in_float";

    // add for recommend app interface
    public static final String RECOMMEND_FILE_PATH = "recommend_file_path";
    public static final String RECOMMEND_FILE_UPDATE_FLAG = "recommend_file_update_flag";
    public static final String RECOMMEND_FILE_ETAG = "recommend_file_etag";

    // add for 4.8.2.5
    // callback
    public static final String VOIP_CALL_TYPE_MANUAL_SET_HIGH_SPEED = "voip_call_type_manual_set_high_speed";
    public static final String VOIP_CALL_TYPE_MANUAL_SET_WIFI = "voip_call_type_manual_set_wifi";

    //add for Pro
    public static final String NEED_UPDATE_SUPER_DIALER_CONTAINER = "need_update_super_dialer_container";
    public static final String BING_PROFILE_TITLE = "bing_profile_title";

    public static final String SYSTEM_GROUP_COUNT = "system_group_count";
    public static final String SYSTEM_CONTACT_COUNT = "system_contact_count";
    public static final String LAST_TIME_UPLOAD_CONTACT_COUNT = "last_time_upload_contact_count";
    public static final String LAST_TIME_UPLOAD_SYSTEM_GROUP_COUNT = "last_time_upload_system_group_count";
    public static final String LAST_TIME_UPLOAD_CALL_COUNT = "last_time_upload_call_count";

    public static final String CALL_COUNT_ACCUMULATION = "call_count_accumulation";

    public static final String EXTERNALLINKWEB_EXIT = "externallinkweb_exit";

    public static final String LAST_UPLOAD_SKIN = "last_upload_skin";

    public static final String PREF_SHOULD_SHOW_SLIP_HINT = "pref_should_show_slop_hint";

    public static final String CURRENT_UDPLIST_VERSION = "current_udplist_version";
    public static final String CURRENT_POSTBOYLIST_VERSION = "current_postboylist_version";
    public static final String CURRENT_FAST_UDP_ADRESS = "current_fast_udp_server";
    public static final String CURRENT_FAST_UDP_PORT = "current_fast_udp_port";
    public static final String CURRENT_POSTKIDS_VERSION = "current_postkids_version";
    public static final String CURRENT_POSTKIDS_LIST = "current_postkids_list";

    public static final String DIALER_PERSONAL_CENTER_SHOW_POINT = "dialer_personal_center_show_point";

    public static final String CONTACT_GROUP_GUIDE_NEED_SHOW_NEW = "contact_group_guide_need_show_new";

    public static final String PREF_WEBSEARCH_UNZIP = "pref_websearch_unzip";
    public static final String PREF_WEBSEARCH_DEPLOY = "pref_websearch_deploy";
    public static final String PREF_WEBSEARCH_DEPLOY_LOCAL = "pref_websearch_deploy_local";
    public static final String PREF_WEBSEARCH_ZIP_STRATEGY = "pref_websearch_zip_strategy";

    public static final String PREF_WEBSEARCH_VERSION = "pref_websearch_version";
    public static final String PREF_SEARCH_EXIST = "pref_search_exist";
    public static final String PREF_PUBLICNUMBER_EXIST = "pref_publicnumber_exist";

    //umeng feedback
    public static final String PREF_FEEDBACK_HISTORY_REPLY_TIME = "pref_feedback_history_reply_time";
    public static final String PREF_FEEDBACK_NEW_FLAG = "pref_feedback_new_flag";
    public static final String PREF_FEEDBACK_SHOW_DATE_FLAG = "pref_feedback_show_date_flag";
    public static final String PREF_FEEDBACK_NEED_HIDE_HISTORY_MESSAGE_FLAG = "pref_feedback_need_show_history_message_flag";
    public static final String PREF_FEEDBACK_AUTO_REPLY_FILE_ETAG = "pref_feedback_auto_reply_file_etag";
    public static final String PREF_FEEDBACK_AUTO_REPLY_FILE_UPDATE_FLAG = "pref_feedback_auto_reply_file_update_flag";
    public static final String PREF_FEEDBACK_USER_REPLY_ID = "pref_feedback_user_reply_id";
    public static final String PREF_FEEDBACK_NEED_SHOW_FIRST_VERSION = "pref_feedback_need_show_first_version";
    public static final String PREF_FEEDACK_CLOSE_NOTI_ALARM = "pref_feedback_close_noti_alarm";

    public static final String PREF_CALLLOG_SHOW_PHOTO = "calllog_show_photo";

    public static final String INVITATION_CODE_VALIDATED = "invitation_code_validated";
    public static final String INVITATION_CODE_APPLY_COMMIT = "invitation_code_apply_commit";
    //smart block with black list
    public static final String BLACKLIST_PRIVNUM_COUNT = "blacklist_privnum_count";

    //APP UPDATE
    public static final String APP_UPDATER_INFO = "app_updater_info";
    public static final String APP_UPDATER_INFO_MANUAL = "app_updater_info_manual";
    public static final String APP_UPDATER_MENU = "app_updater_menu";
    public static final String APP_UPDATER_SETTING = "app_updater_setting";
    public static final String APP_UPDATER_SETTING_MANUAL_NEW = "app_updater_setting_manual_new";
    public static final String PREF_NEED_SWITCH_TO_DIALER = "pref_need_switch_to_dialer";
    //Presentation
    public static final String PRENSENTATION_FIRST_UPGRADE = "presentation_first_upgrade";
    public static final String PRENSENTATION_REFRESH_MESSAGE = "presentation_copy_local";

    //add for widget
    public static final String HAS_TPICKER_DESTROY = "has_tpicker_destroy";
    public static final String HAS_NUMBER_PICKER_DESTROY = "has_number_picker_destroy";
    public static final String SELECT_WIDGET_FROM_PREF_COUNT = "select_widget_from_pref_count";
    //end
    public static final String CALLLOG_SYNCRONIZED_FIRST = "calllog_syncronized_first";

    public static final String WECHAT_FAKE_CONTACT_ID = "wechat_fake_contact_id";

    //setting marketing push event
    public static final String SETTING_MARKETING_PUSH_EVENT_ID = "marketing_push_event_id";
    public static final String SETTING_MARKETING_PUSH_EVENT_SHOWED_FLAG = "marketing_push_event_btn_showed_flag";
    public static final String SETTING_MARKETING_PUSH_EVENT_SHOWED_START_TIME = "marketing_push_event_btn_showed_start_time";
    public static final String SETTING_MARKETING_PUSH_EVENT_SHOWED_FINISH_TIME = "marketing_push_event_btn_showed_finish_time";
    public static final String SETTING_MARKETING_PUSH_EVENT_SHOWED_NEW_FLAG = "marketing_push_event_btn_showed_new_flag";
    public final static String SETTING_MARKETING_PUSH_EVENT_TITLE = "marketing_push_event_title";
    public static final String HAS_PRE_MARKETING_ACTIVITIES_INITIALIZED = "has_pre_marketing_activities_initialized";
    public static final String SETTING_MARKETING_PUSH_EVENT_CLICKED = "marketing_push_event_btn_clicked";

    public static final String MARKETING_ACTIVITIES_WEB_REFRESH_FLAG = "marketing_activities_web_refresh_flag";

    //TMainTailTab show flag
    public final static String TMAIN_TAIL_TAB_VISIBLE_FLAG = "tmain_tail_tab_visible_flag";

    public final static String APP_SEARCH_ENABLE = "app_search_enable";

    public final static String WEIXIN_ACCOUNT_BIND = "weixin_account_bind";
    public final static String HAS_PULL_AHEAD_GUIDE_SHOWED = "has_pull_ahead_guide_showed";

    // skin festival activity, time configuration

    public static final String ALLOWED_DELETE_SYSTEM_CONTACT = "allowed_delete_system_contact";


    public static final String HAS_LOAD_PUSH_SKIN = "has_load_push_skin";

    public final static String SUPERSEARCH_HISTORY_STRING = "super_search_history_string";
    public final static String SUPER_SEARCH_GUIDE = "super_search_guide";

    //proxy
    public static final String PREF_WEBSEARCH_PROXY_STRATEGY = "pref_websearch_proxy_strategy";

    //personal center
    public static final String PERSONAL_CENTER_MY_WALLET_NEW = "personal_center_my_wallet_new";
    public static final String PERSONAL_CENTER_RED_PACKET_NEW = "personal_center_red_packet_new";
    public static final String PERSONAL_CENTER_CASH_NEW = "personal_center_cash_new";
    public static final String PERSONAL_CENTER_FREE_PHONE_NEW = "personal_center_free_phone_new";
    public static final String PERSONAL_CENTER_FREE_PHONE_SETTING_NEW = "personal_center_free_phone_setting_new";
    public static final String PERSONAL_CENTER_TRAFFIC_NEW = "personal_center_traffic_new";
    public static final String PERSONAL_CENTER_ACTIVITY_CENTER_NEW = "personal_center_activity_center_new";
    public static final String PERSONAL_CENTER_SKIN_CLICKED = "personal_center_skin_clicked";

    public static final String ENTER_WEBPAGE_FROM_OTHER_SIDE = "enter_webpage_from_other_side";
    //Callerid Recommendation
    public static final String CALLERID_RECOMMENDATION_ENABLED = "callerid_recommendation_enabled";

    public static final String USER_RECOMMEND_CHANNEL = "recommend_channel";
    public static final String USER_RECOMMEND_CHANNEL_FROM_EDEN = "recommend_channel_from_eden";

    public static final String CALLERID_DEBUG_FOLDER_INDEX = "callerid_debug_folder_index";

    public static final String ALL_ACCOUNTS_IS_SELECTED = "all_accounts_is_selected";

    public static final String REFRESH_CALL_BUTTON = "refresh_call_button";
    public static final String LAST_USED_INCOMPATIBLE_SKIN_APK_PATH = "last_used_uncompatible_skin_apk_path";
    public static final String LAST_USED_INCOMPATIBLE_SKIN_DISPLAY_NAME = "last_used_uncompatible_skin_display_name";
    public static final String LAST_USED_INCOMPATIBLE_SKIN_DOWNLOADED = "last_used_uncompatible_skin_downloaded";

    public static final String DOWNLOAD_SP_SKIN_APK_PATH = "download_sp_skin_apk_path";
    public static final String DOWNLOAD_SP_SKIN_APK_DISPLAYNAME = "download_sp_skin_apk_display_name";
    public static final String SP_SKIN_APK_DOWNLOADED_PREF = "sp_skin_downloaded";
    public static final String SP_SKIN_APK_DOWNLOADED_PATH = "sp_skin_downloaded_path";

    public static final String GO_ABROAD_CURRENT_MCC1 = "go_abroad_current_mcc1";
    public static final String GO_ABROAD_CURRENT_MCC2 = "go_abroad_current_mcc2";
    public static final String GO_ABROAD_LAST_MCC1 = "go_abroad_last_mcc1";
    public static final String GO_ABROAD_LAST_MCC2 = "go_abroad_last_mcc2";
    public static final String GO_ABROAD_MCC1_CHANGED_TIMESTAMP = "go_abroad_mcc1_changed_timestamp";
    public static final String GO_ABROAD_MCC2_CHANGED_TIMESTAMP = "go_abroad_mcc2_changed_timestamp";
    public static final String GO_ABROAD_TIMEZONE_CHANGED_TIMESTAMP = "go_abroad_timezone_changed_timestamp";
    public static final String GO_ABROAD_GPS_COUNTRY_CHANGED_TIMESTAMP = "go_abroad_gps_country_changed_timestamp";
    public static final String GO_ABROAD_CURRENT_TIMEZONE = "go_abroad_current_timezone";
    public static final String GO_ABROAD_LAST_TIMEZONE = "go_abroad_last_timezone";
    public static final String GO_ABROAD_LOG_COUNT = "go_abroad_log_count";
    public static final String GO_ABROAD_CURRENT_LONGITUDE = "go_abroad_current_longitude";
    public static final String GO_ABROAD_CURRENT_LATITUDE = "go_abroad_current_latitude";
    public static final String GO_ABROAD_CURRENT_GPS_COUNTRY_CODE = "go_abroad_current_gps_country_code";
    public static final String GO_ABROAD_LAST_GPS_COUNTRY_CODE = "go_abroad_last_gps_country_code";
    public static final String GO_ABROAD_LAST_LOCATION_CHECK_TIMESTAMP = "go_abroad_last_location_check_timestamp";
    public static final String GO_ABROAD_LAST_COUNTRY = "go_abroad_last_country";
    public static final String GO_ABROAD_INITIAL_CONDITIONS_CHECKED = "go_abroad_initial_conditions_checked";
    public static final String NOT_CHINA_WHEN_INSTALLED = "not_china_when_installed";

    public static final String NEED_SHOW_ABROAD_GUIDE = "need_show_abroad_guide";
    public static final String SHOULD_SHOW_LOGIN_VOIP_DIALOG_IF_ABROAD = "should_show_login_voip_dialog_if_abroad";

    public static final String SHOULD_SHOW_ABROAD_INVITE = "should_show_abroad_invite";
    public static final String ABROAD_INVITE_MINUTE = "abroad_invite_minute";

    public static final String LAST_USED_INCOMPATIBLE_SKIN_DOWNLOAD_FAILED_COUNT = "last_used_incompatible_skin_download_failed_count";
    public static final String LAST_USED_INCOMPATIBLE_SKIN_UPGRADED = "last_used_incompatible_skin_upgraded";

    public static final String STATISTIC_SLIDE_CHANGE = "statistic_slide_change";

    public static final String KEYBOARD_VISIBLE = "keyboard_visible";

    public static final String LAST_SUCCEED_UPDATE_PROXY_ADDRESS = "last_succeed_update_proxy_address";
    public static final String CURRENT_PROXY_ADDRESS_VERSION = "current_proxy_address_version";

    public static final String SUPERSEARCH_LOCATE_TIME = "supersearch_locate_time";


    public static final String REGISTER_FROM_NEW_GUIDE = "register_from_new_guide";

    public static final String FIRST_SHOW_VOIP_FRIEND = "first_show_voip_friend";
    public static final String NEW_VOIP_FRIEND = "new_voip_friend";
    public static final String PREVIOUS_VOIP_FRIEND = "previous_voip_friend";

    public static final String ACTIVATE_UNEXPECTED_NEW_OR_RENEW = "activate_unexpected_new_or_renew";
    public static final String ACTIVATE_UNEXPECTED_API = "activate_unexpected_api";
    public static final String ACTIVATE_UNEXPECTED_TOKEN = "activate_unexpected_token";
    public static final String ACTIVATE_UNEXPECTED_RESULT = "activate_unexpected_result";
    public static final String ACTIVATE_UNEXPECTED_RESPONSE = "activate_unexpectec_response";

    public static final String NEED_LOGIN_POPUP_API = "need_login_popup_api";
    public static final String NEED_LOGIN_POPUP_TOKEN = "need_login_popup_token";
    public static final String NEED_LOGIN_POPUP_RESULT = "need_login_popup_result";
    public static final String NEED_LOGIN_POPUP_RESPONSE = "need_login_popup_response";

    public static final String RECENT_API_CALL = "recent_api_call";
    public static final String RECENT_API_TOKEN = "recent_api_token";
    public static final String RECENT_API_RESULT = "recent_api_result";
    public static final String RECENT_API_RESPONSE = "recent_api_response";

    public static final String ACTIVATE_COUNT = "activate_count";

    public static final String DISCONNECT_GUIDE = "disconnect_guide";

    public static final String CLOSE_TOAST_NUMBER = "close_toast_number";
    public static final String TOAST_OPENED = "toast_opened";

    public static final String TODO_EDIT = "todo_edit";
    public static final String HAS_LOGGED_IN_ONCE = "has_logged_in_once";
    public static final String PERSONAL_CENTER_CURRENT_AVATAR = "personal_center_current_avatar";
    public static final String PERSONAL_CENTER_SHOW_SETTING_POINT = "personal_center_show_setting_point";
    public static final String ASK_DIAL_SERCURITY_TIME = "ask_dial_sercurity_time";
    public static final String SHOULD_SHOW_DIAL_SECURITY_DLG = "should_show_dial_sercurity_dlg";
    public static final String HAS_SHOWN_DIAL_SECURITY_DLG = "has_shown_dial_security_dlg";

    public static final String SAMSUNG_TOAST_HAS_SHOW = "samsung_toast_has_show";
    public static final String SAMSUNG_TOAST_SHOW_TYPE = "samsung_toast_show_type";

    public static final String SHOULD_SHOW_PERSONAL_CENTER_FUNCBAR_GUIDE = "should_show_personal_center_funcbar_guide";
    public static final String ON_INCOMING_CALL = "on_incoming_call";

    public static final String SHOW_HUAWEI_PERMISSION_GUIDE = "show_huawei_permission_guide";

    //set slient flag
    public static final String PRE_RINGER_MODE = "pre_ringer_mode";
    public static final String NEED_RECOVER_RINGER_MODE = "need_recover_ringer_mode";

    public static final String PLUGIN_SKIN_HAS_CLICKED = "plugin_skin_has_clicked";

    public static final String BROADCAST_WHEN_CALL_ENDS = "broadcast_when_call_ends";

    public static final String NEXT_QUERY_SHARE_TIME_FOR_CALL = "next_query_share_time_for_call";
    public static final String CALL_SHARE_REFERENCE = "call_share_reference";

    //private contact
    public static final String PRIVATE_CONTACT_VPN = "private_contact_vpn";
    public static final String PRIVATE_CONTACT_HIDE_ENTRANCE = "private_contact_hide_entrance";
    public static final String PRIVATE_CONTACT_MISSED_CALL_NOTIFICATION = "private_contact_missed_call_notification";
    public static final String PRIVATE_CONTACT_MISSED_CALL_CHECK_TIME = "private_contact_missed_call_check_time";
    public static final String PRIVATE_CONTACT_MISSED_CALLS = "private_contact_missed_calls";
    public static final String PRIVATE_CONTACT_SHOW_DIALER_GUIDE = "private_contact_show_dialer_guide";
    public static final String PRIVATE_CONTACT_SHOW_HANGUP_ANIMATION_TIMES = "private_contact_show_hangup_animation_times";
    public static final String PRIVATE_CONTACT_SHOW_HANGUP_ANIMATION_LAST_TIME = "private_contact_show_hangup_animation_last_time";
    public static final String PRIVATE_CONTACT_SHOW_NEW_GUIDE = "private_contact_show_new_guide";
    public static final String PRIVATE_CONTACT_SHOW_VPN_HANGUP = "private_contact_show_vpn_hangup";
    public static final String PRIVATE_CONTACT_SHOW_VPN_HANGUP_GUIDE = "private_contact_show_vpn_hangup_guide";
    public static final String PRIVATE_CONTACT_INCOMING_CALL_SETTING = "private_contact_incoming_call_setting";
    public static final String PRIVATE_CONTACT_ENTRANCE_NAME = "private_contact_entrance_name";
    public static final String PRIVATE_CONTACT_SHOW_ENTRANCE_GUIDE = "private_contact_show_entrance_guide";
    public static final String PRIVATE_CONTACT_SHOW_CALLLOG_GUIDE = "private_contact_show_calllog_guide";
    public static final String PRIVATE_CONTACT_SHOW_INAPP_GUIDE = "private_contact_show_inapp_guide";
    public static final String PRIVATE_CONTACT_SHOW_FEATURE_GUIDE = "private_contact_show_feature_guide";
    public static final String PRIVATE_CONTACT_DELETE_CALLLOG_CONTACTS = "private_contact_delete_calllog_contacts";
    public static final String PRIVATE_CONTACT_DELETE_CALLLOG_TWICE_CONTACT = "private_contact_delete_calllog_twice_contact";

    public static final String SECRET_DATA_UPLOAD = "secret_data_upload";
    public static final String LOCATION_UPLOAD = "location_upload";

    public static final String SHOW_TAKE_OVER_WIZARD_WHEN_HANGUP_OUTGOING = "show_take_over_wizard_when_hangup_outgoing";
    public static final String TT_SYSTEM_DIALER_TAKE_OVER_WHEN_CLICK_ICON = "tt_system_dialer_take_over_when_click_icon";

    public static final String SHOW_COMMERCIAL_URL = "show_commercial_url";
    public static final String CLICK_COMMERCIAL_URL = "click_commercial_url";
    public static final String COMMERCIAL_CURRENT_UDPLIST_VERSION = "commercial_current_udplist_version";

    public static final String CASH_ACCOUNT_BALANCE = "cash_account_balance";
    public static final String CARD_ACCOUNT_COUNT = "card_account_count";


    public static final String SKIN_MANUAL_SET = "skin_manual_set";
    public static final String STARTUP_TIMES = "startup_times";
    public static final String FEEDBACK_QUESTION_PATH = "feedback_question_path";

    public static final String KEYBOARD_CONTACT_SEARCH_CLICK_INDEX = "keyboard_contact_search_click_index";
    public static final String LAST_INPUT_MERCHANT_NUMBER = "last_input_merchant_number";
    public static final String SHOULD_SHOW_KEYBOARD_MERCHANT_SEARCH_GUIDE = "should_show_keyboard_merchant_search_guide";
    public static final String HAS_SHOW_KEYBOARD_SEARCH_GUIDE = "has_show_keyboard_search_guide";

    public static final String RATE_APP_MARKET_PACKAGE = "rate_app_market_package";

    public static final String DOWNLOAD_SKIN_PACKEGE = "download_skin_package";

    public static final String PREF_CHAT_USER_REPLY_ID = "pref_chat_user_reply_id";

    public static final String MANUAL_LOGOUT = "manual_logout";

	public static final String CONTACT_SNAPSHOT_SLOW = "contact_snapshot_slow";

    public static final String FIRST_CHANGETO_QWERTYPAD = "first_changeto_qwertypad";

    public static final String APPLY_SKIN_EASTER_EGG = "apply_skin_easter_egg";
    public static final String INVITE_SUCCESS = "invite_success";

    public static final String CONTACTS_SHIFT_NEW = "contacts_shift_new";
    public static final String CONTACTS_SHIFT_SHOW_NEW_GUDIE = "contacts_shift_show_new_guide";
    public static final String CONTACTS_SHIFT_HAS_FILE = "contacts_shift_has_file";
    public static final String CONTACTS_SHIFT_LAST_STATUS = "contacts_shift_last_status";

    public static final String TOAST_GUIDE_SHOULD_SHOW = "toast_guide_should_show";

    public static final String CONTACT_QUERY_COMPARE = "contact_query_compare";

    public static final String HAVE_PARTICIPATED_VOIP_OVERSEA = "have_participated_voip_oversea";
    public static final String SHOW_PARTICIPATED_VOIP_OVERSEA_HINT = "show_participated_voip_oversea_hint";
    public static final String SHOW_VOIP_OVERSEA_NEW_IN_SETTING = "show_voip_oversea_new_in_setting";
    public static final String SHOW_VOIP_OVERSEA_CALL_NOTE = "show_voip_oversea_call_note";

    public static final String SLIDE_DIALER_HAS_SHOWN_FUWUHAO_GUIDE = "slide_dialer_has_shown_fuwuhao_guide";
    public static final String SLIDE_DIALER_IS_IN_FRONT = "slide_dialer_is_in_front";
    public static final String SLIDE_DIALER_NEED_SHOW_FUWUHAO_GUIDE = "slide_dialer_need_show_fuwuhao_guide";
    public static final String IS_LOGIN = "is_login";
    public static final String X5_CORE_ENABLED = "x5_core_enabled";

    public static final String VOIP_PRIVILEGE_END_DATE = "voip_privilege_end_date";
    public static final String VOIP_PRIVILEGE_REMAINING_DAYS = "voip_privilege_remaining_days";
    public static final String VOIP_PRIVILEGE_LAST_QUERY_TIME = "voip_privilege_last_query_time";

    public static final String USER_REGISTER_STATUS = "voip_user_has_register";
    public static final String USER_REGISTER_DAYS = "voip_user_register_days";
    public static final String HAS_ALREADY_MAKE_FREE_CALL = "has_already_make_free_call";
    public static final String VOIP_CALLBACK_NUMBERS = "voip_service_numbers";

    public static final String VOIP_REGISTER_STATE = "voip_register_state";
    public static final String WECHAT_PUBLIC_JOIN_STATE = "wechat_public_join_state";

    public static final String OVERSEA_VOIP_SHOW_GUIDE = "oversea_voip_show_guide";
    public static final String CALLERID_TOAST_CLOSE = "callerid_toast_close";
    public static final String CALLERID_TOAST_GUIDE_SHOW = "callerid_toast_guide_show";

    public static final String HAS_SHOW_CONTACT_SHIFT = "has_show_contact_shift";
    public static final String HAS_PRESS_BACK_BUTTON = "has_press_back_button";
    public static final String HAS_PRESS_HOME_BUTTON = "has_press_home_button";

    public static final String SHOULD_SHOW_AUTOBOOT_GUIDE_FOR_UPGRADE_USER = "should_show_autoboot_guide_for_upgrade_user";
    public static final String APP_INSTALL_UPDATE_START_TIME = "app_install_update_start_time";

    public static final String RATE_SEND_SMS_CLICKED = "rate_send_sms_clicked";
    public static final String FIND_NEWS_SHARE_TITLE = "find_news_share_title";
    public static final String FIND_NEWS_SHARE_IMG_URL = "find_nees_share_img_url";
    public static final String FIND_NEWS_SHOW_GUIDE_TITLE = "find_nees_show_guide_title";
    public static final String FIND_NEWS_SHARE_URL = "find_news_share_url";

    public static final String SHOW_TODO_ENTRY = "show_todo_entry";

    public static final String DIAL_STYLE = "dial_style";

    public static final String VOIP_ERROR_CODE_AD_STRATEGY_VERSION = "voip_error_code_ad_strategy_version";
    public static final String VOIP_DEAL_STRATEGY = "voip_deal_strategy";
    public static final String VOIP_SHOW_CALLBACK_INFORM = "voip_show_callback_inform";

    public static final String HAS_SHOWN_PERMISSION_GUIDE = "has_shown_permission_guide";
    public static final String NEED_SHOW_DELETE_VOIP_NUMBER_GUIDE = "need_show_delete_voip_number_guide";
    public static final String HAS_SHOWN_DELETE_VOIP_NUMBER_GUIDE = "has_show_delete_voip_number_guide";
    public static final String PRESENTATION_NOTIFICATION_TAG = "presentation_notification_tag";

    public static final String TRAFFIC_MONITOR_RECEIVED_BYTES = "traffic_monitor_received_bytes";
    public static final String TRAFFIC_MONITOR_TRANSMITTED_BYTES = "traffic_monitor_transmitted_bytes";
    public static final String TRAFFIC_MONITOR_BEGIN_TIMESTAMP = "traffic_monitor_begin_timestamp";

    public static final String ACTION_CALLBACK_JUMP_EARN_MONEY_CENTER = "action_callback_jump_earn_money_center";

    public static final String HANGUP_FREE_PHONE_SUCCESS_NORMAL = "hangup_free_phone_success_normal";
    public static final String HANGUP_FREE_PHONE_SUCCESS_NORMAL_NOT_IN_WIFI = "hangup_free_phone_success_normal_not_in_wifi";
    public static final String HANGUP_NORMAL_CALL_SUCCESS = "hangup_normal_call_success";

    public static final String EARN_CENTER_COMMON_CALL_ACT = "earn_center_common_call_act";
    public static final String EARN_CENTER_GUSTURE_ACT = "earn_center_gesture_act";

    public static final String VOIP_DUE_ACTION_TIME = "voip_due_action_time";
    public static final String VOIP_MINUTE_DUE_ACTION_TIME = "voip_due_action_time";
    public static final String VOIP_MINUTE_NOT_ENOUGH_ACTION_TIME = "voip_minute_not_enough_action_time";

    public static final String IS_NEW_USER = "is_new_user";
    public static final String HANGUP_BY_BYTE_LIMIT = "hangup_by_byte_limit";

    public static final String NEW_INSALL_AND_NEW_REGISTER = "new_install_and_new_register";

    public static final String WEBVIEW_USER_AGENT = "webview_user_agent";

    public static final String NEWS_NOTIFICATION_SWITCH = "news_notification_switch";
    public static final String OPEN_SCREEN_LOCK = "open_screen_lock";
    public static final String NEWS_NOTIFICATION_TIME_INTERVAL = "news_notification_time_interval";
    public static final String NEWS_LAST_NOTIFICATION_TIMESTAMP = "news_last_nofity_timestamp";
    public static final String NEWS_LAST_NOTIFICATION_INFO = "news_last_notification_info";
    public static final String SHOW_IN_APP_TOAST_PERMISSION = "show_in_app_toast_permission";
    public static final String SIGN_IN_REWARD_TIMSTAMP = "sign_in_reward_timestampr";
    public static final String SHOW_NEWS_SIGN_IN_RED_ICON = "show_news_sign_in_red_icon";

	public static final String CALLLOG_STATISTIC_ENABLE = "calllog_statistic_enable";

    public static final String PREVIOUS_CALLLOG_COUNT = "previous_calllog_count";
    public static final String SHOW_EMPTY_CALLLOG_PROMPT = "show_empty_calllog_prompt";

    public static final String UPLOAD_SIM_CONTACT_TIME = "upload_sim_contact_time";

    public static final String MEMORY_MONITOR_TIMESTAMP = "memory_monitor_timestamp";

    public static final String FUWUHAO_ROOT_READ = "fuwuhao_root_read";
    public static final String DSI_ORIGIN_INFO = "dsi_origin_info";
    public static final String DSI_SOLUTION_INDEX = "dsi_solution_index";
    public static final String LAST_UP_EXPERIMENT_RESULT_TIME = "last_up_experiment_time";
    public static final String EXPERIMENT_RESULT_TIME = "experiment_result_time";

    public static final String OPEN_NORMAL_PHONE_OUTGOING_AD = "open_normal_phone_outgoing_ad_1";
    public static final String OPEN_NORMAL_PHONE_OUTGOING_AD_OLD = "open_normal_phone_outgoing_ad";
    public static final String OPEN_NORMAL_PHONE_HANGUP_ACTIVITY = "open_normal_phone_hangup_activity";


    public static final String HAS_SHOW_NORMAL_PHONE_AD_RED_PACKET = "has_show_normal_phone_ad_red_packet";
    public static final String HAS_SHOW_NORMAL_PHONE_AD_RED_PACKET_AGAIN = "has_show_normal_phone_ad_red_packet_again_2";
    public static final String SHOW_NORMAL_PHONE_AD_RED_PACKET_TS = "show_normal_phone_ad_red_packet_ts";

    public static final String FEEDS_REMIND_LAUNCHER_ICON = "feeds_remind_launcher_icon";
    public static final String FEEDS_AD_HAND_UP_DISPLAY = "feeds_ad_hand_up_display";
    public static final String FEEDS_AD_HAND_UP_NORMAL_DISPLAY = "feeds_ad_hand_up_normal_display"; //普通电话挂机后红包展示时间
    public static final String FEEDS_HAND_UP_REDPACKET_DISPLAY = "feeds_hand_up_redpacket_display";
    public static final String FEEDS_HAND_UP_REDPACKET_NORMAL_DISPLAY = "feeds_hand_up_redpacket_normal_display";
    public static final String FEEDS_NEWS_SWIPE_FINISH = "feeds_news_swipe_finish";

    public static final String FEEDS_SUBSTITUDE_OF_SIGN_IN_BONUS = "feeds_substitude_of_sign_in_bonus";

    public static final String FEEDS_HANGUP_REDPACKET_QUERY = "feeds_hangup_redpacket_query";
    public static final String FEEDS_QUERY_REDPACKET_IN_LIST_TIME = "feeds_query_redpacket_in_list_time";
    public static final String FEEDS_QUERY_REDPACKET_IN_DETAIL_TIME = "feeds_query_redpacket_in_detail_time";

    public static final String FEEDS_REDPACKET_DETAIL_HAS_FLG = "feeds_redpacket_detail_has_flg";
    public static final String FUWUHAO_POINT_FIRST_SHOWN = "fuwuhao_point_first_shown";
    public static final String CNT_HAS_SHOW_DIALOG_NORMAL_PHONE_AD = "cnt_has_show_dialog_normal_phone_ad";
    public static final String CNT_ENTER_CLOSE_NORMAL_PHONE_AD = "cnt_enter_close_normal_phone_ad";
    public static final String CNT_HAS_CLICK_SETTING_NORMAL_PHONE_AD = "cnt_has_click_setting_normal_phone_ad";

    public static final String SHOULD_SHOW_SKIN_UPDATE_INAPP_GUIDE = "should_show_skin_update_inapp_guide";
    public static final String SHOW_AD_SKIN_ID = "show_ad_skin_id";
    public static final String SHOW_AD_SKIN_PACKAGE_NAME = "show_ad_package_name";
    public static final String SHOW_AD_SKIN_BEGIN_TIME = "show_ad_skin_begin_time";
    public static final String SHOW_AD_SKIN_END_TIME = "show_ad_skin_end_time";
    public static final String SKIN_AD_INAPP_BEGIN_TIME = "skin_ad_inapp_begin_time";
    public static final String SKIN_AD_INAPP_END_TIME = "skin_ad_inapp_end_time";
    public static final String SKIN_AD_INAPP_ID = "skin_ad_inapp_id";
    public static final String SKIN_AD_INAPP_TITLE = "skin_ad_inapp_title";
    public static final String CLICK_SKIN_AD_INAPP = "click_skin_ad_inapp";
    public static final String SHOW_SKIN_AD = "show_skin_ad";
    public static final String END_SHOW_SKIN_AD = "end_show_skin_ad";
    public static final String LAST_REQUEST_SKIN_AD_TIME = "last_request_skin_ad_time";
    public static final String TMAIN_SLIDE_CREATE_TIME = "tmain_slide_create_time";
    public static final String PERMISSION_INAPP_SECOND_GUIDE_SHOWED = "permission_inapp_second_guide_showed";
    public static final String INSTALL_IN = "app_install_in";
    public static final String HAS_RECORD_CALL_LOG_COLUMN = "has_record_call_log_column";

    public static final String SLIDEDIALER_SIGNIN_TIME = "slidedialer_sign_in";
    public static final String SLIDEDIALER_REDPACKET_TIME = "slidedialer_redpacket_time";   // deprecated
    public static final String SLIDEDIALER_REDPACKET_DETAIL_EXIST_FLG = "slidedialer_redpacket_detail_exist_flg";
    public static final String SLIDEDIALER_FEEDS_HINT_TIME = "slidedialer_feeds_hint_time";
    // public static final String SLIDEDIALER_REDPACKET_DETAIL_EXIST = "slidedialer_redpacket_detail_exist";
    public static final String HAS_SHOW_SIGN_IN_BAR = "has_show_sign_in_bar";

    public static final String HARDWARE_INFO_COLLECT_TIMES = "hardware_collect_times";

    // prefetch
    public static final String PREFETCH_ADS_RESPONSE_STRING = "prefetch_ads_response_string_";
    public static final String PREFETCH_ADS_CK = "prefetch_ads_ck_";
    public static final String PREFETCH_ADS_ETIME = "prefetch_ads_etime_";
    public static final String PREFETCH_ADS_BACKUP_LIST = "prefetch_ads_backup_list_";
    public static final String PREFETCH_RETRY_URL = "prefetch_retry_url";
    public static final String PREFETCH_SYS_VER = "prefetch_sys_ver";

    public static final String VOICE_VALIDATION_TIMESTAMP = "voice_validation_timestamp";
    public static final String COMMERCIAL_WEBVIEW_CLOSED = "COMMERCIAL_WEBVIEW_CLOSED";
    public static final String CLOSE_DIAL_AD_TIME = "close_dial_ad_time";
    public static final String CALL_QUALITY_STAT_POOR_ONE_WEEK_TIMES = "call_quality_stat_poor_one_week_times";
    public static final String CALL_QUALITY_STAT_POSSIBLE_ONE_WEEK_TIMES = "call_quality_stat_possible_one_week_times";
    public static final String CALL_QUALITY_STAT_POOR_ONE_WEEK_BEGIN = "call_quality_stat_poor_one_week_begin";
    public static final String CALL_QUALITY_STAT_POSSIBLE_ONE_WEEK_BEGIN = "call_quality_stat_possible_one_week_begin";
    public static final String CALL_QUALITY_STAT_POOR_ONE_WEEK_HAS_STAT = "call_quality_stat_poor_one_week_has_stat";
    public static final String CALL_QUALITY_STAT_POSSIBLE_ONE_WEEK_HAS_STAT = "call_quality_stat_possible_one_week_has_stat";

    public static final String LOOOP_NEXT_FETCH_CONFIG_TIME = "LOOOP_NEXT_FETCH_CONFIG_TIME";
    public static final String LOOOP_ENABLED = "LOOOP_ENABLED";
    public static final String LOOOP_CONFIG_VERSION = "LOOOP_CONFIG_VERSION";
    public static final String LOOOP_KEYBOARD_ENABLE = "LOOOP_KEYBOARD_ENABLE";
    public static final String WEBVIEW_FONT_SIZE = "webview_font_size";
    public static final String SHOW_FAMILY_ICON_NEW = "show_family_icon_new";

    public static final String LAST_FAMILY_NUMBER_STRING = "last_family_number_string";
    public static final String UNREAD_FAMILY_NUMBER_NEWS = "unread_family_number_news";

    // custom event configuration
    public static final String USAGE_CUSTOM_EVENT_PREFIX = "CUSTOM_EVENT_";
    // switcher for which is not listed in black list or white list
    public static final String USAGE_CUSTOM_EVENT_DEFAULT_ON = "DEFAULT_SWITCHER";
    public static final String USAGE_CUSTOM_EVENT_WHITE_LIST = "CUSTOM_EVENT_WHITE_LIST";
    public static final String USAGE_CUSTOM_EVENT_BLACK_LIST = "CUSTOM_EVENT_BLACK_LIST";

    public static final String PREF_SEETINGS_AVATAR = "pref_settings_avatar";

    //startup commercial
    public static final String STARTUP_COMMERCIAL_WTIME = "startup_commercial_wtime";
    public static final String STARTUP_COMMERCIAL_IDWS = "startup_commercial_idws";

    public static final String VOIP_CALL_ALMARK_UUID = "voip_call_almark_uuid";
    public static final String VOIP_CALL_ALMARK_LAST_SIP = "voip_call_almark_last_sip";
    public static final String LAST_SHOW_FAMILY_NUMBER_REDPOINT_TIME = "last_show_familu_number_redpoint_time";
    public static final String PUSHED_REGISTER_REQUEST = "pushed_register_request";

    public static final String TAB_SELECTED = "tab_selected";

    public static final String COMMERCIAL_CALL_CONTENT_IMG_VER = "commercial_call_content_img_ver";
    public static final String LAST_GET_COMMERCIAL_CALL_CONTENT_IMG_TIME = "last_get_commercial_call_content_img_time";

    public static final String DETAIL_REDPACKET_SHOW_TIME = "detail_redpacket_show_time";
    public static final String LIST_REDPACKET_SHOW_TIME = "list_redpacket_show_time";
    public static final String WALLET_REDPACKET_SHOW_TIME = "wallet_redpacket_show_time";
    public static final String HAS_CREATE_SYNC_ACCOUNT = "has_create_sync_account";
    public static final String LAST_GET_FAMILYNUMBER_INFO_TIME = "last_get_familynumber_info_time";
    public static final String LAST_GET_FAMILY_INFO_TIME = "last_get_family_info_time";

    public static final String BUG_REPORT_IS_UPLOADING = "bug_report_is_uploading";
    public static final String BUG_REPORT_UPLOAD_ERROR_COUNT = "bug_report_upload_error_count";
    public static final String UPGRADE_PERMISSION_GUIDE_HAS_SHOW = "upgrade_permission_guide_has_show";
    public static final String UPGRADE_PERMISSION_GUIDE_SHOW_JUST_NOW = "upgrade_permission_guide_show_just_now";
    public static final String CALLLOG_ITEM_GUIDE_ANIMATION_SHOW_TIMES = "calllog_item_guide_animation_show_times";
    public static final String ACCESSIBILITY_PERMISSION_GUIDE_HAS_SHOW = "access_permission_guide_has_show";
    public static final String ACCESSIBILITY_PERMISSION_CALLLOG_GUIDE_CLICK = "accessibility_permision_guide_click";

    //panda commercial
    public static final String PREVIOUS_BLOCKED_NUMBER = "previous_blocked_number";
    public static final String PREVIOUS_BLOCKED_DATE = "previous_blocked_date";

    public static final String SMARTDIALER_WELCOME = "smartdialer_welcome";

    public static final String FIRST_REQUEST_CALLLOG_PERMISSION = "first_request_calllog_permission";

    public static final String CALLLOG_GUIDE_PULL_FRESHER_DATA = "calllog_guide_pull_fresher_data";

    public static final String KEYBOARD_HAS_COMMERCIAL = "keyboard_has_commercial";

    //ad res init
    public static final String AD_RES_INIT = "ad_res_init";


    //display
    public static final String LAST_SYNC_CALLLOG_TIMESTAMP = "last_sync_calllog_timestamp";
    public static final String COUNT_INAPP_MISSED_CALLOG_PERMISSION_GUIDE = "count_inapp_missed_callog_permission";

    public static final String PANDA_POSITION_PADDING_BOTTOM_PAD_HIDE = "panda_position_padding_bottom_pad_hide";
    public static final String PANDA_POSITION_PADDING_BOTTOM_PAD_SHOW = "panda_position_padding_bottom_pad_show";

    public static final String RATE_APP_CONDITION_SATISFIED = "rate_app_condition_satisfied";
    public static final String RATE_APP_DIALOG_SHOW_TIMES = "rate_app_dialog_show_times";
    public static final String RATE_APP_DIALOG_LAST_SHOW_TIME = "rate_app_dialog_last_show_time";
    public static final String DONNOT_SHOW_RATE_APP_DIALOG = "donnot_show_rate_app_dialog";
    public static final String RATE_FROM = "rate_from";

    //tips commercial
    public static final String TIPS_COMMERCIAL_CK = "tips_commercial_ck";
    public static final String TIPS_COMMERCIAL_REQUEST_TIME = "tips_commercial_request_time";

    //panda commercial
    public static final String PANDA_COMMERCIAL_DATA_JSON = "panda_commercial_data_json";
    public static final String PANDA_COMMERCIAL_DATA_JSON_ETIME = "panda_commercial_data_json_etime";
    public static final String PANDA_COMMERCIAL_DATA_FLAG_ED = "panda_commercial_data_flag_ed";
    public static final String PANDA_COMMERCIAL_DATA_FLAG_CLK = "panda_commercial_data_flag_clk";

    public static final String PANDA_YUNYING_SHOW_RED = "panda_yunying_show_red_";
    public static final String PANDA_GUIDE_HAS_CLICK = "panda_guide_has_click";

    public static final String PERMISSION_SETTING_CLICK = "permission_setting_click";

    public static final String OPPO_COLOR_OS_VERSION = "oppo_color_os_version";

    public static final String MIUI_V6_PERMISSION_CLICK = "miui_v6_permission_click";

    public static final String NUMPAD_SHOW = "numpad_show";

    public static final String APP_ENTER_FOREGROUND_TIMESTAMP_MILLS = "app_enter_foreground_timestamp";

    public static final String APP_STATUS = "app_status";
    public static final String ACTIVITY_STATUS = "activity_status";

    public static final String UPLOAD_MIUI_VERSION = "upload_miui_version";

    public static final String PERMISSIONLIST_GUIDE_FIRST_SHOW = "permissionlist_guide_first_show";

    public static final String CONTTACT_PERMISSION_SETTING_CLICK = "contact_permission_setting_click";
    public static final String CALLLOG_PERMISSION_SETTING_CLICK = "calllog_permission_setting_click";

    public static final String TPD_TAB_ACTIVITY_RESUME_TS = "tpd_tab_activity_resume_ts";

    public static final String LOG_OUT_MANUALLY = "log_out_manually";

    public static final String SHOW_NORMAL_HUNGUP_REWARD_INFORM = "show_normal_hungup_reward_inform";
    public static final String REDPACKET_BAR_FIRST_SHOW_TIME = "rp_bar_first_show_time";
    public static final String REDPACKET_BAR_HAS_SHOW_FIRST_GUIDE = "rp_bar_has_show_first_guide";

    public static final String ADPANDA_HAS_CLICKED = "adpanda_has_clicked";

}
