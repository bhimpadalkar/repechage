package com.rowing.coepbc.repechage;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.rowing.coepbc.repechage.models.Participant;
import com.rowing.coepbc.repechage.models.Race;

import java.util.ArrayList;
import java.util.List;

public class RoundDetailsAdapter extends BaseAdapter {
  private Context context;
  private int race_detail_layout;
  private List<Race> races;
  private int numberOfLanes;
  private List<String> winnersList = new ArrayList<String>();

  public RoundDetailsAdapter(Context context, int race_detail_layout, List<Race> races, int numberOfLanes) {
    this.context = context;
    this.race_detail_layout = race_detail_layout;
    this.races = races;
    this.numberOfLanes = numberOfLanes;
    for (Race race : races) {
      winnersList.add(race.getParticipantsForRace().get(0).getName());
    }

  }

  @Override
  public int getCount() {
    return races.size();
  }

  @Override
  public Object getItem(int position) {
    return winnersList.get(position);
  }

  @Override
  public long getItemId(int position) {
    return 0;
  }

  @Override
  public View getView(final int position, View convertView, ViewGroup parent) {
    LayoutInflater layoutInflater = ((Activity) parent.getContext()).getLayoutInflater();
    convertView = layoutInflater
          .inflate(race_detail_layout, null);
    ((TextView)convertView.findViewById(R.id.race_number)).setText("Race " + races.get(position).getRaceID());

    List<String> nameOfParticipants = getNameOfParticipants(races.get(position).getParticipantsForRace());
    ((TextView)convertView.findViewById(R.id.race_details)).setText(getRaceDetail(nameOfParticipants));

    ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(context, android.R.layout.simple_spinner_item, nameOfParticipants);
    arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    LinearLayout winnerListLayout = (LinearLayout) convertView.findViewById(R.id.winner_list);
    for (int i = 0; i < numberOfLanes-1 ; i++) {
      View winnerListItem = layoutInflater.inflate(R.layout.race_winner_list_item, winnerListLayout, false);
      Spinner winnerDropDown = (Spinner) winnerListItem.findViewById(R.id.select_winner_dropdown);
      winnerDropDown.setAdapter(arrayAdapter);
      winnerDropDown.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int i, long id) {
          winnersList.set(position, parent.getSelectedItem().toString());
        }
        @Override
        public void onNothingSelected(AdapterView<?> parent) {
        }
      });
      ((TextView)winnerListItem.findViewById(R.id.position_title)).setText("Position " + (i+1));
      winnerListLayout.addView(winnerListItem);
    }
    return convertView;
  }

  private List<String> getNameOfParticipants(List<Participant> participantsForRace) {
    List<String> namesOfParticipants = new ArrayList<String>();
    for (Participant participant : participantsForRace) {
      namesOfParticipants.add(participant.getName());
    }
    return namesOfParticipants;
  }

  private CharSequence getRaceDetail(List<String> namesOfParticipants) {
    String raceDetail = "";
    for (int i = 0 ; i < namesOfParticipants.size() ; i++) {
      raceDetail += String.format("Lane %d - %s\n", i + 1, namesOfParticipants.get(i));
    }
    return raceDetail;
  }
}
