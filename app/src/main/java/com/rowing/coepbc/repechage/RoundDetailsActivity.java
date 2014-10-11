package com.rowing.coepbc.repechage;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.List;

public class RoundDetailsActivity extends Activity{

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.round_details_activity);
    Repechage repechage = (Repechage) getIntent().getSerializableExtra(MainActivity.REPECHAGE_DATA);
    populateRoundDetails(repechage);
  }

  private void populateRoundDetails(Repechage repechage) {
    RoundManager roundManager = new RoundManager(repechage);
    List<Race> racesForFirstRound = roundManager.getRacesForFirstRound();
    ListView roundDetailsView = (ListView) findViewById(R.id.round_detail);
    roundDetailsView.setAdapter(new RoundDetailsAdapter<Race>(this, R.layout.race_detail_layout, racesForFirstRound));
  }
}
