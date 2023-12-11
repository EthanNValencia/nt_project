package com.nephew.oesa.repositories;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.nephew.oesa.entities.FAQs;

@Repository
public interface FAQsRepository extends JpaRepository<FAQs, Long> {
	@Query(value="SELECT * FROM faqs WHERE question_is_answered = true;", nativeQuery=true)
	public Optional<ArrayList<FAQs>> selectAllAnsweredQuestions();
	
	@Query(value="SELECT * FROM faqs WHERE question_is_answered = false;", nativeQuery=true)
	public Optional<ArrayList<FAQs>> selectAllUnansweredQuestions();
	
	@Query("SELECT f FROM FAQs f WHERE f.company.id = :companyId")
	public List<FAQs> findByCompanyId(@Param("companyId") long companyId);
	
	 @Query("SELECT f FROM FAQs f WHERE f.company.companyUrl = :companyUrl")
	 public List<FAQs> findByCompanyUrl(@Param("companyUrl") String companyUrl);
	
}
