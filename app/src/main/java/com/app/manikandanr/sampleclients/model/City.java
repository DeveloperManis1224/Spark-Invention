package com.app.manikandanr.sampleclients.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Map;

public class City implements Parcelable {
    private long id;
    private String stateID;
    private String city;
    private String createdAt;
    private String updatedAt;

    protected City(Parcel in) {
        id = in.readLong();
        stateID = in.readString();
        city = in.readString();
        createdAt = in.readString();
        updatedAt = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(id);
        dest.writeString(stateID);
        dest.writeString(city);
        dest.writeString(createdAt);
        dest.writeString(updatedAt);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<City> CREATOR = new Creator<City>() {
        @Override
        public City createFromParcel(Parcel in) {
            return new City(in);
        }

        @Override
        public City[] newArray(int size) {
            return new City[size];
        }
    };

    public long getID() { return id; }
    public void setID(long value) { this.id = value; }

    public String getStateID() { return stateID; }
    public void setStateID(String value) { this.stateID = value; }

    public String getCity() { return city; }
    public void setCity(String value) { this.city = value; }

    public String getCreatedAt() { return createdAt; }
    public void setCreatedAt(String value) { this.createdAt = value; }

    public String getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(String value) { this.updatedAt = value; }
}
