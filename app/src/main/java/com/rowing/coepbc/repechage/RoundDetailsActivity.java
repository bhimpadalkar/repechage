package com.rowing.coepbc.repechage;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

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
    repechage = (Repechage) getIntent().getSerializableExtra(MainActivity.REPECHAGE_DATA);
    roundType = (RoundType) getIntent().getSerializableExtra(ROUND_TYPE);
    setRoundTitle();
    populateRoundDetails(repechage);
  }

  private void setRoundTitle() {
    ((TextView)findViewById(R.id.round_title)).setText(roundType.getName());
  }

  private void populateRoundDetails(Repechage repechage) {
    RoundManager roundManager = new RoundManager(repechage);
    racesForRound = roundManager.getRacesForRound(roundType);
    ListView roundDetailsView = (ListView) findViewById(R.id.round_detail);
    roundDetailsAdapter = new RoundDetailsAdapter<Race>(this, R.layout.race_detail_layout, racesForRound);
    roundDetailsView.setAdapter(roundDetailsAdapter);
  }

  public void proceedForNextRound(View view) {
    for (int i = 0; i < racesForRound.size(); i++) {
      Race race = racesForRound.get(i);
      String winnerName = (String) roundDetailsAdapter.getItem(i);
      Participant winner = repechage.getParticipantByName(winnerName);
      if(winner != null){
        race.declareResult(winner);
      }
    }
    repechage.storeRacesPlayedInLastRound(racesForRound);
    Intent intent = new Intent(this, RoundDetailsActivity.class);
    intent.putExtra(MainActivity.REPECHAGE_DATA, repechage);
    intent.putExtra(ROUND_TYPE, RoundType.REPECHAGE);
    startActivity(intent);
  }
}
