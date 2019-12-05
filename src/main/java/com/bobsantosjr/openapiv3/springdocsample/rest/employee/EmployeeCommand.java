package com.bobsantosjr.openapiv3.springdocsample.rest.employee;

public class EmployeeCommand {
    private String firstName;
    private String lastName;

    public EmployeeCommand(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }
}
