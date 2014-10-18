package com.rowing.coepbc.repechage;

import java.util.ArrayList;
import java.util.List;

public class CommonSlotManager {
  private Repechage repechage;

  public CommonSlotManager(Repechage repechage) {
    this.repechage = repechage;
  }

  protected int getNumberOfRaces() {
    return repechage.getNumberOfParticipantsRemaining() / repechage.getNumberOfLanes();
  }

  protected List<Race> getRacesForFirstRound() {
    List<Race> races = new ArrayList<Race>();
    int numberOfRaces = getNumberOfRaces();
    for (int i = 0; i < numberOfRaces; i++) {
      List<Participant> participantsForRace = new ArrayList<Participant>();
      int numberOfLanes = repechage.getNumberOfLanes();
      for (int j = 0; j < numberOfLanes; j++) {
        Participant participant = repechage.getParticipants().get(i * numberOfLanes + j);
        participantsForRace.add(participant);
      }
      races.add(new Race(String.format("A%d", i+1), participantsForRace));
    }
    return races;
  }
}
