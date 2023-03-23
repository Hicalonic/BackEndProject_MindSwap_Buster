package org.mindswap.mapper;


import org.mapstruct.Mapper;
import org.mindswap.dto.StoreDto;
import org.mindswap.model.Store;

@Mapper
public interface StoreMapper {

    StoreDto fromEntityToDto(Store store);
    Store fromDtoToEntity(StoreDto storeDto);
}
