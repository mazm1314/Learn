package com.clearn.services;

import com.clearn.bean.Comment;
import com.clearn.bean.File;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

/**
 * @Author Administrator
 * @Date 2018.21:01
 */
public interface ICommentService {
    Page<Comment> findAll(Specification<Comment> spec, Pageable pageable);

    List<Comment> findAll(Specification<Comment> spec);

    void saveComment(Comment comment);

    void deleteComment(Comment comment);

    Comment findById(long l);

    void updateComment(Comment comment);

    
}
