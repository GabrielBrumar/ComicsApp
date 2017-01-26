package com.marvel.comicsapp.models;

import android.os.Parcel;
import android.os.Parcelable;

public class MarvelCharacter implements Parcelable {

	private long id;
	private String name;
	private String description;
	private ImageResource thumbnail;
	private long getId() {
		return id;
	}

	private String getName() {
		return name;
	}

	private String getDescription() {
		return description;
	}

	public ImageResource getThumbnail() {
		return thumbnail;
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeLong(this.id);
		dest.writeString(this.name);
		dest.writeString(this.description);
		dest.writeParcelable(this.thumbnail, flags);
	}

	public MarvelCharacter() {
	}

	protected MarvelCharacter(Parcel in) {
		this.id = in.readLong();
		this.name = in.readString();
		this.description = in.readString();
		this.thumbnail = in.readParcelable(ImageResource.class.getClassLoader());
	}

	public static final Parcelable.Creator<MarvelCharacter> CREATOR = new Parcelable.Creator<MarvelCharacter>() {
		@Override
		public MarvelCharacter createFromParcel(Parcel source) {
			return new MarvelCharacter(source);
		}

		@Override
		public MarvelCharacter[] newArray(int size) {
			return new MarvelCharacter[size];
		}
	};
}
