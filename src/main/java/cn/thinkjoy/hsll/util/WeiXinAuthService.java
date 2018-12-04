package cn.thinkjoy.hsll.util;

import cn.thinkjoy.hsll.bean.AccessToken;
import cn.thinkjoy.hsll.bean.SnsapiUserinfo;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * Created by warden on 17/7/24.
 */
public class WeiXinAuthService {

    private static final Logger logger = LoggerFactory.getLogger(WeiXinAuthService.class);

    private static String accessTokenKey = "access_token_key";

    public static SnsapiUserinfo getUserinfoFromWeiChrt(String code){
        RedisUtil redisUtil = new RedisUtil();
        AccessToken accesstoken = null;
//        if(redisUtil.get(accessTokenKey) != null){
//            accesstoken =(AccessToken) redisUtil.get(accessTokenKey);
//        }else{
            accesstoken = getAccessToken(code);
//            redisUtil.set(accessTokenKey,accesstoken,7000l);
//        }
        return getUserInfo(accesstoken);
    }


    /**
     * 根据code获取微信accesstoken
     *
     * @param code 微信auth认证通过以后的code
     * @return
     */
    private static AccessToken getAccessToken(String code) {
        String ACCESS_TOKEN_SERVICE = "https://api.weixin.qq.com/sns/oauth2/access_token?appid="
                + WeiXinUtil.getAppid() + "&secret="
                + WeiXinUtil.getAppsecret() + "&code=" + code
                + "&grant_type=authorization_code";
        String jsonresult = doPost(ACCESS_TOKEN_SERVICE);

        AccessToken accesstoken = (AccessToken) JsonUtil.tranjsonStrToObject(jsonresult, AccessToken.class);
        return accesstoken;
    }


    /**
     * 根据access token 获取为细腻用户信息
     *
     * @param accessToken
     * @return
     */
    private static SnsapiUserinfo getUserInfo(AccessToken accessToken){
        String ACCESS_USER_INFO = "https://api.weixin.qq.com/sns/userinfo?access_token="+accessToken.getAccess_token()+"&openid="+accessToken.getOpenid()+"&lang=zh_CN";
        String jsonresult = doPost(ACCESS_USER_INFO);
        return (SnsapiUserinfo) JsonUtil.tranjsonStrToObject(jsonresult, SnsapiUserinfo.class);
    }




    private static String doPost(String url) {
        String response = null;
        HttpClient client = new HttpClient();
        HttpMethod method = new PostMethod(url);
        try {
            client.executeMethod(method);
            if (method.getStatusCode() == HttpStatus.SC_OK) {
                response = method.getResponseBodyAsString();
            }
            if(StringUtils.isNotBlank(response))
                response = new String(response.getBytes("iso-8859-1"),"utf-8");
        } catch (IOException e) {
            logger.error("发起POST请求异常，请求地址："+url+"，异常信息："+e);
        } finally {
            method.releaseConnection();
        }

        return response;
    }
}
