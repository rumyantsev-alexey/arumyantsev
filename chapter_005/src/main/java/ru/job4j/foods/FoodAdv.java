package ru.job4j.foods;

import lombok.Getter;
import lombok.Setter;

public class FoodAdv extends Food {

    @Getter
    @Setter
    private boolean vegetable;

    @Getter
    @Setter
    private boolean canReproduct;

    public FoodAdv(final Food food, boolean vegetable, boolean recycle) {
        this.setName(food.getName());
        this.setCreateDate(food.getCreateDate());
        this.setExpaireDate(food.getExpaireDate());
        this.setPrice(food.getPrice());
        this.setDiscountPrice(food.getDiscountPrice());
        this.vegetable = vegetable;
        this.canReproduct = recycle;
    }



}

