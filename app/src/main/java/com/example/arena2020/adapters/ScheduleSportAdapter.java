package com.example.arena2020.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.arena2020.R;
import com.example.arena2020.items.ScheduleSport;
import com.example.arena2020.ui.scores.TypeOneScoresFragment;
import com.example.arena2020.ui.scores.TypeTwoScoresFragment;

import java.util.ArrayList;

public class ScheduleSportAdapter extends RecyclerView.Adapter<ScheduleSportAdapter.ViewHolder> {

    private Context mContext;
    private ArrayList<ScheduleSport> mEvents;
    private FragmentManager mFragmentManager;

    public ScheduleSportAdapter(ArrayList<ScheduleSport> events, Context context) {
        mEvents = events;
        mContext = context;
        mFragmentManager = ((AppCompatActivity) context).getSupportFragmentManager();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View listItemView = inflater.inflate(R.layout.list_item_schedule, parent, false);
        ViewHolder viewHolder = new ViewHolder(listItemView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final ScheduleSport sportsEvent = mEvents.get(position);

        TextView timeHoursTextView = holder.timeHoursTextView;
        TextView timeAOPTextView = holder.timeAOPTextView;
        TextView nameTextView = holder.nameTextView;
        TextView subtitleTextView = holder.subtitleTextView;
        final ImageView bookmarkedImageView = holder.bookmarkedImageView;

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openLiveScoreFragment(sportsEvent.getDocumentId(), sportsEvent.getSportType());
            }
        });

        timeHoursTextView.setText(sportsEvent.getTime());
        timeAOPTextView.setText(sportsEvent.getAoP());

        //TODO: get respective color from sportsEvent and set that color on colorImageView

        nameTextView.setText(sportsEvent.getSportName());
        subtitleTextView.setText(sportsEvent.getVsTeams());

        if (sportsEvent.isBookmarked())
            bookmarkedImageView.setImageResource(R.drawable.outline_bookmark_24);
        else
            bookmarkedImageView.setImageResource(R.drawable.outline_bookmark_border_24);

        bookmarkedImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (sportsEvent.isBookmarked()) {
                    //TODO: Remove event from calendar
                    bookmarkedImageView.setImageResource(R.drawable.outline_bookmark_border_24);
                    sportsEvent.setBookmarked(false);
                } else {
                    //TODO: Add event to calendar
                    bookmarkedImageView.setImageResource(R.drawable.outline_bookmark_24);
                    sportsEvent.setBookmarked(true);
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return mEvents.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView timeHoursTextView;
        public TextView timeAOPTextView;
        public TextView nameTextView;
        public TextView subtitleTextView;
        public ImageView bookmarkedImageView;

        public ViewHolder(View itemView) {
            super(itemView);
            timeHoursTextView = itemView.findViewById(R.id.list_schedule_sport_time_hours);
            timeAOPTextView = itemView.findViewById(R.id.list_schedule_sport_aop);
            nameTextView = itemView.findViewById(R.id.list_schedule_sport_name_text_view);
            subtitleTextView = itemView.findViewById(R.id.list_schedule_sport_subtitle);
            bookmarkedImageView = itemView.findViewById(R.id.list_schedule_sport_bookmark);
        }

    }

    private void openLiveScoreFragment(String documentID, long sportType) {
        if (sportType == ScheduleSport.SPORT_TYPE_ONE)
            mFragmentManager.beginTransaction().replace(R.id.fragment_frame, new TypeOneScoresFragment(documentID), documentID).addToBackStack(documentID).commit();
        else if (sportType == ScheduleSport.SPORT_TYPE_TWO)
            mFragmentManager.beginTransaction().replace(R.id.fragment_frame, new TypeTwoScoresFragment(documentID), documentID).addToBackStack(documentID).commit();
    }

}
