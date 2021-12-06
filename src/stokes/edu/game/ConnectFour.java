package stokes.edu.game;


import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class ConnectFour {
    private static int[][] board = new int[6][7];
    private static Checker previousChecker = null;

    public static boolean dropInColumn(int column, Checker checker) {
        if (checker != null && previousChecker == checker) throw new RuntimeException("You must take turns");
        previousChecker = checker;
        if (column < 0 || column >=7) throw new RuntimeException("Column not found");
        for (int i = 5; i >= 0; i--) {
            Coordinate coordinate = new Coordinate(i, column);
            if (getCoordinateVal(coordinate) > 0) continue;
            setCoordinateVal(coordinate, checker);
            if (victoryAttained(coordinate, checker, VictoryPath.HORIZONTAL)) return true;
            if (victoryAttained(coordinate, checker, VictoryPath.VERTICAL)) return true;
            if (victoryAttained(coordinate, checker, VictoryPath.DIAGONAL_UP)) return true;
            if (victoryAttained(coordinate, checker, VictoryPath.DIAGONAL_DOWN)) return true;
            if (drawAttained()) throw new RuntimeException("Game ends in tie");
            return false;
        }

        throw new RuntimeException("Column slots all full");
    }

    private static boolean victoryAttained(Coordinate coordinate, Checker checker, VictoryPath path) {
        int connectedCount = 0;
        Queue<Coordinate> coordinates = new LinkedList<Coordinate>();
        coordinates.add(coordinate);
        while(!coordinates.isEmpty()) {
            Coordinate currentCoordinate = coordinates.poll();
            if (getCoordinateVal(currentCoordinate) != checker.val) continue;
            connectedCount++;
            if (connectedCount > 3) return true;

            // check left
            int leftCol = currentCoordinate.column-1;
            if (leftCol >= 0 && path == VictoryPath.HORIZONTAL && directionAllowed(currentCoordinate, CurrentDirection.LEFT))
                coordinates.add(new Coordinate(currentCoordinate.row, leftCol, CurrentDirection.LEFT));
            // check right
            int rightCol = currentCoordinate.column+1;
            if (rightCol < 7 && path == VictoryPath.HORIZONTAL && directionAllowed(currentCoordinate, CurrentDirection.RIGHT))
                coordinates.add(new Coordinate(currentCoordinate.row, rightCol, CurrentDirection.RIGHT));

            // check down
            int downRow = currentCoordinate.row+1;
            if (downRow < 6 && path == VictoryPath.VERTICAL && directionAllowed(currentCoordinate, CurrentDirection.DOWN))
                coordinates.add(new Coordinate(downRow, currentCoordinate.column, CurrentDirection.DOWN));
            // check up
            int upRow = currentCoordinate.row-1;

            // check left up diagonal
            if (path == VictoryPath.DIAGONAL_DOWN && upRow >= 0 && leftCol >=0 && directionAllowed(currentCoordinate, CurrentDirection.DIAGONAL_LEFT_UP))
                coordinates.add(new Coordinate(upRow, leftCol, CurrentDirection.DIAGONAL_LEFT_UP));
            // check right down diagonal
            if (path == VictoryPath.DIAGONAL_DOWN && downRow < 6 && rightCol < 7 && directionAllowed(currentCoordinate, CurrentDirection.DIAGONAL_RIGHT_DOWN))
                coordinates.add(new Coordinate(downRow, rightCol, CurrentDirection.DIAGONAL_RIGHT_DOWN));

            // check left down diagonal
            if (path == VictoryPath.DIAGONAL_UP && downRow < 6 && leftCol >= 0 && directionAllowed(currentCoordinate, CurrentDirection.DIAGONAL_LEFT_DOWN))
                coordinates.add(new Coordinate(downRow, leftCol, CurrentDirection.DIAGONAL_LEFT_DOWN));
            // check right up diagonal
            if (path == VictoryPath.DIAGONAL_UP && upRow >=0 && rightCol < 7 && directionAllowed(currentCoordinate, CurrentDirection.DIAGONAL_RIGHT_UP))
                coordinates.add(new Coordinate(upRow, rightCol, CurrentDirection.DIAGONAL_RIGHT_UP));

        }

        return connectedCount > 3;
    }

    private static boolean directionAllowed(Coordinate coordinate, CurrentDirection direction) {
        if (coordinate.direction == CurrentDirection.ALL) return true;
        return coordinate.direction == direction;
    }

    private static boolean drawAttained() {
        for (int i=0; i < 6; i++) {
            for (int j=0; j < 7; j++){
                Coordinate coordinate = new Coordinate(i, j);
                if (getCoordinateVal(coordinate) == 0) return false;
            }
        }

        return true;
    }

    private static int getCoordinateVal(Coordinate coordinate) {
        return board[coordinate.row][coordinate.column];
    }

    private static void setCoordinateVal(Coordinate coordinate, Checker checker) {
        board[coordinate.row][coordinate.column] = checker.val;
    }

    static class Coordinate {
        int row;
        int column;
        CurrentDirection direction;

        Coordinate(int row, int column) {
            this.row = row;
            this.column= column;
            this.direction = CurrentDirection.ALL;
        }

        Coordinate(int row, int column, CurrentDirection direction) {
            this.row = row;
            this.column = column;
            this.direction = direction;
        }
    }

    static enum VictoryPath {
        HORIZONTAL,
        VERTICAL,
        DIAGONAL_UP,
        DIAGONAL_DOWN;
    }

    static enum CurrentDirection {
        ALL,
        LEFT,
        RIGHT,
        UP,
        DOWN,
        DIAGONAL_LEFT_UP,
        DIAGONAL_RIGHT_UP,
        DIAGONAL_LEFT_DOWN,
        DIAGONAL_RIGHT_DOWN;
    }

    static enum Checker {
        RED(1),
        BLACK(2);

        public int val = 0;
        Checker(int val) {
            this.val = val;
        }

    }


    public static void main(String[] args) {
//        dropInColumn(0, Checker.BLACK);
//        dropInColumn(0, Checker.RED);
//        dropInColumn(0, Checker.BLACK);
//        dropInColumn(0, Checker.RED);
//        dropInColumn(0, Checker.BLACK);
//        dropInColumn(0, Checker.RED);
//
//        dropInColumn(1, Checker.BLACK);
//        dropInColumn(1, Checker.RED);
//        dropInColumn(1, Checker.BLACK);
//        dropInColumn(1, Checker.RED);
//        dropInColumn(1, Checker.BLACK);
//        dropInColumn(1, Checker.RED);
//
//        dropInColumn(2, Checker.BLACK);
//        dropInColumn(2, Checker.RED);
//        dropInColumn(2, Checker.BLACK);
//        dropInColumn(2, Checker.RED);
//        dropInColumn(2, Checker.BLACK);
//        dropInColumn(2, Checker.RED);
//
//        dropInColumn(3, Checker.BLACK);
//        dropInColumn(3, Checker.RED);
//        dropInColumn(3, Checker.BLACK);
//        dropInColumn(3, Checker.RED);
//        dropInColumn(3, Checker.BLACK);
//        dropInColumn(3, Checker.RED);
//
//        dropInColumn(4, Checker.BLACK);
//        dropInColumn(4, Checker.RED);
//        dropInColumn(4, Checker.BLACK);
//        dropInColumn(4, Checker.RED);
//        dropInColumn(4, Checker.BLACK);
//        dropInColumn(4, Checker.RED);
//
//        dropInColumn(5, Checker.BLACK);
//        dropInColumn(5, Checker.RED);
//        dropInColumn(5, Checker.BLACK);
//        dropInColumn(5, Checker.RED);
//        dropInColumn(5, Checker.BLACK);
//        dropInColumn(5, Checker.RED);
//
//        dropInColumn(6, Checker.BLACK);
//        dropInColumn(6, Checker.RED);
//        dropInColumn(6, Checker.BLACK);
//        dropInColumn(6, Checker.RED);
//        dropInColumn(6, Checker.BLACK);
//        dropInColumn(6, Checker.RED);


//        // Horizontal win
//        boolean victory = false;
//        victory = dropInColumn(0, Checker.BLACK);
//        if (victory) System.out.println("Black wins");
//        victory = dropInColumn(1, Checker.BLACK);
//        if (victory) System.out.println("Black wins");
//        victory = dropInColumn(3, Checker.BLACK);
//        if (victory) System.out.println("Black wins");
//        victory = dropInColumn(2, Checker.BLACK);
//        if (victory) System.out.println("Black wins");

//        // Diagonal win
//        boolean victory = false;
//        victory = dropInColumn(0, Checker.BLACK);
//        victory = dropInColumn(0, Checker.RED);
//        victory = dropInColumn(0, Checker.BLACK);
//        victory = dropInColumn(0, Checker.BLACK);
//
//        if (victory) System.out.println("Black wins");
//        victory = dropInColumn(1, Checker.BLACK);
//        victory = dropInColumn(1, Checker.RED);
//        victory = dropInColumn(1, Checker.BLACK);
//        if (victory) System.out.println("Black wins");
//        victory = dropInColumn(2, Checker.RED);
//        victory = dropInColumn(2, Checker.BLACK);
//        if (victory) System.out.println("Black wins");
//        victory = dropInColumn(3, Checker.BLACK);
//        if (victory) System.out.println("Black wins");

//        // Diagonal win
//        boolean victory = false;
//        victory = dropInColumn(3, Checker.BLACK);
//        victory = dropInColumn(3, Checker.RED);
//        victory = dropInColumn(3, Checker.BLACK);
//        victory = dropInColumn(1, Checker.RED);
//
//        victory = dropInColumn(3, Checker.BLACK);
//        victory = dropInColumn(6, Checker.RED);
//
//        if (victory) System.out.println("Black wins");
//        victory = dropInColumn(2, Checker.BLACK);
//        victory = dropInColumn(2, Checker.RED);
//        victory = dropInColumn(2, Checker.BLACK);
//        if (victory) System.out.println("Black wins");
//        victory = dropInColumn(6, Checker.RED);
//        victory = dropInColumn(1, Checker.BLACK);
//        if (victory) System.out.println("Black wins");
//        victory = dropInColumn(5, Checker.RED);
//        victory = dropInColumn(0, Checker.BLACK);
//        if (victory) System.out.println("Black wins");

//        // Vertical win
//        boolean victory = false;
//        victory = dropInColumn(0, Checker.BLACK);
//        if (victory) System.out.println("Black wins");
//        victory = dropInColumn(0, Checker.BLACK);
//        if (victory) System.out.println("Black wins");
//        victory = dropInColumn(0, Checker.BLACK);
//        if (victory) System.out.println("Black wins");
//        victory = dropInColumn(0, Checker.BLACK);
//        if (victory) System.out.println("Black wins");

        boolean victory = false;
        victory = dropInColumn(3, Checker.BLACK);
        if (victory) System.out.println("Black wins");

        victory = dropInColumn(3, Checker.RED);
        if (victory) System.out.println("Red wins");

        victory = dropInColumn(4, Checker.BLACK);
        if (victory) System.out.println("Black wins");

        victory = dropInColumn(5, Checker.RED);
        if (victory) System.out.println("Red wins");

        victory = dropInColumn(2, Checker.BLACK);
        if (victory) System.out.println("Black wins");

        victory = dropInColumn(1, Checker.RED);
        if (victory) System.out.println("Red wins");

        victory = dropInColumn(4, Checker.BLACK);
        if (victory) System.out.println("Black wins");

        victory = dropInColumn(4, Checker.RED);
        if (victory) System.out.println("Red wins");

        victory = dropInColumn(2, Checker.BLACK);
        if (victory) System.out.println("Black wins");

        victory = dropInColumn(5, Checker.RED);
        if (victory) System.out.println("Red wins");

        victory = dropInColumn(2, Checker.BLACK);
        if (victory) System.out.println("Black wins");

        victory = dropInColumn(2, Checker.RED);
        if (victory) System.out.println("Red wins");

        victory = dropInColumn(1, Checker.BLACK);
        if (victory) System.out.println("Black wins");

        victory = dropInColumn(5, Checker.RED);
        if (victory) System.out.println("Red wins");

        victory = dropInColumn(5, Checker.BLACK);
        if (victory) System.out.println("Black wins");

        victory = dropInColumn(6, Checker.RED);
        if (victory) System.out.println("Red wins");

        victory = dropInColumn(1, Checker.BLACK);
        if (victory) System.out.println("Black wins");

        victory = dropInColumn(6, Checker.RED);
        if (victory) System.out.println("Red wins");

        victory = dropInColumn(1, Checker.BLACK);
        if (victory) System.out.println("Black wins");

        victory = dropInColumn(1, Checker.RED);
        if (victory) System.out.println("Red wins");

        victory = dropInColumn(0, Checker.BLACK);
        if (victory) System.out.println("Black wins");

        victory = dropInColumn(6, Checker.RED);
        if (victory) System.out.println("Red wins");

        victory = dropInColumn(6, Checker.BLACK);
        if (victory) System.out.println("Black wins");

        victory = dropInColumn(3, Checker.RED);
        if (victory) System.out.println("Red wins");

        for (int i=0; i < 6; i++)
            System.out.println(Arrays.toString(board[i]));
    }


}
