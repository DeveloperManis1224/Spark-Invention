package com.app.manikandanr.sampleclients.DataModels;

public class AttendanceData {
    private String _studentName;
    private String _studentRegNumber;
    private String _studentPaymentStatus;
    private String _studentAttendance;
    private String _isPresent;
    private String _id;
    private String _studentId;

    public AttendanceData(String _studentName, String _studentRegNumber,
                          String _studentPaymentStatus, String _studentAttendance,
                          String _isPresent, String _id, String _studentId) {
        this._studentName = _studentName;
        this._studentRegNumber = _studentRegNumber;

        this._studentPaymentStatus = _studentPaymentStatus;
        this._studentAttendance = _studentAttendance;
        this._isPresent = _isPresent;
        this._id = _id;
        this._studentId = _studentId;
    }

    public String get_studentName() {
        return _studentName;
    }

    public void set_studentName(String _studentName) {
        this._studentName = _studentName;
    }

    public String get_studentRegNumber() {
        return _studentRegNumber;
    }

    public void set_studentRegNumber(String _studentRegNumber) {
        this._studentRegNumber = _studentRegNumber;
    }


    public String get_studentPaymentStatus() {
        return _studentPaymentStatus;
    }

    public void set_studentPaymentStatus(String _studentPaymentStatus) {
        this._studentPaymentStatus = _studentPaymentStatus;
    }

    public String get_studentAttendance() {
        return _studentAttendance;
    }

    public void set_studentAttendance(String _studentAttendance) {
        this._studentAttendance = _studentAttendance;
    }

    public String get_isPresent() {
        return _isPresent;
    }

    public void set_isPresent(String _isPresent) {
        this._isPresent = _isPresent;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String get_studentId() {
        return _studentId;
    }

    public void set_studentId(String _studentId) {
        this._studentId = _studentId;
    }
}
