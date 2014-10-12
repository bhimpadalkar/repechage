package com.rowing.coepbc.repechage;

import java.util.List;

public class Race {
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
}
