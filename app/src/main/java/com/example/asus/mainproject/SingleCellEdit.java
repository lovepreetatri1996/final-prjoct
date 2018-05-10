package com.example.asus.mainproject;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.asus.mainproject.dataModels.EventData;
import com.example.asus.mainproject.dataModels.ProfileData;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Calendar;

import static com.example.asus.mainproject.fragments.Add_Event_Detailes.place_s;

public class SingleCellEdit extends AppCompatActivity {

    private CheckBox c_doctor,c_sweeper,c_transportation,c_video,c_clockroom,c_infodesk,c_accountant,c_electric,c_network,c_waste,c_helper;

    private ImageView clock,doctor,sweeper,transprotation,video,clockroom,infodesk,accountant,electri,network,waste,helper;

    public static TextView place_txt ;

    public  static   String place_s ;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_cell_edit);

        ImageView cal = findViewById(R.id.calandar);

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
        c_transportation= findViewById(R.id.c_transportation);

        place_txt = findViewById(R.id.place_txt);


        place_txt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivityForResult( new Intent(SingleCellEdit.this , PlacePickActivity.class) , 100);
            }
        });




        final EditText event_name=findViewById(R.id.event_name);
    final EditText event_date=findViewById(R.id.dateofbrith);
    final EditText event_time=findViewById(R.id.select_time);
    EditText event_description=findViewById(R.id.event_description);
    EditText event_activity_one=findViewById(R.id.activity_one);
    EditText event_activity_two=findViewById(R.id.activity_two);
    EditText event_activity_three=findViewById(R.id.activity_three);
    EditText event_activity_four=findViewById(R.id.activity_four);
    EditText event_activity_five=findViewById(R.id.activity_five);



        final EditText doctor=findViewById(R.id.doctor_txt);
        final EditText sweeper=findViewById(R.id.sweeper_txt);
        final EditText account=findViewById(R.id.accountant_txt);
        final EditText electric=findViewById(R.id.electrician_txt);
        final EditText network=findViewById(R.id.network_txt);
        final EditText helper=findViewById(R.id.helper_txt);
        final EditText waste=findViewById(R.id.waste_picker_txt);
        final EditText transport=findViewById(R.id.transportation_txt);
        final EditText infodesk=findViewById(R.id.info_txt);
        final EditText clock_et=findViewById(R.id.clock_txt);
        final EditText video=findViewById(R.id.videographer_txt);




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

    String role = getIntent().getStringExtra("member_required");

    System.out.println("role is ****************************** "+role);

        try {
            JSONArray jsonArray_roles = new JSONArray(role);

            for(int i = 0 ; i < jsonArray_roles.length() ; i ++)
            {
                JSONObject jsonObject = jsonArray_roles.getJSONObject(i);

                String rr = jsonObject.getString("name");

                String member_required = jsonObject.getString("number");

                if(rr.toLowerCase().contains("doctor"))
                {
                    c_doctor.setChecked(true);
                    doctor.setText(member_required);
                }

                if(rr.toLowerCase().contains("sweeper"))
                {
                    c_sweeper.setChecked(true);
                    sweeper.setText(member_required);
                }

                if(rr.toLowerCase().contains("info desk"))
                {
                    c_infodesk.setChecked(true);
                    infodesk.setText(member_required);
                }
                if (rr.toLowerCase().contains("video ")) {
                    c_video.setChecked(true);
                    video.setText(member_required);
                }
                if (rr.toLowerCase().contains("transportation")) {

                    c_transportation.setChecked(true);
                    transport.setText(member_required);
                }
                if (rr.toLowerCase().contains("clock ")) {

                    c_clockroom.setChecked(true);

                    clock_et.setText(member_required);
                }
                if (rr.toLowerCase().contains("network ")) {

                    c_network.setChecked(true);

                    network.setText(member_required);

                }
                if (rr.toLowerCase().contains("helper")) {

                    c_helper.setChecked(true);

                    helper.setText(member_required);
                }
                if (rr.toLowerCase().contains("waste ")) {

                    c_waste.setChecked(true);
                    waste.setText(member_required);
                }
                if (rr.toLowerCase().contains("account")) {

                    c_accountant.setChecked(true);
                    account.setText(member_required);
                }
                if (rr.toLowerCase().contains("electric")) {

                    c_electric.setChecked(true);
                    electric.setText(member_required);
                }

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        event_name.setText(name);
        event_date.setText(date);
        event_time.setText(time);
        event_description.setText(eventdescription);
        event_activity_one.setText(activityone);
        event_activity_two.setText(activitytwo);
        event_activity_three.setText(activitythree);
        event_activity_four.setText(activityfour);
        event_activity_five.setText(activityfive);



        place_txt.setText(location);



        cal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {

                Calendar c = Calendar.getInstance();



                DatePickerDialog datePickerDialog = new DatePickerDialog(SingleCellEdit.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                        event_date.setText(String.valueOf(i2) + " /" + String.valueOf(i1 + 1)+"/"+String.valueOf(i));


                    }
                }, c.get(Calendar.YEAR) , c.get(Calendar.MONTH) , c.get(Calendar.DAY_OF_WEEK) );

                datePickerDialog.show();
            }
        });

        final Calendar call = Calendar.getInstance();
       int   year_x = call.get(Calendar.YEAR);
       int  month_x = call.get(Calendar.MONTH);
       int day_x = call.get(Calendar.DAY_OF_MONTH);


        ImageView  clock = findViewById(R.id.clock);



        clock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Calendar c = Calendar.getInstance();

                int hour = c.get(Calendar.HOUR);
                int minute = c.get(Calendar.MINUTE);


                TimePickerDialog time_dialog = new TimePickerDialog(SingleCellEdit.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int i, int i1) {

                        event_time.setText(String.valueOf(i)+":"+String.valueOf(i1));


                    }
                }, hour, minute, true);

                time_dialog.show();
            }
        });

}

    public void update(View view) {

        JSONArray jsonArray = new JSONArray();

        final EditText event_name=findViewById(R.id.event_name);
        final EditText event_date=findViewById(R.id.dateofbrith);
        final EditText event_time=findViewById(R.id.select_time);
        final EditText event_description=findViewById(R.id.event_description);
        final EditText event_activity_one=findViewById(R.id.activity_one);
        final EditText event_activity_two=findViewById(R.id.activity_two);
        final EditText event_activity_three=findViewById(R.id.activity_three);
        final EditText event_activity_four=findViewById(R.id.activity_four);
        final EditText event_activity_five=findViewById(R.id.activity_five);



        final String e_name=event_name.getText().toString();
        final String e_date=event_date.getText().toString();
        final String e_time=event_time.getText().toString();
        final String e_description=event_description.getText().toString();
        final String e_activity_one=event_activity_one.getText().toString();
        final String e_activity_two=event_activity_two.getText().toString();
        final String e_activity_three=event_activity_three.getText().toString();
        final String e_activity_four=event_activity_four.getText().toString();
        final String e_activity_five=event_activity_five.getText().toString();



        if(e_name.length() <=4 )
        {
            event_name.setError("name must be of minimum 4 characters");
            return;
        }
        if(e_date.length() == 0 )
        {
            event_date.setError( "please enter date of the event" );
            return;
        }
        if(e_time.length() == 0 )
        {
            event_time.setError( "please enter time of the event" );
            return;
        }

        if(e_description.length() <=20 )
        {
            event_description.setError("name must be of minimum 40 characters");
            return;
        }

        if(e_activity_one.length() <=10 )
        {
            event_activity_one.setError("name must be of minimum 30 characters");
            return;
        }

        if(e_activity_two.length() <=10 )
        {
            event_activity_two.setError("name must be of minimum 30 characters");
            return;
        }
        if(e_activity_three.length() <=10 )
        {
            event_activity_three.setError("name must be of minimum 30 characters");
            return;
        }
        if(e_activity_four.length() <=10 )
        {
            event_activity_four.setError("name must be of minimum 30 characters");
            return;
        }
        if(e_activity_five.length() <=10 )
        {
            event_activity_five.setError("name must be of minimum 30 characters");
            return;
        }

        final EditText doctor=findViewById(R.id.doctor_txt);
        final EditText sweeper=findViewById(R.id.sweeper_txt);
        final EditText account=findViewById(R.id.accountant_txt);
        final EditText electric=findViewById(R.id.electrician_txt);
        final EditText network=findViewById(R.id.network_txt);
        final EditText helper=findViewById(R.id.helper_txt);
        final EditText waste=findViewById(R.id.waste_picker_txt);
        final EditText transport=findViewById(R.id.transportation_txt);
        final EditText infodesk=findViewById(R.id.info_txt);
        final EditText clock=findViewById(R.id.clock_txt);
        final EditText video=findViewById(R.id.videographer_txt);



        final String e_doctor=doctor.getText().toString();
        final String e_sweeper=sweeper.getText().toString();
        final String e_transportation=transport.getText().toString();
        final String e_video=video.getText().toString();
        final String e_clock=clock.getText().toString();
        final String e_infodesk=infodesk.getText().toString();
        final String e_network=network.getText().toString();
        final String e_account=account.getText().toString();
        final String e_electri=electric.getText().toString();
        final String e_waste=waste.getText().toString();
        final String e_helper=helper.getText().toString();


        if(c_doctor.isChecked())
        {
            try {
                JSONObject jsonObject = new JSONObject();

                jsonObject.put("name" , "doctor");
                jsonObject.put("number" , e_doctor);
                jsonArray.put(jsonObject);

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        if(c_sweeper.isChecked())
        {
            try {
                JSONObject jsonObject = new JSONObject();

                jsonObject.put("name" , "sweeper");
                jsonObject.put("number" , e_sweeper);
                jsonArray.put(jsonObject);

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }


        if(c_transportation.isChecked())
        {
            try {
                JSONObject jsonObject = new JSONObject();

                jsonObject.put("name" , "transportation");
                jsonObject.put("number" , e_transportation);
                jsonArray.put(jsonObject);

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        if(c_video.isChecked())
        {
            try {
                JSONObject jsonObject = new JSONObject();

                jsonObject.put("name" , "video");
                jsonObject.put("number" , e_video);
                jsonArray.put(jsonObject);

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        if(c_clockroom.isChecked())
        {
            try {
                JSONObject jsonObject = new JSONObject();

                jsonObject.put("name" , "clock");
                jsonObject.put("number" , e_clock);
                jsonArray.put(jsonObject);

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        if(c_infodesk.isChecked())
        {
            try {
                JSONObject jsonObject = new JSONObject();

                jsonObject.put("name" , "info desk");
                jsonObject.put("number" , e_infodesk);
                jsonArray.put(jsonObject);

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        if(c_network.isChecked())
        {
            try {
                JSONObject jsonObject = new JSONObject();

                jsonObject.put("name" , "network");
                jsonObject.put("number" , e_network);
                jsonArray.put(jsonObject);

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        if(c_accountant.isChecked())
        {
            try {
                JSONObject jsonObject = new JSONObject();

                jsonObject.put("name" , "accountant");
                jsonObject.put("number" , e_account);
                jsonArray.put(jsonObject);

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }


        if(c_electric.isChecked())
        {
            try {
                JSONObject jsonObject = new JSONObject();

                jsonObject.put("name" , "Electric");
                jsonObject.put("number" , e_electri);
                jsonArray.put(jsonObject);

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }



        if(c_waste.isChecked())
        {
            try {
                JSONObject jsonObject = new JSONObject();

                jsonObject.put("name" , "waste");
                jsonObject.put("number" , e_waste);
                jsonArray.put(jsonObject);

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        if(c_helper.isChecked())
        {
            try {
                JSONObject jsonObject = new JSONObject();

                jsonObject.put("name" , "helper");
                jsonObject.put("number" , e_helper);
                jsonArray.put(jsonObject);

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }


        EventData data = new EventData(e_name, e_date, e_time, e_description, e_activity_one, e_activity_two, e_activity_three, e_activity_four, e_activity_five , place_s , jsonArray.toString());

        OnCompleteListener listener = new OnCompleteListener()
        {
            @Override
            public void onComplete(@NonNull Task task) {

                if(task.isSuccessful())
                {
                    Toast.makeText(SingleCellEdit.this , "event updated " , Toast.LENGTH_SHORT).show();
                    finish();
                }
            }
        };

        FirebaseDatabase database = FirebaseDatabase.getInstance();

        FirebaseAuth auth = FirebaseAuth.getInstance();

        String email = auth.getCurrentUser().getEmail();

        database.getReference().child("event").child(email.replace(".","")).child(getIntent().getStringExtra("key")).setValue(data).addOnCompleteListener(listener);





    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == 100 && resultCode == 200)
        {
            place_s =   data.getStringExtra("place");

            place_txt.setText(place_s);

        }
    }


}
