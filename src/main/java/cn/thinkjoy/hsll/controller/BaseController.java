package cn.thinkjoy.hsll.controller;

/**
 * Created by wpliu on 16/3/2.
 */

import cn.thinkjoy.hsll.bean.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.InputStreamReader;


public class BaseController {

    private static final Logger logger = LoggerFactory.getLogger(BaseController.class);


    public String getJsonString(HttpServletRequest request){
        String jsonString = "";

        try {
            BufferedReader br = new BufferedReader(new InputStreamReader((ServletInputStream) request.getInputStream(), "UTF-8"));
            String line = null;
            StringBuilder sb = new StringBuilder();
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }
            jsonString = sb.toString();
        }catch (Exception e){
            e.printStackTrace();
            logger.error("BaseController数据解析错误");
        }
        return jsonString;
    }

    public User getLoginUser(HttpServletRequest request){
        User user = (User) request.getSession().getAttribute("login_user");
        return user;
    }



}
