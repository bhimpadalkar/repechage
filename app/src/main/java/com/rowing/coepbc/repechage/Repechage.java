package com.rowing.coepbc.repechage;

import android.os.Parcel;
import android.os.Parcelable;

public class Repechage implements Parcelable {
  private int numberOfLanes;
  private int numberOfEntries;

  public static final Parcelable.Creator<Repechage> CREATOR = new Parcelable.Creator<Repechage>() {

    public Repechage createFromParcel(Parcel in) {
      return new Repechage(in);
    }

    public Repechage[] newArray(int size) {
      return new Repechage[size];
    }
  };

  public Repechage(int numberOfLanes, int numberOfEntries) {
    this.numberOfLanes = numberOfLanes;
    this.numberOfEntries = numberOfEntries;
  }

  public Repechage(Parcel in) {
    numberOfLanes = in.readInt();
    numberOfEntries = in.readInt();
  }

  public int getNumberOfLanes() {
    return numberOfLanes;
  }

  public int getNumberOfEntries() {
    return numberOfEntries;
  }

  @Override
  public int describeContents() {
    return 0;
  }

  @Override
  public void writeToParcel(Parcel dest, int flags) {
    dest.writeInt(numberOfLanes);
    dest.writeInt(numberOfEntries);
  }
}
