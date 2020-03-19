package at.ac.tuwien.sepm.assignment.individual.endpoint.dto;

import at.ac.tuwien.sepm.assignment.individual.entity.Horse;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class OwnerDto extends BaseDto {

    private String name;
    private List<Horse> ownedHorses = new ArrayList<>();//optional

    public OwnerDto() {
    }

    public OwnerDto(String name) {
        this.name = name;
    }

    public OwnerDto(Long id, String name, List<Horse> ownedHorses, LocalDateTime created, LocalDateTime updated) {
        super(id, created, updated);
        this.name = name;
        this.ownedHorses = ownedHorses;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Horse> getOwnedHorses() {
        return ownedHorses;
    }

    public void setOwnedHorses(List<Horse> ownedHorses) {
        this.ownedHorses = ownedHorses;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof OwnerDto)) return false;
        if (!super.equals(o)) return false;
        OwnerDto ownerDto = (OwnerDto) o;
        return Objects.equals(name, ownerDto.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), name);
    }

    @Override
    protected String fieldsString() {
        return super.fieldsString() + ", name='" + name + '\'';
    }

    @Override
    public String toString() {
        return "OwnerDto{ " + fieldsString() + " }";
    }
}
