package com.rowing.coepbc.repechage.models;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.rowing.coepbc.repechage.models.RoundType.REPECHAGE;
import static com.rowing.coepbc.repechage.models.RoundType.REPECHAGE_FIRST;

public abstract class RoundManager {
  private Repechage repechage;

  public RoundManager(Repechage repechage) {
    this.repechage = repechage;
  }

  public abstract Round getRound(RoundType roundType, char roundName);

  protected List<Race> getRacesForFirstRound(int numberOfLanes) {
    return getRacesSequentially(repechage.getParticipants(), numberOfLanes);
  }

  protected List<Race> getRacesSequentially(List<Participant> participants, int numberOfLanes) {
    List<Race> races = new ArrayList<Race>();
    for (int i = 0; i < participants.size() / numberOfLanes; i++) {
      List<Participant> participantsForRace = new ArrayList<Participant>();
      for (int j = 0; j < numberOfLanes; j++) {
        participantsForRace.add(participants.get(i * numberOfLanes + j));
      }
      races.add(new Race(participantsForRace));
    }
    return races;
  }

  protected List<Race> getRacesAlternately(List<Participant> participants) {
    List<Race> races = new ArrayList<Race>();
    for (int i = 0; i < participants.size() / 2; i++) {
      races.add(new Race(Arrays.asList(participants.get(i), participants.get(i + 2))));
    }
    return races;
  }

  public void declareResultForRound(Round round) {
    for (int i = 0; i < round.races.size(); i++) {
      Race race = round.races.get(i);
      race.declareResult(round.type);
      race.updateLastPlayedRaceName(round.name, i);
    }
    if((round.type == REPECHAGE_FIRST) || (round.type == REPECHAGE)) repechage.eliminateLosers();
    repechage.updateParticipants();
  }
}
