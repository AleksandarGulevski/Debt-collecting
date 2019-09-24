package com.haselt.debtcollecting.repository;

import com.haselt.debtcollecting.entity.Debtor;
import com.haselt.debtcollecting.entity.User;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;
import java.util.Set;

public interface UserRepository extends PagingAndSortingRepository<User, Long> {
    Optional<User> findByEmail(String email);

 }
