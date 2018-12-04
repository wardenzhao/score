package cn.thinkjoy.hsll.util;

/**
 * Created by warden on 17/7/24.
 */
public class WeiXinUtil {

    private static final String appid = "wx1bed28867ce8a1ad";
    private static final String appsecret = "ff13bdd9c73308bc7495067ca9b58106";
    private static final String domain="http://172.16.222.194:8080/hsll";

    public static String getAppid() {
        return appid;
    }

    public static String getAppsecret() {
        return appsecret;
    }

    public static String getDomain() {
        return domain;
    }
}
