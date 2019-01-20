package com.app.manikandanr.sampleclients.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Map;

public class Alert implements Parcelable {
    private long id;
    private String studentID;
    private String date;
    private String status;
    private String createdAt;
    private String updatedAt;

    protected Alert(Parcel in) {
        id = in.readLong();
        studentID = in.readString();
        date = in.readString();
        status = in.readString();
        createdAt = in.readString();
        updatedAt = in.readString();
    }

    public static final Creator<Alert> CREATOR = new Creator<Alert>() {
        @Override
        public Alert createFromParcel(Parcel in) {
            return new Alert(in);
        }

        @Override
        public Alert[] newArray(int size) {
            return new Alert[size];
        }
    };

    public long getID() { return id; }
    public void setID(long value) { this.id = value; }

    public String getStudentID() { return studentID; }
    public void setStudentID(String value) { this.studentID = value; }

    public String getDate() { return date; }
    public void setDate(String value) { this.date = value; }

    public String getStatus() { return status; }
    public void setStatus(String value) { this.status = value; }

    public String getCreatedAt() { return createdAt; }
    public void setCreatedAt(String value) { this.createdAt = value; }

    public String getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(String value) { this.updatedAt = value; }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(id);
        dest.writeString(studentID);
        dest.writeString(date);
        dest.writeString(status);
        dest.writeString(createdAt);
        dest.writeString(updatedAt);
    }
}
