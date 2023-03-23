package org.mindswap.mapper;

import javax.annotation.processing.Generated;
import org.mindswap.dto.StoreCreateDto;
import org.mindswap.dto.StoreDto;
import org.mindswap.dto.StoreUpdateDto;
import org.mindswap.model.Store;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-03-23T16:54:33+0000",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 19.0.2 (Oracle Corporation)"
)
@Component
public class StoreMapperImpl implements StoreMapper {

    @Override
    public Store fromDtoToEntity(StoreDto storeDto) {
        if ( storeDto == null ) {
            return null;
        }

        Store.StoreBuilder store = Store.builder();

        store.city( storeDto.getCity() );
        store.address( storeDto.getAddress() );
        store.managerId( storeDto.getManagerId() );

        return store.build();
    }

    @Override
    public StoreDto fromEntityToDto(Store store) {
        if ( store == null ) {
            return null;
        }

        StoreDto.StoreDtoBuilder storeDto = StoreDto.builder();

        storeDto.city( store.getCity() );
        storeDto.address( store.getAddress() );
        storeDto.managerId( store.getManagerId() );

        return storeDto.build();
    }

    @Override
    public Store fromCreateDtoToEntity(StoreCreateDto storeCreateDto) {
        if ( storeCreateDto == null ) {
            return null;
        }

        Store.StoreBuilder store = Store.builder();

        store.city( storeCreateDto.getCity() );
        store.address( storeCreateDto.getAddress() );
        store.managerId( storeCreateDto.getManagerId() );

        return store.build();
    }

    @Override
    public StoreCreateDto fromEntityToStoreCreateDto(Store store) {
        if ( store == null ) {
            return null;
        }

        StoreCreateDto.StoreCreateDtoBuilder storeCreateDto = StoreCreateDto.builder();

        storeCreateDto.city( store.getCity() );
        storeCreateDto.address( store.getAddress() );
        storeCreateDto.managerId( store.getManagerId() );

        return storeCreateDto.build();
    }

    @Override
    public Store fromUpdateDtoToEntity(StoreUpdateDto storeUpdateDto) {
        if ( storeUpdateDto == null ) {
            return null;
        }

        Store.StoreBuilder store = Store.builder();

        store.city( storeUpdateDto.getCity() );
        store.address( storeUpdateDto.getAddress() );
        store.managerId( storeUpdateDto.getManagerId() );

        return store.build();
    }

    @Override
    public StoreUpdateDto fromEntityToUpdateDto(Store store) {
        if ( store == null ) {
            return null;
        }

        StoreUpdateDto.StoreUpdateDtoBuilder storeUpdateDto = StoreUpdateDto.builder();

        storeUpdateDto.city( store.getCity() );
        storeUpdateDto.address( store.getAddress() );
        storeUpdateDto.managerId( store.getManagerId() );

        return storeUpdateDto.build();
    }
}
