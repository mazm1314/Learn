//package com.clearn.bean;
//
//import javax.persistence.*;
//
///**
// * @Author Administrator
// * @Date 2018.23:20
// * @Description 考试-学生 关系表
// */
//@Entity
//@Table(name = "exam_user")
//public class ExamAndUser {
//
//    @javax.persistence.Id
//    @GeneratedValue(strategy= GenerationType.IDENTITY)
//    private Long Id;
//
//    private Long examId;    // 考试ID
//
//    private Long studentId; // 学生ID
//    private Long teacherId; // 老师ID
//
//    private String studentName; // 学生名称
//    private String teacherName; // 老师名称
//
//    private String answer;  // 答案；
//
//    private String mark;    // 成绩
//
//
//    public Long getId() {
//        return Id;
//    }
//
//    public void setId(Long id) {
//        Id = id;
//    }
//
//    public Long getExamId() {
//        return examId;
//    }
//
//    public void setExamId(Long examId) {
//        this.examId = examId;
//    }
//
//    public Long getStudentId() {
//        return studentId;
//    }
//
//    public void setStudentId(Long studentId) {
//        this.studentId = studentId;
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
//    public String getStudentName() {
//        return studentName;
//    }
//
//    public void setStudentName(String studentName) {
//        this.studentName = studentName;
//    }
//
//    public String getTeacherName() {
//        return teacherName;
//    }
//
//    public void setTeacherName(String teacherName) {
//        this.teacherName = teacherName;
//    }
//
//    public String getAnswer() {
//        return answer;
//    }
//
//    public void setAnswer(String answer) {
//        this.answer = answer;
//    }
//
//    public String getMark() {
//        return mark;
//    }
//
//    public void setMark(String mark) {
//        this.mark = mark;
//    }
//}
