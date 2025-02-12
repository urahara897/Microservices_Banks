package com.eazybytes.accounts.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Schema(
    name = "Customer",
    description = "Customer and Account details"
)
public class CustomerDto {
    @Schema(
            description = "Name of the Customer", example = "Kakarot"
    )
    @NotEmpty(message = "Name should not be empty")
    @Size(min = 4, max= 30, message = "Name should be between 4 and 30 characters")
    private String name;
    @Schema(
            description = "Email Address of the Customer", example = "kakarot@gmail.com"
    )
    @NotEmpty(message = "Email should not be empty")
    @Email(message = "Invalid email address")
    private String email;
    @Schema(
            description = "Mobile Number of the Customer", example = "9999999999"
    )
    @Pattern(regexp="(^$|[0-9]{10})",message = "Mobile number must be 10 digits")
    private String mobileNumber;

    @Schema(
            description = "Account Details of the Customer"
    )
    private AccountsDto accountsDto;
}
