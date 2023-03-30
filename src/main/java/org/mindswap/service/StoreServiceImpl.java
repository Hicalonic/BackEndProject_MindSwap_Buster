package org.mindswap.service;

import org.mindswap.exceptions.StoreNotFoundException;
import org.mindswap.model.Store;
import org.mindswap.dto.StoreCreateDto;
import org.mindswap.dto.StoreDto;
import org.mindswap.mapper.StoreMapper;
import org.mindswap.repository.StoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StoreServiceImpl implements StoreService {

    private StoreRepository storeRepository;
    private StoreMapper storeMapper;


    @Autowired
    public StoreServiceImpl(StoreRepository storeRepository, StoreMapper storeMapper) {
        this.storeRepository = storeRepository;
        this.storeMapper = storeMapper;
    }

    @Override
    public StoreDto createStore(StoreCreateDto storeCreateDto) {
        Store store = storeMapper.fromCreateDtoToEntity(storeCreateDto);
        storeRepository.save(store);
        return storeMapper.fromEntityToDto(store);
    }

    @Override
    public StoreDto getStoreById(Long storeId) {
        Store store = storeRepository.findById(storeId).orElseThrow(StoreNotFoundException::new);
        return storeMapper.fromEntityToDto(store);
    }

    @Override
    public List<StoreDto> getAllStores() {
        return storeRepository.findAll().stream().map(s->storeMapper.fromEntityToDto(s)).toList();
    }



    @Override
    public void deleteStore(Long storeId) {
        Store store = storeRepository.findById(storeId).orElseThrow(StoreNotFoundException::new);
        storeRepository.delete(store);
    }


}
