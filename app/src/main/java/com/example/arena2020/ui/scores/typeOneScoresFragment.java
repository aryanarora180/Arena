package com.example.arena2020.ui.scores;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.arena2020.R;
import com.example.arena2020.items.ScheduleSport;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

public class typeOneScoresFragment extends Fragment {

    private String documentID;

    private FirebaseFirestore db;

    public typeOneScoresFragment(String documentID) {
        db = FirebaseFirestore.getInstance();
        this.documentID = documentID;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_type_one_scores, container, false);

        final TextView sportNameTextView = root.findViewById(R.id.live_score_type_one_sport_name);
        final TextView teamANameTextView = root.findViewById(R.id.live_score_type_one_score_team_left);
        final TextView teamBNameTextView = root.findViewById(R.id.live_score_type_one_score_team_right);
        final TextView scoreTextView = root.findViewById(R.id.live_score_type_one_score);

        db.collection(getString(R.string.firebase_collection_schedule)).document(documentID).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {
                        sportNameTextView.setText(ScheduleSport.getSportName(document.getLong(getString(R.string.firebase_collection_schedule_field_sportCode))));
                        teamANameTextView.setText(document.getString(getString(R.string.firebase_collection_schedule_field_name_team_a)));
                        teamBNameTextView.setText(document.getString(getString(R.string.firebase_collection_schedule_field_name_team_b)));
                        scoreTextView.setText(formatScores(document.getLong(getString(R.string.firebase_collection_schedule_field_score_team_a)), document.getLong(getString(R.string.firebase_collection_schedule_field_score_team_b))));
                    }
                }
            }
        });

        db.collection(getString(R.string.firebase_collection_schedule)).document(documentID).addSnapshotListener(new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot snapshot,
                                @Nullable FirebaseFirestoreException e) {
                if (snapshot != null && snapshot.exists()) {
                    //TODO: put live scores
                }
            }
        });


        return root;
    }

    private String formatScores(long aScore, long bScore) {
        return aScore + ":" + bScore;
    }

}
