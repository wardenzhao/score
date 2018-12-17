package cn.thinkjoy.hsll.util;


import com.alibaba.fastjson.JSONObject;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.IOException;
import java.util.List;

/**
 * json工具类
 * @author yonson
 */
public class JsonUtil {
	
	/**
	 * 对像转化为json字符串
	 * @param obj
	 * @return
	 */
	public static String tranObjectToJsonStr(Object obj){
		
		return JSONObject.toJSONString(obj);
		
	}
	
	/**
	 * json字符串转化为对像
	 * @param jsonStr
	 * @return 
	 * @return
	 */
	public static <T extends Object> T tranjsonStrToObject(String jsonStr,Class<T> clazz){
		
		return (T)JSONObject.parseObject(jsonStr,clazz);
		
	}

	public static JsonNode objectToJsonNode(Object obj){
		try {
			ObjectMapper objectMapper = new ObjectMapper();
			String objJson=objectMapper.writeValueAsString(obj);
			JsonNode jsonNode = objectMapper.readTree(objJson);
			return jsonNode;
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	//对象转换为List集合
	public static <T extends Object> List<T> strToList(String str, Class<T> clazz) {
		List<T> list = JSONObject.parseArray(str, clazz);
		return list;
	}

}
