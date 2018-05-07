package com.example.asus.mainproject.dataModels;

/**
 * Created by Asus on 4/26/2018.
 */

public class EventData {

    public String name,date,time,description,activity_one, activity_two,activity_three,activity_four,activity_five , location;

    public String key , email_key;

    public String members_required ;

    public   EventData()
    {

    }
    public EventData(String name, String date ,String time,String description, String activity_one, String activity_two, String activity_three, String activity_four, String activity_five ,  String location , String members_required)

    {
        this.name = name;
        this.date=date;
        this .time=time;
        this.description=description;
        this.activity_one=activity_one;
        this.activity_two=activity_two;
        this.activity_three=activity_three;
        this.activity_four=activity_four;
        this.activity_five=activity_five;

        this.members_required = members_required ;

        this.location = location ;

    }

    public EventData(String name, String date ,String time,String description, String activity_one, String activity_two, String activity_three, String activity_four, String activity_five ,  String location , String members_required , String key)

    {
        this.name = name;
        this.date=date;
        this .time=time;
        this.description=description;
        this.activity_one=activity_one;
        this.activity_two=activity_two;
        this.activity_three=activity_three;
        this.activity_four=activity_four;
        this.activity_five=activity_five;
        this.members_required = members_required ;

        this.location = location ;

        this.key = key;

    }

    public EventData(String name, String date ,String time,String description, String activity_one, String activity_two, String activity_three, String activity_four, String activity_five ,  String location , String members_required , String key , String email_key)

    {
        this.name = name;
        this.date=date;
        this .time=time;
        this.description=description;
        this.activity_one=activity_one;
        this.activity_two=activity_two;
        this.activity_three=activity_three;
        this.activity_four=activity_four;
        this.activity_five=activity_five;
        this.members_required = members_required;

        this.location = location ;

        this.key = key;
        this.email_key = email_key ;

    }

}
