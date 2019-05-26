
package com.app.manikandanr.sampleclients.Data;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Students {

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
    @SerializedName("payment_status")
    @Expose
    private String paymentStatus;
    @SerializedName("last_payment_date")
    @Expose
    private String lastPaymentDate;
    @SerializedName("balance_amount")
    @Expose
    private Object balanceAmount;
    @SerializedName("deleted_at")
    @Expose
    private Object deletedAt;
    @SerializedName("department_section_id")
    @Expose
    private String departmentSectionId;
    @SerializedName("department_year_id")
    @Expose
    private String departmentYearId;
    @SerializedName("program_id")
    @Expose
    private String programId;
    @SerializedName("user_id")
    @Expose
    private String userId;
    @SerializedName("parent_name")
    @Expose
    private String parentName;
    @SerializedName("parent_occupation")
    @Expose
    private String parentOccupation;
    @SerializedName("parent_phone")
    @Expose
    private String parentPhone;
    @SerializedName("student_image")
    @Expose
    private String studentImage;
    @SerializedName("bill_no")
    @Expose
    private String billNo;
    @SerializedName("course")
    @Expose
    private Course course;
    @SerializedName("category")
    @Expose
    private Category category;
    @SerializedName("alert")
    @Expose
    private Object alert;
    @SerializedName("payment")
    @Expose
    private List<Object> payment = null;
    @SerializedName("city")
    @Expose
    private City city;
    @SerializedName("state")
    @Expose
    private State state;
    @SerializedName("country")
    @Expose
    private Country country;
    @SerializedName("organization")
    @Expose
    private Organization organization;
    @SerializedName("department")
    @Expose
    private Department department;
    @SerializedName("branch")
    @Expose
    private Object branch;
    @SerializedName("user")
    @Expose
    private User user;

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

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public String getLastPaymentDate() {
        return lastPaymentDate;
    }

    public void setLastPaymentDate(String lastPaymentDate) {
        this.lastPaymentDate = lastPaymentDate;
    }

    public Object getBalanceAmount() {
        return balanceAmount;
    }

    public void setBalanceAmount(Object balanceAmount) {
        this.balanceAmount = balanceAmount;
    }

    public Object getDeletedAt() {
        return deletedAt;
    }

    public void setDeletedAt(Object deletedAt) {
        this.deletedAt = deletedAt;
    }

    public String getDepartmentSectionId() {
        return departmentSectionId;
    }

    public void setDepartmentSectionId(String departmentSectionId) {
        this.departmentSectionId = departmentSectionId;
    }

    public String getDepartmentYearId() {
        return departmentYearId;
    }

    public void setDepartmentYearId(String departmentYearId) {
        this.departmentYearId = departmentYearId;
    }

    public String getProgramId() {
        return programId;
    }

    public void setProgramId(String programId) {
        this.programId = programId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getParentName() {
        return parentName;
    }

    public void setParentName(String parentName) {
        this.parentName = parentName;
    }

    public String getParentOccupation() {
        return parentOccupation;
    }

    public void setParentOccupation(String parentOccupation) {
        this.parentOccupation = parentOccupation;
    }

    public String getParentPhone() {
        return parentPhone;
    }

    public void setParentPhone(String parentPhone) {
        this.parentPhone = parentPhone;
    }

    public String getStudentImage() {
        return studentImage;
    }

    public void setStudentImage(String studentImage) {
        this.studentImage = studentImage;
    }

    public String getBillNo() {
        return billNo;
    }

    public void setBillNo(String billNo) {
        this.billNo = billNo;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Object getAlert() {
        return alert;
    }

    public void setAlert(Object alert) {
        this.alert = alert;
    }

    public List<Object> getPayment() {
        return payment;
    }

    public void setPayment(List<Object> payment) {
        this.payment = payment;
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

    public Organization getOrganization() {
        return organization;
    }

    public void setOrganization(Organization organization) {
        this.organization = organization;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public Object getBranch() {
        return branch;
    }

    public void setBranch(Object branch) {
        this.branch = branch;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

}
