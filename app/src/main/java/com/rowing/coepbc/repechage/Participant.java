package com.rowing.coepbc.repechage;

import java.io.Serializable;

public class Participant implements Serializable{
  private int index;
  private String name;

  public Participant(int index, String name) {
    this.index = index;
    this.name = name;
  }
}
