package ru.job4j.cars;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "users")
public class UsersEntity implements ProjectCars {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "password")
    private String password;

    @Column(name = "email")
    private String email;

    @ManyToOne(cascade = {CascadeType.REFRESH})
    @JoinColumn(name = "city_id")
    private CityEntity city;

    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
    private Set<CarEntity> car;

    public UsersEntity() {

    }

    public UsersEntity(String name, String password, String email, CityEntity city) {
        this.name = name;
        this.password = password;
        this.email = email;
        this.city = city;
    }

    public Set<CarEntity> getCar() {
        return car;
    }

    public void setCar(Set<CarEntity> car) {
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public CityEntity getCity() {
        return city;
    }

    public void setCity(CityEntity city) {
        this.city = city;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        UsersEntity that = (UsersEntity) o;

        if (name != null ? !name.equals(that.name) : that.name != null) {
            return false;
        }
        return password != null ? password.equals(that.password) : that.password == null;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (password != null ? password.hashCode() : 0);
        return result;
    }

}
