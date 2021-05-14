package com.tenniscourts.reservations;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import javax.validation.constraints.NotNull;


import com.fasterxml.jackson.annotation.JsonFormat;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Data
public class CreateReservationsRequestDTOs {
    
    @NotNull
    private Long guestId;
    
    @JsonFormat(pattern="yyyy-MM-dd'T'HH:mm")
    @NotNull
    private String startDateTime; //O Swagger nao esta enviando com LocalDateTime ao java

    @JsonFormat(pattern="yyyy-MM-dd'T'HH:mm")
    private String endDateTime; //O Swagger nao esta enviando com LocalDateTime ao java

    @NotNull
    private List<Long> tennisCourtId;
    
    @NotNull
    private BigDecimal value;
    
    

}
