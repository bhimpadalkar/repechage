package com.rowing.coepbc.repechage.models;

import java.io.Serializable;
import java.util.List;

public class Race implements Serializable{
  private List<Participant> participantsForRace;

  public Race(List<Participant> participantsForRace) {
    this.participantsForRace = participantsForRace;
  }

  public List<Participant> getParticipantsForRace() {
    return participantsForRace;
  }

  public void declareResult(RoundType roundType) {
    for (Participant participant : participantsForRace) {
      if(participant.getRankInRace() == 0){
        participant.addPoints(1);
      } else {
        participant.reduceChancesRemaining();
        if(roundType != RoundType.REPECHAGE) participant.addPoints(-1);
      }
    }
  }

  public Participant getWinner() {
    for (Participant participant : participantsForRace) {
      if(participant.getRankInRace() == 0)
        return participant;
    }
    return null;
  }

  public Participant getLoser() {
    for (Participant participant : participantsForRace) {
      if(participant.getRankInRace() != 0)
        return participant;
    }
    return null;
  }

  public void setRankForParticipant(String participantName, int rank) {
    for (Participant participant : participantsForRace) {
      if(participant.getName().equals(participantName)){
        participant.setRankInRace(rank);
        return;
      }
    }
  }

  public void updateLastPlayedRaceName(char roundName, int raceIndex) {
    for (Participant participant : participantsForRace) {
      participant.setLastPlayedRaceName(String.valueOf(roundName) + (raceIndex + 1));
    }
  }
}
