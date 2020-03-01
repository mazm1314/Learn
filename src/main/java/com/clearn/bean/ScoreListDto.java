package com.clearn.bean;

/**
 * @Author Administrator
 * @Date 2018.22:45
 * @Description 成绩列表DTO
 */
public class ScoreListDto {

    private String subject; //课程
    private String studentName; // 学生名称
    private String score;   // 成绩；
    private String seq;

    public String getSeq() {
        return seq;
    }

    public void setSeq(String seq) {
        this.seq = seq;
    }

    public ScoreListDto() {
    }

    public ScoreListDto(String subject, String studentName, String score,String seq) {
        this.subject = subject;
        this.studentName = studentName;
        this.score = score;
        this.seq = seq;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }
}
