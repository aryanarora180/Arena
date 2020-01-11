package com.example.arena2020.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.arena2020.R;
import com.example.arena2020.adapters.AnnouncementAdapter;
import com.example.arena2020.items.Announcement;

import java.util.ArrayList;
import java.util.Date;

public class AnnouncementFragment extends Fragment {

    private AnnouncementAdapter mAdapter;
    private RecyclerView mRecyclerView;
    private ProgressBar mProgressBar;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_announcements, container, false);

        mRecyclerView = root.findViewById(R.id.announcements_recycler);
        mProgressBar = root.findViewById(R.id.announcements_progress_bar);

        setRecyclerData(getAnnouncements());

        return root;
    }

    private void setRecyclerData(ArrayList<Announcement> announcements) {
        setLoadingView();
        mAdapter = new AnnouncementAdapter(announcements, getContext());
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        setRecyclerView();
    }

    private ArrayList<Announcement> getAnnouncements() {
        //TODO: get data and return it
        ArrayList<Announcement> announcements = new ArrayList<>();
        announcements.add(new Announcement("Welcome to Arena 2020", "We\'re excited to see you!", new Date()));

        return announcements;
    }

    private void setLoadingView() {
        mRecyclerView.setVisibility(View.INVISIBLE);
        mProgressBar.setVisibility(View.VISIBLE);
    }

    private void setRecyclerView() {
        mRecyclerView.setVisibility(View.VISIBLE);
        mProgressBar.setVisibility(View.INVISIBLE);
    }

}
