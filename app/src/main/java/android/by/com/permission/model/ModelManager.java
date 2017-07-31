package android.by.com.permission.model;

import android.annotation.TargetApi;
import android.by.com.permission.MyApplication;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.os.StrictMode;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;



public class ModelManager extends Observable {
    private static final String TAG = "ModelManager";
    // The id of all models which extends Model.
    private static final int MODEL_ACCOUNT_AND_GROUP = 0;
    private static final int MODEL_BLACK_LIST = 1;
    private static final int MODEL_CALL_LOG = 2;
    private static final int MODEL_CONTACT = 5;
    private static final int MODEL_GESTURE = 6;
    private static final int MODEL_MESSAGE = 7;
    private static final int MODEL_RULE = 10;
    private static final int MODEL_STATUS = 11;
    private static final int MODEL_VALUE = 12;
    private static final int MODEL_PLUGIN = 15;
    private static final int MODEL_CALLER_ID = 16;
    private static final int MODEL_C2CUSER_LIST = 17;
    private static final int MODEL_SMARTSEARCH = 18;

    /**
     * The count of the models.
     */
    static final int ALL_MODEL_COUNT = 19;

    private static volatile ModelManager sInstance = null;
    private static final int OBSERVER_INDEXBASE = 1500;
    public static final int SMS_DATABASE_CHANGE = 5 + OBSERVER_INDEXBASE;

    public static final int ON_MODEL_QUERY_COMPLETE = 7 + OBSERVER_INDEXBASE;
    public static final int ON_MODEL_INSERT_COMPLETE = 8 + OBSERVER_INDEXBASE;
    public static final int ON_MODEL_UPDATE_COMPLETE = 9 + OBSERVER_INDEXBASE;
    public static final int ON_MODEL_DELETE_COMPLETE = 10 + OBSERVER_INDEXBASE;

    public static final int START_QUERY_GROUP = 13 + OBSERVER_INDEXBASE;
    public static final int PHOTO_CHANGE = 15 + OBSERVER_INDEXBASE;

    public static final int SET_BLACK = 16 + OBSERVER_INDEXBASE;
    public static final int SET_VOICEMAIL = 17 + OBSERVER_INDEXBASE;
    public static final int KEYBOARD_FUNC_COPY = 18 + OBSERVER_INDEXBASE;
    public static final int KEYBOARD_FUNC_PASTE = 19 + OBSERVER_INDEXBASE;

    public static final int CONTACT_SNAPSHOT_CHANGE = 20 + OBSERVER_INDEXBASE;
    public static final int ATTR_CHANGE = 24 + OBSERVER_INDEXBASE;
    public static final int CALL_OUT = 25 + OBSERVER_INDEXBASE;
    public static final int KEYBOARD_TYPE_CHANGE = 26 + OBSERVER_INDEXBASE;
    public final static int INAPP_READY = 27 + OBSERVER_INDEXBASE;

    public final static int GESTURE_RECOGNIZE_COMPLETED = 28 + OBSERVER_INDEXBASE;
    public final static int ON_GESTURE_QUERY_COMPLETED = 29 + OBSERVER_INDEXBASE;
    public final static int GESTURE_LIB_CHANGED = 30 + OBSERVER_INDEXBASE;
    public final static int CONTACTS_SNAPSHOT_DONE = 31 + OBSERVER_INDEXBASE;
    public final static int REFRESH_FASTSCROLL = 34 + OBSERVER_INDEXBASE;
    public final static int TODO_ALARM = 35 + OBSERVER_INDEXBASE;
    public final static int UPDATE_INAPP_CONFIRM = 36 + OBSERVER_INDEXBASE;
    public final static int SKIN_GUIDE_CANCEL = 37 + OBSERVER_INDEXBASE;
    public final static int DIAL_SKIN_IMAGE_CHANGE = 38 + OBSERVER_INDEXBASE;

    public final static int FINISH_MARKETING_INIT = 39 + OBSERVER_INDEXBASE;
    public final static int UPDATE_TAB_NUMBER = 40 + OBSERVER_INDEXBASE;
    public final static int AUTO_STARTUP_DISABLED = 41 + OBSERVER_INDEXBASE;
    public final static int SHOW_KEYBOARD_SEARCH_GUIDE = 42 + OBSERVER_INDEXBASE;
    public final static int SHOW_WALLET_GUIDE_IN_FUNCBAR = 43 + OBSERVER_INDEXBASE;
    public final static int GESTURE_ACTION_SHOWN = 44 + OBSERVER_INDEXBASE;
    public final static int GESTURE_DISMISSED = 45 + OBSERVER_INDEXBASE;
    public final static int PREFERENCE_CHANGED = 46 + OBSERVER_INDEXBASE;

