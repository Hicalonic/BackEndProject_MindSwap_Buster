package org.mindswap.service;

import org.mindswap.dto.UserDto;

public interface AdminService {

    UserDto makeManager(Long workerId);

    UserDto makeWorker(Long clientId);

    UserDto makeAdmin(Long workerId);

}
