package com.rowing.coepbc.repechage;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class ParticipantEntryActivity extends Activity{

  private Repechage repechage;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.participant_entry_activity);

    repechage = getIntent().getParcelableExtra("repechage_data");
    populateInputForEntries();

    Button goForRoundOneButton = (Button) findViewById(R.id.go_for_round_button);
    goForRoundOneButton.setText(String.format("%s %d",getString(R.string.go_for_round),1));
  }

  private void populateInputForEntries() {
    List<String> participantsIndices = new ArrayList<String>();

    for(int i = 0; i < repechage.getNumberOfEntries(); i++) {
      participantsIndices.add(String.format("%d. ", i + 1));
    }

    ListView participantsEntriesView = (ListView) findViewById(R.id.participant_entry);
    participantsEntriesView.setAdapter(new ParticipantsArrayAdapter<String>(R.layout.single_participant_entry, participantsIndices));
  }

  public void showRacesForRound(View view) {

  }
}
