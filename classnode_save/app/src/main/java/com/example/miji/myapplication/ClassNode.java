package com.example.miji.myapplication;


import android.content.Intent;

import java.io.Serializable;
import java.util.ArrayList;

public class ClassNode implements Serializable {

    String ClassName;
    ArrayList<Integer> monday;
    ArrayList<Integer> tuesday;
    ArrayList<Integer> wednesday ;
    ArrayList<Integer> thursday ;
    ArrayList<Integer> friday ;

//    ArrayList<Integer> monday = new ArrayList<Integer>();
//    ArrayList<Integer> tuesday = new ArrayList<Integer>();
//    ArrayList<Integer> wednesday = new ArrayList<Integer>();
//    ArrayList<Integer> thursday = new ArrayList<Integer>();
//    ArrayList<Integer> friday = new ArrayList<Integer>();

    String nearbyElevator;

    public void ClassNode(){
        ClassName = "";
 //       monday = new ArrayList<Integer>();
//        tuesday = new ArrayList<Integer>();
//        tuesday.clear();
//        wednesday.clear();
//        thursday.clear();
//        friday.clear();

       nearbyElevator = "";
    }

    public String getClassName(){
        return ClassName;
    }

    public ArrayList<Integer> getMonday(){
        return monday;
    }

    public ArrayList<Integer> getTuesday(){
        return tuesday;
    }

    public ArrayList<Integer> getWednesday(){
        return wednesday;
    }

    public ArrayList<Integer> getThursday(){
        return thursday;
    }

    public ArrayList<Integer> getFriday(){
        return friday;
    }

    public String getNearbyElevator(){
        return nearbyElevator;
    }


    public void setClassName(String ClassName){
        this.ClassName = ClassName;
    }

    public void setMonday(ArrayList<Integer> monday){
        this.monday = monday;
    }

    public void setTuesday(ArrayList<Integer> tuesday){
        this.tuesday = tuesday;
    }

    public void setWednesday(ArrayList<Integer> wednesday){
        this.wednesday = wednesday;
    }

    public void setThursday(ArrayList<Integer> thursday){
        this.thursday = thursday;
    }

    public void setFriday(ArrayList<Integer> friday){
        this.friday = friday;
    }

    public void setNearbyElevator(String nearbyElevator){
        this.nearbyElevator = nearbyElevator;
    }



}
