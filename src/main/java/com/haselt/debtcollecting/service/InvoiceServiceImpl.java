package com.haselt.debtcollecting.service;

import com.haselt.debtcollecting.dto.InvoiceItemDto;
import com.haselt.debtcollecting.entity.Invoice;
import com.haselt.debtcollecting.entity.InvoiceItem;
import com.haselt.debtcollecting.exception.ResourceNotFoundException;
import com.haselt.debtcollecting.mapper.InvoiceItemMapper;
import com.haselt.debtcollecting.repository.InvoiceItemRepository;
import com.haselt.debtcollecting.repository.InvoiceRepository;
import com.haselt.debtcollecting.util.ErrorCode;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class InvoiceServiceImpl implements InvoiceService {

    private InvoiceItemRepository invoiceItemRepository;
    private InvoiceRepository invoiceRepository;

    @Override
    public InvoiceItem create(InvoiceItemDto invoiceItemDto, long id) {
        InvoiceItem invoiceItem = InvoiceItemMapper.dtoToEntity(invoiceItemDto);
        Invoice invoice = getInvoiceById(id);
        invoice.addInvoiceItem(invoiceItem);
        return invoiceItemRepository.save(invoiceItem);
    }

    @Override
    public List<InvoiceItem> getAllPerInvoice(long invoiceId) {
        Invoice invoice = getInvoiceById(invoiceId);
        return invoiceItemRepository.findByInvoice(invoice);
    }

    @Override
    public InvoiceItem getInvoiceItemDetailsById(long id) {
        InvoiceItem invoiceItem = invoiceItemRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        ErrorCode.INVOICE_ITEM_NOT_FOUND, "Invoice item with id " + id + " not found!"));
        return invoiceItem;
    }

    public Invoice getInvoiceById(long id) {
        return invoiceRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        ErrorCode.INVOICE_NOT_FOUND, "Invoice with id " + id + " not found!"));
    }
}
