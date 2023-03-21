package org.mindswap.dto;

import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class InvoiceDto {

    @NotNull(message = "Must have a price")
    private Long price;

    @NotNull(message = "Must have a valid rental")
    private Long rentalId;

    @NotNull(message = "Must have a valid worker")
    private Long workerId;
}
