package at.ac.tuwien.sepm.assignment.individual.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Owner extends BaseEntity {

    private String name;
    private List<Horse> ownedHorses = new ArrayList<>();//optional

    public Owner() {
    }

    public Owner(String name) {
        this.name = name;
    }

    public Owner(Long id, String name) {
        super(id);
        this.name = name;
    }

    public Owner(Long id, String name, List<Horse> ownedHorses, LocalDateTime created, LocalDateTime updated) {
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
        if (!(o instanceof Owner)) return false;
        if (!super.equals(o)) return false;
        Owner owner = (Owner) o;
        return Objects.equals(name, owner.name);
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
        return "Owner{ " + fieldsString() +" }";
    }
}
