package com.rowing.coepbc.repechage;

import android.app.Activity;
import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
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
    EditText nameView = (EditText) convertView.findViewById(R.id.participant_name);
    nameView.setText(participantsList.get(position));
    nameView.addTextChangedListener(getTextChangeListener(position));
    return convertView;
  }

  private TextWatcher getTextChangeListener(final int position) {
    return new TextWatcher() {
      @Override
      public void beforeTextChanged(CharSequence s, int start, int count, int after) {

      }

      @Override
      public void onTextChanged(CharSequence s, int start, int before, int count) {

      }

      @Override
      public void afterTextChanged(Editable s) {
        participantsList.set(position, s.toString());
      }
    };
  }
}