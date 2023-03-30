package org.mindswap.service;

import jakarta.transaction.Transactional;
import org.mindswap.dto.*;
import org.mindswap.exceptions.ClientNotFoundException;
import org.mindswap.mapper.UserMapper;
import org.mindswap.model.Rental;
import org.mindswap.model.User;
import org.mindswap.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientServiceImpl implements ClientService {

    private UserRepository userRepository;
    private UserMapper userMapper;

    @Autowired
    public ClientServiceImpl(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }


    @Override
    public UserDto createClient(UserCreateDto userCreateDto) {
        User user = userMapper.fromCreateDtoToEntity(userCreateDto);
        userRepository.save(user);
        return userMapper.fromEntityToDto(user);
    }

    @Override
    public List<UserDto> createClients(List<UserCreateDto> clientCreateDtoList) {
        List<User> clientList = clientCreateDtoList.stream().map(c->userMapper.fromCreateDtoToEntity(c)).toList();
        clientList.forEach(c -> userRepository.save(c));
        return clientList.stream().map(c->userMapper.fromEntityToDto(c)).toList();

    }

    @Override
    public UserDto getClientById(Long clientId) {
       User user = userRepository.findById(clientId).orElseThrow(ClientNotFoundException::new);
        return userMapper.fromEntityToDto(user);
    }

    @Override
    public List<UserDto> getAllClients() {
        return userRepository.findAll().stream().map(c->userMapper.fromEntityToDto(c)).toList();
    }


    @Override
    public UserDto updateClient(Long clientId, UserUpdateDto userUpdateDto) {
        User user = userRepository.findById(clientId).orElseThrow(ClientNotFoundException::new);

       if(userUpdateDto.getFirstName() != null) {
           user.setFirstName(userUpdateDto.getFirstName());
       }
       if(userUpdateDto.getLastName() != null) {
           user.setLastName(userUpdateDto.getLastName());
       }
       if(userUpdateDto.getEmail() != null) {
           user.setEmail(userUpdateDto.getEmail());
       }

       userRepository.save(user);
       return userMapper.fromEntityToDto(user);
    }

    @Override
    public UserDto updatePassword(Long clientId, UpdatePasswordDto updatePasswordDto) {
        User user = userRepository.findById(clientId).orElseThrow(ClientNotFoundException::new);
        if(updatePasswordDto.getPassword().equals(user.getPassword())) {
            user.setPassword(updatePasswordDto.getNewPassword());
        }
        userRepository.save(user);
        return userMapper.fromEntityToDto(user);
    }

    @Override
    public void deleteClient(Long clientId) {
        User user = userRepository.findById(clientId).orElseThrow(ClientNotFoundException::new);
        userRepository.delete(user);
    }

    @Override
    @Transactional
    public void saveRental(Rental rental, User user){
        user.getRentalList().add(rental);
        userRepository.save(user);
    }
}
