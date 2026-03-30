package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import com.example.demo.security.CustomUserDetailsService;

@Configuration
@EnableWebSecurity(debug = false)
public class WebSecurityConfig {

    // @Bean
    // public UserDetailsService userDetailsService() {
    // InMemoryUserDetailsManager userDetailsMngr = new
    // InMemoryUserDetailsManager();

    // UserDetails user = User.withUsername("miUsuario") // Cambiar el usuario
    // .password("123456") // Especificar la contraseña
    // .authorities("read") // Las authorities representan los permisos que tiene el
    // usuario
    // .roles("USER") // Los roles son un conjunto de authorities
    // .build();

    // userDetailsMngr.createUser(user); // Agregar el usuario a la lista de
    // usuarios

    // return userDetailsMngr; // Retornar la lista de usuarios
    // }

    @Bean
    public UserDetailsService userDetailsService() {
        return new CustomUserDetailsService();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        // return NoOpPasswordEncoder.getInstance();
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .authorizeHttpRequests(authz -> authz
                        .requestMatchers("/mvc/public/**").permitAll()
                        .requestMatchers("/mvc/auth/login", "/css/**", "/js/**").permitAll()
                        .anyRequest().authenticated())
                .formLogin(form -> form
                        .loginPage("/mvc/auth/login") // URL personalizada para mostrar login
                        .loginProcessingUrl("/mvc/auth/login") // URL que procesa el login
                        .defaultSuccessUrl("/mvc/users", true) // Redirección después del login exitoso
                        .failureUrl("/mvc/auth/login?error") // Redirección en caso de error
                        .usernameParameter("username") // Nombre del campo username
                        .passwordParameter("password") // Nombre del campo password
                        .permitAll())
                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/mvc/auth/login?logout")
                        .permitAll())
                .build();
    }
}
