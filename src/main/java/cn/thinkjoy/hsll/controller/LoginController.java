package cn.thinkjoy.hsll.controller;

/**
 * Created by wpliu on 16/3/2.
 */

import cn.thinkjoy.hsll.bean.User;
import cn.thinkjoy.hsll.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * 点击拨号控制类
 */
@Scope("prototype")
@Controller("loginController")
@RequestMapping(value = "/")
public class LoginController {

    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/",method = RequestMethod.GET)
    public ModelAndView clickView(HttpServletRequest request){

        return new ModelAndView("login");
    }

    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public ModelAndView login(HttpServletRequest request){

        String username = request.getParameter("username");
        String password = request.getParameter("password");

        Map<String,String> msg = new HashMap<String,String>();
        if(username == null || "".equals(username)){
            msg.put("msg","用户名不能为空。");
            return new ModelAndView("login", msg);
        }

        if(password == null || "".equals(password)){
            msg.put("msg","密码不能为空。");
            return new ModelAndView("login", msg);
        }

        User user = userService.getUserByUsername(username);
        if(user != null && user.getId()>0){
            if(user.getType() == 1){
                request.getSession().setAttribute("login_user",user);
                return new ModelAndView("redirect:/user/list");
            }else{
                request.getSession().setAttribute("login_user",user);
                return new ModelAndView("p_index");
            }
        }else{
            msg.put("msg","用户名或者密码错误。");
        }
        return new ModelAndView("login", msg);
    }


    @RequestMapping(value = "/parent/index")
    public ModelAndView parentIndex(HttpServletRequest request){
        User user = (User) request.getSession().getAttribute("login_user");
        if(user != null && user.getId()>0){


        }else{
            return new ModelAndView("login");
        }
        return new ModelAndView("p_index");
    }
}
