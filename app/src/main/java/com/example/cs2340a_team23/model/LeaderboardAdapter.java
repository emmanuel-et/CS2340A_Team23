package com.example.cs2340a_team23.model;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.content.Context;
import java.util.List;
import android.view.LayoutInflater;
import android.widget.TextView;
import com.example.cs2340a_team23.R;



public class LeaderboardAdapter extends BaseAdapter {

    private Context context;
    private List<LeaderboardEntry> leaderboardEntries;

    public LeaderboardAdapter(Context context, List<LeaderboardEntry> leaderboardEntries) {
        this.context = context;
        this.leaderboardEntries = leaderboardEntries;
    }

    @Override
    public int getCount() {
        return leaderboardEntries.size();
    }

    @Override
    public Object getItem(int position) {
        return leaderboardEntries.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.activity_leaderboard_entry, parent, false);
        }

        TextView nameTextView = convertView.findViewById(R.id.nameTextView);
        TextView scoreTextView = convertView.findViewById(R.id.scoreTextView);

        LeaderboardEntry entry = leaderboardEntries.get(position);

        nameTextView.setText(entry.getName());
        scoreTextView.setText(String.valueOf(entry.getScore()));

        return convertView;
    }
}
