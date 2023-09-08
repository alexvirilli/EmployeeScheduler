package com.example.employeeschedulergui;


import java.util.EventListener;

public class Store {

    String storeName;
    StoreHours storeHours;
    //Employment employment;

    public static final int INELIGIBLE = 1;
    public static final int ELIGIBLE = 0;

    //constructors
    public Store(){
        storeName = null;
        storeHours = null;
    }

    public Store(String storeName, StoreHours storeHours){
        this.storeName = storeName;
        this.storeHours = storeHours;
    }

    //getters
    public String getStoreName(){
        return this.storeName;
    }

    public StoreHours getStoreHours(){
        return this.storeHours;
    }

    public void setStoreHours(StoreHours storeHours){
        this.storeHours = storeHours;
    }

    public static final int sundayIndex = 0;
    public static final int mondayIndex = 1;
    public static final int tuesdayIndex = 2;
    public static final int wednesdayIndex = 3;
    public static final int thursdayIndex = 4;
    public static final int fridayIndex = 5;
    public static final int saturdayIndex = 6;
    public static final int NOT_FOUND = -1;

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

    public void printHours(){
        System.out.println(getStoreName() + ": hours");
        storeHours.printStoreHours();
    }

    private int[] getArrayCrossProduct(int[] arr1, int[] arr2){
        int[] combinedArray = new int[48];
        if(arr1.length != arr2.length) return null;
        for(int i = 0; i < arr1.length; i++){
            if(arr1[i] == INELIGIBLE || arr2[i] == INELIGIBLE){
                combinedArray[i] = INELIGIBLE;
            } else {
                combinedArray[i] = ELIGIBLE;
            }
        }
        return combinedArray;
    }

    public void printAvailableWorkers(String day, Employment employment){
        if(getIndex(day)==NOT_FOUND) return;
        int dayIndex = getIndex(day);
        System.out.println(day.toUpperCase());
        if(storeHours.isClosed(day)){
            System.out.println("\tstore closed");
        } else {
            int dayStoreHours[] = storeHours.getDay(day);
            Employee employeeArray[] = employment.getEmployment();
            int j = 0;
            while (j < employment.getSize() && employeeArray[j] != null) {
                int hourCount = 0;
                int[] dayEmployeeHours = employeeArray[j].getEmployeeHours().getDay(day);
                String hours = "";
                int[] combinedArray = getArrayCrossProduct(dayStoreHours, dayEmployeeHours);
                if (!employeeArray[j].getEmployeeHours().isIneligble(day)) {
                    for (int i = 0; i < 47; i++) {
                        if (combinedArray[i] == ELIGIBLE && hourCount == 0) {
                            hours = hours + storeHours.indexToTimeRepresentation(i);
                            hourCount++;
                        }
                        if (combinedArray[i] == ELIGIBLE && combinedArray[i + 1] == INELIGIBLE) {
                            hours = hours + " - " + storeHours.indexToTimeRepresentation(i);
                            //dashCount++;
                        }
                        if (combinedArray[i] == INELIGIBLE && combinedArray[i + 1] == ELIGIBLE && hourCount != 0) {
                            hours = hours + ", " + storeHours.indexToTimeRepresentation(i + 1);
                            //commaCount++;
                        }
                    }
                    System.out.println("\t" + employeeArray[j].profile.toString() + ": " + hours);
                }
                j++;
            }
        }
    }

    public void printAvailableWorkers(Employment employment){
        printAvailableWorkers("sunday",employment);
        printAvailableWorkers("monday",employment);
        printAvailableWorkers("tuesday",employment);
        printAvailableWorkers("wednesday",employment);
        printAvailableWorkers("thursday",employment);
        printAvailableWorkers("friday",employment);
        printAvailableWorkers("saturday",employment);
    }

    public void printEmployment(Employment employment){

        int i = 0;
        Employee[] employeeArray = employment.getEmployment();
        while(i < employment.getSize() && employeeArray[i]!=null){
            System.out.println(employeeArray[i].getProfile().toString());
            i++;
        }
    }
}
