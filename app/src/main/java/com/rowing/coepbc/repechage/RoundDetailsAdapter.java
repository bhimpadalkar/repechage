package com.rowing.coepbc.repechage;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class RoundDetailsAdapter<T> extends ArrayAdapter {
  private Context context;
  private int race_detail_layout;
  private List<Race> races;

  public RoundDetailsAdapter(Context context, int race_detail_layout, List<Race> races) {
    super(context, race_detail_layout);
    this.context = context;
    this.race_detail_layout = race_detail_layout;
    this.races = races;
  }

  @Override
  public int getCount() {
    return races.size();
  }

  @Override
  public View getView(int position, View convertView, ViewGroup parent) {
    if(convertView == null){
      convertView = ((Activity)parent.getContext()).getLayoutInflater()
          .inflate(race_detail_layout, null);
    }
    ((TextView)convertView.findViewById(R.id.race_number)).setText(String.format("Race %d", position + 1));

    Race race = races.get(position);
    List<Participant> participantsForRace = race.getParticipantsForRace();
    ((TextView)convertView.findViewById(R.id.race_details)).setText(getRaceDetail(getNameOfParticipants(participantsForRace)));

    Spinner winnerDropDown = (Spinner) convertView.findViewById(R.id.select_winner_dropdown);
    ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(context, android.R.layout.simple_spinner_item, getNameOfParticipants(participantsForRace));
    arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    winnerDropDown.setAdapter(arrayAdapter);
    convertView.setId(position);
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
