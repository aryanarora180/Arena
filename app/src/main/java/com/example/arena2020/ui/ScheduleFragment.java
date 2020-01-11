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
import com.wangjie.rapidfloatingactionbutton.RapidFloatingActionButton;
import com.wangjie.rapidfloatingactionbutton.RapidFloatingActionHelper;
import com.wangjie.rapidfloatingactionbutton.RapidFloatingActionLayout;
import com.wangjie.rapidfloatingactionbutton.contentimpl.labellist.RFACLabelItem;
import com.wangjie.rapidfloatingactionbutton.contentimpl.labellist.RapidFloatingActionContentLabelList;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ScheduleFragment extends Fragment implements RapidFloatingActionContentLabelList.OnRapidFloatingActionContentLabelListListener {

    private ScheduleSportAdapter mAdapter;
    private RecyclerView mRecyclerView;

    public static final int NO_FILTER = 1001;

    private Chip mChip23;
    private Chip mChip24;
    private Chip mChip25;
    private Chip mChip26;
    private ChipGroup mChipGroup;
    private ProgressBar mProgressBar;
    private RapidFloatingActionLayout rfaLayout;
    private RapidFloatingActionButton filterFab;
    private RapidFloatingActionHelper filterFabHelpler;
    private int selectedDay;

    ArrayList<ScheduleSport> mScheduleEvents;

    private FirebaseFirestore db;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_schedule, container, false);

        db = FirebaseFirestore.getInstance();

        mScheduleEvents = new ArrayList<>();

        mRecyclerView = root.findViewById(R.id.sports_schedule_recycler);
        mProgressBar = root.findViewById(R.id.schedule_progress_bar);

        mChip23 = root.findViewById(R.id.schedule_23_chip);
        mChip24 = root.findViewById(R.id.schedule_24_chip);
        mChip25 = root.findViewById(R.id.schedule_25_chip);
        mChip26 = root.findViewById(R.id.schedule_26_chip);
        mChipGroup = root.findViewById(R.id.schedule_chip_group);

        rfaLayout = root.findViewById(R.id.schedule_filter_kayout);
        filterFab = root.findViewById(R.id.schedule_filter_fab);

        displayFabOptions();

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

        getScheduleEvents(selectedDay, NO_FILTER);
        setRecyclerData();

        mChip23.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectedDay = 23;
                getScheduleEvents(selectedDay, NO_FILTER);
                setRecyclerData();
            }
        });

        mChip24.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectedDay = 24;
                getScheduleEvents(selectedDay, NO_FILTER);
                setRecyclerData();
            }
        });

        mChip25.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectedDay = 25;
                getScheduleEvents(selectedDay, NO_FILTER);
                setRecyclerData();
            }
        });

        mChip26.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectedDay = 26;
                getScheduleEvents(selectedDay, NO_FILTER);
                setRecyclerData();
            }
        });

        return root;
    }

    private void setRecyclerData() {
        mAdapter = new ScheduleSportAdapter(mScheduleEvents, getContext());
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    private void getScheduleEvents(final int day, final int filter) {
        //TODO: get data according to date and filter and return it
        setLoadingView();
        mScheduleEvents.clear();
        db.collection(getString(R.string.firebase_collection_schedule))
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                mScheduleEvents.add(new ScheduleSport(document.getId(),
                                        document.getDate(getString(R.string.firebase_collection_schedule_field_date)),
                                        document.getLong(getString(R.string.firebase_collection_schedule_field_sportCode)),
                                        document.getString(getString(R.string.firebase_collection_schedule_field_teams)),
                                        false));
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

    @Override
    public void onRFACItemLabelClick(int position, RFACLabelItem item) {
        switch (position) {
            case NO_FILTER:
                getScheduleEvents(selectedDay, NO_FILTER);
                setRecyclerData();
                filterFabHelpler.toggleContent();
                break;
            default:
                getScheduleEvents(selectedDay, NO_FILTER);
                setRecyclerData();
                filterFabHelpler.toggleContent();
        }
    }

    @Override
    public void onRFACItemIconClick(int position, RFACLabelItem item) {
        //do nothing since we're not using the icon feature
    }

    private void displayFabOptions() {
        RapidFloatingActionContentLabelList fabContent = new RapidFloatingActionContentLabelList(getContext());
        fabContent.setOnRapidFloatingActionContentLabelListListener(this);
        List<RFACLabelItem> options = new ArrayList<>();
        options.add(new RFACLabelItem<Integer>()
                .setLabel(getString(R.string.filter_remove))
                .setWrapper(NO_FILTER)
        );
        fabContent.setItems(options);
        filterFabHelpler = new RapidFloatingActionHelper(getContext(), rfaLayout, filterFab, fabContent).build();
    }

}