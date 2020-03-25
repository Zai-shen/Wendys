package at.ac.tuwien.sepm.assignment.individual.endpoint.dto;

import at.ac.tuwien.sepm.assignment.individual.entity.Horse;

import java.time.LocalDateTime;
import java.util.Objects;

public class HorseDto extends BaseDto {

    private String name;
    private String description; //optional
    private Integer rating;
    private LocalDateTime birthDay;
    private String breed;
    private String imageURI;
    private Long ownerId; //optional

    public HorseDto() {
    }

    public HorseDto(Long id, String name, Integer rating, LocalDateTime birthDay, LocalDateTime created, LocalDateTime updated) {
        super(id, created, updated);
        this.name = name;
        this.rating = rating;
        this.birthDay = birthDay;
    }

    // Standard horseDto without optionals
    public HorseDto(String name, Integer rating, LocalDateTime birthDay, String breed, String imageURI) {
        this.name = name;
        this.rating = rating;
        this.birthDay = birthDay;
        this.breed = breed;
        this.imageURI = imageURI;
    }

    public HorseDto(Long id, String name, String description, Integer rating, LocalDateTime birthDay, String breed, String imageURI, Long ownerId, LocalDateTime created, LocalDateTime updated) {
        super(id, created, updated);
        this.name = name;
        this.description = description;
        this.rating = rating;
        this.birthDay = birthDay;
        this.breed = breed;
        this.imageURI = imageURI;
        this.ownerId = ownerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(LocalDateTime birthDay) {
        this.birthDay = birthDay;
    }

    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    public String getImageURI() {
        return imageURI;
    }

    public void setImageURI(String imageURI) {
        this.imageURI = imageURI;
    }

    public Long getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Long ownerId) {
        this.ownerId = ownerId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        HorseDto horseDto = (HorseDto) o;
        return name.equals(horseDto.name) &&
            Objects.equals(description, horseDto.description) &&
            rating.equals(horseDto.rating) &&
            birthDay.equals(horseDto.birthDay) &&
            breed.equals(horseDto.breed) &&
            imageURI.equals(horseDto.imageURI) &&
            Objects.equals(ownerId, horseDto.ownerId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), name, description, rating, birthDay, breed, imageURI, ownerId);
    }

    @Override
    protected String fieldsString() {
        return super.fieldsString() + ", name=" + name + ", description=" + description + ", rating=" + rating
            + ", birthday=" + birthDay + ", breed=" + breed + ", imageURI=" + (imageURI != null ? "true" : "false") + ", ownerId" + ownerId;
    }

    @Override
    public String toString() {
        return "HorseDto{ " + fieldsString() + " }";
    }
}
