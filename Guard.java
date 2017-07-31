package com.example.shubu.peb;

import android.icu.util.Calendar;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.sql.Time;
import java.util.GregorianCalendar;

public class Guard extends AppCompatActivity {
    Button search,reset;
    EditText car_no;
    String carno;
    Time inTime,outTime,duration;
    String current_time;
    String current_date;
    Calendar c;
    Boolean USER=false,VISITOR=false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.guard_entry);
        /*
        search=(Button)findViewById(R.id.search_vehicle);
        reset=(Button)findViewById(R.id.clear_vehicle);
        c=Calendar.getInstance();
        current_date=new SimpleDateFormat("dd-MM-yyyy").format(c.getTime());
        current_time=new java.text.SimpleDateFormat("HH:mm:ss").format(c.getTime());
        car_no=(EditText)findViewById(R.id.ETvehicle_no);
        carno=car_no.getText().toString();
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                search_vehicle(v);
            }
        });*/
    }

    public void search_vehicle(View view){

        DatabaseReference databaseReference=FirebaseDatabase.getInstance().getReference();
        databaseReference.child("user").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot ds:dataSnapshot.getChildren()){
                    User user=ds.getValue(User.class);
                    if (user.car_no.equals(carno)){
                        USER=true;
                         residentSetData(user);
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        if (!USER) {
            databaseReference.child("visitor").addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    for (DataSnapshot ds : dataSnapshot.getChildren()) {
                        Visitor visitor = ds.getValue(Visitor.class);
                        if (visitor.carNo.equals(carno)) {
                            VISITOR = true;
                            visitorSetData(visitor);
                        }
                    }

                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
        }
        if(!VISITOR&&!USER){
            Visitor visitor=new Visitor();
            visitorSetData(visitor);
        }
    }
    public void residentSetData(User usr){
        if (usr != null) {
            ResidentFrag someFragment = new ResidentFrag();
            GregorianCalendar gc = new GregorianCalendar();
            int am_pm = gc.get(java.util.Calendar.AM_PM);
            String intime = gc.get(java.util.Calendar.HOUR) + "H:" + gc.get(java.util.Calendar.MINUTE) + "M:" + gc.get(java.util.Calendar.SECOND) + "S" + (am_pm == 1 ? "PM" : "AM");
            Bundle data = new Bundle();
            data.putSerializable("usr", usr);
            data.putString("intime", intime);


//
            someFragment.setArguments(data);
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.fragment2, someFragment); // give your fragment container id in first parameter
            transaction.addToBackStack(null);  // if written, this transaction will be added to backstack
            transaction.commit();
        }


    }
    public void visitorSetData(Visitor visitor){

    }
}
