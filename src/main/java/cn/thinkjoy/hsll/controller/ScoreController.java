package cn.thinkjoy.hsll.controller;

/**
 * Created by wpliu on 16/3/2.
 */

import cn.thinkjoy.hsll.bean.User;
import cn.thinkjoy.hsll.bean.UserScore;
import cn.thinkjoy.hsll.resp.HomeworkMsg;
import cn.thinkjoy.hsll.resp.ScoreResp;
import cn.thinkjoy.hsll.resp.TestMsg;
import cn.thinkjoy.hsll.service.UserScoreService;
import cn.thinkjoy.hsll.service.UserService;
import cn.thinkjoy.hsll.util.JsonUtil;
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
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 点击拨号控制类
 */
@Scope("prototype")
@Controller("scoreController")
@RequestMapping(value = "/score")
public class ScoreController extends BaseController{

    private static final Logger logger = LoggerFactory.getLogger(ScoreController.class);

    @Autowired
    private UserService userService;

    @Autowired
    private UserScoreService userScoreService;

    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public ModelAndView clickView(HttpServletRequest request,long userId){

        if(getLoginUser(request) == null){
            return new ModelAndView("redirect:/");
        }
        Map<String,Object> map = new HashMap<>();

        List<UserScore> scoreList = userScoreService.getByUserId(userId);

        User user = userService.findOneById(userId);

        map.put("scoreList", scoreList);
        map.put("userId",userId);
        map.put("user",user);
        return new ModelAndView("score_list",map);
    }

    @RequestMapping(value = "/add",method = RequestMethod.GET)
    public ModelAndView add(HttpServletRequest request,long userId){
        if(getLoginUser(request) == null){
            return new ModelAndView("redirect:/");
        }
        Map<String,Object> map = new HashMap<>();
        map.put("userId",userId);
        return new ModelAndView("score_add",map);
    }

    @ResponseBody
    @RequestMapping(value = "/edit",method = RequestMethod.POST)
    public Map<String,String> edit(HttpServletRequest request,@ModelAttribute UserScore userScore){
        Map<String,String> map = new HashMap<>();
        if(getLoginUser(request) == null){
            map.put("status","0");
            map.put("msg","登录失效，请重新登录！");
            return map;
        }
        try{
            if(userScore.getId()>0){
                userScoreService.updateData(userScore);
            }else{
                // 获取指定格式的时间
                SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmssSSS");

                userScore.setAddCode(df.format(new Date()));
                userScore.setCreateTime(new Date());
                userScoreService.insertData(userScore);
            }
            map.put("status","1");
        }catch (Exception e){
            map.put("status","0");
            map.put("msg","请求出错！");
            e.printStackTrace();
        }
        return map;
    }


    @RequestMapping(value = "/edit",method = RequestMethod.GET)
    public ModelAndView editGet(HttpServletRequest request,long id){
        if(getLoginUser(request) == null){
            return new ModelAndView("redirect:/");
        }
        Map<String,Object> map = new HashMap<>();
        UserScore userScore = userScoreService.findOneById(id);
        ScoreResp scoreResp = new ScoreResp();

        scoreResp.setId(userScore.getId());
        scoreResp.setWeekProgress(userScore.getWeekProgress());
        scoreResp.setTeacherComment(userScore.getTeacherComment());
        scoreResp.setParentComment(userScore.getParentComment());

        if(userScore.getTestMsg() != null){
            TestMsg testMsg = new TestMsg();
            testMsg = JsonUtil.tranjsonStrToObject(userScore.getTestMsg(),TestMsg.class);
            scoreResp.setTestMsg(testMsg);
        }

        if(userScore.getHomeworkMsg() != null){
            List<HomeworkMsg> list = JsonUtil.strToList(userScore.getHomeworkMsg(),HomeworkMsg.class);
            scoreResp.setHomeworkMsg(list);
        }

        map.put("userId",userScore.getUserId());
        map.put("score",scoreResp);

        return new ModelAndView("score_edit",map);
    }


    @RequestMapping(value = "/delete",method = RequestMethod.GET)
    public ModelAndView delete(HttpServletRequest request,long id,long userId){
        if(getLoginUser(request) == null){
            return new ModelAndView("redirect:/");
        }
        userScoreService.deleteById(id);
        return new ModelAndView("redirect:/score/list?userId="+userId);
    }


    @RequestMapping(value = "preview",method = RequestMethod.GET)
    public ModelAndView preview(HttpServletRequest request,long id){
        if(getLoginUser(request) == null){
            return new ModelAndView("redirect:/");
        }
        Map<String,Object> map = new HashMap<>();
        UserScore userScore = userScoreService.findOneById(id);
        ScoreResp scoreResp = new ScoreResp();

        scoreResp.setId(userScore.getId());
        scoreResp.setWeekProgress(userScore.getWeekProgress());
        scoreResp.setTeacherComment(userScore.getTeacherComment());
        scoreResp.setParentComment(userScore.getParentComment());

        if(userScore.getTestMsg() != null){
            TestMsg testMsg = new TestMsg();
            testMsg = JsonUtil.tranjsonStrToObject(userScore.getTestMsg(),TestMsg.class);
            scoreResp.setTestMsg(testMsg);
        }

        if(userScore.getHomeworkMsg() != null){
            List<HomeworkMsg> list = JsonUtil.strToList(userScore.getHomeworkMsg(),HomeworkMsg.class);
            scoreResp.setHomeworkMsg(list);
        }

        map.put("user",userService.findOneById(userScore.getUserId()));
        map.put("score",scoreResp);

        return new ModelAndView("p_index",map);
    }
}
