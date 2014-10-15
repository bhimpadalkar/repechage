package com.rowing.coepbc.repechage;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Repechage implements Serializable {
  private List<Participant> participants;
  private int numberOfLanes;
  private int numberOfParticipants;
  private List<Race> racesPlayedInLastRound;

  public Repechage(int numberOfLanes, int numberOfParticipants) {
    this.numberOfLanes = numberOfLanes;
    this.numberOfParticipants = numberOfParticipants;
    this.participants = new ArrayList<Participant>();
  }

  public int getNumberOfEntries() {
    int numberOfEntries = 0;
    switch (numberOfLanes){
      case 2:
        if(numberOfParticipants <= 4)
          numberOfEntries = 4;
        else if(numberOfParticipants <= 8)
          numberOfEntries = 8;
        else numberOfEntries = 16;
        break;
      case 3:
        numberOfEntries = ((numberOfParticipants + 2) / 3) * 3;
        break;
      case 4:
        if(numberOfParticipants <= 12)
          numberOfEntries = ((numberOfParticipants + 1 ) / 2) * 2;
        else
          numberOfEntries = ((numberOfParticipants + 3) / 4) * 4;
        break;
    }
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

  public void storeRacesPlayedInLastRound(List<Race> racesPlayedInLastRound) {
    this.racesPlayedInLastRound = racesPlayedInLastRound;
  }

  public List<Race> getRacesPlayedInLastRound() {
    return racesPlayedInLastRound;
  }
}
