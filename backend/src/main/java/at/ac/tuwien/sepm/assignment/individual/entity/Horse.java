package at.ac.tuwien.sepm.assignment.individual.entity;

import java.time.LocalDateTime;
import java.util.Objects;

public class Horse extends BaseEntity {

    private String name;
    private String description; //optional
    private Integer rating;
    private LocalDateTime birthDay;
    private String breed;
    private String imageURI;
    private Long ownerId; //optional - horses may exist w/out owners, the world is ready for strong independent horses.


    public Horse() {
    }

    // Empty horse for search
    public Horse(String name, String description, Integer rating, LocalDateTime birthDay, String breed) {
        this.name = name;
        this.description = description;
        this.rating = rating;
        this.birthDay = birthDay;
        this.breed = breed;
    }

    // Standard horse without optionals
    public Horse(String name, Integer rating, LocalDateTime birthDay, String breed, String imageURI) {
        this.name = name;
        this.rating = rating;
        this.birthDay = birthDay;
        this.breed = breed;
        this.imageURI = imageURI;
    }

    // Horse with all optional parameters
    public Horse(Long id, String name, String description, Integer rating, LocalDateTime birthDay, String breed, String imageURI, Long ownerId, LocalDateTime created, LocalDateTime updated) {
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
        Horse horse = (Horse) o;
        return name.equals(horse.name) &&
            Objects.equals(description, horse.description) &&
            rating.equals(horse.rating) &&
            birthDay.equals(horse.birthDay) &&
            breed.equals(horse.breed) &&
            imageURI.equals(horse.imageURI) &&
            Objects.equals(ownerId, horse.ownerId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), name, description, rating, birthDay, breed, imageURI, ownerId);
    }

    @Override
    protected String fieldsString() {
        return super.fieldsString() + ", name=" + name + ", description=" + description + ", rating=" + rating
            + ", birthday=" + birthDay + ", breed=" + breed + ", imageURI=" + (imageURI != null ? "true" : "false") + ", ownerId=" + ownerId;
    }

    @Override
    public String toString() {
        return "Horse{ " + fieldsString() + " }";
    }
}
