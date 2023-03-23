package org.mindswap.service;

import org.mindswap.dtosUser.UserWorkerCreateDto;
import org.mindswap.dtosUser.UserWorkerDto;
import org.mindswap.dtosUser.UserWorkerUpdateDto;
import org.mindswap.dtosUser.RoleUpdateDto;

import java.util.List;

public interface WorkerService {

    public UserWorkerDto createWorker(UserWorkerCreateDto userWorkercreateDto);
    UserWorkerDto getWorkerById(Long workerId);
    List<UserWorkerDto> getAllWorkers();
    UserWorkerDto updateWorker(Long workerId, UserWorkerUpdateDto userWorkerUpdateDto);
    void updateWorkerRole(Long workerId, RoleUpdateDto roleUpdateDto);
    public void deleteWorker(Long workerID);



}
