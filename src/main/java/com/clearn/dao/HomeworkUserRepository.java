package com.clearn.dao;

import com.clearn.bean.Comment;
import com.clearn.bean.HomeworkAndUser;
import com.clearn.bean.ScoreSearchResult;
import org.hibernate.transform.ResultTransformer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.data.repository.query.Param;

import javax.persistence.ColumnResult;
import javax.persistence.EntityResult;
import javax.persistence.QueryHint;
import javax.persistence.SqlResultSetMappings;
import java.util.List;
import java.util.Map;

/**
 * @Author Administrator
 * @Date 2018.17:14
 * @Description homework_user
 */
public interface HomeworkUserRepository extends JpaRepository<HomeworkAndUser,Long>,JpaSpecificationExecutor<HomeworkAndUser> {

    HomeworkAndUser findHomeworkAndUserByStudentIdAndHomeworkId(Long userId, Long homeworkId);

    List<HomeworkAndUser> findHomeworkAndUserByTeacherId(Long userId);

    @Query(value = "select SUM(hu.score) as totalScore,hu.student_id as studentId from homework_user as hu where hu.subject_name= :subjectName GROUP BY hu.student_id",nativeQuery = true)
    List<ScoreSearchResult> getScoreList(@Param("subjectName") String subjectName);
}
