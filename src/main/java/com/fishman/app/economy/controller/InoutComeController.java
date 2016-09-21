package com.fishman.app.economy.controller;

import com.fishman.app.economy.common.redis.RedisClientTemplate;
import com.fishman.app.economy.model.InoutCome;
import com.fishman.app.economy.service.InoutComeService;
import com.fishman.app.economy.service.UserService;
import com.fishman.app.economy.util.ConstantUtil;
import com.fishman.app.economy.util.DateStyle;
import com.fishman.app.economy.util.DateUtil;
import com.fishman.app.economy.util.RespCodeUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by hema on 16/9/21.
 */
@RestController
@RequestMapping(value = "/inout")
public class InoutComeController {

    @Autowired
    InoutComeService inoutComeService;
    @Autowired
    UserService userService;
    @Autowired
    RedisClientTemplate redis;
    /**
     * 保存收入或支出记录
     * @param type
     * @param money
     * @param category
     * @param startDate
     * @param endDate
     */
    @RequestMapping(value = "/{type}/save",method = RequestMethod.POST)
    public String saveIncome(@PathVariable(value="type") String type,
                           @RequestParam(value="accessToken",required=true) String accessToken,
                           @RequestParam(value="money",required=true) String money,
                           @RequestParam(value="category",required=true) String category,
                           @RequestParam(value="startDate",required=false) String startDate,
                           @RequestParam(value="endDate",required=false) String endDate){
        String userId = redis.get("accessToken-"+accessToken);
        if(userId!=null){
            InoutCome inoutCome = new InoutCome();
            inoutCome.setUid(Long.parseLong(userId));
            inoutCome.setType(Integer.parseInt(type));
            inoutCome.setCreTime(new Date());
            inoutCome.setMoney(Double.parseDouble(money));
            inoutCome.setUpdateTime(new Date());
            inoutCome.setCategoryId(Integer.parseInt(category));
            if(!StringUtils.isEmpty(startDate)){
                inoutCome.setStartDate(DateUtil.StringToDate(startDate, DateStyle.YYYY_MM_DD_HH_MM_EN));
            }
            if(!StringUtils.isEmpty(endDate)){
                inoutCome.setEndDate(DateUtil.StringToDate(endDate,DateStyle.YYYY_MM_DD_HH_MM_EN));
            }
            inoutComeService.saveIncome(inoutCome);
            return RespCodeUtil.success();
        }
        return RespCodeUtil.error(RespCodeUtil.e10000);
    }

    @RequestMapping(value = "/{type}/list",method = RequestMethod.POST)
    public String getInoutComeList(@PathVariable(value="type") String type){
        if(ConstantUtil.InoutComeType.Income==Integer.parseInt(type)){
            List<InoutCome> inoutComeList = inoutComeService.getInoutComeList(Integer.parseInt(type));
            Map<String,List<InoutCome>>  data = new HashMap<String, List<InoutCome>>();
            data.put("inoutComeList",inoutComeList);
            return RespCodeUtil.success(data);
        }
        return RespCodeUtil.error(RespCodeUtil.e10000);
    }
}
