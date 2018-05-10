package com.example.asus.mainproject;

import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.google.firebase.auth.FirebaseAuth;

public class Slider_viewer_bablu extends AppCompatActivity {
        private ViewPager viewPager;
        private SlideAdapter myadapter;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_slider_viewer_bablu);
            viewPager = (ViewPager) findViewById(R.id.viewpager);
            myadapter = new SlideAdapter(this);
            viewPager.setAdapter(myadapter);

        }

        public void skip(View view) {
            Intent i=new Intent(Slider_viewer_bablu.this,Homes.class);
            startActivity(i);
        }

}
