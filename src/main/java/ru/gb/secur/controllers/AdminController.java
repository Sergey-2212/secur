package ru.gb.secur.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.gb.secur.DTO.UserDto;
import ru.gb.secur.entityconverters.UserConvereter;
import ru.gb.secur.services.UserService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
@Slf4j
public class AdminController {

    private final UserService service;
    private final UserConvereter userConvereter;


    @GetMapping("/")
    public List<UserDto> getAllUsersList() {
        List<UserDto> list = service.getAllUsersList().stream().map(userConvereter::entityToDtoConverter).collect(Collectors.toList());
        log.info("Controller: List = " + list.toString());
        return list;
    }


 }
