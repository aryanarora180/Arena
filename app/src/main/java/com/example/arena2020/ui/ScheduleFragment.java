package com.example.arena2020.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.arena2020.R;
import com.example.arena2020.adapters.ScheduleSportAdapter;
import com.example.arena2020.items.ScheduleSport;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;

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

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_schedule, container, false);

        mRecyclerView = root.findViewById(R.id.sports_schedule_recycler);

        mChip23 = root.findViewById(R.id.schedule_23_chip);
        mChip24 = root.findViewById(R.id.schedule_24_chip);
        mChip25 = root.findViewById(R.id.schedule_25_chip);
        mChip26 = root.findViewById(R.id.schedule_26_chip);
        mChipGroup = root.findViewById(R.id.schedule_chip_group);

        //TODO: get sports events and save them
        ArrayList<ScheduleSport> scheduleEvents = new ArrayList<>();
        scheduleEvents.add(new ScheduleSport(new Date(), "Squash", "BPHC vs IITM", false, ScheduleSport.SPORT_COLOR_SQUASH));
        scheduleEvents.add(new ScheduleSport(new Date(), "Cricket", "BPHC vs BPGC", true, ScheduleSport.SPORT_COLOR_CRICKET));

        //Seeing what day it is and setting the respective Chip
        Date curDate = new Date();
        Date dateJan24 = new Date(1579824001L);
        Date dateJan25 = new Date(1579910401L);
        Date dateJan26 = new Date(1579996801L);
        Date dateJan27 = new Date(1580083201L);

        if (curDate.after(dateJan24) && curDate.before(dateJan25)) {
            mChip24.setChecked(true);
        } else if (curDate.after(dateJan25) && curDate.before(dateJan26)) {
            mChip25.setChecked(true);
        } else if (curDate.after(dateJan26) && curDate.before(dateJan27)) {
            mChip26.setChecked(true);
        } else {
            mChip23.setChecked(true);
            setRecyclerData(scheduleEvents);
        }

        mChip23.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //call set recyclerData for Jan 23rd
            }
        });

        mChip24.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //call set recyclerData for Jan 24th
            }
        });

        mChip25.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //call set recyclerData for Jan 25th
            }
        });

        mChip26.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //call set recyclerData for Jan 26th
            }
        });

        return root;
    }

    private void setRecyclerData(ArrayList<ScheduleSport> scheduleEvents) {
        mAdapter = new ScheduleSportAdapter(scheduleEvents, getContext());
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    }

}