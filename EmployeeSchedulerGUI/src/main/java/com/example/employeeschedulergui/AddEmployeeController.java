package com.example.employeeschedulergui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.time.format.DateTimeFormatter;

public class AddEmployeeController {

    @FXML
    private DatePicker dob;

    @FXML
    private TextField employeeID;

    @FXML
    private TextField firstName;

    @FXML
    private TextField friday1;

    @FXML
    private TextField friday2;

    @FXML
    private TextField lastName;

    @FXML
    private TextArea messageArea;

    @FXML
    private TextField monday1;

    @FXML
    private TextField monday2;

    @FXML
    private TextField saturday1;

    @FXML
    private TextField saturday2;

    @FXML
    private TextField sunday1;

    @FXML
    private TextField sunday2;

    @FXML
    private TextField thursday1;

    @FXML
    private TextField thursday2;

    @FXML
    private TextField tuesday1;

    @FXML
    private TextField tuesday2;

    @FXML
    private TextField wednesday1;

    @FXML
    private TextField wednesday2;

    private Employment employment;

    @FXML
    public void initialize(Employment employment){
        this.employment = employment;
    }

    private boolean isInt(String n){
        try{
            Integer.parseInt(n);
            return true;
        } catch (NumberFormatException ex){
            return false;
        }
    }

    private Profile makeProfile(){
        String strDate = dob.getValue().format(DateTimeFormatter.ofPattern("MM/dd/yyyy"));
        Date date = new Date(strDate);
        String fname = firstName.getText();
        String lname = lastName.getText();
        int id = Integer.parseInt(employeeID.getText());
        return new Profile(fname,lname,date,id);
    }
    private EmployeeHours ineligibleBuilder(TextField day, String dayOfWeek, EmployeeHours employeeHours){
        if(!(day.getText() == null) && !day.getText().isEmpty()){
            String dayHrs = day.getText();
            employeeHours.setIneligible(dayOfWeek,dayHrs);
        }
        return employeeHours;
    }
    private EmployeeHours makeEmployeeHours(){
        EmployeeHours employeeHours = new EmployeeHours();
        employeeHours = ineligibleBuilder(sunday1,"sunday",employeeHours);
        employeeHours = ineligibleBuilder(sunday2,"sunday",employeeHours);
        employeeHours = ineligibleBuilder(monday1,"monday",employeeHours);
        employeeHours = ineligibleBuilder(monday2,"monday",employeeHours);
        employeeHours = ineligibleBuilder(tuesday1,"tuesday",employeeHours);
        employeeHours = ineligibleBuilder(tuesday2,"tuesday",employeeHours);
        employeeHours = ineligibleBuilder(wednesday1,"wednesday",employeeHours);
        employeeHours = ineligibleBuilder(wednesday2,"wednesday",employeeHours);
        employeeHours = ineligibleBuilder(thursday1,"thursday",employeeHours);
        employeeHours = ineligibleBuilder(thursday2,"thursday",employeeHours);
        employeeHours = ineligibleBuilder(friday1,"friday",employeeHours);
        employeeHours = ineligibleBuilder(friday2,"friday",employeeHours);
        employeeHours = ineligibleBuilder(saturday1,"saturday",employeeHours);
        employeeHours = ineligibleBuilder(saturday2,"saturday",employeeHours);
        return employeeHours;
    }

    private Employee makeEmployee(Profile profile, EmployeeHours employeeHours) {
        Employee employee = new Employee(profile, employeeHours);
        return employee;
    }

    private void reset(){
        firstName.setText("");
        lastName.setText("");
        employeeID.setText("");
        dob.setValue(null);
        sunday1.setText(null);
        sunday2.setText(null);
        monday1.setText(null);
        monday2.setText(null);
        tuesday1.setText(null);
        tuesday2.setText(null);
        wednesday1.setText(null);
        wednesday2.setText(null);
        thursday1.setText(null);
        thursday2.setText(null);
        friday1.setText(null);
        friday2.setText(null);
        saturday1.setText(null);
        saturday2.setText(null);
    }

