package org.mindswap.service;



import org.mindswap.dto.UserDto;
import org.mindswap.dto.UserUpdateDto;

import java.util.List;

public interface ManagerService {

    public UserDto getInfoById(Long id);

    public List<UserDto> getAllManagers();

    public UserDto updateManager(Long id, UserUpdateDto userUpdateDto);

    //void updateManagerRole(Long id, UserUpdateDto userUpdateDto);

    public void deleteManager(Long id);


}