package com.rowing.coepbc.repechage.models;

import java.util.List;

public interface RoundManager {
  public List<Race> getRacesForRound(RoundType roundType);
}
