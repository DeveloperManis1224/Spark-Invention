package com.app.manikandanr.sampleclients.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Map;

public class StudentData  implements Parcelable {
    private long status;
    private String message;
    private Students students;

    protected StudentData(Parcel in) {
        status = in.readLong();
        message = in.readString();
    }

    public static final Creator<StudentData> CREATOR = new Creator<StudentData>() {
        @Override
        public StudentData createFromParcel(Parcel in) {
            return new StudentData(in);
        }

        @Override
        public StudentData[] newArray(int size) {
            return new StudentData[size];
        }
    };

    public long getStatus() { return status; }
    public void setStatus(long value) { this.status = value; }

    public String getMessage() { return message; }
    public void setMessage(String value) { this.message = value; }

    public Students getStudents() { return students; }
    public void setStudents(Students value) { this.students = value; }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(status);
        dest.writeString(message);
    }
}
