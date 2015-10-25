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
import com.rowing.coepbc.repechage.models.Round;

import java.util.ArrayList;
import java.util.List;

public class RoundDetailsAdapter extends BaseAdapter {
  private final char roundName;
  private Context context;
  private int race_detail_layout;
  private List<Race> races;
  private int numberOfLanes;

  public RoundDetailsAdapter(Context context, int race_detail_layout, Round round, int numberOfLanes) {
    this.context = context;
    this.race_detail_layout = race_detail_layout;
    this.races = round.races;
    this.roundName = round.name;
    this.numberOfLanes = numberOfLanes;
  }

  @Override
  public int getCount() {
    return races.size();
  }

  @Override
  public Object getItem(int position) {
    return races.get(position);
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
    ((TextView)convertView.findViewById(R.id.race_number)).setText("Race " + roundName + (position+1));

    List<String> nameOfParticipants = getNameOfParticipants(races.get(position).getParticipantsForRace());
    ((TextView)convertView.findViewById(R.id.race_details)).setText(getRaceDetail(nameOfParticipants));

    ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(context, android.R.layout.simple_spinner_item, nameOfParticipants);
    arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    LinearLayout winnerListLayout = (LinearLayout) convertView.findViewById(R.id.winner_list);

    for (int winnerPosition = 0; winnerPosition < numberOfLanes ; winnerPosition++) {
      View winnerListItem = layoutInflater.inflate(R.layout.race_winner_list_item, winnerListLayout, false);
      Spinner winnerDropDown = (Spinner) winnerListItem.findViewById(R.id.select_winner_dropdown);
      winnerDropDown.setAdapter(arrayAdapter);
      final int finalWinnerPosition = winnerPosition;
      winnerDropDown.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int i, long id) {
          String participantName = parent.getSelectedItem().toString();
          races.get(position).setRankForParticipant(participantName, finalWinnerPosition);
        }
        @Override
        public void onNothingSelected(AdapterView<?> parent) {
        }
      });
      winnerDropDown.setSelection(winnerPosition);
      ((TextView)winnerListItem.findViewById(R.id.position_title)).setText("Position " + (winnerPosition + 1));
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
