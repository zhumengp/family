package org.zhump.familybill.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import lombok.extern.log4j.Log4j2;

import java.util.List;

/**
* Title:JackJsonUtils
* Description: json 解析工具类
* @author zhump
* @version 1.0.0
* @date 2021/6/8 23:35
*/
@Log4j2
public class JackJsonUtils {



    public static class JackJsonHolder{
        public static final ObjectMapper instance = new ObjectMapper();
    }

    public static ObjectMapper getInstance(){
        return JackJsonHolder.instance;
    }


    public static String toJson(Object obj){
        try {
            return getInstance().writeValueAsString(obj);
        }catch (Exception e){
            log.info("json this is a error，Object:[{}]",obj,e);
        }
        return null;
    }

    /**
     * 解析字符串为对象
     * @param json
     * @param value
     * @param <T>
     * @return
     */
    public static <T> T parse(String json,Class<T> value){
        try {
            return getInstance().readValue(json,value);
        }catch (JsonProcessingException e){
            log.info("json this is a error，params:[{}],Object:[{}]",json,value,e);
        }
        return null;
    }

    /**
    * Title:
    * Description:集合转换
    * @author zhump
    * @version 1.0.0
    * @date 2021/6/8 23:55
    */
    public static <T> List<T> parseArray(String json, Class<T> value){

        try {
            JavaType javaType = getInstance().getTypeFactory().constructParametricType(List.class, value);
            return getInstance().readValue(json,javaType);
        }catch (JsonProcessingException e){
            log.info("json this is a error，params:[{}],Object:[{}]",json,value,e);
        }
        return null;
    }
}
