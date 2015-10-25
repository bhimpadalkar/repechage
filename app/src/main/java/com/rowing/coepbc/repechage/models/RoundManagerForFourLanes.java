package com.rowing.coepbc.repechage.models;

public class RoundManagerForFourLanes extends RoundManager {

  public static final int NUMBER_OF_LANES = 4;
  private Repechage repechage;

  public RoundManagerForFourLanes(Repechage repechage) {
    super(repechage);
    this.repechage = repechage;
  }

  @Override
  public Round getRound(RoundType roundType, char roundName) {
    return new Round(super.getRacesForFirstRound(NUMBER_OF_LANES), 'A', RoundType.HEAT);
  }
}
