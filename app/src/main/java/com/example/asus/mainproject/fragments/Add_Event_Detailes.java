package com.example.asus.mainproject.fragments;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.InflateException;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.asus.mainproject.MainActivity;
import com.example.asus.mainproject.PlacePickActivity;
import com.example.asus.mainproject.R;
import com.example.asus.mainproject.SignUP;
import com.example.asus.mainproject.dataModels.EventData;
import com.example.asus.mainproject.dataModels.ProfileData;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlacePicker;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Calendar;

import static android.app.Activity.RESULT_OK;

public class Add_Event_Detailes extends Fragment {
    Button button;
    private TextView tvDisplayTime, select_location;
    private TimePicker timePicker1;
    private Button btnChangeTime;
    private CheckBox c_doctor,c_sweeper,c_transportation,c_video,c_clockroom,c_infodesk,c_accountant,c_electric,c_network,c_waste,c_helper;

    private ImageView clock,doctor,sweeper,transprotation,video,clockroom,infodesk,accountant,electri,network,waste,helper;

    private EditText time_et , date_et ;
    private Button add_et;

    private int hour;
    private int minute;
    private Dialog dialogshow;

    static final int TIME_DIALOG_ID = 999;

    private static View view;
    int year_x, day_x, month_x;

   public  static   String place_s ;

   public static TextView place_txt ;

    public Add_Event_Detailes() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        if (view != null) {
            ViewGroup parent = (ViewGroup) view.getParent();
            if (parent != null)
                parent.removeView(view);
        }
        try {
            view = inflater.inflate(R.layout.fragment_add__event__detailes, container, false);
        } catch (InflateException e) {
        /* map is already there, just return view as it is */
        }

        place_txt = view.findViewById(R.id.place_txt);

        time_et = view.findViewById(R.id.select_time);
        date_et=view.findViewById(R.id.dateofbrith);

