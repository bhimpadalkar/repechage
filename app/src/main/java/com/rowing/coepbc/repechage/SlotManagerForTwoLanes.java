package com.rowing.coepbc.repechage;

import java.util.ArrayList;
import java.util.List;

public class SlotManagerForTwoLanes extends CommonSlotManager implements SlotManager{

  private Repechage repechage;

  public SlotManagerForTwoLanes(Repechage repechage) {
    super(repechage);
    this.repechage = repechage;
  }

  @Override
  public List<Race> getRacesForRound(RoundType roundType) {
    if (roundType.equals(RoundType.HEAT))
      return getRacesForFirstRound();
    else return getRacesForRepechage();
  }

  private List<Race> getRacesForRepechage() {
    List<Race> races = new ArrayList<Race>();
    int numberOfRaces = getNumberOfRaces();
    List<Race> racesPlayedInLastRound = repechage.getRacesPlayedInLastRound();
    for (int i = 0; i < numberOfRaces; i++) {
      Race raceToSelectLastWinner = racesPlayedInLastRound.get(i);
      Participant winnerInLastRound = raceToSelectLastWinner.getWinner();

      Race raceToSelectLastLosers = racesPlayedInLastRound.get((i + 1) % numberOfRaces);
      List<Participant> losersInLastRound = raceToSelectLastLosers.getLosers();
      List<Participant> participantsForRace = new ArrayList<Participant>();
      participantsForRace.add(winnerInLastRound);
      for (Participant participant : losersInLastRound) {
        participantsForRace.add(participant);
      }
      races.add(new Race(i+1, participantsForRace));
    }
    return races;
  }

  public List<Race> getRacesForFirstRound() {
    return super.getRacesForFirstRound();
  }
}
