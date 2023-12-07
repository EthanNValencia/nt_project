package com.nephew.error.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nephew.error.entities.FrontEndError;
import com.nephew.error.repositories.FrontEndErrorRepository;

@Service
public class FrontEndErrorService {
	
	@Autowired
	private FrontEndErrorRepository frontEndErrorRepository;

	public void saveNewFrontEndError(FrontEndError error) {
		frontEndErrorRepository.save(error);
	}
	
	
	
}
