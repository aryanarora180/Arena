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
import com.example.arena2020.adapters.ScheduleSportAdapter;
import com.example.arena2020.items.ScheduleSport;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Date;

public class ScheduleFragment extends Fragment {

    private ScheduleSportAdapter mAdapter;
    private RecyclerView mRecyclerView;

    private Chip mChip23;
    private Chip mChip24;
    private Chip mChip25;
    private Chip mChip26;
    private ChipGroup mChipGroup;
    private ProgressBar mProgressBar;
    private int selectedDay;

    ArrayList<ScheduleSport> mAllScheduleEvents;
    ArrayList<ScheduleSport> mScheduleEventsToDisplay;

    private FirebaseFirestore db;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_schedule, container, false);

        db = FirebaseFirestore.getInstance();

        mAllScheduleEvents = new ArrayList<>();
        mScheduleEventsToDisplay = new ArrayList<>();

        mRecyclerView = root.findViewById(R.id.sports_schedule_recycler);
        mProgressBar = root.findViewById(R.id.schedule_progress_bar);

        getAllScheduleEvents();

        mChip23 = root.findViewById(R.id.schedule_23_chip);
        mChip24 = root.findViewById(R.id.schedule_24_chip);
        mChip25 = root.findViewById(R.id.schedule_25_chip);
        mChip26 = root.findViewById(R.id.schedule_26_chip);
        mChipGroup = root.findViewById(R.id.schedule_chip_group);

        //Seeing what day it is and setting the respective Chip
        Date curDate = new Date();
        Date dateJan24 = new Date(1579824001L);
        Date dateJan25 = new Date(1579910401L);
        Date dateJan26 = new Date(1579996801L);
        Date dateJan27 = new Date(1580083201L);

        if (curDate.after(dateJan24) && curDate.before(dateJan25)) {
            mChip24.setChecked(true);
            selectedDay = 24;
        } else if (curDate.after(dateJan25) && curDate.before(dateJan26)) {
            mChip25.setChecked(true);
            selectedDay = 25;
        } else if (curDate.after(dateJan26) && curDate.before(dateJan27)) {
            mChip26.setChecked(true);
            selectedDay = 26;
        } else {
            mChip23.setChecked(true);
            selectedDay = 23;
        }

        mChip23.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mChipGroup.getCheckedChipId() == View.NO_ID) {
                    mChip23.setChecked(true);
                }
                setLoadingView();
                selectedDay = 23;
                filterScheduleEvents(selectedDay);
                setRecyclerData();
            }
        });

        mChip24.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mChipGroup.getCheckedChipId() == View.NO_ID) {
                    mChip24.setChecked(true);
                }
                setLoadingView();
                selectedDay = 24;
                filterScheduleEvents(selectedDay);
                setRecyclerData();
            }
        });

        mChip25.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mChipGroup.getCheckedChipId() == View.NO_ID) {
                    mChip25.setChecked(true);
                }
                setLoadingView();
                selectedDay = 25;
                filterScheduleEvents(selectedDay);
                setRecyclerData();
            }
        });

        mChip26.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mChipGroup.getCheckedChipId() == View.NO_ID) {
                    mChip26.setChecked(true);
                }
                setLoadingView();
                selectedDay = 26;
                filterScheduleEvents(selectedDay);
                setRecyclerData();
            }
        });

        return root;
    }

    private void setRecyclerData() {
        mAdapter = new ScheduleSportAdapter(mScheduleEventsToDisplay, getContext());
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        setRecyclerView();
    }

    private void getAllScheduleEvents() {
        setLoadingView();
        mScheduleEventsToDisplay.clear();
        db.collection(getString(R.string.firebase_collection_schedule))
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                mAllScheduleEvents.add(new ScheduleSport(document.getId(),
                                        document.getDate(getString(R.string.firebase_collection_schedule_field_date)),
                                        document.getLong(getString(R.string.firebase_collection_schedule_field_sportCode)),
                                        document.getLong(getString(R.string.firebase_collection_schedule_field_matchStatus)),
                                        document.getString(getString(R.string.firebase_collection_schedule_field_name_team_a)),
                                        document.getString(getString(R.string.firebase_collection_schedule_field_name_team_b)),
                                        false));
                            }
                            filterScheduleEvents(selectedDay);
                            setRecyclerView();
                            setRecyclerData();
                        } else {
                            Log.d("ScheduleFragment", "Error getting documents: ", task.getException());
                        }
                    }
                });
    }

    private void filterScheduleEvents(int day) {
        mScheduleEventsToDisplay.clear();
        for (ScheduleSport sportEvent : mAllScheduleEvents) {
            if (day == sportEvent.getDay())
                mScheduleEventsToDisplay.add(sportEvent);
        }
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