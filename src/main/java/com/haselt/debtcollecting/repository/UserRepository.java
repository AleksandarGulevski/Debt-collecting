package com.haselt.debtcollecting.repository;

import com.haselt.debtcollecting.entity.User;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface UserRepository extends PagingAndSortingRepository<User, Long> {
}
