package com.tenniscourts.reservations;

import java.math.BigDecimal;

import org.springframework.stereotype.Component;

@Component
public class ReservationMapperImpl implements ReservationMapper{
	@Override
	public Reservation map(CreateReservationRequestDTO source) {
		BigDecimal fee = new BigDecimal(10);
 		Reservation reservation = new Reservation();
		reservation.getGuest().setId(source.getGuestId());
		reservation.getSchedule().setId(source.getScheduleId());
		reservation.setValue(source.getValue().add(fee));
		reservation.setReservationStatus(ReservationStatus.READY_TO_PLAY);
		reservation.setRefundValue(fee);
		reservation.getSchedules().add(reservation.getSchedule());
		return reservation;
	}

	@Override
	public ReservationDTO map(Reservation source) {
		ReservationDTO reservationDTO = new ReservationDTO();
		reservationDTO.setId(source.getId());
		reservationDTO.setGuestId(source.getGuest().getId());
		reservationDTO.setRefundValue(source.getRefundValue());
		reservationDTO.setReservationStatus(source.getReservationStatus().name());
		reservationDTO.setScheduledId(source.getSchedule().getId());
		reservationDTO.setValue(source.getValue());
		return reservationDTO;
	}

	@Override
	public Reservation map(ReservationDTO source) {
		Reservation reservation = new Reservation();
		BigDecimal fee = new BigDecimal(10);
		if(source.getRefundValue() == null) {
		reservation.setValue(source.getValue().add(fee));
		}
		reservation.setId(source.getId());
		reservation.getGuest().setId(source.getGuestId());
		reservation.getSchedule().setId(source.getScheduledId());
		reservation.setReservationStatus(ReservationStatus.READY_TO_PLAY);
		reservation.setValue(source.getValue());
		reservation.setRefundValue(fee);
		reservation.getSchedules().add(reservation.getSchedule());
		
		return reservation;
	}

	@Override
	public Reservation map(CreateReservationsRequestDTOs source) {
		return null;
	}

}
