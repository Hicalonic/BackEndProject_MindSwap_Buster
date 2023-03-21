package org.mindswap.service;

import org.mindswap.dto.ClientDto;
import org.mindswap.dto.ClientUpdateDto;
import org.mindswap.dto.StoreDto;
import org.mindswap.dto.StoreUpdateDto;

import java.util.List;

public interface StoreService {
    StoreDto getStoreById(Long storeId);
    List<StoreDto> getAllStores();
    StoreDto updateStore(Long clientId, StoreUpdateDto storeUpdateDto);
}
