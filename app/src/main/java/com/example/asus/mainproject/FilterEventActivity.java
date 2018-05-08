package com.example.asus.mainproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.asus.mainproject.fragments.Events;

public class FilterEventActivity extends AppCompatActivity {


    TextView location ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter_event);

        location = findViewById(R.id.location);
    }

    public void select_location(View view)
    {

        startActivityForResult( new Intent(FilterEventActivity.this , PlacePickActivity.class) , 100);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == 100 && resultCode == 200)
        {

            location.setText(data.getStringExtra("place"));

        }
    }

    public void filter(View view) {

        Events.get_data(location.getText().toString());

        finish();
    }
}
