package com.haselt.debtcollecting.api;

import com.haselt.debtcollecting.dto.InvoiceDto;
import com.haselt.debtcollecting.mapper.InvoiceMapper;
import com.haselt.debtcollecting.service.InvoiceService;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Set;
import java.util.stream.Collectors;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1")
public class InvoiceController {

    private final InvoiceService invoiceService;

    @GetMapping(value = "/invoices", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Set<InvoiceDto> getAll(){
        return invoiceService.getAll().stream()
                .map(InvoiceMapper::entityToDto)
                .collect(Collectors.toSet());
    }

    @PostMapping(value = "/invoices", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes =MediaType.APPLICATION_JSON_UTF8_VALUE )
    public InvoiceDto create(@RequestBody InvoiceDto invoiceDto){
        return InvoiceMapper.entityToDto(invoiceService.create(invoiceDto));
    }

    @GetMapping(value = "/invoices/{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public InvoiceDto getById(@PathVariable("id") long id){
        return InvoiceMapper.entityToDto(invoiceService.getById(id));
    }
}