    public final static int RESET_SIMCARD_NAME = 47 + OBSERVER_INDEXBASE;
    public final static int REDPACKET_TO_DISCOVERY = 48 + OBSERVER_INDEXBASE;

    private static final int MSG_BASE = 1600;
    public final static int MSG_POSTNEWS = 9 + MSG_BASE;
    public final static int MSG_WS_TOKEN_EXPIRED = 10 + MSG_BASE;
    public final static int MSG_WS_METHOD_NOT_ALLOWED = 11 + MSG_BASE;

    private static boolean sEnvironmentSetup = false;

    private static Object initializerLock = new Object();

    private boolean mRegisterContentObserver;


    private ArrayList<String> mPickedContactForWidget = new ArrayList<String>(4);
    private boolean restoreWidgetFlag = false;

//    public static void deinitContext() {
//        synchronized (initializerLock) {
//            SkinManager.deinit();
//        }
//    }

    @TargetApi(Build.VERSION_CODES.GINGERBREAD)
    private static void enableStrictMode() {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.GINGERBREAD) {
            return;
        }

        StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder()
                .detectAll().penaltyLog().build());
        StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder()
                .detectAll().penaltyLog().build());
    }

    public static boolean isEnvironmentSetup() {
        return sEnvironmentSetup;
    }

//    @LaunchPerf
    public static void setupEnvironment(final Context appContext) {
        //TLog.i(TAG, "looop log, modelmanager setupenvironment, process: " + ProcessUtil.getCurrentProcessName(appContext));

        synchronized (initializerLock) {
            if (!sEnvironmentSetup) {
//                Log.d(TAG, "setupEnvironment");
//                if (Configs.ENABLE_DEBUG_STRICT_MODE) {
//                    enableStrictMode();
//                }
//                EdenActive.initialize(new EdenAssist());
//                PrefUtilWidget.initialize(appContext);
//                StartupVerifier.init(appContext);
//                TPTelephonyManager.init();
//                StartupCommercialManager.init();
//                //init PhoneNumberUtil
//                new Thread(new Runnable() {
//                    @Override
//                    public void run() {
//                        if (Build.VERSION.SDK_INT > 20) {
//                            PhoneNumberUtils.formatNumber("13800000000", "CN");
//                        }
//                    }
//                }).start();
//
//                if (TMemory.MEMDUMP) {
//                    TMemory.dump("after startup verifier.initialize()");
//                }
                sInstance.init();
                sEnvironmentSetup = true;
            }
        }
    }

    public static void initialize(Context appContext) {
        if (sInstance != null) {
//            TLog.printStackTrace(new IllegalStateException("ModelManager already initialized"));
            return;
        }
        sInstance = new ModelManager(appContext);
    }


//    private ScreenStateReceiver mScreenStateReceiver;

    //    @LaunchPerf
    private void init() {
//        if (Configs.ENABLE_CRASH_REPORT) {
//            if (!TestConfig.TEST_MODE) {
//                Thread.setDefaultUncaughtExceptionHandler(new TExceptionHandler());
//            }
//        }
        ApplicationEnvInit(ModelManager.getInst());

        ModelManager.getInst().getHandler().postDelayed(new Runnable() {
            @Override
            public void run() {
                IntentFilter smsfilter = new IntentFilter();
                smsfilter.addAction("android.provider.Telephony.SMS_RECEIVED");
                smsfilter.addCategory(Intent.CATEGORY_DEFAULT);
                smsfilter.setPriority(2147483647);
//                mAppCtx.registerReceiver(new SMSStateReceiver(), smsfilter);
//                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH) {
//                    mScreenStateReceiver = new ScreenStateReceiver();
//                    IntentFilter filter = new IntentFilter();
//                    filter.addAction(Intent.ACTION_SCREEN_OFF);
//                    filter.addAction(Intent.ACTION_SCREEN_ON);
//                    filter.addAction(Intent.ACTION_USER_PRESENT);
//                    mAppCtx.registerReceiver(mScreenStateReceiver, filter);
//                }
            }
        }, 5000);


//        if (Configs.ENABLE_OEM){
//            PackageUtil.isOnlinePackageInstalled();
//        }
//
//        getSmartSearch().asyncInitSmartSearchIndex();
    }

    //    @LaunchPerf
    private void ApplicationEnvInit(ModelManager model) {
        // Load Profile
//        model.getRule().loadProfiles();
        // Load screen status from system
        // ModelManager.getInst().getStatus().setScreenOn(false);
    }

    public static ModelManager getInst() {
        if (sInstance == null) {
            throw new IllegalStateException(
                    "Call ModelManager.init(AppCtx) first.");
        }

        return sInstance;
    }

    @Deprecated
    public static Context getContext() {
        return MyApplication.getAppContext();
    }

    private Context mAppCtx = null;

    //    private Model[] mModels = null;
