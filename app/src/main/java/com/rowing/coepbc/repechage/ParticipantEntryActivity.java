package com.rowing.coepbc.repechage;

import android.app.Activity;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ParticipantEntryActivity extends Activity{

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.participant_entry);
    int numberOfEntries = getIntent().getIntExtra("number_of_entries", 1);
    populateInputForEntries(numberOfEntries);
  }

  private void populateInputForEntries(int numberOfEntries) {
    LinearLayout participantsEntriesLayout = (LinearLayout) findViewById(R.id.participant_entries);
    for(int i = 0; i < numberOfEntries; i++) {
      LinearLayout singleParticipantLayout = (LinearLayout) getLayoutInflater().inflate(R.layout.single_participant_entry, null);
      TextView indexView = (TextView) singleParticipantLayout.findViewById(R.id.participant_entry_index);
      indexView.setText(String.format("%d. ",i+1));
      participantsEntriesLayout.addView(singleParticipantLayout);
    }
  }
}
