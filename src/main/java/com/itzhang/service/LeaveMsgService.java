package com.itzhang.service;

import com.itzhang.entity.LeaveMsg;

import java.util.List;

public interface LeaveMsgService {
    List<LeaveMsg> getMsgs(Integer pageNum, Integer pageSize);
    void insertMsg(LeaveMsg msg);
    void deleteMsg(String msgId);
}
