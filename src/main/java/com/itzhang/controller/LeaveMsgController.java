package com.itzhang.controller;

import com.itzhang.entity.LeaveMsg;
import com.itzhang.entity.MyQuery;
import com.itzhang.entity.R;
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

    @ApiOperation(value = "获取所有留言", notes = "若要获取所有留言，则不需要传content")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "content", value = "内容关键词", paramType = "string"),
            @ApiImplicitParam(name = "pageNum", value = "当前页数", paramType = "int"),
            @ApiImplicitParam(name = "pageSize", value = "每页信息数", paramType = "int")
    })
    @GetMapping("/msgs")
    public R getMsgs(@RequestParam(required = false) String content, @RequestParam Integer pageNum, @RequestParam Integer pageSize) {
        List<LeaveMsg> msgs = leaveMsgService.getMsgs(new MyQuery(content, (pageNum-1)*pageSize, pageSize));
        return new R(200, "获取成功！", msgs);
    }

    @ApiOperation(value = "添加留言")
    @ApiImplicitParam(name = "content", value = "内容", paramType = "string")
    @PostMapping("/msg")
    public R addMsg(@RequestParam String content) {
        LeaveMsg msg = new LeaveMsg(null, content, null);
        leaveMsgService.insertMsg(msg);
        return new R(200, "添加成功！", msg);
    }

    @ApiOperation(value = "删除留言", notes = "需要权限 msg:delete")
    @ApiImplicitParam(name = "msg_id", value = "留言ID", paramType = "string")
    @RequiresPermissions("msg:delete")
    @DeleteMapping("/msg/{msg_id}")
    public R deleteMsg(@PathVariable String msg_id) {
        leaveMsgService.deleteMsg(msg_id);
        return new R(200, "删除成功！", null);
    }
}
