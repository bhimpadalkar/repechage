package com.rowing.coepbc.repechage;

import java.util.List;

public class SlotManagerForThreeLanes extends CommonSlotManager implements SlotManager {

  private Repechage repechage;

  public SlotManagerForThreeLanes(Repechage repechage) {
    super(repechage);
    this.repechage = repechage;
  }

  @Override
  public List<Race> getRacesForRound(RoundType roundType) {
    return super.getRacesForFirstRound();
  }
}
