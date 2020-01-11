package com.example.arena2020.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.arena2020.R;
import com.example.arena2020.items.ScheduleSport;

import java.util.ArrayList;

public class ScheduleSportAdapter extends RecyclerView.Adapter<ScheduleSportAdapter.ViewHolder> {

    private Context mContext;
    private ArrayList<ScheduleSport> mEvents;

    public ScheduleSportAdapter(ArrayList<ScheduleSport> events, Context context) {
        mEvents = events;
        mContext = context;
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
        ImageView colorImageView = holder.colorImageView;
        TextView nameTextView = holder.nameTextView;
        TextView subtitleTextView = holder.subtitleTextView;
        final ImageView bookmarkedImageView = holder.bookmarkedImageView;

        timeHoursTextView.setText(sportsEvent.getTime());
        timeAOPTextView.setText(sportsEvent.getAoP());

        switch (sportsEvent.getColor()) {
            case ScheduleSport.SPORT_COLOR_CRICKET:
                colorImageView.setColorFilter(R.color.sportCricket);
                break;
            case ScheduleSport.SPORT_COLOR_SQUASH:
                colorImageView.setColorFilter(R.color.sportSquash);
                break;
            default:
                colorImageView.setColorFilter(R.color.sportDefault);
                //TODO: get respective color code from sportsEvent and set that color on colorImageView
        }

        nameTextView.setText(sportsEvent.getName());
        subtitleTextView.setText(sportsEvent.getSubtitle());

        if (sportsEvent.isBookmarked())
            bookmarkedImageView.setImageResource(R.drawable.outline_bookmark_24);
        else
            bookmarkedImageView.setImageResource(R.drawable.outline_bookmark_border_24);

        bookmarkedImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (sportsEvent.isBookmarked()) {
                    bookmarkedImageView.setImageResource(R.drawable.outline_bookmark_border_24);
                    //TODO: Remvoe bookmark on backend
                } else {
                    bookmarkedImageView.setImageResource(R.drawable.outline_bookmark_24);
                    //TODO: Remvoe bookmark on backend
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
        public ImageView colorImageView;
        public TextView nameTextView;
        public TextView subtitleTextView;
        public ImageView bookmarkedImageView;

        public ViewHolder(View itemView) {
            super(itemView);
            timeHoursTextView = itemView.findViewById(R.id.list_schedule_sport_time_hours);
            timeAOPTextView = itemView.findViewById(R.id.list_schedule_sport_aop);
            colorImageView = itemView.findViewById(R.id.list_schedule_sport_color);
            nameTextView = itemView.findViewById(R.id.list_schedule_sport_name_text_view);
            subtitleTextView = itemView.findViewById(R.id.list_schedule_sport_subtitle);
            bookmarkedImageView = itemView.findViewById(R.id.list_schedule_sport_bookmark);
        }

    }

}
