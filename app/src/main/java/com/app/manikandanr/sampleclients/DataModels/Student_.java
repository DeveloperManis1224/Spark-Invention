
package com.app.manikandanr.sampleclients.DataModels;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import org.apache.commons.lang.builder.ToStringBuilder;

public class Student_ {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("serial_no")
    @Expose
    private String serialNo;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("dob")
    @Expose
    private String dob;
    @SerializedName("instituation_id")
    @Expose
    private String instituationId;
    @SerializedName("organization_id")
    @Expose
    private String organizationId;
    @SerializedName("category_id")
    @Expose
    private String categoryId;
    @SerializedName("phone")
    @Expose
    private String phone;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("country_id")
    @Expose
    private String countryId;
    @SerializedName("state_id")
    @Expose
    private String stateId;
    @SerializedName("city_id")
    @Expose
    private String cityId;
    @SerializedName("address")
    @Expose
    private String address;
    @SerializedName("course_id")
    @Expose
    private String courseId;
    @SerializedName("role")
    @Expose
    private String role;
    @SerializedName("join_status")
    @Expose
    private String joinStatus;
    @SerializedName("org_discount_type")
    @Expose
    private String orgDiscountType;
    @SerializedName("org_discount")
    @Expose
    private String orgDiscount;
    @SerializedName("course_discount_type")
    @Expose
    private String courseDiscountType;
    @SerializedName("course_discount")
    @Expose
    private String courseDiscount;
    @SerializedName("overall_discount")
    @Expose
    private String overallDiscount;
    @SerializedName("calc_amount")
    @Expose
    private String calcAmount;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("updated_at")
    @Expose
    private String updatedAt;
    @SerializedName("department_id")
    @Expose
    private String departmentId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSerialNo() {
        return serialNo;
    }

    public void setSerialNo(String serialNo) {
        this.serialNo = serialNo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

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

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCountryId() {
        return countryId;
    }

    public void setCountryId(String countryId) {
        this.countryId = countryId;
    }

    public String getStateId() {
        return stateId;
    }

    public void setStateId(String stateId) {
        this.stateId = stateId;
    }

    public String getCityId() {
        return cityId;
    }

    public void setCityId(String cityId) {
        this.cityId = cityId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getJoinStatus() {
        return joinStatus;
    }

    public void setJoinStatus(String joinStatus) {
        this.joinStatus = joinStatus;
    }

    public String getOrgDiscountType() {
        return orgDiscountType;
    }

    public void setOrgDiscountType(String orgDiscountType) {
        this.orgDiscountType = orgDiscountType;
    }

    public String getOrgDiscount() {
        return orgDiscount;
    }

    public void setOrgDiscount(String orgDiscount) {
        this.orgDiscount = orgDiscount;
    }

    public String getCourseDiscountType() {
        return courseDiscountType;
    }

    public void setCourseDiscountType(String courseDiscountType) {
        this.courseDiscountType = courseDiscountType;
    }

    public String getCourseDiscount() {
        return courseDiscount;
    }

    public void setCourseDiscount(String courseDiscount) {
        this.courseDiscount = courseDiscount;
    }

    public String getOverallDiscount() {
        return overallDiscount;
    }

    public void setOverallDiscount(String overallDiscount) {
        this.overallDiscount = overallDiscount;
    }

    public String getCalcAmount() {
        return calcAmount;
    }

    public void setCalcAmount(String calcAmount) {
        this.calcAmount = calcAmount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
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

    public String getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(String departmentId) {
        this.departmentId = departmentId;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("id", id).append("serialNo", serialNo).append("name", name).append("dob", dob).append("instituationId", instituationId).append("organizationId", organizationId).append("categoryId", categoryId).append("phone", phone).append("email", email).append("countryId", countryId).append("stateId", stateId).append("cityId", cityId).append("address", address).append("courseId", courseId).append("role", role).append("joinStatus", joinStatus).append("orgDiscountType", orgDiscountType).append("orgDiscount", orgDiscount).append("courseDiscountType", courseDiscountType).append("courseDiscount", courseDiscount).append("overallDiscount", overallDiscount).append("calcAmount", calcAmount).append("status", status).append("createdAt", createdAt).append("updatedAt", updatedAt).append("departmentId", departmentId).toString();
    }

}
