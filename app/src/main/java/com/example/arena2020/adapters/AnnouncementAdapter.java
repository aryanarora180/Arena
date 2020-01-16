package com.example.arena2020.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.arena2020.R;
import com.example.arena2020.items.Announcement;

import java.util.ArrayList;

public class AnnouncementAdapter extends RecyclerView.Adapter<AnnouncementAdapter.ViewHolder> {

    private Context mContext;
    private ArrayList<Announcement> mAnnouncements;

    public AnnouncementAdapter(ArrayList<Announcement> announcements, Context context) {
        mAnnouncements = announcements;
        mContext = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View listItemView = inflater.inflate(R.layout.list_item_announcement, parent, false);
        ViewHolder viewHolder = new ViewHolder(listItemView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final Announcement announcement = mAnnouncements.get(position);

        TextView timeTextView = holder.timeTextView;
        TextView titleTextView = holder.titleTextView;
        TextView descTextView = holder.descTextView;

        timeTextView.setText(announcement.getTime());
        titleTextView.setText(announcement.getAnnouncementName());
        descTextView.setText(announcement.getAnnouncementDescription());
    }

    @Override
    public int getItemCount() {
        return mAnnouncements.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView timeTextView;
        public TextView titleTextView;
        public TextView descTextView;

        public ViewHolder(View itemView) {
            super(itemView);
            timeTextView = itemView.findViewById(R.id.list_item_announcement_date_text_view);
            titleTextView = itemView.findViewById(R.id.live_score_type_three_sport_name);
            descTextView = itemView.findViewById(R.id.list_item_announcement_description_text_view);
        }

    }

}
