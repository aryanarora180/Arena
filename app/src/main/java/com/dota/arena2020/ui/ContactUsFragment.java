package com.dota.arena2020.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.LinearSmoothScroller;
import androidx.recyclerview.widget.RecyclerView;

import com.dota.arena2020.R;
import com.dota.arena2020.adapters.ContactUsAdapter;
import com.dota.arena2020.items.ContactUs;

import java.util.ArrayList;

public class ContactUsFragment extends Fragment {

    private RecyclerView mRecyclerView;

    private TextView mOrganizingBodyTextView;
    private CardView mOrganizingBodyCardView;
    private TextView mTechSenateTextView;
    private CardView mTechSenateCardView;

    private ArrayList<ContactUs> mContactUses;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View rootview = inflater.inflate(R.layout.fragment_contact_us, container, false);

        OnBackPressedCallback callback = new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                Fragment fragment = new AnnouncementFragment();
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.fragment_frame, fragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        };
        requireActivity().getOnBackPressedDispatcher().addCallback(this,callback);

        mRecyclerView = rootview.findViewById(R.id.contact_us_recycler_view);

        mOrganizingBodyTextView = rootview.findViewById(R.id.organizing_body_text_view);
        mOrganizingBodyCardView = rootview.findViewById(R.id.organizing_body_card_view);
        mTechSenateTextView = rootview.findViewById(R.id.tech_senate_text_view);
        mTechSenateCardView = rootview.findViewById(R.id.tech_senate_header_card_view);

        mContactUses = new ArrayList<>();
        addDataToArray();

        ContactUsAdapter adapter = new ContactUsAdapter(getContext(), mContactUses);
        mRecyclerView.setAdapter(adapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        setOrganizingBody();

        final LinearLayoutManager manager = (LinearLayoutManager) mRecyclerView.getLayoutManager() ;

        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                int position = manager.findFirstVisibleItemPosition();
                int type = mContactUses.get(position).getPublicityType();
                switch(type) {
                    case 2:
                        setTechSenate();
                        break;
                    case 1:
                    default:
                        setOrganizingBody();
                }
            }
        });

        final RecyclerView.SmoothScroller smoothScroller = new LinearSmoothScroller(getContext()) {
            @Override protected int getVerticalSnapPreference() {
                return LinearSmoothScroller.SNAP_TO_START;
            }
        };

        mOrganizingBodyCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                smoothScroller.setTargetPosition(0);
                manager.startSmoothScroll(smoothScroller);
            }
        });

        mTechSenateCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                smoothScroller.setTargetPosition(13);
                manager.startSmoothScroll(smoothScroller);
            }
        });

        return rootview;
    }

    private void addDataToArray() {
        //For organizing body members:
        mContactUses.add(new ContactUs("Nikhil Peruri","President", "+917981720729", ContactUs.PUBLICITY_TYPE_ORGANIZING_BODY));
        mContactUses.add(new ContactUs("Praneeth Shetty","Sports Secretary(Boys)", "+917680993056", ContactUs.PUBLICITY_TYPE_ORGANIZING_BODY));
        mContactUses.add(new ContactUs("Siri Jagarlamudi","Sports Secretary(Girls)", "+917032674829", ContactUs.PUBLICITY_TYPE_ORGANIZING_BODY));
        mContactUses.add(new ContactUs("Sumedha Nallamilli","Publicity Head", "919441363744", ContactUs.PUBLICITY_TYPE_ORGANIZING_BODY));
        mContactUses.add(new ContactUs("Gowtham Dhanisetti","Firewallz Head", "+917674947271", ContactUs.PUBLICITY_TYPE_ORGANIZING_BODY));
        mContactUses.add(new ContactUs("Shreyas Reddy","Sponsorship Head", "+919389385938", ContactUs.PUBLICITY_TYPE_ORGANIZING_BODY));



        //For tech senate members:
        mContactUses.add(new ContactUs("Swapnil Tagwale","Cricket Captain", "+918465927512", ContactUs.PUBLICITY_TYPE_TECH_SENATE));
        mContactUses.add(new ContactUs("Abhimanyu Gulia","Football Captain(B)", "+919650441702", ContactUs.PUBLICITY_TYPE_TECH_SENATE));
        mContactUses.add(new ContactUs("Mrunal Dhaygude","Football Captain(G)", "+917350593598", ContactUs.PUBLICITY_TYPE_TECH_SENATE));
        mContactUses.add(new ContactUs("Hardik Jain","Hockey Captain", "+917087423740", ContactUs.PUBLICITY_TYPE_TECH_SENATE));
        mContactUses.add(new ContactUs("Shantanu Gupta","Basketball Captain(B)", "+917997167897", ContactUs.PUBLICITY_TYPE_TECH_SENATE));
        mContactUses.add(new ContactUs("Bhavika Sharma","Basketball Captain(G)", "+918340038686", ContactUs.PUBLICITY_TYPE_TECH_SENATE));
        mContactUses.add(new ContactUs("Rohit Reddy","Volleyball Captain(B)", "+919121408747", ContactUs.PUBLICITY_TYPE_TECH_SENATE));
        mContactUses.add(new ContactUs("Shibani","Volleyball Captain(G)", "+918016949292", ContactUs.PUBLICITY_TYPE_TECH_SENATE));
        mContactUses.add(new ContactUs("Sai Karthik","Kabaddi Captain", "+919182625689", ContactUs.PUBLICITY_TYPE_TECH_SENATE));
        mContactUses.add(new ContactUs("Utkarsh Grover","Badmintion Captain(B)", "+918368522551", ContactUs.PUBLICITY_TYPE_TECH_SENATE));
        mContactUses.add(new ContactUs("Snehha Tripathi","Badminton Captain(G)", "+919632967994", ContactUs.PUBLICITY_TYPE_TECH_SENATE));
        mContactUses.add(new ContactUs("Aayush Garg","Tennis Captain(B)", "+919909920479", ContactUs.PUBLICITY_TYPE_TECH_SENATE));
        mContactUses.add(new ContactUs("Shivangi Prasad","Tennis Captain(G)", "+919178459949", ContactUs.PUBLICITY_TYPE_TECH_SENATE));
        mContactUses.add(new ContactUs("Ishan Khasnis","Squash Captain(B)", "+919930895185", ContactUs.PUBLICITY_TYPE_TECH_SENATE));
        mContactUses.add(new ContactUs("Nitya Gupta","Squash Captain(G)", "+918826620119", ContactUs.PUBLICITY_TYPE_TECH_SENATE));
        mContactUses.add(new ContactUs("Shivaank Agarwal","TT Captain(B)", "+919867618713", ContactUs.PUBLICITY_TYPE_TECH_SENATE));
        mContactUses.add(new ContactUs("Aishvarya Srivastava","TT Captain(G)", "+919899011357", ContactUs.PUBLICITY_TYPE_TECH_SENATE));
        mContactUses.add(new ContactUs("Karan Grover","Chess Captain", "+917999882809", ContactUs.PUBLICITY_TYPE_TECH_SENATE));
        mContactUses.add(new ContactUs("Amitesh Sahu","Carrom Captain", "+918093596667", ContactUs.PUBLICITY_TYPE_TECH_SENATE));
        mContactUses.add(new ContactUs("Manoj kumar Raparthi","Snooker Captain", "+918501017080", ContactUs.PUBLICITY_TYPE_TECH_SENATE));
        mContactUses.add(new ContactUs("N Amsavalli","Throwball Captain", "+918500176854", ContactUs.PUBLICITY_TYPE_TECH_SENATE));
        mContactUses.add(new ContactUs("Sai Karthik","Kabaddi Captain", "+919182625689", ContactUs.PUBLICITY_TYPE_TECH_SENATE));




    }

    private void setOrganizingBody() {
        mOrganizingBodyTextView.setTextColor(getContext().getColor(R.color.background));
        mOrganizingBodyCardView.setCardBackgroundColor(getContext().getColor(R.color.colorPrimary));

        mTechSenateTextView.setTextColor(getContext().getColor(R.color.colorPrimary));
        mTechSenateCardView.setCardBackgroundColor(getContext().getColor(R.color.background));
    }

    private void setTechSenate() {
        mOrganizingBodyTextView.setTextColor(getContext().getColor(R.color.colorPrimary));
        mOrganizingBodyCardView.setCardBackgroundColor(getContext().getColor(R.color.background));

        mTechSenateTextView.setTextColor(getContext().getColor(R.color.background));
        mTechSenateCardView.setCardBackgroundColor(getContext().getColor(R.color.colorPrimary));
    }

}