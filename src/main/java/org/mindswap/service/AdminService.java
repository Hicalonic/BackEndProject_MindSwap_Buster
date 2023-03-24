package org.mindswap.service;

public interface AdminService {

    void makeManager(Long workerId);

    void makeClient(Long workerId);

    void makeAdmin(Long workerId);

}
