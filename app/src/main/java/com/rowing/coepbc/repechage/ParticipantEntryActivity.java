package com.rowing.coepbc.repechage;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class ParticipantEntryActivity extends Activity{

  private Repechage repechage;
  private ListView participantsEntriesView;
  private ParticipantsArrayAdapter<String> participantsArrayAdapter;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.participant_entry_activity);

    repechage = (Repechage) getIntent().getSerializableExtra(MainActivity.REPECHAGE_DATA);
    populateInputForEntries();
  }

  private void populateInputForEntries() {
    participantsEntriesView = (ListView) findViewById(R.id.participant_entry);
    participantsArrayAdapter = new ParticipantsArrayAdapter<String>(this, R.layout.single_participant_entry, repechage.getNumberOfEntries());
    participantsEntriesView.setAdapter(participantsArrayAdapter);
  }

  public void showRaces(View view) {
    List<Participant> participantsToAdd = new ArrayList<Participant>();
    for(int i = 0 ; i < repechage.getNumberOfEntries() ; i++) {
      Participant participant = (Participant) participantsArrayAdapter.getItem(i);
      if(participant.getName().equals("")){
        RepechageUIUtils.showErrorDialog(this, R.string.participant_name_error);
        return;
      }
      participantsToAdd.add(participant);
    }
    repechage.setParticipants(participantsToAdd);
    Intent intent = new Intent(this, RoundDetailsActivity.class);
    intent.putExtra(MainActivity.REPECHAGE_DATA, repechage);
    intent.putExtra(RoundDetailsActivity.ROUND_TYPE, RoundType.HEAT);
    startActivity(intent);
  }
}
