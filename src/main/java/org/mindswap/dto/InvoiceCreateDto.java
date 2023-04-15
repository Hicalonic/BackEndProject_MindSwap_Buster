package org.mindswap.dto;

import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class InvoiceCreateDto {

    @NotNull(message = "Must have a price")
    private double price;

    @NotNull(message = "Must have a rental")
    private Long rentalId;
}