        place_txt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivityForResult( new Intent(getContext() , PlacePickActivity.class) , 100);
            }
        });


        ImageView cal = view.findViewById(R.id.calandar);


        cal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {

                Calendar c = Calendar.getInstance();



                DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                 date_et.setText(String.valueOf(i2) + " /" + String.valueOf(i1 + 1)+"/"+String.valueOf(i));


                    }
                }, c.get(Calendar.YEAR) , c.get(Calendar.MONTH) , c.get(Calendar.DAY_OF_WEEK) );

                datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis());

                datePickerDialog.show();
            }
        });

        final Calendar call = Calendar.getInstance();
        year_x = call.get(Calendar.YEAR);
        month_x = call.get(Calendar.MONTH);
        day_x = call.get(Calendar.DAY_OF_MONTH);


        clock = view.findViewById(R.id.clock);

        select_location = view.findViewById(R.id.select_location);

        clock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Calendar c = Calendar.getInstance();

                int hour = c.get(Calendar.HOUR);
                int minute = c.get(Calendar.MINUTE);


                TimePickerDialog time_dialog = new TimePickerDialog(getContext(), new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int i, int i1) {

                        time_et.setText(String.valueOf(i)+":"+String.valueOf(i1));


                    }
                }, hour, minute, true);

                time_dialog.show();
            }
        });

        add_et=view.findViewById(R.id.add_event);
        add_et.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                JSONArray jsonArray = new JSONArray();



                c_doctor=view.findViewById(R.id.c_doctor);
                c_sweeper=view.findViewById(R.id.c_sweeper);
                c_accountant=view.findViewById(R.id.c_account);
                c_clockroom=view.findViewById(R.id.c_clock_room);
                c_helper=view.findViewById(R.id.c_helper);
                c_waste=view.findViewById(R.id.c_waste);
                c_electric=view.findViewById(R.id.c_electri);
                c_infodesk=view.findViewById(R.id.c_info);
                c_network=view.findViewById(R.id.c_network);
                c_video=view.findViewById(R.id.c_video);
                c_transportation=view.findViewById(R.id.c_transportation);





                final EditText event_name=view.findViewById(R.id.event_name);
                final EditText event_date=view.findViewById(R.id.dateofbrith);
                final EditText event_time=view.findViewById(R.id.select_time);
                final EditText event_description=view.findViewById(R.id.event_description);
                final EditText event_activity_one=view.findViewById(R.id.activity_one);
                final EditText event_activity_two=view.findViewById(R.id.activity_two);
                final EditText event_activity_three=view.findViewById(R.id.activity_three);
                final EditText event_activity_four=view.findViewById(R.id.activity_four);
                final EditText event_activity_five=view.findViewById(R.id.activity_five);



                final EditText doctor=view.findViewById(R.id.doctor_txt);
                final EditText sweeper=view.findViewById(R.id.sweeper_txt);
                final EditText account=view.findViewById(R.id.accountant_txt);
                final EditText electric=view.findViewById(R.id.electrician_txt);
                final EditText network=view.findViewById(R.id.network_txt);
                final EditText helper=view.findViewById(R.id.helper_txt);
                final EditText waste=view.findViewById(R.id.waste_picker_txt);
                final EditText transport=view.findViewById(R.id.transportation_txt);
                final EditText infodesk=view.findViewById(R.id.info_txt);
                final EditText clock=view.findViewById(R.id.clock_txt);
                final EditText video=view.findViewById(R.id.videographer_txt);



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



              OnCompleteListener listener = new OnCompleteListener() {
                  @Override
                  public void onComplete(@NonNull Task task) {

                      if(task.isSuccessful())
                      {
                          event_name.setText("");
                          event_date.setText("");
                          event_time.setText("");
                          event_description.setText("");
                          event_activity_one.setText("");
                          event_activity_two.setText("");
                          event_activity_three.setText("");
                          event_activity_four.setText("");
                          event_activity_five.setText("");

                      }
                  }
              };


               FirebaseAuth auth =  FirebaseAuth.getInstance();

               String email = auth.getCurrentUser().getEmail().replace("." , "");

                EventData data = new EventData(e_name, e_date, e_time, e_description, e_activity_one, e_activity_two, e_activity_three, e_activity_four, e_activity_five , place_s , jsonArray.toString());


                FirebaseDatabase database = FirebaseDatabase.getInstance();

                long current_time_stamp = System.currentTimeMillis();

                database.getReference().child("event").child(email).child(String.valueOf(current_time_stamp)).setValue(data).addOnCompleteListener(listener);




            }
        });
        doctor= view.findViewById(R.id.doctor);
        doctor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                myshow("we provide u with the doctors for your help in case any mishappening occurs during the event. ");

            }
        });

       sweeper= view.findViewById(R.id.sweeper);
        sweeper.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                myshow("sweepers are provided in the event for the cleanliness and refreshing environment of the event ");

            }
        });
       transprotation= view.findViewById(R.id.transportation);
        transprotation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                myshow("there will be all time availability of the drivers in case anyone needs to move their bagages and any other important items from one place to another ");

            }
        });
        video= view.findViewById(R.id.videographer);
        video.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                myshow("in case any person needs picture or video during the event , will be provided with this facility during the event ");

            }
        });
       clockroom= view.findViewById(R.id.clock_room_manager);
        clockroom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                myshow("this manager will take care of the inventory kept inside the clock room  ");

            }
        });
      infodesk= view.findViewById(R.id.infodesk);
        infodesk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                myshow("it contains all the information regarding the event  ");

            }
        });
        accountant= view.findViewById(R.id.accountant);
        accountant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                myshow("this person induces the entries regarding the volunteers registering into the event  ");

            }
        });
        network= view.findViewById(R.id.network);
      network.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                myshow("network provider will provide volunteers with good connectivity of internet and in case any problem arises due to network issue this person wil efficiently solve this problem  ");

            }
        });
      electri= view.findViewById(R.id.electrician);
        electri.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                myshow("electricians will provide you with the solutions if any problem arises such as lightning issue in the bulbs etc   ");

            }
        });
        waste= view.findViewById(R.id.waste_picker);
        waste.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                myshow("this person will provide proper sanitation facilities inside and around the event and if there is any sanitation problem to any of the visitors in the event, this very person will solve this problem and provide healthy environment in the event  ");

            }
        });
       helper= view.findViewById(R.id.helper);
        helper.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                myshow("this manager will take care of the inventory kept inside the clock room  ");

            }
        });



        return view;
    }




    public static void update_place()
    {
        place_txt.setText(place_s);
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == 100)
        {
          place_s =   data.getStringExtra("place");

          update_place();
        }
    }

    public void myshow(String message){
        dialogshow=new Dialog(getContext() , R.style.Theme_Dialog);
        dialogshow.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialogshow.setContentView(R.layout.info_dialog);

        TextView info = dialogshow.findViewById(R.id.info);

        info.setText(message);

        Button got_it = dialogshow.findViewById(R.id.got_it);

        got_it.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogshow.dismiss();
            }
        });

        dialogshow.setTitle(message);
        dialogshow.show();

    }
}
