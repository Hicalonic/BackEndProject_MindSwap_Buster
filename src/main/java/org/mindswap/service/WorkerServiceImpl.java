package org.mindswap.service;

import jakarta.transaction.Transactional;
import org.mindswap.dto.UserCreateDto;
import org.mindswap.dto.UserDto;
import org.mindswap.dto.UserDtoJsonBody;
import org.mindswap.dto.UserUpdateDto;
import org.mindswap.exceptions.ClientNotFoundException;
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
public class WorkerServiceImpl implements WorkerService {
    private UserMapper userMapper;
    private UserRepository userRepository;

    @Autowired
    public WorkerServiceImpl(UserMapper userMapper, UserRepository userRepository) {
        this.userMapper = userMapper;
        this.userRepository = userRepository;
    }

    @Override
    public UserDto createWorker(UserCreateDto userCreateDto) {
        User user = userMapper.fromCreateDtoToEntity(userCreateDto);
        userRepository.save(user);
        return userMapper.fromEntityToDto(user);
    }

    @Override
    @Transactional
    public UserDtoJsonBody getWorkerById(Long workerId) {
        User user = userRepository.findById(workerId).orElseThrow(WorkerNotFoundException::new);
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
    public List<UserDtoJsonBody> getAllWorkers() {
        List<User> onlyWorkersList = userRepository.findAll().stream().filter(user -> user.getRole().equals(Role.WORKER)).toList();
        List<UserDtoJsonBody> onlyWorkersListDtos = new ArrayList<>();

        for (User user : onlyWorkersList) {
            onlyWorkersListDtos.add(UserDtoJsonBody.builder()
                    .firstname(user.getFirstName())
                    .lastname(user.getLastName())
                    .email(user.getEmail())
                    .role(user.getRole())
                    .build());
        }
    return onlyWorkersListDtos;
    }

    @Override
    public UserDto updateWorker(Long workerId, UserUpdateDto userUpdateDto) {
        User user = userRepository.findById(workerId).orElseThrow(WorkerNotFoundException::new);
        if (userUpdateDto.getFirstName() != null) {
            user.setFirstName(userUpdateDto.getFirstName());
        }
        if (userUpdateDto.getLastName() != null) {
            user.setLastName(userUpdateDto.getLastName());
        }
        if (userUpdateDto.getEmail() != null) {
            user.setEmail(userUpdateDto.getEmail());
        }

        userRepository.save(user);
        return userMapper.fromEntityToDto(user);
    }

    @Override
    public void updateWorkerRole(Long workerId, UserUpdateDto userUpdateDto) {
        User user = userRepository.findById(workerId).orElseThrow(WorkerNotFoundException::new);
        User userToUpdate = userMapper.fromUpdateDtoToEntity(userUpdateDto);
        if (userToUpdate.getRole() != null) {
            user.setRole(userToUpdate.getRole());
        }
        userRepository.save(user);
    }

    @Override
    public void deleteWorker(Long workerID) {
        User user = userRepository.findById(workerID).orElseThrow(WorkerNotFoundException::new);
        userRepository.delete(user);
    }
}
