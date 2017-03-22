package com.dev.jaskiewicz.mobilephones.data;


import android.os.Parcel;
import android.os.Parcelable;

public class MobilePhone implements Parcelable{


    protected MobilePhone(Parcel in) {
    }

    public static final Creator<MobilePhone> CREATOR = new Creator<MobilePhone>() {
        @Override
        public MobilePhone createFromParcel(Parcel in) {
            return new MobilePhone(in);
        }

        @Override
        public MobilePhone[] newArray(int size) {
            return new MobilePhone[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
    }
}
