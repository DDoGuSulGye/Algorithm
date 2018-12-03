package com.example.miji.classsave;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ElevatorNode {
    String area = null;
    String day = null;
    int lowest = 0; // 해당 구역 엘리베이터가 운행하는 최하층 ex> B6 : -6
    int highest = 0; // 해당 구역 엘리베이터가 운행하는 최상층
    int people = 0; // 해당 시간, 해당 area에 대한 모든 층의 사람 수(혼잡도)
    int count = 0 ; // 몇 번 반복해야 되는지
    Map<Integer,Integer> floorPeople = new HashMap<>(); // 해당 층, 해당 area에 대한 혼잡도, index -> 0 : B6, 1 : B5 ~~ 14 : 9
    double spendTimeLongest;
    double spendTimeShortest;


    public double getSpendTimeLongest() { return spendTimeLongest; }

    public void setSpendTimeLongest(double spendTimeLongest) {  this.spendTimeLongest = spendTimeLongest;  }

    public double getSpendTimeShortest() { return spendTimeShortest; }

    public void setSpendTimeShortest(double spendTimeShortest) {  this.spendTimeShortest = spendTimeShortest;  }


    public ElevatorNode(String area, String day, int lowest, int highestint) {
        this.area = area;
        this.lowest = lowest;
        this.highest = highestint;
    }

    public int getCount() { return count; }
    public void setCount(int count) { this.count = count; }
    public String getDay() { return day; }

    public void setDay(String day) { this.day = day; }
    public int getLowest() { return lowest; }

    public void setLowest(int lowest) { this.lowest = lowest; }

    public int getHighest() { return highest; }

    public void setHighest(int highest) { this.highest = highest; }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public int getPeople() {
        return people;
    }

    public void setPeople(int people) {
        this.people = people;
    }

    public Map<Integer,Integer> getFloorPeople() {
        return floorPeople;
    }

    public void setFloorPeople(Map<Integer,Integer> floorPeople) {
        this.floorPeople = floorPeople;
    }
}
