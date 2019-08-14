package ru.job4j.foods;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

/**
 * Класс моделирует продуктовый склад, включая приход товара и его проверку на произвольную дату
 * За основу генерации дат берется текущая дата + год
 */
public class FoodmarketNew {
    private List<FoodAdv> incommingFoods = new ArrayList<>();

    /**
     * Метод генерирует приход определенного продукта на основной склад
     * @param genclass класс продукта
     * @param cnt количество
     */
    private void generateFood(Class<? extends Food> genclass, int cnt, boolean vegetable, boolean recycle) {
        Random rnd = new Random(System.currentTimeMillis());
        Long currTime = System.currentTimeMillis();
        for (var i = 0; i < cnt; i++) {
            Food food = null;
            try {
                food = genclass.getConstructor().newInstance();
            } catch (Exception e) {
                e.printStackTrace();
            }
            food.setName(genclass.getSimpleName() + rnd.nextInt(10000));
            food.setCreateDate(new Date(currTime));
            food.setExpaireDate(new Date(currTime + (long) (Math.random() * 31540000000L)));
            food.setPrice((double) rnd.nextInt(10000));
            food.setDiscountPrice(food.getPrice() * ((double) rnd.nextInt(100)) / 100D);
            FoodAdv fooda = new FoodAdv(food, vegetable, recycle);
            incommingFoods.add(fooda);
        }
    }

    public static void main(String[] args) {
        FoodmarketNew fm = new FoodmarketNew();

        List<Store<Food>> liststore = new ArrayList<>();
        liststore.add(new Cold("Cold"));
        Warehouse ware = new Warehouse("Warehouse", 5);
        liststore.add(ware);
        liststore.add(new WarehouseAdv("WarehouseAdv", ware));
        liststore.add(new Shop("Shop"));
        Trash trash = new Trash("Trash");
        liststore.add(new Recycle("Recycle", trash));
        liststore.add(trash);
        fm.generateFood(Apple.class, 5, true, true);
        fm.generateFood(Rice.class, 5, false, false);
        fm.generateFood(Bread.class, 5, false, true);
        fm.generateFood(Meat.class, 5, false, false);
        fm.generateFood(Banana.class, 5, true, true);
        fm.generateFood(Carrot.class, 5, true, true);
        fm.generateFood(Melon.class, 5, true, false);
        fm.generateFood(Potato.class, 5, true, true);
        Date today = new Date(System.currentTimeMillis() + (long) (Math.random() * 31540000000L));
        fm.incommingFoods.forEach((x) -> {
            try {
                ControlQuality.pass(today, (Food) x, liststore);
            } catch (NoRulesException e) {
                System.out.println(x + "никуда не попал ((");
            }
        });
        System.out.println("Current date:" + today);
        for (Store<Food> store: liststore) {
            System.out.println(store.getName() + ":");
            store.listGoods().stream().map((x) -> (FoodAdv) x).forEach((x) -> System.out.println(x + "\t\t\t" + x.expPercent(today) + " prc"));
        }
    }
}
