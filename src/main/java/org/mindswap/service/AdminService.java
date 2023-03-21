package org.mindswap.service;

import org.mindswap.dto.*;
import org.mindswap.repository.StaffRepository;


public interface AdminService {
    public WorkerDto addAdmin(Long workerId);

}
