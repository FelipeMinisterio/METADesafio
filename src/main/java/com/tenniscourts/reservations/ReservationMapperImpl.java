package com.tenniscourts.reservations;

import org.springframework.stereotype.Component;

@Component
public class ReservationMapperImpl implements ReservationMapper{
	@Override
	public Reservation map(CreateReservationRequestDTO source) {
		Reservation reservation = new Reservation();
		reservation.getGuest().setId(source.getGuestId());
		reservation.getSchedule().setId(source.getScheduleId());
		reservation.setValue(source.getValue());
		reservation.setReservationStatus(ReservationStatus.READY_TO_PLAY);
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
		reservation.getGuest().setId(source.getGuestId());
		reservation.setValue(source.getValue());
		reservation.getSchedule().setId(source.getScheduledId());
		reservation.setReservationStatus(ReservationStatus.READY_TO_PLAY);
		
		return reservation;
	}

	@Override
	public Reservation map(CreateReservationsRequestDTOs source) {
		return null;
	}

}
