package com.haselt.debtcollecting.service;

import com.haselt.debtcollecting.dto.DebtorDto;
import com.haselt.debtcollecting.dto.UserDto;
import com.haselt.debtcollecting.entity.Debtor;
import com.haselt.debtcollecting.entity.User;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Set;

@Transactional
@Validated
public interface UserService {

    User create(@NotNull(message = "Invalid data") final UserDto userDto);

    Set<User> getAllUsers();

    Set<Debtor> getAll();

    Debtor createDebtor(@NotNull(message = "Invalid data") final DebtorDto debtorDto, final long id);

    Debtor getDebtorById(final long id);

    void deleteDebtor(final long id);

    Set<Debtor> getAllDebtors(final long userId);

    List<Debtor> findByFirstNameStartingWith(String prefix);

    List<Debtor> findByLastNameIs(String name);

    List<User> findUsersByLastName(String name);
}
