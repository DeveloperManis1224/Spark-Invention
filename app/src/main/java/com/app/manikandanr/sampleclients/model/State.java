package com.app.manikandanr.sampleclients.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Map;

public class State implements Parcelable {
    private long id;
    private String countryID;
    private String state;
    private String createdAt;
    private String updatedAt;

    protected State(Parcel in) {
        id = in.readLong();
        countryID = in.readString();
        state = in.readString();
        createdAt = in.readString();
        updatedAt = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(id);
        dest.writeString(countryID);
        dest.writeString(state);
        dest.writeString(createdAt);
        dest.writeString(updatedAt);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<State> CREATOR = new Creator<State>() {
        @Override
        public State createFromParcel(Parcel in) {
            return new State(in);
        }

        @Override
        public State[] newArray(int size) {
            return new State[size];
        }
    };

    public long getID() { return id; }
    public void setID(long value) { this.id = value; }

    public String getCountryID() { return countryID; }
    public void setCountryID(String value) { this.countryID = value; }

    public String getState() { return state; }
    public void setState(String value) { this.state = value; }

    public String getCreatedAt() { return createdAt; }
    public void setCreatedAt(String value) { this.createdAt = value; }

    public String getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(String value) { this.updatedAt = value; }
}
