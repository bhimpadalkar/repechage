package com.rowing.coepbc.repechage;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

public class RoundDetailsActivity extends Activity{

  public static final String ROUND_NUMBER = "round_number";
  private RoundDetailsAdapter<Race> roundDetailsAdapter;
  private List<Race> racesForRound;
  private Repechage repechage;
  private String roundNumber;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.round_details_activity);
    repechage = (Repechage) getIntent().getSerializableExtra(MainActivity.REPECHAGE_DATA);
    roundNumber = getIntent().getStringExtra(ROUND_NUMBER);
    ((TextView)findViewById(R.id.round_title)).setText(String.format("Round %s", roundNumber));

    populateRoundDetails(repechage);
  }

  private void populateRoundDetails(Repechage repechage) {
    RoundManager roundManager = new RoundManager(repechage);
    racesForRound = roundManager.getRacesForRound();
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
    Intent intent = new Intent(this, RoundDetailsActivity.class);
    intent.putExtra(ROUND_NUMBER, "B");
    intent.putExtra(MainActivity.REPECHAGE_DATA, repechage);
    startActivity(intent);
  }
}
