package org.mindswap.service;

import org.mindswap.dto.ClientDto;
import org.mindswap.dto.StoreDto;
import org.mindswap.dto.WorkerDto;
import org.mindswap.dto.WorkerUpdateDto;

import java.util.List;

public interface WorkerService {



    public WorkerDto createWorker(WorkerDto wortkerDto);
    WorkerDto getWorkerById(Long workerId);
    List<WorkerDto> getAllWorkers();
    WorkerDto updateWorker(Long workerId, WorkerUpdateDto workerUpdateDto);
    public void deleteWorker(Long workerID);



}
