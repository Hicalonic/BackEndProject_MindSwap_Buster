package org.mindswap.service;

import org.mindswap.dtosUser.RoleUpdateDto;
import org.mindswap.dtosUser.UserManagerDto;
import org.mindswap.dtosUser.UserManagerUpdateDto;
import org.mindswap.exceptions.WorkerNotFoundException;
import org.mindswap.mappersUser.UserManagerMapper;
import org.mindswap.model.Role;
import org.mindswap.model.User;
import org.mindswap.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ManagerServiceImpl implements ManagerService {

    private UserRepository userRepository;
    private UserManagerMapper userManagerMapper;
    @Autowired
    public ManagerServiceImpl(UserRepository userRepository, UserManagerMapper userManagerMapper) {
        this.userRepository = userRepository;
        this.userManagerMapper = userManagerMapper;
    }


    @Override
    public UserManagerDto getInfo() {

        return null;
    }

    @Override
    public UserManagerDto getInfoById(Long id) {
        User manager = userRepository.findById(id).orElseThrow(WorkerNotFoundException::new);
        return userManagerMapper.fromEntityToDto(manager);
    }

    @Override
    public List<UserManagerDto> getAllManagers() {
        List<User> managers = userRepository.findAll().stream().filter(u -> u.getRole().equals(Role.MANAGER)).toList();
        return managers.stream().map(m-> userManagerMapper.fromEntityToDto(m)).toList();
    }

    @Override
    public UserManagerDto updateManager(Long id, UserManagerUpdateDto userManagerUpdateDto) {
        User manager = userRepository.findById(id).orElseThrow(WorkerNotFoundException::new);
        if(userManagerUpdateDto.getFirstName() != null) {
            manager.setFirstName(userManagerUpdateDto.getFirstName());
        }
        if(userManagerUpdateDto.getLastName() != null) {
            manager.setLastName(userManagerUpdateDto.getLastName());
        }
        if(userManagerUpdateDto.getEmail() != null) {
            manager.setEmail(userManagerUpdateDto.getEmail());
        }
        if(userManagerUpdateDto.getRentalList() != null) {
            manager.setRentalList(userManagerUpdateDto.getRentalList());
        }
        userRepository.save(manager);
        return userManagerMapper.fromEntityToDto(manager);
    }

    @Override
    public void updateManagerRole(Long id, RoleUpdateDto roleUpdateDto) {
        User manager = userRepository.findById(id).orElseThrow(WorkerNotFoundException::new);
        if(roleUpdateDto.getRole() != null) {
            manager.setRole(roleUpdateDto.getRole());
        }
        userRepository.save(manager);
    }
    @Override
    public void deleteManager(Long id) {
        User manager = userRepository.findById(id).orElseThrow(WorkerNotFoundException::new);
        userRepository.delete(manager);
    }
}
