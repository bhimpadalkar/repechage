package com.rowing.coepbc.repechage.models;

import java.io.Serializable;

public class Participant implements Serializable{
  private String name;
  private ParticipantStatus raceStatus;
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

  public void reduceRemainingChances() {
    chancesRemaining--;
  }

  public int chancesRemaining() {
    return chancesRemaining;
  }
}
