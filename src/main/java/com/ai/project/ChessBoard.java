package com.ai.project;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class ChessBoard {
    private int[] board;

    /**
     * Creates a board with random elements in it.
     */
    public ChessBoard() {
        Integer[] nums = {0, 1, 2, 3, 4, 5, 6, 7, 8};
        ArrayList<Integer> list = new ArrayList<>(Arrays.asList(nums));
        Collections.shuffle(list);

        this.board = new int[9];

        for (int i = 0; i < list.size(); i++) {
            this.board[i] = list.get(i);
        }
    }

    /**
     * Create a board from given array.
     * @param board board
     */
    public ChessBoard(int[] board) {
        assert board.length == 9 : "board must contain 9 elements in it";

        for (int item : board) {
            assert item >= 0 && item <= 9 : "item must be between 0 and 9";
        }

        // TODO: Check for double items

        this.board = board;
    }

    /**
     * Finds the index of zero.
     * @return the index of zero.
     */
    private int findZeroIndex() {
        int zero = 0;

        while (this.board[zero] != 0) {
            zero++;
        }

        return zero;
    }

    private void swap(int firstIndex, int secondIndex) {
        int temp = this.board[secondIndex];

        this.board[secondIndex] = this.board[firstIndex];
        this.board[firstIndex] = temp;
    }

    public void moveTo(ChessBoardMoveDirection moveDirection) {
        if(!canMoveTo(moveDirection)) {
            throw new RuntimeException("Can't move to " + moveDirection);
        }

        int zIndex = findZeroIndex();

        switch (moveDirection) {
            case LEFT:
                swap(zIndex - 1, zIndex);
                break;

            case RIGHT:
                swap(zIndex + 1, zIndex);
                break;

            case UP:
                swap(zIndex - 3, zIndex);
                break;

            case DOWN:
                swap(zIndex + 3, zIndex);
                break;

            default:
                throw new RuntimeException("Unhandled move direction: " + moveDirection);
        }
    }

    public boolean canMoveTo(ChessBoardMoveDirection moveDirection) {
        int zIndex = findZeroIndex();

        switch (moveDirection) {
            case LEFT:
                return zIndex != 0 && zIndex != 3 && zIndex != 6;

            case RIGHT:
                return zIndex != 2 && zIndex != 5 && zIndex != 8;

            case UP:
                return zIndex != 0 && zIndex != 1 && zIndex != 2;

            case DOWN:
                return zIndex != 6 && zIndex != 7 && zIndex != 8;

            default:
                throw new RuntimeException("Unhandled move direction: " + moveDirection);
        }
    }

    // check if state is goal
    public boolean isGoal() {
        int[] goalState = {0, 1, 2,
                3, 4, 5,
                6, 7, 8};
        return Arrays.equals(this.board, goalState);
    }

    // prints the current state
    @Override
    public String toString() {
        return "" + board[0] + " " + board[1] + " " + board[2] + "\n" +
                    board[3] + " " + board[4] + " " + board[5] + "\n" +
                    board[6] + " " + board[7] + " " + board[8];

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ChessBoard that = (ChessBoard) o;
        return Arrays.equals(board, that.board);
    }

    public ChessBoard copy() {
        return new ChessBoard(this.board.clone());
    }
}
