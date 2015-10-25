package com.rowing.coepbc.repechage.models;

import java.util.List;

public class Round {

  public final List<Race> races;
  public final char name;
  public final RoundType type;

  public Round(List<Race> races, char name, RoundType type) {
    this.races = races;
    this.name = name;
    this.type = type;
  }
}
