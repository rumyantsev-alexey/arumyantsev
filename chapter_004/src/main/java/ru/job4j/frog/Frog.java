package ru.job4j.frog;

import java.util.ArrayList;
import java.util.List;

/**
 * Класс реализует поведение лягушки согласно условиям задачи
 */
public class Frog {

    private static final int CIRCLES = 10;
    private static final int SECTORS = 16;
    private static final int MAXJUMP = 100;
    private final Point begin;
    private final Point end;
    private final List<Point> forest;
    private int turn = 0;
    private List<Point> currState = new ArrayList<>();

    public Frog(final Point begin, final Point end, final List<Point> forest) {
        if (!validPoint(begin) || !validPoint(end)) {
            throw new IllegalArgumentException("Ошибка в начальных параметрах лягухи");
        }
        this.begin = begin;
        this.end = end;
        for (Point pt: forest) {
            if (!validPoint(pt)) {
                throw new IllegalArgumentException("Ошибка в параметрах деревьев");
            }
        }
        this.forest = forest;
        currState.add(begin);
    }

    /**
     * метод проверяет что точка находится в границах поля
     * @param point
     * @return находится?
     */
    private static boolean validPoint(final Point point) {
        return point.getX() > 0 && point.getX() < (CIRCLES + 1) && point.getY() > 0 && point.getY() < (SECTORS + 1);
    }

    /**
     * Метод реализует движение лягушки к цели
     * @return список необходимых скачков лягушки для достижения цели (либо null - если цель не достигнута за MAXJUMP прыжков)
     */
    public List<Point> runFrog() {
        List<Point> result = new ArrayList<>();
        Point win;
        boolean letsGo;
        do {
            jumpFrog();
            System.out.printf("Прыжок: %s%n", turn);
            System.out.printf("Точек приземления: %s%n---------------%n", currState.size());
            win = checkWin();
            letsGo = win == null;
        } while (letsGo && turn < MAXJUMP);
        System.out.println(!letsGo ? "WWWWiIIINNNN" : "НЕ СМОГЛА (((");
/*        while (win != null && !letsGo) {
            result.add(win);
            win = win.getParent();
        } */
        for (var i = 0; (i < turn + 1) && !letsGo; i++) {
            result.add(win);
            win = win.getParent();
        }
        return result;
    }

    /**
     * Метод проверяет попала ли лягушка в финишную точку при текущем прыжке
     * @return финишную точку или null если не попала
     */
    private Point checkWin() {
        Point result = null;
        for (Point pt: currState) {
            if (end.equals(pt)) {
                result = pt;
                break;
            }
        }
        return result;
    }

    /**
     * Метод генерирует возможные положения лягушки в новом прыжке
     */
    private void jumpFrog() {
        List<Point> result = new ArrayList<>();
        for (Point point: currState) {
            int x = point.getX();
            int y = point.getY();
            result.add(normaizatPoint(new Point(x, y + 3, point)));
            result.add(normaizatPoint(new Point(x + 1, y + 2, point)));
            result.add(normaizatPoint(new Point(x + 2, y + 1, point)));
            result.add(normaizatPoint(new Point(x - 1, y + 2, point)));
            result.add(normaizatPoint(new Point(x + 2, y + 1, point)));
        }
        currState = checkRestrictPoints(result);
        turn++;
    }

    /**
     * Метод проверяет список точек на корректность местоположения и столкновения с деревьями
     * @param points список точек
     * @return новый список корректных точек из старого списка
     */
    private List<Point> checkRestrictPoints(final List<Point> points) {
        List<Point> result = new ArrayList<>();
        for (Point pt: points) {
            if (checkRestrictPoint(pt)) {
                result.add(pt);
            }
        }
        return result;
    }

    /**
     * Метод проверяет точеку на корректность местоположения и столкновения с деревьями
     * @param point точка
     * @return успех
     */
    private boolean checkRestrictPoint(final Point point) {
        boolean result = validPoint(point);
        if (result) {
            for (Point pt: forest) {
                if (point.equals(pt)) {
                    result = false;
                    break;
                }
            }
        }
        return result;
    }

    /**
     * Метод нормализует координаты точек при превышении границы секторов
     * @param point
     * @return
     */
    private Point normaizatPoint(Point point) {
        int dy = point.getY() % SECTORS;
        return new Point(point.getX(), dy == 0 ? 16 : dy, point.getParent());
    }


}
