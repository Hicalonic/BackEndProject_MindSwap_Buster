package org.mindswap.service;

import org.mindswap.dtosUser.RoleUpdateDto;
import org.mindswap.dtosUser.UserManagerDto;
import org.mindswap.dtosUser.UserManagerUpdateDto;
import org.mindswap.model.User;

import java.util.List;

public interface ManagerService {

    public UserManagerDto getInfo();
    public UserManagerDto getInfoById(Long id);

    public List<UserManagerDto> getAllManagers();

    public UserManagerDto updateManager(Long id, UserManagerUpdateDto userManagerUpdateDto);

    void updateManagerRole(Long id, RoleUpdateDto roleUpdateDto);

    public void deleteManager(Long id);


}
