package com.rowing.coepbc.repechage;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Race implements Serializable{
  private int laneNumber;
  private List<Participant> participantsForRace;

  public Race(int laneNumber, List<Participant> participantsForRace) {
    this.laneNumber = laneNumber;
    this.participantsForRace = participantsForRace;
  }

  public List<Participant> getParticipantsForRace() {
    return participantsForRace;
  }

  public void declareResult(Participant winner) {
    for (Participant participant : participantsForRace) {
      if(participant.getName().equals(winner.getName())){
        participant.setRaceStatus(ParticipantStatus.WINNER);
      } else {
        participant.setRaceStatus(ParticipantStatus.LOSER);
      }
    }
  }

  public Participant getWinner() {
    for (Participant participant : participantsForRace) {
      if (participant.getRaceStatus().equals(ParticipantStatus.WINNER)) {
        return participant;
      }
    }
    return null;
  }

  public List<Participant> getLosers() {
    List<Participant> losers = new ArrayList<Participant>();
    for (Participant participant : participantsForRace) {
      if (participant.getRaceStatus().equals(ParticipantStatus.LOSER)) {
        losers.add(participant);
      }
    }
    return losers;
  }
}
