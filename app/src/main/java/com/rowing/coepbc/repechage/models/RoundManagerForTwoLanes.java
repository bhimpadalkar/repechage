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
    participants.addAll(repechage.getParticipantsByStatus(ParticipantStatus.WINNER));
    participants.addAll(repechage.getParticipantsByStatus(ParticipantStatus.LOSER));
    return getRacesSequentially(participants, "B");
  }

  private List<Race> getRacesForRepechage() {
    List<Participant> participants = new ArrayList<Participant>();
    for (int i = repechage.getMaxNumberOfPoints(); i > 0; i--) {
       participants.addAll(repechage.getParticipantsByPoints(i));
    }
    return getRacesSequentially(participants, "C");
  }

  private List<Race> getRacesForSemiFinal() {
    List<Race> races = new ArrayList<Race>();
    List<Participant> participants = new ArrayList<Participant>();
    participants.addAll(repechage.getParticipantsByStatus(ParticipantStatus.WINNER));
    participants.addAll(repechage.getParticipantsByStatus(ParticipantStatus.LOSER));
    races.add(new Race("SF1", Arrays.asList(participants.get(0), participants.get(3))));
    races.add(new Race("SF2", Arrays.asList(participants.get(1), participants.get(2))));
    return races;
  }

  private List<Race> getRacesForFinal() {
    List<Race> races = new ArrayList<Race>();
    races.add(new Race("F1", repechage.getParticipantsByStatus(ParticipantStatus.WINNER)));
    races.add(new Race("F2", repechage.getParticipantsByStatus(ParticipantStatus.LOSER)));
    return races;
  }
}
