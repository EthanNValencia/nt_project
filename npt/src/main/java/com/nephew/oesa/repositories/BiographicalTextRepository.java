package com.nephewtechnologies.npt.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.nephewtechnologies.npt.entities.employee.BiographicalText;

public interface BiographicalTextRepository extends JpaRepository<BiographicalText, Long>{

	@Query(value = "SELECT * FROM biographical_text ORDER BY position", nativeQuery = true)
	public List<BiographicalText> findAllOrderByPosition();
	
}
