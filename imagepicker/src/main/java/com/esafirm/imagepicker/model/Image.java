package com.esafirm.imagepicker.model;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;

public class Image implements Parcelable {

    private long id;
    private String name;
    private Uri imageUri;
    private MediaType mediaType;
    private long creationDate;
    private String bucketName;
    private String mimeType;

    public Image(long id, String name, String bucketName, String mimeType, Uri imageUri, MediaType mediaType, long creationDate) {
        this.id = id;
        this.name = name;
        this.imageUri = imageUri;
        this.mediaType = mediaType;
        this.creationDate = creationDate;
        this.bucketName = bucketName;
        this.mimeType = mimeType;
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

    public String getMimeType() {return mimeType; }

    public void setMimeType(String mimeType) {
        this.mimeType = mimeType;
    }

    public String getBucketName() {return bucketName;}

    public void setBucketName(String bucketName) {
        this.bucketName = bucketName;
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
        dest.writeParcelable(this.imageUri, flags);
        dest.writeInt(this.mediaType.value);
        dest.writeLong(this.creationDate);
        dest.writeString(this.bucketName);
        dest.writeString(this.mimeType);
    }

    protected Image(Parcel in) {
        this.id = in.readLong();
        this.name = in.readString();
        this.imageUri = in.readParcelable(Uri.class.getClassLoader());
        this.mediaType = MediaType.fromInteger(in.readInt());
        this.creationDate = in.readLong();
        this.bucketName = in.readString();
        this.mimeType = in.readString();
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
