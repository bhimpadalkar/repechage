package com.rowing.coepbc.repechage.models;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RoundManagerForTwoLanes extends CommonRoundManager implements RoundManager {

  private Repechage repechage;

  public RoundManagerForTwoLanes(Repechage repechage) {
    super(repechage);
    this.repechage = repechage;
  }

  @Override
  public List<Race> getRacesForRound(RoundType roundType) {
    switch (roundType){
      case HEAT:
        return getRacesForFirstRound();
      case REPECHAGE:
        return getRacesForRepechage();
      case SEMI_FINAL:
        return getRacesForSemiFinal();
      default:
        return getRacesForFinal();
    }
  }

  private List<Race> getRacesForFinal() {
    List<Race> races = new ArrayList<Race>();
    races.add(new Race("F1", repechage.getWinners()));
    races.add(new Race("F2", repechage.getLosers()));
    return races;
  }

  private List<Race> getRacesForRepechage() {
    return getRacesForSemiFinal();
  }

  private List<Race> getRacesForSemiFinal() {
    List<Race> races = new ArrayList<Race>();
    List<Participant> participants = new ArrayList<Participant>();
    participants.addAll(repechage.getWinners());
    participants.addAll(repechage.getLosers());
    races.add(new Race("SF1", Arrays.asList(participants.get(0), participants.get(3))));
    races.add(new Race("SF2", Arrays.asList(participants.get(1), participants.get(2))));
    return races;
  }

  public List<Race> getRacesForFirstRound() {
    return super.getRacesForFirstRound();
  }
}
