package com.rowing.coepbc.repechage.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Repechage implements Serializable {
  public static final int MAX_ENTRIES_2_LANES = 16;
  public static final int MAX_ENTRIES_3_LANES = 18;
  public static final int MAX_ENTRIES_4_LANES = 20;
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
        else numberOfEntries = MAX_ENTRIES_2_LANES;
        break;
      case 3:
        numberOfEntries = ((numberOfParticipants + 2) / 3) * 3;
        numberOfEntries = (numberOfEntries > MAX_ENTRIES_3_LANES) ? MAX_ENTRIES_3_LANES : numberOfEntries;
        break;
      case 4:
        if(numberOfParticipants <= 12)
          numberOfEntries = ((numberOfParticipants + 1 ) / 2) * 2;
        else
          numberOfEntries = ((numberOfParticipants + 3) / 4) * 4;
        numberOfEntries = (numberOfEntries > MAX_ENTRIES_4_LANES) ? MAX_ENTRIES_4_LANES : numberOfEntries;
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

  public List<Participant> getParticipantsByPoints(int points) {
    List<Participant> participantList = new ArrayList<Participant>();
    for (Participant participant : participants) {
      if (participant.points() == points && !participant.isEliminated()) {
        participantList.add(participant);
      }
    }
    return participantList;
  }

  public List<Participant> getParticipantsByRank(int rank) {
    List<Participant> participantList = new ArrayList<Participant>();
    for (Participant participant : participants) {
      if (participant.getRankInRace() == rank && !participant.isEliminated()) {
        participantList.add(participant);
      }
    }
    return participantList;
  }

  public List<Participant> getEliminatedParticipants() {
    List<Participant> participantList = new ArrayList<Participant>();
    for (Participant participant : participants) {
      if (participant.isEliminated()) {
        participantList.add(participant);
      }
    }
    return participantList;
  }

  public int getNumberOfParticipantsRemaining() {
    int remainingParticipants = 0;
    for (Participant participant : participants) {
      if (!participant.isEliminated()) {
        remainingParticipants++;
      }
    }
    return remainingParticipants;
  }

  public void updateParticipants() {
    List<Participant> newListOfParticipants = new ArrayList<>();
    newListOfParticipants.addAll(getParticipantsByRank(0));
    newListOfParticipants.addAll(getParticipantsByRank(1));
    newListOfParticipants.addAll(getEliminatedParticipants());
    this.participants = newListOfParticipants;
  }

  public void eliminateLosers() {
    for (Participant participant : participants) {
      if (participant.chancesRemaining() <= 0) {
        participant.setIsEliminated(true);
      }
    }
  }

  public int getMaxNumberOfPoints() {
    return participants.get(0).points();
  }
}
