package com.example.arena2020.ui.scores;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import com.example.arena2020.R;
import com.example.arena2020.items.ScheduleSport;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class MatchScheduledFragment extends Fragment {

    public static final int TYPE_SCHEDULED = 380;
    public static final int TYPE_IN_PROGRESS = 380;
    String documentId;
    int mMode;


    private FirebaseFirestore db;
    private ConstraintLayout viewsLayout;
    private ProgressBar progressBar;

    public MatchScheduledFragment(String documentId, int mode) {
        this.documentId = documentId;
        mMode = mode;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_match_scheduled, container, false);

        db = FirebaseFirestore.getInstance();

        viewsLayout = root.findViewById(R.id.live_score_constraint);
        progressBar = root.findViewById(R.id.live_score_loader);

        TextView matchStatus = root.findViewById(R.id.match_status);
        if (mMode == TYPE_IN_PROGRESS)
            matchStatus.setText(getString(R.string.match_in_progress));
        else
            matchStatus.setText(getString(R.string.match_scheduled));

        final TextView sportNameTextView = root.findViewById(R.id.live_score_sport_name);
        final TextView teamANameTextView = root.findViewById(R.id.live_score_score_team_a);
        final TextView teamBNameTextView = root.findViewById(R.id.live_score_type_three_score_team_b);

        setLoadingView();

        db.collection(getString(R.string.firebase_collection_schedule)).document(documentId).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    setScoreView();
                    if (document.exists()) {
                        sportNameTextView.setText(ScheduleSport.getSportName(document.getLong(getString(R.string.firebase_collection_schedule_field_sportCode))));
                        teamANameTextView.setText(document.getString(getString(R.string.firebase_collection_schedule_field_name_team_a)));
                        teamBNameTextView.setText(document.getString(getString(R.string.firebase_collection_schedule_field_name_team_b)));
                    }
                }
            }
        });

        return root;

    }

    private void setLoadingView() {
        progressBar.setVisibility(View.VISIBLE);
        viewsLayout.setVisibility(View.INVISIBLE);
    }

    private void setScoreView() {
        progressBar.setVisibility(View.INVISIBLE);
        viewsLayout.setVisibility(View.VISIBLE);
    }

}
