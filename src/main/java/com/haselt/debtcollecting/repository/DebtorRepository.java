package com.haselt.debtcollecting.repository;

import com.haselt.debtcollecting.entity.Debtor;
import com.haselt.debtcollecting.entity.User;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;
import java.util.Set;

public interface DebtorRepository extends PagingAndSortingRepository<Debtor, Long> {

    Optional<Debtor> findBySsn(String ssn);
    Set<Debtor> findByUser(User user);
}
