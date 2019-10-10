package com.haselt.debtcollecting.api;

import com.haselt.debtcollecting.dto.InvoiceItemDto;
import com.haselt.debtcollecting.mapper.InvoiceItemMapper;
import com.haselt.debtcollecting.service.InvoiceService;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1")
public class InvoiceController {

private final InvoiceService invoiceService;


    @PostMapping(value = "/invoices/{id}/items", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public InvoiceItemDto create(@RequestBody InvoiceItemDto invoiceItemDto, @PathVariable("id") long id) {
        return InvoiceItemMapper.entityToDto(invoiceService.create(invoiceItemDto, id));
    }

    @GetMapping(value = "/invoices/{id}/items", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public List<InvoiceItemDto> getAllperInvoice(@PathVariable ("id") long id) {
        return invoiceService.getAllPerInvoice(id).stream()
                .map(InvoiceItemMapper::entityToDto)
                .collect(Collectors.toList());
    }

    @GetMapping(value = "invoices/items/{id}/details", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public InvoiceItemDto getInvoiceItemDetailsById(@PathVariable("id") long id){
        return InvoiceItemMapper.entityToDto(invoiceService.getInvoiceItemDetailsById(id));
    }


}
