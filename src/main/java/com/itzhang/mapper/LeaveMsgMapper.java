package com.itzhang.mapper;

import com.itzhang.entity.LeaveMsg;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface LeaveMsgMapper {
    List<LeaveMsg> getMsgs(@Param("start")Integer start, @Param("offset") Integer offset);
    void insertMsg(LeaveMsg leaveMsg);
    void deleteMsgByMsgId(String msgId);
}
