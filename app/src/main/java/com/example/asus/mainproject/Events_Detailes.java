package com.example.asus.mainproject;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.asus.mainproject.dataModels.JoinEvent;
import com.example.asus.mainproject.fragments.Events;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Events_Detailes extends AppCompatActivity {



    private CheckBox c_doctor,c_sweeper,c_transportation,c_video,c_clockroom,c_infodesk,c_accountant,c_electric,c_network,c_waste,c_helper;

    private ImageView clock,doctor,sweeper,transprotation,video,clockroom,infodesk,accountant,electri,network,waste,helper;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_events__detailes);





        doctor= findViewById(R.id.doctor);
        sweeper= findViewById(R.id.sweeper);
        transprotation= findViewById(R.id.transportation);
        video= findViewById(R.id.videographer);
        clockroom= findViewById(R.id.clock_room_manager);
        infodesk= findViewById(R.id.infodesk);
        accountant= findViewById(R.id.accountant);
        network= findViewById(R.id.network);
        electri= findViewById(R.id.electrician);
        waste= findViewById(R.id.waste_picker);
        helper= findViewById(R.id.helper);



        TextView event_name=findViewById(R.id.event_name);
        TextView event_date=findViewById(R.id.dateofbrith);
        TextView event_time=findViewById(R.id.select_time);
        TextView event_description=findViewById(R.id.event_description);
        TextView event_activity_one=findViewById(R.id.activity_one);
        TextView event_activity_two=findViewById(R.id.activity_two);
        TextView event_activity_three=findViewById(R.id.activity_three);
        TextView event_activity_four=findViewById(R.id.activity_four);
        TextView event_activity_five=findViewById(R.id.activity_five);


        c_doctor=findViewById(R.id.c_doctor);
        c_sweeper=findViewById(R.id.c_sweeper);
        c_accountant=findViewById(R.id.c_account);
        c_clockroom=findViewById(R.id.c_clock_room);
        c_helper=findViewById(R.id.c_helper);
        c_waste=findViewById(R.id.c_waste);
        c_electric=findViewById(R.id.c_electri);
        c_infodesk=findViewById(R.id.c_info);
        c_network=findViewById(R.id.c_network);
        c_video=findViewById(R.id.c_video);
        c_transportation=findViewById(R.id.c_transportation);




        String name = getIntent().getStringExtra("name");
        String date = getIntent().getStringExtra("date");
        String location= getIntent().getStringExtra("location");
        String time = getIntent().getStringExtra("time");
        String eventdescription = getIntent().getStringExtra("event_description");
        String activityone= getIntent().getStringExtra("activityone");
        String activitytwo= getIntent().getStringExtra("activitytwo");
        String activitythree= getIntent().getStringExtra("activitythree");
        String activityfour= getIntent().getStringExtra("activityfour");
        String activityfive= getIntent().getStringExtra("activityfive");



        event_name.setText(name);
        event_date.setText(date);
        event_time.setText(time);
        event_description.setText(eventdescription);
        event_activity_one.setText(activityone);
        event_activity_two.setText(activitytwo);
        event_activity_three.setText(activitythree);
        event_activity_four.setText(activityfour);
        event_activity_five.setText(activityfive);


        get_all_joining_events();



    }

    public void back_back(View view) 
    {
        finish();
    }

   /* public void join(View view) {

        if(join_btn.getText().toString().toLowerCase().equals("unjoin")) {
            FirebaseAuth auth = FirebaseAuth.getInstance();

            String email = auth.getCurrentUser().getEmail().replace(".", "");

            FirebaseDatabase database = FirebaseDatabase.getInstance();

            JoinEvent event = new JoinEvent(getIntent().getStringExtra("event_email_key"), getIntent().getStringExtra("event_key"));

            database.getReference().child("joined_event").child(email).child(getIntent().getStringExtra("event_key")).setValue(null);

            join_btn.setText("JOIN");
        }

        else {
            FirebaseAuth auth = FirebaseAuth.getInstance();

            String email = auth.getCurrentUser().getEmail().replace(".", "");

            FirebaseDatabase database = FirebaseDatabase.getInstance();

            JoinEvent event = new JoinEvent(getIntent().getStringExtra("event_email_key"), getIntent().getStringExtra("event_key"));

            database.getReference().child("joined_event").child(email).child(getIntent().getStringExtra("event_key")).setValue(event).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {

                    if (task.isSuccessful()) {
                        Toast.makeText(Events_Detailes.this, "Event joined", Toast.LENGTH_SHORT).show();
                    }
                }
            });

            join_btn.setText("UNJOIN");
        }

    }*/

    private void get_joining_events()
    {

        FirebaseAuth auth = FirebaseAuth.getInstance();

        String email = auth.getCurrentUser().getEmail().replace(".","");

        FirebaseDatabase database = FirebaseDatabase.getInstance();

        database.getReference().child("joined_event").child(email).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for ( DataSnapshot dataSnapshot1 : dataSnapshot.getChildren())
                {
                    JoinEvent data = dataSnapshot1.getValue(JoinEvent.class);

                    if(data.event_key.equals(getIntent().getStringExtra("event_key")))
                    {
                       for(int i = 0 ; i < data.role.split(",").length ; i ++)
                       {
                           String rol = data.role.split(",")[i];

                           if(rol.equals(""))
                           {

                           }

                           else {

                               if(rol.equals("doctor"))
                               {
                                   c_doctor.setChecked(true);
                               }

                               if(rol.equals("sweeper"))
                               {
                                   c_sweeper.setChecked(true);
                               }

                               if(rol.equals("info desk"))
                               {
                                   c_infodesk.setChecked(true);
                               }
                               if (rol.equals("video")) {

                                   c_video.setChecked(true);
                               }
                               if (rol.equals("transportation")) {

                                   c_transportation.setChecked(true);
                               }
                               if (rol.equals("clock room")) {

                                   c_clockroom.setChecked(true);
                               }
                               if (rol.equals("network")) {

                                   c_network.setChecked(true);

                               }
                               if (rol.equals("helper")) {

                                   c_helper.setChecked(true);
                               }
                               if (rol.equals("waste")) {

                                   c_waste.setChecked(true);
                               }
                               if (rol.equals("account")) {

                                   c_accountant.setChecked(true);
                               }
                               if (rol.equals("electric")) {

                                   c_electric.setChecked(true);
                               }
                           }
                       }
                    }

                }


            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });



    }


    private void get_all_joining_events()
    {

        final EditText doctor_et=findViewById(R.id.doctor_txt);
        final EditText sweeper_et=findViewById(R.id.sweeper_txt);
        final EditText account_et=findViewById(R.id.accountant_txt);
        final EditText electric_et=findViewById(R.id.electrician_txt);
        final EditText network_et=findViewById(R.id.network_txt);
        final EditText helper_et=findViewById(R.id.helper_txt);
        final EditText waste_et=findViewById(R.id.waste_picker_txt);
        final EditText transport_et=findViewById(R.id.transportation_txt);
        final EditText infodesk_et=findViewById(R.id.info_txt);
        final EditText clock_et=findViewById(R.id.clock_txt);
        final EditText video_et=findViewById(R.id.videographer_txt);


        final   TextView doctor_z=findViewById(R.id.doctor_t);
        final  TextView sweeper_z=findViewById(R.id.sweeper_t);
        final  TextView account_z=findViewById(R.id.accountant_t);
        final  TextView clock_z=findViewById(R.id.clock_room_manager_t);
        final  TextView helper_z=findViewById(R.id.helper_t);
        final  TextView transportation_z=findViewById(R.id.transportation_t);
        final  TextView waste_z=findViewById(R.id.waste_picker_t);
        final  TextView electric_z=findViewById(R.id.electrician_t);
        final  TextView network_z=findViewById(R.id.network_t);
        final  TextView infodesk_z=findViewById(R.id.info_t);
        final  TextView video_z=findViewById(R.id.videographer_t);


        FirebaseAuth auth = FirebaseAuth.getInstance();

        String email = auth.getCurrentUser().getEmail().replace(".","");

        FirebaseDatabase database = FirebaseDatabase.getInstance();

        database.getReference().child("joined_event").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                int doctor_num = 0 , sweeper_num = 0 , info_desk_num = 0,account_num = 0,clock_num = 0,helper_num = 0,transportation_num = 0,waste_num = 0,electric_num = 0,network_num = 0,video_num = 0 ;

                for ( DataSnapshot dataSnapshot1 : dataSnapshot.getChildren())
                {

                    for(DataSnapshot dataSnapshot2 : dataSnapshot1.getChildren()) {
                        JoinEvent data = dataSnapshot2.getValue(JoinEvent.class);

                        if (data.event_key.equals(getIntent().getStringExtra("event_key")))
                        {


                            for (int i = 0; i < data.role.split(",").length; i++) {
                                String rol = data.role.split(",")[i];

                                if (rol.equals(""))
                                {

                                }
                                else
                                    {

                                    if (rol.equals("doctor")) {

                                        doctor_num ++;
                                    }

                                    if (rol.equals("sweeper")) {

                                        sweeper_num ++;
                                    }

                                    if (rol.equals("info desk")) {

                                        info_desk_num ++;
                                    }
                                        if (rol.equals("video")) {

                                            video_num ++;
                                        }
                                        if (rol.equals("transportation")) {

                                           transportation_num ++;
                                        }
                                        if (rol.equals("clock room")) {

                                            clock_num ++;
                                        }
                                        if (rol.equals("network")) {

                                            network_num ++;

                                        }
                                        if (rol.equals("helper")) {

                                            helper_num++;
                                        }
                                        if (rol.equals("waste")) {

                                            waste_num ++;
                                        }
                                        if (rol.equals("account")) {

                                            account_num ++;
                                        }
                                        if (rol.equals("electric")) {

                                            electric_num ++;
                                        }

                                }
                            }
                        }

                    }



                }

                String member_required = getIntent().getStringExtra("member_required");

                try {
                    JSONArray jsonArray = new JSONArray(member_required);

                    for(int i = 0 ; i < jsonArray.length() ; i ++)
                    {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);

                        if(jsonObject.getString("name").equals("doctor"))
                        {
                            doctor_z.setVisibility(View.VISIBLE);

                            c_doctor.setVisibility(View.VISIBLE);

                            doctor.setVisibility(View.VISIBLE);

                            doctor_et.setVisibility(View.VISIBLE);

                            doctor_et.setText(String.valueOf(doctor_num)+"/"+jsonObject.getString("number"));
                        }

                        if(jsonObject.getString("name").equals("sweeper"))
                        {
                            sweeper_z.setVisibility(View.VISIBLE);

                            c_sweeper.setVisibility(View.VISIBLE);

                            sweeper.setVisibility(View.VISIBLE);

                            sweeper_et.setVisibility(View.VISIBLE);

                            sweeper_et.setText(String.valueOf(sweeper_num)+"/"+jsonObject.getString("number"));
                        }

                        if(jsonObject.getString("name").equals( "transportation"))
                        {
                            transportation_z.setVisibility(View.VISIBLE);

                            c_transportation.setVisibility(View.VISIBLE);

                            transprotation.setVisibility(View.VISIBLE);

                            transport_et.setVisibility(View.VISIBLE);

                            transport_et.setText(String.valueOf(transportation_num)+"/"+jsonObject.getString("number"));
                        }

                        if(jsonObject.getString("name").equals( "video"))
                        {
                            video_z.setVisibility(View.VISIBLE);

                            c_video.setVisibility(View.VISIBLE);

                            video.setVisibility(View.VISIBLE);

                            video_et.setVisibility(View.VISIBLE);

                            video_et.setText(String.valueOf(video_num)+"/"+jsonObject.getString("number"));
                        }
                        if(jsonObject.getString("name").equals( "clock"))
                        {
                            clock_z.setVisibility(View.VISIBLE);

                            c_clockroom.setVisibility(View.VISIBLE);

                            clock.setVisibility(View.VISIBLE);

                            clock_et.setVisibility(View.VISIBLE);

                            clock_et.setText(String.valueOf(clock_num)+"/"+jsonObject.getString("number"));
                        }
                        if(jsonObject.getString("name").equals( "info desk"))
                        {
                            infodesk_z.setVisibility(View.VISIBLE);

                            c_infodesk.setVisibility(View.VISIBLE);

                            infodesk.setVisibility(View.VISIBLE);

                            infodesk_et.setVisibility(View.VISIBLE);

                            infodesk_et.setText(String.valueOf(info_desk_num)+"/"+jsonObject.getString("number"));
                        }
                        if(jsonObject.getString("name").equals( "network"))
                        {
                            network_z.setVisibility(View.VISIBLE);

                            c_network.setVisibility(View.VISIBLE);

                            network.setVisibility(View.VISIBLE);

                            network_et.setVisibility(View.VISIBLE);

                            network_et.setText(String.valueOf(network_num)+"/"+jsonObject.getString("number"));
                        }
                        if(jsonObject.getString("name").equals( "accountant"))
                        {
                            account_z.setVisibility(View.VISIBLE);

                            c_accountant.setVisibility(View.VISIBLE);

                            accountant.setVisibility(View.VISIBLE);

                            account_et.setVisibility(View.VISIBLE);

                            account_et.setText(String.valueOf(account_num)+"/"+jsonObject.getString("number"));
                        }
                        if(jsonObject.getString("name").equals( "Electric"))
                        {
                            electric_z.setVisibility(View.VISIBLE);

                            c_electric.setVisibility(View.VISIBLE);

                            electri.setVisibility(View.VISIBLE);

                            electric_et.setVisibility(View.VISIBLE);

                            electric_et.setText(String.valueOf(electric_num)+"/"+jsonObject.getString("number"));
                        }
                        if(jsonObject.getString("name").equals( "waste"))
                        {
                            waste_z.setVisibility(View.VISIBLE);

                            c_waste.setVisibility(View.VISIBLE);

                            waste.setVisibility(View.VISIBLE);

                            waste_et.setVisibility(View.VISIBLE);

                            waste_et.setText(String.valueOf(waste_num)+"/"+jsonObject.getString("number"));
                        }
                        if(jsonObject.getString("name").equals( "helper"))
                        {
                            helper_z.setVisibility(View.VISIBLE);

                            c_helper.setVisibility(View.VISIBLE);

                            helper.setVisibility(View.VISIBLE);

                            helper_et.setVisibility(View.VISIBLE);

                            helper_et.setText(String.valueOf(helper_num)+"/"+jsonObject.getString("number"));
                        }


                    }

                    get_joining_events();

                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });



    }

    public void join_event(View view) {

        String role = "";

        if(c_doctor.isChecked())
        {

           role = role+"doctor,";
        }

        if(c_sweeper.isChecked())
        {
            role = role+"sweeper,";

        }


        if(c_transportation.isChecked())
        {
            role = role+"transportation,";

        }

        if(c_video.isChecked())
        {
           role=role+"video,";

        }

        if(c_clockroom.isChecked())
        {
           /* try {
                JSONObject jsonObject = new JSONObject();

                jsonObject.put("name" , "clock");
                jsonObject.put("number" , e_clock);
                jsonArray.put(jsonObject);

            } catch (JSONException e) {
                e.printStackTrace();
            }*/
        }

        if(c_infodesk.isChecked())
        {
            role = role+ "info desk,";
           /* try {
                JSONObject jsonObject = new JSONObject();

                jsonObject.put("name" , "info desk");
                jsonObject.put("number" , e_infodesk);
                jsonArray.put(jsonObject);

            } catch (JSONException e) {
                e.printStackTrace();
            }*/
        }

        if(c_network.isChecked())
        {
          /*  try {
                JSONObject jsonObject = new JSONObject();

                jsonObject.put("name" , "network");
                jsonObject.put("number" , e_network);
                jsonArray.put(jsonObject);

            } catch (JSONException e) {
                e.printStackTrace();
            }*/
        }

        if(c_accountant.isChecked())
        {
           /* try {
                JSONObject jsonObject = new JSONObject();

                jsonObject.put("name" , "accountant");
                jsonObject.put("number" , e_account);
                jsonArray.put(jsonObject);

            } catch (JSONException e) {
                e.printStackTrace();
            }*/
        }


        if(c_electric.isChecked())
        {
           /* try {
                JSONObject jsonObject = new JSONObject();

                jsonObject.put("name" , "Electric");
                jsonObject.put("number" , e_electri);
                jsonArray.put(jsonObject);

            } catch (JSONException e) {
                e.printStackTrace();
            }*/
        }



        if(c_waste.isChecked())
        {
           /* try {
                JSONObject jsonObject = new JSONObject();

                jsonObject.put("name" , "waste");
                jsonObject.put("number" , e_waste);
                jsonArray.put(jsonObject);

            } catch (JSONException e) {
                e.printStackTrace();
            }*/
        }

        if(c_helper.isChecked())
        {
           /* try {
                JSONObject jsonObject = new JSONObject();

                jsonObject.put("name" , "helper");
                jsonObject.put("number" , e_helper);
                jsonArray.put(jsonObject);

            } catch (JSONException e) {
                e.printStackTrace();
            }*/
        }


        if(role.equals(""))
        {
            Toast.makeText(Events_Detailes.this , "please select atleast one role" , Toast.LENGTH_SHORT).show();
            return;
        }

        if(role.split(",").length >= 3)
        {
            Toast.makeText(Events_Detailes.this , "you can only select two roles" , Toast.LENGTH_SHORT).show();
            return;
        }


        FirebaseAuth auth = FirebaseAuth.getInstance();

        String email = auth.getCurrentUser().getEmail().replace(".", "");

        FirebaseDatabase database = FirebaseDatabase.getInstance();

        JoinEvent event = new JoinEvent(getIntent().getStringExtra("event_email_key"), getIntent().getStringExtra("event_key") , role);

        database.getReference().child("joined_event").child(email).child(getIntent().getStringExtra("event_key")).setValue(event);




    }


}
