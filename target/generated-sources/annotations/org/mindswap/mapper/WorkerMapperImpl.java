package org.mindswap.mapper;

import javax.annotation.processing.Generated;

import org.mindswap.model.Worker;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-03-24T10:36:59+0000",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 19.0.2 (Oracle Corporation)"
)
@Component
public class WorkerMapperImpl implements WorkerMapper {

    @Override
    public Worker fromDtoToEntity(WorkerDto workerDto) {
        if ( workerDto == null ) {
            return null;
        }

        Worker.WorkerBuilder worker = Worker.builder();

        worker.firstName( workerDto.getFirstName() );
        worker.lastName( workerDto.getLastName() );
        worker.email( workerDto.getEmail() );

        return worker.build();
    }

    @Override
    public WorkerDto fromEntityToDto(Worker worker) {
        if ( worker == null ) {
            return null;
        }

        WorkerDto.WorkerDtoBuilder workerDto = WorkerDto.builder();

        workerDto.firstName( worker.getFirstName() );
        workerDto.lastName( worker.getLastName() );
        workerDto.email( worker.getEmail() );

        return workerDto.build();
    }

    @Override
    public Worker fromCreateDtoToEntity(WorkerCreateDto workerCreateDto) {
        if ( workerCreateDto == null ) {
            return null;
        }

        Worker.WorkerBuilder worker = Worker.builder();

        worker.firstName( workerCreateDto.getFirstName() );
        worker.lastName( workerCreateDto.getLastName() );
        worker.email( workerCreateDto.getEmail() );
        worker.password( workerCreateDto.getPassword() );

        return worker.build();
    }

    @Override
    public WorkerCreateDto fromEntityToCreateDto(Worker worker) {
        if ( worker == null ) {
            return null;
        }

        WorkerCreateDto.WorkerCreateDtoBuilder workerCreateDto = WorkerCreateDto.builder();

        workerCreateDto.firstName( worker.getFirstName() );
        workerCreateDto.lastName( worker.getLastName() );
        workerCreateDto.email( worker.getEmail() );
        workerCreateDto.password( worker.getPassword() );

        return workerCreateDto.build();
    }

    @Override
    public Worker fromUpdateDtoToEntity(WorkerUpdateDto workerUpdateDto) {
        if ( workerUpdateDto == null ) {
            return null;
        }

        Worker.WorkerBuilder worker = Worker.builder();

        worker.firstName( workerUpdateDto.getFirstName() );
        worker.lastName( workerUpdateDto.getLastName() );
        worker.email( workerUpdateDto.getEmail() );

        return worker.build();
    }

    @Override
    public WorkerUpdateDto fromEntityToUpdateDto(Worker worker) {
        if ( worker == null ) {
            return null;
        }

        WorkerUpdateDto.WorkerUpdateDtoBuilder workerUpdateDto = WorkerUpdateDto.builder();

        workerUpdateDto.firstName( worker.getFirstName() );
        workerUpdateDto.lastName( worker.getLastName() );
        workerUpdateDto.email( worker.getEmail() );

        return workerUpdateDto.build();
    }
}
