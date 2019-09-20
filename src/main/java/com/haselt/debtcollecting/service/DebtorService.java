package com.haselt.debtcollecting.service;

import com.haselt.debtcollecting.dto.DebtorDto;
import com.haselt.debtcollecting.entity.Debtor;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import java.util.Set;

@Transactional
@Validated
public interface DebtorService {

    Set<Debtor> getAll();
    Debtor create(final DebtorDto debtorDto);
    Debtor getById(final Long id);
}
