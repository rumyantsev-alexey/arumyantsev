package ru.job4j.cars;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "mark")
public class MarkEntity implements ProjectCars {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "mark", fetch = FetchType.EAGER)
    private Set<ModelEntity> model;

    @OneToMany(mappedBy = "mark", fetch = FetchType.EAGER)
    private Set<CarEntity> car;

    public MarkEntity() {

    }

    public MarkEntity(String name) {
        this.name = name;
    }

    public Set<CarEntity> getCar() {
        return car;
    }

    public void setCar(Set<CarEntity> car) {
        this.car = car;
    }

    public Set<ModelEntity> getModel() {
        return model;
    }

    public void setModel(Set<ModelEntity> model) {
        this.model = model;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        MarkEntity that = (MarkEntity) o;

        return name != null ? name.equals(that.name) : that.name == null;
    }

    @Override
    public int hashCode() {
        return name != null ? name.hashCode() : 0;
    }

}
