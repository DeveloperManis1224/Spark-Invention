
package com.app.manikandanr.sampleclients.DataModels;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class AlertData {

    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("students")
    @Expose
    private List<Student_> students = null;
    @SerializedName("marketings")
    @Expose
    private List<Marketing> marketings = null;

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

    public List<Student_> getStudents() {
        return students;
    }

    public void setStudents(List<Student_> students) {
        this.students = students;
    }

    public List<Marketing> getMarketings() {
        return marketings;
    }

    public void setMarketings(List<Marketing> marketings) {
        this.marketings = marketings;
    }



}
