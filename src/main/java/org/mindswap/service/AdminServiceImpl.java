package org.mindswap.service;

import org.mindswap.dto.*;
import org.mindswap.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;

public class AdminServiceImpl implements AdminService {

    private UserRepository userRepository;
    private UserMapper userMapper;


    @Autowired
    public AdminServiceImpl(UserRepository userRepository, UserMapper userMapper
                            ) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }




    @Override
    public WorkerDto addAdmin(Long workerId) {
        return null;
    }







}
