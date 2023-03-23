package org.mindswap.controller.UserControllers;


import jakarta.validation.Valid;
import org.mindswap.dto.*;
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
    public ResponseEntity<String> welcomeWorker() {
        return new ResponseEntity<>("Welcome to Blockbuster, dear worker.", HttpStatus.OK);
    }

    @GetMapping(path = "/info")
    @PreAuthorize("hasRole('WORKER')")
    public ResponseEntity<WorkerDto> myInfo() {
        Long authenticatedWorkerId = Long.valueOf(getAuthenticatedUserId());
        WorkerDto myInfoDto = workerService.getWorkerById(authenticatedWorkerId);

        //TODO: VERIFY THIS METHOD
        return new ResponseEntity<>(myInfoDto, HttpStatus.OK);
    }

    @GetMapping(path = "/{id}")
    @PreAuthorize("hasAnyRole('MANAGER','ADMIN')")
    public ResponseEntity<Object> getWorkerInfo(@PathVariable("id") Long workerId) {
        WorkerDto worker = workerService.getWorkerById(workerId);

        //TODO: VERIFY THIS METHOD
        return new ResponseEntity<>(worker, HttpStatus.OK);
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<Object> updateWorkerInfo(@PathVariable("id") Long workerId, @Valid @RequestBody WorkerUpdateDto workerUpdateDto) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String role = auth.getAuthorities().iterator().next().getAuthority();
        Long authenticatedWorkerId = Long.valueOf(getAuthenticatedUserId());

        //If A client tries to access other clients:
        if (role.equals(Role.WORKER) && !authenticatedWorkerId.equals(workerId)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        workerService.updateWorker(workerId, workerUpdateDto);

        //TODO: VERIFY THIS METHOD
        return new ResponseEntity<>("Updated successfully.", HttpStatus.OK);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Object> deleteWorker(@PathVariable("id") Long workerId) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String role = auth.getAuthorities().iterator().next().getAuthority();
        Long authenticatedWorkerId = Long.valueOf(getAuthenticatedUserId());

        //If A client tries to access other clients:
        if (role.equals(Role.CLIENT) && !authenticatedWorkerId.equals(workerId)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        workerService.deleteWorker(workerId);

        //TODO: VERIFY THIS METHOD
        return new ResponseEntity<>("Deleted successfully.", HttpStatus.OK);
    }

    @GetMapping(path = "/all")
    @PreAuthorize("hasAnyRole('MANAGER','ADMIN')")
    public ResponseEntity<List<WorkerDto>> getAllWorkers() {
        List<WorkerDto> workersList = workerService.getAllWorkers();

        //TODO: VERIFY THIS METHOD
        return new ResponseEntity<>(workersList, HttpStatus.OK);
    }
}