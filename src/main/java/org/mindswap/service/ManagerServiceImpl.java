package org.mindswap.service;

import jakarta.transaction.Transactional;
import org.mindswap.dto.UserDto;
import org.mindswap.dto.UserDtoJsonBody;
import org.mindswap.dto.UserUpdateDto;
import org.mindswap.exceptions.WorkerNotFoundException;
import org.mindswap.mapper.UserMapper;
import org.mindswap.model.Role;
import org.mindswap.model.User;
import org.mindswap.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
    @Transactional
    public UserDtoJsonBody getInfoById(Long id) {
        User user = userRepository.findById(id).orElseThrow(WorkerNotFoundException::new);
        UserDtoJsonBody userInfo = UserDtoJsonBody.builder()
                .firstname(user.getFirstName())
                .lastname(user.getLastName())
                .email(user.getEmail())
                .role(user.getRole()).
                build();
        return userInfo;
    }

    @Override
    @Transactional
    public List<UserDtoJsonBody> getAllManagers() {
        List<User> managersList = userRepository.findAll().stream().filter(user -> user.getRole().equals(Role.MANAGER)).toList();
        List<UserDtoJsonBody> managerListDto = new ArrayList<>();

        for (User user : managersList) {
            managerListDto.add(UserDtoJsonBody.builder()
                    .firstname(user.getFirstName())
                    .lastname(user.getLastName())
                    .email(user.getEmail())
                    .role(user.getRole())
                    .build());
        }
        return managerListDto;
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
