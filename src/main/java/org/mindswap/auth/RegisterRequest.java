package org.mindswap.auth;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {

  @NotBlank(message = "Must have a  first name")
  private String firstname;

  @NotBlank(message = "Must have a last name")
  private String lastname;

  @NotBlank(message = "Must have email")
  @Pattern(regexp = "^[A-Za-z0-9+_.-]+@(.+)$", message = "Invalid email")
  private String email;
  @Size(min = 3, max=10, message = "Password must be at least 3 characters long, and 10 max")
  @NotNull(message = "Must retype your password")
  private String password;
}
