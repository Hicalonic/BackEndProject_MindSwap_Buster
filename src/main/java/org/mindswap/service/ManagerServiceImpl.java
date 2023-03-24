package org.mindswap.service;

import org.mindswap.dto.UserDto;
import org.mindswap.dto.UserUpdateDto;
import org.mindswap.exceptions.WorkerNotFoundException;
import org.mindswap.mapper.UserMapper;
import org.mindswap.model.Role;
import org.mindswap.model.User;
import org.mindswap.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ManagerServiceImpl implements ManagerService {

    private UserRepository userRepository;
    private UserMapper userMapper;
    @Autowired
    public ManagerServiceImpl(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }




    @Override
    public UserDto getInfoById(Long id) {
        User manager = userRepository.findById(id).orElseThrow(WorkerNotFoundException::new);
        return userMapper.fromEntityToDto(manager);
    }

    @Override
    public List<UserDto> getAllManagers() {
        List<User> managers = userRepository.findAll().stream().filter(u -> u.getRole().equals(Role.MANAGER)).toList();
        return managers.stream().map(m-> userMapper.fromEntityToDto(m)).toList();
    }

    @Override
    public UserDto updateManager(Long id, UserUpdateDto userUpdateDto) {
        User manager = userRepository.findById(id).orElseThrow(WorkerNotFoundException::new);
        if(userUpdateDto.getFirstName() != null) {
            manager.setFirstName(userUpdateDto.getFirstName());
        }
        if(userUpdateDto.getLastName() != null) {
            manager.setLastName(userUpdateDto.getLastName());
        }
        if(userUpdateDto.getEmail() != null) {
            manager.setEmail(userUpdateDto.getEmail());
        }
        /*if(userUpdateDto.getRentalList() != null) {
            manager.setRentalList(userUpdateDto.getRentalList());
        }*/
        userRepository.save(manager);
        return userMapper.fromEntityToDto(manager);
    }

/*    @Override
    public void updateManagerRole(Long id, UserUpdateDto userUpdateDto) {
        User manager = userRepository.findById(id).orElseThrow(WorkerNotFoundException::new);
        if(userUpdateDto.getRole() != null) {
            manager.setRole(userUpdateDto.getRole());
        }
        userRepository.save(manager);
    }*/
    @Override
    public void deleteManager(Long id) {
        User manager = userRepository.findById(id).orElseThrow(WorkerNotFoundException::new);
        userRepository.delete(manager);
    }
}
