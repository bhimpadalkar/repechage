package com.rowing.coepbc.repechage;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.rowing.coepbc.repechage.models.Participant;

public class FinalResultActivity extends Activity{
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.final_result_activity);
    Participant goldWinner = (Participant) getIntent().getSerializableExtra(RoundDetailsActivity.GOLD_WINNER);
    Participant silverWinner = (Participant) getIntent().getSerializableExtra(RoundDetailsActivity.SILVER_WINNER);
    Participant bronzeWinner = (Participant) getIntent().getSerializableExtra(RoundDetailsActivity.BRONZE_WINNER);
    ((TextView)findViewById(R.id.gold_winner)).setText(goldWinner.getName());
    ((TextView)findViewById(R.id.silver_winner)).setText(silverWinner.getName());
    ((TextView)findViewById(R.id.bronze_winner)).setText(bronzeWinner.getName());
  }

  public void startNew(View view) {
    Intent intent = new Intent(this, MainActivity.class);
    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
    startActivity(intent);
  }
}
