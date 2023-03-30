package org.mindswap.service;

import org.mindswap.dto.UserDto;
import org.mindswap.dto.UserDtoJsonBody;

public interface AdminService {

    UserDtoJsonBody makeManager(Long workerId);

    UserDtoJsonBody makeWorker(Long clientId);

    UserDto makeAdmin(Long workerId);

}
