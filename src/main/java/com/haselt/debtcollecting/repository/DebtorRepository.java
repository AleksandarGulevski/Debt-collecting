package com.haselt.debtcollecting.repository;

import com.haselt.debtcollecting.entity.Debtor;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;

public interface DebtorRepository extends PagingAndSortingRepository<Debtor, Long> {

    Optional<Debtor> findByFirstNameAndLastName(String firstName, String lastName);
}
