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
public class StaffMapperImpl implements StaffMapper {

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
    public WorkerDto fromDtoToEntity(Worker worker) {
        if ( worker == null ) {
            return null;
        }

        WorkerDto.WorkerDtoBuilder workerDto = WorkerDto.builder();

        workerDto.firstName( worker.getFirstName() );
        workerDto.lastName( worker.getLastName() );
        workerDto.email( worker.getEmail() );

        return workerDto.build();
    }
}
