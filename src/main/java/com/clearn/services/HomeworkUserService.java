package com.clearn.services;

import com.clearn.bean.HomeworkAndUser;
import com.clearn.bean.ScoreSearchResult;
import com.clearn.dao.HomeworkUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @Author Administrator
 * @Date 2018.22:04
 */
@Service
public class HomeworkUserService implements IHomeworkUserService{

    @Autowired
    private HomeworkUserRepository homeworkUserRepository;

    @Override
    public List<HomeworkAndUser> findAll(Specification<HomeworkAndUser> spec) {
        return homeworkUserRepository.findAll(spec);
    }

    @Override
    public void saveHomeworkAndUser(HomeworkAndUser homeworkAndUser) {
        homeworkUserRepository.save(homeworkAndUser);
    }

    @Override
    public HomeworkAndUser getHomeworkAndUserByUserIdAndHomeworkId(Long userId, Long homeworkId) {
        return homeworkUserRepository.findHomeworkAndUserByStudentIdAndHomeworkId(userId,homeworkId);
    }

    @Override
    public List<HomeworkAndUser> findAll() {
        return homeworkUserRepository.findAll();
    }

    @Override
    public List<HomeworkAndUser> findHomeworkAndUserByTeacherId(Long userId) {
        return homeworkUserRepository.findHomeworkAndUserByTeacherId(userId);
    }

    @Override
    public HomeworkAndUser getHomeworkAndUserById(long l) {
        return homeworkUserRepository.findOne(l);
    }

    @Override
    public List<ScoreSearchResult> getScoreList(String subjectName) {
        return homeworkUserRepository.getScoreList(subjectName);
    }
}

