package com.fishman.app.economy.util;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by hema on 16/9/21.
 */
public class RespCodeUtil {

    private static RespCodeProperties pro = RespCodeProperties.getInstance();

    public static final String e00000 = "00000";   // 系统错误
    public static final String e10000 = "10000";   // 系统错误

    //    用户
    public static final String e20000 = "20000";   // 用户名不正确
    public static final String e20001 = "20001";   // 密码错误
    public static final String e20002 = "20002";   // 用户名已存在

    public static String error(String errorCode){
        Map<String, Object> ret = new HashMap<String, Object>();
        return error(ret,errorCode);
    }

    public static String error(Map<String, Object> map, String errorCode) {
        map.put("code", errorCode);
        map.put("status", "error");
        map.put("msg", pro.getProperties(errorCode));
        return JsonUtil.object2String(map);
    }

    public static String success() {
        Map<String, Object> ret = new HashMap<String, Object>();
        ret.put("code", e00000);
        ret.put("status", "success");
        ret.put("msg", pro.getProperties(e00000));
        return JsonUtil.object2String(ret);
    }
    public static<T> String success(Map<String,T> data) {
        Map<String, Object> ret = new HashMap<String, Object>();
        ret.put("code", e00000);
        ret.put("status", "success");
        ret.put("msg", pro.getProperties(e00000));
        ret.put("data",data);
        return JsonUtil.object2String(ret);
    }

}
