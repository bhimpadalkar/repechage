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
    Intent intent = new Intent(this, ParticipantEntryActivity.class);
    intent.putExtra("number_of_lanes", numberOfLanes);
    intent.putExtra("number_of_entries", numberOfEntries);
    startActivity(intent);
  }
}
