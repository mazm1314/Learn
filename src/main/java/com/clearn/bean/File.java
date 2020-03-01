package com.clearn.bean;

import javax.persistence.*;

/**
 * @Author Administrator
 * @Date 2018.21:35
 * @Description  文件
 */
@Entity
@Table(name = "file")
public class File {

    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private Long fileId;
    private Long userId;
    private String fileName;
    private String filePath;

    public Long getFileId() {
        return fileId;
    }

    public void setFileId(Long fileId) {
        this.fileId = fileId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }
}
