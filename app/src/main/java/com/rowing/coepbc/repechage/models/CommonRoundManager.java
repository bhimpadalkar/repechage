package com.rowing.coepbc.repechage.models;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class CommonRoundManager {
  private Repechage repechage;

  public CommonRoundManager(Repechage repechage) {
    this.repechage = repechage;
  }

  protected List<Race> getRacesForFirstRound(int numberOfLanes) {
    return getRacesSequentially(repechage.getParticipants(), "A", numberOfLanes);
  }

  protected List<Race> getRacesSequentially(List<Participant> participants, String roundName, int numberOfLanes) {
    List<Race> races = new ArrayList<Race>();
    for (int i = 0; i < participants.size() / numberOfLanes; i++) {
      List<Participant> participantsForRace = new ArrayList<Participant>();
      for (int j = 0; j < numberOfLanes; j++) {
        participantsForRace.add(participants.get(i * numberOfLanes + j));
      }
      races.add(new Race(roundName + (i + 1), participantsForRace));
    }
    return races;
  }

  protected List<Race> getRacesAlternately(List<Participant> participants, String roundName) {
    List<Race> races = new ArrayList<Race>();
    for (int i = 0; i < participants.size() / 2; i++) {
      races.add(new Race(roundName + (i + 1), Arrays.asList(participants.get(i), participants.get(i + 2))));
    }
    return races;
  }
}
