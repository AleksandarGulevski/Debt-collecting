package com.haselt.debtcollecting.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;

@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class InvoiceItem implements Serializable {

    private static final BigDecimal ONE_HUNDRED = new BigDecimal(100);

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String itemName;
    private String itemType;
    private BigDecimal itemPriceWithoutVat;
    private String itemDescription;
    private LocalDate itemDate;
    private BigDecimal itemVatPercentage;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "invoice_id")
    private Invoice invoice;

    @Transient
    public BigDecimal getPriceWithVat() {
        return itemPriceWithoutVat
                .add(((itemVatPercentage.divide(ONE_HUNDRED)).multiply(itemPriceWithoutVat).setScale(3, RoundingMode.HALF_EVEN)));
    }

    @Transient
    public BigDecimal getVatFromPrice() {
        return itemVatPercentage.divide(ONE_HUNDRED).multiply(itemPriceWithoutVat).setScale(3, RoundingMode.HALF_EVEN);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        InvoiceItem that = (InvoiceItem) o;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "InvoiceItem{" +
                "id=" + id +
                ", itemName='" + itemName + '\'' +
                ", itemType='" + itemType + '\'' +
                ", itemPriceWithoutVat=" + itemPriceWithoutVat +
                ", itemDescription='" + itemDescription + '\'' +
                ", itemDate=" + itemDate +
                ", itemVatPercentage=" + itemVatPercentage +
                '}';
    }
}
