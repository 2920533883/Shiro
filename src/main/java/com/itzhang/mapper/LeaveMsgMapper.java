package com.itzhang.mapper;

import com.itzhang.entity.LeaveMsg;
import com.itzhang.entity.MyQuery;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Queue;

@Mapper
public interface LeaveMsgMapper {
    List<LeaveMsg> getMsgs(MyQuery query);
    void insertMsg(LeaveMsg leaveMsg);
    void deleteMsgByMsgId(String msgId);
}
