package org.mindswap.mapper;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface StaffMapper {

    Worker fromDtoToEntity(WorkerDto workerDto);
    WorkerDto fromDtoToEntity(Worker worker);
}
