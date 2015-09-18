package com.rowing.coepbc.repechage.models;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RoundManagerForTwoLanes extends CommonRoundManager implements RoundManager {

  private static final int NUMBER_OF_LANES = 2;
  private Repechage repechage;

  public RoundManagerForTwoLanes(Repechage repechage) {
    super(repechage);
    this.repechage = repechage;
  }

  @Override
  public List<Race> getRacesForRound(RoundType roundType) {
    switch (roundType){
      case HEAT:
        return getRacesForFirstRound(NUMBER_OF_LANES);
      case REPECHAGE_FIRST:
        return getRacesForFirstRepechage();
      case REPECHAGE:
        return getRacesForRepechage();
      case SEMI_FINAL:
        return getRacesForSemiFinal();
      default:
        return getRacesForFinal();
    }
  }

  private List<Race> getRacesForFirstRepechage() {
    List<Participant> participants = new ArrayList<Participant>();
    participants.addAll(repechage.getParticipantsByRank(0));
    participants.addAll(repechage.getParticipantsByRank(1));
    return getRacesSequentially(participants, "B", NUMBER_OF_LANES);
  }

  private List<Race> getRacesForRepechage() {
    List<Participant> participants = new ArrayList<Participant>();
    for (int i = repechage.getMaxNumberOfPoints(); i >= 0; i--) {
      List<Participant> participantsByPoints = repechage.getParticipantsByPoints(i);
      if(participantsByPoints != null && participantsByPoints.size() >= 4)
        participants.addAll(participantsByPoints);
    }
    if(participants.size() == 4)
      return getRacesAlternately(participants, "C");
    return getRacesSequentially(participants, "C", NUMBER_OF_LANES);
  }

  private List<Race> getRacesForSemiFinal() {
    List<Race> races = new ArrayList<Race>();
    List<Participant> participants = new ArrayList<Participant>();
    participants.addAll(repechage.getParticipantsByRank(0));
    participants.addAll(repechage.getParticipantsByRank(1));
    races.add(new Race("SF1", Arrays.asList(participants.get(0), participants.get(3))));
    races.add(new Race("SF2", Arrays.asList(participants.get(1), participants.get(2))));
    return races;
  }

  private List<Race> getRacesForFinal() {
    List<Race> races = new ArrayList<Race>();
    races.add(new Race("F1", repechage.getParticipantsByRank(0)));
    races.add(new Race("F2", repechage.getParticipantsByRank(1)));
    return races;
  }
}
