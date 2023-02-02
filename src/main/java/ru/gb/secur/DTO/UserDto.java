package ru.gb.secur.DTO;

import lombok.Data;
import ru.gb.secur.entities.Role;
import ru.gb.secur.entities.User;

import java.util.Collection;

@Data
public class UserDto {

    private Long id;

    private String username;

    private Collection<Role> roles;

    public UserDto (User user) {
        id = user.getId();
        username = user.getUsername();
        roles = user.getRoles();
    }
}
