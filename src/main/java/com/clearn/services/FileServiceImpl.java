package com.clearn.services;

import com.clearn.bean.File;
import com.clearn.dao.FileRepository;
import org.apache.catalina.webresources.FileResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author Administrator
 * @Date 2018.22:48
 */
@Service
public class FileServiceImpl implements IFileService{

    @Autowired
    private FileRepository fileRepository;

    /**
     * 获得所有文件；
     * @return
     */
    public List<File> findAll(){
        return fileRepository.findAll();
    }

    /**
     * 获取分页文件
     * @param pageable
     * @return
     */
    public Page<File> findPage(Pageable pageable){
        return fileRepository.findAll(pageable);
    }

    /**
     * 保存文件
     * @param fileBean
     */
    @Override
    public void saveFile(File fileBean) {
        fileRepository.save(fileBean);
    }

    /**
     * 删除文件
     * @param fileId
     */
    @Override
    public void deleteFile(Long fileId) {
        fileRepository.delete(fileRepository.getOne(fileId));
    }

    @Override
    public File findOne(long fileId) {
        return fileRepository.getOne(fileId);
    }
}