    private boolean catchCrash(){
        if(firstName.getText().equals("") || lastName.getText().equals("") || employeeID.getText().equals("") || dob.getValue() == null){
            messageArea.setText("Please fill out all employee information");
            return false;
        }
        Date date = new Date(dob.getValue().format(DateTimeFormatter.ofPattern("MM/dd/yyyy")));
        if(!date.isSixteen()){
            messageArea.setText("DOB Invalid: younger than 16 years old");
            return false;
        }
        if(!checkAllTimeInputs()){
            messageArea.setText("Invalid time input");
            return false;
        }
        return true;
    }

    private boolean checkAllTimeInputs(){
        if(!checkValidInputForTime(sunday1)) return false;
        if(!checkValidInputForTime(sunday2)) return false;
        if(!checkValidInputForTime(monday1)) return false;
        if(!checkValidInputForTime(monday2)) return false;
        if(!checkValidInputForTime(tuesday1)) return false;
        if(!checkValidInputForTime(tuesday2)) return false;
        if(!checkValidInputForTime(wednesday1)) return false;
        if(!checkValidInputForTime(wednesday2)) return false;
        if(!checkValidInputForTime(thursday1)) return false;
        if(!checkValidInputForTime(thursday2)) return false;
        if(!checkValidInputForTime(friday1)) return false;
        if(!checkValidInputForTime(friday2)) return false;
        if(!checkValidInputForTime(saturday1)) return false;
        if(!checkValidInputForTime(saturday2)) return false;
        return true;
    }

    private boolean checkValidInputForTime(TextField textField){
        if(textField.getText() == null || textField.getText().isEmpty()) return true;
        if(!textField.getText().contains(":")) return false;
        if(textField.getText().contains("-")){
            String twoTimes[] = textField.getText().split("-");
            String time1 = twoTimes[0];
            String time2 = twoTimes[1];
            if(!time1.contains(":") || !time2.contains(":")) return false;
            String time1split[] = time1.split(":");
            String time2split[] = time2.split(":");
            if(!isInt(time1split[0]) || !isInt(time1split[1]) || !isInt(time2split[0]) || !isInt(time2split[1])) return false;
            int time1hr = Integer.parseInt(time1split[0]);
            int time1min = Integer.parseInt(time1split[1]);
            int time2hr = Integer.parseInt(time2split[0]);
            int time2min = Integer.parseInt(time2split[1]);
            if(time1hr < 0 || time1hr > 24 || time2hr < 0 || time2hr > 24) return false;
            if(time1hr == 24 && time1min > 0 || time2hr == 24 && time2min > 0) return false;
            if(time1hr > time2hr) return false;
            if(time1hr == time2hr && time1min > time2min) return false;
            if(time1min < 0 || time1min > 59 || time2min < 0 || time2min > 59) return false;
            if(time1min % 30 != 0 || time2min % 30 != 0) return false;
        } else {
            String time[] = textField.getText().split(":");
            if(!isInt(time[0]) || !isInt(time[1])) return false;
            int hrs = Integer.parseInt(time[0]);
            int min = Integer.parseInt(time[1]);
            if(hrs < 0 || hrs > 24) return false;
            if(hrs == 24 && min > 0) return false;
            if(min < 0 || min > 59) return false;
            if(min % 30 != 0) return false;
        }
        return true;
    }

    @FXML
    void addEmployee(ActionEvent event) {
        if(catchCrash()) {
            Employee employee = makeEmployee(makeProfile(), makeEmployeeHours());
            if (!employment.contains(employee)) {
                employment.add(employee);
                messageArea.setText(employee.toString() + " added to employment");
                reset();
            } else {
                messageArea.setText(employee.toString() + " is already employed");
            }
        }
    }

    @FXML
    void removeEmployee(ActionEvent event) {
        Employee employee = makeEmployee(makeProfile(),makeEmployeeHours());
        if(employment.contains(employee)){
            employment.remove(employee);
            messageArea.setText(employee.toString() + " removed from employment");
            reset();
        } else {
            messageArea.setText(employee.toString() + " is not employed");
        }
    }

}
