package com.haselt.debtcollecting.api;

import com.haselt.debtcollecting.dto.InvoiceDto;
import com.haselt.debtcollecting.mapper.InvoiceMapper;
import com.haselt.debtcollecting.service.DebtorService;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Set;
import java.util.stream.Collectors;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/debtors")
public class DebtorController {

    private final DebtorService debtorService;

    @GetMapping(value = "/invoices", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Set<InvoiceDto> getAll() {
        return debtorService.getAll().stream()
                .map(InvoiceMapper::entityToDto)
                .collect(Collectors.toSet());
    }

    @PostMapping(value = "/{id}/invoices", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public InvoiceDto create(@RequestBody InvoiceDto invoiceDto, @PathVariable("id") long id) {
        return InvoiceMapper.entityToDto(debtorService.create(invoiceDto, id));
    }

    @GetMapping(value = "/{id}/invoices", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Set<InvoiceDto> getAllPerDebtor(@PathVariable("id") long id) {
        return debtorService.getAllPerDebtor(id).stream()
                .map(item -> InvoiceMapper.entityToDto(item))
                .collect(Collectors.toSet());
    }
    

    @GetMapping(value = "/invoices/{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public InvoiceDto getById(@PathVariable("id") long id) {
        return InvoiceMapper.entityToDto(debtorService.getById(id));
    }
}
