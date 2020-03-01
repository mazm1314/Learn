package com.clearn.services;

import com.clearn.bean.Homework;
import com.clearn.dao.HomeworkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author Administrator
 * @Date 2018.17:12
 */
@Service
public class HomeworkService implements IHomeworkService{

    @Autowired
    private HomeworkRepository homeworkRepository;


    @Override
    public Page<Homework> findAll(Specification<Homework> spec, Pageable pageable) {
        return homeworkRepository.findAll(spec,pageable);
    }

    @Override
    public List<Homework> findAll(Specification<Homework> spec) {
        return homeworkRepository.findAll(spec);
    }

    // 保存作业；
    @Override
    public void saveHomework(Homework homework) {
        homeworkRepository.save(homework);
    }

    /**
     * 获得作业根据作业ID
     * @param l
     * @return
     */
    @Override
    public Homework getHomeworkById(long l) {
        return homeworkRepository.findOne(l);
    }

    @Override
    public void updateHomework(Homework homework) {
        homeworkRepository.save(homework);
    }

    @Override
    public void deleteHomeworkById(long l) {
        homeworkRepository.delete(l);
    }
}
