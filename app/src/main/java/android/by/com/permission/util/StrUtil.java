package android.by.com.permission.util;

import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.style.CharacterStyle;
import android.text.style.UnderlineSpan;


import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class StrUtil {
    public final static String NULL = "null";
    public final static String TIMESTAMP = "timestamp";
    public final static String EVENT_ID = "id";
    public final static String EVENT_VALUE = "value";
    public final static String EVENT_PATH = "path";
    
	public static String null2epty(String in) {
		return (in == null || NULL.equals(in)) ? "" : in;
	}
	
	public static boolean isNumeric(String str) {
        for (int i = 0; i < str.length(); i++) {
            if (!Character.isDigit(str.charAt(i))) {
                return false;
            }
        }
        return true;
    }
	
	public static SpannableStringBuilder getUnderlineStr(String text) {
	    if(TextUtils.isEmpty(text))
		return null;
	    
	    SpannableStringBuilder spannable = new SpannableStringBuilder(text);
	    CharacterStyle span = new UnderlineSpan();
	    spannable.setSpan(span, 0, text.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
	    
	    return spannable;
	}
	
	public static boolean equals(String str, int value) {
	    if (str == null) {
	        return false;
	    }
	    
	    try {
	        return Integer.valueOf(str).intValue() == value;
	    } catch (NumberFormatException ex) {
	        return false;
	    }
	}
	
	public static int parseInt(String str, int defaultValue) {
	    if (str == null) {
	        return defaultValue;
	    }
	    
	    try {
            return Integer.valueOf(str).intValue();
        } catch (NumberFormatException ex) {
            return defaultValue;
        }
	}
	
   public static long parseLong(String str, long defaultValue) {
        if (str == null) {
            return defaultValue;
        }
        
        try {
            return Long.valueOf(str).longValue();
        } catch (NumberFormatException ex) {
            return defaultValue;
        }
    }
   
   public static boolean nullOrEmpty(String str) {
       return str == null || str.length() == 0;
   }
   
   /**
    * If the two string are actually "equal". Null or empty strings treated as equal.
    * @param str1
    * @param str2
    * @return
    */
   public static boolean actualEqual(String str1, String str2) {
       boolean e1 = nullOrEmpty(str1);
       boolean e2 = nullOrEmpty(str2);
       
       if (e1 && e2) {
           return true;
       }
       
       if ((e1 && !e2) || (!e1 && e2)) {
           return false;
       }
       
       return str1.equals(str2);
   }

    public static String convertStringToJson(String path, String eventId) {
        Map<String,Object > map1 = new HashMap<String, Object>();
        Map<String, Object> map2 = new HashMap<String,Object>();
        map2.put(EVENT_ID, eventId);
        map2.put(TIMESTAMP, System.currentTimeMillis());
        JSONObject json1 = new JSONObject(map2);
        String json1String =  json1.toString();
        map1.put(EVENT_VALUE, json1String);
        map1.put(EVENT_PATH,path);
        JSONObject json = new JSONObject(map1);
        String result = json.toString();
        return  result;
    }

    public static boolean containsOnlyAscii(String str) {
        if (str == null)
            return true;
        for (int i = 0; i < str.length(); i++) {
            int c = str.charAt(i);
            if (c >= 128)
                return false;
        }
        return true;
    }

}
