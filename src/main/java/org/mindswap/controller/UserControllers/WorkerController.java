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
    @PreAuthorize("hasAnyRole('WORKER','MANAGER','ADMIN')")
    public ResponseEntity<String> welcomeWorker() {
        return new ResponseEntity<>("Welcome to Blockbuster, dear worker.", HttpStatus.OK);
    }

    @GetMapping(path = "/info")
    @PreAuthorize("hasRole('WORKER')")
    public ResponseEntity<UserDto> myInfo() {
        Long authenticatedWorkerId = Long.valueOf(getAuthenticatedUserId());
        UserDto myInfoDto = workerService.getWorkerById(authenticatedWorkerId);

        //TODO: VERIFY THIS METHOD
        return new ResponseEntity<>(myInfoDto, HttpStatus.OK);
    }

    @GetMapping(path = "/{id}")
    @PreAuthorize("hasAnyRole('MANAGER','ADMIN')")
    public ResponseEntity<UserDto> getWorkerInfo(@PathVariable("id") Long workerId) {
        UserDto worker = workerService.getWorkerById(workerId);

        //TODO: VERIFY THIS METHOD
        return new ResponseEntity<>(worker, HttpStatus.OK);
    }

    @PutMapping(path = "/{id}")
    @PreAuthorize("hasAnyRole('WORKER','MANAGER','ADMIN')")
    public ResponseEntity<String> updateWorkerInfo(@PathVariable("id") Long workerId, @Valid @RequestBody UserUpdateDto userUpdateDto) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String role = auth.getAuthorities().iterator().next().getAuthority();
        Long authenticatedWorkerId = Long.valueOf(getAuthenticatedUserId());

        //If A client tries to access other clients:
        if (role.equals(Role.WORKER) && !authenticatedWorkerId.equals(workerId)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        workerService.updateWorker(workerId, userUpdateDto);

        //TODO: VERIFY THIS METHOD
        return new ResponseEntity<>("Updated successfully.", HttpStatus.OK);
    }

    @DeleteMapping(path = "/{id}")
    @PreAuthorize("hasAnyRole('WORKER','MANAGER','ADMIN')")
    public ResponseEntity<String> deleteWorker(@PathVariable("id") Long workerId) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String role = auth.getAuthorities().iterator().next().getAuthority();
        Long authenticatedWorkerId = Long.valueOf(getAuthenticatedUserId());

        //If A worker tries to access other clients:
        if (role.equals(Role.WORKER) && !authenticatedWorkerId.equals(workerId)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        workerService.deleteWorker(workerId);

        //TODO: VERIFY THIS METHOD
        return new ResponseEntity<>("Deleted successfully.", HttpStatus.OK);
    }

    @GetMapping(path = "/all")
    @PreAuthorize("hasAnyRole('MANAGER','ADMIN')")
    public ResponseEntity<List<UserDto>> getAllWorkers() {
        List<UserDto> workersList = workerService.getAllWorkers();

        //TODO: VERIFY THIS METHOD
        return new ResponseEntity<>(workersList, HttpStatus.OK);
    }

    @GetMapping(path = "/{id}/update-role")
    @PreAuthorize("hasAnyRole('MANAGER','ADMIN')")
    public ResponseEntity<String> updateWorkerToManagerRole(@PathVariable("id") Long workerId, @Valid @RequestBody UserUpdateDto userUpdateDto) {
        workerService.updateWorkerRole(workerId,userUpdateDto);

        //TODO: VERIFY THIS METHOD
        return new ResponseEntity<>("Role updated to manager.", HttpStatus.OK);
    }

}