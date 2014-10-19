package com.rowing.coepbc.repechage.models;

import java.io.Serializable;

public class Participant implements Serializable{
  private String name;
  private ParticipantStatus raceStatus;
  private int points = 0;

  public Participant(String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }

  public void setRaceStatus(ParticipantStatus raceStatus) {
    this.raceStatus = raceStatus;
    if(raceStatus == ParticipantStatus.WINNER) points++;
    else points--;
  }

  public ParticipantStatus getRaceStatus() {
    return raceStatus;
  }

  public int points() {
    return points;
  }
}
