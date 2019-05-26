package com.app.manikandanr.sampleclients.Data;

public class AttendancePresentData {

    private String studentId;
    private String studentName;
    private String isPresent;

    public AttendancePresentData(String studentId, String studentName, String isPresent) {
        this.studentId = studentId;
        this.studentName = studentName;
        this.isPresent = isPresent;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getIsPresent() {
        return isPresent;
    }

    public void setIsPresent(String isPresent) {
        this.isPresent = isPresent;
    }
}
