package com.haselt.debtcollecting.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@Entity
public class Debtor implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String iban;
    private String ssn;



    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(
            mappedBy = "debtor",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<Invoice> invoices = new ArrayList<>();

    public void addInvoice(Invoice invoice) {
        invoices.add(invoice);
        invoice.setDebtor(this);
    }

    public void removeInvoice(Invoice invoice) {
        invoices.remove(invoice);
        invoice.setDebtor(null);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Debtor debtor = (Debtor) o;
        return id.equals(debtor.id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Debtor{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", iban='" + iban + '\'' +
                '}';
    }
}
