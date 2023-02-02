package ru.gb.secur.DTO;

import lombok.Data;
import ru.gb.secur.entities.Role;
import ru.gb.secur.entities.User;

import java.util.Collection;
import java.util.stream.Collectors;

@Data
public class UserDto {

    private Long id;
    private String username;
    private String roles;

    public UserDto (User user) {
        id = user.getId();
        username = user.getUsername();
        roles = user.getRoles().stream().map(p -> (p.getName())).collect(Collectors.joining(" , \n"));
    }
}
