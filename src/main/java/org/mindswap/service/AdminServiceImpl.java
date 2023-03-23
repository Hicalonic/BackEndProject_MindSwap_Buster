package org.mindswap.service;

import org.mindswap.dto.*;

import org.mindswap.mapper.WorkerMapper;
import org.mindswap.repository.StaffRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service

public class AdminServiceImpl implements AdminService {

    private StaffRepository staffRepository;

    private WorkerMapper workerMapper;


    @Autowired
    public AdminServiceImpl(StaffRepository staffRepository, WorkerMapper workerMapper) {
        this.staffRepository = staffRepository;
        this.workerMapper = workerMapper;
    }



    @Override
    public WorkerDto addAdmin(Long workerId) {
        return null;
    }







}
