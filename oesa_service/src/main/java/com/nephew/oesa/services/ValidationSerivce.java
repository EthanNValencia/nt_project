package com.nephew.oesa.services;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

import org.springframework.stereotype.Service;

import com.nephew.oesa.dto.AppointmentDto;
import com.nephew.oesa.dto.EmployeeDto;

@Service
public class ValidationSerivce {
	
	public boolean isNewAppointmentValid(EmployeeDto employeeDto, LocalDateTime beginTime, LocalDateTime endTime) {
		LocalDate beginLocalDate = beginTime.toLocalDate();
		LocalDate endLocalDate = endTime.toLocalDate();
		int comparison = beginLocalDate.compareTo(endLocalDate);
		if (comparison < 0) {
		   	return false;
		} else if (comparison > 0) {
		    return false;
		} else {
		    // Dates are equal, which is what I want. 
		}
		if(employeeDto.getAppointments().containsKey(beginLocalDate)) {
			ArrayList<AppointmentDto> appArrayList = employeeDto.getAppointments().get(beginLocalDate);
			for(AppointmentDto app : appArrayList) {
				if(!compareLocalDateTimes(beginTime, app.getBeginTime())) {
					return false;
				}
				if(!compareLocalDateTimes(endTime, app.getEndTime())) {
					return false;
				}
			}
		} else {
			// If there are no appointments for this day, then there can be no appointment conflicts. 
			return true;
		}
		return true;
	}

	private boolean compareLocalDateTimes(LocalDateTime time1, LocalDateTime time2) {
		int compare = time1.compareTo(time2);
		if(compare == 0) {
			return false;
		}
		return true;
	}
}
