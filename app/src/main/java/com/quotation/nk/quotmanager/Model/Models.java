package com.quotation.nk.quotmanager.Model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Nk on 10/28/2018.
 */

public class Models implements Parcelable {


    String models_name;

    protected Models(Parcel in) {

        models_name = in.readString();
    }

    public static final Creator<Models> CREATOR = new Creator<Models>() {
        @Override
        public Models createFromParcel(Parcel in) {
            return new Models(in);
        }

        @Override
        public Models[] newArray(int size) {
            return new Models[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

        dest.writeString(models_name);
    }

    public String getCity_name() {
        return models_name;
    }

    public void setCity_name(String city_name) {
        this.models_name = city_name;
    }

    public static Creator<Models> getCREATOR() {
        return CREATOR;
    }

}

