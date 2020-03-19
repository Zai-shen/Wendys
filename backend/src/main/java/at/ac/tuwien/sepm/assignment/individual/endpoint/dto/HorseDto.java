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
    private Long ownerId;

    public HorseDto() {
    }

    public HorseDto(Long id, String name, Integer rating, LocalDateTime birthDay, LocalDateTime created, LocalDateTime updated) {
        super(id, created, updated);
        this.name = name;
        this.rating = rating;
        this.birthDay = birthDay;
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
        if (!(o instanceof Horse)) return false;
        if (!super.equals(o)) return false;
        HorseDto horseDto = (HorseDto) o;
        return Objects.equals(name, horseDto.name) && Objects.equals(rating, horseDto.rating) && Objects.equals(birthDay, horseDto.birthDay); //description?
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), name, description, rating, birthDay);
    }

    @Override
    protected String fieldsString() {
        return super.fieldsString() + ", name='" + name + ((description!=null)?", description='" + description:"") + ", rating='" + rating + ", birthday='" + birthDay + '\'';
    }

    @Override
    public String toString() {
        return "HorseDto{ " + fieldsString() + " }";
    }
}
