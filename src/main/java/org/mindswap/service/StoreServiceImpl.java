package org.mindswap.service;

import org.mindswap.exceptions.StoreNotFoundException;
import org.mindswap.model.Role;
import org.mindswap.model.Store;
import org.mindswap.dto.StoreCreateDto;
import org.mindswap.dto.StoreDto;
import org.mindswap.dto.StoreUpdateDto;
import org.mindswap.mapper.StoreMapper;
import org.mindswap.model.User;
import org.mindswap.model.Worker;
import org.mindswap.repository.StoreRepository;
import org.mindswap.repository.UserRepository;
import org.mindswap.repository.WorkerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StoreServiceImpl implements StoreService {

    private StoreRepository storeRepository;
    private StoreMapper storeMapper;

    private UserRepository userRepository;

    @Autowired
    public StoreServiceImpl(StoreRepository storeRepository, StoreMapper storeMapper, UserRepository userRepository) {
        this.storeRepository = storeRepository;
        this.storeMapper = storeMapper;
        this.userRepository = userRepository;
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
    public StoreDto updateStore(Long storeID, StoreUpdateDto storeUpdateDto) {
        Store store = storeRepository.findById(storeID).orElseThrow(StoreNotFoundException::new);
        User user = userRepository.getReferenceById(storeUpdateDto.getManagerId());
        if (storeUpdateDto.getAddress() != null) {
            store.setAddress(storeUpdateDto.getAddress());
        }
        if (storeUpdateDto.getCity() != null) {
            store.setCity(storeUpdateDto.getCity());
        }
        if (storeUpdateDto.getManagerId() != null && storeRepository.existsById(storeUpdateDto.getManagerId())
                && user.getRole().equals(Role.MANAGER)){
            store.setManagerId(storeUpdateDto.getManagerId());
        }
        storeRepository.save(store);
        return storeMapper.fromEntityToDto(store);

    }

    @Override
    public void deleteStore(Long storeId) {
        Store store = storeRepository.findById(storeId).orElseThrow(StoreNotFoundException::new);
        storeRepository.delete(store);
    }


}
