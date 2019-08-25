package ru.job4j.tttconsole;

/**
 * Класс игрока в 3Т
 */
public class HumanPlayer extends Player {
    private IPrint3T print;

    /**
     * Конструктор
     * @param name имя
     * @param print система вывода
     * */
    public HumanPlayer(String name, IPrint3T print) {
        super(name);
        this.print = print;
    }

    /**
     * Метод возвращает следующий введенный ход ход игрока в 3Т
     * @param board
     * @return
     */
    @Override
    public Point getStep(Board board) {
        Point result = null;
        while (result == null) {
            String in = print.inputLine(this.getName() + " enter your step:");
            if (in.matches("^[\\d]+-[\\d]+$")) {
                String[] xy = in.split("-");
                result = new Point(Integer.parseInt(xy[0]), Integer.parseInt(xy[1]));
                if (!board.checkRestictBoard(result) || !Mark.o.equals(board.getPointVal(result))) {
                    result = null;
                    print.println("Error enter. Try again.");
                }
            } else {
                print.println("Illegal enter. Try again.");
            }
        }
        return result;
    }
}
