/**
   

 * Copyright(c) 2013 CooTek
 * All rights reserved.
 */
package android.by.com.permission.model;

/**
 * @author ThomasYe(Thomas.Ye@CooTek.cn)
 * 
 */
public class StartupVerifier {
    private static final String TAG = "StartupVerifier";

    private static final String USERDATA_FILE = "com.cootek.smartdialer.userdata";
    
    public static final int INSTALL_TYPE_NEW = 1;
    public static final int INSTALL_TYPE_UPGRADE = 2;

    private static final String START_TIMES = "start_times";
    private static final String LAST_UPDATE_HR = "last_update_hr";
    
    public static final String INSTALL_TYPE = "install_type";
    
    public static long MSECOND_PER_DAY = 86400000L;

    public static int VOIP_ERROR_STRATEGY_VERSION = 1;

//    @LaunchPerf
//    public static void init(Context ctx) {
//        TLog.d(TAG, "init");
//        String oldVersion = PrefEssentialUtil.getKeyString(PrefEssentialKeys.APK_VERSION, "");
//        if (TextUtils.isEmpty(oldVersion)) {
//            File file = new File(ctx.getFilesDir().getAbsolutePath()
//                    + "/../shared_prefs", USERDATA_FILE + ".xml");
//            if (file.exists()) {
//                SharedPreferences sp = ctx.getSharedPreferences(USERDATA_FILE,
//                        Context.MODE_PRIVATE);
//                oldVersion = sp.getString(PrefEssentialKeys.APK_VERSION, "");
//                file.delete();
//            }
//        }
//        String newVersion = String.valueOf(TPApplication.getCurVersionCode());
//        TLog.d(TAG, "oldVersion: " + oldVersion + ", newVersion: " + newVersion);
//        if (TextUtils.isEmpty(oldVersion)) {
//            HashMap<String, Object> map = new HashMap<String, Object>();
//            map.put(StatConst.BASE_INSTALL_TYPE, INSTALL_TYPE_NEW);
//            map.put(StatConst.BASE_MANUFACTURE, Build.MANUFACTURER);
//            map.put(StatConst.BASE_MODEL, Build.MODEL);
//            StatRecorder.record(StatConst.PATH_BASE_INFO, map);
//            onInstall(ctx);
//            PrefEssentialUtil.setKey(PrefEssentialKeys.APK_VERSION, newVersion);
//        } else {
//            if (!oldVersion.equals(newVersion)) {
//                HashMap<String, Object> map = new HashMap<String, Object>();
//                map.put(StatConst.BASE_INSTALL_TYPE, INSTALL_TYPE_UPGRADE);
//                map.put(StatConst.BASE_MANUFACTURE, Build.MANUFACTURER);
//                map.put(StatConst.BASE_MODEL, Build.MODEL);
//                StatRecorder.record(StatConst.PATH_BASE_INFO, map);
//                onUpgrade(ctx, Integer.parseInt(oldVersion), Integer.parseInt(newVersion));
//                PrefEssentialUtil.setKey(PrefEssentialKeys.APK_VERSION, newVersion);
//                // [Thomas] If we assume Umeng key would be changed, uncomment the following line.
//                /* initUmengAppkey(ctx); */
//            }
//        }
//
//        if (LoginUtil.isLogged()) {
//            HashMap<String, Object> map = new HashMap<String, Object>();
//            map.put(StatConst.BASE_INSTALL_TYPE, INSTALL_TYPE_NEW);
//            map.put("enable", C2CUtil.isVoipModeOn());
//            map.put("oldver",oldVersion);
//            map.put("newver",newVersion);
//            StatRecorder.record(StatConst.EVENT_VOIP_SWITCH_ENABLE,map);
//        }
//
//        PrefUtil.setKey(PrefKeys.APP_INSTALL_UPDATE_START_TIME, System.currentTimeMillis());
//
//        ModelManager.getInst().getHandler().postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                AsyncTask<Void, Void, Void> task = new AsyncTask<Void, Void, Void>() {
//                    @Override
//                    protected Void doInBackground(Void... params) {
//                        VisualKeyboardUtil.loadFile();
//                        YellowPageHotlineKeyBoard.loadFile();
//                        return null;
//                    }
//                };
//                task.execute();
//            }
//        } , 5000);
//
//    }
//
//    private static void onInstall(Context context) {
//        TLog.d(TAG, "onInstall");
//        if (TLog.DBG) {
//            TLog.i(StartupVerifier.class, "TouchPal Installed");
//        }
//        String secret = LogoutStatisticUtil.getLoginSecret();
//        if(!TextUtils.isEmpty(secret)) {
//            StatRecorder.record(StatConst.PATH_SECRET, StatConst.INSTALL_SECRET, 1);
//        }
//        LogoutStatisticUtil.storeLoginSecret("");
//        activate(Activator.ACTIVATE_TYPE_NEW);
//        setUpdateStartTime();
//        // since we have turn on the number formatter.
//        // No need to turn it on for US/CA
//        PrefUtil.setKey(INSTALL_TYPE, INSTALL_TYPE_NEW);
//
//    	long current = System.currentTimeMillis();
//    	PrefUtil.setKey(PrefKeys.FIRST_TIME_TO_INSTALL, current);
//    	PrefUtil.setKey(PrefKeys.BACKUP_DATE, current);
//        com.cootek.andes.utils.PrefUtil.setKey(com.cootek.andes.preference.PrefKeys.BIBI_PROFILE_HEAD_SIGNATURE, String.valueOf(System.currentTimeMillis()));
//
//        new BackUpAndRestore().execute(BackUpAndRestore.COMMAND_RESTORE);
//
//      //remove all the old local html files
//        TimerTask task = new TimerTask() {
//			public void run() {
//				 WebSearchLocalAssistant.updateLocalHtml(ModelManager.getContext());
//	             PrefUtil.setKey(PrefKeys2.WEBSEARCH_SKIN_CHANGED, true);
//			}
//        };
//        Timer timer = new Timer();
//        timer.schedule(task, 0);
//        BlockingSettingModel.onInstall();
//        MissedCallClean.setFirstTimeUse();
//
//		//show c2c guide
//		PrefUtil.setKey(PrefKeys.VOIP_SHOW_GUIDE, true);
//
//        ChannelUtil.saveChannelCodeInfo();
//	    PrefUtil.setKey(PrefKeys.VOIP_SHOULD_ALERT_CONSUME_DATA, true);
//
//        PrefUtil.setKey(PrefKeys.VOIP_SHOULD_ALERT_VOIP_LOCATE, true);
//
//
//        PrivateContactUtil.restorePasswordIfAny();
//
//        PrefUtil.setKey(PrefKeys.TOAST_GUIDE_SHOULD_SHOW, true);
//        PrefUtil.setKey(PrefKeys.TOAST_OPENED, true);
//
//        PrefUtil.setKey(PrefKeys.INSTALL_IN, true);
//    }
//
//    private static void onUpgrade(Context context, int oldVersion, int newVersion) {
//        TLog.d(TAG, "onUpgrade");
//        PrefUtil.setKey(PrefKeys.TEST_VERSION_ACTIVATION_SUCCEED, true);
//
//        if (oldVersion < 4720 && newVersion >= 4720) {
//            PrefUtil.deleteKey(PrefKeys.PLUGIN_NEW_MARK_PREFIX + ModelPlugin.PLUGIN_CALLBLOCKING_ID);
//        }
//        if (oldVersion < 4760) {
//        	if (PrefUtil.getKeyInt(PrefKeys.HAPTIC_FEEDBACK_LEN, -1) == -1) {
//        		PrefUtil.setKey(PrefKeys.HAPTIC_FEEDBACK_LEN, context.getResources().getInteger(R.integer.dial_viberate3));
//        	}
//        }
//        if(oldVersion < 4770) {
//        	PrefUtil.deleteKey(PrefKeys.PLUGIN_NEW_MARK_PREFIX + ModelPlugin.PLUGIN_CALLBLOCKING_ID);
//        	BlockingSettingModel.onUpgrade();
//        }
//        if(oldVersion <= 4773) {
//        	PrefUtil.deleteKey(PrefKeys.CLEAN_MISSED_CALLS_LASTRECORD);
//        }
//
//        if (oldVersion < 4800) {
//            PrefEssentialUtil.setKey(PrefEssentialKeys.NEED_SHOW_LANDING_PAGE, true);
//        }
//
//        if (oldVersion < 4812) {
//            PrefUtil.deleteKey(PrefKeys.PNATTR_INCALL);
//            PrefUtil.deleteKey(PrefKeys.PNATTR_INCALL_DISP_UNTIL);
//            PrefUtil.deleteKey(PrefKeys.PNATTR_OUTGOING);
//            PrefUtil.deleteKey(PrefKeys.PNATTR_OUTGOING_DISP_UNTIL);
//            PrefUtil.deleteKey(PrefKeys.ONCALL_LOCDISPLAY_YTOAST_v2);
//            PrefUtil.deleteKey(PrefKeys.LOCDISPLAY_INCOMING_UNKNOWN_ONLY);
//            PrefUtil.deleteKey(PrefKeys.LOCDISPLAY_OUTGOING_UNKNOWN_ONLY);
//            PrefUtil.deleteKey(PrefKeys.LOCDISPLAY_UNKNOWN_ONLY);
//        }
//
//        if (oldVersion > 4820 && oldVersion < 4824) {
//            if (PrefUtil.containsKey(PrefKeys.CALL_HANGUP_VIEW_NOT_SHOW)) {
//                boolean ret = PrefUtil.getKeyBoolean(
//                        PrefKeys.CALL_HANGUP_VIEW_NOT_SHOW, false);
//                PrefUtil.setKey(PrefKeys.CALL_HANGUP_VIEW_SHOW_TYPE,
//                        ret ? CallerIdToastSettingActivity.CALL_HANGUP_SHOW_DISMISS
//                                : CallerIdToastSettingActivity.CALL_HANGUP_SHOW_SMART);
//                PrefUtil.deleteKey(PrefKeys.CALL_HANGUP_VIEW_NOT_SHOW);
//            }
//            PrefUtil.setKey(PrefKeys.TOAST_ALPHA, 8);
//        }
//        if (oldVersion < 5000) {
//            PrefUtil.deleteKey(PrefKeys.INSTALLED_PLUGINS);
//            if (ModelManager.getInst().getGesture().hasGesture()) {
//                PrefUtil.setKey(PrefKeys.GESTURE_DIALING_ON, true);
//            }
//
//            //delete this old key, because it may has too long characters
//            PrefUtil.deleteKey(TakeoverConflictReminder.TAKE_OVER_SOFTWARES);
//            PrefUtil.deleteKey(PrefKeys.PHONEPAD_LANGUAGE);
//            if (oldVersion > 0 && !PrefUtil.containsKey(PrefKeys.HANGUP_VIBRATE)) {
//                PrefUtil.setKey(PrefKeys.HANGUP_VIBRATE, true);
//            }
//            DialogUtil.deleteShortcut();
//            PrefUtil.setKey(PrefKeys.PREF_CALLLOG_SHOW_PHOTO, true);
//        }
//
//        if (oldVersion <= 5190) {
//            EdenUtil.refreshEdenCookie();
//            PrefEssentialUtil.setKey(PrefEssentialKeys.NEED_SHOW_LANDING_PAGE, true);
//        }
//
//        if (oldVersion < 5200) {
//            PrefUtil.setKey(PrefKeys.SAC_PREF_CLICK, PrefUtil.getKeyStringRes(PrefKeys.SAC_PREF_CLICK, R.string.pref_sac_action_key_confirm_to_call));
//        }
//
//        if (oldVersion < 5210) {
//            PrefUtil.deleteKey(PrefKeys.IS_SYSTEM_DIALER_CONTACT_SAME_APP);
//        }
//
//        if (PrefUtil.getKeyBoolean(PrefKeys.PRENSENTATION_FIRST_UPGRADE, true)) {
//            PrefUtil.setKey(PrefKeys.NEED_UPDATE_SUPER_DIALER_CONTAINER, true);
//            PrefUtil.setKey(PrefKeys.DIALER_PERSONAL_CENTER_SHOW_POINT, true);
//            PrefUtil.setKey(PrefKeys.PRENSENTATION_FIRST_UPGRADE, false);
//        }
//
//
//        if(oldVersion == 5550 || oldVersion == 5551) {
//            PrefEssentialUtil.setKey(PrefEssentialKeys.NEED_SHOW_LANDING_PAGE, false);
//        }
//
//        if (oldVersion < 5620){
//            if (PrefUtil.getKeyBoolean(PrefKeys.PNATTR_INCALL, true) || PrefUtil.getKeyBoolean(PrefKeys.PNATTR_OUTGOING,true)) {
//                PrefUtil.setKey(PrefKeys.TOAST_OPENED, true);
//            }
//            PrefUtil.setKey(PrefKeys.LOCDISPLAY_INCOMING_UNKNOWN_ONLY,PrefUtil.getKeyBoolean(PrefKeys.LOCDISPLAY_INCOMING_UNKNOWN_ONLY,false));
//            PrefUtil.setKey(PrefKeys.LOCDISPLAY_OUTGOING_UNKNOWN_ONLY,PrefUtil.getKeyBoolean(PrefKeys.LOCDISPLAY_OUTGOING_UNKNOWN_ONLY,false));
//            PrefUtil.setKey(PrefKeys.PNATTR_INCALL,PrefUtil.getKeyBoolean(PrefKeys.PNATTR_INCALL,true));
//            PrefUtil.setKey(PrefKeys.PNATTR_OUTGOING,PrefUtil.getKeyBoolean(PrefKeys.PNATTR_OUTGOING,true));
//        }
//
//        PrefUtil.setKey(PrefKeys.PREF_FEEDBACK_NEED_SHOW_FIRST_VERSION,true);
//        if (oldVersion <= 5644) {
//            PrefUtil.setKey(PrefKeys.COMMERCIAL_CURRENT_UDPLIST_VERSION, 0);
//        }
//
//
//        if (oldVersion <=5770) {
//            PrefUtil.setKey(PrefKeys.SHOULD_SHOW_AUTOBOOT_GUIDE_FOR_UPGRADE_USER, true);
//        }
//        // 5660 is market perInstall and 5710 is oem perInstall
//        if (oldVersion <= 5651 || oldVersion == 5660 || oldVersion == 5710) {
//            try {
//                int flow = PrefUtil.getKeyInt(PrefKeys.VOIP_TRAFFIC_ACCOUNT_BALANCE, 0);
//                PrefUtil.deleteKey(PrefKeys.VOIP_TRAFFIC_ACCOUNT_BALANCE);
//                PrefUtil.setKey(PrefKeys.VOIP_TRAFFIC_ACCOUNT_BALANCE, (long)flow);
//            } catch(ClassCastException e) {
//                e.printStackTrace();
//            }
//        }
//        if (oldVersion <= 5730) {
//            PrefUtil.setKey(PrefKeys.PRENSENTATION_REFRESH_MESSAGE, true);
//        }
//
//        if (oldVersion <= 5720) {
//            PrefUtil.setKey(PrefKeys.CURRENT_UDPLIST_VERSION, 0);
//            PrefUtil.setKey(PrefKeys.COMMERCIAL_CURRENT_UDPLIST_VERSION, 0);
//        }
//
//        if (oldVersion <= 5743) {
//            PrefUtil.setKey(PrefKeys.PERSONAL_CENTER_FREE_PHONE_SETTING_NEW, true);
//        }
//
//        if (oldVersion < 5772) {
//            if (PrefUtil.getKeyBoolean(DualSimConst.SINGLE_SLOT_TELEPHONY, false)) {
//                PrefUtil.setKey(DualSimConst.SIM_MODE, DualSimConst.MODE_SINGLE_SIM);
//                PrefUtil.deleteKey(DualSimConst.SINGLE_SLOT_TELEPHONY);
//            }
//            PrefUtil.setKey(DualSimConst.UPDATE_OLD_DUALSIM_MODULE, true);
//        }
//
//        if (oldVersion < 5820) {
//            PrefUtil.deleteKey(PrefKeys.WEBVIEW_USER_AGENT);
//        }
//
//        if (oldVersion < 5857) {
//            PrefUtil.deleteKey(PrefKeys.DUALSIM_SCANNED_METHOD + "endCall");
//            PrefUtil.deleteKey(PrefKeys.DUALSIM_CACHED_METHOD + "endCall");
//            PrefUtil.deleteKey(PrefKeys.DUALSIM_ENCLUDE_METHOD + "endCall");
//            PrefUtil.deleteKey(PrefKeys.DUALSIM_PARAMID_METHOD + "endCall");
//            PrefUtil.setKey(PrefKeys.DUALSIM_UPDATE_METHOD + "endCall", true);
//        }
//
//        if (oldVersion >= 6041 && oldVersion < 6062 && LoginUtil.isLogged()) {
//            C2CUtil.setVoipModeOn(true);
//        }
//
//        if (oldVersion < 5942) {
//            File messageFile = new File(ModelManager.getContext().getFilesDir(), InAppMessageManager.MESSAGE_FILE);
//            if (messageFile.exists()) {
//                messageFile.delete();
//            }
//        }
//
//        if (PrefUtil.getKeyInt(PrefKeys.VOIP_ERROR_CODE_AD_STRATEGY_VERSION, 0) < VOIP_ERROR_STRATEGY_VERSION ){
//            File fileInData = ModelManager.getContext().getFileStreamPath(VoipErrorCodeAndAdStrategy.STRATEGY_FILE_NAME);
//            if (fileInData.exists()) {
//                fileInData.delete();
//            }
//        }
//
//
//        String secret = PrefEssentialUtil.getKeyString(PrefEssentialKeys.SEATTLE_SECRET, "");
//        LogoutStatisticUtil.storeLoginSecret(secret);
//
//        if (TLog.DBG) {
//            TLog.i(StartupVerifier.class, "TouchPal Upgraded.");
//        }
//        String phoneAccountName = PrefUtil.getKeyString(PrefKeys.GUESSED_PHONE_ACCOUNT_NAME, Constants.EMPTY_STR);
//        String phoneAccountType = PrefUtil.getKeyString(PrefKeys.GUESSED_PHONE_ACCOUNT_TYPE, Constants.EMPTY_STR);
//        if (!TextUtils.isEmpty(phoneAccountName) && phoneAccountName.equals(ModelContact.Dialer_ACCOUNT_TYPE_PHONE)) {
//            PrefUtil.deleteKey(PrefKeys.GUESSED_PHONE_ACCOUNT_NAME);
//            PrefUtil.deleteKey(PrefKeys.GUESSED_PHONE_ACCOUNT_TYPE);
//        } else if (!TextUtils.isEmpty(phoneAccountType) && phoneAccountType.equals(ModelContact.Dialer_ACCOUNT_TYPE_PHONE)) {
//            PrefUtil.deleteKey(PrefKeys.GUESSED_PHONE_ACCOUNT_NAME);
//            PrefUtil.deleteKey(PrefKeys.GUESSED_PHONE_ACCOUNT_TYPE);
//        }
//
//        PrefUtil.setKey(PrefKeys.VOIP_HAS_USED_FEEDBACK, false);
//        PrefUtil.setKey(PrefKeys.SLIDEDIALER_REDPACKET_DETAIL_EXIST_FLG, false);
//        PrefUtil.setKey(PrefKeys.SLIDEDIALER_SIGNIN_TIME, -1);
//        PrefUtil.setKey(PrefKeys.SLIDEDIALER_REDPACKET_TIME, -1);
//        // clear old files to make update normally.
//        clearOldFiles();
//        activate(Activator.ACTIVATE_TYPE_UPGRADE);
//        setUpdateStartTime();
//        PrefUtil.setKey(INSTALL_TYPE, INSTALL_TYPE_UPGRADE);
//        if (BuildConfig.ENABLE_SKIN_PUSH) {
//        	PrefUtil.setKey(PrefKeys.HAS_LOAD_PUSH_SKIN, false);
//        }
//        //key vibrate downgrade
//        if(PrefUtil.getKeyInt(PrefKeys.HAPTIC_FEEDBACK_LEN, 0) > 45) {
//        	PrefUtil.setKey(PrefKeys.HAPTIC_FEEDBACK_LEN, 45);
//        }
//
//        //remove all the old local html files
//        new Thread(new Runnable() {
//
//            @Override
//            public void run() {
//                WebSearchLocalAssistant.updateLocalHtml(ModelManager
//                        .getContext());
//                PrefUtil.setKey(PrefKeys2.WEBSEARCH_SKIN_CHANGED, true);
//            }
//        }).start();
//        MissedCallClean.setFirstTimeUse();
//        PrefEssentialUtil.setKey(PrefEssentialKeys.APK_LAST_VERSION, oldVersion);
//
//        replaceServiceRCFile();
//
//        PrefUtil.setKey(VisualKeyboardUtil.PREF_DATA_FILE_NAME, "");
//
//        //re-get pre-marketing file while update
//        if (BuildConfig.ENABLE_PRE_MARKETING_ACTIVITIES) {
//        	PrefUtil.setKey(
//					PrefKeys.HAS_PRE_MARKETING_ACTIVITIES_INITIALIZED,
//					false);
//        } else {
//            //do not show the new flag of marketing activities in settings while update to exclude-activities version
//            PrefUtil.setKey(
//                    PrefKeys.SETTING_MARKETING_PUSH_EVENT_SHOWED_NEW_FLAG,
//                    false);
//            PrefUtil.setKey(
//                    PrefKeys.SETTING_MARKETING_PUSH_EVENT_SHOWED_FLAG,
//                    false);
//        }
//
//        SkinManager.getInst().downloadCurrentSkin();
//
//        //try to take over system dialer or contact
//        // setting the key to be true means to take over.
//        boolean isUpgradeInstall =
//                PrefUtil.getKeyInt(StartupVerifier.INSTALL_TYPE, StartupVerifier.INSTALL_TYPE_NEW) == StartupVerifier.INSTALL_TYPE_UPGRADE;
//        if (!PrefUtil.getKeyBoolean(PrefKeys.SYSTEM_DIALER, true)) {
//            if (isUpgradeInstall) {
//                //if it is the upgrade installed, show the take-over wizard only after the hangup outgoing call.
//                PrefUtil.setKey(PrefKeys.SHOW_TAKE_OVER_WIZARD, false); //disable home dialer icon
//                PrefUtil.setKey(PrefKeys.SHOW_TAKE_OVER_WIZARD_WHEN_HANGUP_OUTGOING, true);
//                PrefUtil.setKey(PrefKeys.FIRST_IN_SYSTEM_APP, false); //disable notification
//
//            } else {
//                //if it is newly installed, show the wizard at 3 occasions.
//                //dialer icon in the home, hangup outgoing call and notification.
//                PrefUtil.deleteKey(PrefKeys.SYSTEM_DIALER);
//                PrefUtil.setKey(PrefKeys.SHOW_TAKE_OVER_WIZARD, true);
//            }
//            PrefUtil.setKey(PrefKeys.TT_SYSTEM_DIALER_TAKE_OVER_WHEN_CLICK_ICON, 0L);
//        }
//
//        if (!PrefUtil.containsKey(PrivateContactPasswordManager.PRIVATE_PASSWORD_KEY)) {
//            PrivateContactUtil.restorePasswordIfAny();
//        }
//
//        File file = new File(context.getFilesDir(), FileUtils.LOCAL_CALLERID_FILE);
//        if (file.exists()) {
//            file.delete();
//        }
//
//        if (PrefUtil.containsKey(PrefKeys.TOAST_OPENED)) {
//            PrefUtil.setKey(PrefKeys.TOAST_GUIDE_SHOULD_SHOW, false);
//        } else {
//            PrefUtil.setKey(PrefKeys.TOAST_GUIDE_SHOULD_SHOW, true);
//            PrefUtil.setKey(PrefKeys.TOAST_OPENED, true);
//        }
//    }
//
//    private static void activate(String type) {
//        PrefUtil.setKey(Activator.ACTIVATE_TYPE, type);
//        new TAsyncTask<Void, Void, Void>() {
//            @Override
//            protected Void doInBackground(Void... params) {
//                Activator.freshActivate();
//                return null;
//            }
//        }.execute();
//    }
//
//    private static void setUpdateStartTime() {
//        long currentHr = System.currentTimeMillis() / 1000 / 60 / 60;
//        PrefUtil.setKey(START_TIMES, 0);
//        PrefUtil.setKey(LAST_UPDATE_HR, currentHr);
//    }
//
//
//    private static void clearOldFiles() {
//        PrefUtil.deleteKey(ModelCalllog.CALLLOG_CACHED_DEVICE_TYPE);
//        // need reactivate when update
//        PrefUtil.deleteKey(Activator.IS_ACTIVATED);
//        PrefUtil.deleteKey(PrefKeys.CALL_ACTIVATED);
//        PrefUtil.deleteKey(PrefKeys.BLOCK_HISTORY_READ_COUNT);
//        PrefUtil.deleteKey(PrefKeys.YP_TIPS_TDIALER);
//        PrefUtil.deleteKey("set_default_app");
//        PrefUtil.deleteKey("default_app_rule");
//        PrefUtil.deleteKey("dialer_start_time");
////        PrefUtil.deleteKey(PrefKeys.TEST_VERSION_ACTIVATION_SUCCEED);
//        PrefUtil.deleteKey(PrefKeys.PREF_SHOW_STARTUP);
//
//        //clear update information
//        PrefUtil.deleteKey(PrefKeys.IN_APP_APP_UPDATE);
//        PrefUtil.deleteKey(PrefKeys.UPDATE_APK_URL);
//        PrefUtil.deleteKey(PrefKeys.UPDATE_APK_DESCRIPTION);
//        PrefUtil.deleteKey(PrefKeys.UPDATE_APK_FILE_PATH);
//        PrefUtil.deleteKey(PrefKeys.APP_UPDATER_SETTING);
//        PrefUtil.deleteKey(PrefKeys.APP_UPDATER_INFO);
//        PrefUtil.deleteKey(PrefKeys.APP_UPDATER_SETTING_MANUAL_NEW);
//        PrefUtil.deleteKey(PrefKeys.APP_UPDATER_INFO_MANUAL);
//        PrefUtil.deleteKey(PrefKeys.APP_UPDATER_MENU);
//        // remove old conuntry imgs
//        File dir = ModelManager.getContext().getFilesDir();
//        File[] files = dir.listFiles(new FileFilter() {
//            @Override
//            public boolean accept(File pathname) {
//                String name = pathname.getName();
//                if(name != null) {
//					return name
//							.equals(YellowPageManager.NATIONAL_MAIN_CALLERID_FILE_NAME)
//							|| name.equals(YellowPageManager.NATIONAL_MAIN_CLASSIFY_FILE_NAME);
//                } else {
//                	return false;
//                }
//            }
//        });
//        if (files != null) {
//            for (File f : files) {
//                f.delete();
//            }
//        }
//
//        // remove offline caller id cache
//        CallerIDUtil.cleanCache();
//
//        File favoriteOrder = new File(dir, "favorite_order.config");
//        if (favoriteOrder.exists()) {
//            favoriteOrder.delete();
//        }
//    }
//
//    @LaunchPerf
//	public static boolean onLaunch(Context ctx) {
//		// no key means first.
//        boolean showPopup = false;
//        final boolean showAds = shouldShowAds(ctx);
//		showPopup = showPopup(ctx, showAds);
//		final int launchTimes = PrefUtil.getKeyInt(PrefKeys.LAUNCH_APP_TIMES, 0) + 1;
//        PrefUtil.setKey(PrefKeys.LAUNCH_APP_TIMES, launchTimes);
//        /* This is for preinstall use */
//        if (launchTimes == 5) {
//            PrefUtil.setKey(PrefKeys.LAUNCH_APP_TIMES+"_5", System.currentTimeMillis());
//        }
//        //update location
//        ModelManager.getInst().getHandler().postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                long lastCheckTime = PrefUtil.getKeyLong(PrefKeys.LOCATE_DONE_AFTER_INIT_TIME, 0);
//                final long curTime = System.currentTimeMillis();
//                boolean showLandingPage = PrefEssentialUtil.getKeyBoolean(PrefEssentialKeys.NEED_SHOW_LANDING_PAGE, true);
//                if (curTime - lastCheckTime > 5 * DateUtils.HOUR_IN_MILLIS && lastCheckTime > 0) {
//                    if ((!OSUtil.isHuawei() && !OSUtil.isMiuiV5()) || (launchTimes > 1 && !showAds && !showLandingPage)) {
//                        WebSearchLocateManager.getInst().update();
//                        PrefUtil.setKey(PrefKeys.LOCATE_DONE_AFTER_INIT_TIME, curTime);
//                    }
//                }
//            }
//        }, 10000);
//
//        return showPopup;
//	}
//
//    private static WindowManager sWindow = null;
//
//    private static WindowManager getWindow(Context ctx) {
//        if (sWindow == null) {
//            sWindow = (WindowManager) ctx
//                    .getSystemService(Context.WINDOW_SERVICE);
//        }
//        return sWindow;
//    }
//
//    private static void destoryWindow() {
//        sWindow = null;
//    }
//
//    @TargetApi(Build.VERSION_CODES.GINGERBREAD)
//    @LaunchPerf
//    private static boolean showPopup(final Context ctx, boolean showAds) {
//        // status
//        boolean showPopup = false;
//
//        boolean showLandingPage = PrefEssentialUtil.getKeyBoolean(PrefEssentialKeys.NEED_SHOW_LANDING_PAGE, true);
//        if (!showAds && !showLandingPage) {
//            showPopup = showFullscreenToast();
//        } else {
//            PrefEssentialUtil.setKey(PrefEssentialKeys.NEED_SHOW_LANDING_PAGE, false);
//            showLandingPage(ctx, showAds, showLandingPage);
//            showPopup = true;
//        }
//        return showPopup;
//    }
//
//    private static void showLandingPage(final Context ctx, boolean showAds, boolean showAppGuide) {
//        Intent intent = new Intent(ctx, LandingPageActivity.class);
//        intent.putExtra(LandingPageActivity.EXTRA_SHOW_ADS, showAds);
//        intent.putExtra(LandingPageActivity.EXTRA_SHOW_APP_GUIDE, showAppGuide);
//        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//        ctx.startActivity(intent);
//    }
//
//    @LaunchPerf
//    private static boolean shouldShowAds(Context ctx) {
//		String activateType = PrefUtil.getKeyString(Activator.ACTIVATE_TYPE,
//				Activator.ACTIVATE_TYPE_NEW);
//		if(activateType.equals(Activator.ACTIVATE_TYPE_UPGRADE)
//                || BuildConfig.SHOW_ADS_CHANNEL.length == 0) {
//			return false;
//		}
//        int ct = PrefUtil.getKeyInt(START_TIMES, 0);
//        if (ct < 3 && !PrefUtil.getKeyBoolean(PrefKeys.DUALSIM_RESTART, false)) {
//            boolean showAds = false;
//            long hr = PrefUtil.getKeyLong(LAST_UPDATE_HR, 0);
//            long cur = System.currentTimeMillis() / 1000 / 60 / 60;
//            if (TLog.DBG) {
//                TLog.i("showAds", "hr: " + hr + ", cur: " + cur);
//            }
//            if (cur - hr < 24) {
//                String channelCode = ChannelCodeUtils.getChannelCode(ctx);
//                for (String channel : BuildConfig.SHOW_ADS_CHANNEL) {
//                    if (channel.equals(channelCode)) {
//                        showAds = true;
//                        break;
//                    }
//                }
//
//            }
//            PrefUtil.setKey(START_TIMES, ct + 1);
//            return showAds;
//        }
//        PrefUtil.setKey(PrefKeys.DUALSIM_RESTART, false);
//        return false;
//    }
//
//    private static boolean showFullscreenToast() {
//        boolean showToast = false;
//        if (PresentationClient.isInitialized()) {
//            FullscreenToast fullscreenMessage = PresentationClient
//                    .getInstance().getFullscreenToast();
//            if (fullscreenMessage != null) {
//                Intent intent = new Intent(ModelManager.getContext(),
//                        FullscreenConnect.class);
//                intent.putExtra(IntentUtil.URL_ADDRESS, "file://"
//                        + fullscreenMessage.showPath);
//                String condition = fullscreenMessage.getCondition();
//                if (!TextUtils.isEmpty(condition)) {
//                    try {
//                        JSONObject json = new JSONObject(condition);
//                        String animationType = json.getString(PresentationConst.TOAST_ATTR_ANIMATION_TYPE);
//                        String duration = json.getString(PresentationConst.TOAST_ATTR_DURATION);
//                        intent.putExtra(
//                                FullscreenConnect.FULLSCREEN_ANIMATION_TYPE, animationType);
//                        intent.putExtra(
//                                FullscreenConnect.FULLSCREEN_DURATION, duration);
//
//                    } catch (JSONException e) {
//                        TLog.printStackTrace(e);
//                    }
//                }
//                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                ModelManager.getContext().startActivity(intent);
//                PresentationClient.getInstance().showToast(
//                        fullscreenMessage.getId());
//                PresentationClient.getInstance().closeToast(
//                        fullscreenMessage.getId());
//                PresentationClient.getInstance().saveData();
//                showToast = true;
//            }
//        }
//        return showToast;
//    }
//
//    /*
//     * add for Pro
//     */
//    private static void replaceServiceRCFile() {
//		File target = new File(ServicePrefManager.PATH_LOCAL);
//		if (target.exists()) {
//			target.delete();
//		}
//		FileUtils.copyRawFile(R.raw.freecalls_servicerc, target);
//    }


}
