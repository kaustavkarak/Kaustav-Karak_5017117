package com.employee.controller;

import com.employee.exception.ResourceNotFoundException;
import com.employee.model.Department;
import com.employee.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/departments")
public class DepartmentController {

    @Autowired
    private DepartmentRepository departmentRepository;

    @GetMapping
    public List<Department> getAllDepartments() {
        return departmentRepository.findAll();
    }

    @PostMapping
    public Department createDepartment(@RequestBody Department department) {
        return departmentRepository.save(department);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Department> getDepartmentById(@PathVariable(value = "id") Long departmentId) {
        Department department = departmentRepository.findById(departmentId)
                .orElseThrow(() -> new ResourceNotFoundException("Department not found for this id :: " + departmentId));
        return ResponseEntity.ok().body(department);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Department> updateDepartment(@PathVariable(value = "id") Long departmentId,
                                                       @RequestBody Department departmentDetails) {
        Department department = departmentRepository.findById(departmentId)
                .orElseThrow(() -> new ResourceNotFoundException("Department not found for this id :: " + departmentId));

        department.setName(departmentDetails.getName());

        final Department updatedDepartment = departmentRepository.save(department);
        return ResponseEntity.ok(updatedDepartment);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDepartment(@PathVariable(value = "id") Long departmentId) {
        Department department = departmentRepository.findById(departmentId)
                .orElseThrow(() -> new ResourceNotFoundException("Department not found for this id :: " + departmentId));

        departmentRepository.delete(department);
        return ResponseEntity.ok().build();
    }
}
