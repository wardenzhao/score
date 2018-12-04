package cn.thinkjoy.hsll.controller;

/**
 * Created by wpliu on 16/3/2.
 */

import cn.thinkjoy.hsll.bean.Member;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * 点击拨号控制类
 */
@Scope("prototype")
@Controller("testController")
@RequestMapping(value = "/hsll")
public class TestController {

    private static final Logger logger = LoggerFactory.getLogger(TestController.class);

    @Autowired
    private MemberService memberService;

    @ResponseBody
    @RequestMapping(value = "/test",method = RequestMethod.GET)
    public String clickView(HttpServletRequest request){
        Member member = memberService.getMemberByOpenId("123456");
        return "asdsa";
    }




}
