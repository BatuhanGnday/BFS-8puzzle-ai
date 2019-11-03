package com.ai.project;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Getter
public class Node {
    private Node parent;
    private List<Node> children;
    private ChessBoard chessBoard;

    public Node(Node parent, ChessBoard chessBoard) {
        this.parent = parent;
        this.children = new ArrayList<>();
        this.chessBoard = chessBoard;
    }

    public void moveToAllDirections() {
        for (ChessBoardMoveDirection moveDirection : ChessBoardMoveDirection.ALL_POSSIBLE_DIRECTIONS) {
            if (!this.chessBoard.canMoveTo(moveDirection)) {
                continue;
            }

            ChessBoard copiedChessBoard = chessBoard.copy();
            copiedChessBoard.moveTo(moveDirection);

            // Create new node
            Node node = new Node(this, copiedChessBoard);

            // Add new node as child
            this.children.add(node);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Node node = (Node) o;
        return Objects.equals(chessBoard, node.chessBoard);
    }
}
