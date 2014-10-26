package com.rowing.coepbc.repechage.models;

import java.util.List;

public class RoundManagerForFourLanes extends CommonRoundManager implements RoundManager {

  public static final int NUMBER_OF_LANES = 4;
  private Repechage repechage;

  public RoundManagerForFourLanes(Repechage repechage) {
    super(repechage);
    this.repechage = repechage;
  }

  @Override
  public List<Race> getRacesForRound(RoundType roundType) {
    return super.getRacesForFirstRound(NUMBER_OF_LANES);
  }
}
