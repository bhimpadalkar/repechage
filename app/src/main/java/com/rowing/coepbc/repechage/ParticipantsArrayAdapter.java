package com.rowing.coepbc.repechage;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class ParticipantsArrayAdapter<T> extends ArrayAdapter {
  private int single_participant_entry_layout;
  private int size;
  private Context context;

  public ParticipantsArrayAdapter(Context context, int resource, int size) {
    super(context, resource);
    this.context = context;
    this.single_participant_entry_layout = resource;
    this.size = size;
  }

  @Override
  public int getCount() {
    return size;
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
    ((TextView)convertView.findViewById(R.id.participant_entry_index)).setText(String.format("%d. ", position + 1));
    return convertView;
  }
}