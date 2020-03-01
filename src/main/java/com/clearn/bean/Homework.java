package com.clearn.bean;

import javax.persistence.*;

/**
 * @Author Administrator
 * @Date 2018.15:18
 * @Description 家庭作业
 */
@Table(name = "homework")
@Entity
public class Homework {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long Id;

    private String title;   // 作业标题
    private String content; // 作业内容

    private Long teacherId; // 老师ID
    private String teacherName; // 老师名称

    private int status; // 作业状态； 1-未发布 2-已发布

    private String subject; // 学科名称：英语/数学/语文

    private String homeworkType;    // 题目类型 : 选择/简单/判断；


    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getHomeworkType() {
        return homeworkType;
    }

    public void setHomeworkType(String homeworkType) {
        this.homeworkType = homeworkType;
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Long getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(Long teacherId) {
        this.teacherId = teacherId;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }
}
