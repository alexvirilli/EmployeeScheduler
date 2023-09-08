package com.example.employeeschedulergui;


public class Main {

    public static void main(String args[]){

        StoreHours jansHours = new StoreHours();
        jansHours.setOpen("sunday", "11:00-16:30");
        jansHours.setClosed("monday");
        jansHours.setOpen("tuesday", "11:00-18:00");
        jansHours.setOpen("wednesday","11:00-19:00");
        jansHours.setOpen("thursday","11:00-19:00");
        jansHours.setOpen("friday","11:00-18:00");
        jansHours.setOpen("saturday","10:00-17:00");

        EmployeeHours alexHours = new EmployeeHours();
        alexHours.setIneligible("sunday");
        alexHours.setIneligible("monday","8:00-14:00");
        alexHours.setIneligible("monday","17:00-21:00");
        alexHours.setIneligible("tuesday","14:00-18:00");

        Date dob = new Date("9/17/2002");
        int id = 1922;
        String fname = "Alexander";
        String lname = "Virilli";
        Profile profile = new Profile(fname,lname,dob,id);

        Employee alex = new Employee(profile,alexHours);

        Employment employment = new Employment();
        employment.add(alex);

        Date dob2 = new Date("9/17/2002");
        int id2 = 1922;
        String fname2 = "Alexander";
        String lname2 = "Virilli";
        Profile profile2 = new Profile(fname2,lname2,dob2,id2);

        EmployeeHours employeeHours = new EmployeeHours();
        Employee alex2 = new Employee(profile2, employeeHours);
        //employment.add(alex2);

        Store jansBoutique = new Store("jansBoutique",jansHours);

        jansBoutique.printEmployment(employment);

        jansBoutique.printHours();
        alex.printAvailability();
    }
}
