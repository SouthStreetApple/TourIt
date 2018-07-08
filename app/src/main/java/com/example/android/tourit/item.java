package com.example.android.tourit;

import android.os.Parcel;
import android.os.Parcelable;

public class item implements Parcelable {
    String itemTitle;
    String itemAddress;
    String itemCoordinates;
    String itemDescription;
    String itemTimeStart;
    String itemTimeEnd;
    String itemEmail;
    String itemPhone;
    int itemImage;

    public void setItem(String item_title, String item_address, String item_coordinates, String item_description, String item_time_start, String item_time_End, String item_email, String item_phone, int item_image) {
        itemTitle = item_title;
        itemAddress = item_address;
        itemCoordinates = item_coordinates;
        itemDescription = item_description;
        itemTimeStart = item_time_start;
        itemTimeEnd = item_time_End;
        itemImage = item_image;
        itemEmail = item_email;
        itemPhone = item_phone;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.itemTitle);
        dest.writeString(this.itemAddress);
        dest.writeString(this.itemCoordinates);
        dest.writeString(this.itemDescription);
        dest.writeString(this.itemTimeStart);
        dest.writeString(this.itemTimeEnd);
        dest.writeInt(this.itemImage);
        dest.writeString(this.itemEmail);
        dest.writeString(this.itemPhone);
    }

    public item() {
    }

    protected item(Parcel in) {
        this.itemTitle = in.readString();
        this.itemAddress = in.readString();
        this.itemCoordinates = in.readString();
        this.itemDescription = in.readString();
        this.itemTimeStart = in.readString();
        this.itemTimeEnd = in.readString();
        this.itemImage = in.readInt();
        this.itemEmail = in.readString();
        this.itemPhone = in.readString();
    }

    public static final Parcelable.Creator<item> CREATOR = new Parcelable.Creator<item>() {
        @Override
        public item createFromParcel(Parcel source) {
            return new item(source);
        }

        @Override
        public item[] newArray(int size) {
            return new item[size];
        }
    };
}
