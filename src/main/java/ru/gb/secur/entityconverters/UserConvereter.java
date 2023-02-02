package ru.gb.secur.entityconverters;

import org.springframework.stereotype.Component;
import ru.gb.secur.DTO.UserDto;
import ru.gb.secur.entities.User;
import javax.persistence.Converter;

@Component
public class UserConvereter {

    public UserDto entityToDtoConverter (User user) {
        return new UserDto(user);
    }

}
