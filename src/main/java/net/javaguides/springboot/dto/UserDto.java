package net.javaguides.springboot.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    private Long id;
    @NotEmpty(message = "user first name should not be empty or null") /* user first name should not be empty or null */
    private String firstName;
    @NotEmpty(message="user last name should not be empty or null")/* user last name should not be empty or null */
    private String lastName;
    @NotEmpty(message="user email should not be empty or null") /* user email should not be empty or null */
    @Email(message="email address must be valid")/*email add must be valid*/
    private String email;
}
