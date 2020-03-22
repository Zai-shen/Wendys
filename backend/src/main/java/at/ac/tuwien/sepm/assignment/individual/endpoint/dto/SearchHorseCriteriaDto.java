package at.ac.tuwien.sepm.assignment.individual.endpoint.dto;

import org.springframework.lang.Nullable;

import javax.validation.constraints.*;
import java.time.LocalDateTime;
import java.util.Objects;

public final class SearchHorseCriteriaDto {

    @Nullable
    private String name; //optional
    @Nullable
    private String description; //optional
    @Min(1)
    @Max(5)
    @Nullable
    private Integer rating; //optional
    @Nullable
    private LocalDateTime birthDay; //optional
    @Nullable
    private String breed; //optional
    @Null
    private String createdAt; //forbidden
    @Null
    private String updatedAt; //forbidden
    @Null
    private Long ownerId; //forbidden
    @Null
    private Long id; //forbidden

    public SearchHorseCriteriaDto() {
    }

    public SearchHorseCriteriaDto(Long id, String name, String description, Integer rating, LocalDateTime birthDay, String breed) {
        this.name = name;
        this.description = description;
        this.rating = rating;
        this.birthDay = birthDay;
        this.breed = breed;
    }

    @Nullable
    public String getName() {
        return name;
    }

    public void setName(@Nullable String name) {
        this.name = name;
    }

    @Nullable
    public String getDescription() {
        return description;
    }

    public void setDescription(@Nullable String description) {
        this.description = description;
    }

    @Nullable
    public Integer getRating() {
        return rating;
    }

    public void setRating(@Nullable Integer rating) {
        this.rating = rating;
    }

    @Nullable
    public LocalDateTime getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(@Nullable LocalDateTime birthDay) {
        this.birthDay = birthDay;
    }

    @Nullable
    public String getBreed() {
        return breed;
    }

    public void setBreed(@Nullable String breed) {
        this.breed = breed;
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

    public Long getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Long ownerId) {
        this.ownerId = ownerId;
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
        if (o == null || getClass() != o.getClass()) return false;
        SearchHorseCriteriaDto that = (SearchHorseCriteriaDto) o;
        return Objects.equals(name, that.name) &&
            Objects.equals(description, that.description) &&
            Objects.equals(rating, that.rating) &&
            Objects.equals(birthDay, that.birthDay) &&
            Objects.equals(breed, that.breed);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, description, rating, birthDay, breed);
    }

    protected String fieldsString() {
        return ", name='" + name + ", description='" + description + ", rating='" + rating + ", birthday='" + birthDay + ", breed=" + breed + '\'';
    }

    @Override
    public String toString() {
        return "SearchHorseCriteriaDto{ " + fieldsString() + " }";
    }
}
