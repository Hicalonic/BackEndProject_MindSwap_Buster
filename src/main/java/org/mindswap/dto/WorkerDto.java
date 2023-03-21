package org.mindswap.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class WorkerDto {

    @NotBlank(message = "Must have a  first name")
    private String firstName;

    @NotBlank(message = "Must have a last name")
    private String lastName;

    @NotNull(message = "Must have a store")
    private Long storeId;
}
