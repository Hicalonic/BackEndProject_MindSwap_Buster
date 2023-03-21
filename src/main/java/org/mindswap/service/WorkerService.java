package org.mindswap.service;

import org.mindswap.dto.ClientDto;
import org.mindswap.dto.WorkerUpdateDto;

import java.util.List;

public interface WorkerService {
    ClientDto getWorkerById(Long workerId);
    List<ClientDto> getAllWorkers();
    ClientDto updateWorker(Long workerId, WorkerUpdateDto workerUpdateDto);
}
