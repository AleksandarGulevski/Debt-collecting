package com.haselt.debtcollecting.service;

import com.haselt.debtcollecting.dto.DebtorDto;
import com.haselt.debtcollecting.entity.Debtor;
import com.haselt.debtcollecting.exception.DebtorAlreadyExistisException;
import com.haselt.debtcollecting.exception.ResourceNotFoundException;
import com.haselt.debtcollecting.mapper.DebtorMapper;
import com.haselt.debtcollecting.repository.DebtorRepository;
import com.haselt.debtcollecting.util.ErrorCode;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@AllArgsConstructor
@Service
public class DebtorServiceImpl implements DebtorService {

    private final DebtorRepository debtorRepository;

    @Override
    public Set<Debtor> getAll() {
        Set<Debtor> debtors = new HashSet<>();
        debtorRepository.findAll().forEach(debtors::add);
        return debtors;
    }

    @Override
    public Debtor create(DebtorDto debtorDto) {
        Debtor debtor = DebtorMapper.dtoToEntity(debtorDto);
        Optional<Debtor> debtorOptional = debtorRepository.findByFirstNameAndLastName(debtorDto.getFirstName(), debtorDto.getLastName());
        if (debtorOptional.isPresent()){
            throw new DebtorAlreadyExistisException(
            ErrorCode.DEBTOR_AREADY_EXISTS, "Debtor with that first and last name already exists");
        }
        return debtorRepository.save(debtor);
    }

    @Override
    public Debtor getById(Long id) {
        return debtorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        ErrorCode.DEBTOR_NOT_FOUND, "Debtor with id " + id + " not found!"));
    }
}
