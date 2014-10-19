package com.rowing.coepbc.repechage;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.rowing.coepbc.repechage.models.Participant;

import java.util.ArrayList;
import java.util.List;

public class ParticipantsArrayAdapter<T> extends ArrayAdapter {
  private List<String> participantsList = new ArrayList<String>();
  private int single_participant_entry_layout;
  private int size;

  public ParticipantsArrayAdapter(Context context, int resource, int size) {
    super(context, resource);
    this.single_participant_entry_layout = resource;
    this.size = size;
    for (int i = 0; i < size; i++) {
       participantsList.add("");
    }
  }

  @Override
  public int getCount() {
    return size;
  }

  @Override
  public Object getItem(int position) {
    return new Participant(participantsList.get(position));
  }

  @Override
  public View getView(final int position, View convertView, ViewGroup parent) {
    if(convertView == null){
      convertView = ((Activity)parent.getContext()).getLayoutInflater()
          .inflate(single_participant_entry_layout, null);
    }
    ((TextView)convertView.findViewById(R.id.participant_entry_index)).setText(String.format("%d. ", position + 1));
    convertView.findViewById(R.id.participant_name).setOnFocusChangeListener(new View.OnFocusChangeListener() {
      @Override
      public void onFocusChange(View v, boolean hasFocus) {
        participantsList.set(position, ((EditText) v).getText().toString());
      }
    });
    return convertView;
  }
}