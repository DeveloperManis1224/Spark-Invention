
package com.app.manikandanr.sampleclients.Data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Data {

    @SerializedName("instituation_id")
    @Expose
    private String instituationId;
    @SerializedName("organization_id")
    @Expose
    private String organizationId;
    @SerializedName("department_id")
    @Expose
    private String departmentId;
    @SerializedName("department_year_id")
    @Expose
    private String departmentYearId;
    @SerializedName("department_section_id")
    @Expose
    private String departmentSectionId;
    @SerializedName("category_id")
    @Expose
    private String categoryId;
    @SerializedName("syllabi_id")
    @Expose
    private String syllabiId;
    @SerializedName("branch_id")
    @Expose
    private Integer branchId;

    public String getInstituationId() {
        return instituationId;
    }

    public void setInstituationId(String instituationId) {
        this.instituationId = instituationId;
    }

    public String getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(String organizationId) {
        this.organizationId = organizationId;
    }

    public String getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(String departmentId) {
        this.departmentId = departmentId;
    }

    public String getDepartmentYearId() {
        return departmentYearId;
    }

    public void setDepartmentYearId(String departmentYearId) {
        this.departmentYearId = departmentYearId;
    }

    public String getDepartmentSectionId() {
        return departmentSectionId;
    }

    public void setDepartmentSectionId(String departmentSectionId) {
        this.departmentSectionId = departmentSectionId;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getSyllabiId() {
        return syllabiId;
    }

    public void setSyllabiId(String syllabiId) {
        this.syllabiId = syllabiId;
    }

    public Integer getBranchId() {
        return branchId;
    }

    public void setBranchId(Integer branchId) {
        this.branchId = branchId;
    }

}
