package ru.gb.secur.services;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.gb.secur.entities.Role;
import ru.gb.secur.repositories.RoleRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RoleService {

    private final RoleRepository roleRepository;

    public List<String> getAllRoles() {
        return roleRepository.findAll().stream().map(p -> p.getName()).collect(Collectors.toList());
    }

    public Role addNewRole (String name) {
       return roleRepository.save(new Role(name));
    }
}
