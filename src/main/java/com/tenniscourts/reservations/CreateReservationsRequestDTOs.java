package com.tenniscourts.reservations;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Data
public class CreateReservationsRequestDTOs {
	
    @NotNull
    private LocalDateTime startDateTime;
    
    @NotNull
    private LocalDateTime endDateTime;
    
    @NotNull
    private Long guestId;

    @NotNull
    private List<Long> tennisCourtId;
    
    @NotNull
    private BigDecimal value;
    
    

}
