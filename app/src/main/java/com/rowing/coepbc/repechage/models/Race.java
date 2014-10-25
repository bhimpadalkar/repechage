package com.rowing.coepbc.repechage.models;

import java.io.Serializable;
import java.util.List;

public class Race implements Serializable{
  private String raceID;
  private List<Participant> participantsForRace;

  public Race(String raceID, List<Participant> participantsForRace) {
    this.raceID = raceID;
    this.participantsForRace = participantsForRace;
  }

  public List<Participant> getParticipantsForRace() {
    return participantsForRace;
  }

  public void declareResult(Participant winner, RoundType roundType) {
    for (Participant participant : participantsForRace) {
      if(participant.getName().equals(winner.getName())){
        participant.setRaceStatus(ParticipantStatus.WINNER);
        participant.addPoints(1);
      } else {
        participant.setRaceStatus(ParticipantStatus.LOSER);
        participant.reduceChancesRemaining();
        if(roundType != RoundType.REPECHAGE) participant.addPoints(-1);
      }
    }
  }

  public String getRaceID() {
    return raceID;
  }

  public Participant getWinner() {
    for (Participant participant : participantsForRace) {
      if(participant.getRaceStatus() == ParticipantStatus.WINNER)
        return participant;
    }
    return null;
  }

  public Participant getLoser() {
    for (Participant participant : participantsForRace) {
      if(participant.getRaceStatus() != ParticipantStatus.WINNER)
        return participant;
    }
    return null;
  }
}
