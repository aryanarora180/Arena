package com.example.arena2020.ui.scores;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.example.arena2020.R;
import com.google.firebase.firestore.FirebaseFirestore;

public class MatchScheduledFragment extends Fragment {

    private String documentId;

    private FirebaseFirestore db;

    public MatchScheduledFragment(String documentId) {
        this.documentId = documentId;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_match_scheduled, container);


        return root;
    }

}
