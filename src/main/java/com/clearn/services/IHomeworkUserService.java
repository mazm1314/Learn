package com.clearn.services;

import com.clearn.bean.HomeworkAndUser;
import com.clearn.bean.ScoreSearchResult;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;
import java.util.Map;

/**
 * @Author Administrator
 * @Date 2018.22:04
 */
public interface IHomeworkUserService {
    List<HomeworkAndUser> findAll(Specification<HomeworkAndUser> specOfUnDealHomework);

    void saveHomeworkAndUser(HomeworkAndUser homeworkAndUser);

    HomeworkAndUser getHomeworkAndUserByUserIdAndHomeworkId(Long userId, Long homeworkId);

    List<HomeworkAndUser> findAll();

    List<HomeworkAndUser> findHomeworkAndUserByTeacherId(Long userId);

    HomeworkAndUser getHomeworkAndUserById(long l);

    List<ScoreSearchResult> getScoreList(String subjectName);
}
