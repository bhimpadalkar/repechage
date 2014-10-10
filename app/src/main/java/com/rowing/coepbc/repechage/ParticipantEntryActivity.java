package com.rowing.coepbc.repechage;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ParticipantEntryActivity extends Activity{

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.participant_entry_activity);

    Repechage repechage = getIntent().getParcelableExtra("repechage_data");
    LinearLayout participantsEntriesLayout = (LinearLayout) findViewById(R.id.participant_entry);
    populateInputForEntries(repechage.getNumberOfEntries(), participantsEntriesLayout);

    Button goForRoundOneButton = (Button) findViewById(R.id.go_for_round_button);
    goForRoundOneButton.setText(String.format("%s %d",getString(R.string.go_for_round),1));
  }

  private void populateInputForEntries(int numberOfEntries, LinearLayout participantsEntriesLayout) {
    for(int i = 0; i < numberOfEntries; i++) {
      LinearLayout singleParticipantLayout = (LinearLayout) getLayoutInflater().inflate(R.layout.single_participant_entry, null);
      TextView indexView = (TextView) singleParticipantLayout.findViewById(R.id.participant_entry_index);
      indexView.setText(String.format("%d. ",i+1));
      participantsEntriesLayout.addView(singleParticipantLayout);
    }
  }
}
