package com.clearn.dao;

import com.clearn.bean.HomeworkAndUser;
import com.clearn.bean.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @Author Administrator
 * @Date 2018.22:46
 */
public interface MessageRepository extends JpaRepository<Message,Long>,JpaSpecificationExecutor<Message> {
}
