package com.haselt.debtcollecting.service;

import com.haselt.debtcollecting.dto.InvoiceItemDto;
import com.haselt.debtcollecting.entity.InvoiceItem;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@Transactional
@Validated
public interface InvoiceService {

    InvoiceItem create(@Valid @NotNull(message = "Invalid data") final InvoiceItemDto invoiceItemDto, final long id);

    List<InvoiceItem> getAllPerInvoice(final long invoiceId);

    InvoiceItem getInvoiceItemDetailsById(final long id);


}
