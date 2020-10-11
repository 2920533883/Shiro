package com.itzhang.service;

import com.itzhang.entity.LeaveMsg;
import com.itzhang.entity.MyQuery;

import java.util.List;

public interface LeaveMsgService {
    List<LeaveMsg> getMsgs(MyQuery query);
    void insertMsg(LeaveMsg msg);
    void deleteMsg(String msgId);
}
