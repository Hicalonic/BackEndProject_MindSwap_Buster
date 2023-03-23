package org.mindswap.mapper;

import org.mapstruct.Mapper;
import org.mindswap.dto.WorkerDto;
import org.mindswap.model.Worker;

@Mapper
public interface StaffMapper {

    Worker fromDtoToEntity(WorkerDto workerDto);
    WorkerDto fromDtoToEntity(Worker worker);
}
