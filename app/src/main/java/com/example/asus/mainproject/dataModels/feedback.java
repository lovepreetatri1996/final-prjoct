package com.example.asus.mainproject.dataModels;

/**
 * Created by Asus on 5/2/2018.
 */

public class feedback {
    public String email,description , date ;

    public String key;

    public   feedback()
    {

    }
    public feedback(String email, String description , String date)
    {
        this.email = email;

        this.description=description;

        this.date = date ;

    }


}
