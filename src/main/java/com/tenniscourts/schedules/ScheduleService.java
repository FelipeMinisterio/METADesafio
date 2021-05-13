package com.tenniscourts.schedules;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class ScheduleService {

	private final ScheduleRepository scheduleRepository;

	private final ScheduleMapper scheduleMapper;

	public ScheduleService() {
		this.scheduleMapper = new ScheduleMapper() {

			@Override
			public List<ScheduleDTO> map(List<Schedule> source) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ScheduleDTO map(Schedule source) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public Schedule map(ScheduleDTO source) {
				Schedule schedule = new Schedule();
				schedule.setStartDateTime(source.getStartDateTime());
				schedule.setEndDateTime(source.getEndDateTime());
				schedule.getTennisCourt().setId(source.getId());
				return schedule;
			}
		};
		this.scheduleRepository = null;
	}

	public ScheduleDTO addSchedule(Long tennisCourtId, CreateScheduleRequestDTO createScheduleRequestDTO) {
		// TODO: implement addSchedule
		return null;
	}

	public List<ScheduleDTO> findSchedulesByDates(LocalDateTime startDate, LocalDateTime endDate) {
		// TODO: implement
		return null;
	}

	public ScheduleDTO findSchedule(Long scheduleId) {
		// TODO: implement
		return null;
	}

	public List<ScheduleDTO> findSchedulesByTennisCourtId(Long tennisCourtId) {
		return scheduleMapper.map(scheduleRepository.findByTennisCourt_IdOrderByStartDateTime(tennisCourtId));
	}
}
