package MidtermExam.Group2.mapper;

import MidtermExam.Group2.dto.CustomerDTO;
import MidtermExam.Group2.entity.Customer;
import MidtermExam.Group2.entity.Status;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface CustomerMapper {

    CustomerMapper INSTANCE = Mappers.getMapper(CustomerMapper.class);

    @Mapping(target = "createdTime", source = "createdTime")
    @Mapping(target = "updatedTime", source = "updatedTime")
    @Mapping(target = "status", source = "status", qualifiedByName = "mapStatusToString")
    CustomerDTO toDTO(Customer customer);

    @Mapping(target = "createdTime", source = "createdTime")
    @Mapping(target = "updatedTime", source = "updatedTime")
    @Mapping(target = "status", source = "status", qualifiedByName = "mapStringToStatus")
    Customer toEntity(CustomerDTO customerDTO);

    @Named("mapStatusToString")
    default String mapStatusToString(Status status) {
        return status != null ? status.name() : null;
    }

    @Named("mapStringToStatus")
    default Status mapStringToStatus(String status) {
        return status != null ? Status.valueOf(status) : null;
    }
}
