package com.rowing.coepbc.repechage;

import java.util.ArrayList;
import java.util.List;

public class RoundManager {
  private Repechage repechage;

  public RoundManager(Repechage repechage) {
    this.repechage = repechage;
  }

  public List<Race> getRacesForFirstRound() {
    List<Race> races = new ArrayList<Race>();
    int numberOfRaces = getNumberOfRaces();
    for(int i = 0 ; i < numberOfRaces ; i++){
      List<Participant> participantsForRace = new ArrayList<Participant>();
      int numberOfLanes = repechage.getNumberOfLanes();
      for(int j = 0 ; j < numberOfLanes; j++){
        Participant participant = repechage.getParticipants().get(i*numberOfLanes + j);
        participantsForRace.add(participant);
      }
      races.add(new Race(i+1, participantsForRace));
    }
    return races;
  }

  private int getNumberOfRaces() {
    return repechage.getNumberOfEntries() / repechage.getNumberOfLanes();
  }
}
