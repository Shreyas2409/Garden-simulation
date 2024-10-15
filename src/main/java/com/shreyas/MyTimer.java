package com.shreyas;


public class MyTimer {
    static int day = 0;
    static int currentDay = 1;
    static long hour = 0;
    static long sec = 0;
    static long start_time = System.currentTimeMillis();

    public MyTimer() {
        start_time = System.currentTimeMillis();
        day = 0;
        hour = 0;
        currentDay = 1;
    }
    public static long getSec() {
        long current_time = System.currentTimeMillis();
        long time = ((current_time - getStart_time()) / 1000) % 60;
        return time;
    }

    public static int getDay() {
        return day;
    }

    public static double getTotalHour() {
        return getHour() + 24 * (getDay() - 1);
    }

    public static int getHour() {
        long current_time = System.currentTimeMillis();
        int time = ((int) (current_time - getStart_time()) / 1000 / 60) % 24;
        if (time == 0 && currentDay != day) {
            day = currentDay;
            System.out.println("day " + day);
        }
        if (time == 23 && currentDay == day) {
            currentDay++;
        }
        return time;
    }

    public static long getStart_time() {
        return start_time;
    }
}
