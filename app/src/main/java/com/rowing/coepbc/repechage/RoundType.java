package com.rowing.coepbc.repechage;

public enum RoundType {
  HEAT("HEATS"), REPECHAGE("REPECHAGE"), SEMI_FINAL("SEMI-FINAL"), FINAL("FINAL");
  private String name;

  RoundType(String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }
}
