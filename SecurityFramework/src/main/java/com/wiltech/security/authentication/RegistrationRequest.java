package com.wiltech.security.authentication;

import java.time.LocalDate;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

/**
 * The type Registration request.
 */
@Value
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class RegistrationRequest {

    @NotNull
    @Size(max = 80, message = "First name cannot have more than {max} characters")
    private String firstName;

    @NotNull
    @Size(max = 80, message = "Last name cannot have more than {max} characters")
    private String lastName;

    @NotNull(message = "Email cannot be null")
    @Email
    //    @Unique
    private String email;

    @NotNull
    @Size(min = 5, max = 20, message = "Password must be between {min} and {max} characters long. Length found : ${validatedValue.length()}")
    private String password;

    private LocalDate dateOfBirth;
}
