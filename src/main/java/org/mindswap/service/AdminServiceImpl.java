package org.mindswap.service;

import jakarta.transaction.Transactional;
import org.mindswap.dto.UserDto;
import org.mindswap.dto.UserDtoJsonBody;
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
    @Transactional
    public UserDtoJsonBody makeWorker(Long clientId) {
        User worker = userRepository.findById(clientId).orElseThrow(WorkerNotFoundException::new);
        worker.setRole(Role.WORKER);
        UserDtoJsonBody userInfo = UserDtoJsonBody.builder()
                .firstname(worker.getFirstName())
                .lastname(worker.getLastName())
                .email(worker.getEmail())
                .role(worker.getRole()).
                build();
        userRepository.save(worker);
        return userInfo;
    }

    @Override
    @Transactional
    public UserDtoJsonBody makeManager(Long workerId) {
        User manager = userRepository.findById(workerId).orElseThrow(WorkerNotFoundException::new);
        manager.setRole(Role.WORKER);

        UserDtoJsonBody userInfo = UserDtoJsonBody.builder()
                .firstname(manager.getFirstName())
                .lastname(manager.getLastName())
                .email(manager.getEmail())
                .role(manager.getRole()).
                build();
        userRepository.save(manager);
        return userInfo;
    }

    @Override
    public UserDto makeAdmin(Long workerId) {
        User user = userRepository.findById(workerId).orElseThrow(WorkerNotFoundException::new);
        user.setRole(Role.ADMIN);
        return userMapper.fromEntityToDto(user);
    }
}