package com.nephew.oesa;

import java.time.LocalTime;
import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.nephew.oesa.entities.Day;
import com.nephew.oesa.entities.employee.Employee;
import com.nephew.oesa.entities.employee.EmployeeDailySchedule;
import com.nephew.oesa.services.EmployeeService;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest(properties = "eureka.client.enabled=false")
public class ServiceDtoTests {
	
	@Autowired
	private EmployeeService employeeService;
	
	public Set<EmployeeDailySchedule> generateEmployeeDailySchedule(Employee employee) {
		Set<EmployeeDailySchedule> schedule = new HashSet<>();
		schedule.add(new EmployeeDailySchedule(Day.Monday, LocalTime.of(7, 0, 0), LocalTime.of(18, 30, 0), new Employee(employee.getId())));
		schedule.add(new EmployeeDailySchedule(Day.Tuesday, LocalTime.of(7, 0, 0), LocalTime.of(18, 30, 0), new Employee(employee.getId())));
		schedule.add(new EmployeeDailySchedule(Day.Wednesday, LocalTime.of(7, 0, 0), LocalTime.of(18, 30, 0), new Employee(employee.getId())));
		schedule.add(new EmployeeDailySchedule(Day.Thursday, LocalTime.of(7, 0, 0), LocalTime.of(18, 30, 0), new Employee(employee.getId())));
		schedule.add(new EmployeeDailySchedule(Day.Friday, LocalTime.of(13, 0, 0), LocalTime.of(15, 0, 0), new Employee(employee.getId())));
		return schedule;
	}
	
	@Order(1)
	@Test
	void init() {
		
	}
	
}
