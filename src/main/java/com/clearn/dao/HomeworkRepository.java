package com.clearn.dao;

import com.clearn.bean.Homework;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @Author Administrator
 * @Date 2018.17:13
 * @Description homework
 */
public interface HomeworkRepository extends JpaRepository<Homework,Long>,JpaSpecificationExecutor<Homework> {
}
