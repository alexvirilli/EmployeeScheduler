package com.example.employeeschedulergui;


public class StoreHours {

    private int sunday[] = new int[48];
    private int monday[] = new int[48];
    private int tuesday[] = new int[48];
    private int wednesday[] = new int[48];
    private int thursday[] = new int[48];
    private int friday[] = new int[48];
    private int saturday[] = new int[48];
    private int[][] week = {sunday, monday, tuesday, wednesday, thursday, friday, saturday};

    public static final int INELIGIBLE = 1;
    public static final int ELIGIBLE = 0;
    public static final int NOT_FOUND = -1;

    public StoreHours(){

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

    public boolean setClosed(String day){
        int index = getIndex(day); if(index == -1) return false;
        for(int i = 0; i < 48; i++){
            week[index][i] = INELIGIBLE;
        }
        return true;
    }

    //time is of form "X:XX-Y:YY" or "X:XX" if not available for the rest of the day
    //given in 24 hr time
    public boolean setClosed(String day, String time){
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

    public boolean setOpen(String day){
        int dayIndex = getIndex(day); if(dayIndex == -1) return false;
        for(int i = 0; i < 48; i++){
            week[dayIndex][i] = ELIGIBLE;
        }
        return true;
    }

    //time is of form "X:XX-Y:YY" or "X:XX" if  available for the rest of the day
    //given in 24 hr time
    public boolean setOpen(String day, String time){
        int dayIndex = getIndex(day); if(dayIndex == -1) return false;

        setClosed(day);

        if(time.contains("-")){ //range of times
            String timeSplit[] = time.split("-");
            int startIndex = parseTime(timeSplit[0]);
            int endIndex = parseTime(timeSplit[1]);
            for(int i = startIndex; i <= endIndex; i++){
                week[dayIndex][i] = ELIGIBLE;
            }
            return true;
        } else { //block out rest of day from given time
            int startIndex = parseTime(time);
            for(int i = startIndex; i < 48; i++){
                week[dayIndex][i] = ELIGIBLE;
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

    public boolean isClosed(String day){
        if(getIndex(day)== NOT_FOUND) return false;
        int dayIndex = getIndex(day);
        for(int i = 0; i < 48; i++){
            if(week[dayIndex][i]==ELIGIBLE) return false;
        }
        return true;
    }

    public String getHours(String day){
        int dayIndex = getIndex(day);
        if(dayIndex == NOT_FOUND) return "invalid day";
        if(isClosed(day)) return "closed";
        String hours = "";
        int openCounter = 0;
        for(int i = 0; i < 47; i++){
            //initial opening
            if(week[dayIndex][i] == ELIGIBLE && openCounter == 0){
                hours = hours + indexToTimeRepresentation(i);
                openCounter++;
            }
            //find closing
            if(week[dayIndex][i] == ELIGIBLE && week[dayIndex][i+1] == INELIGIBLE){
                hours = hours + " - " + indexToTimeRepresentation(i);
            }

        }
        return hours;
    }

    private String getDay(int i){
        if(i == 0) return "sunday";
        if(i == 1) return "monday";
        if(i == 2) return "tuesday";
        if(i == 3) return "wednesday";
        if(i == 4) return "thursday";
        if(i == 5) return "friday";
        if(i == 6) return "saturday";
        return null;
    }

    public void printStoreHours(){
        System.out.println("\tSUNDAY: " + getHours("sunday"));
        System.out.println("\tMONDAY: " + getHours("monday"));
        System.out.println("\tTUESDAY: " + getHours("tuesday"));
        System.out.println("\tWEDNESDAY: " + getHours("wednesday"));
        System.out.println("\tTHURSDAY: " + getHours("thursday"));
        System.out.println("\tFRIDAY: " + getHours("friday"));
        System.out.println("\tSATURDAY: " + getHours("saturday"));
    }

    public void printHours(){
        for(int j = 0; j < 7; j++) {
            String day = getDay(j);
            int dayIndex = getIndex(day);
            System.out.printf(day.toUpperCase() + ":");
            if (isClosed(day)) System.out.println(" closed");
            else {
                String hours = "";
                int openCounter = 0;
                for (int i = 0; i < 47; i++) {
                    //initial opening
                    if (week[dayIndex][i] == ELIGIBLE && openCounter == 0) {
                        hours = hours + indexToTimeRepresentation(i);
                        openCounter++;
                    }
                    //find closing
                    if (week[dayIndex][i] == ELIGIBLE && week[dayIndex][i + 1] == INELIGIBLE) {
                        hours = hours + " - " + indexToTimeRepresentation(i);
                    }

                }
                System.out.println(" " + hours);
            }

        }
    }


}
