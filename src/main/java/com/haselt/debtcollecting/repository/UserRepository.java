package com.haselt.debtcollecting.repository;

import com.haselt.debtcollecting.entity.User;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends PagingAndSortingRepository<User, Long> {
    Optional<User> findByEmail(String email);

    List<User> findByLastNameIs(String lastName);

 }
