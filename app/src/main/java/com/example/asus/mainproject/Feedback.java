package com.example.asus.mainproject;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.asus.mainproject.dataModels.ProfileData;
import com.example.asus.mainproject.dataModels.feedback;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Feedback extends AppCompatActivity {
    EditText edt2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);

    }

    public void feedback_submit(View view) {




        edt2 = findViewById(R.id.write_her_feedback);
        final String description = edt2.getText().toString();
        if (description.length() == 0) {
            edt2.setError("please enter your feedback....");


            return;
        }
        FirebaseAuth auth = FirebaseAuth.getInstance();

        String email = auth.getCurrentUser().getEmail().replace(".","");

        Date c = Calendar.getInstance().getTime();
        System.out.println("Current time => " + c);

        SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy");

        String formattedDate = df.format(c);

                    feedback data = new feedback(email , description , formattedDate);


                    FirebaseDatabase database = FirebaseDatabase.getInstance();


                    database.getReference().child("feedback").child(email).setValue(data).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {


                            if(task.isSuccessful())
                            {
                                Toast.makeText(Feedback.this , "Feedback submitted successfully" , Toast.LENGTH_SHORT).show();
                                finish();

                            }
                            else
                            {
                                System.out.println("error is "+task.getException());

                            }
                        }
                    });





      }
    }
