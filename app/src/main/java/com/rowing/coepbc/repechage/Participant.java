package com.rowing.coepbc.repechage;

import java.io.Serializable;

public class Participant implements Serializable{
  private int index;
  private String name;
  private ParticipantStatus raceStatus;

  public Participant(int index, String name) {
    this.index = index;
    this.name = name;
  }

  public String getName() {
    return name;
  }

  public void setRaceStatus(ParticipantStatus raceStatus) {
    this.raceStatus = raceStatus;
  }
}
