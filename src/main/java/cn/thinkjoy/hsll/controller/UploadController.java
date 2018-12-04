package cn.thinkjoy.hsll.controller;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by wpliu on 17/8/21.
 */

@Scope("prototype")
@Controller("uploadController")
@RequestMapping(value = "/file")
public class UploadController {



    String upAddress="http://192.168.0.102:8282/upload/";

    @RequestMapping(value = "/upload")
    @ResponseBody
    public String upload(@RequestParam(value = "file", required = false) MultipartFile file, HttpServletRequest request) {

        System.out.println("开始");
        String path = request.getSession().getServletContext().getRealPath("upload");
        String fileName = file.getOriginalFilename();
//        String fileName = new Date().getTime()+".jpg";
        int l=fileName.lastIndexOf(".");
        String fileTail=fileName.substring(l);
        String newFileName=System.currentTimeMillis()+fileTail;
        System.out.println(path);
        File targetFile = new File(path, newFileName);
        if(!targetFile.exists()){
            targetFile.mkdirs();
        }

        //保存
        try {
            file.transferTo(targetFile);
        } catch (Exception e) {
            e.printStackTrace();
        }


        return upAddress+newFileName;
    }

    @RequestMapping(value = "/index")
    public ModelAndView index(HttpServletRequest request) {
        Map<String,String> aaa = new HashMap<>();
        aaa.put("aaa","哈哈哈");
        return new ModelAndView("upload",aaa);
    }
}
