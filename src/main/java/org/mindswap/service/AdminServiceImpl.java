package org.mindswap.service;

import org.mindswap.dto.UserDto;
import org.mindswap.exceptions.WorkerNotFoundException;
import org.mindswap.mapper.StoreMapper;
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
    StoreService storeService;
    StoreMapper storeMapper;

    @Autowired
    public AdminServiceImpl(UserRepository userRepository, UserMapper userMapper, StoreService storeService,StoreMapper storeMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.storeService = storeService;
        this.storeMapper = storeMapper;
    }

    @Override
    public UserDto makeWorker(Long clientId) {
        User worker = userRepository.findById(clientId).orElseThrow(WorkerNotFoundException::new);
        worker.setRole(Role.WORKER);
        userRepository.save(worker);
        return userMapper.fromEntityToDto(worker);
    }

    @Override
    public UserDto makeManager(Long workerId) {
        User manager = userRepository.findById(workerId).orElseThrow(WorkerNotFoundException::new);
        manager.setRole(Role.MANAGER);
        userRepository.save(manager);
        return userMapper.fromEntityToDto(manager);
    }

    @Override
    public UserDto makeAdmin(Long workerId) {
        User user = userRepository.findById(workerId).orElseThrow(WorkerNotFoundException::new);
        user.setRole(Role.ADMIN);
        return userMapper.fromEntityToDto(user);
    }
}