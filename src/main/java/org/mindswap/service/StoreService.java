package org.mindswap.service;

import org.mindswap.dto.StoreDto;
import org.mindswap.dto.StoreUpdateDto;

import java.util.List;

public interface StoreService {

    StoreDto getStoreById(Long storeId);

    public StoreDto createStore(StoreDto storeDto);
    public StoreDto updateStore(Long storeID, StoreUpdateDto storeUpdateDto);
    public void deleteStore(Long storeId);
    List<StoreDto> getAllStores();
}
