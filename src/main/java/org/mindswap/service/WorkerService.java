package org.mindswap.service;

import org.mindswap.dto.UserCreateDto;
import org.mindswap.dto.UserDto;
import org.mindswap.dto.UserDtoJsonBody;
import org.mindswap.dto.UserUpdateDto;

import java.util.List;

public interface WorkerService {

    public UserDto createWorker(UserCreateDto userCreateDto);

    UserDtoJsonBody getWorkerById(Long workerId);

    List<UserDtoJsonBody> getAllWorkers();

    UserDto updateWorker(Long workerId, UserUpdateDto userUpdateDto);

    void updateWorkerRole(Long workerId, UserUpdateDto userUpdateDto);

    public void deleteWorker(Long workerID);
}