package com.aitivity.enterprise.wallet.entity;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Arrays;

public class WawetCommand implements Parcelable {
    public long id;
    public String command;
    //public final long timeStamp;
    public final WawetCommandOperation[] operations;

    public WawetCommand(
            long id,
            //long timeStamp,
            String command,
            WawetCommandOperation[] operations
    ){
        //this.timeStamp = timeStamp;
        this.operations = operations;

    };

    protected WawetCommand(Parcel in) {
        id = in.readLong();
        //timeStamp = in.readLong();
        command = in.readString();
        Parcelable[] parcelableArray = in.readParcelableArray(WawetCommandOperation.class.getClassLoader());
        WawetCommandOperation[] operations = null;
        if (parcelableArray != null) {
            operations = Arrays.copyOf(parcelableArray, parcelableArray.length, WawetCommandOperation[].class);
        }
        this.operations = operations;
    }

    public static final Creator<WawetCommand> CREATOR = new Creator<WawetCommand>() {
        @Override
        public WawetCommand createFromParcel(Parcel in) {
            return new WawetCommand(in);
        }

        @Override
        public WawetCommand[] newArray(int size) {
            return new WawetCommand[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(id);
        //dest.writeLong(timeStamp);
        dest.writeString(command);
        dest.writeParcelableArray(operations, flags);
    }
}
