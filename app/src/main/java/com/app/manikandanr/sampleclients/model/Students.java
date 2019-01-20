package com.app.manikandanr.sampleclients.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Map;

public class Students implements Parcelable {
    private long id;
    private String serialNo;
    private String name;
    private String dob;
    private String instituationID;
    private String organizationID;
    private String categoryID;
    private String phone;
    private String email;
    private String countryID;
    private String stateID;
    private String cityID;
    private String address;
    private String courseID;
    private String role;
    private String joinStatus;
    private String orgDiscountType;
    private String orgDiscount;
    private String courseDiscountType;
    private String courseDiscount;
    private String overallDiscount;
    private String calcAmount;
    private String status;
    private String createdAt;
    private String updatedAt;
    private String departmentID;
    private String paymentStatus;
    private String lastPaymentDate;
    private String balanceAmount;
    private Object deletedAt;
    private Object[] payment;
    private Course course;
    private Alert alert;
    private City city;
    private State state;
    private Country country;

    protected Students(Parcel in) {
        id = in.readLong();
        serialNo = in.readString();
        name = in.readString();
        dob = in.readString();
        instituationID = in.readString();
        organizationID = in.readString();
        categoryID = in.readString();
        phone = in.readString();
        email = in.readString();
        countryID = in.readString();
        stateID = in.readString();
        cityID = in.readString();
        address = in.readString();
        courseID = in.readString();
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
        departmentID = in.readString();
        paymentStatus = in.readString();
        lastPaymentDate = in.readString();
        balanceAmount = in.readString();
        course = in.readParcelable(Course.class.getClassLoader());
        alert = in.readParcelable(Alert.class.getClassLoader());
        city = in.readParcelable(City.class.getClassLoader());
        state = in.readParcelable(State.class.getClassLoader());
        country = in.readParcelable(Country.class.getClassLoader());
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(id);
        dest.writeString(serialNo);
        dest.writeString(name);
        dest.writeString(dob);
        dest.writeString(instituationID);
        dest.writeString(organizationID);
        dest.writeString(categoryID);
        dest.writeString(phone);
        dest.writeString(email);
        dest.writeString(countryID);
        dest.writeString(stateID);
        dest.writeString(cityID);
        dest.writeString(address);
        dest.writeString(courseID);
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
        dest.writeString(departmentID);
        dest.writeString(paymentStatus);
        dest.writeString(lastPaymentDate);
        dest.writeString(balanceAmount);
        dest.writeParcelable(course, flags);
        dest.writeParcelable(alert, flags);
        dest.writeParcelable(city, flags);
        dest.writeParcelable(state, flags);
        dest.writeParcelable(country, flags);
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

    public long getID() { return id; }
    public void setID(long value) { this.id = value; }

    public String getSerialNo() { return serialNo; }
    public void setSerialNo(String value) { this.serialNo = value; }

    public String getName() { return name; }
    public void setName(String value) { this.name = value; }

    public String getDob() { return dob; }
    public void setDob(String value) { this.dob = value; }

    public String getInstituationID() { return instituationID; }
    public void setInstituationID(String value) { this.instituationID = value; }

    public String getOrganizationID() { return organizationID; }
    public void setOrganizationID(String value) { this.organizationID = value; }

    public String getCategoryID() { return categoryID; }
    public void setCategoryID(String value) { this.categoryID = value; }

    public String getPhone() { return phone; }
    public void setPhone(String value) { this.phone = value; }

    public String getEmail() { return email; }
    public void setEmail(String value) { this.email = value; }

    public String getCountryID() { return countryID; }
    public void setCountryID(String value) { this.countryID = value; }

    public String getStateID() { return stateID; }
    public void setStateID(String value) { this.stateID = value; }

    public String getCityID() { return cityID; }
    public void setCityID(String value) { this.cityID = value; }

    public String getAddress() { return address; }
    public void setAddress(String value) { this.address = value; }

    public String getCourseID() { return courseID; }
    public void setCourseID(String value) { this.courseID = value; }

    public String getRole() { return role; }
    public void setRole(String value) { this.role = value; }

    public String getJoinStatus() { return joinStatus; }
    public void setJoinStatus(String value) { this.joinStatus = value; }

    public String getOrgDiscountType() { return orgDiscountType; }
    public void setOrgDiscountType(String value) { this.orgDiscountType = value; }

    public String getOrgDiscount() { return orgDiscount; }
    public void setOrgDiscount(String value) { this.orgDiscount = value; }

    public String getCourseDiscountType() { return courseDiscountType; }
    public void setCourseDiscountType(String value) { this.courseDiscountType = value; }

    public String getCourseDiscount() { return courseDiscount; }
    public void setCourseDiscount(String value) { this.courseDiscount = value; }

    public String getOverallDiscount() { return overallDiscount; }
    public void setOverallDiscount(String value) { this.overallDiscount = value; }

    public String getCalcAmount() { return calcAmount; }
    public void setCalcAmount(String value) { this.calcAmount = value; }

    public String getStatus() { return status; }
    public void setStatus(String value) { this.status = value; }

    public String getCreatedAt() { return createdAt; }
    public void setCreatedAt(String value) { this.createdAt = value; }

    public String getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(String value) { this.updatedAt = value; }

    public String getDepartmentID() { return departmentID; }
    public void setDepartmentID(String value) { this.departmentID = value; }

    public String getPaymentStatus() { return paymentStatus; }
    public void setPaymentStatus(String value) { this.paymentStatus = value; }

    public String getLastPaymentDate() { return lastPaymentDate; }
    public void setLastPaymentDate(String value) { this.lastPaymentDate = value; }

    public String getBalanceAmount() { return balanceAmount; }
    public void setBalanceAmount(String value) { this.balanceAmount = value; }

    public Object getDeletedAt() { return deletedAt; }
    public void setDeletedAt(Object value) { this.deletedAt = value; }

    public Object[] getPayment() { return payment; }
    public void setPayment(Object[] value) { this.payment = value; }

    public Course getCourse() { return course; }
    public void setCourse(Course value) { this.course = value; }

    public Alert getAlert() { return alert; }
    public void setAlert(Alert value) { this.alert = value; }

    public City getCity() { return city; }
    public void setCity(City value) { this.city = value; }

    public State getState() { return state; }
    public void setState(State value) { this.state = value; }

    public Country getCountry() { return country; }
    public void setCountry(Country value) { this.country = value; }
}
