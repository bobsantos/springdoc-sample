package com.bobsantosjr.openapiv3.springdocsample.core.employee;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.Objects;

@Schema(description = "Employee detail")
public class Employee {
    @Schema(description = "Unique ID representing the employee", required = true)
    private Long id;

    @Schema(description = "First name")
    private String firstName;

    @Schema(description = "Last name")
    private String lastName;

    public Employee(Long id, String firstName, String lastName) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return Objects.equals(firstName, employee.firstName) &&
                Objects.equals(lastName, employee.lastName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName);
    }
}
