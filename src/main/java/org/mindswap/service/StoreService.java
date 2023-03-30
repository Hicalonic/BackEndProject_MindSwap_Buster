package org.mindswap.service;

import org.mindswap.dto.StoreCreateDto;
import org.mindswap.dto.StoreDto;

import java.util.List;

public interface StoreService {

    StoreDto getStoreById(Long storeId);

    public StoreDto createStore(StoreCreateDto storeCreateDto);
    public void deleteStore(Long storeId);
    List<StoreDto> getAllStores();
}
