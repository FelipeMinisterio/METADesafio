package com.tenniscourts.guests;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tenniscourts.exceptions.EntityNotFoundException;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class GuestService {
	
	@Autowired
	private final GuestRepository guestRepository;
	
	@Autowired
	private final GuestMapperImpl guestMapper;
	
	public GuestDTO findGuest(Long guestId) {
		return guestRepository.findById(guestId).map(guestMapper::map).orElseThrow(() -> {
			throw new EntityNotFoundException("Guest not found.");
		});
	}
	public GuestDTO addGuest(CreateGuestRequestDTO createGuestRequestDTO) {
		return guestMapper.map(guestRepository.saveAndFlush(guestMapper.map(createGuestRequestDTO)));
	}
	public GuestDTO editGuest(GuestDTO guestDTO){
		GuestDTO guest = guestRepository.findById(guestDTO.getId()).map(guestMapper::map).orElseThrow(() -> {
			throw new EntityNotFoundException("Guest not found.");	
			});
		guest.setName(guestDTO.getName());
		return guestMapper.map(guestRepository.saveAndFlush(guestMapper.map(guestDTO)));
	}
	public Void deleteGuest(Long guestId) {
		guestRepository.deleteById(guestId);
		return null;
		 
	}
	public List<Guest> findAllGuests() {
		return guestRepository.findAll();
	}
	public List<Guest> findByNameGuest(String name) {
		return guestRepository.findAllByNameContainsIgnoreCase(name);
	}
}
