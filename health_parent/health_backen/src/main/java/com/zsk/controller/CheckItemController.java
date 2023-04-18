package com.zsk.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.zsk.constant.MessageConstant;
import com.zsk.entity.Result;
import com.zsk.pojo.CheckItem;
import com.zsk.service.CheckItemService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 检查项后台管理
 */

@RestController
@RequestMapping("/checkitem")
public class CheckItemController {
    @Reference  //通过dubbo查找服务
    private CheckItemService checkItemService;

    //新增检查项
    @RequestMapping("/add.do")
    public Result add(@RequestBody CheckItem checkItem) {
        System.out.println("来到控制器");
        System.out.println(checkItem);
        try {
            checkItemService.add(checkItem);
            System.out.println("已执行添加操作");
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, MessageConstant.ADD_CHECKITEM_FAIL);
        }
        System.out.println("已完成控制器代码，返回");
        return new Result(true,MessageConstant.ADD_CHECKITEM_SUCCESS);
    }
}
