package com.example.employeeschedulergui;


public class Profile implements Comparable<Profile>{

    private String fname;
    private String lname;
    private Date dob;
    private int id;

    //constructors
    public Profile(){
        this.fname = null;
        this.lname = null;
        this.dob = null;
        this.id = 0;
    }

    public Profile(String fname, String lname, Date dob, int id){
        this.fname = fname;
        this.lname = lname;
        this.dob = dob;
        this.id = id;
    }

    //getters
    public String getFname(){
        return this.fname;
    }
    public String getLname(){
        return this.lname;
    }
    public Date getDob(){
        return this.dob;
    }
    public int getId(){
        return this.id;
    }

    //to string
    public String toString(){
        return this.fname + " " + this.lname + " " + this.dob.toString() +  " (id: " + this.id+")";
    }

    public int compareTo(Profile profile){
        if(this.fname.compareTo(profile.fname) < 0) return -1;
        if(this.fname.compareTo(profile.fname) > 0) return 1;
        if(this.lname.compareTo(profile.lname) < 0) return -1;
        if(this.lname.compareTo(profile.lname) > 0) return 1;
        if(this.dob.compareTo(profile.dob) < 0) return -1;
        if(this.dob.compareTo(profile.dob) > 0) return 1;
        if(this.id < profile.id) return -1;
        if(this.id > profile.id) return 1;
        return 0;
    }

    public boolean equals(Object obj){
        if(this == obj) return true;
        if(!(obj instanceof Profile)) return false;
        Profile profile = (Profile) obj;
        return this.compareTo(profile) == 0;
    }

}
