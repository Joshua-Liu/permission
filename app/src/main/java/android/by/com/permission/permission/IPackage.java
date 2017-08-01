/**
 * 
 */
package android.by.com.permission.permission;

import android.content.res.AssetManager;
import android.content.res.Resources;

/**
 * Interface for accessing the packages no matter it is installed or just put in
 * the files directory.
 * 
 * @author ThomasYe
 */
public interface IPackage {
    
    /**
     * Give the resources of the package.
     * 
     * @return the resources
     */
    public Resources getResources();
    
    /**
     * Give the package name of the package.
     * 
     * @return the package name e.g. "com.cootek.smartdialer"
     */
    public String getPackageName();
    
    /**
     * Give the asset manager of the package.
     * 
     * @return the asset manager
     */
    public AssetManager getAssets();
    
    /**
     * Give the package file path.
     * 
     * @return the file path. If the package is installed, it would return the
     *         package name.
     */
    public String getFilePath();
    
    /**
     * Give the package install status.
     * 
     * @return {@code true} if the package is installed in android system,
     *         {@code false} if the package is not installed.
     */
    public boolean isInstalled();
    
    /**
     * Delete self. Other function should not be called after
     * {@code deleteSelf()} is called. Since delete or uninstall may be cancel,
     * we would requery all relevant packages.
     */
    public void deleteSelf();
    
    /**
     * Give the package id.
     * 
     * @return the package id. If the package is installed, the id is the
     *         package name. Or it would be the file path
     */
    public String getIdentifier();

    /**
     * Give the result of the package is support sound or not.
     *
     * @return the detection of sound support, true or false
     */
    public boolean isSupportTheme();

}
