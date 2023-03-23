package org.mindswap.service;

import org.mindswap.dto.WorkerDto;
import org.mindswap.dto.WorkerUpdateDto;
import org.mindswap.mapper.WorkerMapper;
import org.mindswap.repository.StaffRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class WorkerServiceImpl implements WorkerService {
    private StaffRepository staffRepository;
    private WorkerMapper workerMapper;

    @Autowired
    public WorkerServiceImpl(StaffRepository staffRepository, WorkerMapper workerMapper) {
        this.staffRepository = staffRepository;
        this.workerMapper = workerMapper;
    }




    @Override
    public WorkerDto createWorker(WorkerDto workerDto) {
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
