package com.rowing.coepbc.repechage.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Repechage implements Serializable {
  private List<Participant> participants;
  private int numberOfLanes;
  private int numberOfParticipants;

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

  public int getNumberOfParticipantsRemaining() {
    int remainingParticipants = 0;
    for (Participant participant : participants) {
      if (!participant.getRaceStatus().equals(ParticipantStatus.ELIMINATED)) {
        remainingParticipants++;
      }
    }
    return remainingParticipants;
  }

  public List<Participant> getLosers() {
    List<Participant> loserParticipants = new ArrayList<Participant>();
    for (Participant participant : participants) {
      if (participant.getRaceStatus().equals(ParticipantStatus.LOSER)) {
        loserParticipants.add(participant);
      }
    }
    return loserParticipants;
  }

  public List<Participant> getWinners() {
    List<Participant> winnerParticipants = new ArrayList<Participant>();
    for (Participant participant : participants) {
      if (participant.getRaceStatus().equals(ParticipantStatus.WINNER)) {
        winnerParticipants.add(participant);
      }
    }
    return winnerParticipants;
  }

  public void eliminateLosers() {
    for (Participant participant : participants) {
      if (participant.chancesRemaining() == 0) {
        participant.setRaceStatus(ParticipantStatus.ELIMINATED);
      }
    }
  }
}
