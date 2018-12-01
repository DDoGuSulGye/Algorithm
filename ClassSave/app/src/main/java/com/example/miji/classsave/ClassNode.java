package com.example.miji.classsave;

import java.io.Serializable;
import java.util.ArrayList;

public class ClassNode implements Serializable {

    String ClassName;
    ArrayList<Integer> monday;
    ArrayList<Integer> tuesday;
    ArrayList<Integer> wednesday ;
    ArrayList<Integer> thursday ;
    ArrayList<Integer> friday ;
    String nearbyElevator;
    String stair;

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

    public String getStair() {
        return stair;
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

    public void setStair(String stair){
        this.stair = stair;
    }

}
