package com.rowing.coepbc.repechage.models;

import java.io.Serializable;

public class Participant implements Serializable{
  private String name;
  private ParticipantStatus raceStatus;
  private int points = 0;
  private int chancesRemaining = 2;

  public Participant(String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }

  public void setRaceStatus(ParticipantStatus raceStatus) {
    this.raceStatus = raceStatus;
  }

  public ParticipantStatus getRaceStatus() {
    return raceStatus;
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
}
