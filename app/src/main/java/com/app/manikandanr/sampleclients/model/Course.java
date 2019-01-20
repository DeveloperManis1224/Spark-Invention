package com.app.manikandanr.sampleclients.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Map;

public class Course implements Parcelable {
    private long id;
    private String course;
    private String amount;
    private String status;
    private String createdAt;
    private String updatedAt;
    private String offerType;
    private String offer;
    private String categoryID;

    protected Course(Parcel in) {
        id = in.readLong();
        course = in.readString();
        amount = in.readString();
        status = in.readString();
        createdAt = in.readString();
        updatedAt = in.readString();
        offerType = in.readString();
        offer = in.readString();
        categoryID = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(id);
        dest.writeString(course);
        dest.writeString(amount);
        dest.writeString(status);
        dest.writeString(createdAt);
        dest.writeString(updatedAt);
        dest.writeString(offerType);
        dest.writeString(offer);
        dest.writeString(categoryID);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Course> CREATOR = new Creator<Course>() {
        @Override
        public Course createFromParcel(Parcel in) {
            return new Course(in);
        }

        @Override
        public Course[] newArray(int size) {
            return new Course[size];
        }
    };

    public long getID() { return id; }
    public void setID(long value) { this.id = value; }

    public String getCourse() { return course; }
    public void setCourse(String value) { this.course = value; }

    public String getAmount() { return amount; }
    public void setAmount(String value) { this.amount = value; }

    public String getStatus() { return status; }
    public void setStatus(String value) { this.status = value; }

    public String getCreatedAt() { return createdAt; }
    public void setCreatedAt(String value) { this.createdAt = value; }

    public String getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(String value) { this.updatedAt = value; }

    public String getOfferType() { return offerType; }
    public void setOfferType(String value) { this.offerType = value; }

    public String getOffer() { return offer; }
    public void setOffer(String value) { this.offer = value; }

    public String getCategoryID() { return categoryID; }
    public void setCategoryID(String value) { this.categoryID = value; }
}
