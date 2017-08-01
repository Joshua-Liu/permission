/**
 * 
 */
package android.by.com.permission.util;

import android.os.Environment;
import android.os.StatFs;


import java.io.File;

/**
 * Utility class for access SD card.
 * 
 * @author ThomasYe
 */
public class ExternalStorage {
    
    // Use the TouchPalContact directory to save files.
    private static final String TOUCHPAL_DIR = "TouchPalContact";
    
    /**
     * Set external storage status. As the system would send an EJECT broadcast
     * before unmount the external storage, we should set it to busy status and
     * reject all external storage access. After MOUNTED broadcast is received,
     * the status should be set to not busy.
     */
    public static boolean isBusy = false;
    
    /**
     * Get TouchPal sub directory.
     * 
     * @param dir
     *            the sub directory name.
     * @return the directory. If it doesn't exist & can't be make, return
     *         {@code null}.
     */
    public static File getDirectory(String dir) {
        if (Environment.getExternalStorageState().equals(
                Environment.MEDIA_MOUNTED)
                && !isBusy) {
//            if (TLog.DBG) {
//                TLog.i("ExternalStorage", "mounted: " + dir);
//            }
            File sdcard = Environment.getExternalStorageDirectory();
            File touchpal = new File(sdcard, TOUCHPAL_DIR);
            if (!touchpal.exists()) {
                boolean result = touchpal.mkdir();
                if (!result) {
                    return null;
                }
            }
            File sub = new File(touchpal, dir);
            if (!sub.exists()) {
                boolean result = sub.mkdir();
                if (!result) {
                    return null;
                }
            }
            return sub;
        }
        return null;
    }
    
    /**
     * @return the external storage mount root.
     */
    public static File getSdcardRoot() {
        return Environment.getExternalStorageDirectory();
    }

    public static File getTouchPalContactDir() {
        File touchpal = new File(Environment.getExternalStorageDirectory(), TOUCHPAL_DIR);
        if (!touchpal.exists()) {
            boolean result = touchpal.mkdir();
            if (!result) {
                return null;
            }
        }
        return touchpal;
    }
    
    
    public static int getAvailableSdSize(){
        StatFs stat = new StatFs(ExternalStorage.getSdcardRoot().getPath());
    	
    	long blockSize = stat.getBlockSize();
    	long blocks = stat.getAvailableBlocks();
    	
    	return  (int) (blocks * blockSize / 1024/1024) ;
    	
    }
    
    public static boolean isSdcardEnable() {
        return Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState());
    }
    
}
