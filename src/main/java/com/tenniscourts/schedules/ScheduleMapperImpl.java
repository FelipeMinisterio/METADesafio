package com.tenniscourts.schedules;

import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class ScheduleMapperImpl implements ScheduleMapper{

	@Override
	public Schedule map(ScheduleDTO source) {
		Schedule schedule = new Schedule();
		schedule.setStartDateTime(source.getStartDateTime());
		schedule.setEndDateTime(source.getEndDateTime());
		schedule.getTennisCourt().setId(source.getTennisCourtId());

		return schedule;
	}

	@Override
	public ScheduleDTO map(Schedule source) {
		ScheduleDTO scheduleDTO = new ScheduleDTO();
		scheduleDTO.setStartDateTime(source.getStartDateTime());
		scheduleDTO.setEndDateTime(source.getEndDateTime());
		scheduleDTO.setTennisCourtId(source.getTennisCourt().getId());
		scheduleDTO.setId(source.getId());
		return scheduleDTO;
	}

	@Override
	public List<ScheduleDTO> map(List<Schedule> source) {
		// TODO Auto-generated method stub
		return null;
	}

}
