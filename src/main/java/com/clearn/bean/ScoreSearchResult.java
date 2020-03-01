package com.clearn.bean;

/**
 * @Author Administrator
 * @Date 2018.22:08
 * @Description 成绩查询结果；
 */
public class ScoreSearchResult {

    private Double totalScore;
    private Integer studentId;

    public Double getTotalScore() {
        return totalScore;
    }

    public void setTotalScore(Double totalScore) {
        this.totalScore = totalScore;
    }

    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }
}
