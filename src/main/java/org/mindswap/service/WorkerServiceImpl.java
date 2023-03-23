package org.mindswap.service;

import org.mindswap.dtosUser.UserWorkerCreateDto;
import org.mindswap.dtosUser.UserWorkerDto;
import org.mindswap.dtosUser.UserWorkerUpdateDto;
import org.mindswap.dtosUser.RoleUpdateDto;
import org.mindswap.exceptions.WorkerNotFoundException;
import org.mindswap.mappersUser.UserWorkerMapper;
import org.mindswap.model.User;
import org.mindswap.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WorkerServiceImpl implements WorkerService {
    private UserWorkerMapper userWorkerMapper;
    private UserRepository userRepository;

    @Autowired
    public WorkerServiceImpl(UserWorkerMapper userWorkerMapper,UserRepository userRepository) {
        this.userWorkerMapper = userWorkerMapper;
        this.userRepository = userRepository;
    }

    @Override
    public UserWorkerDto createWorker(UserWorkerCreateDto userWorkercreateDto) {
        User user = userWorkerMapper.fromCreateDtoToEntity(userWorkercreateDto);
        userRepository.save(user);
        return userWorkerMapper.fromEntityToDto(user);
    }

    @Override
    public UserWorkerDto getWorkerById(Long workerId) {
        User user = userRepository.findById(workerId).orElseThrow(WorkerNotFoundException::new);
        return userWorkerMapper.fromEntityToDto(user);
    }

    @Override
    public List<UserWorkerDto> getAllWorkers() {
        return userRepository.findAll().stream().map(w -> userWorkerMapper.fromEntityToDto(w)).toList();

    }


    @Override
    public UserWorkerDto updateWorker(Long workerId, UserWorkerUpdateDto userWorkerUpdateDto) {
        User user = userRepository.findById(workerId).orElseThrow(WorkerNotFoundException::new);
        if (userWorkerUpdateDto.getFirstName() != null) {
            user.setFirstName(userWorkerUpdateDto.getFirstName());
        }
        if (userWorkerUpdateDto.getLastName() != null) {
            user.setLastName(userWorkerUpdateDto.getLastName());
        }
        if (userWorkerUpdateDto.getEmail() != null) {
            user.setEmail(userWorkerUpdateDto.getEmail());
        }

        userRepository.save(user);
        return userWorkerMapper.fromEntityToDto(user);
    }

    @Override
    public void updateWorkerRole(Long workerId, RoleUpdateDto roleUpdateDto) {
        User user = userRepository.findById(workerId).orElseThrow(WorkerNotFoundException::new);
        if(roleUpdateDto.getRole() != null) {
            user.setRole(roleUpdateDto.getRole());
        }
        userRepository.save(user);
    }


    @Override
    public void deleteWorker(Long workerID) {
        User user = userRepository.findById(workerID).orElseThrow(WorkerNotFoundException::new);
        userRepository.delete(user);
    }
}
