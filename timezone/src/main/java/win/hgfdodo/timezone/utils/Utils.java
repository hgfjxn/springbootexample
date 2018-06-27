package win.hgfdodo.timezone.utils;

import java.util.Properties;

public class Utils {
    private final static String USER_TIMEZONE = "user.timezone";
    private final static Properties system = System.getProperties();
    public static String jvmTimezone(){
        if(system.contains(USER_TIMEZONE)){
            return system.getProperty(USER_TIMEZONE);
        }else {
            return null;
        }
    }
    public static void setUserTimezone(String timezone){
        system.setProperty(USER_TIMEZONE, timezone);
    }
}
