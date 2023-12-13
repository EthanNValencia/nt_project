package com.nephew.oesa;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.nephew.oesa.entities.employee.Employee;
import com.nephew.oesa.entities.services.Services;
import com.nephew.oesa.services.EmployeeService;
import com.nephew.oesa.services.ServicesService;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest(properties = "eureka.client.enabled=false")
public class ServiceTests {

	private final long MELISSA_ID = 1L;
	private final String MELISSA_FIRST_NAME = "Melissa";
	private final String MELISSA_LAST_NAME = "Meiste";
	
	@Autowired
	private EmployeeService employeeService;
	
	@Autowired
	private ServicesService servicesService;
	
	@Order(1000)
	@Test
	void updateServicesFromMelissaShouldEqual3() {
		Set<Long> serviceIds = new HashSet<>();
		serviceIds.add(1L);
		serviceIds.add(2L);
		serviceIds.add(3L);
		Employee melissa = new Employee();
		try {
			melissa = employeeService.updateEmployeeServices(1L, serviceIds);
		} catch (Exception e) {
			e.printStackTrace();
		}
		assertEquals(3, melissa.getServices().size());
	}

	@Order(1001)
	@Test
	void updateServicesForMelissaShouldEqual4() {
		Set<Long> serviceIds = new HashSet<>();
		serviceIds.add(1L);
		serviceIds.add(2L);
		serviceIds.add(3L);
		serviceIds.add(4L);
		Employee melissa = employeeService.findEmployeeById(MELISSA_ID);
		List<Services> services = servicesService.findAllById(serviceIds);
		melissa.convertServicesListToSet(services);
		melissa = employeeService.updateEmployee(melissa);
		assertEquals(4, melissa.getServices().size());
	}
	
}
