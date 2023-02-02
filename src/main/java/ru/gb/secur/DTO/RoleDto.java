package ru.gb.secur.DTO;

import lombok.Data;
import ru.gb.secur.entities.Role;

@Data
public class RoleDto {

    private Long id;
    private String name;

    public RoleDto(Role role) {
        id = role.getId();
        name = role.getName();
    }

}
