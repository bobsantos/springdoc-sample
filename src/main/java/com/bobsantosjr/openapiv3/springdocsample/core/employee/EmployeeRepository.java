package com.bobsantosjr.openapiv3.springdocsample.core.employee;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public class EmployeeRepository {
    private final Map<Long, Employee> employees;

    public EmployeeRepository(Map<Long, Employee> employees) {
        this.employees = employees;
    }

    public void add(Employee employee) {
        Long id = generateId();
        employees.put(id, new Employee(id, employee.getFirstName(), employee.getLastName()));
    }

    public List<Employee> list() {
        return new ArrayList<>(employees.values());
    }

    public Optional<Employee> get(long id) {
        return Optional.ofNullable(employees.get(id));
    }

    public boolean delete(long id) {
        return employees.remove(id) != null;
    }

    private Long generateId() {
        return (long) (employees.size() + 1);
    }
}
