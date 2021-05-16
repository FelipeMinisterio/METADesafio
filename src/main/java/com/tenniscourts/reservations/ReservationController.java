package com.tenniscourts.reservations;

import com.tenniscourts.config.BaseRestController;
import lombok.AllArgsConstructor;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

@AllArgsConstructor
@Controller
public class ReservationController extends BaseRestController {

    private final ReservationService reservationService;
    
    public ResponseEntity<List<Integer>> findFreeSchedule(String dateSchedule,Long tennisCourtId){
    	return ResponseEntity.ok(reservationService.findFreeSchedule(dateSchedule,tennisCourtId));
    }

    public ResponseEntity<Void> bookReservation(CreateReservationRequestDTO createReservationRequestDTO) {
        return ResponseEntity.created(locationByEntity(reservationService.bookReservation(createReservationRequestDTO).getId())).build();
    }
    public ResponseEntity<List<ReservationDTO>> bookReservations(CreateReservationsRequestDTOs createReservationsRequestDTOs) {
        return ResponseEntity.ok(reservationService.bookReservations(createReservationsRequestDTOs));
    }

    public ResponseEntity<ReservationDTO> findReservation(Long reservationId) {
        return ResponseEntity.ok(reservationService.findReservation(reservationId));
    }
    public ResponseEntity<List<Reservation>> findAllReservations() {
        return ResponseEntity.ok(reservationService.findAllReservations());
    }

    public ResponseEntity<ReservationDTO> cancelReservation(Long reservationId) {
        return ResponseEntity.ok(reservationService.cancelReservation(reservationId));
    }

    public ResponseEntity<ReservationDTO> rescheduleReservation(Long reservationId, Long scheduleId) {
        return ResponseEntity.ok(reservationService.rescheduleReservation(reservationId, scheduleId));
    }

	public ResponseEntity<Reservation> editReservation(Long reservationId, String reservationStatus) {
		return ResponseEntity.ok(reservationService.editReservation(reservationId, reservationStatus));
	}
}
