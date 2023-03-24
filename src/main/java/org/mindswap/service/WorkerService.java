package org.mindswap.service;

import org.mindswap.dto.UserCreateDto;
import org.mindswap.dto.UserDto;
import org.mindswap.dto.UserUpdateDto;
import org.mindswap.dtosUser.UserWorkerCreateDto;
import org.mindswap.dtosUser.UserWorkerDto;
import org.mindswap.dtosUser.UserWorkerUpdateDto;
import org.mindswap.dtosUser.RoleUpdateDto;

import java.util.List;

public interface WorkerService {

    public UserDto createWorker(UserCreateDto userCreateDto);

    UserDto getWorkerById(Long workerId);

    List<UserDto> getAllWorkers();

    UserDto updateWorker(Long workerId, UserUpdateDto userUpdateDto);

    void updateWorkerRole(Long workerId, UserUpdateDto userUpdateDto);

    public void deleteWorker(Long workerID);
}