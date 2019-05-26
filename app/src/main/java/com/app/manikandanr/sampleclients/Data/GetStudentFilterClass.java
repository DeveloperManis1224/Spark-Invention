
package com.app.manikandanr.sampleclients.Data;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GetStudentFilterClass {

    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("syllabusClass_lists")
    @Expose
    private List<SyllabusClassList> syllabusClassLists = null;
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

    public List<SyllabusClassList> getSyllabusClassLists() {
        return syllabusClassLists;
    }

    public void setSyllabusClassLists(List<SyllabusClassList> syllabusClassLists) {
        this.syllabusClassLists = syllabusClassLists;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

}
