package org.mindswap.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.mindswap.model.Role;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDtoJsonBody {
    private String firstname;
    private String lastname;
    private String email;
    private Role role;
}