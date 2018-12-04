package cn.thinkjoy.hsll.util;

import com.alibaba.dubbo.common.json.JSON;
import com.alibaba.dubbo.common.json.JSONArray;
import com.alibaba.dubbo.common.json.JSONObject;
import com.alibaba.dubbo.common.json.ParseException;

/**
 * Created by wpliu on 17/8/19.
 */
public class TestClass {

    public static void main(String[] args) throws ParseException {
        String json="[{\"id\":123,\"name\":\"张三那\"},{\"id\":456,\"name\":\"我是谁\"}]";
        try {
         JSONArray jsonmap = (JSONArray) JSON.parse(json);
            for (int i=0;i<jsonmap.length();i++){
                JSONObject map= (JSONObject) jsonmap.get(i);
                System.out.println(map);
            }

        } catch (ParseException e) {
            e.printStackTrace();
        }

    }
}
