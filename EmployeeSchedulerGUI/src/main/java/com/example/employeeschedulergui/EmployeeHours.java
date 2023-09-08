package com.example.employeeschedulergui;


public class EmployeeHours {

    private int[] sunday;
    private int[] monday;
    private int[] tuesday;
    private int[] wednesday;
    private int[] thursday;
    private int[] friday;
    private int[] saturday;
    private final int[][] week;

    public static final int INELIGIBLE = 1;
    public static final int ELIGIBLE = 0;
    public static final int NOT_FOUND = -1;

    public EmployeeHours(){
        this.sunday = new int[48];
        this.monday = new int[48];
        this.tuesday = new int[48];
        this.wednesday = new int[48];
        this.thursday = new int[48];
        this.friday = new int[48];
        this.saturday = new int[48];
        this.week = new int[][]{sunday, monday, tuesday, wednesday, thursday, friday, saturday};
    }

    public int[] getDay(String day){
        int dayIndex = getIndex(day);
        if(dayIndex == NOT_FOUND) return null;
        return week[dayIndex];
    }

    public static final int sundayIndex = 0;
    public static final int mondayIndex = 1;
    public static final int tuesdayIndex = 2;
    public static final int wednesdayIndex = 3;
    public static final int thursdayIndex = 4;
    public static final int fridayIndex = 5;
    public static final int saturdayIndex = 6;
    private int getIndex(String day){
        if(day.equalsIgnoreCase("Sunday")) return sundayIndex;
        if(day.equalsIgnoreCase("Monday")) return mondayIndex;
        if(day.equalsIgnoreCase("Tuesday")) return tuesdayIndex;
        if(day.equalsIgnoreCase("Wednesday")) return wednesdayIndex;
        if(day.equalsIgnoreCase("Thursday")) return thursdayIndex;
        if(day.equalsIgnoreCase("Friday")) return fridayIndex;
        if(day.equalsIgnoreCase("Saturday")) return saturdayIndex;
        return NOT_FOUND;
    }

    public boolean setIneligible(String day){
        int index = getIndex(day); if(index == -1) return false;
        for(int i = 0; i < 48; i++){
            week[index][i] = INELIGIBLE;
        }
        return true;
    }

    //time is of form "X:XX-Y:YY" or "X:XX" if not available for the rest of the day
    //given in 24 hr time
    public boolean setIneligible(String day, String time){
        int dayIndex = getIndex(day); if(dayIndex == -1) return false;
        if(time.contains("-")){ //range of times
            String timeSplit[] = time.split("-");
            int startIndex = parseTime(timeSplit[0]);
            int endIndex = parseTime(timeSplit[1]);
            for(int i = startIndex; i <= endIndex; i++){
                week[dayIndex][i] = INELIGIBLE;
            }
            return true;
        } else { //block out rest of day from given time
            int startIndex = parseTime(time);
            for(int i = startIndex; i < 48; i++){
                week[dayIndex][i] = INELIGIBLE;
            }
            return true;
        }
    }

    //of pattern "X:XX", minutes must be increments of 30, 24-hour clock
    private int parseTime(String time){
        String timeSplit[] = time.split(":");
        int hour = Integer.parseInt(timeSplit[0]);
        int minutes = Integer.parseInt(timeSplit[1]);
        if(minutes % 30 != 0) return NOT_FOUND;
        int index = hour * 2;
        if(minutes!=0) index ++;
        return index;
    }

    //print out what the indexes represent in the arrays
    public String indexToTimeRepresentation(int i){
        i = i % 48;
        int hours = i / 2;
        if(hours == 0){
            hours = 12;
        }
        int minutes = (i % 2) * 30;
        if(i < 24){
            if(minutes == 0) return hours + ":00" + " a.m.";
            return hours + ":" + minutes + " a.m.";
        } else {
            hours -= 12;
            if(hours == 0) hours = 12;
            if(minutes == 0) return hours + ":00" + " p.m.";
            return hours + ":" + minutes + " p.m.";
        }
    }

    public boolean printEligibilityHours(String day){
        int dayIndex = getIndex(day); if(dayIndex == NOT_FOUND) return false;
        for(int i = 0; i < 48; i++){
            System.out.printf(indexToTimeRepresentation(i) + " ");
            if(week[dayIndex][i]==ELIGIBLE) System.out.println("eligible to work");
            else System.out.println("not eligible to work");
        }
        return true;
    }

    public boolean isIneligble(String day){
        int dayIndex = getIndex(day);
        for(int i = 0; i < 48; i++){
            if(week[dayIndex][i] == ELIGIBLE) return false;
        }
        return true;
    }

    public boolean isEligible(String day){
        int dayIndex = getIndex(day);
        for(int i = 0; i < 48; i++){
            if(week[dayIndex][i] == INELIGIBLE) return false;
        }
        return true;
    }

    public String getAvailability(String day){
        int dayIndex = getIndex(day);
        if(dayIndex == NOT_FOUND) return "\t" + day.toUpperCase() + ": invalid day";
        if(isIneligble(day)) return "\t" +day.toUpperCase() + ": unavailable all day";
        if(isEligible(day)) return "\t" + day.toUpperCase() + ": available all day";

        String hours = "";
        int availabilityCount = 0;
        int dashCount = 0;
        int commaCount = 0;

        for(int i = 0; i < 47; i++){
            //start of availability
            if(week[dayIndex][i] == ELIGIBLE && availabilityCount == 0){
                hours = hours + indexToTimeRepresentation(i);
                availabilityCount++;
            }
            if(week[dayIndex][i] == ELIGIBLE && week[dayIndex][i+1] == INELIGIBLE){
                hours = hours + " - " + indexToTimeRepresentation(i);
                dashCount++;
            }
            if(week[dayIndex][i] == INELIGIBLE && week[dayIndex][i+1]==ELIGIBLE) {
                hours = hours + ", " + indexToTimeRepresentation(i+1);
                commaCount++;
            }
        }
        if(dashCount == commaCount) {
            return "\t" + day.toUpperCase() + ": " + hours + " - end of day";
        } else {
            return "\t" + day.toUpperCase() + ": " + hours;
        }

    }

    public void printAvailability(){
        System.out.println(getAvailability("sunday"));
        System.out.println(getAvailability("monday"));
        System.out.println(getAvailability("tuesday"));
        System.out.println(getAvailability("wednesday"));
        System.out.println(getAvailability("thursday"));
        System.out.println(getAvailability("friday"));
        System.out.println(getAvailability("saturday"));
    }


}
