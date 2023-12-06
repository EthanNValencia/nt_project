package com.common.model.oesa.repositories;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.common.model.oesa.entities.employee.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
	
	@Query(value="SELECT e.* FROM employee e JOIN employee_service nepa ON e.id = nepa.employee_id JOIN services pa ON pa.id = nepa.service_id WHERE pa.name = :name", nativeQuery=true)
	public Optional<HashSet<Employee>> selectEmployeeThatHasSpeciality(@Param("name") String name);
	
	@Query(value="SELECT e.* FROM employee e JOIN employee_service nepa ON e.id = nepa.employee_id JOIN services pa ON pa.id = nepa.service_id WHERE pa.name = :name", nativeQuery=true)
	public Optional<List<Employee>> selectListEmployeeThatHasSpeciality(@Param("name") String name);
	
	@Query
	public Optional<Employee> findByFirstNameAndLastName(String firstName, String lastName);

	public List<Employee> findAllByOrderById();

}
