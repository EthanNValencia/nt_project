package com.nephew.oesa.services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.stereotype.Service;

import com.nephew.oesa.dto.AppointmentDto;
import com.nephew.oesa.dto.EmployeeDto;
import com.nephew.oesa.dto.FAQsDto;
import com.nephew.oesa.dto.OfficeDto;
import com.nephew.oesa.entities.Appointment;
import com.nephew.oesa.entities.FAQs;
import com.nephew.oesa.entities.Office;
import com.nephew.oesa.entities.employee.Employee;

@Service
public class DtoService {
	
	public FAQsDto convertFAQsDto(FAQs faq) {
		FAQsDto faqsDto = new FAQsDto();
		faqsDto.setAnswer(faq.getAnswer());
		faqsDto.setQuestion(faq.getQuestion());
		faqsDto.setQuestionIsAnswered(faq.isQuestionIsAnswered());
		return faqsDto;
	}

	public ArrayList<FAQsDto> convertFAQsDto(ArrayList<FAQs> faqs) {
		ArrayList<FAQsDto> faqsDto = new ArrayList<>();
		int key = 0;
		for(FAQs faq : faqs) {
			FAQsDto faqDto = new FAQsDto();
			faqDto.setKey(key); // This is to provide the front-end with a unique key. 
			faqDto.setAnswer(faq.getAnswer());
			faqDto.setQuestion(faq.getQuestion());
			faqDto.setQuestionIsAnswered(faq.isQuestionIsAnswered());
			faqsDto.add(faqDto);
			key = key +  1;
		}
		return faqsDto;
	}

	public List<EmployeeDto> convertEmployeesDto(Collection<Employee> employees, boolean nullAppointments) {
		List<EmployeeDto> employeesDto = new ArrayList<>();
		int key = 0;
		for(Employee employee: employees) {
			if(nullAppointments) {
				employee.setAppointments(null);
			}
			employeesDto.add(new EmployeeDto(employee, key));
			key = key + 1;
		}
		return employeesDto;
	}
	
	public EmployeeDto convertEmployeeDto(Employee employee, boolean nullAppointments) {
		EmployeeDto employeeDto = new EmployeeDto();
		int key = 0;
		if(nullAppointments) {
			employee.setAppointments(null);
		}
		employeeDto = new EmployeeDto(employee, key);
		return employeeDto;
	}

	public AppointmentDto convertToAppointmentDto(Appointment newAppointment) {
		AppointmentDto dto = new AppointmentDto(newAppointment);
		return dto;
	}

	public List<OfficeDto> convertOfficesToDtos(List<Office> offices) {
		 List<OfficeDto> officesDto = new ArrayList<>();
		 for(Office office : offices) {
			 officesDto.add(new OfficeDto(office));
		 }
		return officesDto;
	}
	
	
	
}
