package com.dota.arena2020.ui.scores;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.dota.arena2020.R;
import com.dota.arena2020.items.ScheduleSport;
import com.dota.arena2020.ui.ScheduleFragment;
import com.dota.arena2020.R;
import com.dota.arena2020.items.ScheduleSport;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

public class TypeOneScoresFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {

    private String documentID;

    private FirebaseFirestore db;

    private ConstraintLayout viewsLayout;
    private ProgressBar progressBar;
    private SwipeRefreshLayout refreshLayout;

    public TypeOneScoresFragment(String documentID) {
        this.documentID = documentID;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_type_one_scores, container, false);

        refreshLayout = (SwipeRefreshLayout)root.findViewById(R.id.refreshLayout2);
        refreshLayout.setOnRefreshListener(this);

        OnBackPressedCallback callback = new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                Fragment fragment = new ScheduleFragment();
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.fragment_frame, fragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        };
        requireActivity().getOnBackPressedDispatcher().addCallback(this,callback);

        db = FirebaseFirestore.getInstance();

        viewsLayout = root.findViewById(R.id.live_score_constraint);
        progressBar = root.findViewById(R.id.live_score_loader);

        final TextView sportNameTextView = root.findViewById(R.id.live_score_sport_name);
        final TextView teamANameTextView = root.findViewById(R.id.live_score_score_team_a);
        final TextView teamBNameTextView = root.findViewById(R.id.live_score_type_three_score_team_b);
        final TextView scoreTextView = root.findViewById(R.id.live_score_type_three_score);
        final LinearLayout liveIndicator = root.findViewById(R.id.live_indicator_linear_layout);
        final ImageView liveIndicatorImageView = root.findViewById(R.id.live_indicator_image_view);

        setLoadingView();

        db.collection(getString(R.string.firebase_collection_schedule)).document(documentID).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {
                        setScoreView();
                        long matchStatus = document.getLong(getString(R.string.firebase_collection_schedule_field_matchStatus));
                        if (matchStatus == ScheduleSport.MATCH_IN_PROGRESS) {
                            Animation animation = new AlphaAnimation(1, 0);
                            animation.setDuration(600);
                            animation.setInterpolator(new LinearInterpolator());
                            animation.setRepeatCount(Animation.INFINITE);
                            animation.setRepeatMode(Animation.REVERSE);
                            liveIndicatorImageView.startAnimation(animation);

                            sportNameTextView.setText(ScheduleSport.getSportName(document.getLong(getString(R.string.firebase_collection_schedule_field_sportCode))));
                            teamANameTextView.setText(document.getString(getString(R.string.firebase_collection_schedule_field_name_team_a)));
                            teamBNameTextView.setText(document.getString(getString(R.string.firebase_collection_schedule_field_name_team_b)));
                            scoreTextView.setText(formatScores(document.getLong(getString(R.string.firebase_collection_schedule_field_score_team_a)), document.getLong(getString(R.string.firebase_collection_schedule_field_score_team_b))));

                            db.collection(getString(R.string.firebase_collection_schedule)).document(documentID).addSnapshotListener(new EventListener<DocumentSnapshot>() {
                                @Override
                                public void onEvent(@Nullable DocumentSnapshot snapshot,
                                                    @Nullable FirebaseFirestoreException e) {
                                    if (snapshot != null && snapshot.exists()) {
                                        scoreTextView.setText(formatScores(snapshot.getLong(getString(R.string.firebase_collection_schedule_field_score_team_a)), snapshot.getLong(getString(R.string.firebase_collection_schedule_field_score_team_b))));
                                    }
                                }
                            });

                        } else if (matchStatus == ScheduleSport.MATCH_COMPLETED) {
                            liveIndicator.setVisibility(View.GONE);
                            sportNameTextView.setText(ScheduleSport.getSportName(document.getLong(getString(R.string.firebase_collection_schedule_field_sportCode))));
                            teamANameTextView.setText(document.getString(getString(R.string.firebase_collection_schedule_field_name_team_a)));
                            teamBNameTextView.setText(document.getString(getString(R.string.firebase_collection_schedule_field_name_team_b)));
                            scoreTextView.setText(formatScores(document.getLong(getString(R.string.firebase_collection_schedule_field_score_team_a)), document.getLong(getString(R.string.firebase_collection_schedule_field_score_team_b))));
                        }
                    }
                }
            }
        });
        return root;
    }

    private String formatScores(long aScore, long bScore) {
        return aScore + "-" + bScore;
    }

    private void setLoadingView() {
        progressBar.setVisibility(View.VISIBLE);
        viewsLayout.setVisibility(View.INVISIBLE);
    }

    private void setScoreView() {
        progressBar.setVisibility(View.INVISIBLE);
        viewsLayout.setVisibility(View.VISIBLE);
    }

    @Override
    public void onRefresh() {

    }
}
