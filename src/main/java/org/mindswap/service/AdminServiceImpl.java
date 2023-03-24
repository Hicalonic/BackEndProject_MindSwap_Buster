package org.mindswap.service;

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
    public void makeManager(Long workerId) {
        User user = userRepository.findById(workerId).orElseThrow(WorkerNotFoundException::new);
        user.setRole(Role.MANAGER);
    }

    @Override
    public void makeClient(Long workerId) {
        User user = userRepository.findById(workerId).orElseThrow(WorkerNotFoundException::new);
        user.setRole(Role.CLIENT);
    }

    @Override
    public void makeAdmin(Long workerId) {
        User user = userRepository.findById(workerId).orElseThrow(WorkerNotFoundException::new);
        user.setRole(Role.ADMIN);
    }
}
