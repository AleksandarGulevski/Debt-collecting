package com.haselt.debtcollecting.api;

import com.haselt.debtcollecting.dto.DebtorDto;
import com.haselt.debtcollecting.dto.UserDto;
import com.haselt.debtcollecting.mapper.DebtorMapper;
import com.haselt.debtcollecting.mapper.UserMapper;
import com.haselt.debtcollecting.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/users")
public class UserController {

    private final UserService userService;


    @PostMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public UserDto create(@RequestBody UserDto userDto) {
        return UserMapper.entityToDto(userService.create(userDto));
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Set<UserDto> getAllUsers() {
        return userService.getAllUsers().stream()
                .map(item -> UserMapper.entityToDto(item))
                .collect(Collectors.toSet());
    }

    @GetMapping(value = "/{id}/debtors", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Set<DebtorDto> getAllDebtors(@PathVariable("id") long id) {
        return userService.getAllDebtors(id).stream()
                .map(item -> DebtorMapper.entityToDto(item))
                .collect(Collectors.toSet());
    }

    @PostMapping(value = "/{id}/debtors", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public DebtorDto createDebtor(@RequestBody DebtorDto debtorDto, @PathVariable("id") long id) {
        return DebtorMapper.entityToDto(userService.createDebtor(debtorDto, id));
    }

    @GetMapping(value = "/debtors/{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public DebtorDto getDebtorById(@PathVariable("id") long id) {
        return DebtorMapper.entityToDto(userService.getDebtorById(id));
    }

    @GetMapping(value = "/debtors/sort/firstname/{firstname}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public List<DebtorDto> findByNameStartingWith(@PathVariable("firstname") String prefix) {
        return userService.findByFirstNameStartingWith(prefix).stream()
                .map(item -> DebtorMapper.entityToDto(item))
                .collect(Collectors.toList());
    }

    @GetMapping(value = "/debtors/sort/lastname/{lastname}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public List<DebtorDto> findByLastNameIs(@PathVariable("lastname") String lastName) {
        return userService.findByLastNameIs(lastName).stream()
                .map(item -> DebtorMapper.entityToDto(item))
                .collect(Collectors.toList());
    }

    @GetMapping(value = "/{lastname}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public List<UserDto> findByUserLastNameIs(@PathVariable("lastname") String lastName) {
        return userService.findUsersByLastName(lastName).stream()
                .map(item -> UserMapper.entityToDto(item))
                .collect(Collectors.toList());
    }
}
