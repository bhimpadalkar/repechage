package com.rowing.coepbc.repechage;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.rowing.coepbc.repechage.models.Participant;
import com.rowing.coepbc.repechage.models.Repechage;
import com.rowing.coepbc.repechage.models.RoundType;

import java.util.ArrayList;
import java.util.List;

public class ParticipantEntryActivity extends Activity{

  private Repechage repechage;
  private ParticipantsArrayAdapter<String> participantsArrayAdapter;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.participant_entry_activity);

    ListView roundDetailsView = (ListView) findViewById(R.id.participant_entry);
    View footerView = getLayoutInflater().inflate(R.layout.proceed_button, null);
    roundDetailsView.addFooterView(footerView);

    repechage = (Repechage) getIntent().getSerializableExtra(MainActivity.REPECHAGE_DATA);
    populateInputForEntries();
  }

  private void populateInputForEntries() {
    ListView participantsEntriesView = (ListView) findViewById(R.id.participant_entry);
    participantsArrayAdapter = new ParticipantsArrayAdapter<String>(this, R.layout.single_participant_entry, repechage.getNumberOfEntries(), participantsEntriesView);
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
