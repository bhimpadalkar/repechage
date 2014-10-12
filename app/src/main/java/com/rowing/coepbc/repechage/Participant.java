package com.rowing.coepbc.repechage;

import java.io.Serializable;

public class Participant implements Serializable{
  private String name;
  private ParticipantStatus raceStatus;

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
}
