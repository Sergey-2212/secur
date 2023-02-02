package ru.gb.secur.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.gb.secur.DTO.UserDto;
import ru.gb.secur.entities.Role;
import ru.gb.secur.entities.User;
import ru.gb.secur.entityconverters.UserConvereter;
import ru.gb.secur.repositories.UserRepository;

import javax.transaction.Transactional;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;

    public Optional<User> findByUsername (String name) {
       return userRepository.findByUsername(name);
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(String.format("User '%s' didn't find.", username)));
        return new org.springframework.security.core.userdetails.User(
                user.getUsername(), user.getPassword(),mapRolesToAuthorities(user.getRoles()));
           }
    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles) {
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
           }
    @Transactional
    public List<User> getAllUsersList() {
        List<User> list = userRepository.findAll();
        for (User s: list) {
            int index = 0;

            for (Role r : s.getRoles()) {
                log.debug(" getAllUsersList " + s.getUsername() + "  " + r.getName());
            }

        }
      return  list;

    }

    public User updateUser(User user) {
        return userRepository.save(user);
    }

    public User addNewUser(User user) {
        user.setId(null);
        return userRepository.save(user);
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}
