package cn.thinkjoy.hsll.controller;

/**
 * Created by wpliu on 16/3/2.
 */

import cn.thinkjoy.hsll.bean.User;
import cn.thinkjoy.hsll.service.UserService;
import cn.thinkjoy.hsll.util.PinYinUtil;
import cn.thinkjoy.hsll.util.RedisUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 点击拨号控制类
 */
@Scope("prototype")
@Controller("userController")
@RequestMapping(value = "/user")
public class UserController extends BaseController{

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;


    @RequestMapping(value = "/list")
    public ModelAndView userList(HttpServletRequest request){
        if(getLoginUser(request) == null){
            return new ModelAndView("redirect:/");
        }
        int pageSize = 10;
        int page = 1;
        String username = request.getParameter("username");
        String name = request.getParameter("name");
        List<User> listUser = userService.getUserList(username, name, pageSize, page);
        Map<String,Object> map = new HashMap<String,Object>();

        map.put("userList",listUser);
        map.put("username",username);
        map.put("name",name);
        return new ModelAndView("admin_index",map);
    }

    @RequestMapping(value = "/add",method = RequestMethod.GET)
    public ModelAndView add(HttpServletRequest request){
        if(getLoginUser(request) == null){
            return new ModelAndView("redirect:/");
        }
        return new ModelAndView("user_add");
    }

    @RequestMapping(value = "/edit",method = RequestMethod.GET)
    public ModelAndView editGet(HttpServletRequest request,long id){
        if(getLoginUser(request) == null){
            return new ModelAndView("redirect:/");
        }
        User user = userService.findOneById(id);
        Map<String,Object> map = new HashMap<>();
        map.put("user",user);
        return new ModelAndView("user_edit",map);
    }

    @ResponseBody
    @RequestMapping(value = "/edit",method = RequestMethod.POST)
    public Map<String,String> edit(HttpServletRequest request,@ModelAttribute User user){
        Map<String,String> map = new HashMap<>();
        if(getLoginUser(request) == null){
            map.put("status","0");
            map.put("msg","登录失效，请重新登录！");
            return map;
        }
        try{
            if(user.getId() >0 ){
                String username = PinYinUtil.getPingYin(user.getName());
                user.setUsername(username);
                userService.updateData(user);
            }else{
                String username = PinYinUtil.getPingYin(user.getName());
                user.setUsercode(createStuNo());
                user.setUsername(username);
                user.setType(2);
                user.setStatus(1);
                user.setCreateTime(new Date());
                userService.insertData(user);
            }
            map.put("status","1");
        }catch (Exception e){
            map.put("status","0");
            map.put("msg","请求出错！");
            e.printStackTrace();
        }
        return map;
    }

    @RequestMapping(value = "/delete",method = RequestMethod.GET)
    public ModelAndView delete(HttpServletRequest request,long id){
        if(getLoginUser(request) == null){
            return new ModelAndView("redirect:/");
        }
        userService.deleteById(id);
        return new ModelAndView("redirect:/user/list");
    }

    /**
     * 学生编号生成
     * @return
     */
    public String createStuNo(){
        StringBuffer no = new StringBuffer();
        SimpleDateFormat df = new SimpleDateFormat("yyMMdd");//设置日期格式
        no.append(df.format(new Date()));

        String key = no.toString()+"_no";
        RedisUtil redisUtil = new RedisUtil();
        Object num = redisUtil.get(key);
        if(num == null){
            no.append("0001");
            redisUtil.set(key,2,getRemainSecondsOneDay(new Date()));
        }else{
            no.append(String.format("%04d", (int) num));
            redisUtil.set(key,(int)num + 1,getRemainSecondsOneDay(new Date()));
        }
        return no.toString();
    }

    public Long getRemainSecondsOneDay(Date currentDate) {
        Calendar midnight=Calendar.getInstance();
        midnight.setTime(currentDate);
        midnight.add(midnight.DAY_OF_MONTH, 1);
        midnight.set(midnight.HOUR_OF_DAY, 0);
        midnight.set(midnight.MINUTE, 0);
        midnight.set(midnight.SECOND, 0);
        midnight.set(midnight.MILLISECOND, 0);
        Long seconds=(long)((midnight.getTime().getTime()-currentDate.getTime())/1000);
        return seconds;
    }
}
