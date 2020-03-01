package com.clearn.services;

import com.clearn.bean.Message;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

/**
 * @Author Administrator
 * @Date 2018.22:47
 */
public interface IMessageService {
    Page<Message> findAll(Specification<Message> spec, Pageable pageable);

    List<Message> findAll(Specification<Message> spec);

    void deleteMessage(long l);

    void saveMessage(Message message);

    List<Message> finAll();
}
