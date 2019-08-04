package ru.job4j.foods;

import lombok.*;

import java.util.Date;

/**
 * Класс определяет продукт
 */
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Food {

    @Getter
    @Setter
    private String name;

    @Getter
    @Setter
    private Date createDate;

    @Getter
    @Setter
    private Date expaireDate;

    @Getter
    @Setter
    private Double price;

    @Getter
    @Setter
    private Double discountPrice;

    /**
     * Метод подсчитывает процент годности продукта
     * @param currentdate дата проверки
     * @return процент от 0 до 100
     */
    public int expPercent(Date currentdate) {
        int result = 0;
        if ((currentdate.getTime() - createDate.getTime() > 0)) {
            int d2 = (int) (100 * ((double) (currentdate.getTime() - createDate.getTime()) / (expaireDate.getTime() - createDate.getTime())));
            result = d2 > 100 ? 100 : d2;
        }
        return result;
    }
}
