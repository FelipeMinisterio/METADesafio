package com.tenniscourts.guests;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface GuestMapper {

	Guest map(CreateGuestRequestDTO source);
	
    @InheritInverseConfiguration
	GuestDTO map(Guest source);
    
    Guest map(GuestDTO source);
}
