
package com.app.manikandanr.sampleclients.Data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Student {

    private boolean isPresent=false;

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
    @SerializedName("phone")
    @Expose
    private String phone;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("country_id")
    @Expose
    private Integer countryId;
    @SerializedName("state_id")
    @Expose
    private Integer stateId;
    @SerializedName("city_id")
    @Expose
    private Integer cityId;
    @SerializedName("address")
    @Expose
    private String address;
    @SerializedName("instituation_id")
    @Expose
    private Integer instituationId;
    @SerializedName("organization_id")
    @Expose
    private Integer organizationId;
    @SerializedName("department_id")
    @Expose
    private Integer departmentId;
    @SerializedName("category_id")
    @Expose
    private Integer categoryId;
    @SerializedName("branch_id")
    @Expose
    private Integer branchId;
    @SerializedName("payment")
    @Expose
    private Integer payment;
    @SerializedName("payment_status")
    @Expose
    private Integer paymentStatus;
    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("department_section_id")
    @Expose
    private Integer departmentSectionId;
    @SerializedName("department_year_id")
    @Expose
    private Integer departmentYearId;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("updated_at")
    @Expose
    private String updatedAt;
    @SerializedName("category")
    @Expose
    private Category category;
    @SerializedName("department")
    @Expose
    private Department department;
    @SerializedName("organization")
    @Expose
    private Organization organization;
    @SerializedName("branch")
    @Expose
    private Branch branch;
    @SerializedName("city")
    @Expose
    private City city;
    @SerializedName("state")
    @Expose
    private State state;
    @SerializedName("country")
    @Expose
    private Country country;

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

    public Integer getCountryId() {
        return countryId;
    }

    public void setCountryId(Integer countryId) {
        this.countryId = countryId;
    }

    public Integer getStateId() {
        return stateId;
    }

    public void setStateId(Integer stateId) {
        this.stateId = stateId;
    }

    public Integer getCityId() {
        return cityId;
    }

    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getInstituationId() {
        return instituationId;
    }

    public void setInstituationId(Integer instituationId) {
        this.instituationId = instituationId;
    }

    public Integer getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(Integer organizationId) {
        this.organizationId = organizationId;
    }

    public Integer getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Integer departmentId) {
        this.departmentId = departmentId;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public Integer getBranchId() {
        return branchId;
    }

    public void setBranchId(Integer branchId) {
        this.branchId = branchId;
    }

    public Integer getPayment() {
        return payment;
    }

    public void setPayment(Integer payment) {
        this.payment = payment;
    }

    public Integer getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(Integer paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getDepartmentSectionId() {
        return departmentSectionId;
    }

    public void setDepartmentSectionId(Integer departmentSectionId) {
        this.departmentSectionId = departmentSectionId;
    }

    public Integer getDepartmentYearId() {
        return departmentYearId;
    }

    public void setDepartmentYearId(Integer departmentYearId) {
        this.departmentYearId = departmentYearId;
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

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public Organization getOrganization() {
        return organization;
    }

    public void setOrganization(Organization organization) {
        this.organization = organization;
    }

    public Branch getBranch() {
        return branch;
    }

    public void setBranch(Branch branch) {
        this.branch = branch;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public boolean isPresent() {
        return isPresent;
    }

    public void setPresent(boolean present) {
        isPresent = present;
    }
}
