package com.example.asus.mainproject.dataModels;

/**
 * Created by Asus on 5/3/2018.
 */

public class JoinEvent  {

    public String event_email , event_key , role ;

    public JoinEvent()
    {

    }

    public JoinEvent(String event_email , String event_key , String role)
    {

        this.event_email  = event_email ;
        this.event_key = event_key;
        this.role = role ;
    }
}
