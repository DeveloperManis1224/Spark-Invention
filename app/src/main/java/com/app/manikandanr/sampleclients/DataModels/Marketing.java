package com.app.manikandanr.sampleclients.DataModels;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Marketing {
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("instituation_id")
    @Expose
    private String instituationId;
    @SerializedName("organization_id")
    @Expose
    private String organizationId;
    @SerializedName("serial_no")
    @Expose
    private String serialNo;
    @SerializedName("staff_name")
    @Expose
    private String staffName;
    @SerializedName("phone")
    @Expose
    private String phone;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("state_id")
    @Expose
    private String stateId;
    @SerializedName("city_id")
    @Expose
    private String cityId;
    @SerializedName("country_id")
    @Expose
    private String countryId;
    @SerializedName("year")
    @Expose
    private String year;
    @SerializedName("strength")
    @Expose
    private String strength;
    @SerializedName("address")
    @Expose
    private String address;
    @SerializedName("landline")
    @Expose
    private String landline;
    @SerializedName("event_id")
    @Expose
    private String eventId;
    @SerializedName("date")
    @Expose
    private String date;
    @SerializedName("next_date")
    @Expose
    private String nextDate;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("updated_at")
    @Expose
    private String updatedAt;
    @SerializedName("website")
    @Expose
    private String website;
    @SerializedName("distance")
    @Expose
    private String distance;
    @SerializedName("latitude")
    @Expose
    private String latitude;
    @SerializedName("longitude")
    @Expose
    private String longitude;
    @SerializedName("principal_ao_name")
    @Expose
    private String principalAoName;
    @SerializedName("principal_ao_details")
    @Expose
    private String principalAoDetails;
    @SerializedName("department_id")
    @Expose
    private String departmentId;
    @SerializedName("demo_date")
    @Expose
    private String demoDate;
    @SerializedName("other_details")
    @Expose
    private String otherDetails;
    @SerializedName("user_id")
    @Expose
    private String userId;
    @SerializedName("branch_id")
    @Expose
    private String branchId;
    @SerializedName("organization")
    @Expose
    private Organization organization;
    @SerializedName("event")
    @Expose
    private Event event;
    @SerializedName("city")
    @Expose
    private Object city;
    @SerializedName("state")
    @Expose
    private Object state;
    @SerializedName("country")
    @Expose
    private Object country;
    @SerializedName("department")
    @Expose
    private Department department;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public String getSerialNo() {
        return serialNo;
    }

    public void setSerialNo(String serialNo) {
        this.serialNo = serialNo;
    }

    public String getStaffName() {
        return staffName;
    }

    public void setStaffName(String staffName) {
        this.staffName = staffName;
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

    public String getCountryId() {
        return countryId;
    }

    public void setCountryId(String countryId) {
        this.countryId = countryId;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getStrength() {
        return strength;
    }

    public void setStrength(String strength) {
        this.strength = strength;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getLandline() {
        return landline;
    }

    public void setLandline(String landline) {
        this.landline = landline;
    }

    public String getEventId() {
        return eventId;
    }

    public void setEventId(String eventId) {
        this.eventId = eventId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getNextDate() {
        return nextDate;
    }

    public void setNextDate(String nextDate) {
        this.nextDate = nextDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getPrincipalAoName() {
        return principalAoName;
    }

    public void setPrincipalAoName(String principalAoName) {
        this.principalAoName = principalAoName;
    }

    public String getPrincipalAoDetails() {
        return principalAoDetails;
    }

    public void setPrincipalAoDetails(String principalAoDetails) {
        this.principalAoDetails = principalAoDetails;
    }

    public String getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(String departmentId) {
        this.departmentId = departmentId;
    }

    public String getDemoDate() {
        return demoDate;
    }

    public void setDemoDate(String demoDate) {
        this.demoDate = demoDate;
    }

    public String getOtherDetails() {
        return otherDetails;
    }

    public void setOtherDetails(String otherDetails) {
        this.otherDetails = otherDetails;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getBranchId() {
        return branchId;
    }

    public void setBranchId(String branchId) {
        this.branchId = branchId;
    }

    public Organization getOrganization() {
        return organization;
    }

    public void setOrganization(Organization organization) {
        this.organization = organization;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public Object getCity() {
        return city;
    }

    public void setCity(Object city) {
        this.city = city;
    }

    public Object getState() {
        return state;
    }

    public void setState(Object state) {
        this.state = state;
    }

    public Object getCountry() {
        return country;
    }

    public void setCountry(Object country) {
        this.country = country;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

}
