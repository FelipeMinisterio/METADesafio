package com.tenniscourts.schedules;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/schedule")
public class ScheduleRestController {

	@Autowired
	private ScheduleController scheduleController;
	
	@PostMapping(value="/adding")
	public ResponseEntity<Void> addScheduleByTennisCourtsId(CreateScheduleRequestDTO createScheduleRequestDTO){
		return scheduleController.addScheduleTennisCourt(createScheduleRequestDTO);
	}
}
