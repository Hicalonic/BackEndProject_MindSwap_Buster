package org.mindswap.service;

import org.mindswap.dto.*;

import java.util.List;

public interface WorkerService {



    public WorkerDto createWorker(WorkerCreateDto wortkerCreateDto);
    WorkerDto getWorkerById(Long workerId);
    List<WorkerDto> getAllWorkers();
    WorkerDto updateWorker(Long workerId, WorkerUpdateDto workerUpdateDto);
    public void deleteWorker(Long workerID);



}
