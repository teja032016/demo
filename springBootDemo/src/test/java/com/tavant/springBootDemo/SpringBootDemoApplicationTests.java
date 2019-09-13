package com.tavant.springBootDemo;

import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

import com.tavant.springBootDemo.controller.EmployeeRestController;
import com.tavant.springBootDemo.entity.Employee;
import com.tavant.springBootDemo.repository.EmployeeRepository;

@RunWith(SpringRunner.class)
@SpringBootTest

public class SpringBootDemoApplicationTests {

	@Autowired
	private EmployeeRestController employeeRestController;
	
	@Autowired
	private EmployeeRepository employeeRepository;
	
	@Before
	public void initH2DBData() {
		Employee employee = new Employee();
		
		employee.setDepartment("CSE");
		employee.setName("Teju");
		employee.setSalary(100);
		Employee a = employeeRepository.save(employee);
		System.out.println("new emp id generated : "+a.getId());
		
		Employee employee1 = new Employee();
		
		employee1.setDepartment("CSE");
		employee1.setName("mallesh");
		employee1.setSalary(200);
		Employee b = employeeRepository.save(employee1);
		System.out.println("new emp id generated : "+b.getId());
		
		Employee employee2 = new Employee();
		
		employee2.setDepartment("CSE");
		employee2.setName("kallesh");
		employee2.setSalary(300);
		Employee c = employeeRepository.save(employee2);
		System.out.println("new emp id generated : "+c.getId());
		
	}

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Test
	public void getAllEmployeesTest() {
		List<Employee> employees = employeeRestController.getEmployees();
		System.out.println(employees.size());
		Assert.assertNotNull(employees);
		/*for (Employee employee : employees) {
			employee.get
		}*/
		
	}
	@Test
	public void getEmployeeTest() {
		Employee employee = employeeRestController.getEmployee(1L);
		System.out.println(employee.getName());
		Assert.assertNotNull(employee);
		Assert.assertEquals(employee.getName(), "Teju");
		Assert.assertEquals(employee.getDepartment(), "CSE");
		Assert.assertEquals(employee.getSalary(), new Integer(100));
		
		Employee employee1 = employeeRestController.getEmployee(2L);
		Assert.assertNotNull(employee1);
		Assert.assertEquals(employee1.getName(), "mallesh");
		Assert.assertEquals(employee1.getDepartment(), "CSE");
		Assert.assertEquals(employee1.getSalary(), new Integer(200));
		
		Employee employee2 = employeeRestController.getEmployee(3L);
		Assert.assertNotNull(employee2);
		Assert.assertEquals(employee2.getName(), "kallesh");
		Assert.assertEquals(employee2.getDepartment(), "CSE");
		Assert.assertEquals(employee2.getSalary(), new Integer(300));
		
		}
	
	@Test
	public void saveEmployeeTest() {
		Employee employee = new Employee();
		employee.setName("ajit");
		employeeRepository.save(employee);
		List<Employee> employees = employeeRestController.getEmployees();
		Assert.assertEquals(employees.size(),4);
		Assert.assertEquals(employees.get(3).getName(),"ajit");
		
	}
	
	@Test
	public void deleteEmployeeTest() {
		employeeRepository.deleteById(1L);
		List<Employee> employees = employeeRestController.getEmployees();
		Assert.assertEquals(employees.size(),2);
		
	}
	
	@Test
	public void updateEmployeeTest() {
		String query = "update employee set EMPLOYEE_NAME='ajit' where EMPLOYEE_NAME='mallesh'";
	       
		 jdbcTemplate.execute(query);
		 List<Employee> employees= employeeRepository.findAll();
		 Assert.assertEquals(employees.get(0).getName(),"ajit");
		
		
		    
		
		
		
		
	}

}
