package com.haselt.debtcollecting.service;

import com.haselt.debtcollecting.dto.InvoiceDto;
import com.haselt.debtcollecting.entity.Invoice;
import com.haselt.debtcollecting.exception.ResourceNotFoundException;
import com.haselt.debtcollecting.mapper.InvoiceMapper;
import com.haselt.debtcollecting.repository.InvoiceRepository;
import com.haselt.debtcollecting.util.ErrorCode;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@AllArgsConstructor
@Service
public class InvoiceServiceImpl implements InvoiceService {

    private final InvoiceRepository invoiceRepository;

    @Override
    public Set<Invoice> getAll() {
        Set<Invoice> invoices = new HashSet<>();
        invoiceRepository.findAll().forEach(invoices::add);
        return invoices;
    }

    @Override
    public Invoice create(InvoiceDto invoiceDto) {
        Invoice invoice = InvoiceMapper.dtoToEntity(invoiceDto);
        return invoiceRepository.save(invoice);
    }

    @Override
    public Invoice getById(Long id) {
        return invoiceRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        ErrorCode.INVOICE_NOT_FOUND, "Invoice with id " + id + " not found!"));
    }
}
