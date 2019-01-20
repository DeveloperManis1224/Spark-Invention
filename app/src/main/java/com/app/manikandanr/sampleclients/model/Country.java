package com.app.manikandanr.sampleclients.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Map;

public class Country implements Parcelable {
    private long id;
    private String country;
    private String status;
    private Object createdAt;
    private Object updatedAt;

    protected Country(Parcel in) {
        id = in.readLong();
        country = in.readString();
        status = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(id);
        dest.writeString(country);
        dest.writeString(status);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Country> CREATOR = new Creator<Country>() {
        @Override
        public Country createFromParcel(Parcel in) {
            return new Country(in);
        }

        @Override
        public Country[] newArray(int size) {
            return new Country[size];
        }
    };

    public long getID() { return id; }
    public void setID(long value) { this.id = value; }

    public String getCountry() { return country; }
    public void setCountry(String value) { this.country = value; }

    public String getStatus() { return status; }
    public void setStatus(String value) { this.status = value; }

    public Object getCreatedAt() { return createdAt; }
    public void setCreatedAt(Object value) { this.createdAt = value; }

    public Object getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(Object value) { this.updatedAt = value; }
}
