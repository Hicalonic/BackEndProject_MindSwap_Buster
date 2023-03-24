package org.mindswap.service;

import org.mindswap.dto.UserDto;

public interface AdminService {

    UserDto makeManager(Long workerId);

    UserDto makeClient(Long workerId);

    UserDto makeAdmin(Long workerId);

}
