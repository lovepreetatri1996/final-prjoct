package com.example.asus.mainproject;

import android.app.Dialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatRatingBar;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.asus.mainproject.dataModels.JoinEvent;
import com.example.asus.mainproject.dataModels.RatingData;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Joining_Events_Detailes extends AppCompatActivity {




    private TextView joining_as ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_joining_events__detailes);

        joining_as = findViewById(R.id.joining_as);

        TextView event_name=findViewById(R.id.event_name);
        TextView event_date=findViewById(R.id.dateofbrith);
        TextView event_time=findViewById(R.id.select_time);
        TextView event_description=findViewById(R.id.event_description);
        TextView event_activity_one=findViewById(R.id.activity_one);
        TextView event_activity_two=findViewById(R.id.activity_two);
        TextView event_activity_three=findViewById(R.id.activity_three);
        TextView event_activity_four=findViewById(R.id.activity_four);
        TextView  place_txt = findViewById(R.id.place_txt);

        TextView event_activity_five=findViewById(R.id.activity_five);






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

        String role = getIntent().getStringExtra("role").toUpperCase();


        joining_as.setText(role);

        place_txt.setText(location);

        event_name.setText(name);
        event_date.setText(date);
        event_time.setText(time);
        event_description.setText(eventdescription);
        event_activity_one.setText(activityone);
        event_activity_two.setText(activitytwo);
        event_activity_three.setText(activitythree);
        event_activity_four.setText(activityfour);
        event_activity_five.setText(activityfive);






    }

    public void back_back(View view) 
    {
        finish();
    }



    public void unjoin_event(View view) {

        FirebaseAuth auth = FirebaseAuth.getInstance();

        String email = auth.getCurrentUser().getEmail().replace(".", "");

        FirebaseDatabase database = FirebaseDatabase.getInstance();


        database.getReference().child("joined_event").child(email).child(getIntent().getStringExtra("event_key")).setValue(null);


        finish();
    }

    public void rating_dialog(View view) {


          final Dialog   dialogshow=new Dialog(Joining_Events_Detailes.this, R.style.Theme_Dialog);
            dialogshow.requestWindowFeature(Window.FEATURE_NO_TITLE);
            dialogshow.setContentView(R.layout.rating_dialog);

            final AppCompatRatingBar bar = dialogshow.findViewById(R.id.rating_bar);

            bar.setNumStars(5);



            Button cancel_it = dialogshow.findViewById(R.id.cancel_it);

            Button apply = dialogshow.findViewById(R.id.apply);

            cancel_it.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialogshow.dismiss();
                }
            });

            apply.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    RatingData data = new RatingData(bar.getRating());

                    FirebaseAuth auth = FirebaseAuth.getInstance();

                    String email = auth.getCurrentUser().getEmail().replace(".", "");

                    FirebaseDatabase database = FirebaseDatabase.getInstance();


                    database.getReference().child("event_rating").child(email).child(getIntent().getStringExtra("event_key")).setValue(data);


                    dialogshow.dismiss();


                }
            });


            dialogshow.show();



    }


}
