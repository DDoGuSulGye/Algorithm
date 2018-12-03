package com.example.miji.classsave;

/**
 * Created by leesd on 2018-12-02.
 */

public class StairNode {
    String area = null;
    String day = null;
    int lowest = 0;
    int highest = 0;
    int timeByStair = 0; // 구역 별 한 층의 계단에 대해 걸리는 시간

    public StairNode(String area, String day, int lowest, int highest) {
        this.area = area;
        this.day = day;
        this.lowest = lowest;
        this.highest = highest;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public int getLowest() {
        return lowest;
    }

    public void setLowest(int lowest) {
        this.lowest = lowest;
    }

    public int getHighest() {
        return highest;
    }

    public void setHighest(int highest) {
        this.highest = highest;
    }

    public int getTimeByStair() {
        return timeByStair;
    }

    public void setTimeByStair(int timeByStair) {
        this.timeByStair = timeByStair;
    }

}
