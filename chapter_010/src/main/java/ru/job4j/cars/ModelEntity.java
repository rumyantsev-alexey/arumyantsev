package ru.job4j.cars;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "model")
public class ModelEntity implements ProjectCars {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    @ManyToOne (cascade = {CascadeType.REFRESH})
    @JoinColumn(name = "mark_id")
    private MarkEntity mark;

    @OneToMany(mappedBy = "model", fetch = FetchType.EAGER)
    private Set<CarEntity> car;

    public ModelEntity() {

    }

    public ModelEntity(String name, MarkEntity mark) {
        this.name = name;
        this.mark = mark;
    }

    public Set<CarEntity> getCar() {
        return car;
    }

    public void setCar(Set<CarEntity> car) {
        this.car = car;
    }

    public MarkEntity getMark() {
        return mark;
    }

    public void setMark(MarkEntity mark) {
        this.mark = mark;
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

        ModelEntity that = (ModelEntity) o;

        if (name != null ? !name.equals(that.name) : that.name != null) {
            return false;
        }
        return mark != null ? mark.equals(that.mark) : that.mark == null;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (mark != null ? mark.hashCode() : 0);
        return result;
    }
}
