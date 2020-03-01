package com.clearn.services;

import com.clearn.bean.File;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * @Author Administrator
 * @Date 2018.22:48
 */
public interface IFileService {

    public List<File> findAll();
    public Page<File> findPage(Pageable pageable);
    void saveFile(File fileBean);
    void deleteFile(Long i);
    File findOne(long l);
}
