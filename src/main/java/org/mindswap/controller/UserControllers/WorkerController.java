package org.mindswap.controller.UserControllers;


import jakarta.validation.Valid;
import org.mindswap.dto.UserDto;
import org.mindswap.dto.UserUpdateDto;
import org.mindswap.model.Role;
import org.mindswap.service.WorkerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.mindswap.security.config.JwtAuthenticationFilter.getAuthenticatedUserId;

@RestController
@RequestMapping("/worker")
public class WorkerController {
    private WorkerService workerService;

    @Autowired
    public WorkerController(WorkerService workerService) {
        this.workerService = workerService;
    }

    @GetMapping(path = "")
    @PreAuthorize("hasRole('WORKER')")
    public ResponseEntity<String> welcomeWorker() {
        return new ResponseEntity<>("Welcome to Blockbuster, dear worker.", HttpStatus.OK);
    }

    @GetMapping(path = "/info")
    @PreAuthorize("hasRole('WORKER')")
    public ResponseEntity<UserDto> getMyInfo() {
        Long authenticatedWorkerId = Long.valueOf(getAuthenticatedUserId());
        UserDto myInfoDto = workerService.getWorkerById(authenticatedWorkerId);
        return new ResponseEntity<>(myInfoDto, HttpStatus.OK);
    }

    @PutMapping(path = "/info")
    @PreAuthorize("hasRole('WORKER')")
    public ResponseEntity<String> updateMyInfo(@Valid @RequestBody UserUpdateDto userUpdateDto) {
        Long authenticatedWorkerId = Long.valueOf(getAuthenticatedUserId());
        workerService.updateWorker(authenticatedWorkerId,userUpdateDto);
        return new ResponseEntity<>("Your information has been updated.", HttpStatus.OK);
    }

    @DeleteMapping(path = "/info")
    @PreAuthorize("hasRole('WORKER')")
    public ResponseEntity<String> deleteMyInfo() {
        Long authenticatedWorkerId = Long.valueOf(getAuthenticatedUserId());
        workerService.deleteWorker(authenticatedWorkerId);
        return new ResponseEntity<>("Your account has been deleted.", HttpStatus.OK);
    }


    @GetMapping(path = "/{id}")
    @PreAuthorize("hasAnyRole('MANAGER','ADMIN')")
    public ResponseEntity<UserDto> getWorkerInfo(@PathVariable("id") Long workerId) {
        UserDto worker = workerService.getWorkerById(workerId);
        return new ResponseEntity<>(worker, HttpStatus.OK);
    }

    @PutMapping(path = "/{id}")
    @PreAuthorize("hasAnyRole('MANAGER','ADMIN')")
    public ResponseEntity<String> updateWorkerInfo(@PathVariable("id") Long workerId, @Valid @RequestBody UserUpdateDto userUpdateDto) {
        workerService.updateWorker(workerId, userUpdateDto);
        return new ResponseEntity<>("Updated successfully.", HttpStatus.OK);
    }

    @DeleteMapping(path = "/{id}")
    @PreAuthorize("hasAnyRole('MANAGER','ADMIN')")
    public ResponseEntity<String> deleteWorker(@PathVariable("id") Long workerId) {
        workerService.deleteWorker(workerId);
        return new ResponseEntity<>("Deleted successfully.", HttpStatus.OK);
    }

    @GetMapping(path = "/all")
    @PreAuthorize("hasAnyRole('MANAGER','ADMIN')")
    public ResponseEntity<List<UserDto>> getAllWorkers() {
        List<UserDto> workersList = workerService.getAllWorkers();
        return new ResponseEntity<>(workersList, HttpStatus.OK);
    }
}