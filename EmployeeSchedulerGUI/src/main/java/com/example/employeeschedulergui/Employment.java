package com.example.employeeschedulergui;


public class Employment {

    private Employee[] employment;
    private int size;

    public Employment(){
        this.size = 4;
        this.employment = new Employee[size];
    }

    public int getSize(){
        return this.size;
    }

    public Employee[] getEmployment(){
        return this.employment;
    }


    private void grow(){
        int tempSize = size;
        size+=4;
        Employee[] tempArr = new Employee[size];
        for(int i = 0; i < tempSize; i++){
            tempArr[i] = employment[i];
        }
        employment = tempArr;
    }

    //returns true if student is in roster, false otherwise
    public boolean contains(Employee employee){
        for(int i = 0; i < size; i++){
            if(employment[i]==null) return false;
            if(employment[i].equals(employee)) return true;
        }
        return false;
    }

    public Employee getEmployee(Profile profile){

        int i = 0;
        while(i < this.size && employment[i]!=null){
            if(employment[i].getProfile().equals(profile)) return employment[i];
            i++;
        }
        return null;

    }

    public static final int NOT_FOUND = -1;
    private int find(Employee employee){
        for(int i = 0; i < size; i++){
            if(employment[i] == null) return NOT_FOUND;
            if(employment[i].equals(employee)) return i;
        }
        return NOT_FOUND;
    }

    public boolean add(Employee employee){
        if(contains(employee)) return false;
        if(employment[size-1]!=null) grow();
        int i = 0;
        while(employment[i]!=null){
            i++;
        }
        employment[i] = employee;
        return true;
    }

    public boolean remove(Employee employee){
        if(!contains(employee)) return false;
        if(find(employee)==-1) return false;
        int index = find(employee);
        for(int i = index; i < size; i++){
            if(i+1<size && employment[i+1]==null){
                employment[i] = null;
                return true;
            }
            if(i+1 < size) employment[i] = employment[i+1];
        }
        employment[size-1] = null;
        return true;
    }

    public void print(){
        int i = 0;
        while(i < this.size && employment[i]!=null){
            System.out.println(employment[i].getProfile().toString());
            i++;
        }
    }


}
