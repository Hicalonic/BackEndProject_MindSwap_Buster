package org.mindswap.mapper;

import org.mapstruct.Mapper;
import org.mindswap.model.Worker;

@Mapper(componentModel = "spring")
public interface StaffMapper {

    Worker fromDtoToEntity(WorkerDto workerDto);
    WorkerDto fromDtoToEntity(Worker worker);
}
