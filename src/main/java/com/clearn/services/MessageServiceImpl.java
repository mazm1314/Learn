package com.clearn.services;

import com.clearn.bean.Message;
import com.clearn.dao.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author Administrator
 * @Date 2018.22:47
 */
@Service
public class MessageServiceImpl implements IMessageService{

    @Autowired
    private MessageRepository messageRepository;


    @Override
    public Page<Message> findAll(Specification<Message> spec, Pageable pageable) {
        return messageRepository.findAll(spec,pageable);
    }

    @Override
    public List<Message> findAll(Specification<Message> spec) {
        return messageRepository.findAll(spec);
    }

    @Override
    public void deleteMessage(long l) {
        messageRepository.delete(l);
    }

    @Override
    public void saveMessage(Message message) {
        messageRepository.save(message);
    }

    @Override
    public List<Message> finAll() {
        return messageRepository.findAll();
    }


}
