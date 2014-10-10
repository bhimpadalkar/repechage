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
  }

  private void populateInputForEntries() {
    ListView participantsEntriesView = (ListView) findViewById(R.id.participant_entry);
    participantsEntriesView.setAdapter(new ParticipantsArrayAdapter<String>(this, R.layout.single_participant_entry, repechage.getNumberOfEntries()));
  }
}
