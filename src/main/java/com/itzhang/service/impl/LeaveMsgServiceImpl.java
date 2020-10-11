package com.itzhang.service.impl;

import com.itzhang.entity.MyQuery;
import com.itzhang.mapper.LeaveMsgMapper;
import com.itzhang.entity.LeaveMsg;
import com.itzhang.service.LeaveMsgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service("leaveMsgService")
public class LeaveMsgServiceImpl implements LeaveMsgService {

    @Autowired
    LeaveMsgMapper leaveMsgMapper;

    @Override
    public List<LeaveMsg> getMsgs(MyQuery query) {
        return leaveMsgMapper.getMsgs(query);
    }

    @Override
    public void insertMsg(LeaveMsg msg) {
        leaveMsgMapper.insertMsg(msg);
    }

    @Override
    public void deleteMsg(String msgId) {
        leaveMsgMapper.deleteMsgByMsgId(msgId);
    }
}
