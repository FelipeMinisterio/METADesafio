package com.tenniscourts.guests;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/guest")
public class GuestRestController {
	
	@Autowired
	private GuestController guestController;
	
	@PostMapping(value="/adding")
	public ResponseEntity<Void> addGuest(CreateGuestRequestDTO createGuestRequestDTO){
		return guestController.addGuest(createGuestRequestDTO);
	}
	
	@GetMapping(value="/find")
	public ResponseEntity<GuestDTO> findGuest(Long guestId){
		return guestController.findGuest(guestId);
	}
	@PostMapping(value="/edit")
	public ResponseEntity<GuestDTO> editGuest(GuestDTO guestDTO){
		return guestController.editGuest(guestDTO);
	}
	@DeleteMapping
	public ResponseEntity<Void> deleteGuest(Long guestId){
		return guestController.deleteGuest(guestId);
	}
	@GetMapping(value="/findAll")
	public ResponseEntity<List<Guest>> findAllGuests(){
		return guestController.fingAllGuests();
	}
	@GetMapping(value="findGuest")
	public ResponseEntity<List<Guest>> findByNameGuest(String name){
		return guestController.findByNameGuest(name);
	}

}
