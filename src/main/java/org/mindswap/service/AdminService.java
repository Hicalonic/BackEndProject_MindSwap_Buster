package org.mindswap.service;

import org.mindswap.dto.WorkerDto;

public interface AdminService {
    public WorkerDto makeAdmin(Long userId);
    public void deleteUser(Long userId);
    public void deleteStore(Long storeId);
    public void deleteMovie(Long movieId);
}
