package com.app.manikandanr.sampleclients.Data;

public class StudentAlertData {
    private String id;
    private String studentId;
    private String studentDate ;
    private  String studentSerialNumber;
    private String studentName;
    private String studentDob;
    private String studentInstitutionType;
    private String studentCategory;
    private String studentOrganization;
    private String studentPhone;
    private String studentEmail;
    private String studentAddress;
    private String studentCourse;

    private String studentPaymentStatus;
    private String studentAttendanceStatus;
    private String studentLastPaymentDate;
    private String balanceAmount;


    public StudentAlertData(String studentId, String studentDate, String studentSerialNumber,
                            String studentName, String studentDob, String studentInstitutionType,
                            String studentCategory, String studentOrganization, String studentPhone,
                            String studentEmail, String studentAddress, String studentCourse,
                            String studentPaymentStatus, String studentAttendanceStatus,
                            String studentLastPaymentDate, String balanceAmount) {
        this.studentId = studentId;
        this.studentDate = studentDate;
        this.studentSerialNumber = studentSerialNumber;
        this.studentName = studentName;
        this.studentDob = studentDob;
        this.studentInstitutionType = studentInstitutionType;
        this.studentCategory = studentCategory;
        this.studentOrganization = studentOrganization;
        this.studentPhone = studentPhone;
        this.studentEmail = studentEmail;
        this.studentAddress = studentAddress;
        this.studentCourse = studentCourse;
        this.studentPaymentStatus = studentPaymentStatus;
        this.studentAttendanceStatus = studentAttendanceStatus;
        this.studentLastPaymentDate = studentLastPaymentDate;
        this.balanceAmount = balanceAmount;
    }

    public StudentAlertData(String id,String studentId, String studentDate, String studentSerialNumber,
                            String studentName, String studentDob, String studentInstitutionType,
                            String studentCategory, String studentOrganization, String studentPhone,
                            String studentEmail, String studentAddress, String studentCourse) {
        this.studentId = studentId;
        this.studentDate = studentDate;
        this.studentSerialNumber = studentSerialNumber;
        this.studentName = studentName;
        this.studentDob = studentDob;
        this.studentInstitutionType = studentInstitutionType;
        this.studentCategory = studentCategory;
        this.studentOrganization = studentOrganization;
        this.studentPhone = studentPhone;
        this.studentEmail = studentEmail;
        this.studentAddress = studentAddress;
        this.studentCourse = studentCourse;
        this.id = id;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getStudentDate() {
        return studentDate;
    }

    public void setStudentDate(String studentDate) {
        this.studentDate = studentDate;
    }

    public String getStudentSerialNumber() {
        return studentSerialNumber;
    }

    public void setStudentSerialNumber(String studentSerialNumber) {
        this.studentSerialNumber = studentSerialNumber;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getStudentDob() {
        return studentDob;
    }

    public void setStudentDob(String studentDob) {
        this.studentDob = studentDob;
    }

    public String getStudentInstitutionType() {
        return studentInstitutionType;
    }

    public void setStudentInstitutionType(String studentInstitutionType) {
        this.studentInstitutionType = studentInstitutionType;
    }

    public String getStudentCategory() {
        return studentCategory;
    }

    public void setStudentCategory(String studentCategory) {
        this.studentCategory = studentCategory;
    }

    public String getStudentOrganization() {
        return studentOrganization;
    }

    public void setStudentOrganization(String studentOrganization) {
        this.studentOrganization = studentOrganization;
    }

    public String getStudentPhone() {
        return studentPhone;
    }

    public void setStudentPhone(String studentPhone) {
        this.studentPhone = studentPhone;
    }

    public String getStudentEmail() {
        return studentEmail;
    }

    public void setStudentEmail(String studentEmail) {
        this.studentEmail = studentEmail;
    }

    public String getStudentAddress() {
        return studentAddress;
    }

    public void setStudentAddress(String studentAddress) {
        this.studentAddress = studentAddress;
    }

    public String getStudentCourse() {
        return studentCourse;
    }

    public void setStudentCourse(String studentCourse) {
        this.studentCourse = studentCourse;
    }

    public String getStudentPaymentStatus() {
        return studentPaymentStatus;
    }

    public void setStudentPaymentStatus(String studentPaymentStatus) {
        this.studentPaymentStatus = studentPaymentStatus;
    }

    public String getStudentAttendanceStatus() {
        return studentAttendanceStatus;
    }

    public void setStudentAttendanceStatus(String studentAttendanceStatus) {
        this.studentAttendanceStatus = studentAttendanceStatus;
    }

    public String getStudentLastPaymentDate() {
        return studentLastPaymentDate;
    }

    public void setStudentLastPaymentDate(String studentLastPaymentDate) {
        this.studentLastPaymentDate = studentLastPaymentDate;
    }

    public String getBalanceAmount() {
        return balanceAmount;
    }

    public void setBalanceAmount(String balanceAmount) {
        this.balanceAmount = balanceAmount;
    }
}
