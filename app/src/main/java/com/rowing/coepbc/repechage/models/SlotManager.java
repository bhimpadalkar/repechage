package com.rowing.coepbc.repechage.models;

import java.util.List;

public interface SlotManager {
  public List<Race> getRacesForRound(RoundType roundType);
}
