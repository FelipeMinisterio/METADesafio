package com.tenniscourts.tenniscourts;

import com.tenniscourts.exceptions.EntityNotFoundException;
import com.tenniscourts.schedules.ScheduleService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class TennisCourtService {

	private final TennisCourtRepository tennisCourtRepository;

	private final ScheduleService scheduleService;

	private final TennisCourtMapper tennisCourtMapper;

	public TennisCourtService() {
		this.tennisCourtMapper = new TennisCourtMapper() {

			@Override
			public TennisCourt map(TennisCourtDTO source) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public TennisCourtDTO map(TennisCourt source) {
				// TODO Auto-generated method stub
				return null;
			}
		};
		this.scheduleService = new ScheduleService();
		this.tennisCourtRepository = null;
	}

	public TennisCourtDTO addTennisCourt(TennisCourtDTO tennisCourt) {
		return tennisCourtMapper.map(tennisCourtRepository.saveAndFlush(tennisCourtMapper.map(tennisCourt)));
	}

	public TennisCourtDTO findTennisCourtById(Long id) {
		return tennisCourtRepository.findById(id).map(tennisCourtMapper::map).orElseThrow(() -> {
			throw new EntityNotFoundException("Tennis Court not found.");
		});
	}

	public TennisCourtDTO findTennisCourtWithSchedulesById(Long tennisCourtId) {
		TennisCourtDTO tennisCourtDTO = findTennisCourtById(tennisCourtId);
		tennisCourtDTO.setTennisCourtSchedules(scheduleService.findSchedulesByTennisCourtId(tennisCourtId));
		return tennisCourtDTO;
	}
}
