package com.bobsantosjr.openapiv3.springdocsample.rest.employee;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Employee details")
public class EmployeeCommand {
    @Schema(description = "First name", required = true)
    private String firstName;

    @Schema(description = "Last name")
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
