package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.example.demo.security.CustomUserDetailsService;
import com.example.demo.security.filters.ExampleFilter;
import com.example.demo.security.filters.JwtAuthenticationFilter;

@Configuration
@EnableWebSecurity(debug = false)
@EnableMethodSecurity
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
    public JwtAuthenticationFilter jwtAuthenticationFilter() {
        return new JwtAuthenticationFilter();
    }

    @Bean
    @Order(1)
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .securityMatcher("/mvc/**", "/h2-console/**")
                .csrf(csrf -> csrf.ignoringRequestMatchers("/h2-console/**"))
                .headers(headers -> headers.frameOptions(frame -> frame.sameOrigin()))
                .authorizeHttpRequests(authz -> authz
                        .requestMatchers("/mvc/public/**", "/h2-console/**").permitAll()
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

    @Bean
    public ExampleFilter exampleFilter() {
        return new ExampleFilter();
    }

    @Bean
    @Order(2)
    public SecurityFilterChain apiSecurityFilterChain(HttpSecurity http) throws Exception {
        return http
                .securityMatcher("/example/**")
                .csrf(csrf -> csrf.disable())
                .addFilterBefore(exampleFilter(), UsernamePasswordAuthenticationFilter.class)
                .authorizeHttpRequests(authz -> authz
                        .requestMatchers("/example/public/**").permitAll()
                        .anyRequest().authenticated())
                .build();
    }

    @Bean
    @Order(3)
    public SecurityFilterChain restSecurityFilterChain(HttpSecurity http) throws Exception {
        return http
                .securityMatcher("/rest/**")
                .csrf(csrf -> csrf.disable())
                .addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class)
                .authorizeHttpRequests(authz -> authz
                        .requestMatchers("/rest/public/**").permitAll()
                        .anyRequest().authenticated())
                .sessionManagement(t -> t.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) // No tener sesiones, stateful
                .build();
    }
}
