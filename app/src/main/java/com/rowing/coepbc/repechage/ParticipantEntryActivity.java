package com.rowing.coepbc.repechage;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

public class ParticipantEntryActivity extends Activity{

  private Repechage repechage;
  private ListView participantsEntriesView;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.participant_entry_activity);

    repechage = (Repechage) getIntent().getSerializableExtra(MainActivity.REPECHAGE_DATA);
    populateInputForEntries();
  }

  private void populateInputForEntries() {
    participantsEntriesView = (ListView) findViewById(R.id.participant_entry);
    participantsEntriesView.setAdapter(new ParticipantsArrayAdapter<String>(this, R.layout.single_participant_entry, repechage.getNumberOfEntries()));
  }

  public void showRaces(View view) {
    for(int i = 0 ; i < repechage.getNumberOfEntries() ; i++) {
      Participant participant = (Participant) participantsEntriesView.getAdapter().getItem(i);
      if(participant.getName().equals("")){
        RepechageUIUtils.showErrorDialog(this, R.string.participant_name_error);
        return;
      }
      repechage.addParticipant(participant);
    }
    Intent intent = new Intent(this, RoundDetailsActivity.class);
    intent.putExtra(MainActivity.REPECHAGE_DATA, repechage);
    startActivity(intent);
  }
}
