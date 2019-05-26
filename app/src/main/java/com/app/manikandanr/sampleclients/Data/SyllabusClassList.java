
package com.app.manikandanr.sampleclients.Data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SyllabusClassList {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("syllabi_id")
    @Expose
    private Integer syllabiId;
    @SerializedName("class")
    @Expose
    private String _class;
    @SerializedName("hours")
    @Expose
    private Integer hours;
    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("created_at")
    @Expose
    private Object createdAt;
    @SerializedName("updated_at")
    @Expose
    private Object updatedAt;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSyllabiId() {
        return syllabiId;
    }

    public void setSyllabiId(Integer syllabiId) {
        this.syllabiId = syllabiId;
    }

    public String getClass_() {
        return _class;
    }

    public void setClass_(String _class) {
        this._class = _class;
    }

    public Integer getHours() {
        return hours;
    }

    public void setHours(Integer hours) {
        this.hours = hours;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Object getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Object createdAt) {
        this.createdAt = createdAt;
    }

    public Object getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Object updatedAt) {
        this.updatedAt = updatedAt;
    }

}
