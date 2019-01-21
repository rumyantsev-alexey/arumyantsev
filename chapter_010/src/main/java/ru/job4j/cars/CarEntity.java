package ru.job4j.cars;

import java.util.List;

public class CarEntity {
    private int id;
    private String note;
    private UsersEntity user;
    private CityEntity city;
    private MarkEntity mark;
    private ModelEntity model;
    private Integer price;
    private Integer issue;
    private Integer enginecapacity;
    private Integer power;
    private TransmissionEntity trans;
    private BodytypeEntity btype;
    private EnginestypeEntity etype;
    private DriveunitEntity dunit;
    private WheelEntity wheel;
    private List<FotoEntity> fotos;

    public CarEntity() {

    }

    public CarEntity(final String note) {
        this.note = note;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public List<FotoEntity> getFotos() {
        return fotos;
    }

    public void setFotos(List<FotoEntity> fotos) {
        this.fotos = fotos;
    }

    public MarkEntity getMark() {
        return mark;
    }

    public void setMark(MarkEntity mark) {
        this.mark = mark;
    }

    public UsersEntity getUser() {
        return user;
    }

    public void setUser(UsersEntity user) {
        this.user = user;
    }

    public CityEntity getCity() {
        return city;
    }

    public void setCity(CityEntity city) {
        this.city = city;
    }

    public ModelEntity getModel() {
        return model;
    }

    public void setModel(ModelEntity model) {
        this.model = model;
    }

    public TransmissionEntity getTrans() {
        return trans;
    }

    public void setTrans(TransmissionEntity trans) {
        this.trans = trans;
    }

    public BodytypeEntity getBtype() {
        return btype;
    }

    public void setBtype(BodytypeEntity btype) {
        this.btype = btype;
    }

    public EnginestypeEntity getEtype() {
        return etype;
    }

    public void setEtype(EnginestypeEntity etype) {
        this.etype = etype;
    }

    public DriveunitEntity getDunit() {
        return dunit;
    }

    public void setDunit(DriveunitEntity dunit) {
        this.dunit = dunit;
    }

    public WheelEntity getWheel() {
        return wheel;
    }

    public void setWheel(WheelEntity wheel) {
        this.wheel = wheel;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getIssue() {
        return issue;
    }

    public void setIssue(Integer issue) {
        this.issue = issue;
    }

    public Integer getEnginecapacity() {
        return enginecapacity;
    }

    public void setEnginecapacity(Integer enginecapacity) {
        this.enginecapacity = enginecapacity;
    }

    public Integer getPower() {
        return power;
    }

    public void setPower(Integer power) {
        this.power = power;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CarEntity carEntity = (CarEntity) o;

        if (note != null ? !note.equals(carEntity.note) : carEntity.note != null) return false;
        if (!user.equals(carEntity.user)) return false;
        if (!city.equals(carEntity.city)) return false;
        return model.equals(carEntity.model);
    }

    @Override
    public int hashCode() {
        int result = note != null ? note.hashCode() : 0;
        result = 31 * result + user.hashCode();
        result = 31 * result + city.hashCode();
        result = 31 * result + model.hashCode();
        return result;
    }
}
