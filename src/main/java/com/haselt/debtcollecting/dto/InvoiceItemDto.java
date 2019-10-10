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
public class InvoiceItemDto implements Serializable {

    private Long id;
    @NotBlank(message = "Item name missing")
    @Length(message = "Maximum length 100 characters", max = 100)
    private String itemName;
    @NotBlank(message = "Item type missing")
    @Length(message = "Maximum length 100 characters", max = 100)
    private String itemType;
    @NotNull(message = "You must enter an price without VAT for the invoice item")
    private BigDecimal itemPriceWithoutVat;
    @Length(message = "Maximum length 400 characters", max = 400)
    private String itemDescription;
    @NotNull(message = "Invoice item date is missing")
    private LocalDate itemDate;
    @NotNull(message = "You must enter an the percentage of VAT")
    private BigDecimal itemVatPercentage;

    private BigDecimal priceWithVat;

    private BigDecimal vatFromPrice;
}
