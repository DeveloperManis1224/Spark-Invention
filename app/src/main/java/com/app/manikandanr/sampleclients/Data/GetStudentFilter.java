
package com.app.manikandanr.sampleclients.Data;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GetStudentFilter {

    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("syllabus_lists")
    @Expose
    private ArrayList<SyllabusList> syllabusLists = null;
    @SerializedName("students")
    @Expose
    private ArrayList<Student> students = null;
    @SerializedName("data")
    @Expose
    private Data data;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<SyllabusList> getSyllabusLists() {
        return syllabusLists;
    }

    public void setSyllabusLists(ArrayList<SyllabusList> syllabusLists) {
        this.syllabusLists = syllabusLists;
    }

    public ArrayList<Student> getStudents() {
        return students;
    }

    public void setStudents(ArrayList<Student> students) {
        this.students = students;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

}
