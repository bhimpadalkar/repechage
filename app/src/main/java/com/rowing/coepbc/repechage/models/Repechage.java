package com.rowing.coepbc.repechage.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Repechage implements Serializable {
  private List<Participant> participants = new ArrayList<Participant>();
  private int numberOfLanes;
  private int numberOfParticipants;

  public Repechage(int numberOfLanes, int numberOfParticipants) {
    this.numberOfLanes = numberOfLanes;
    this.numberOfParticipants = numberOfParticipants;
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

  public List<Participant> getParticipantsByStatus(ParticipantStatus participantStatus) {
    List<Participant> participantList = new ArrayList<Participant>();
    for (Participant participant : participants) {
      if (participant.getRaceStatus().equals(participantStatus)) {
        participantList.add(participant);
      }
    }
    return participantList;
  }

  public List<Participant> getParticipantsByPoints(int points) {
    List<Participant> participantList = new ArrayList<Participant>();
    for (Participant participant : participants) {
      if (participant.points() == points) {
        participantList.add(participant);
      }
    }
    return participantList;
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

  public void updateParticipants() {
    List<Participant> newListOfParticipants = new ArrayList<Participant>();
    newListOfParticipants.addAll(getParticipantsByStatus(ParticipantStatus.WINNER));
    newListOfParticipants.addAll(getParticipantsByStatus(ParticipantStatus.LOSER));
    newListOfParticipants.addAll(getParticipantsByStatus(ParticipantStatus.ELIMINATED));
    this.participants = newListOfParticipants;
  }

  public void eliminateLosers() {
    for (Participant participant : participants) {
      if (participant.points() == 0) {
        participant.setRaceStatus(ParticipantStatus.ELIMINATED);
      }
    }
  }

  public int getMaxNumberOfPoints() {
    return participants.get(0).points();
  }
}
