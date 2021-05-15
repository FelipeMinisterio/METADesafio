package com.tenniscourts.guests;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import com.tenniscourts.config.BaseRestController;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Controller
public class GuestController extends BaseRestController{

	private final GuestService guestService;
	
	public ResponseEntity<GuestDTO> findGuest(Long guestId){
		return ResponseEntity.ok(guestService.findGuest(guestId));
	}
	
	public ResponseEntity<Void> addGuest(CreateGuestRequestDTO createGuestRequestDTO){
		return ResponseEntity.created(locationByEntity(guestService.addGuest(createGuestRequestDTO).getId())).build();
	}
	
	public ResponseEntity<GuestDTO> editGuest(GuestDTO guestDTO){
		return ResponseEntity.ok(guestService.editGuest(guestDTO));
	}

	public ResponseEntity<Void> deleteGuest(Long guestId) {
		return ResponseEntity.ok(guestService.deleteGuest(guestId));
	}

	public ResponseEntity<List<Guest>> fingAllGuests() {
		return ResponseEntity.ok(guestService.findAllGuests());
	}

	public ResponseEntity<List<Guest>> findByNameGuest(String name) {
		return ResponseEntity.ok(guestService.findByNameGuest(name));
	}
	
}
