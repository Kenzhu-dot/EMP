package com.situ.emsvue.service.impl;

import com.situ.emsvue.mapper.HomeMapper;
import com.situ.emsvue.pojo.VO.MessageVO;
import com.situ.emsvue.pojo.entity.Message;
import com.situ.emsvue.service.IHomeService;
import com.situ.emsvue.util.JwtUtil;
import com.situ.emsvue.util.ThreadLocalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class HomeServiceImpl implements IHomeService {

    @Autowired
    private HomeMapper homeMapper;

    @Override
    public List<MessageVO> getMessageList(Integer emId, Integer messageStatus) {
        System.out.println(emId);
            return homeMapper.getMessageList(emId,messageStatus);
    }

    @Override
    public void sendMessage(Message message) {
        Map<String, Object> map = ThreadLocalUtil.get();
        Integer emId = (Integer) map.get("id");
        message.setEmId(emId);
        homeMapper.sendMessage(message);
    }
}
