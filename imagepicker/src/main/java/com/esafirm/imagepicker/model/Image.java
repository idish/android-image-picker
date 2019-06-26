package com.esafirm.imagepicker.model;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;

public class Image implements Parcelable {

    private long id;
    private String name;
    private String path;
    private Uri imageUri;
    private MediaType mediaType;
    private long creationDate;

    public Image(long id, String name, String path, Uri imageUri, MediaType mediaType, long creationDate) {
        this.id = id;
        this.name = name;
        this.path = path;
        this.imageUri = imageUri;
        this.mediaType = mediaType;
        this.creationDate = creationDate;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Uri getImageUri() { return imageUri; }

    public MediaType getMediaType() { return mediaType; }

    public long getCreationDate() { return creationDate; }

    /* --------------------------------------------------- */
    /* > Parcelable */
    /* --------------------------------------------------- */

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(this.id);
        dest.writeString(this.name);
        dest.writeString(this.path);
        dest.writeParcelable(this.imageUri, flags);
        dest.writeInt(this.mediaType.value);
        dest.writeLong(this.creationDate);
    }

    protected Image(Parcel in) {
        this.id = in.readLong();
        this.name = in.readString();
        this.path = in.readString();
        this.imageUri = in.readParcelable(Uri.class.getClassLoader());
        this.mediaType = MediaType.fromInteger(in.readInt());
        this.creationDate = in.readLong();
    }

    public static final Creator<Image> CREATOR = new Creator<Image>() {
        @Override
        public Image createFromParcel(Parcel source) {
            return new Image(source);
        }

        @Override
        public Image[] newArray(int size) {
            return new Image[size];
        }
    };

}
