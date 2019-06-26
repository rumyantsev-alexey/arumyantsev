package ru.job4j.availability;

/**
 * Класс записи состойния сервера
 */
public class Record {
    private Status status;
    private String time;

    public Record(String str) {
        status = Status.getEnumStatus(str.split(" ")[0]);
        time = str.split(" ")[1];
    }

    public Status getStatus() {
        return status;
    }

    public String getTime() {
        return time;
    }

    @Override
    public String toString() {
        return "Record{ status=" + status + ", time='" + time + '\'' + '}';
    }
}
