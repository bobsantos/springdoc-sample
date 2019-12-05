package com.bobsantosjr.openapiv3.springdocsample.rest.employee;

import com.bobsantosjr.openapiv3.springdocsample.core.employee.Employee;
import com.bobsantosjr.openapiv3.springdocsample.core.employee.EmployeeRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static java.util.Objects.requireNonNull;

@RestController
@RequestMapping("/employees")
public class EmployeeController {
    private final EmployeeRepository employeeRepository;

    public EmployeeController(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody EmployeeCommand command) {
        employeeRepository.add(new Employee(null, command.getFirstName(), command.getLastName()));
        return ResponseEntity.ok(requireNonNull(employeeRepository.list().stream().reduce((first, second) -> second).orElse(null)));
    }

    @GetMapping
    public ResponseEntity<?> get() {
        return ResponseEntity.ok(employeeRepository.list());
    }
}
