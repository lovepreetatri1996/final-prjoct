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
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.asus.mainproject.Events_Detailes;
import com.example.asus.mainproject.FilterEventActivity;
import com.example.asus.mainproject.Joining_Events_Detailes;
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

/**
 * A simple {@link Fragment} subclass.
 */
public class JoiningEventFragment extends Fragment {


    private RecyclerView recyclerView ;

    public static List<EventData> list ;

    public static   Adpater adapter ;

    private List<JoinEvent> join_list ;

    public JoiningEventFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_joining_event, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        join_list = new ArrayList<>();


        recyclerView = view.findViewById(R.id.recyler);

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext() , LinearLayoutManager.VERTICAL , false));

        list = new ArrayList<>();

        adapter = new Adpater();

        recyclerView.setAdapter(adapter);

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


                        for(JoinEvent join_data : join_list)
                        {
                            if(join_data.event_key.equals(snap2.getKey()))
                            {
                                EventData data2 = new EventData(data.name , data.date , data.time ,data.description , data.activity_one , data.activity_two , data.activity_three ,
                                        data.activity_four , data.activity_five  , data.location , join_data.role ,  snap2.getKey() , snap.getKey());

                                list.add(data2);

                            }
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


    public class view_holder extends RecyclerView.ViewHolder
    {

        public TextView event_name , event_date , event_location ;
        public LinearLayout event_cell;

        public view_holder(View itemView) {
            super(itemView);

            event_name = itemView.findViewById(R.id.event_name);

            event_date = itemView.findViewById(R.id.date_);

            event_location = itemView.findViewById(R.id.location_);
            event_cell=itemView.findViewById(R.id.event_cell);
        }
    }

    public class Adpater extends RecyclerView.Adapter<view_holder>
    {

        @Override
        public view_holder onCreateViewHolder(ViewGroup parent, int viewType)
        {
            return new view_holder(LayoutInflater.from(getContext()).inflate(R.layout.single_event_cell , parent , false));
        }

        @Override
        public void onBindViewHolder(view_holder holder, int position) {


            final EventData data = list.get(position);

            holder.event_name.setText(data.name);

            holder.event_date.setText(data.date);

            holder.event_location.setText(data.location);

            holder.event_cell.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Intent i = new Intent(getContext() , Joining_Events_Detailes.class);

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
                    i.putExtra("member_required" , data.members_required);

                    i.putExtra("event_email_key" , data.email_key);
                    i.putExtra("event_key" , data.key);
                    i.putExtra("role" , data.members_required);

                    startActivity(i);



                }
            });

        }

        @Override
        public int getItemCount() {
            return list.size();
        }
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




}
