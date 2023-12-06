package com.nephew.es.errorservice.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nephew.es.errorservice.entities.FrontEndError;
import com.nephew.es.errorservice.repositories.FrontEndErrorRepository;

@Service
public class FrontEndErrorService {
	
	@Autowired
	private FrontEndErrorRepository frontEndErrorRepository;

	public void saveNewFrontEndError(FrontEndError error) {
		frontEndErrorRepository.save(error);
	}
	
	
	
}
