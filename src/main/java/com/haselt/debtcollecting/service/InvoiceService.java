package com.haselt.debtcollecting.service;

import com.haselt.debtcollecting.dto.InvoiceDto;
import com.haselt.debtcollecting.entity.Invoice;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import java.util.Set;

@Transactional
@Validated
public interface InvoiceService {

    Set<Invoice> getAll();
    Invoice create(final InvoiceDto invoiceDto);
    Invoice getById(final Long id);


}
