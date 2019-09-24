package com.haselt.debtcollecting.dto;

import lombok.Builder;
import lombok.Value;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

@Value
@Builder
public class InvoiceDto implements Serializable {

    private final Long id;
    @NotBlank(message = "Status missing")
    @Length(message = "Status can be: open, paid, overdue or canceled", max = 8)
    private final String status;
    @NotNull(message = "You must enter an amount for the invoice")
    private final BigDecimal amount;
    @NotNull(message = "Invoice dueDate is missing")
    private final LocalDate dueDate;

}
