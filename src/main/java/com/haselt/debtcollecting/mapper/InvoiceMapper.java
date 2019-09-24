package com.haselt.debtcollecting.mapper;

import com.haselt.debtcollecting.dto.InvoiceDto;
import com.haselt.debtcollecting.entity.Invoice;

public class InvoiceMapper {

    public static Invoice dtoToEntity(final InvoiceDto invoiceDto){
        Invoice invoice = new Invoice()
                .setStatus(invoiceDto.getStatus())
                .setAmount(invoiceDto.getAmount())
                .setDueDate(invoiceDto.getDueDate());
        return invoice;
    }

    public static InvoiceDto entityToDto(final Invoice invoice){
        InvoiceDto invoiceDto = InvoiceDto.builder()
                .status(invoice.getStatus())
                .amount(invoice.getAmount())
                .dueDate(invoice.getDueDate())
                .id(invoice.getId())
                .build();
        return invoiceDto;
    }
}
