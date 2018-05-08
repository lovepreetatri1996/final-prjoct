package com.example.asus.mainproject.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.asus.mainproject.Events_Detailes;
import com.example.asus.mainproject.FilterEventActivity;
import com.example.asus.mainproject.R;
import com.example.asus.mainproject.dataModels.EventData;
import com.example.asus.mainproject.dataModels.JoinEvent;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


public class Events extends Fragment {

    public Events() {
        // Required empty public constructor
    }



    private LinearLayout filter_icon ;

    private RecyclerView recyclerView ;

    public static List<EventData> list ;

    public static   Events.Adpater adapter ;

    private List<JoinEvent> join_list ;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_events, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        filter_icon = view.findViewById(R.id.filter_icon);

        join_list = new ArrayList<>();


        recyclerView = view.findViewById(R.id.recyler);

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext() , LinearLayoutManager.VERTICAL , false));

        list = new ArrayList<>();

        adapter = new Events.Adpater();

        recyclerView.setAdapter(adapter);

        filter_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(getContext() , FilterEventActivity.class);

                startActivity(i);
            }
        });

        get_joining_events();

    }

    private void get_data()

    {
        final FirebaseDatabase database = FirebaseDatabase.getInstance();

        database.getReference().child("event").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                list.clear();

                for ( DataSnapshot snap : dataSnapshot.getChildren() )
                {
                    for ( DataSnapshot snap2 : snap.getChildren())
                    {
                        EventData data = snap2.getValue(EventData.class);

                        EventData data2 = new EventData(data.name , data.date , data.time ,data.description , data.activity_one , data.activity_two , data.activity_three ,
                                data.activity_four , data.activity_five  , data.location , data.members_required , snap2.getKey() , snap.getKey() );
                        list.add(data2);


                    }
                }

                adapter.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });




    }


    public class view_holder extends RecyclerView.ViewHolder
    {

        public TextView event_name , event_date , event_location ;
        public LinearLayout event_cell;

        public ImageView joined_event ;



        public view_holder(View itemView) {
            super(itemView);

            event_name = itemView.findViewById(R.id.event_name);

            event_date = itemView.findViewById(R.id.date_);

            event_location = itemView.findViewById(R.id.location_);
            event_cell=itemView.findViewById(R.id.event_cell);

            joined_event = itemView.findViewById(R.id.joined_event);




        }
    }

    public class Adpater extends RecyclerView.Adapter<view_holder>
    {

        @Override
        public view_holder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new view_holder(LayoutInflater.from(getContext()).inflate(R.layout.single_event_cell , parent , false));
        }

        @Override
        public void onBindViewHolder(view_holder holder, int position) {


            final EventData data = list.get(position);

            holder.event_name.setText(data.name);

            holder.event_date.setText(data.date);

            holder.event_location.setText(data.location);

            holder.joined_event.setVisibility(View.GONE);

            for(JoinEvent join_data : join_list)
            {
                if(join_data.event_key.equals(data.key))
                {
                    holder.joined_event.setVisibility(View.VISIBLE);
                    break;

                }
            }

            holder.event_cell.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Intent i = new Intent(getContext() , Events_Detailes.class);

                    i.putExtra("name" , data.name);
                    i.putExtra("date" , data.date);
                    i.putExtra("location",data.location);
                    i.putExtra("time" , data.time);
                    i.putExtra("event_description" , data.description);
                    i.putExtra("activityone" , data.activity_one);
                    i.putExtra("activitytwo" , data.activity_two);
                    i.putExtra("activitythree" , data.activity_three);
                    i.putExtra("activityfour" , data.activity_four);
                    i.putExtra("activityfive" , data.activity_five);


                    i.putExtra("event_email_key" , data.email_key);
                    i.putExtra("event_key" , data.key);
                    i.putExtra("member_required" , data.members_required);

                    startActivity(i);



                }
            });

        }

        @Override
        public int getItemCount() {
            return list.size();
        }
    }


    public static void get_data(final String location)

    {
        if(location.length() <2)
        {
            return;
        }
        list.clear();

        final FirebaseDatabase database = FirebaseDatabase.getInstance();

        database.getReference().child("event").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for ( DataSnapshot snap : dataSnapshot.getChildren() )
                {
                    for ( DataSnapshot snap2 : snap.getChildren())
                    {
                        EventData data = snap2.getValue(EventData.class);

                        if(data.location.contains(location)) {
                            EventData data2 = new EventData(data.name, data.date, data.time, data.description, data.activity_one, data.activity_two, data.activity_three,
                                    data.activity_four, data.activity_five,  data.location, snap2.getKey(), snap.getKey());
                            list.add(data2);

                        }

                    }
                }

                adapter.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });




    }

    private void get_joining_events()
    {


        FirebaseAuth auth = FirebaseAuth.getInstance();

        String email = auth.getCurrentUser().getEmail().replace(".","");

        FirebaseDatabase database = FirebaseDatabase.getInstance();

        database.getReference().child("joined_event").child(email).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                join_list.clear();

                for ( DataSnapshot dataSnapshot1 : dataSnapshot.getChildren())
                {
                    JoinEvent data = dataSnapshot1.getValue(JoinEvent.class);

                    join_list.add(data);
                }

                get_data();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });



    }


    private void get_events_rating()
    {


        FirebaseAuth auth = FirebaseAuth.getInstance();

        String email = auth.getCurrentUser().getEmail().replace(".","");

        FirebaseDatabase database = FirebaseDatabase.getInstance();

        database.getReference().child("event_rating").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                join_list.clear();

                for ( DataSnapshot dataSnapshot1 : dataSnapshot.getChildren())
                {
                    JoinEvent data = dataSnapshot1.getValue(JoinEvent.class);

                    join_list.add(data);
                }

                get_data();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });



    }




}
