package com.example.arena2020.ui;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.arena2020.R;
import com.example.arena2020.adapters.AnnouncementAdapter;
import com.example.arena2020.items.Announcement;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class AnnouncementFragment extends Fragment {

    private AnnouncementAdapter mAdapter;
    private RecyclerView mRecyclerView;
    private ProgressBar mProgressBar;

    ArrayList<Announcement> mAnnouncements;

    private FirebaseFirestore db;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_announcements, container, false);

        mRecyclerView = root.findViewById(R.id.announcements_recycler);
        mProgressBar = root.findViewById(R.id.announcements_progress_bar);

        db = FirebaseFirestore.getInstance();

        mAnnouncements = new ArrayList<>();

        getAnnouncements();
        setRecyclerData();

        return root;
    }

    private void setRecyclerData() {
        mAdapter = new AnnouncementAdapter(mAnnouncements, getContext());
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    private void getAnnouncements() {
        setLoadingView();
        mAnnouncements.clear();
        db.collection(getString(R.string.firebase_collection_announcements))
                .orderBy(getString(R.string.firebase_collection_announcements_field_date))
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                mAnnouncements.add(new Announcement(document.getId(),
                                        document.getString(getString(R.string.firebase_collection_announcements_field_name)),
                                        document.getString(getString(R.string.firebase_collection_announcements_field_desc)),
                                        document.getDate(getString(R.string.firebase_collection_announcements_field_date))));
                            }
                            setRecyclerView();
                            setRecyclerData();
                        } else {
                            Log.d("ScheduleFragment", "Error getting documents: ", task.getException());
                        }
                    }
                });
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
