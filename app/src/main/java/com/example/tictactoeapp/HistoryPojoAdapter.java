package com.example.tictactoeapp;

import static android.content.Context.MODE_PRIVATE;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.google.gson.Gson;

import java.text.SimpleDateFormat;
import java.util.Locale;

public class HistoryPojoAdapter extends ArrayAdapter<HistoryPojo> {
    Context mContext;

    // View lookup cache
    private static class ViewHolder {
        TextView txtDate;
        TextView txtName1;
        TextView txtName2;
    }
    public HistoryPojoAdapter(HistoryHolder data, Context context) {
        super(context, R.layout.history_row,data.items);
        this.mContext=context;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        HistoryPojo dataModel = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        ViewHolder viewHolder; // view lookup cache stored in tag
        if (convertView == null) {
            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.history_row, parent, false);
            viewHolder.txtDate = convertView.findViewById(R.id.date_id);
            viewHolder.txtName1 = convertView.findViewById(R.id.name1_id);
            viewHolder.txtName2 = convertView.findViewById(R.id.name2_id);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        SimpleDateFormat sdf;
        sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        String text = sdf.format(dataModel.match);
        viewHolder.txtDate.setText(text);
        viewHolder.txtDate.setTextColor(getContext().getColor(R.color.black));
        viewHolder.txtDate.setBackgroundColor(getContext().getColor(R.color.white));
        viewHolder.txtName1.setText(dataModel.player1);
        viewHolder.txtName1.setBackgroundColor(getContext().getColor(R.color.white));
        viewHolder.txtName2.setText(dataModel.player2);
        viewHolder.txtName2.setBackgroundColor(getContext().getColor(R.color.white));

//        mContext.getSharedPreferences("history", MODE_PRIVATE);

//        SharedPreferences appSharedPrefs = PreferenceManager.getDefaultSharedPreferemces(mContext.getApplicationContext());
//        Gson gson = new Gson();
//        String json = appSharedPrefs.getString("history", "");
//        tasks = gson.fromJson(json, new TypeToken<ArrayList<Task>>(){}.getType());
//
//        if (viewHolder.txtName1 == )


            viewHolder.txtName1.setTextColor(getContext().getColor(R .color.winner));
            viewHolder.txtName2.setTextColor(getContext().getColor(R.color.black));


            viewHolder.txtName2.setTextColor(getContext().getColor(R.color.winner));
            viewHolder.txtName1.setTextColor(getContext().getColor(R.color.black));

        // Return the completed view to render on screen
        return convertView;
    }
}
