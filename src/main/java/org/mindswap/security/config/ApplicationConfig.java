package org.mindswap.security.config;

import lombok.RequiredArgsConstructor;
import org.mindswap.exceptions.ClientNotFoundException;
import org.mindswap.model.Client;
import org.mindswap.model.Worker;
import org.mindswap.repository.ClientRepository;
import org.mindswap.repository.WorkerRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

@Configuration
@RequiredArgsConstructor
public class ApplicationConfig {

    private final ClientRepository clientRepository;
    private final WorkerRepository workerRepository;


  @Bean
  public UserDetailsService userDetailsService() {
    return username -> clientRepository.findByEmail(username)
        .orElseThrow(ClientNotFoundException::new);
  }


   /* @Bean
    public UserDetailsService userDetailsService() {
        return email -> {
            Optional<Client> client = clientRepository.findByEmail(email);
            if (client.isPresent()) {
                return client.get();
            } else {
                Optional<Worker> worker = workerRepository.findByEmail(email);
                if (worker.isPresent()) {
                    return worker.get();
                } else {
                    throw new UsernameNotFoundException("User not found with email: " + email);
                }
            }
        };
    }*/

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
