package com.bobsantosjr.openapiv3.springdocsample.rest.employee;

import com.bobsantosjr.openapiv3.springdocsample.core.employee.Employee;
import com.bobsantosjr.openapiv3.springdocsample.core.employee.EmployeeRepository;
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

    @PostMapping
    public ResponseEntity<?> create(@RequestBody EmployeeCommand command) {
        employeeRepository.add(new Employee(null, command.getFirstName(), command.getLastName()));
        return ResponseEntity.ok(requireNonNull(employeeRepository.list().stream().reduce((first, second) -> second).orElse(null)));
    }

    @GetMapping
    public ResponseEntity<?> get() {
        List<Employee> employees = employeeRepository.list();

        return employees.size() > 0 ? ResponseEntity.ok(employeeRepository.list()) : ResponseEntity.notFound().build();
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<?> get(@PathVariable long id) {
        Optional<Employee> employee = employeeRepository.get(id);
        if(employee.isPresent()) {
            return ResponseEntity.ok(employee.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
