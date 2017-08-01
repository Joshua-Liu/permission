package android.by.com.permission.util;

import android.annotation.TargetApi;
import android.by.com.permission.MyApplication;
import android.by.com.permission.model.ModelManager;
import android.content.res.AssetManager;
import android.os.Build;
import android.os.Environment;
import android.os.StatFs;
import android.text.TextUtils;
import android.text.format.Formatter;
import android.util.Log;
import android.util.Xml;



import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xmlpull.v1.XmlPullParser;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.RandomAccessFile;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

public class FileUtils {

	private static final int COPY_FILE_BUFFER_LENGTH = 4 * 1024;
    public static final String LOCAL_CALLERID_FILE = "callerid_contact.json";
    private static final String TOUCHPAL_CONTACTS_DIR = "TouchPalContact";
    private static final String TAG = "FileUtils";
	
	public static void copyFile(File in, File out) {
		if (in.exists()) {
			if (!out.exists()) {
				try {
					out.createNewFile();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			
			FileInputStream is = null;
			FileOutputStream os = null;
			try {
				is = new FileInputStream(in);
				os = new FileOutputStream(out);
				FileUtils.copyFile(is, os);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				try {
					if (is != null) {
						is.close();
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
				try {
					if (os != null) {
						os.close();
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public static void copyFile(InputStream in, OutputStream out)
			throws IOException {
		byte[] buf = new byte[COPY_FILE_BUFFER_LENGTH];
		int l = 0;
		while ((l = in.read(buf)) > 0) {
			out.write(buf, 0, l);
		}
		out.flush();
	}

    /**
     * do chmod to file or directory
     * @param mode
     *      r:4 w:2 x:1 -:0 
     * @param path
     */
    public static void chmod(String mode, String path) {
        String[] args = {"chmod", mode, path};
        exec(args);
    }

    
    public static String exec(String[] args) {
        String result = "";
        ProcessBuilder processBuilder = new ProcessBuilder(args);
        Process process = null;
        InputStream errIs = null;
        InputStream inIs = null;
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            int read = -1;
            process = processBuilder.start();
            errIs = process.getErrorStream();
            while ((read = errIs.read()) != -1) {
                baos.write(read);
            }
            baos.write('\n');
            inIs = process.getInputStream();
            while ((read = inIs.read()) != -1) {
                baos.write(read);
            }
            byte[] data = baos.toByteArray();
            result = new String(data);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (errIs != null) {
                    errIs.close();
                }
                if (inIs != null) {
                    inIs.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (process != null) {
                process.destroy();
            }
        }
        return result;
    }

    public static boolean copyAssetsData(AssetManager am, String srcPath, String targetPath) {
        try {
            InputStream in = am.open(srcPath);
            FileOutputStream out = new FileOutputStream(targetPath + File.separator + srcPath);
            copyFile(in, out);
            out.close();
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public static boolean copyAssetsDir(AssetManager am, String srcDir, String targetDir) {
        try {
            String[] filenames = am.list(srcDir);
            if (filenames.length == 0) {
                return false;
            }

            File f = new File(targetDir + File.separator + srcDir);
            if(!f.exists())
                f.mkdirs();

            InputStream inputStream = null;
            for(String filename : filenames) {
                if(TextUtils.isEmpty(filename)) continue;
                String name = srcDir + File.separator + filename;

                try {
                    inputStream = am.open(name);
                    inputStream.close();
                    if (copyAssetsData(am, name, targetDir) == false) {
                        return false;
                    }
                } catch (Exception e) {
                    if (copyAssetsDir(am, name, targetDir) == false) {
                        return false;
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public static boolean copyAssetsFile(final String assetsFileName,
            final File target) {
        InputStream input = null;
        FileOutputStream output = null;
        try {
            target.createNewFile();
            input = ModelManager.getContext().getAssets()
                    .open(assetsFileName);
            output = new FileOutputStream(target);

            byte[] buffer = new byte[1024];
            int size = 0;
            while ((size = input.read(buffer)) > 0) {
                output.write(buffer, 0, size);
            }
        } catch (IOException e) {
            return false;
        } finally {
            try {
                if (input != null) {
                    input.close();
                }
            } catch (IOException e) {
                // ignore
            }
            try {
                if (output != null) {
                    output.close();
                }
            } catch (IOException e) {
                // ignore
            }
        }
        return true;
    }
    
    public static void deleteDir(File dir) {
        if (dir != null && dir.isDirectory() && dir.listFiles() != null) {
            for (File child : dir.listFiles()) {
                deleteDir(child);
            }
        }
        dir.delete();
    }

    public static void deleteFile(File file) {
        if (file != null && file.exists()) {
            file.delete();
        }
    }

    public static long getFileLength(String filePath) {
        long fileLength = 0;
        try {
            RandomAccessFile raf = new RandomAccessFile(filePath, "rw");
            fileLength = raf.length();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return fileLength;
    }

    public static boolean truncateFileFromEnd(String filePath, int targetByteSize) {
        boolean ret = false;
        if (!isFileExists(filePath)) {
            return ret;
        }

        String tempFileName = filePath + ".tmp";
        File currentFile = new File(filePath);
        File tempFile = new File(tempFileName);
        try {
            RandomAccessFile raf = new RandomAccessFile(filePath, "rw");
            FileOutputStream fos = new FileOutputStream(tempFileName);
            long originLength = raf.length();
            Log.d("FileUtil", "truncateFileFromEnd: targetByteSize = " + targetByteSize + ", originLength = " + originLength);
            if (targetByteSize < originLength) {
                raf.seek(originLength - targetByteSize);

                final int bufferSize = COPY_FILE_BUFFER_LENGTH;
                byte[] buf = new byte[bufferSize];
                int length = 0;
                while ((length = raf.read(buf)) > 0) {
                    fos.write(buf, 0, length);
                }

                fos.close();
                raf.close();

                copyFile(tempFile, currentFile);
            }
            ret = true;
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            deleteFile(tempFile);
        }
        return ret;
    }


    public static boolean isFileExists(String absolutePath) {
        if (TextUtils.isEmpty(absolutePath)) {
            return false;
        }

        File file = new File(absolutePath);
        return isFileExists(file);
    }

    public static boolean isFileExists(File file) {
        return (file != null && file.exists());
    }

//    public static void saveNote(String content, String fileName) {
//        File dir = ExternalStorage.getDirectory(CallNoteUtil.getRecordPath());
//        if (dir != null) {
//            if (!dir.exists()) {
//                dir.mkdir();
//            }
//            BufferedWriter bw = null;
//            try {
//                bw = new BufferedWriter(new FileWriter(new File(dir, fileName)));
//                bw.write(content);
//            } catch (IOException e) {
//                e.printStackTrace();
//            } finally {
//                try {
//                    if (bw != null) {
//                        bw.close();
//                    }
//                } catch (IOException e) {
//                    // TODO Auto-generated catch block
//                    e.printStackTrace();
//                }
//            }
//        }
//    }
    
    public static String readNote(File file) {
            BufferedReader br = null;
            StringBuilder sb = new StringBuilder();
            String str = null;
            try {
                br = new BufferedReader(new FileReader(file));
                str = br.readLine();
                while (str != null) {
                    sb.append(str);
                    str = br.readLine();
                    if (str != null) {
                        sb.append("\n");
                    }
                }
                return sb.toString();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (br != null) {
                    try {
                        br.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        return null;
    }
    
//    public static String readNote(String fileName) {
//        File dir = ExternalStorage.getDirectory(CallNoteUtil.getRecordPath());
//        if (dir != null) {
//            if (!dir.exists()) {
//                return null;
//            }
//            File file = new File(dir, fileName);
//            return readNote(file);
//        }
//        return null;
//    }
    
    
    
    public static void copyRawFile(int rawFileResId, File target) {
        try {
            FileOutputStream out = ModelManager.getContext()
                    .openFileOutput(target.getName(), 0);
            InputStream in = ModelManager.getContext().getResources()
                    .openRawResource(rawFileResId);
            int readByte;
            byte[] buff = new byte[8048];
            while ((readByte = in.read(buff)) != -1) {
                out.write(buff, 0, readByte);
            }
            out.flush();
            out.close();
            in.close();

        } catch (IOException e) {
            Log.i(TAG,e.getMessage());
        }
    }    
    
    
    
    
    public static String getDataStringFromXml(File file, String TAG) throws Exception {
    	FileInputStream xml = new FileInputStream(file);
        XmlPullParser pullParser = Xml.newPullParser();
        pullParser.setInput(xml, "UTF-8");       
        int event = pullParser.getEventType();
        while (event != XmlPullParser.END_DOCUMENT){
            switch (event) {
            case XmlPullParser.START_DOCUMENT:
                break;
            case XmlPullParser.START_TAG:
            	if (TAG.equals(pullParser.getName())) {
            		return String.valueOf(pullParser.getAttributeValue(0) != null ?
            				pullParser.getAttributeValue(0) : null);
            	}
                break;
            case XmlPullParser.TEXT:
            	break;
            case XmlPullParser.END_TAG:
                break;
            }
            event = pullParser.next();
        }
        return null;
    }
    
    
    
//    public static void saveXmlData(File file, String TAG, String value) throws Exception {
//    	Log.i("dialer", "save xml file " + file.getName());
//        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
//    	DocumentBuilder builder = factory.newDocumentBuilder();
//    	Document document = null;
//    	if (file.exists()) {
//	        document = builder.parse(file);
//    	} else {
//    		document = builder.newDocument();
//    	}
//
//    	if (document == null) {
//    		return;
//    	}
//
//    	if (document.getElementsByTagName(TAG).getLength() == 0)
//    	{
//    		Element root = document.createElement("root");
//    		Element tag=document.createElement(TAG);
//	        tag.setAttribute("value", value);
//	        if (document.getFirstChild() == null) {
//	        	document.appendChild(root);
//	        }
//	        document.getFirstChild().appendChild(tag);
//	        Transformer transformer= TransformerFactory.newInstance().newTransformer();
//	        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
//	        transformer.transform(new DOMSource(document), new StreamResult(file));
//    	}
//    }

    public static void writeStringToFile(String filePath, String content) throws IOException {
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(filePath)));
        writer.write(content);
        writer.close();
    }

    public static long calculateDirectorySize(File file) {
        if (file == null) return 0;
        long size = 0;
        if (file.isDirectory()) {
            File[] children = file.listFiles();
            if (children == null) return 0;
            for (File child : children) {
                size += calculateDirectorySize(child);
            }
        } else {
            size = file.length();
        }
        return size;
    }

    public static List<File> getFileList(File parent) {
        List<File> fileList = new ArrayList<File>();
        FileFilter fileFilter = new FileFilter() {
            @Override
            public boolean accept(File file) {
                if (file.isHidden()) return false;
                return true;
            }
        };
        if (parent.isDirectory()) {
            for (File children : parent.listFiles(fileFilter)) {
                fileList.addAll(getFileList(children));
            }
        } else {
            fileList.add(parent);
        }
        return fileList;
    }

    public static void writeFileByAppend(String dirName , String fileName, String value) {
        String val = value + "\n";
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            File file = new File(ExternalStorage.getDirectory(dirName), fileName);
            if(!file.exists()){
                try {
                    file.createNewFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            try{
                BufferedReader bufferedReader =  null;
                BufferedWriter bufferedWriter = null;
                bufferedReader = new BufferedReader(new StringReader(val));
                bufferedWriter = new BufferedWriter(new FileWriter(file,true));
                char buf[] = new char[1024];
                int len;
                while ((len = bufferedReader.read(buf)) != -1) {
                    bufferedWriter.write(buf, 0, len);
                }
                bufferedWriter.flush();
                bufferedReader.close();
                bufferedWriter.close();
            } catch (Exception e) {

            }

        }
    }

    public static String readSingleLineFile(String fileAbsolutePath) {
        File file = new File(fileAbsolutePath);
        if (!file.exists()) {
            return null;
        }

        if (!file.isFile()) {
            return null;
        }
        
        FileReader reader = null;
        BufferedReader br = null;
        String row;
        try {
            reader = new FileReader(file);
            br = new BufferedReader(reader);
            row = br.readLine();
            return row;
        } catch (IOException e) {
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                }
            }

            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                }
            }
        }

        return null;
    }

    //Total size of SD card
    @TargetApi(Build.VERSION_CODES.CUPCAKE)
    public static String getSDTotalSize() {
        File path = Environment.getExternalStorageDirectory();
        StatFs statFs = new StatFs(path.getPath());
        long blockSize = statFs.getBlockSize();
        long totalBlocks = statFs.getBlockCount();
        return Formatter.formatFileSize(ModelManager.getContext(), blockSize * totalBlocks);
    }

    //Available size of SD card
    @TargetApi(Build.VERSION_CODES.CUPCAKE)
    public static String getSDAvailableSize() {
        File path = Environment.getExternalStorageDirectory();
        StatFs statFs = new StatFs(path.getPath());
        long blockSize = statFs.getBlockSize();
        long availableBlocks = statFs.getAvailableBlocks();
        return Formatter.formatFileSize(ModelManager.getContext(), blockSize * availableBlocks);
    }

    //Total size of Rom
    @TargetApi(Build.VERSION_CODES.CUPCAKE)
    public static String getRomTotalSize() {
        File path = Environment.getDataDirectory();
        StatFs statFs = new StatFs(path.getPath());
        long blockSize = statFs.getBlockSize();
        long totalBlocks = statFs.getBlockCount();
        return Formatter.formatFileSize(ModelManager.getContext(), blockSize * totalBlocks);
    }

    //Available size of Rom
    @TargetApi(Build.VERSION_CODES.CUPCAKE)
    public static String getRomAvailableSize() {
        File path = Environment.getDataDirectory();
        StatFs statFs = new StatFs(path.getPath());
        long blockSize = statFs.getBlockSize();
        long availableBlocks = statFs.getAvailableBlocks();
        return Formatter.formatFileSize(ModelManager.getContext(), blockSize * availableBlocks);
    }
//
//    public static void copyFile(InputStream in, OutputStream out)
//            throws IOException {
//        byte[] buf = new byte[COPY_FILE_BUFFER_LENGTH];
//        int l = 0;
//        while ((l = in.read(buf)) > 0) {
//            out.write(buf, 0, l);
//        }
//        out.flush();
//    }


    public static boolean isSdcardEnable() {
        return Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState());
    }

    private static String getPathInInternalStorage(String relativePath) {
        String internalRootPath = MyApplication.getAppContext().getFilesDir().getAbsolutePath();
        return String.format("%s%s%s", internalRootPath, File.separator, relativePath);
    }

    private static File getInternalStorageRootPath() {
        String internalRootPath = MyApplication.getAppContext().getFilesDir().getAbsolutePath();
        return new File(internalRootPath);
    }
    private static File getDirFromInternalStorage(String subDirName) {
        boolean success = false;
        String pathInInternalStorage = getPathInInternalStorage(subDirName);
        File internalFile = new File(pathInInternalStorage);
        if (!internalFile.exists() || !internalFile.isDirectory()) {
            if (internalFile.exists() && !internalFile.isDirectory()) {
                internalFile.delete();
            }
            success = internalFile.mkdirs();
        } else {
            success = true;
        }
        Log.d(TAG, "use internal storage for " + subDirName + ", success = " + success);
        return success ? internalFile : getInternalStorageRootPath();
    }

    public static File getDirectory(String dirName) {
        File dir = getDirectory(dirName, true);
        if (!FileUtils.isSdcardEnable() || dir == null) {
            dir = getDirFromInternalStorage(dirName);
        }
        return dir;
    }

    public static File getDirectory(String dir, boolean createDir) {
        if (dir == null) {
            return null;
        }
        String externalStorageState = null;
        try {
            externalStorageState = Environment.getExternalStorageState();
        } catch (Exception e) {
            Log.i(TAG,e.getMessage());
            return null;
        }

        if (externalStorageState != null
                && externalStorageState.equals(Environment.MEDIA_MOUNTED)) {
            File sdcard = Environment.getExternalStorageDirectory();
            File touchpal = new File(sdcard, TOUCHPAL_CONTACTS_DIR);
            if (!touchpal.isDirectory()) {
                touchpal.delete();
            }
            if (!touchpal.exists()) {
                if (createDir) {
                    deleteTmpFiles(touchpal);
                    File tmp = new File(touchpal.getAbsolutePath() + System.currentTimeMillis());
                    tmp.mkdir();
                    if (!tmp.renameTo(touchpal)) {
                        tmp.delete();
                        Log.e(TAG, "getDirectory: rename to touchpal failed");
                        return null;
                    }
                } else {
                    Log.e(TAG, "getDirectory: createDir is false and touchpal is not exist, return null");
                    return null;
                }
            }
            File sub = new File(touchpal, dir);
            if (!sub.isDirectory()) {
                if (createDir) {
                    sub.delete();
                } else {
                    Log.e(TAG, "getDirectory: createDir is false and sub is not a directory, return null");
                    return null;
                }
            }
            if (!sub.exists()) {
                if (createDir) {
                    File tmp = new File(touchpal, dir + System.currentTimeMillis());
                    tmp.mkdirs();
                    if (!tmp.renameTo(sub)) {
                        tmp.delete();
                        Log.e(TAG, "getDirectory: failed to rename sub and sub is not exist, return null");
                        return null;
                    }
                } else {
                    Log.e(TAG, "getDirectory: createDir is false and sub is not exist, return null");
                    return null;
                }
            }
            return sub;
        }

        Log.e(TAG, "getDirectory failed: externalStorageState = " + externalStorageState);
        return null;

    }

    private static void deleteTmpFiles(File file) {
        if (file == null) {
            return;
        }
        final String targetFileName = file.getName();
        if (TextUtils.isEmpty(targetFileName)) {
            return;
        }
        File parentFolder = file.getParentFile();
        if (parentFolder == null) {
            return;
        }
        File[] tmpFiles = parentFolder.listFiles(new FilenameFilter() {
            @Override
            public boolean accept(File dir, String filename) {
                if (filename.startsWith(targetFileName)) {
                    if (filename.length() > targetFileName.length()) {
                        String postfix = filename.substring(targetFileName.length());
                        try {
                            Long.parseLong(postfix);
                            return true;
                        } catch (NumberFormatException e) {
                        }
                    }
                }
                return false;
            }
        });
        if (tmpFiles != null) {
            for (File deleteFile : tmpFiles) {
                deleteFile.delete();
            }
        }
    }

//    public static File getLogFile(String logFileName) {
//        File logDir = getDirectory(StorageConsts.DIR_NAME_LOG_OUTPUT);
//        String filePath = logDir.getAbsolutePath() + File.separator + logFileName;
//        return new File(filePath);
//    }

}

