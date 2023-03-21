package org.mindswap.mapper;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mindswap.dto.WorkerCreateDto;
import org.mindswap.dto.WorkerDto;
import org.mindswap.dto.WorkerUpdateDto;
import org.mindswap.model.Worker;

@Mapper
public interface WorkerMapper {
    Worker fromDtoToEntity(WorkerDto workerDto);
    @InheritInverseConfiguration
    WorkerDto fromEntityToDto(Worker worker);

    Worker fromCreateDtoToEntity(WorkerCreateDto workerCreateDto);
    @InheritInverseConfiguration
    WorkerCreateDto fromEntityToCreateDto(Worker worker);

    Worker fromUpdateDtoToEntity(WorkerUpdateDto workerUpdateDto);
    @InheritInverseConfiguration
    WorkerUpdateDto fromEntityToUpdateDto(Worker worker);



}
