package com.tenniscourts.reservations;

import com.tenniscourts.exceptions.EntityNotFoundException;
import com.tenniscourts.schedules.Schedule;
import com.tenniscourts.schedules.ScheduleDTO;
import com.tenniscourts.schedules.ScheduleMapperImpl;
import com.tenniscourts.schedules.ScheduleRepository;

import lombok.AllArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@AllArgsConstructor
public class ReservationService {

	@Autowired
	private final ReservationRepository reservationRepository;
	@Autowired
	private final ReservationMapperImpl reservationMapper;
	@Autowired
	private ScheduleMapperImpl scheduleMapper;
	@Autowired
	private ScheduleRepository scheduleRepository;

	public ReservationDTO bookReservation(CreateReservationRequestDTO createReservationRequestDTO) {
		return reservationMapper
				.map(reservationRepository.saveAndFlush(reservationMapper.map(createReservationRequestDTO)));
	}

	public List<Integer> findFreeSchedule(String dateSchedule) {
			List<Integer> dayHours = new ArrayList<Integer>();
			dayHours.add(8);dayHours.add(9);dayHours.add(10);dayHours.add(11);dayHours.add(12);dayHours.add(13);dayHours.add(14);
			dayHours.add(15);dayHours.add(16);dayHours.add(17);dayHours.add(18);dayHours.add(19);dayHours.add(20);dayHours.add(21);
		try {
			Date date = new SimpleDateFormat("yyyy-MM-dd").parse(dateSchedule);
			List<Date> schedulesDates = new ArrayList<Date>();
			List<Schedule> schedules = scheduleRepository.findByStartDateTimeContains(dateSchedule);
			for (Schedule schedule : schedules) {
				String scheduleDate = "" + schedule.getStartDateTime().getYear() + "-"
						+ schedule.getStartDateTime().getMonthValue() + "-" + schedule.getStartDateTime().getDayOfMonth();
				schedulesDates.add(new SimpleDateFormat("yyy-MM-dd").parse(scheduleDate));
			}
			for (int y=0;y<schedulesDates.size();y++) {
				if (schedulesDates.get(y).equals(date)) {
						for(int x=0;x<dayHours.size();x++) {
						if(schedules.get(y).getStartDateTime().getHour() == dayHours.get(x)) {
							dayHours.remove(x);
						}
					}
					
				} else {
					return dayHours;
				}
			}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return dayHours;
	}

	public List<ReservationDTO> bookReservations(CreateReservationsRequestDTOs createReservationsRequestDTOs) {
		List<ReservationDTO> listaReservas = new ArrayList<ReservationDTO>();
		for (Long id : createReservationsRequestDTOs.getTennisCourtId()) {
			ScheduleDTO schedule = new ScheduleDTO();
			schedule.setStartDateTime(LocalDateTime.parse(createReservationsRequestDTOs.getStartDateTime()));
			schedule.setEndDateTime(LocalDateTime.parse(createReservationsRequestDTOs.getEndDateTime()));
			schedule.setTennisCourtId(id);
			ScheduleDTO scheduleDTO = scheduleMapper.map(scheduleRepository.saveAndFlush(scheduleMapper.map(schedule)));
			// salvo o schedule
			ReservationDTO reservationDTO = new ReservationDTO();
			reservationDTO.setScheduledId(scheduleDTO.getId());
			reservationDTO.setValue(createReservationsRequestDTOs.getValue());
			reservationDTO.setGuestId(createReservationsRequestDTOs.getGuestId());
			reservationDTO.setScheduledId(scheduleDTO.getId());
			reservationDTO.setTennisCourtId(scheduleDTO.getTennisCourtId());
			reservationMapper.map(reservationRepository.saveAndFlush(reservationMapper.map(reservationDTO)));
			listaReservas.add(reservationDTO);
		}
		return listaReservas;
	}

	public ReservationDTO findReservation(Long reservationId) {
		return reservationRepository.findById(reservationId).map(reservationMapper::map).orElseThrow(() -> {
			throw new EntityNotFoundException("Reservation not found.");
		});
	}

	public List<Reservation> findAllReservations() {
		return reservationRepository.findAll();
	}

	public ReservationDTO cancelReservation(Long reservationId) {
		return reservationMapper.map(this.cancel(reservationId));
	}

	private Reservation cancel(Long reservationId) {
		return reservationRepository.findById(reservationId).map(reservation -> {

			this.validateCancellation(reservation);

			BigDecimal refundValue = getRefundValue(reservation);
			return this.updateReservation(reservation, refundValue, ReservationStatus.CANCELLED);

		}).orElseThrow(() -> {
			throw new EntityNotFoundException("Reservation not found.");
		});
	}

	private Reservation updateReservation(Reservation reservation, BigDecimal refundValue, ReservationStatus status) {
		reservation.setReservationStatus(status);
		reservation.setValue(reservation.getValue().subtract(refundValue));
		reservation.setRefundValue(refundValue);

		return reservationRepository.save(reservation);
	}

	private void validateCancellation(Reservation reservation) {
		if (!ReservationStatus.READY_TO_PLAY.equals(reservation.getReservationStatus())) {
			throw new IllegalArgumentException("Cannot cancel/reschedule because it's not in ready to play status.");
		}

		if (reservation.getSchedule().getStartDateTime().isBefore(LocalDateTime.now())) {
			throw new IllegalArgumentException("Can cancel/reschedule only future dates.");
		}
	}

	public BigDecimal getRefundValue(Reservation reservation) {
		long hours = ChronoUnit.HOURS.between(LocalDateTime.now(), reservation.getSchedule().getStartDateTime());
		BigDecimal cancelRescheduleFee = new BigDecimal(2.5);

		if (hours >= 24) {
			return reservation.getValue();
		}
		if(hours >= 12 && hours <= 23) {
			reservation.setRefundValue(cancelRescheduleFee);
			return reservation.getValue().subtract(reservation.getRefundValue());
		}
		if(hours >= 2 && hours <= 11) {
			reservation.setRefundValue(cancelRescheduleFee.multiply(new BigDecimal(2)));
			return reservation.getValue().subtract(reservation.getRefundValue());

		}if(hours >= 0 && hours <= 2) {
			reservation.setRefundValue(cancelRescheduleFee.multiply(new BigDecimal(3)));
			return reservation.getValue().subtract(reservation.getRefundValue());
		}

		return BigDecimal.ZERO;
	}

	/*
	 * TODO: This method actually not fully working, find a way to fix the issue
	 * when it's throwing the error: "Cannot reschedule to the same slot.
	 */
	public ReservationDTO rescheduleReservation(Long previousReservationId, Long scheduleId) {
		Reservation previousReservation = cancel(previousReservationId);
//		previousReservation.getSchedules().add(scheduleMapper.map(scheduleRepository.findById(scheduleId).map(scheduleMapper::map).orElseThrow(() -> {
//			throw new EntityNotFoundException("Schedule not found.");
//		})));

		if (scheduleId.equals(previousReservation.getSchedule().getId())) {
			throw new IllegalArgumentException("Cannot reschedule to the same slot.");
		}

		previousReservation.setReservationStatus(ReservationStatus.RESCHEDULED);
		reservationRepository.save(previousReservation);

		ReservationDTO newReservation = bookReservation(CreateReservationRequestDTO.builder()
				.guestId(previousReservation.getGuest().getId()).value(previousReservation.getRefundValue().add(previousReservation.getValue()).subtract(new BigDecimal(10))).scheduleId(scheduleId).build());
		newReservation.setPreviousReservation(reservationMapper.map(previousReservation));
		return newReservation;
	}
}
