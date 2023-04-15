package org.mindswap.controller.UserController;


import jakarta.validation.Valid;
import org.mindswap.dto.UserDtoJsonBody;
import org.mindswap.dto.UserUpdateDto;
import org.mindswap.service.WorkerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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
    @PreAuthorize("hasAuthority('WORKER')")
    public ResponseEntity<String> welcomeWorker() {
        return new ResponseEntity<>("Welcome to Blockbuster, dear worker.", HttpStatus.OK);
    }
    
    @GetMapping(path = "/info")
    @PreAuthorize("hasAuthority('WORKER')")
    public ResponseEntity<UserDtoJsonBody> getMyInfo() {
        Long authenticatedWorkerId = Long.valueOf(getAuthenticatedUserId());
        UserDtoJsonBody myInfoDto = workerService.getWorkerById(authenticatedWorkerId);
        return new ResponseEntity<>(myInfoDto, HttpStatus.OK);
    }

    @PutMapping(path = "/info")
    @PreAuthorize("hasAuthority('WORKER')")
    public ResponseEntity<String> updateMyInfo(@Valid @RequestBody UserUpdateDto userUpdateDto) {
        Long authenticatedWorkerId = Long.valueOf(getAuthenticatedUserId());
        workerService.updateWorker(authenticatedWorkerId,userUpdateDto);
        return new ResponseEntity<>("Your information has been updated.", HttpStatus.OK);
    }

    @DeleteMapping(path = "/info")
    @PreAuthorize("hasAuthority('WORKER')")
    public ResponseEntity<String> deleteMyInfo() {
        Long authenticatedWorkerId = Long.valueOf(getAuthenticatedUserId());
        workerService.deleteWorker(authenticatedWorkerId);
        return new ResponseEntity<>("Your account has been deleted.", HttpStatus.OK);
    }


    @GetMapping(path = "/{id}")
    @PreAuthorize("hasAnyAuthority('MANAGER','ADMIN')")
    public ResponseEntity<UserDtoJsonBody> getWorkerInfo(@PathVariable("id") Long workerId) {
        UserDtoJsonBody worker = workerService.getWorkerById(workerId);
        return new ResponseEntity<>(worker, HttpStatus.OK);
    }

    @PutMapping(path = "/{id}")
    @PreAuthorize("hasAnyAuthority('WORKER','MANAGER','ADMIN')")
    public ResponseEntity<String> updateWorkerInfo(@PathVariable("id") Long workerId, @Valid @RequestBody UserUpdateDto userUpdateDto) {
        workerService.updateWorker(workerId, userUpdateDto);
        return new ResponseEntity<>("Updated successfully.", HttpStatus.OK);
    }

    @DeleteMapping(path = "/{id}")
    @PreAuthorize("hasAnyAuthority('MANAGER','ADMIN')")
    public ResponseEntity<String> deleteWorker(@PathVariable("id") Long workerId) {
        workerService.deleteWorker(workerId);
        return new ResponseEntity<>("Deleted successfully.", HttpStatus.OK);
    }

    @GetMapping(path = "/all")
    @PreAuthorize("hasAnyAuthority('MANAGER','ADMIN')")
    public ResponseEntity<List<UserDtoJsonBody>> getAllWorkers() {
        List<UserDtoJsonBody> workersList = workerService.getAllWorkers();
        return new ResponseEntity<>(workersList, HttpStatus.OK);
    }
}