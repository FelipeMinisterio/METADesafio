package com.tenniscourts.reservations;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/reservation")
public class ReservationRestController {
	@Autowired
	private ReservationController reservationController;
	
		@GetMapping(value = "/find")
		public ResponseEntity<ReservationDTO> findReservation(Long reservationId){
			return reservationController.findReservation(reservationId);
		}
		@GetMapping(value = "/list")
		public ResponseEntity<List<Reservation>> findAllReservation(){
			return reservationController.findAllReservations();
		}
		@PostMapping(value = "/booking")
		public ResponseEntity<Void>bookReservation(CreateReservationRequestDTO createReservationRequestDTO){			
			return reservationController.bookReservation(createReservationRequestDTO);			
		}
		@PostMapping(value = "/booking-reservations")
		public ResponseEntity<List<ReservationDTO>>bookReservations(CreateReservationsRequestDTOs createReservationsRequestDTOs){
			return reservationController.bookReservations(createReservationsRequestDTOs);		
		}
		 
}
