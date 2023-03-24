package org.mindswap.auth;

import lombok.RequiredArgsConstructor;
import org.mindswap.model.*;
import org.mindswap.repository.TokenRepository;
import org.mindswap.security.config.JwtService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
  private final ClientRepository repository;
  private final TokenRepository tokenRepository;
  private final PasswordEncoder passwordEncoder;
  private final JwtService jwtService;
  private final AuthenticationManager authenticationManager;

  public AuthenticationResponse register(RegisterRequest request) {
    Client client = Client.builder()
        .firstName(request.getFirstname())
        .lastName(request.getLastname())
        .email(request.getEmail())
        .password(passwordEncoder.encode(request.getPassword()))
        .role(Role.CLIENT)
        .build();
    Client savedClient = repository.save(client);
    String jwtToken = jwtService.generateToken(client);
    saveClientToken(savedClient, jwtToken);
    return AuthenticationResponse.builder()
        .token(jwtToken)
        .build();
  }

  public AuthenticationResponse authenticate(AuthenticationRequest request) {
    authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(
            request.getEmail(),
            request.getPassword()
        )
    );
    Client client = repository.findByEmail(request.getEmail())
        .orElseThrow();
    String jwtToken = jwtService.generateToken(client);
    //revokeAllClientTokens(client);
    saveClientToken(client, jwtToken);
    return AuthenticationResponse.builder()
        .token(jwtToken)
        .build();
  }

  private void saveClientToken(Client client, String jwtToken) {
    Token token = Token.builder()
        .email(client.getEmail())
        .token(jwtToken)
        .tokenType(TokenType.BEARER)
        .expired(false)
        .revoked(false)
        .build();
    tokenRepository.save(token);
  }

  private void saveWorkerToken(Worker worker, String jwtToken){
    Token token = Token.builder()
            .email(worker.getEmail())
            .token(jwtToken)
            .tokenType(TokenType.BEARER)
            .expired(false)
            .revoked(false)
            .build();
  }

//  private void revokeAllClientTokens(Client client) {
//    var validUserTokens = tokenRepository.findAllValidTokenByEmail(client.getEmail());
//    if (validUserTokens.isEmpty())
//      return;
//    validUserTokens.forEach(token -> {
//      token.setExpired(true);
//      token.setRevoked(true);
//    });
//    tokenRepository.saveAll(validUserTokens);
//  }
}
