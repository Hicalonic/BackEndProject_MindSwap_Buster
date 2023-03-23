package org.mindswap.mapper;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Date;
import javax.annotation.processing.Generated;
import org.mindswap.dto.RentalCreateDto;
import org.mindswap.dto.RentalDto;
import org.mindswap.dto.RentalUpdateDto;
import org.mindswap.model.Rental;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-03-23T16:04:32+0000",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 19.0.2 (Oracle Corporation)"
)
public class RentalMapperImpl implements RentalMapper {

    @Override
    public Rental fromDtoToEntity(RentalDto rentalDto) {
        if ( rentalDto == null ) {
            return null;
        }

        Rental.RentalBuilder rental = Rental.builder();

        rental.client( rentalDto.getClient() );
        if ( rentalDto.getStartDate() != null ) {
            rental.startDate( LocalDateTime.ofInstant( rentalDto.getStartDate().toInstant(), ZoneOffset.UTC ).toLocalDate() );
        }
        if ( rentalDto.getEndDate() != null ) {
            rental.endDate( LocalDateTime.ofInstant( rentalDto.getEndDate().toInstant(), ZoneOffset.UTC ).toLocalDate() );
        }

        return rental.build();
    }

    @Override
    public RentalDto fromEntityToDto(Rental rental) {
        if ( rental == null ) {
            return null;
        }

        RentalDto.RentalDtoBuilder rentalDto = RentalDto.builder();

        if ( rental.getStartDate() != null ) {
            rentalDto.startDate( Date.from( rental.getStartDate().atStartOfDay( ZoneOffset.UTC ).toInstant() ) );
        }
        if ( rental.getEndDate() != null ) {
            rentalDto.endDate( Date.from( rental.getEndDate().atStartOfDay( ZoneOffset.UTC ).toInstant() ) );
        }
        rentalDto.client( rental.getClient() );

        return rentalDto.build();
    }

    @Override
    public Rental fromCreateDtoToEntity(RentalCreateDto rentalCreateDto) {
        if ( rentalCreateDto == null ) {
            return null;
        }

        Rental.RentalBuilder rental = Rental.builder();

        rental.client( rentalCreateDto.getClient() );
        if ( rentalCreateDto.getStartDate() != null ) {
            rental.startDate( LocalDateTime.ofInstant( rentalCreateDto.getStartDate().toInstant(), ZoneOffset.UTC ).toLocalDate() );
        }
        if ( rentalCreateDto.getEndDate() != null ) {
            rental.endDate( LocalDateTime.ofInstant( rentalCreateDto.getEndDate().toInstant(), ZoneOffset.UTC ).toLocalDate() );
        }

        return rental.build();
    }

    @Override
    public RentalCreateDto fromEntityToCreateDto(Rental rental) {
        if ( rental == null ) {
            return null;
        }

        RentalCreateDto.RentalCreateDtoBuilder rentalCreateDto = RentalCreateDto.builder();

        if ( rental.getStartDate() != null ) {
            rentalCreateDto.startDate( Date.from( rental.getStartDate().atStartOfDay( ZoneOffset.UTC ).toInstant() ) );
        }
        if ( rental.getEndDate() != null ) {
            rentalCreateDto.endDate( Date.from( rental.getEndDate().atStartOfDay( ZoneOffset.UTC ).toInstant() ) );
        }
        rentalCreateDto.client( rental.getClient() );

        return rentalCreateDto.build();
    }

    @Override
    public Rental fromUpdateDtoToEntity(RentalUpdateDto rentalUpdateDto) {
        if ( rentalUpdateDto == null ) {
            return null;
        }

        Rental.RentalBuilder rental = Rental.builder();

        rental.client( rentalUpdateDto.getClient() );
        rental.startDate( rentalUpdateDto.getStartDate() );
        rental.endDate( rentalUpdateDto.getEndDate() );

        return rental.build();
    }

    @Override
    public RentalUpdateDto fromEntityToUpdateDto(Rental rental) {
        if ( rental == null ) {
            return null;
        }

        RentalUpdateDto.RentalUpdateDtoBuilder rentalUpdateDto = RentalUpdateDto.builder();

        rentalUpdateDto.startDate( rental.getStartDate() );
        rentalUpdateDto.endDate( rental.getEndDate() );
        rentalUpdateDto.client( rental.getClient() );

        return rentalUpdateDto.build();
    }
}
