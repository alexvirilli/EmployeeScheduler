package com.example.employeeschedulergui;

public class Employee implements Comparable<Employee> {

    Profile profile;
    EmployeeHours employeeHours;

    //constructors
    public Employee(){
        this.profile = null;
        this.employeeHours = null;
    }

    public Employee(Profile profile, EmployeeHours employeeHours){
        this.profile = profile;
        this.employeeHours = employeeHours;
    }
    //getters
    public Profile getProfile(){
        return this.profile;
    }
    public EmployeeHours getEmployeeHours(){
        return this.employeeHours;
    }

    //2 employees are equal if they have the same profile
    //takes profile as a parameter
    public boolean equals(Object obj){
        if(this == obj) return true;
        if(!(obj instanceof  Employee)) return false;
        Employee employee = (Employee) obj;
        return this.profile.equals(employee.getProfile());
    }

    public int compareTo(Employee employee){
        if(this.profile.compareTo(employee.profile) < 0) return -1;
        if(this.profile.compareTo(employee.profile) > 0) return 1;
        return 0;
    }

    public void printAvailability(){
        System.out.println(profile.toString() + ": availability");
        employeeHours.printAvailability();
    }

    //to string
    @Override
    public String toString(){
        return this.profile.toString();
    }



}
