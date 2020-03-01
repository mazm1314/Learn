//package com.clearn.bean;
//
//import javax.persistence.*;
//
///**
// * @Author Administrator
// * @Date 2018.23:14
// * @Description  试题
// */
//@Entity
//@Table(name = "exam")
//public class Exam {
//
//    @Id
//    @GeneratedValue(strategy= GenerationType.IDENTITY)
//    private Long id;
//
//    private Long teacherId; // 老师ID
//    private String title;   // 标题
//    private String content; // 内容
//
//    private String subject; // 学科： 英文/语文/数学...
//
//
//    public Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }
//
//    public Long getTeacherId() {
//        return teacherId;
//    }
//
//    public void setTeacherId(Long teacherId) {
//        this.teacherId = teacherId;
//    }
//
//    public String getTitle() {
//        return title;
//    }
//
//    public void setTitle(String title) {
//        this.title = title;
//    }
//
//    public String getContent() {
//        return content;
//    }
//
//    public void setContent(String content) {
//        this.content = content;
//    }
//
//    public String getSubject() {
//        return subject;
//    }
//
//    public void setSubject(String subject) {
//        this.subject = subject;
//    }
//}
