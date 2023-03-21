package org.mindswap.service;

import org.mindswap.dto.WorkerDto;
import org.mindswap.dto.WorkerUpdateDto;
import org.mindswap.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class WorkerServiceImpl implements WorkerService {

    private UserRepository userRepository;
    private UserMapper userMapper;

    @Autowired
    public WorkerServiceImpl(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    @Override
    public WorkerDto createWorker(WorkerDto wortkerDto) {
        return null;
    }

    @Override
    public WorkerDto getWorkerById(Long workerId) {
        return null;
    }

    @Override
    public List<WorkerDto> getAllWorkers() {
        return null;
    }


    @Override
    public WorkerDto updateWorker(Long workerId, WorkerUpdateDto workerUpdateDto) {

        return null;
    }

    @Override
    public void deleteWorker(Long workerID) {

    }


}
