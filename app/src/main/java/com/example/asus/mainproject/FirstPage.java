package com.example.asus.mainproject;

import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class FirstPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_page);

    }

    public void signup(View view) {
        Intent i=new Intent(FirstPage.this,SignUP.class);
        startActivity(i);
    }




    public void jaja(View view) {
        Intent i=new Intent(FirstPage.this,Homes.class);
        startActivity(i);

    }

    public void login(View view) {






        EditText email_et = findViewById(R.id.email_et);
        String email = email_et.getText().toString();
        if(email_et.length() == 0 )
        {
            email_et.setError("Please enter email");
            return;
        }




        EditText password_et = findViewById(R.id.password_et);
        String password = password_et.getText().toString();

        if(password_et.length() == 0 )
        {
            password_et.setError("Please enter your password");
            return;
        }


        FirebaseAuth auth = FirebaseAuth.getInstance();

        auth.signInWithEmailAndPassword(email , password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if(task.isSuccessful())
                {
                    Intent i = new Intent(FirstPage.this , Homes.class);

                    startActivity(i);
                    finish();
                }

                else {

                    Toast.makeText(FirstPage.this , "invalid login" , Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void frogot_password(View view) {
        Intent i = new Intent(FirstPage.this , Forgot_password.class);

        startActivity(i);
    }
}
