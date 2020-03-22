package at.ac.tuwien.sepm.assignment.individual.endpoint.dto;

import at.ac.tuwien.sepm.assignment.individual.entity.Horse;
import org.springframework.lang.Nullable;

import javax.validation.constraints.Null;
import java.util.List;
import java.util.Objects;

public class SearchOwnerCriteriaDto{

    @Nullable
    private String name;
    @Null
    private List<Horse> ownedHorses; //forbidden
    @Null
    private String createdAt; //forbidden
    @Null
    private String updatedAt; //forbidden
    @Null
    private Long id; //forbidden

    public SearchOwnerCriteriaDto() {
    }

    public SearchOwnerCriteriaDto(String name) {
        this.name = name;
    }

    @Nullable
    public String getName() {
        return name;
    }

    public void setName(@Nullable String name) {
        this.name = name;
    }

    public List<Horse> getOwnedHorses() {
        return ownedHorses;
    }

    public void setOwnedHorses(List<Horse> ownedHorses) {
        this.ownedHorses = ownedHorses;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SearchOwnerCriteriaDto)) return false;
        if (!super.equals(o)) return false;
        SearchOwnerCriteriaDto ownerDto = (SearchOwnerCriteriaDto) o;
        return Objects.equals(name, ownerDto.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), name);
    }

    protected String fieldsString() {
        return ", name='" + name + '\'';
    }

    @Override
    public String toString() {
        return "SearchOwnerCriteriaDto{ " + fieldsString() + " }";
    }
}
