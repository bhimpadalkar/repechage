package com.rowing.coepbc.repechage.models;

import java.util.List;

public class RoundManagerForThreeLanes extends RoundManager {

  private static final int NUMBER_OF_LANES = 3;
  private Repechage repechage;

  public RoundManagerForThreeLanes(Repechage repechage) {
    super(repechage);
    this.repechage = repechage;
  }

  @Override
  public List<Race> getRacesForRound(RoundType roundType) {
    return super.getRacesForFirstRound(NUMBER_OF_LANES);
  }
}