//    private ContactSynchronizer contactObserver = null;
//    private CallLogSynchronizer callLogObserver = null;
    private Handler mHandler = new Handler(Looper.getMainLooper());

    private ModelManager(Context context) {
        mAppCtx = context;
//        mModels = new Model[ALL_MODEL_COUNT];
    }

    @Deprecated
    public Context getAppCtx() {
        return mAppCtx;
    }

    public Handler getHandler() {
        return mHandler;
    }

    public ContentResolver getCR() {
        return mAppCtx.getContentResolver();
    }


//    /**
//     * Get current version name.
//     *
//     * @return the version name. {@link Constants#EMPTY_STR}, if occur any
//     *         error.
//     */
//    public String getCurVersionName() {
//        try {
//            return mAppCtx.getPackageManager().getPackageInfo(
//                    mAppCtx.getPackageName(), 0).versionName;
//        } catch (NameNotFoundException e) {
//            TLog.printStackTrace(e);
//        }
//        return Constants.EMPTY_STR;
//    }

    public void addViewListener(Observer observer) {
        this.addObserver(observer);
    }

    public void deleteViewListener(Observer observer) {
        this.deleteObserver(observer);
    }

    public void notifyObservers() {
        this.setChanged();
        super.notifyObservers();
    }

    public void notifyObservers(Object message) {
        this.setChanged();
        super.notifyObservers(message);
    }

//    public ModelStatus getStatus() {
//        return (ModelStatus) getModel(MODEL_STATUS);
//    }
//
//    public ModelValue getValue() {
//        return (ModelValue) getModel(MODEL_VALUE);
//    }
//
//    public ModelCalllog getCalllog() {
//        return (ModelCalllog) getModel(MODEL_CALL_LOG);
//    }
//
//    public ModelMessage getSMSMessage() {
//        return (ModelMessage) getModel(MODEL_MESSAGE);
//    }
//
//    public ModelContact getContact() {
//        return (ModelContact) getModel(MODEL_CONTACT);
//    }
//
//    public ModelPlugin getPlugin() {
//        return (ModelPlugin) getModel(MODEL_PLUGIN);
//    }
//
//    public ModelAccountAndGroup getAccountAndGroup() {
//        return (ModelAccountAndGroup) getModel(MODEL_ACCOUNT_AND_GROUP);
//    }
//
//    public ModelBlackList getBlackList() {
//        return (ModelBlackList) getModel(MODEL_BLACK_LIST);
//    }
//
//    public ModelC2CUserList getC2CUserList() {
//    	return (ModelC2CUserList) getModel(MODEL_C2CUSER_LIST);
//    }
//
//    public ModelGesture getGesture() {
//        return (ModelGesture) getModel(MODEL_GESTURE);
//    }
//
//    public ModelRule getRule() {
//        return (ModelRule) getModel(MODEL_RULE);
//    }
//
//    public ModelCallerId getCallerId() {
//    	return (ModelCallerId) getModel(MODEL_CALLER_ID);
//    }
//
//    public ModelSmartSearch getSmartSearch() {
//    	return (ModelSmartSearch) getModel(MODEL_SMARTSEARCH);
//    }
//
//    private Model getModel(int modelId) {
//        if (mModels[modelId] == null) {
//            if (TMemory.MEMDUMP) {
//                TMemory.dump("before create model: " + modelId);
//            }
//            mModels[modelId] = createModel(modelId);
//            if (TMemory.MEMDUMP) {
//                TMemory.dump("after create model: " + modelId);
//            }
//        }
//
//        return mModels[modelId];
//    }
//
//
//    private Model createModel(int modelId) {
//        switch (modelId) {
//        case MODEL_ACCOUNT_AND_GROUP:
//            return new ModelAccountAndGroup(this);
//        case MODEL_BLACK_LIST:
//            return new ModelBlackList(this);
//        case MODEL_CALL_LOG:
//            return new ModelCalllog(this);
//        case MODEL_CONTACT:
//            return new ModelContact(this);
//        case MODEL_GESTURE:
//            return new ModelGesture(this);
//        case MODEL_MESSAGE:
//            return new ModelMessage(this);
//        case MODEL_RULE:
//            return new ModelRule(this);
//        case MODEL_STATUS:
//            return new ModelStatus(this);
//        case MODEL_VALUE:
//            return new ModelValue(this);
//        case MODEL_PLUGIN:
//            return new ModelPlugin(this);
//        case MODEL_CALLER_ID:
//        	return new ModelCallerId(this);
//        case MODEL_C2CUSER_LIST:
//        	return new ModelC2CUserList(this);
//        case MODEL_SMARTSEARCH:
//        	return new ModelSmartSearch(this);
//        default:
//            throw new IllegalArgumentException(String.format(
//                    Locale.US, "model id does not exist: %d", modelId));
//        }
//    }

