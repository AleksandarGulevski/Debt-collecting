package com.haselt.debtcollecting.service;

import com.haselt.debtcollecting.dto.DebtorDto;
import com.haselt.debtcollecting.dto.UserDto;
import com.haselt.debtcollecting.entity.Debtor;
import com.haselt.debtcollecting.entity.User;
import com.haselt.debtcollecting.exception.DebtorAlreadyExistsException;
import com.haselt.debtcollecting.exception.ResourceNotFoundException;
import com.haselt.debtcollecting.exception.ResourceValidationException;
import com.haselt.debtcollecting.mapper.DebtorMapper;
import com.haselt.debtcollecting.mapper.UserMapper;
import com.haselt.debtcollecting.repository.DebtorRepository;
import com.haselt.debtcollecting.repository.UserRepository;
import com.haselt.debtcollecting.util.ErrorCode;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@AllArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private final DebtorRepository debtorRepository;
    private final UserRepository userRepository;

    @Override
    public User create(UserDto userDto) {
        User user = UserMapper.dtoToEntity(userDto);
        Optional<User> userOptional = userRepository.findByEmail(userDto.getEmail());
        if (userOptional.isPresent()) {
            throw new DebtorAlreadyExistsException(
                    ErrorCode.USER_ALREADY_EXISTS, "User with that email already exists");
        }
        return userRepository.save(user);
    }

    @Override
    public Set<User> getAllUsers() {
        Set<User> users = new HashSet<>();
        userRepository.findAll().forEach(users::add);
        return users;
    }

    @Override
    public Set<Debtor> getAll() {
        Set<Debtor> debtors = new HashSet<>();
        debtorRepository.findAll().forEach(debtors::add);
        return debtors;
    }

    @Override
    public Debtor createDebtor(DebtorDto debtorDto, long id) {
        Debtor debtor = DebtorMapper.dtoToEntity(debtorDto);
        Optional<Debtor> debtorOptional = debtorRepository.findBySsn(debtorDto.getSsn());
        if (debtorOptional.isPresent()) {
            throw new DebtorAlreadyExistsException(
                    ErrorCode.DEBTOR_ALREADY_EXISTS, "Debtor with that Social Security Number already exists");
        }
        User user = getUserById(id);
        user.addDebtor(debtor);
        return debtorRepository.save(debtor);
    }


    @Override
    public Debtor getDebtorById(long id) {
        return debtorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        ErrorCode.DEBTOR_NOT_FOUND, "Debtor with id " + id + " not found!"));
    }

    @Override
    public void deleteDebtor(long id) {
        debtorRepository.findById(id).ifPresent((debtor) -> {
            debtorRepository.delete(debtor);
        });
        Optional<Debtor> debtorOptional = debtorRepository.findById(id);
        if (debtorOptional.isPresent()) {
            throw new ResourceValidationException(
                    ErrorCode.DEBTOR_DELETING_UNSUCCESSFUL, "Debtor deleting was unsuccessful, try again"
            );
        }
    }

    @Override
    public Set<Debtor> getAllDebtors(long userId) {
        User user = getUserById(userId);
        return debtorRepository.findByUser(user);
    }

    public User getUserById(long userId) {
        return userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException(
                ErrorCode.USER_NOT_FOUND, "User with id " + userId + " not found!"));
    }

    @Override
    public List<Debtor> findByFirstNameStartingWith(String prefix) {
        return debtorRepository.findByFirstNameStartingWith(prefix);
    }


    @Override
    public List<Debtor> findByLastNameIs(String name) {
        return debtorRepository.findByLastNameIs(name);
    }

    @Override
    public List<User> findUsersByLastName(String lastName) {
        return userRepository.findByLastNameIs(lastName);
    }
}
