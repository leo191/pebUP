package com.example.shubu.peb;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.sql.Time;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class ResidentFrag extends Fragment {

    TextView name,address,phone_no,car_no,in_time,out_time,duration;
    public ResidentFrag() {
        // Required empty public constructor
    }

    public static ResidentFrag newInstance()
    {
        return new ResidentFrag();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.resident_fragment,container,false);
        name=(TextView)view.findViewById(R.id.name);
        address=(TextView)view.findViewById(R.id.address);
        phone_no=(TextView)view.findViewById(R.id.phone_no);
        car_no=(TextView)view.findViewById(R.id.car_no);
        in_time=(TextView)view.findViewById(R.id.intime);
        out_time=(TextView)view.findViewById(R.id.outtime);
        duration=(TextView)view.findViewById(R.id.duration);
        savedInstanceState = getArguments();
        User usr = (User) savedInstanceState.getSerializable("usr");
        setData(usr.name,
                usr.address,
                usr.phone_no,
                usr.car_no,
                savedInstanceState.getString("intime"),
                savedInstanceState.getString(""),
                savedInstanceState.getString(""));

        return view;
    }
    public void setData(String name, String address, String phone_no, String car_no, String in_time, String out_time, String duration)
    {
        this.name.setText(name);
        this.address.setText(address);
        this.phone_no.setText(phone_no);
        this.car_no.setText(car_no);
        this.in_time.setText(in_time);
        this.out_time.setText(out_time);
        this.duration.setText(duration);
    }

}
