package org.mindswap.mappersUser;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mindswap.dto.WorkerCreateDto;
import org.mindswap.dto.WorkerDto;
import org.mindswap.dto.WorkerUpdateDto;
import org.mindswap.dtosUser.UserWorkerCreateDto;
import org.mindswap.dtosUser.UserWorkerDto;
import org.mindswap.dtosUser.UserWorkerUpdateDto;
import org.mindswap.model.User;
import org.mindswap.model.Worker;

@Mapper(componentModel = "spring")
public interface UserWorkerMapper {

    User fromDtoToEntity(UserWorkerDto userWorkerDto);
    @InheritInverseConfiguration
    UserWorkerDto fromEntityToDto(User user);

    User fromCreateDtoToEntity(UserWorkerCreateDto userWorkerCreateDto);
    @InheritInverseConfiguration
    UserWorkerCreateDto fromEntityToCreateDto(User user);

    User fromUpdateDtoToEntity(UserWorkerUpdateDto userWorkerUpdateDto);
    @InheritInverseConfiguration
    UserWorkerUpdateDto fromEntityToUpdateDto(User user);
}
