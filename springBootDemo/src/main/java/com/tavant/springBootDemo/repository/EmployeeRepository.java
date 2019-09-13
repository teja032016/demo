package com.tavant.springBootDemo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
 
import com.tavant.springBootDemo.entity.Employee;
 
@Repository
public interface EmployeeRepository extends JpaRepository<Employee,Long> {
 
}
