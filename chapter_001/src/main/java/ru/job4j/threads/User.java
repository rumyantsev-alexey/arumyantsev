package ru.job4j.threads;

/**
 * Класс Пользователь для хранилища
 */
public class User implements Comparable<User> {
    private Integer id;
    private Integer amount;

    public User (final Integer id, final Integer amount) {
        this.id = id;
        this.amount = amount;
    }

    public Integer getId() {
        return id;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    @Override
    public int compareTo(User o) {
        if (o == null) {
            throw new NullPointerException();
        }
        return this.getId().compareTo(o.getId());
    }
}
