package com.rowing.coepbc.repechage.models;

import java.util.List;

public class RoundManager {
  private SlotManager slotManager;

  public RoundManager(Repechage repechage) {
    decideRoundManagerType(repechage);
  }

  private void decideRoundManagerType(Repechage repechage) {
    switch (repechage.getNumberOfLanes()){
      case 2:
        this.slotManager = new SlotManagerForTwoLanes(repechage);
        break;
      case 3:
        this.slotManager = new SlotManagerForThreeLanes(repechage);
        break;
      case 4:
        this.slotManager = new SlotManagerForFourLanes(repechage);
        break;
    }
  }

  public List<Race> getRacesForRound(RoundType roundType) {
    return slotManager.getRacesForRound(roundType);
  }
}
