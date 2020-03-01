package com.clearn.bean;

/**
 * @Author Administrator
 * @Date 2018.22:38
 *
 *
 */
public class HomeworkDto {
    private Long homeworkAndUserId;
    private Long homeworkId;       // 作业ID
    private Long studentId;         // 学生ID
    private String studentName;     // 学生名称
    private Long teacherId;         // 老师ID
    private String teacherName;     // 老师名称
    private int status;             // 作业状态：1-未发布，2-已发布
    private String homeworkType;    // 作业类型：选择、简单、判断
    private String content;         // 选项
    private String title;           // 题目
    private String subject;         // 学科 ： 英文、数学、语文
    private String answer;          // 答案；


    public Long getHomeworkAndUserId() {
        return homeworkAndUserId;
    }

    public void setHomeworkAndUserId(Long homeworkAndUserId) {
        this.homeworkAndUserId = homeworkAndUserId;
    }

    public Long getHomeworkId() {
        return homeworkId;
    }

    public void setHomeworkId(Long homeworkId) {
        this.homeworkId = homeworkId;
    }

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
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

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getHomeworkType() {
        return homeworkType;
    }

    public void setHomeworkType(String homeworkType) {
        this.homeworkType = homeworkType;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }
}
