package com.pozniak.anatolii.androidtest.models;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Владелец on 24.10.2017.
 */

public class AnimalModel implements Parcelable {

    private String url;
    private String title;

    public AnimalModel(Parcel in) {
        url = in.readString();
        title = in.readString();
    }

    public static final Creator<AnimalModel> CREATOR = new Creator<AnimalModel>() {
        @Override
        public AnimalModel createFromParcel(Parcel in) {
            return new AnimalModel(in);
        }

        @Override
        public AnimalModel[] newArray(int size) {
            return new AnimalModel[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(url);
        parcel.writeString(title);
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
