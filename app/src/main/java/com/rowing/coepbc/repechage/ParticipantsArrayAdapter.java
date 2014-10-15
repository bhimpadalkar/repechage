package com.rowing.coepbc.repechage;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

public class ParticipantsArrayAdapter<T> extends ArrayAdapter {
  private int single_participant_entry_layout;
  private int size;
  private ListView participantsEntriesView;

  public ParticipantsArrayAdapter(Context context, int resource, int size, ListView participantsEntriesView) {
    super(context, resource);
    this.single_participant_entry_layout = resource;
    this.size = size;
    this.participantsEntriesView = participantsEntriesView;
  }

  @Override
  public int getCount() {
    return size;
  }

  @Override
  public Object getItem(int position) {
    EditText nameView = (EditText) participantsEntriesView.getChildAt(position).findViewById(R.id.participant_name);
    return new Participant(nameView.getText().toString());
  }

  @Override
  public View getView(int position, View convertView, ViewGroup parent) {
    if(convertView == null){
      convertView = ((Activity)parent.getContext()).getLayoutInflater()
          .inflate(single_participant_entry_layout, null);
    }
    ((TextView)convertView.findViewById(R.id.participant_entry_index)).setText(String.format("%d. ", position + 1));
    return convertView;
  }
}