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
    @NotEmpty /* user first name should not be empty or null */
    private String firstName;
    @NotEmpty/* user last name should not be empty or null */
    private String lastName;
    @NotEmpty /* user email should not be empty or null */
    @Email/*email add must be valid*/
    private String email;
}
