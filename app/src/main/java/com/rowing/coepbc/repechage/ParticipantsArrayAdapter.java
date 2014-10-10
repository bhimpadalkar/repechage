package com.rowing.coepbc.repechage;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class ParticipantsArrayAdapter<T> extends BaseAdapter {
  private int single_participant_entry_layout;
  private List<String> participantsIndices;

  public ParticipantsArrayAdapter(int single_participant_entry_layout, List<String> participantsIndices) {
    this.single_participant_entry_layout = single_participant_entry_layout;
    this.participantsIndices = participantsIndices;
  }

  @Override
  public int getCount() {
    return participantsIndices.size();
  }

  @Override
  public Object getItem(int position) {
    return null;
  }

  @Override
  public long getItemId(int position) {
    return 0;
  }

  @Override
  public View getView(int position, View convertView, ViewGroup parent) {
    if(convertView == null){
      convertView = ((Activity)parent.getContext()).getLayoutInflater()
          .inflate(single_participant_entry_layout, null);
    }
    ((TextView)convertView.findViewById(R.id.participant_entry_index)).setText(participantsIndices.get(position));
    return convertView;
  }
}