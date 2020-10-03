package com.itzhang.service;

import com.itzhang.pojo.LeaveMsg;
import io.swagger.models.auth.In;

import java.util.List;

public interface LeaveMsgService {
    List<LeaveMsg> getMsgs(Integer pageNum, Integer pageSize);
    void insertMsg(String content);
    void deleteMsg(String msgId);
}
