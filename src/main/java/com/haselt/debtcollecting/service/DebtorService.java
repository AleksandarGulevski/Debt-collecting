package com.haselt.debtcollecting.service;

import com.haselt.debtcollecting.dto.InvoiceDto;
import com.haselt.debtcollecting.entity.Invoice;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Set;

@Transactional
@Validated
public interface DebtorService {

    Set<Invoice> getAll();

    Set<Invoice> getAllPerDebtor(final long debtorId);

    Invoice create(@NotNull(message = "Invalid data") final InvoiceDto invoiceDto, final long id);

    Invoice getById(final long id);

    void deleteInvoice(final long id);

    List<Invoice> findTop3ByOrderByAmount();

    List<Invoice> findByAmountOrderByAmountAsc();

    List<Invoice> findByAmountOrderByAmountDesc();

}
