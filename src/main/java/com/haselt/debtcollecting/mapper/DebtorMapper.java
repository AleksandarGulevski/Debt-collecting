package com.haselt.debtcollecting.mapper;

import com.haselt.debtcollecting.dto.DebtorDto;
import com.haselt.debtcollecting.entity.Debtor;

public class DebtorMapper {

    public static Debtor dtoToEntity(final DebtorDto debtorDto){
        Debtor debtor = new Debtor()
                .setFirstName(debtorDto.getFirstName())
                .setLastName(debtorDto.getLastName())
                .setEmail(debtorDto.getEmail())
                .setIban(debtorDto.getIban())
                .setSsn(debtorDto.getSsn());
        return debtor;
    }

    public static DebtorDto entityToDto(final Debtor debtor){
        DebtorDto debtorDto = DebtorDto.builder()
                .firstName(debtor.getFirstName())
                .lastName(debtor.getLastName())
                .email(debtor.getEmail())
                .iban(debtor.getIban())
                .ssn((debtor.getSsn()))
                .id(debtor.getId())
                .build();
        return debtorDto;
    }
}
