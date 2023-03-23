package org.mindswap.dtosUser;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;
import org.mindswap.model.Role;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class RoleUpdateDto {

    @Enumerated(EnumType.STRING)
    private Role role;
}
