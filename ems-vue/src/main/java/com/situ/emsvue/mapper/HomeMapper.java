package com.situ.emsvue.mapper;

import com.situ.emsvue.pojo.VO.MessageVO;
import com.situ.emsvue.pojo.entity.Message;

import java.util.List;

public interface HomeMapper {
    List<MessageVO> getMessageList(Integer emId, Integer messageStatus);

    void sendMessage(Message message);
}
