package com.haselt.debtcollecting.repository;

import com.haselt.debtcollecting.entity.Invoice;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface InvoiceRepository extends PagingAndSortingRepository<Invoice, Long> {
}
