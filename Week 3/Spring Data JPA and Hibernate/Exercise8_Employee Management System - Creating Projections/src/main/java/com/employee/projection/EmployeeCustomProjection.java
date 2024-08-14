package com.employee.projection;

import org.springframework.beans.factory.annotation.Value;

public interface EmployeeCustomProjection {

    @Value("#{target.name + ' (' + target.department.name + ')'}")
    String getFullNameWithDepartment();
}
