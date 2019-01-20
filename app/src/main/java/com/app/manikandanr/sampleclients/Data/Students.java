
package com.app.manikandanr.sampleclients.Data;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class Students implements Parcelable {

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
    private String balanceAmount;
    @SerializedName("deleted_at")
    @Expose
    private Object deletedAt;
    @SerializedName("payment")
    @Expose
    private List<Object> payment = null;
    @SerializedName("course")
    @Expose
    private Course course;
    @SerializedName("alert")
    @Expose
    private Alert alert;
    @SerializedName("city")
    @Expose
    private City city;
    @SerializedName("state")
    @Expose
    private State state;
    @SerializedName("country")
    @Expose
    private Country country;

    protected Students(Parcel in) {
        if (in.readByte() == 0) {
            id = null;
        } else {
            id = in.readInt();
        }
        serialNo = in.readString();
        name = in.readString();
        dob = in.readString();
        instituationId = in.readString();
        organizationId = in.readString();
        categoryId = in.readString();
        phone = in.readString();
        email = in.readString();
        countryId = in.readString();
        stateId = in.readString();
        cityId = in.readString();
        address = in.readString();
        courseId = in.readString();
        role = in.readString();
        joinStatus = in.readString();
        orgDiscountType = in.readString();
        orgDiscount = in.readString();
        courseDiscountType = in.readString();
        courseDiscount = in.readString();
        overallDiscount = in.readString();
        calcAmount = in.readString();
        status = in.readString();
        createdAt = in.readString();
        updatedAt = in.readString();
        departmentId = in.readString();
        paymentStatus = in.readString();
        lastPaymentDate = in.readString();
        balanceAmount = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        if (id == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(id);
        }
        dest.writeString(serialNo);
        dest.writeString(name);
        dest.writeString(dob);
        dest.writeString(instituationId);
        dest.writeString(organizationId);
        dest.writeString(categoryId);
        dest.writeString(phone);
        dest.writeString(email);
        dest.writeString(countryId);
        dest.writeString(stateId);
        dest.writeString(cityId);
        dest.writeString(address);
        dest.writeString(courseId);
        dest.writeString(role);
        dest.writeString(joinStatus);
        dest.writeString(orgDiscountType);
        dest.writeString(orgDiscount);
        dest.writeString(courseDiscountType);
        dest.writeString(courseDiscount);
        dest.writeString(overallDiscount);
        dest.writeString(calcAmount);
        dest.writeString(status);
        dest.writeString(createdAt);
        dest.writeString(updatedAt);
        dest.writeString(departmentId);
        dest.writeString(paymentStatus);
        dest.writeString(lastPaymentDate);
        dest.writeString(balanceAmount);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Students> CREATOR = new Creator<Students>() {
        @Override
        public Students createFromParcel(Parcel in) {
            return new Students(in);
        }

        @Override
        public Students[] newArray(int size) {
            return new Students[size];
        }
    };

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

    public String getBalanceAmount() {
        return balanceAmount;
    }

    public void setBalanceAmount(String balanceAmount) {
        this.balanceAmount = balanceAmount;
    }

    public Object getDeletedAt() {
        return deletedAt;
    }

    public void setDeletedAt(Object deletedAt) {
        this.deletedAt = deletedAt;
    }

    public List<Object> getPayment() {
        return payment;
    }

    public void setPayment(List<Object> payment) {
        this.payment = payment;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public Alert getAlert() {
        return alert;
    }

    public void setAlert(Alert alert) {
        this.alert = alert;
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

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("id", id).append("serialNo", serialNo).append("name", name).append("dob", dob).append("instituationId", instituationId).append("organizationId", organizationId).append("categoryId", categoryId).append("phone", phone).append("email", email).append("countryId", countryId).append("stateId", stateId).append("cityId", cityId).append("address", address).append("courseId", courseId).append("role", role).append("joinStatus", joinStatus).append("orgDiscountType", orgDiscountType).append("orgDiscount", orgDiscount).append("courseDiscountType", courseDiscountType).append("courseDiscount", courseDiscount).append("overallDiscount", overallDiscount).append("calcAmount", calcAmount).append("status", status).append("createdAt", createdAt).append("updatedAt", updatedAt).append("departmentId", departmentId).append("paymentStatus", paymentStatus).append("lastPaymentDate", lastPaymentDate).append("balanceAmount", balanceAmount).append("deletedAt", deletedAt).append("payment", payment).append("course", course).append("alert", alert).append("city", city).append("state", state).append("country", country).toString();
    }

}
