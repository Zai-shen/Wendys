package at.ac.tuwien.sepm.assignment.individual.endpoint.mapper;

import at.ac.tuwien.sepm.assignment.individual.endpoint.dto.OwnerDto;
import at.ac.tuwien.sepm.assignment.individual.endpoint.dto.SearchOwnerCriteriaDto;
import at.ac.tuwien.sepm.assignment.individual.entity.Owner;
import org.springframework.stereotype.Component;

@Component
public class OwnerMapper {

    public OwnerDto entityToDto(Owner owner) {
        return new OwnerDto(owner.getId(), owner.getName(), owner.getOwnedHorses(), owner.getCreatedAt(), owner.getUpdatedAt());
    }

    public Owner dtoToEntity(OwnerDto ownerDto) {
        return new Owner(ownerDto.getId(), ownerDto.getName(), ownerDto.getOwnedHorses(), ownerDto.getCreatedAt(), ownerDto.getUpdatedAt());
    }

    public Owner criteriaDtoToEntity(SearchOwnerCriteriaDto socd) {
        return new Owner(socd.getName());
    }
}
