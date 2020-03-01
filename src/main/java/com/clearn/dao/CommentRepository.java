package com.clearn.dao;

import com.clearn.bean.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * @Author Administrator
 * @Date 2018.21:00
 * @Description comment
 */
public interface CommentRepository extends JpaRepository<Comment,Long>,JpaSpecificationExecutor<Comment> {


}
