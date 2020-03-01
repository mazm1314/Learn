package com.clearn.services;

import com.clearn.bean.Comment;
import com.clearn.bean.File;
import com.clearn.dao.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author Administrator
 * @Date 2018.21:02
 */
@Service
public class CommentServiceImpl implements ICommentService{

    @Autowired
    private CommentRepository commentRepository;


    @Override
    public Page<Comment> findAll(Specification<Comment> spec, Pageable pageable) {
        return commentRepository.findAll(spec,pageable);
    }

    @Override
    public List<Comment> findAll(Specification<Comment> spec) {
        return commentRepository.findAll(spec);
    }

    /**
     * 保存评论信息；
     * @param comment
     */
    @Override
    public void saveComment(Comment comment) {
        commentRepository.save(comment);
    }

    /**
     * 删除评论；
     * @param comment
     */
    @Override
    public void deleteComment(Comment comment) {
        commentRepository.delete(comment);
    }

    @Override
    public Comment findById(long l) {
        return commentRepository.findOne(l);
    }

    @Override
    public void updateComment(Comment comment) {
        commentRepository.save(comment);
    }
}
