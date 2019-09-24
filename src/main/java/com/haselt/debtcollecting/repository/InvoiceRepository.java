package com.haselt.debtcollecting.repository;

import com.haselt.debtcollecting.entity.Debtor;
import com.haselt.debtcollecting.entity.Invoice;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Set;

public interface InvoiceRepository extends PagingAndSortingRepository<Invoice, Long> {
    Set<Invoice> findByDebtor(Debtor debtor);
}
