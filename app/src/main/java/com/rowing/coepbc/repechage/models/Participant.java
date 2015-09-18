package com.rowing.coepbc.repechage.models;

import java.io.Serializable;

public class Participant implements Serializable{
  private String name;
  private boolean isEliminated;
  private int rankInRace;
  private int points = 0;
  private int chancesRemaining = 2;

  public Participant(String name) {
    this.name = name;
    this.isEliminated = false;
  }

  public String getName() {
    return name;
  }

  public int points() {
    return points;
  }

  public int chancesRemaining() {
    return chancesRemaining;
  }

  public void addPoints(int i) {
    points += i;
  }

  public void reduceChancesRemaining() {
    chancesRemaining--;
  }

  public int getRankInRace() {
    return rankInRace;
  }

  public void setRankInRace(int rankInRace) {
    this.rankInRace = rankInRace;
  }

  public boolean isEliminated() {
    return isEliminated;
  }

  public void setIsEliminated(boolean isEliminated) {
    this.isEliminated = isEliminated;
  }
}
