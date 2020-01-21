package com.example.arena2020.ui.scores;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.example.arena2020.R;

public class TypeFourScoresFragment extends Fragment {

    String documentId;

    public TypeFourScoresFragment(String documentId) {
        this.documentId = documentId;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_type_four_scores, container, false);


        return root;
    }

}
