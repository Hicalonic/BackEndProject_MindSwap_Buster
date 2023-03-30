package org.mindswap.security.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutHandler;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(
        prePostEnabled = true,
        securedEnabled = true,
        jsr250Enabled = true)
@RequiredArgsConstructor
public class SecurityConfiguration {

  private final JwtAuthenticationFilter jwtAuthFilter;
  private final AuthenticationProvider authenticationProvider;
  private final LogoutHandler logoutHandler;

  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    http
        .csrf()
        .disable()
        .authorizeHttpRequests()
            .requestMatchers("/swagger-ui/**").permitAll()
            .requestMatchers("/client/**").permitAll()
            .requestMatchers("/v3/**").permitAll()
            .requestMatchers("/imdb/**").permitAll()
            .requestMatchers("/email/**").permitAll()
        .requestMatchers("/api/v1/auth/**")
          .permitAll()
        .anyRequest()
          .authenticated()
        .and()
          .sessionManagement()
          .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        .and()
        .authenticationProvider(authenticationProvider)
        .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
        .logout()
        .logoutUrl("/api/v1/auth/logout")
        .addLogoutHandler(logoutHandler)
        .logoutSuccessHandler((request, response, authentication) -> SecurityContextHolder.clearContext())
    ;

    return http.build();
  }
}

 /*  ---------------- THIRD IMPLEMENTATION ----------------

        /client/**
                  /c+/info - view his own details
                  /c+/update
                  /c+/delete
                  /c+/id - view a specific client details
                  /w+/all - view the list of all clients

         /movies/**
                   /m+/add
                   /m+/update
                   /m+/delete
                   /w+/id
                   /w+/all
                   /c+/available

         /rental/**
                   /w+/add
                   /w+/update
                   /w+/delete
                   /w+/id - Details of a specific rental
                   /w+/all - List of all rentals
                   /c+/all/client/id/ - All rentals of a specific client
                   /c+/id/client/id/ - A specific rental of a specific client

         /invoice/**
                    /c+/id/client/id/**
                                      /details - View details in json or in html page of a specific invoice
                                      /qrcode - Gets a QRCODE
                                      /email - Gets invoice to email
                    /c+/all/client/id/ - List of all invoices of a specific client
                    /w+/add
                    /w+/update
                    /w+/delete
                    /c+/id - Details of a specific invoice
                    /w+/all - list of all rentals

        /worker/**
                  /w+/info - view his own details
                  /w+/update
                  /w+/delete
                  /m+/id - view a specific worker details
                  /m+/all - view the list of all workers


        /manager/**
                  /m+/info - view his own details
                  /m+/update
                  /m+/delete
                  /a+/id - view a specific manager details
                  /a+/all - view the list of all managers

        /admin/**
                  /a/info - view his own details
                  /a/update
                  /a/delete
                  /a/id - view a specific admin details
                  /a/all - view the list of all admins

          /store/**
                   /m+/create
                   /m+/update/
                   /m+/delete/
                   /m+/all
                   /m+/id

          /register/** - client
                      /worker
                      /manager
                      /admin
  */

/*  ---------------- FOURTH IMPLEMENTATION ----------------

/client/**
         /c+/info - (GET/UPDATE/DELETE) - CURL his own details
         /w+/id - (GET/UPDATE/DELETE) - CURL of specific client
         /w+/all - (GET) - view the list of all clients

/movie/**
          /w+/id - (GET) -
          /m+/id - (PUT/UPDATE/DELETE) - CURL his own details
          /c+/all - (GET) - view the list of all clients
          /c+/available

/rental/**
          /w+/""/ - (POST/UPDATE)
          /w+/id/ - (GET
                   /w+/add
                   /w+/update
                   /w+/delete
                   /w+/id - Details of a specific rental
                   /w+/all - List of all rentals
                   /c+/all/client/id/ - All rentals of a specific client
                   /c+/id/client/id/ - A specific rental of a specific client

         /invoice/**
                    /c+/id/client/id/**
                                      /details - View details in json or in html page of a specific invoice
                                      /qrcode - Gets a QRCODE
                                      /email - Gets invoice to email
                    /c+/all/client/id/ - List of all invoices of a specific client
                    /w+/add
                    /w+/update
                    /w+/delete
                    /c+/id - Details of a specific invoice
                    /w+/all - list of all rentals

        /worker/**
                  /w+/info - view his own details
                  /w+/update
                  /w+/delete
                  /m+/id - view a specific worker details
                  /m+/all - view the list of all workers


        /manager/**
                  /m+/info - view his own details
                  /m+/update
                  /m+/delete
                  /a+/id - view a specific manager details
                  /a+/all - view the list of all managers

        /admin/**
                  /a/info - view his own details
                  /a/update
                  /a/delete
                  /a/id - view a specific admin details
                  /a/all - view the list of all admins

          /store/**
                   /m+/create
                   /m+/update/
                   /m+/delete/
                   /m+/all
                   /m+/id

          /register/** - client
                      /worker
                      /manager
                      /admin

  */

