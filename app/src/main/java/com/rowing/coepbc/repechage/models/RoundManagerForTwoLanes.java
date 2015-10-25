package com.rowing.coepbc.repechage.models;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.rowing.coepbc.repechage.models.RoundType.FINAL;
import static com.rowing.coepbc.repechage.models.RoundType.REPECHAGE;
import static com.rowing.coepbc.repechage.models.RoundType.REPECHAGE_FIRST;
import static com.rowing.coepbc.repechage.models.RoundType.SEMI_FINAL;

public class RoundManagerForTwoLanes extends RoundManager {

  private static final int NUMBER_OF_LANES = 2;

  public RoundManagerForTwoLanes(Repechage repechage) {
    super(repechage);
  }

  @Override
  public Round getRound(RoundType roundType, char roundName) {
    switch (roundType){
      case HEAT:
        return new Round(getRacesForFirstRound(NUMBER_OF_LANES), roundName, RoundType.HEAT);
      case REPECHAGE_FIRST:
        return new Round(getRacesForFirstRepechage(), roundName, RoundType.REPECHAGE_FIRST);
      case REPECHAGE:
        return new Round(getRacesForRepechage(), roundName, RoundType.REPECHAGE);
      case SEMI_FINAL:
        return new Round(getRacesForSemiFinal(), roundName, RoundType.SEMI_FINAL);
      default:
        return new Round(getRacesForFinal(), roundName, RoundType.FINAL);
    }
  }

  @Override
  public RoundType decideTypeOfNextRound(RoundType typeOfCurrentRound) {
    switch (typeOfCurrentRound){
      case HEAT:
        return repechage.getNumberOfParticipantsRemaining() == 4 ? SEMI_FINAL : REPECHAGE_FIRST;
      case REPECHAGE_FIRST:
      case REPECHAGE:
        return repechage.getNumberOfParticipantsRemaining() == 4 ? SEMI_FINAL : REPECHAGE;
      default:
        return FINAL;
    }
  }

  private List<Race> getRacesForFirstRepechage() {
    List<Participant> participants = new ArrayList<Participant>();
    participants.addAll(repechage.getParticipantsByRank(0));
    participants.addAll(repechage.getParticipantsByRank(1));
    return getRacesSequentially(participants, NUMBER_OF_LANES);
  }

  private List<Race> getRacesForRepechage() {
    List<Participant> participants = new ArrayList<Participant>();
    for (int i = repechage.getMaxNumberOfPoints(); i >= 0; i--) {
      List<Participant> participantsByPoints = repechage.getParticipantsByPoints(i);
      if(participantsByPoints != null && participantsByPoints.size() >= 4)
        participants.addAll(participantsByPoints);
    }
    if(participants.size() == 4)
      return getRacesAlternately(participants);
    return getRacesSequentially(participants, NUMBER_OF_LANES);
  }

  private List<Race> getRacesForSemiFinal() {
    List<Race> races = new ArrayList<Race>();
    List<Participant> participants = new ArrayList<Participant>();
    participants.addAll(repechage.getParticipantsByRank(0));
    participants.addAll(repechage.getParticipantsByRank(1));
    races.add(new Race(Arrays.asList(participants.get(0), participants.get(3))));
    races.add(new Race(Arrays.asList(participants.get(1), participants.get(2))));
    return races;
  }

  private List<Race> getRacesForFinal() {
    List<Race> races = new ArrayList<Race>();
    races.add(new Race(repechage.getParticipantsByRank(0)));
    races.add(new Race(repechage.getParticipantsByRank(1)));
    return races;
  }
}
