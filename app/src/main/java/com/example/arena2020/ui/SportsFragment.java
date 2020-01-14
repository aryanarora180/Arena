package com.example.arena2020.ui;

import android.media.session.PlaybackState;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.arena2020.R;

public class SportsFragment extends Fragment {

    GridView gridView;

    String[] sports = {"CRICKET", "FOOTBALL", "VOLLEY BALL", "BASKET BALL", "HOCKEY", "BADMINTON", "TENNIS", "CHESS", "CARROM", "SQUASH", "TABLE TENNIS", "SNOOKER", "THROW BALL", "DUATHLON", "BODY BUIDING", "POWER LIFTING", "FRISBEE" };
    int[] icons = {R.drawable.cricket, R.drawable.football, R.drawable.volleyball, R.drawable.basketball, R.drawable.hockey, R.drawable.badminton, R.drawable.tennis, R.drawable.chess, R.drawable.carrom, R.drawable.squash, R.drawable.tt, R.drawable.snooker, R.drawable.throwball, R.drawable.duathlon, R.drawable.bodybuilding, R.drawable.powerlifting, R.drawable.frisbee};

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_sports, container, false);

        gridView = root.findViewById(R.id.gridview);
        CustomAdapter customAdapter = new CustomAdapter();
        gridView.setAdapter(customAdapter);
        return root;
    }

    private class CustomAdapter extends BaseAdapter {


        @Override
        public int getCount() {
            return sports.length;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View view1 = getLayoutInflater().inflate(R.layout.sports_item,null);

            TextView sport = view1.findViewById(R.id.sport);
            ImageView icon = view1.findViewById(R.id.icon);

            sport.setText(sports[position]);
            icon.setImageResource(icons[position]);

            return view1;
        }
    }
}