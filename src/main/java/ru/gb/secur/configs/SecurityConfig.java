package ru.gb.secur.configs;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import ru.gb.secur.services.UserService;

@EnableWebSecurity
@RequiredArgsConstructor
@Slf4j
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true, jsr250Enabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserService userService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        log.info("DaoAuthenticationProvider");
        http.authorizeRequests()
                .antMatchers("/index.html").authenticated() //любая авторизованная роль
                .antMatchers("/api/v1/products/**").hasAnyRole("ADMIN", "MANAGER")
                .antMatchers("/api/v1//users/**").hasAnyRole("ADMIN", "SUPERADMIN")
                .antMatchers("/sadmin/**").hasRole("SUPERADMIN") // прописать набор ролей для доступа к эндпойнту
                .antMatchers("/admin.html").hasRole("ADMIN")
                //.anyRequest().permitAll() //остальные страницы доступны любому пользователю
                .and()
                //.httpBasic() - //выдаст пользователю стандартную форму для ввода логина пароля.
                .formLogin() //настраиваемая форма авторизации, можно указать форму для авторихации и настроить эндпойнт после авторизации как успешной так и неуспешно.
                .and()
                .logout() //можно настроить событие после логаута
                .invalidateHttpSession(true)
                .deleteCookies("JSESSIONID");
        //      .and()
        //      .sessionManagement() // конфигурирует параметры соединения.
        //      .maximumSession(1)  // лимит на количество залогиненных сессий одним пользователей.
        //      .maxSessionsPreventsLogin(true)
           }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
        daoAuthenticationProvider.setUserDetailsService(userService);
        return daoAuthenticationProvider;
    }
}
