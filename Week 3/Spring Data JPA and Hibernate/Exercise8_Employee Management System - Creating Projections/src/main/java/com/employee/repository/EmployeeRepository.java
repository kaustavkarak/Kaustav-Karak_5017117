package com.employee.repository;

import com.employee.dto.EmployeeDTO;
import com.employee.model.Employee;
import com.employee.projection.EmployeeCustomProjection;
import com.employee.projection.EmployeeNameAndDepartmentProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    List<EmployeeNameAndDepartmentProjection> findByDepartmentId(Long departmentId);

    @Query("SELECT new com.employee.dto.EmployeeDTO(e.name, d.name) FROM Employee e JOIN e.department d WHERE d.id = :departmentId")
    List<EmployeeDTO> findEmployeeDTOsByDepartmentId(@Param("departmentId") Long departmentId);

    @Query("SELECT e.name AS name, d.name AS departmentName FROM Employee e JOIN e.department d")
    List<EmployeeCustomProjection> findCustomEmployeeDetails();
}