//    @Override
//    protected void finalize() throws Throwable {
//        super.finalize();
//        EdenActive.uninitialize();
//        TEngine.destroy();
//        this.deleteObservers();
//    }
//
//    public void updateDualSimColumn() {
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                CalllogProvider.updateDualSimCardName();
//                //notify screen dialer calllog changes
//                if (callLogObserver != null) {
//                    callLogObserver.onChange(false);
//                }
//            }
//        }).start();
//    }
//
//    /**
//     * register content observer for calllog and contact change
//     * @param ctx
//     * @param notifyImmediately true if notify observer immediately
//     */
//    @LaunchPerf
    public void registerContentObserver(Context ctx, boolean notifyImmediately) {
        if (mRegisterContentObserver) {
            return;
        }
        mRegisterContentObserver = true;
//        if (contactObserver == null) {
//            contactObserver = new ContactSynchronizer(ctx);
//            ctx.getContentResolver().registerContentObserver(Contacts.CONTENT_URI,
//                    true, contactObserver);
//        }
//        if (callLogObserver == null) {
//            callLogObserver = new CallLogSynchronizer(ctx);
//            ctx.getContentResolver().registerContentObserver(CallLog.CONTENT_URI,
//                    true, callLogObserver);
//        }
//
//        if (notifyImmediately) {
//            forceNotifyDataChange();
//        }
//
//        TLog.d(ModelManager.class, "===Start listening to database change===");
    }
