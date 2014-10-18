package com.rowing.coepbc.repechage;

import java.io.Serializable;
import java.util.ArrayList;
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

  public void declareResult(Participant winner) {
    for (Participant participant : participantsForRace) {
      if(participant.getName().equals(winner.getName())){
        participant.setRaceStatus(ParticipantStatus.WINNER);
      } else {
        participant.setRaceStatus(ParticipantStatus.LOSER);
      }
    }
  }

  public String getRaceID() {
    return raceID;
  }
}
