package com.itzhang.controller;

import com.itzhang.pojo.LeaveMsg;
import com.itzhang.pojo.R;
import com.itzhang.service.LeaveMsgService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "留言模块")
@RestController("/msg")
public class LeaveMsgController {
    @Autowired
    LeaveMsgService leaveMsgService;

    @ApiOperation(value = "获取所有留言", notes = "需要权限 msg:get")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNum", value = "当前页数", paramType = "int"),
            @ApiImplicitParam(name = "pageSize", value = "每页信息数", paramType = "int")
    })
    @GetMapping("/getMsgs")
    public R getMsgs(@RequestParam Integer pageNum, @RequestParam Integer pageSize){
        List<LeaveMsg> msgs = leaveMsgService.getMsgs(pageNum, pageSize);
        return new R(200, "获取成功！", msgs);
    }

    @ApiOperation(value = "添加留言", notes = "需要权限 msg:insert")
    @ApiImplicitParam(name = "content", value = "内容", paramType = "string")
    @RequiresPermissions("msg:insert")
    @PutMapping("/addMsg")
    public R addMsg(@RequestParam String content){
        leaveMsgService.insertMsg(content);
        return new R(200, "添加成功！", null);
    }

    @ApiOperation(value = "删除留言", notes = "需要权限 msg:delete")
    @ApiImplicitParam(name = "msg_id", value = "留言ID", paramType = "string")
    @RequiresPermissions("msg:delete")
    @DeleteMapping("/deleteMsg/{msg_id}")
    public R deleteMsg(@PathVariable String msg_id){
        leaveMsgService.deleteMsg(msg_id);
        return new R(200, "删除成功！", null);
    }
}
