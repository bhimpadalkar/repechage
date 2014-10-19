package com.rowing.coepbc.repechage.models;

import java.util.List;

public class RoundManagerForThreeLanes extends CommonRoundManager implements RoundManager {

  private Repechage repechage;

  public RoundManagerForThreeLanes(Repechage repechage) {
    super(repechage);
    this.repechage = repechage;
  }

  @Override
  public List<Race> getRacesForRound(RoundType roundType) {
    return super.getRacesForFirstRound();
  }
}
