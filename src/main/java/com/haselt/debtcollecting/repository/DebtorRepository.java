package com.haselt.debtcollecting.repository;

import com.haselt.debtcollecting.entity.Debtor;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface DebtorRepository extends PagingAndSortingRepository<Debtor, Long> {
}
