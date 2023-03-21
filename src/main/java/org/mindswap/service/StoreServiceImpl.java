package org.mindswap.service;

import org.apache.catalina.Store;
import org.mindswap.dto.StoreDto;
import org.mindswap.dto.StoreUpdateDto;
import org.mindswap.mapper.StoreMapper;
import org.mindswap.repository.StoreRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class StoreServiceImpl implements StoreService {

    private StoreRepository storeRepository;
    private StoreMapper storeMapper;
    @Autowired
    public StoreServiceImpl(StoreRepository storeRepository, StoreMapper storeMapper) {
        this.storeRepository = storeRepository;
        this.storeMapper = storeMapper;
    }



    @Override
    public StoreDto createStore(StoreDto storeDto) {
        return null;
    }

    @Override
    public StoreDto getStoreById(Long storeId) {
        return null;
    }

    @Override
    public List<StoreDto> getAllStores() {
        return null;
    }

    @Override
    public StoreDto updateStore(Long storeID, StoreUpdateDto storeUpdateDto) {
        return null;
    }

    @Override
    public void deleteStore(Long storeId) {

    }


}
