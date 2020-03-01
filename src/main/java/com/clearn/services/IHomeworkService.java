package com.clearn.services;

import com.clearn.bean.Comment;
import com.clearn.bean.Homework;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

/**
 * @Author Administrator
 * @Date 2018.17:12
 */
public interface IHomeworkService {

    // 根据查询条件，获得分页查询结果；
    Page<Homework> findAll(Specification<Homework> spec, Pageable pageable);

    // 根据查询条件，获得查询结果；
    List<Homework> findAll(Specification<Homework> spec);

    // 保存作业；
    void saveHomework(Homework homework);

    // 根据作业ID获得作业
    Homework getHomeworkById(long l);

    // 修改作业；
    void updateHomework(Homework homework);

    // 删除作业根据作业ID；
    void deleteHomeworkById(long l);
}
