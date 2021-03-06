package com.example.asus.mainproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.asus.mainproject.dataModels.EventData;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Events_of_user extends AppCompatActivity {

    List<EventData> list ;

    private RecyclerView recyclerView ;


    Events_of_user.Adpater adapter ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_event_info);

        list = new ArrayList<>();

        recyclerView = findViewById(R.id.recyler);

        recyclerView.setLayoutManager(new LinearLayoutManager(Events_of_user.this, LinearLayoutManager.VERTICAL , false));

        list = new ArrayList<>();

        adapter = new Events_of_user.Adpater();

        recyclerView.setAdapter(adapter);

        get_event_data();
    }
    public void get_event_data(){
        FirebaseDatabase database = FirebaseDatabase.getInstance();

        FirebaseAuth auth = FirebaseAuth.getInstance();

        String email = auth.getCurrentUser().getEmail();
        database.getReference().child("event").child(email.replace(".","")).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                list.clear();

                for(DataSnapshot d : dataSnapshot.getChildren())
                {
                    EventData data = d.getValue(EventData.class);

                    EventData data2 = new EventData(data.name , data.date , data.time ,data.description , data.activity_one , data.activity_two , data.activity_three ,
                            data.activity_four , data.activity_five  , data.location , d.getKey());
                    list.add(data2);





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

    public class Adpater extends RecyclerView.Adapter<Events_of_user.view_holder>
    {

        @Override
        public Events_of_user.view_holder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new Events_of_user.view_holder(LayoutInflater.from(parent.getContext()).inflate(R.layout.single_event_cell , parent , false));
        }

        @Override
        public void onBindViewHolder(Events_of_user.view_holder holder, int position) {


            final EventData data = list.get(position);

            holder.event_name.setText(data.name);

            holder.event_date.setText(data.date);

            holder.event_location.setText(data.location);

            holder.event_cell.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Intent i = new Intent(Events_of_user.this , SingleCellEdit.class);

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
                    i.putExtra("member_required" , data.members_required );

                    i.putExtra("key" , data.key);

                    startActivity(i);



                }
            });

        }

        @Override
        public int getItemCount() {
            return list.size();
        }
    }
    public void finsh_back(View view) {
        finish();
    }
}

