package org.mindswap.mapper;


import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mindswap.dto.StoreCreateDto;
import org.mindswap.dto.StoreDto;
import org.mindswap.dto.StoreUpdateDto;
import org.mindswap.model.Store;

@Mapper(componentModel = "spring")
public interface StoreMapper {

    Store fromDtoToEntity(StoreDto storeDto);
    @InheritInverseConfiguration
    StoreDto fromEntityToDto(Store store);

    Store fromCreateDtoToEntity(StoreCreateDto storeCreateDto);
    @InheritInverseConfiguration
    StoreCreateDto fromEntityToStoreCreateDto(Store store);

    Store fromUpdateDtoToEntity(StoreUpdateDto storeUpdateDto);
    @InheritInverseConfiguration
    StoreUpdateDto fromEntityToUpdateDto(Store store);

}
