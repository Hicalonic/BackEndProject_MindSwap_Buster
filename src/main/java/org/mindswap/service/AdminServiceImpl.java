package org.mindswap.service;

import org.mindswap.dto.UserDto;
import org.mindswap.exceptions.WorkerNotFoundException;
import org.mindswap.mapper.UserMapper;
import org.mindswap.model.Role;
import org.mindswap.model.User;
import org.mindswap.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminServiceImpl implements AdminService{

    UserRepository userRepository;
    UserMapper userMapper;

    @Autowired
    public AdminServiceImpl(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }
    @Override
    public UserDto makeManager(Long workerId) {
        User user = userRepository.findById(workerId).orElseThrow(WorkerNotFoundException::new);
        user.setRole(Role.MANAGER);
        return userMapper.fromEntityToDto(user);
    }

    @Override
    public UserDto makeClient(Long workerId) {
        User user = userRepository.findById(workerId).orElseThrow(WorkerNotFoundException::new);
        user.setRole(Role.CLIENT);
        return userMapper.fromEntityToDto(user);
    }

    @Override
    public UserDto makeAdmin(Long workerId) {
        User user = userRepository.findById(workerId).orElseThrow(WorkerNotFoundException::new);
        user.setRole(Role.ADMIN);
        return userMapper.fromEntityToDto(user);
    }
}
