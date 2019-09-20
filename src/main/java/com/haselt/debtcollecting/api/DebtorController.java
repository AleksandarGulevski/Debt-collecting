package com.haselt.debtcollecting.api;

import com.haselt.debtcollecting.dto.DebtorDto;
import com.haselt.debtcollecting.mapper.DebtorMapper;
import com.haselt.debtcollecting.service.DebtorService;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Set;
import java.util.stream.Collectors;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1")
public class DebtorController {

    private final DebtorService debtorService;

    @GetMapping(value = "/debtors", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Set<DebtorDto> getAll(){
        return debtorService.getAll().stream()
                .map(DebtorMapper::entityToDto)
                .collect(Collectors.toSet());
    }

    @PostMapping(value = "/debtors", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes =MediaType.APPLICATION_JSON_UTF8_VALUE )
    public DebtorDto create(@RequestBody DebtorDto debtorDto){
        return DebtorMapper.entityToDto(debtorService.create(debtorDto));
    }

    @GetMapping(value = "/debtors/{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public DebtorDto getById(@PathVariable("id") long id){
        return DebtorMapper.entityToDto(debtorService.getById(id));
    }
}
