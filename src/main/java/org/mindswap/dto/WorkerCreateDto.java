package org.mindswap.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WorkerCreateDto {
    @NotBlank(message = "Must have a  first name")
    private String firstName;

    @NotBlank(message = "Must have a last name")
    private String lastName;

    @NotNull(message = "Must have a store")
    private Long storeId;

    @Size(min = 6, message = "Password must be at least 6 characters long")
    @NotBlank(message = "Must have a password")
    private String password;

    @Size(min = 6, message = "Password must be at least 6 characters long")
    @NotBlank(message = "Must retype your password")
    private String retypedPassword;
}
