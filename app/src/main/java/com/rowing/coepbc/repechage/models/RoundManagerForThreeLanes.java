package com.rowing.coepbc.repechage.models;

public class RoundManagerForThreeLanes extends RoundManager {

  private static final int NUMBER_OF_LANES = 3;
  private Repechage repechage;

  public RoundManagerForThreeLanes(Repechage repechage) {
    super(repechage);
    this.repechage = repechage;
  }

  @Override
  public Round getRound(RoundType roundType, char roundName) {
    return new Round(super.getRacesForFirstRound(NUMBER_OF_LANES), 'A', RoundType.HEAT);
  }
}
