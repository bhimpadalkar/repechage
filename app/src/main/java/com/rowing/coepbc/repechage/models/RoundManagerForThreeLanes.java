package com.rowing.coepbc.repechage.models;

import static com.rowing.coepbc.repechage.models.RoundType.HEAT;

public class RoundManagerForThreeLanes extends RoundManager {

  private static final int NUMBER_OF_LANES = 3;

  public RoundManagerForThreeLanes(Repechage repechage) {
    super(repechage);
  }

  @Override
  public Round getRound(RoundType roundType, char roundName) {
    return new Round(super.getRacesForFirstRound(NUMBER_OF_LANES), 'A', HEAT);
  }

  @Override
  public RoundType decideTypeOfNextRound(RoundType typeOfCurrentRound) {
    return HEAT;
  }
}
