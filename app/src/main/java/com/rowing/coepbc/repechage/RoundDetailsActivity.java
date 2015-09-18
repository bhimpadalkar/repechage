package com.rowing.coepbc.repechage;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.rowing.coepbc.repechage.models.Participant;
import com.rowing.coepbc.repechage.models.Race;
import com.rowing.coepbc.repechage.models.Repechage;
import com.rowing.coepbc.repechage.models.RoundManager;
import com.rowing.coepbc.repechage.models.RoundManagerForFourLanes;
import com.rowing.coepbc.repechage.models.RoundManagerForThreeLanes;
import com.rowing.coepbc.repechage.models.RoundManagerForTwoLanes;
import com.rowing.coepbc.repechage.models.RoundType;

import java.util.List;

import static com.rowing.coepbc.repechage.models.RoundType.FINAL;
import static com.rowing.coepbc.repechage.models.RoundType.REPECHAGE;
import static com.rowing.coepbc.repechage.models.RoundType.REPECHAGE_FIRST;
import static com.rowing.coepbc.repechage.models.RoundType.SEMI_FINAL;

public class RoundDetailsActivity extends Activity{

  public static final String ROUND_TYPE = "round_type";
  public static final String GOLD_WINNER = "GOLD_WINNER";
  public static final String SILVER_WINNER = "SILVER_WINNER";
  public static final String BRONZE_WINNER = "BRONZE_WINNER";
  private RoundDetailsAdapter roundDetailsAdapter;
  private List<Race> racesForRound;
  private Repechage repechage;
  private RoundType roundType;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.round_details_activity);
    ListView roundDetailsView = (ListView) findViewById(R.id.round_detail);
    View footerView = getLayoutInflater().inflate(R.layout.next_button, null);
    roundDetailsView.addFooterView(footerView);

    repechage = (Repechage) getIntent().getSerializableExtra(MainActivity.REPECHAGE_DATA);
    roundType = (RoundType) getIntent().getSerializableExtra(ROUND_TYPE);
    setRoundTitle();
    populateRoundDetails();
  }

  private void setRoundTitle() {
    ((TextView)findViewById(R.id.round_title)).setText(roundType.getName());
  }

  private void populateRoundDetails() {
    RoundManager roundManager = decideRoundManagerType();
    racesForRound = roundManager.getRacesForRound(roundType);
    ListView roundDetailsView = (ListView) findViewById(R.id.round_detail);
    roundDetailsAdapter = new RoundDetailsAdapter(this, R.layout.race_detail_layout, racesForRound, repechage.getNumberOfLanes());
    roundDetailsView.setAdapter(roundDetailsAdapter);
  }

  private RoundManager decideRoundManagerType() {
    switch (repechage.getNumberOfLanes()){
      case 2:
        return new RoundManagerForTwoLanes(repechage);
      case 3:
        return new RoundManagerForThreeLanes(repechage);
      default:
        return new RoundManagerForFourLanes(repechage);
    }
  }

  public void proceedForNextRound(View view) {
    saveRaceResult();
    if(roundType == FINAL){
      declareFinalResult();
      return;
    }

    RoundType typeOfNextRound;
    switch (roundType){
      case HEAT:
        if(repechage.getNumberOfParticipantsRemaining() == 4) typeOfNextRound = SEMI_FINAL;
        else typeOfNextRound = REPECHAGE_FIRST;
        break;
      case REPECHAGE_FIRST:
      case REPECHAGE:
        if(repechage.getNumberOfParticipantsRemaining() == 4) typeOfNextRound = SEMI_FINAL;
        else typeOfNextRound = REPECHAGE;
        break;
      default:
        typeOfNextRound = FINAL;
    }
    Intent intent = new Intent(this, RoundDetailsActivity.class);
    intent.putExtra(MainActivity.REPECHAGE_DATA, repechage);
    intent.putExtra(ROUND_TYPE, typeOfNextRound);
    startActivity(intent);
  }

  private void saveRaceResult() {
    for (Race race : racesForRound) {
      race.declareResult(roundType);
    }
    if((roundType == REPECHAGE_FIRST) || (roundType == REPECHAGE)) repechage.eliminateLosers();
    repechage.updateParticipants();
  }

  private void declareFinalResult() {
    Race raceForGold = racesForRound.get(0);
    Race raceForBronze = racesForRound.get(1);
    Participant goldWinner = raceForGold.getWinner();
    Participant silverWinner = raceForGold.getLoser();
    Participant bronzeWinner = raceForBronze.getWinner();

    Intent intent = new Intent(this, FinalResultActivity.class);
    intent.putExtra(GOLD_WINNER, goldWinner);
    intent.putExtra(SILVER_WINNER, silverWinner);
    intent.putExtra(BRONZE_WINNER, bronzeWinner);
    startActivity(intent);
  }
}
