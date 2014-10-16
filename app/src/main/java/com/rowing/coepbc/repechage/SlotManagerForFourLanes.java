package com.rowing.coepbc.repechage;

import java.util.List;

public class SlotManagerForFourLanes extends CommonSlotManager implements SlotManager {

  private Repechage repechage;

  public SlotManagerForFourLanes(Repechage repechage) {
    super(repechage);
    this.repechage = repechage;
  }

  @Override
  public List<Race> getRacesForRound(RoundType roundType) {
    return super.getRacesForFirstRound();
  }
}
