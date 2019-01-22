package ru.job4j.cars;

import java.util.Arrays;
import javax.persistence.*;

@Entity
@Table(name="foto")
public class FotoEntity {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @Column(name="name")
    private String name;

    @Lob
    @Column( name = "foto" )
    private byte[] foto;

    @ManyToOne(cascade= {CascadeType.REFRESH})
    @JoinColumn(name="car_id")
    private CarEntity car;

    public CarEntity getCar() {
        return car;
    }

    public void setCar(CarEntity car) {
        this.car = car;
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

    public byte[] getFoto() {
        return foto;
    }

    public void setFoto(byte[] foto) {
        this.foto = foto;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        FotoEntity that = (FotoEntity) o;

        if (id != that.id) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (!Arrays.equals(foto, that.foto)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + Arrays.hashCode(foto);
        return result;
    }

    @ManyToOne(optional = false)
    private CarEntity carEntities;

    public CarEntity getCarEntities() {
        return carEntities;
    }

    public void setCarEntities(CarEntity carEntities) {
        this.carEntities = carEntities;
    }
}
