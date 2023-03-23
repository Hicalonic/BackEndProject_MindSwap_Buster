package org.mindswap.dto;

import lombok.*;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class InvoiceUpdateDto {
    private Long price;

    private Long rentalId;

    private Long workerId;
}
