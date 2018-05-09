package com.example.asus.mainproject;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MyEventSingleActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_event_single);

        final TextView event_name = findViewById(R.id.event_name);
        final TextView event_date = findViewById(R.id.dateofbrith);
        final TextView event_time = findViewById(R.id.select_time);
        TextView event_description = findViewById(R.id.event_description);
        TextView event_activity_one = findViewById(R.id.activity_one);
        TextView event_activity_two = findViewById(R.id.activity_two);
        TextView event_activity_three = findViewById(R.id.activity_three);
        TextView event_activity_four = findViewById(R.id.activity_four);
        TextView event_activity_five = findViewById(R.id.activity_five);

        TextView place_txt = findViewById(R.id.place_txt);

        String name = getIntent().getStringExtra("name");
        String date = getIntent().getStringExtra("date");
        String location = getIntent().getStringExtra("location");
        String time = getIntent().getStringExtra("time");
        String eventdescription = getIntent().getStringExtra("event_description");
        String activityone = getIntent().getStringExtra("activityone");
        String activitytwo = getIntent().getStringExtra("activitytwo");
        String activitythree = getIntent().getStringExtra("activitythree");
        String activityfour = getIntent().getStringExtra("activityfour");
        String activityfive = getIntent().getStringExtra("activityfive");

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

        TextView joining_as = findViewById(R.id.joining_as);

        String role = getIntent().getStringExtra("member_required");

        System.out.println("role is ****************************** " + role);

        try {
            JSONArray jsonArray_roles = new JSONArray(role);

            String all_roles = "";

            for (int i = 0; i < jsonArray_roles.length(); i++) {
                JSONObject jsonObject = jsonArray_roles.getJSONObject(i);

                String rr = jsonObject.getString("name");

                String member_required = jsonObject.getString("number");

                all_roles = all_roles + rr + "  " + member_required + "  ,  ";

            }

            joining_as.setText(all_roles);


        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
