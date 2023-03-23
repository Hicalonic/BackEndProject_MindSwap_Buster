package org.mindswap.controller;

import org.mindswap.dto.StoreCreateDto;
import org.mindswap.dto.StoreDto;
import org.mindswap.dto.StoreUpdateDto;
import org.mindswap.service.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping ("/store")
public class StoreController {
    private StoreService storeService;
@Autowired
    public StoreController(StoreService storeService) {
        this.storeService = storeService;
    }

    @GetMapping("{id}")
    public ResponseEntity<StoreDto> getStoreById(@PathVariable("{id}")Long id) {
     StoreDto storeDto = storeService.getStoreById(id);
     return new ResponseEntity<>(storeDto, HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<StoreDto>> getAllStores(){
    List<StoreDto> storeDtoList = storeService.getAllStores();
    return new ResponseEntity<>(storeDtoList, HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<StoreDto> createStore(@RequestBody StoreCreateDto storeCreateDto){
    StoreDto storeDto = storeService.createStore(storeCreateDto);
    return new ResponseEntity<>(storeDto, HttpStatus.CREATED);
    }

    @PutMapping("{id}")
    public ResponseEntity<StoreDto> updatedStore(@PathVariable("{id}") Long id,@RequestBody StoreUpdateDto storeUpdateDto){
    StoreDto storeDto = storeService.updateStore(id ,storeUpdateDto);
    return new ResponseEntity<>(storeDto, HttpStatus.ACCEPTED);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<StoreDto> deleteStore(@PathVariable("{id}") Long id, @RequestBody StoreUpdateDto storeUpdateDto){
    storeService.deleteStore(id);
    return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }
}
