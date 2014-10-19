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
import static com.rowing.coepbc.repechage.models.RoundType.SEMI_FINAL;

public class RoundDetailsActivity extends Activity{

  public static final String ROUND_TYPE = "round_type";
  private RoundDetailsAdapter<Race> roundDetailsAdapter;
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
    roundDetailsAdapter = new RoundDetailsAdapter<Race>(this, R.layout.race_detail_layout, racesForRound);
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
    if(roundType == FINAL) return;
    for (int i = 0; i < racesForRound.size(); i++) {
      Race race = racesForRound.get(i);
      String winnerName = (String) roundDetailsAdapter.getItem(i);
      Participant winner = repechage.getParticipantByName(winnerName);
      if(winner != null){
        race.declareResult(winner);
      }
    }
    Intent intent = new Intent(this, RoundDetailsActivity.class);
    intent.putExtra(MainActivity.REPECHAGE_DATA, repechage);
    RoundType typeOfNextRound;
    switch (roundType){
      case HEAT:
      case REPECHAGE:
        if(repechage.getNumberOfParticipantsRemaining() == 4) typeOfNextRound = SEMI_FINAL;
        else typeOfNextRound = REPECHAGE;
        break;
      default:
        typeOfNextRound = FINAL;
    }
    intent.putExtra(ROUND_TYPE, typeOfNextRound);
    startActivity(intent);
  }
}
