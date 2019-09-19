package com.haselt.debtcollecting.dto;

import lombok.Builder;
import lombok.Value;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Value
@Builder
public class DebtorDto implements Serializable {

    private final Long id;
    @NotBlank(message = "First name missing")
    @Length(message = "Maximum length 100 characters", max = 100)
    private final String firstName;
    @NotBlank(message = "Last name missing")
    @Length(message = "Maximum characters reached", max = 100)
    private final String lastName;
    @NotBlank(message = "Email address missing")
    @Length(message = "Maximum characters reached", max = 100)
    private final String email;
    @NotNull(message = "IBAN missing")
    private final String iban;
}
