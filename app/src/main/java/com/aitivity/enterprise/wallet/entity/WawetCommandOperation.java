package com.aitivity.enterprise.wallet.entity;

import android.os.Parcel;
import android.os.Parcelable;

import com.wallet.crypto.trustapp.entity.TransactionContract;

public class WawetCommandOperation implements Parcelable {
    public String wawetCommandId;
    public String viewType;
    public String from;
    public String to;
    public String value;
    public TransactionContract contract;

    public WawetCommandOperation() {

    }

    private WawetCommandOperation(Parcel in) {
        wawetCommandId = in.readString();
        viewType = in.readString();
        from = in.readString();
        to = in.readString();
        value = in.readString();
        contract = in.readParcelable(WawetCommandContract.class.getClassLoader());
    }

    public static final Creator<WawetCommandOperation> CREATOR = new Creator<WawetCommandOperation>() {
        @Override
        public WawetCommandOperation createFromParcel(Parcel in) {
            return new WawetCommandOperation(in);
        }

        @Override
        public WawetCommandOperation[] newArray(int size) {
            return new WawetCommandOperation[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeString(wawetCommandId);
        parcel.writeString(viewType);
        parcel.writeString(from);
        parcel.writeString(to);
        parcel.writeString(value);
        parcel.writeParcelable(contract, flags);
    }
}
