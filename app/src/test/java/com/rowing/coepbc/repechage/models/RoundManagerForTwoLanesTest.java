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
    assertParticipants(racesForHeats.get(0), "A", "B");
    assertParticipants(racesForHeats.get(1), "C", "D");

    setRanks(racesForHeats.get(0), true);
    setRanks(racesForHeats.get(1), true);
    roundManagerForTwoLanes.declareResultForRound(round);

    round = roundManagerForTwoLanes.getRound(RoundType.SEMI_FINAL, 'B');
    List<Race> racesForSemiFinal = round.races;
    assertParticipants(racesForSemiFinal.get(0), "A", "D");
    assertParticipants(racesForSemiFinal.get(1), "C", "B");

    setRanks(racesForSemiFinal.get(0), false);
    setRanks(racesForSemiFinal.get(1), false);
    roundManagerForTwoLanes.declareResultForRound(round);

    round = roundManagerForTwoLanes.getRound(RoundType.FINAL, 'C');
    List < Race > racesForFinal = round.races;
    assertParticipants(racesForFinal.get(0), "D", "B");
    assertParticipants(racesForFinal.get(1), "A", "C");
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
    List<Race> racesForHeats = round.races;
    setRanks(racesForHeats.get(0), true);
    setRanks(racesForHeats.get(1), true);
    setRanks(racesForHeats.get(2), true);
    setRanks(racesForHeats.get(3), true);
    roundManagerForTwoLanes.declareResultForRound(round);

    round = roundManagerForTwoLanes.getRound(RoundType.REPECHAGE_FIRST, 'B');
    List<Race> racesForFirstRepechage = round.races;
    assertEquals(4, racesForFirstRepechage.size());
    assertParticipants(racesForFirstRepechage.get(0), "A", "C");
    assertParticipants(racesForFirstRepechage.get(1), "E", "G");
    assertParticipants(racesForFirstRepechage.get(2), "B", "D");
    assertParticipants(racesForFirstRepechage.get(3), "F", "H");
    setRanks(racesForFirstRepechage.get(0), false);
    setRanks(racesForFirstRepechage.get(1), true);
    setRanks(racesForFirstRepechage.get(2), true);
    setRanks(racesForFirstRepechage.get(3), false);
    roundManagerForTwoLanes.declareResultForRound(round);

    round = roundManagerForTwoLanes.getRound(RoundType.REPECHAGE, 'C');
    List<Race> racesForRepechage = round.races;
    assertEquals(2, racesForRepechage.size());
    assertParticipants(racesForRepechage.get(0), "B", "A");
    assertParticipants(racesForRepechage.get(1), "H", "G");
    setRanks(racesForRepechage.get(0), false);
    setRanks(racesForRepechage.get(1), true);
    roundManagerForTwoLanes.declareResultForRound(round);

    round = roundManagerForTwoLanes.getRound(RoundType.SEMI_FINAL, 'D');
    List<Race> racesForSemifinal = round.races;
    assertEquals(2, racesForSemifinal.size());
    assertParticipants(racesForSemifinal.get(0), "C", "H");
    assertParticipants(racesForSemifinal.get(1), "E", "A");
    setRanks(racesForSemifinal.get(0), true);
    setRanks(racesForSemifinal.get(1), true);
    roundManagerForTwoLanes.declareResultForRound(round);

    round = roundManagerForTwoLanes.getRound(RoundType.FINAL, 'E');
    List<Race> racesForFinal = round.races;
    assertEquals(2, racesForFinal.size());
    assertParticipants(racesForFinal.get(0), "C", "E");
    assertParticipants(racesForFinal.get(1), "H", "A");
  }

  @Test
  public void shouldReturnCorrectRacesForSixteenParticipants() {
    Repechage repechage = new Repechage(2, 16);
    Participant participantA = new Participant("A");
    Participant participantB = new Participant("B");
    Participant participantC = new Participant("C");
    Participant participantD = new Participant("D");
    Participant participantE = new Participant("E");
    Participant participantF = new Participant("F");
    Participant participantG = new Participant("G");
    Participant participantH = new Participant("H");
    Participant participantI = new Participant("I");
    Participant participantJ = new Participant("J");
    Participant participantK = new Participant("K");
    Participant participantL = new Participant("L");
    Participant participantM = new Participant("M");
    Participant participantN = new Participant("N");
    Participant participantO = new Participant("O");
    Participant participantP = new Participant("P");
    repechage.setParticipants(Arrays.asList(participantA, participantB, participantC, participantD,
        participantE, participantF, participantG, participantH,
        participantI, participantJ, participantK, participantL,
        participantM, participantN, participantO, participantP));
    RoundManagerForTwoLanes roundManagerForTwoLanes = new RoundManagerForTwoLanes(repechage);

    Round round = roundManagerForTwoLanes.getRound(RoundType.HEAT, 'A');
    List<Race> racesForHeats = round.races;
    setRanks(racesForHeats.get(0), true);
    setRanks(racesForHeats.get(1), true);
    setRanks(racesForHeats.get(2), true);
    setRanks(racesForHeats.get(3), true);
    setRanks(racesForHeats.get(4), true);
    setRanks(racesForHeats.get(5), true);
    setRanks(racesForHeats.get(6), true);
    setRanks(racesForHeats.get(7), true);
    roundManagerForTwoLanes.declareResultForRound(round);

    round = roundManagerForTwoLanes.getRound(RoundType.REPECHAGE_FIRST, 'B');
    List<Race> racesForFirstRepechage = round.races;
    assertEquals(8, racesForFirstRepechage.size());
    assertParticipants(racesForFirstRepechage.get(0), "A", "C");
    assertParticipants(racesForFirstRepechage.get(1), "E", "G");
    assertParticipants(racesForFirstRepechage.get(2), "I", "K");
    assertParticipants(racesForFirstRepechage.get(3), "M", "O");
    assertParticipants(racesForFirstRepechage.get(4), "B", "D");
    assertParticipants(racesForFirstRepechage.get(5), "F", "H");
    assertParticipants(racesForFirstRepechage.get(6), "J", "L");
    assertParticipants(racesForFirstRepechage.get(7), "N", "P");
    setRanks(racesForFirstRepechage.get(0), true);
    setRanks(racesForFirstRepechage.get(1), true);
    setRanks(racesForFirstRepechage.get(2), true);
    setRanks(racesForFirstRepechage.get(3), true);
    setRanks(racesForFirstRepechage.get(4), true);
    setRanks(racesForFirstRepechage.get(5), true);
    setRanks(racesForFirstRepechage.get(6), true);
    setRanks(racesForFirstRepechage.get(7), true);
    roundManagerForTwoLanes.declareResultForRound(round);

    round = roundManagerForTwoLanes.getRound(RoundType.REPECHAGE, 'C');
    List<Race> racesForSecondRepechage = round.races;
    assertEquals(6, racesForSecondRepechage.size());
    assertParticipants(racesForSecondRepechage.get(0), "A", "E");
    assertParticipants(racesForSecondRepechage.get(1), "I", "M");
    assertParticipants(racesForSecondRepechage.get(2), "B", "F");
    assertParticipants(racesForSecondRepechage.get(3), "J", "N");
    assertParticipants(racesForSecondRepechage.get(4), "C", "G");
    assertParticipants(racesForSecondRepechage.get(5), "K", "O");
    setRanks(racesForSecondRepechage.get(0), true);
    setRanks(racesForSecondRepechage.get(1), true);
    setRanks(racesForSecondRepechage.get(2), true);
    setRanks(racesForSecondRepechage.get(3), true);
    setRanks(racesForSecondRepechage.get(4), true);
    setRanks(racesForSecondRepechage.get(5), true);
    roundManagerForTwoLanes.declareResultForRound(round);

    round = roundManagerForTwoLanes.getRound(RoundType.REPECHAGE, 'D');
    List<Race> racesForThirdRepechage = round.races;
    assertEquals(2, racesForThirdRepechage.size());
    assertParticipants(racesForThirdRepechage.get(0), "B", "C");
    assertParticipants(racesForThirdRepechage.get(1), "J", "K");
    setRanks(racesForThirdRepechage.get(0), true);
    setRanks(racesForThirdRepechage.get(1), true);
    roundManagerForTwoLanes.declareResultForRound(round);

    round = roundManagerForTwoLanes.getRound(RoundType.REPECHAGE, 'E');
    List<Race> racesForFourthRepechage = round.races;
    assertEquals(2, racesForFourthRepechage.size());
    assertParticipants(racesForFourthRepechage.get(0), "B", "E");
    assertParticipants(racesForFourthRepechage.get(1), "J", "M");
    setRanks(racesForFourthRepechage.get(0), true);
    setRanks(racesForFourthRepechage.get(1), true);
    roundManagerForTwoLanes.declareResultForRound(round);

    round = roundManagerForTwoLanes.getRound(RoundType.SEMI_FINAL, 'F');
    List<Race> racesForSemiFinal = round.races;
    assertEquals(2, racesForSemiFinal.size());
    assertParticipants(racesForSemiFinal.get(0), "A", "J");
    assertParticipants(racesForSemiFinal.get(1), "I", "B");
    setRanks(racesForSemiFinal.get(0), true);
    setRanks(racesForSemiFinal.get(1), true);
    roundManagerForTwoLanes.declareResultForRound(round);

    round = roundManagerForTwoLanes.getRound(RoundType.FINAL, 'G');
    List<Race> racesForFinal = round.races;
    assertEquals(2, racesForFinal.size());
    assertParticipants(racesForFinal.get(0), "A", "I");
    assertParticipants(racesForFinal.get(1), "J", "B");
  }

  private void assertParticipants(Race race, String firstPlayerName, String secondPlayerName) {
    assertEquals(firstPlayerName, race.getParticipantsForRace().get(0).getName());
    assertEquals(secondPlayerName, race.getParticipantsForRace().get(1).getName());
  }

  private void setRanks(Race race, boolean isFirstPlayerWinner) {
    race.getParticipantsForRace().get(0).setRankInRace(isFirstPlayerWinner ? 0 : 1);
    race.getParticipantsForRace().get(1).setRankInRace(isFirstPlayerWinner ? 1 : 0);
  }

}