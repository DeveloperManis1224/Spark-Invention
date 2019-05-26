
package com.app.manikandanr.sampleclients.Data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Department {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("organization_role")
    @Expose
    private String organizationRole;
    @SerializedName("department")
    @Expose
    private String department;
    @SerializedName("status")
    @Expose
    private Object status;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("updated_at")
    @Expose
    private String updatedAt;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOrganizationRole() {
        return organizationRole;
    }

    public void setOrganizationRole(String organizationRole) {
        this.organizationRole = organizationRole;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public Object getStatus() {
        return status;
    }

    public void setStatus(Object status) {
        this.status = status;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

}
