package ru.job4j.tttconsole;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Класс игры в 3Т
 */
public class Game {
    private final Board board;
    private final Map<Mark, Player> players;
    private final int wincount;
    private int count = 1;
    private final IPrint3T print;

    /**
     * Конструктор
     * @param board поле для игры
     * @param wincount количество значков подрял для выигрыша
     * @param one первый игрок
     * @param two второй игрок
     * @param print система вывода
     */
    public Game(Board board, int wincount, Player one, Player two, IPrint3T print) {
        this.board = board;
        players = Map.of(Mark.X, one, Mark.O, two);
        this.wincount = wincount;
        this.print = print;
    }

    /**
     * Метод реализует механизм игры
     */
    public void doGame() {
        print.println("Game Tic-Tac-Toe");
        print.println("Boards size:" + board.getSize());
        print.println("Win count:" + wincount);
        for (Map.Entry<Mark, Player> plr : players.entrySet()) {
            print.print(String.format("Player:%s Mark:%s%n", plr.getValue().getName(), plr.getKey().getPrint()));
        }
        print.println(String.format("%n"));
        int maxcount = board.getSize() * board.getSize();
        boolean go = true;
        while (go) {
            for (Map.Entry<Mark, Player> plr : players.entrySet()) {
                print.println(String.format("%n"));
                print.println("Move: " + count);
                Point step = plr.getValue().getStep(board);
                board.setPointVal(step, plr.getKey());
                print.println("Player " + plr.getValue().getName() + " (" + plr.getKey().getPrint() + ") go to " + step);
                print.printBoard(board);
                count++;
                if (count > maxcount) {
                    go = false;
                    break;
                }
                if (this.isWin(step)) {
                    print.println("Player " + plr.getValue().getName() + " (" + plr.getKey().getPrint() + ") is WINNER");
                    go = false;
                    break;
                }
            }
        }
        if (count > maxcount) {
            print.println("End game. No more moves ((( DRAW");
        }
    }

    /**
     * Метод определяет выигрышную ситуацию на доске после хода в точку
     * @param point точка
     * @return есть выигрышь
     */
    public boolean isWin(Point point) {
        Mark currmark = board.getPointVal(point);
        Map<Point, List<Point>> orientation = new HashMap<>();
        orientation.put(new Point(-1, -1), new ArrayList<>());
        orientation.put(new Point(-1, 0), new ArrayList<>());
        orientation.put(new Point(-1, 1), new ArrayList<>());
        orientation.put(new Point(0, 1), new ArrayList<>());
        orientation.put(new Point(1, 1), new ArrayList<>());
        orientation.put(new Point(1, 0), new ArrayList<>());
        orientation.put(new Point(1, -1), new ArrayList<>());
        orientation.put(new Point(0, -1), new ArrayList<>());
        for (Map.Entry<Point, List<Point>> ent: orientation.entrySet()) {
            Point curr = point;
            do {
                Point newp = new Point(curr.getX() + ent.getKey().getX(), curr.getY() + ent.getKey().getY());
                if (currmark.equals(board.getPointVal(newp))) {
                    ent.getValue().add(newp);
                    curr = newp;
                } else {
                    curr = point;
                }
            }
            while (!curr.equals(point));
        }
        return (orientation.get(new Point(-1, -1)).size() + orientation.get(new Point(1, 1)).size() + 1) >= wincount
                || (orientation.get(new Point(-1, 0)).size() + orientation.get(new Point(1, 0)).size() + 1) >= wincount
                || (orientation.get(new Point(-1, 1)).size() + orientation.get(new Point(1, -1)).size() + 1) >= wincount
                || (orientation.get(new Point(0, -1)).size() + orientation.get(new Point(0, 1)).size() + 1) >= wincount;
    }

    public static void main(String[] args) {
        Board board = new Board(3);
        Game game = new Game(board, 3, new AIPlayer("AIRobot"),
                                                new HumanPlayer("Boris", new Print3TConsole(System.in, System.out)),
                                                new Print3TConsole(System.in, System.out));
//        Game game = new Game(board, 3, new AIPlayer("AIRobot"),
//                                      new AIPlayer("AIRobot2"),
//                                      new Print3TConsole(System.in, System.out));
//        Game game = new Game(board, 3, new HumanPlayer("Alex", new Print3TConsole(System.in, System.out)),
//                                      new HumanPlayer("Boris", new Print3TConsole(System.in, System.out)),
//                                      new Print3TConsole(System.in, System.out));
        game.doGame();
    }
}
