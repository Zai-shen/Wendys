package at.ac.tuwien.sepm.assignment.individual.endpoint.dto;

import org.springframework.beans.factory.annotation.Required;
import org.springframework.lang.Nullable;

import javax.validation.constraints.*;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Objects;

public final class SearchHorseCriteriaDto {

    @Nullable
    private String name; //optional
    @Nullable
    private String description; //optional
    @Nullable
    private String rating; //optional
    @Nullable
    private String birthDay; //optional
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

    public SearchHorseCriteriaDto(Long id, String name, String description, String rating, String birthDay, String breed) {
        this.id = id;
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
    public String getRating() {
        return rating;
    }

    public void setRating(@Nullable String rating) {
        this.rating = rating;
    }

    public Integer returnRating(){
        if (this.rating != null && !this.rating.equals("null"))
            return Integer.parseInt(this.rating);
        else
            return null;
    }

    @Nullable
    public String getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(@Nullable String birthDay) {
        this.birthDay = birthDay;
    }

    public LocalDateTime returnBirthDay(){
        if (this.birthDay != null && !this.birthDay.equals("null"))
        return LocalDateTime.parse(this.birthDay);
        else
            return null;
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
        return "id=" + id + ", name=" + name + ", description=" + description + ", rating=" + rating + ", birthday=" + birthDay + ", breed=" + breed;
    }

    @Override
    public String toString() {
        return "SearchHorseCriteriaDto{ " + fieldsString() + " }";
    }
}
