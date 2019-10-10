package com.haselt.debtcollecting.repository;

import com.haselt.debtcollecting.entity.Invoice;
import com.haselt.debtcollecting.entity.InvoiceItem;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface InvoiceItemRepository extends PagingAndSortingRepository<InvoiceItem, Long> {

    List<InvoiceItem> findByInvoice(Invoice invoice);
}
