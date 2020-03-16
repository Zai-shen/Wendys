package at.ac.tuwien.sepm.assignment.individual.entity;

import java.time.LocalDateTime;
import java.util.Objects;

public class Horse extends BaseEntity {

    private String name;
    private String description; //optional
    private Integer rating;
    private LocalDateTime birthDay;
    private String breed; //optional
    private String imageURI; //optional


    public Horse() {
    }

    public Horse(Long id, String name, Integer rating, LocalDateTime birthDay, LocalDateTime created, LocalDateTime updated) {
        super(id, created, updated);
        this.name = name;
        this.rating = rating;
        this.birthDay = birthDay;
    }

    public Horse(Long id, String name, String description, Integer rating, LocalDateTime birthDay, String breed, String imageURI, LocalDateTime created, LocalDateTime updated) {
        super(id, created, updated);
        this.name = name;
        this.description = description;
        this.rating = rating;
        this.birthDay = birthDay;
        this.breed = breed;
        this.imageURI = imageURI;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Horse)) return false;
        if (!super.equals(o)) return false;
        Horse horse = (Horse) o;
        return Objects.equals(name, horse.name) && Objects.equals(rating, horse.rating) && Objects.equals(birthDay, horse.birthDay); //description?
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), name, rating, birthDay);
    } //description?

    @Override
    protected String fieldsString() {
        return super.fieldsString() + ", name='" + name + ((description!=null)?", description='" + description:"") + ", rating='" + rating + ", birthday='" + birthDay + '\'';
    }

    @Override
    public String toString() {
        return "Horse{ " + fieldsString() +" }";
    }
}
