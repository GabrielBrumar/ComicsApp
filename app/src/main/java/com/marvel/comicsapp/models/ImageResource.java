package com.marvel.comicsapp.models;

import android.os.Parcel;
import android.os.Parcelable;

public class ImageResource implements Parcelable {
    private String path;

    public String getPath() {
        return path;
    }

    public String getExtension() {
        return extension;
    }

    private String extension;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.path);
        dest.writeString(this.extension);
    }

    public ImageResource() {
    }

    protected ImageResource(Parcel in) {
        this.path = in.readString();
        this.extension = in.readString();
    }

    public static final Parcelable.Creator<ImageResource> CREATOR = new Parcelable.Creator<ImageResource>() {
        @Override
        public ImageResource createFromParcel(Parcel source) {
            return new ImageResource(source);
        }

        @Override
        public ImageResource[] newArray(int size) {
            return new ImageResource[size];
        }
    };
}
