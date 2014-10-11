package com.rowing.coepbc.repechage;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.TextView;

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
    ((TextView)convertView.findViewById(R.id.race_details)).setText(getRaceDetail(position));
    return convertView;
  }

  private CharSequence getRaceDetail(int raceNumber) {
    Race race = races.get(raceNumber);
    List<Participant> participantsForRace = race.getParticipantsForRace();
    String raceDetail = "";
    for (int i = 0; i < participantsForRace.size(); i++) {
      raceDetail += String.format("%d - %s\n", i+1, participantsForRace.get(i).getName());
    }
    return raceDetail;
  }
}
