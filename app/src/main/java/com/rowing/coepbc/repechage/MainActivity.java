package com.rowing.coepbc.repechage;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.rowing.coepbc.repechage.models.Repechage;

public class MainActivity extends Activity {

  public static final String REPECHAGE_DATA = "repechage_data";
  public static final String MAX_ENTRIES_WARNING = "Time trials should be taken to select first ";

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
  }

  public void proceedClicked(View view) {
    String numberOfLanesText = ((EditText) findViewById(R.id.number_of_lanes)).getText().toString();
    String numberOfEntriesText = ((EditText) findViewById(R.id.number_of_entries)).getText().toString();
    if(numberOfLanesText.equals("") || numberOfEntriesText.equals("")){
      RepechageUIUtils.showErrorDialog(this, R.string.empty_lanes_entries_data);
      return;
    }
    int numberOfLanes = Integer.parseInt(numberOfLanesText);
    int numberOfEntries = Integer.parseInt(numberOfEntriesText);
    validate(numberOfLanes, numberOfEntries);
  }

  private void validate(int numberOfLanes, int numberOfEntries) {
    if(numberOfLanes < 2 || numberOfLanes > 4) {
      RepechageUIUtils.showErrorDialog(this, R.string.invalid_lanes_error);
      return;
    }
    switch (numberOfLanes){
      case 2:
        promptMaxEntries(numberOfLanes, numberOfEntries, Repechage.MAX_ENTRIES_2_LANES);
        break;
      case 3:
        promptMaxEntries(numberOfLanes, numberOfEntries, Repechage.MAX_ENTRIES_3_LANES);
        break;
      case 4:
        promptMaxEntries(numberOfLanes, numberOfEntries, Repechage.MAX_ENTRIES_4_LANES);
        break;
    }
  }

  private void promptMaxEntries(final int numberOfLanes, final int numberOfEntries, int maxEntries) {
    if(numberOfEntries > maxEntries){
      RepechageUIUtils.showDialogWithCallback(this, String.format(MAX_ENTRIES_WARNING + maxEntries), new DialogCallBack() {
        @Override
        public void onSuccess() {
          startRounds(numberOfLanes, numberOfEntries);
        }
      });
    }
    else startRounds(numberOfLanes, numberOfEntries);
  }

  private void startRounds(int numberOfLanes, int numberOfEntries) {
    Repechage repechage = new Repechage(numberOfLanes, numberOfEntries);

    Intent intent = new Intent(this, ParticipantEntryActivity.class);
    intent.putExtra(REPECHAGE_DATA, repechage);
    startActivity(intent);
  }
}
