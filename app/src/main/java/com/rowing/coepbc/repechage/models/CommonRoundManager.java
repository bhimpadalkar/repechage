package com.rowing.coepbc.repechage.models;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class CommonRoundManager {
  private Repechage repechage;

  public CommonRoundManager(Repechage repechage) {
    this.repechage = repechage;
  }

  protected List<Race> getRacesForFirstRound() {
    return getRacesSequentially(repechage.getParticipants(), "A");
  }

  protected List<Race> getRacesSequentially(List<Participant> participants, String roundName) {
    List<Race> races = new ArrayList<Race>();
    for (int i = 0; i < participants.size()/2; i++) {
      races.add(new Race(roundName+(i+1), Arrays.asList(participants.get(i*2), participants.get(i*2+1))));
    }
    return races;
  }
}
