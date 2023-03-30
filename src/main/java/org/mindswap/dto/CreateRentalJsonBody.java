package org.mindswap.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateRentalJsonBody {

    @NotBlank(message = "Needs a costumer id ")
    private Long clientId;
    @NotBlank(message = "Needs a store id ")
    private Long storeId;
    @NotBlank(message = "Needs movies")
    private List<Long> movieIdList;
    @NotBlank(message = "Needs a start date")
    private LocalDate startDate;
    @NotBlank(message = "Needs a delivery date")
    private LocalDate endDate;




}
