package com.example.shubu.peb;

/**
 * Created by dell on 7/31/2017.
 */

public class Visitor {

    public Visitor(){}
    public String name,carNo,address,phoneNo,inTime,OutTime,duration;
    long inMilis;

    public Visitor(String name,
                    String carNo,
                    String address,
                    String phoneNo,
                    String inTime
    ) {
        this.name = name;
        this.carNo = carNo;
        this.address = address;
        this.phoneNo = phoneNo;
        this.inTime = inTime;

    }

}
