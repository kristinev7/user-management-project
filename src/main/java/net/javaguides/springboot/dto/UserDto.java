package net.javaguides.springboot.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

//swagger annotation to provide information in swagger ui
@Schema(
        description = "UserDto Model Information"
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    private Long id;

    @Schema(
            description = "User First Name"
    )
    @NotEmpty(message = "user first name should not be empty or null") /* user first name should not be empty or null */
    private String firstName;

    @Schema(
            description = "User Last Name"
    )
    @NotEmpty(message="user last name should not be empty or null")/* user last name should not be empty or null */
    private String lastName;

    @Schema(
            description = "User Email Address"
    )
    @NotEmpty(message="user email should not be empty or null") /* user email should not be empty or null */
    @Email(message="email address must be valid")/*email add must be valid*/
    private String email;
}
