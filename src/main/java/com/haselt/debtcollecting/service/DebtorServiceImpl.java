package com.haselt.debtcollecting.service;

import com.haselt.debtcollecting.dto.InvoiceDto;
import com.haselt.debtcollecting.entity.Debtor;
import com.haselt.debtcollecting.entity.Invoice;
import com.haselt.debtcollecting.exception.ResourceNotFoundException;
import com.haselt.debtcollecting.exception.ResourceValidationException;
import com.haselt.debtcollecting.mapper.InvoiceMapper;
import com.haselt.debtcollecting.repository.DebtorRepository;
import com.haselt.debtcollecting.repository.InvoiceRepository;
import com.haselt.debtcollecting.util.ErrorCode;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@AllArgsConstructor
@Service
public class DebtorServiceImpl implements DebtorService {

    private final InvoiceRepository invoiceRepository;
    private final DebtorRepository debtorRepository;

    @Override
    public Set<Invoice> getAll() {
        Set<Invoice> invoices = new HashSet<>();
        invoiceRepository.findAll().forEach(invoices::add);
        return invoices;
    }

    @Override
    public Set<Invoice> getAllPerDebtor(long debtorId) {
        Debtor debtor = getDebtorById(debtorId);
        return invoiceRepository.findByDebtor(debtor);
    }

    @Override
    public void deleteInvoice(long id) {
        invoiceRepository.findById(id).ifPresent((invoice) -> {
            invoiceRepository.delete(invoice);
        });
        Optional<Invoice> invoiceOptional = invoiceRepository.findById(id);
        if (invoiceOptional.isPresent()) {
            throw new ResourceValidationException(
                    ErrorCode.INVOICE_DELETING_UNSUCCESSFUL, "Invoice deleting was unsuccessful, try again"
            );
        }
    }

    @Override
    public Invoice create(InvoiceDto invoiceDto, long id) {
        Invoice invoice = InvoiceMapper.dtoToEntity(invoiceDto);
        Debtor debtor = getDebtorById(id);
        debtor.addInvoice(invoice);
        return invoiceRepository.save(invoice);
    }

    @Override
    public Invoice getById(long id) {
        return invoiceRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        ErrorCode.INVOICE_NOT_FOUND, "Invoice with id " + id + " not found!"));
    }

    @Override
    public List<Invoice> findTop3ByOrderByAmount() {
        return invoiceRepository.findTop3ByOrderByAmount();
    }

    @Override
    public List<Invoice> findByAmountOrderByAmountAsc() {
        return invoiceRepository.findByOrderByAmountAsc();
    }

    @Override
    public List<Invoice> findByAmountOrderByAmountDesc() {
        return invoiceRepository.findByOrderByAmountDesc();
    }


    public Debtor getDebtorById(long id) {
        return debtorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        ErrorCode.DEBTOR_NOT_FOUND, "Debtor with id " + id + " not found!"));
    }
}
