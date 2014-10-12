package com.rowing.coepbc.repechage;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Repechage implements Serializable {
  private List<Participant> participants;
  private int numberOfLanes;
  private int numberOfEntries;

  public Repechage(int numberOfLanes, int numberOfEntries) {
    this.numberOfLanes = numberOfLanes;
    this.numberOfEntries = numberOfEntries;
    this.participants = new ArrayList<Participant>();
  }

  public int getNumberOfEntries() {
    return numberOfEntries;
  }

  public int getNumberOfLanes() {
    return numberOfLanes;
  }

  public List<Participant> getParticipants() {
    return participants;
  }

  public void setParticipants(List<Participant> participants) {
    this.participants = participants;
  }

  public Participant getParticipantByName(String participantName) {
    for(Participant participant : participants){
      if(participant.getName().equals(participantName))
        return participant;
    }
    return null;
  }
}
