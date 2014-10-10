package com.rowing.coepbc.repechage;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends Activity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
  }

  public void proceedClicked(View view) {
    EditText numberOfLanesView = (EditText) findViewById(R.id.number_of_lanes);
    int numberOfLanes = Integer.parseInt(numberOfLanesView.getText().toString());
    EditText numberOfEntriesView = (EditText) findViewById(R.id.number_of_entries);
    int numberOfEntries = Integer.parseInt(numberOfEntriesView.getText().toString());

    Repechage repechage = new Repechage(numberOfLanes, numberOfEntries);

    Intent intent = new Intent(this, ParticipantEntryActivity.class);
    intent.putExtra("repechage_data", repechage);
    startActivity(intent);
  }
}
