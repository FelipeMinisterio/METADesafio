package com.tenniscourts.guests;

import org.springframework.stereotype.Component;

@Component
public class GuestMapperImpl implements GuestMapper{

	@Override
	public Guest map(CreateGuestRequestDTO createGuestRequestDTO) {
		Guest guest = new Guest();
		guest.setName(createGuestRequestDTO.getName());
		return guest;
	}

	@Override
	public GuestDTO map(Guest source) {
		GuestDTO guestDTO = new GuestDTO();
		guestDTO.setId(source.getId());
		guestDTO.setName(source.getName());
		return guestDTO;
	}

	@Override
	public Guest map(GuestDTO source) {
		Guest guest = new Guest();
		guest.setId(source.getId());
		guest.setName(source.getName());
		return guest;
	}

}