//
//    /**
//     *  Force notify contact/calllog change,
//     *  so we have chance to update data after permission changed
//     *  The change will be ignored if permission is allowed before
//     *  and data has loaded successfully already
//     */
//    public void forceNotifyDataChange() {
//    	if(contactObserver != null) {
//    		contactObserver.onChange(true);
//    	}
//    	if(callLogObserver != null) {
//    		callLogObserver.onChange(true);
//    	}
//    }
//
//    public void unregisterContentObserver(Context ctx) {
//        ctx.getContentResolver().unregisterContentObserver(contactObserver);
//        ctx.getContentResolver().unregisterContentObserver(callLogObserver);
//        TLog.d(ModelManager.class, "===Stop listening to database change===");
//    }
//
//    public void onModelQueryComplete(Cursor c, Object cookie) {
//        if (c != null) {
//            TLog.d(ModelManager.class, "Cursor query complete");
//            this.notifyObservers(new CursorMessage(ON_MODEL_QUERY_COMPLETE, c,
//                    cookie));
//        }
//    }
//
//    public void onGestureQueryComplete(ArrayList<NamedGesture> userdata,
//                                       ArrayList<NamedGesture> sysdata, ArrayList<String> unknownSet,
//                                       Object cookie) {
//        TLog.d(ModelManager.class, "Gesture query complete");
//        this.notifyObservers(new GestureQueryMessage(
//                ON_GESTURE_QUERY_COMPLETED, userdata, sysdata, unknownSet,
//                cookie));
//    }
//
//    public void gestureLibChanged(int changeType, NamedGesture gesture) {
//        this.notifyObservers(new GestureChangeMessage(GESTURE_LIB_CHANGED,
//                changeType, gesture));
//    }
//
//    public void gestureRecognizeDone(RecognizeResult result) {
//        RxBus.getIns().post(new GestureResultMessage(
//                GESTURE_RECOGNIZE_COMPLETED, result));
//    }
//
//    public void smsDataBaseChanged() {
//        this.notifyObservers(new BaseMessage(SMS_DATABASE_CHANGE));
//    }
//
//    public void photoChanged(HashSet<Long> changeSet) {
//    	PhotoManager.getInstance().onPhotoChange(changeSet);
//        this.notifyObservers(new BaseMessage(PHOTO_CHANGE));
//    }
//
//    public void contactSnapshotChanged() {
//        this.notifyObservers(new BaseMessage(CONTACT_SNAPSHOT_CHANGE));
//    }
//
//    public void startQueryGroup() {
//        this.notifyObservers(new BaseMessage(START_QUERY_GROUP));
//    }
//
//    public void notifySnapshotDone(boolean scanCallerId) {
//        this.getCalllog().asyncUpdate(true, scanCallerId);
//        getContact().onContactSnapshotChanged();
//
//        notifyObservers(new BaseMessage(CONTACTS_SNAPSHOT_DONE));
//    }
//
//    public void setBlackState(long cid, int blackstate) {
//        Bundle bundle = new Bundle();
//        bundle.putLong(Constants.BUNDLE_CONTACTID, cid);
//        bundle.putInt(Constants.BUNDLE_BLACK_STATE, blackstate);
//        RxBus.getIns().post(new BundleMessage(SET_BLACK, bundle));
//    }
//
//    public void setBlackState(String number, int blackstate) {
//        Bundle bundle = new Bundle();
//        bundle.putLong(Constants.BUNDLE_CONTACTID, 0);
//        bundle.putInt(Constants.BUNDLE_BLACK_STATE, blackstate);
//        bundle.putString(Constants.BUNDLE_NUMBER, number);
//        RxBus.getIns().post(new BundleMessage(SET_BLACK, bundle));
//    }
//
//    public void setVoiceMail(long cid, boolean isVoiceMail) {
//        Bundle bundle = new Bundle();
//        bundle.putLong(Constants.BUNDLE_CONTACTID, cid);
//        bundle.putBoolean(Constants.BUNDLE_ISVOICEMAIL, isVoiceMail);
//        this.notifyObservers(new BundleMessage(SET_VOICEMAIL, bundle));
//    }
//
//    public void refreshFastScroll() {
//    	this.notifyObservers(new BaseMessage(REFRESH_FASTSCROLL));
//    }
//
////    private static final String CLAUSE_ONLY_VISIBLE = Contacts.IN_VISIBLE_GROUP
////            + "=1";
//    private static final String CLAUSE_ONLY_PHONES = Contacts.HAS_PHONE_NUMBER
//            + "=1";
//
//    public String getContactSelection() {
//        boolean hasNumber = !PrefUtil.getKeyBooleanRes(
//                PrefKeys.CONTACTS_WITHOUT_NUMBER,
//                R.bool.pref_contactswithoutnumber_default);
//        String selection = null;
//        if (hasNumber) {
//            selection = CLAUSE_ONLY_PHONES;
//        }
//        return selection;
//
//    }
//
//
//    /**
//     * add for tabbar widget
//     */
//    public ArrayList<String> getPickedWidgetContacts(){
//        if (mPickedContactForWidget == null) {
//            mPickedContactForWidget = new ArrayList<String>(4);
//        }
//
//        return mPickedContactForWidget;
//    }
//
//    public void addWidgetContactId(String idWithNum) {
//    	if (ModelManager.getInst().getPickedWidgetContacts().size() >= 4) {
//    		return;
//    	}
//
//        if(!mPickedContactForWidget.contains(idWithNum)) {
//            mPickedContactForWidget.add(idWithNum);
//        }
//    }
//
//    public void removeWidgetContactId(String idWithNum) {
//        if(mPickedContactForWidget.contains(idWithNum)) {
//            mPickedContactForWidget.remove(idWithNum);
//        }
//    }
//
//	public ArrayList<String> getSelectedWidgetFromPref() {
//		ArrayList<String> topItemsIdWithPhone = new ArrayList<String>(4);
//        StringBuilder slotKey = new StringBuilder();
//        for (int i = 0; i < TableWidgetOf91.MAX_SLOT_COUNT; i++) {
//            slotKey.append("WIDGET_SLOT_" + i);
//            String id_key = PrefUtilWidget.getWidgetValue(slotKey.toString(), "empty");
//
//            if (!topItemsIdWithPhone.contains(id_key)
//            		&& !id_key.equals("empty")) {
//            	topItemsIdWithPhone.add(id_key);
//            }
//            slotKey.setLength(0);
//        }
//
//        PrefUtilWidget.setKey(PrefKeys.SELECT_WIDGET_FROM_PREF_COUNT, topItemsIdWithPhone.size());
//
//        return topItemsIdWithPhone;
//	}
//
//	public boolean getRestorePrefWidgetFlag() {
//		return restoreWidgetFlag;
//	}
//
//	public void setRestorePrefWidgetFlag(boolean flag) {
//		restoreWidgetFlag = flag;
//	}
//
//	public boolean isCurrentForeground () {
//	    return PackageUtil.isOurAppAtTop();
//	}

    /**
     * end
     */

}



