package com.bobsantosjr.openapiv3.springdocsample.rest.employee;

import com.bobsantosjr.openapiv3.springdocsample.core.employee.Employee;
import com.bobsantosjr.openapiv3.springdocsample.core.employee.EmployeeRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import static java.util.Objects.requireNonNull;

@RestController
@RequestMapping("/employees")
public class EmployeeController {
    private final EmployeeRepository employeeRepository;

    public EmployeeController(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(description = "Creates an employees", responses = {
        @ApiResponse(responseCode = "200", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = Employee.class)))
    })
    public ResponseEntity<?> create(@RequestBody EmployeeCommand command) {
        employeeRepository.add(new Employee(null, command.getFirstName(), command.getLastName()));
        return ResponseEntity.ok(requireNonNull(employeeRepository.list().stream().reduce((first, second) -> second).orElse(null)));
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(description = "Retrieves all employees", responses = {
        @ApiResponse(responseCode = "200", description = "List of employees", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, array = @ArraySchema(schema = @Schema(implementation = Employee.class)))),
        @ApiResponse(responseCode = "404", description = "No employees found")
    })
    public ResponseEntity<?> get() {
        List<Employee> employees = employeeRepository.list();
        return employees.size() > 0 ? ResponseEntity.ok(employeeRepository.list()) : ResponseEntity.notFound().build();
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(description = "Retrieves a single employee", responses = {
        @ApiResponse(responseCode = "200", description = "Employee with given ID found", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = Employee.class))),
        @ApiResponse(responseCode = "404", description = "Employee with given ID not found")
    })
    public ResponseEntity<?> get(@Parameter(description = "Unique employee ID") @PathVariable long id) {
        Optional<Employee> employee = employeeRepository.get(id);
        return ResponseEntity.of(employee);
    }

    @DeleteMapping(value = "/{id}")
    @Operation(description = "Deletes an employee", responses = {
        @ApiResponse(responseCode = "204", description = "Successfully deleted employee with given ID"),
        @ApiResponse(responseCode = "404", description = "Employee with given ID not found")
    })
    public ResponseEntity<Void> delete(@Parameter(description = "Unique employee ID") @PathVariable long id) {
        return employeeRepository.delete(id) ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}
