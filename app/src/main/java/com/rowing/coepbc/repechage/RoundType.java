package com.rowing.coepbc.repechage;

public enum RoundType {
  HEAT("HEATS"), REPECHAGE("REPECHAGE");
  private String name;

  RoundType(String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }
}
