package com.aitivity.enterprise.wallet.entity;

import android.os.Parcel;
import android.os.Parcelable;

public class WawetCommandContract implements Parcelable {
    public String address;
    public String name;
    public String totalSupply;
    public long decimals;
    public String symbol;

    protected WawetCommandContract(Parcel in) {
        address = in.readString();
        name = in.readString();
        totalSupply = in.readString();
        decimals = in.readLong();
        symbol = in.readString();
    }

    public static final Creator<WawetCommandContract> CREATOR = new Creator<WawetCommandContract>() {
        @Override
        public WawetCommandContract createFromParcel(Parcel in) {
            return new WawetCommandContract(in);
        }

        @Override
        public WawetCommandContract[] newArray(int size) {
            return new WawetCommandContract[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(address);
        parcel.writeString(name);
        parcel.writeString(totalSupply);
        parcel.writeLong(decimals);
        parcel.writeString(symbol);
    }
}
