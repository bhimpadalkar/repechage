package com.rowing.coepbc.repechage.models;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class RoundManagerForTwoLanesTest {

  @Test
  public void shouldReturnCorrectRacesForFourParticipants() {
    Repechage repechage = new Repechage(2, 4);
    Participant participantA = new Participant("A");
    Participant participantB = new Participant("B");
    Participant participantC = new Participant("C");
    Participant participantD = new Participant("D");
    repechage.setParticipants(Arrays.asList(participantA, participantB, participantC, participantD));
    RoundManagerForTwoLanes roundManagerForTwoLanes = new RoundManagerForTwoLanes(repechage);

    Round round = roundManagerForTwoLanes.getRound(RoundType.HEAT, 'A');
    List<Race> racesForHeats = round.races;
    assertEquals("A", racesForHeats.get(0).getParticipantsForRace().get(0).getName());
    assertEquals("B", racesForHeats.get(0).getParticipantsForRace().get(1).getName());
    assertEquals("C", racesForHeats.get(1).getParticipantsForRace().get(0).getName());
    assertEquals("D", racesForHeats.get(1).getParticipantsForRace().get(1).getName());

    participantA.setRankInRace(0);
    participantB.setRankInRace(1);
    participantC.setRankInRace(0);
    participantD.setRankInRace(1);
    roundManagerForTwoLanes.declareResultForRound(round);

    round = roundManagerForTwoLanes.getRound(RoundType.SEMI_FINAL, 'B');
    List<Race> racesForSemiFinal = round.races;
    assertEquals("A", racesForSemiFinal.get(0).getParticipantsForRace().get(0).getName());
    assertEquals("D", racesForSemiFinal.get(0).getParticipantsForRace().get(1).getName());
    assertEquals("C", racesForSemiFinal.get(1).getParticipantsForRace().get(0).getName());
    assertEquals("B", racesForSemiFinal.get(1).getParticipantsForRace().get(1).getName());

    participantA.setRankInRace(1);
    participantD.setRankInRace(0);
    participantC.setRankInRace(1);
    participantB.setRankInRace(0);
    roundManagerForTwoLanes.declareResultForRound(round);

    round = roundManagerForTwoLanes.getRound(RoundType.FINAL, 'C');
    List < Race > racesForFinal = round.races;
    assertEquals("D", racesForFinal.get(0).getParticipantsForRace().get(0).getName());
    assertEquals("B", racesForFinal.get(0).getParticipantsForRace().get(1).getName());
    assertEquals("A", racesForFinal.get(1).getParticipantsForRace().get(0).getName());
    assertEquals("C", racesForFinal.get(1).getParticipantsForRace().get(1).getName());
  }

  @Test
  public void shouldReturnCorrectRacesForEightParticipants() {
    Repechage repechage = new Repechage(2, 8);
    Participant participantA = new Participant("A");
    Participant participantB = new Participant("B");
    Participant participantC = new Participant("C");
    Participant participantD = new Participant("D");
    Participant participantE = new Participant("E");
    Participant participantF = new Participant("F");
    Participant participantG = new Participant("G");
    Participant participantH = new Participant("H");
    repechage.setParticipants(Arrays.asList(participantA, participantB, participantC, participantD, participantE, participantF, participantG, participantH));
    RoundManagerForTwoLanes roundManagerForTwoLanes = new RoundManagerForTwoLanes(repechage);

    Round round = roundManagerForTwoLanes.getRound(RoundType.HEAT, 'A');
    participantA.setRankInRace(0);
    participantB.setRankInRace(1);
    participantC.setRankInRace(0);
    participantD.setRankInRace(1);
    participantE.setRankInRace(0);
    participantF.setRankInRace(1);
    participantG.setRankInRace(0);
    participantH.setRankInRace(1);
    roundManagerForTwoLanes.declareResultForRound(round);

    round = roundManagerForTwoLanes.getRound(RoundType.REPECHAGE_FIRST, 'B');
    List<Race> racesForFirstRepechage = round.races;
    assertEquals(4, racesForFirstRepechage.size());
    assertEquals("A", racesForFirstRepechage.get(0).getParticipantsForRace().get(0).getName());
    assertEquals("C", racesForFirstRepechage.get(0).getParticipantsForRace().get(1).getName());
    assertEquals("E", racesForFirstRepechage.get(1).getParticipantsForRace().get(0).getName());
    assertEquals("G", racesForFirstRepechage.get(1).getParticipantsForRace().get(1).getName());
    assertEquals("B", racesForFirstRepechage.get(2).getParticipantsForRace().get(0).getName());
    assertEquals("D", racesForFirstRepechage.get(2).getParticipantsForRace().get(1).getName());
    assertEquals("F", racesForFirstRepechage.get(3).getParticipantsForRace().get(0).getName());
    assertEquals("H", racesForFirstRepechage.get(3).getParticipantsForRace().get(1).getName());
    participantA.setRankInRace(1);
    participantC.setRankInRace(0);
    participantE.setRankInRace(0);
    participantG.setRankInRace(1);
    participantB.setRankInRace(0);
    participantD.setRankInRace(1);
    participantF.setRankInRace(1);
    participantH.setRankInRace(0);
    roundManagerForTwoLanes.declareResultForRound(round);

    round = roundManagerForTwoLanes.getRound(RoundType.REPECHAGE, 'C');
    List<Race> racesForRepechage = round.races;
    assertEquals(2, racesForRepechage.size());
    assertEquals("B", racesForRepechage.get(0).getParticipantsForRace().get(0).getName());
    assertEquals("A", racesForRepechage.get(0).getParticipantsForRace().get(1).getName());
    assertEquals("H", racesForRepechage.get(1).getParticipantsForRace().get(0).getName());
    assertEquals("G", racesForRepechage.get(1).getParticipantsForRace().get(1).getName());
    participantB.setRankInRace(1);
    participantA.setRankInRace(0);
    participantH.setRankInRace(0);
    participantG.setRankInRace(1);
    roundManagerForTwoLanes.declareResultForRound(round);

    round = roundManagerForTwoLanes.getRound(RoundType.SEMI_FINAL, 'D');
    List<Race> racesForSemifinal = round.races;
    assertEquals(2, racesForSemifinal.size());
    assertEquals("C", racesForSemifinal.get(0).getParticipantsForRace().get(0).getName());
    assertEquals("H", racesForSemifinal.get(0).getParticipantsForRace().get(1).getName());
    assertEquals("E", racesForSemifinal.get(1).getParticipantsForRace().get(0).getName());
    assertEquals("A", racesForSemifinal.get(1).getParticipantsForRace().get(1).getName());
    participantC.setRankInRace(0);
    participantH.setRankInRace(1);
    participantE.setRankInRace(0);
    participantA.setRankInRace(1);
    roundManagerForTwoLanes.declareResultForRound(round);

    round = roundManagerForTwoLanes.getRound(RoundType.FINAL, 'E');
    List<Race> racesForFinal = round.races;
    assertEquals(2, racesForSemifinal.size());
    assertEquals("C", racesForFinal.get(0).getParticipantsForRace().get(0).getName());
    assertEquals("E", racesForFinal.get(0).getParticipantsForRace().get(1).getName());
    assertEquals("H", racesForFinal.get(1).getParticipantsForRace().get(0).getName());
    assertEquals("A", racesForFinal.get(1).getParticipantsForRace().get(1).getName());
  }

}